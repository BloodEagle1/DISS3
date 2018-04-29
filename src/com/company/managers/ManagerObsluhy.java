package com.company.managers;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;
import com.company.continualAssistants.*;
import com.company.instantAssistants.*;

//meta! id="8"
public class ManagerObsluhy extends Manager
{
	public ManagerObsluhy(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentPozicovna", id="24", type="Request"
	public void processObsluzZak(MessageForm message)
	{
		myAgent().znizPocetVolnychPracovnikov();
		message.setCode(Mc.start);
		message.setAddressee(Id.procesObsluhy);
		startContinualAssistant(message);
	}

	//meta! sender="ProcesObsluhy", id="61", type="Finish"
	public void processFinish(MessageForm message)
	{
		myAgent().zvysPocetVolnychPracovnikov();
		message.setCode(Mc.obsluzZak);
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
		case Mc.obsluzZak:
			processObsluzZak(message);
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
	public AgentObsluhy myAgent()
	{
		return (AgentObsluhy)super.myAgent();
	}

}