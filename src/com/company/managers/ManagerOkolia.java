package com.company.managers;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;
import com.company.continualAssistants.*;
import com.company.instantAssistants.*;

//meta! id="1"
public class ManagerOkolia extends Manager
{
	public ManagerOkolia(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentModelu", id="15", type="Notice"
	public void processInit(MessageForm message) {
		message.setAddressee(myAgent().findAssistant(Id.planovacPrichZakTerm1));
		startContinualAssistant(message);
		message.setAddressee(myAgent().findAssistant(Id.planovacPrichZakTerm2));
		startContinualAssistant(message);
		message.setAddressee(myAgent().findAssistant(Id.planovacPrichZakPozicovna));
		startContinualAssistant(message);
		message.setAddressee(myAgent().findAssistant(Id.planovacPrichMinibusov));
		startContinualAssistant(message);
	}

	//meta! sender="PlanovacPrichZakPozicovna", id="42", type="Finish"
	public void processFinishPlanovacPrichZakPozicovna(MessageForm message) {
		myAgent().zvysPocetZakaznikovVratenia();

		message.setAddressee(mySim().findAgent(Id.agentModelu));
		message.setCode(Mc.prichZakPozicovna);
		notice(message);
	}

	//meta! sender="PlanovacPrichZakTerm1", id="38", type="Finish"
	public void processFinishPlanovacPrichZakTerm1(MessageForm message) {
		myAgent().zvysPocetZakaznikov();

		message.setAddressee(mySim().findAgent(Id.agentModelu));
		message.setCode(Mc.prichZakTerm1);
		notice(message);
	}

	//meta! sender="PlanovacPrichMinibusov", id="44", type="Finish"
	public void processFinishPlanovacPrichMinibusov(MessageForm message) {
		message.setAddressee(mySim().findAgent(Id.agentModelu));
		message.setCode(Mc.prichMinibusu);
		notice(message);
	}

	//meta! sender="PlanovacPrichZakTerm2", id="40", type="Finish"
	public void processFinishPlanovacPrichZakTerm2(MessageForm message) {
		myAgent().zvysPocetZakaznikov();

		message.setAddressee(mySim().findAgent(Id.agentModelu));
		message.setCode(Mc.prichZakTerm2);
		notice(message);
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
			switch (message.sender().id())
			{
			case Id.planovacPrichZakTerm1:
				processFinishPlanovacPrichZakTerm1(message);
			break;

			case Id.planovacPrichZakPozicovna:
				processFinishPlanovacPrichZakPozicovna(message);
			break;

			case Id.planovacPrichMinibusov:
				processFinishPlanovacPrichMinibusov(message);
			break;

			case Id.planovacPrichZakTerm2:
				processFinishPlanovacPrichZakTerm2(message);
			break;
			}
		break;

		case Mc.init:
			processInit(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentOkolia myAgent()
	{
		return (AgentOkolia)super.myAgent();
	}

}