package com.company.continualAssistants;

import OSPABA.*;
import OSPRNG.UniformContinuousRNG;
import com.company.entity.Minibus;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.agents.*;
import OSPABA.Process;

//meta! id="52"
public class ProcesVystupuTerm3 extends Process
{

	private static UniformContinuousRNG genVystupu = new UniformContinuousRNG(2.0,10.0);

	public ProcesVystupuTerm3(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentTerm3", id="53", type="Start"
	public void processStart(MessageForm message)
	{
		double casVystupu = 0.0;
		Minibus minibus = ((MyMessage)message).getMinibus();

		Zakaznik zakaznik = minibus.vystupZakaznika();
		for (int i = 0; i < (zakaznik.getPocetCestujucich()); i++) {
			casVystupu += genVystupu.sample();
		}
		message.setCode(Mc.vystupTerm3Hotovy);
		hold(casVystupu, message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentTerm3", id="90", type="Notice"
	public void processVystupTerm3Hotovy(MessageForm message)
	{
		assistantFinished(message);
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.vystupTerm3Hotovy:
			processVystupTerm3Hotovy(message);
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
	public AgentTerm3 myAgent()
	{
		return (AgentTerm3)super.myAgent();
	}

}