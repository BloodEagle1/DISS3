package com.company.continualAssistants;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;
import OSPABA.Process;

//meta! id="49"
public class ProcesNastupuTerm2 extends Process
{
	public ProcesNastupuTerm2(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentTerm2", id="50", type="Start"
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

	//meta! sender="AgentTerm2", id="87", type="Notice"
	public void processNastupTerm2Hotovy(MessageForm message)
	{
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

		case Mc.nastupTerm2Hotovy:
			processNastupTerm2Hotovy(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentTerm2 myAgent()
	{
		return (AgentTerm2)super.myAgent();
	}

}