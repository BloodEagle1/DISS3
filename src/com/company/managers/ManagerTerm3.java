package com.company.managers;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;

//meta! id="30"
public class ManagerTerm3 extends Manager
{
	public ManagerTerm3(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentPohybu", id="33", type="Request"
	public void processVystupTerm3(MessageForm message)
	{
		message.setCode(Mc.start);
		message.setAddressee(Id.procesVystupuTerm3);
		startContinualAssistant(message);
	}

	//meta! sender="ProcesVystupuTerm3", id="53", type="Finish"
	public void processFinish(MessageForm message)
	{
		message.setCode(Mc.vystupTerm3);
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
		case Mc.finish:
			processFinish(message);
		break;

		case Mc.vystupTerm3:
			processVystupTerm3(message);
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