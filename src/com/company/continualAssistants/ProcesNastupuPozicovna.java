package com.company.continualAssistants;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;
import OSPABA.Process;

//meta! id="55"
public class ProcesNastupuPozicovna extends Process
{
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