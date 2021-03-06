package com.company.managers;

import OSPABA.*;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.agents.*;

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
		message.setCode(Mc.start);
		message.setAddressee(Id.procesNastupuTerm1);
		startContinualAssistant(message);
	}

	//meta! sender="AgentPohybu", id="28", type="Notice"
	public void processPrichZak(MessageForm message)
	{
		Zakaznik zakaznik = ((MyMessage)message).getZakaznik();
		zakaznik.setVstupDoRaduTerm1(mySim().currentTime());
		myAgent().getRadZakTerm1().enqueue(zakaznik);
	}

	//meta! sender="ProcesNastupuTerm1", id="47", type="Finish"
	public void processFinish(MessageForm message)
	{
		if (!myAgent().getRadZakTerm1().isEmpty() &&
				((MyMessage)message).getMinibus().getPocetVolnychMiest() >= myAgent().getRadZakTerm1().peek().getPocetCestujucich()){
			message.setCode(Mc.start);
			message.setAddressee(Id.procesNastupuTerm1);
			startContinualAssistant(message);
		}else {
			message.setCode(Mc.nastupTerm1);
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