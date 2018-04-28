package com.company.continualAssistants;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;
import OSPABA.Process;

//meta! id="52"
public class ProcesVystupuTerm3 extends Process
{
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