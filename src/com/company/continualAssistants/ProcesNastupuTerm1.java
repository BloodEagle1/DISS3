package com.company.continualAssistants;

import OSPABA.*;
import OSPRNG.UniformContinuousRNG;
import com.company.entity.Minibus;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.agents.*;
import OSPABA.Process;

//meta! id="46"
public class ProcesNastupuTerm1 extends Process
{

	private static UniformContinuousRNG genNastupu = new UniformContinuousRNG(10.0,14.0);

	public ProcesNastupuTerm1(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentTerm1", id="47", type="Start"
	public void processStart(MessageForm message)
	{
		double casNastupu = 0.0;
		if (!myAgent().getRadZakTerm1().isEmpty()){
			Minibus minibus = ((MyMessage)message).getMinibus();
			Zakaznik zakaznik = myAgent().getRadZakTerm1().dequeue();
			myAgent().pridajDoStatCasVRade(mySim().currentTime() - zakaznik.getVstupDoRaduTerm1());
			minibus.nastupZakaznika(zakaznik);
			for (int i = 0; i < (zakaznik.getPocetCestujucich()); i++) {
				casNastupu += genNastupu.sample();
			}
		}
		message.setCode(Mc.nastupTerm1Hotovy);
		hold(casNastupu, message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentTerm1", id="85", type="Notice"
	public void processNastupTerm1Hotovy(MessageForm message)
	{
		assistantFinished(message);
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.nastupTerm1Hotovy:
			processNastupTerm1Hotovy(message);
		break;

		case Mc.start:
			processStart(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentTerm1 myAgent()
	{
		return (AgentTerm1)super.myAgent();
	}

}