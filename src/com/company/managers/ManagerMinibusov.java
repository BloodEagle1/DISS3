package com.company.managers;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;

//meta! id="9"
public class ManagerMinibusov extends Manager
{
	public ManagerMinibusov(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentPohybu", id="21", type="Request"
	public void processPresunMinibusu(MessageForm message)
	{
		message.setCode(Mc.start);
		message.setAddressee(Id.procesPresunu);
		startContinualAssistant(message);
	}

	//meta! sender="ProcesPresunu", id="64", type="Finish"
	public void processFinish(MessageForm message)
	{
		message.setCode(Mc.presunMinibusu);
		response(message);
	}

	//meta! sender="AgentPohybu", id="20", type="Request"
	public void processPrichMinibusu(MessageForm message)
	{
		myAgent().vlozMinibus(((MyMessage)message).getMinibus());
		((MyMessage)message).getMinibus().setCielovaZastavka("Terminal 1");
		message.setCode(Mc.prichMinibusu);
		response(message);
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
		case Mc.prichMinibusu:
			processPrichMinibusu(message);
		break;

		case Mc.presunMinibusu:
			processPresunMinibusu(message);
		break;

		case Mc.finish:
			processFinish(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentMinibusov myAgent()
	{
		return (AgentMinibusov)super.myAgent();
	}

}