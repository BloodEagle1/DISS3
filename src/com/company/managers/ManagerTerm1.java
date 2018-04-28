package com.company.managers;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;
import com.company.continualAssistants.*;
import com.company.instantAssistants.*;

//meta! id="26"
public class ManagerTerm1 extends Manager
{
	public ManagerTerm1(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentPohybu", id="31", type="Request"
	public void processNastupTerm1(MessageForm message)
	{
		((MyMessage) message).getMinibus().setAktualnaZastavka("Terminal 1");
		message.setCode(Mc.start);
		message.setAddressee(Id.procesNastupuTerm1);
		startContinualAssistant(message);
	}

	//meta! sender="AgentPohybu", id="28", type="Notice"
	public void processPrichZak(MessageForm message)
	{
		myAgent().getRadZakTerm1().enqueue(((MyMessage)message).getZakaznik());
	}

	//meta! sender="ProcesNastupuTerm1", id="47", type="Finish"
	public void processFinish(MessageForm message)
	{
		message.setCode(Mc.nastupTerm2);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.prichZak:
			processPrichZak(message);
		break;

		case Mc.finish:
			processFinish(message);
		break;

		case Mc.nastupTerm1:
			processNastupTerm1(message);
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