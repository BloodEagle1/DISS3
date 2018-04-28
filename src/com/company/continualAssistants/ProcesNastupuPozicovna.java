package com.company.continualAssistants;

import OSPABA.*;
import OSPRNG.UniformContinuousRNG;
import com.company.entity.Minibus;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.agents.*;
import OSPABA.Process;

//meta! id="55"
public class ProcesNastupuPozicovna extends Process
{

	private static UniformContinuousRNG genNastupu = new UniformContinuousRNG(10.0,14.0);

	public ProcesNastupuPozicovna(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentPozicovna", id="56", type="Start"
	public void processStart(MessageForm message)
	{
		double casNastupu = 0.0;
		if (!myAgent().getRadZakPozicovna().isEmpty()){
			Minibus minibus = ((MyMessage)message).getMinibus();

			Zakaznik zakaznik = myAgent().getRadZakPozicovna().dequeue();
			minibus.nastupZakaznika(zakaznik);
			for (int i = 0; i < (zakaznik.getPocetCestujucich()); i++) {
				casNastupu += genNastupu.sample();
			}
		}

		message.setCode(Mc.nastupPoziconaHotovy);
		hold(casNastupu, message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentPozicovna", id="92", type="Notice"
	public void processNastupPoziconaHotovy(MessageForm message)
	{
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

		case Mc.nastupPoziconaHotovy:
			processNastupPoziconaHotovy(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentPozicovna myAgent()
	{
		return (AgentPozicovna)super.myAgent();
	}

}