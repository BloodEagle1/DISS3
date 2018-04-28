package com.company.managers;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;
import com.company.continualAssistants.*;
import com.company.instantAssistants.*;

//meta! id="27"
public class ManagerTerm2 extends Manager
{
	public ManagerTerm2(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentPohybu", id="32", type="Request"
	public void processNastupTerm2(MessageForm message)
	{
		message.setCode(Mc.start);
		message.setAddressee(Id.procesNastupuTerm2);
		startContinualAssistant(message);
	}

	//meta! sender="AgentPohybu", id="29", type="Notice"
	public void processPrichZak(MessageForm message)
	{
		myAgent().getRadZakTerm2().enqueue(((MyMessage)message).getZakaznik());
	}

	//meta! sender="ProcesNastupuTerm2", id="50", type="Finish"
	public void processFinish(MessageForm message) {
		if (!myAgent().getRadZakTerm2().isEmpty() &&
				((MyMessage)message).getMinibus().getPocetVolnychMiest() >= myAgent().getRadZakTerm2().peek().getPocetCestujucich()){
			message.setCode(Mc.start);
			message.setAddressee(Id.procesNastupuTerm2);
			startContinualAssistant(message);
		}else {
			message.setCode(Mc.nastupTerm2);
			response(message);
		}
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

		case Mc.prichZak:
			processPrichZak(message);
		break;

		case Mc.nastupTerm2:
			processNastupTerm2(message);
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