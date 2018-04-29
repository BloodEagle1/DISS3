package com.company.managers;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;

//meta! id="2"
public class ManagerModelu extends Manager {
	public ManagerModelu(int id, Simulation mySim, Agent myAgent) {
		super(id, mySim, myAgent);
		init();
		MyMessage message = new MyMessage(mySim());
		message.setAddressee(mySim().findAgent(Id.agentOkolia));
		message.setCode(Mc.init);
		notice(message);
	}

	@Override
	public void prepareReplication() {
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null) {
			petriNet().clear();
		}
	}

	//meta! sender="AgentOkolia", id="12", type="Notice"
	public void processPrichZakPozicovna(MessageForm message) {
		message.setCode(Mc.prichZakPozicovna);
		message.setAddressee(mySim().findAgent(Id.agentPohybu));
		notice(message);
	}

	//meta! sender="AgentOkolia", id="13", type="Notice"
	public void processPrichZakTerm2(MessageForm message) {
		message.setCode(Mc.prichZakTerm2);
		message.setAddressee(mySim().findAgent(Id.agentPohybu));
		notice(message);
	}

	//meta! sender="AgentOkolia", id="10", type="Notice"
	public void processPrichZakTerm1(MessageForm message) {
		message.setCode(Mc.prichZakTerm1);
		message.setAddressee(mySim().findAgent(Id.agentPohybu));
		notice(message);
	}

	//meta! sender="AgentOkolia", id="14", type="Notice"
	public void processPrichMinibusu(MessageForm message) {
		message.setCode(Mc.prichMinibusu);
		message.setAddressee(mySim().findAgent(Id.agentPohybu));
		notice(message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message) {
		switch (message.code()) {
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
		case Mc.prichZakTerm1:
			processPrichZakTerm1(message);
		break;

		case Mc.prichZakPozicovna:
			processPrichZakPozicovna(message);
		break;

		case Mc.prichZakTerm2:
			processPrichZakTerm2(message);
		break;

		case Mc.prichMinibusu:
			processPrichMinibusu(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentModelu myAgent() {
		return (AgentModelu) super.myAgent();
	}

}