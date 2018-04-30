package com.company.continualAssistants;

import OSPABA.*;
import OSPRNG.ExponentialRNG;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.agents.*;

//meta! id="39"
public class PlanovacPrichZakTerm2 extends Scheduler
{
	private static final int HODINA = 3600;
	private static final double[] vstupy = {3.0,6.0,9.0,15.0,17.0,19.0,14.0,6.0,3.0,4.0,21.0,14.0,19.0,12.0,5.0,2.0,3.0,3.0};
	private static ExponentialRNG exponentialRNG1 = new ExponentialRNG(HODINA / vstupy[0]);
	private static ExponentialRNG exponentialRNG2 = new ExponentialRNG(HODINA / vstupy[1]);
	private static ExponentialRNG exponentialRNG3 = new ExponentialRNG(HODINA / vstupy[2]);
	private static ExponentialRNG exponentialRNG4 = new ExponentialRNG(HODINA / vstupy[3]);
	private static ExponentialRNG exponentialRNG5 = new ExponentialRNG(HODINA / vstupy[4]);
	private static ExponentialRNG exponentialRNG6 = new ExponentialRNG(HODINA / vstupy[5]);
	private static ExponentialRNG exponentialRNG7 = new ExponentialRNG(HODINA / vstupy[6]);
	private static ExponentialRNG exponentialRNG8 = new ExponentialRNG(HODINA / vstupy[7]);
	private static ExponentialRNG exponentialRNG9 = new ExponentialRNG(HODINA / vstupy[8]);
	private static ExponentialRNG exponentialRNG10 = new ExponentialRNG(HODINA / vstupy[9]);
	private static ExponentialRNG exponentialRNG11 = new ExponentialRNG(HODINA / vstupy[10]);
	private static ExponentialRNG exponentialRNG12 = new ExponentialRNG(HODINA / vstupy[11]);
	private static ExponentialRNG exponentialRNG13 = new ExponentialRNG(HODINA / vstupy[12]);
	private static ExponentialRNG exponentialRNG14 = new ExponentialRNG(HODINA / vstupy[13]);
	private static ExponentialRNG exponentialRNG15 = new ExponentialRNG(HODINA / vstupy[14]);
	private static ExponentialRNG exponentialRNG16 = new ExponentialRNG(HODINA / vstupy[15]);
	private static ExponentialRNG exponentialRNG17 = new ExponentialRNG(HODINA / vstupy[16]);
	private static ExponentialRNG exponentialRNG18 = new ExponentialRNG(HODINA / vstupy[17]);

	private ExponentialRNG[] genVstupov = {
			exponentialRNG1,exponentialRNG2,exponentialRNG3, exponentialRNG4,
			exponentialRNG5,exponentialRNG6,exponentialRNG7,exponentialRNG8,
			exponentialRNG9,exponentialRNG10,exponentialRNG11,exponentialRNG12,
			exponentialRNG13,exponentialRNG14,exponentialRNG15,exponentialRNG16,
			exponentialRNG17,exponentialRNG18};

	public PlanovacPrichZakTerm2(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentOkolia", id="40", type="Start"
	public void processStart(MessageForm message)
	{
		message.setCode(Mc.novyZakaznik);
		double cas = dajCasHoldu();
		if (cas > 0)
			hold(cas, message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentOkolia", id="82", type="Notice"
	public void processNovyZakaznik(MessageForm message)
	{
		MyMessage msg = new MyMessage((MyMessage) message);
		double cas = dajCasHoldu();
		if (cas > 0)
			hold(cas, msg);

		((MyMessage)message).setZakaznik(new Zakaznik(mySim()));
		assistantFinished(message);
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.start:
			processStart(message);
		break;

		case Mc.novyZakaznik:
			processNovyZakaznik(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentOkolia myAgent()
	{
		return (AgentOkolia)super.myAgent();
	}

	private int dajIndexGeneratoraVstupu(double cas){
		return (int)Math.floor((cas / (15 * 60))%17);
	}

	private double dajCasHoldu(){
		int indexGenVstupu = dajIndexGeneratoraVstupu(mySim().currentTime());
		double vysledokGeneratora = genVstupov[indexGenVstupu].sample();
		if (vysledokGeneratora > 900)
			vysledokGeneratora = 900;
		double casPoHolde = mySim().currentTime() + vysledokGeneratora;
		int indexGenVstupuPoHolde = dajIndexGeneratoraVstupu(casPoHolde);

		if (indexGenVstupuPoHolde != indexGenVstupu && vstupy[indexGenVstupu] < vstupy[indexGenVstupuPoHolde]){
			double vysledokNovehoGeneratora = genVstupov[indexGenVstupuPoHolde].sample();
			if (vysledokNovehoGeneratora > 900)
				vysledokNovehoGeneratora = 900;
			double pom = (((casPoHolde - (indexGenVstupuPoHolde * (15*60))) + vysledokNovehoGeneratora) / 2) + (((15 * 60)*indexGenVstupuPoHolde)-mySim().currentTime());
			//return (((casPoHolde - (indexGenVstupuPoHolde * (15*60))) + genVstupov[indexGenVstupuPoHolde].sample()) / 2) + (((15 * 60)*indexGenVstupuPoHolde)-mySim().currentTime());
			return pom;
		}else {
			return vysledokGeneratora;
		}
	}

}