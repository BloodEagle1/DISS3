package com.company.managers;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;

//meta! id="3"
public class ManagerPohybu extends Manager {
	public ManagerPohybu(int id, Simulation mySim, Agent myAgent) {
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication() {
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null) {
			petriNet().clear();
		}
	}

	//meta! sender="AgentTerm1", id="31", type="Response"
	public void processNastupTerm1(MessageForm message) {
		message.setCode(Mc.presunMinibusu);
		message.setAddressee(mySim().findAgent(Id.agentMinibusov));
		request(message);
	}

	//meta! sender="AgentTerm2", id="32", type="Response"
	public void processNastupTerm2(MessageForm message) {
		message.setCode(Mc.presunMinibusu);
		message.setAddressee(mySim().findAgent(Id.agentMinibusov));
		request(message);
	}

	//meta! sender="AgentModelu", id="16", type="Notice"
	public void processPrichZakPozicovna(MessageForm message) {
		message.setCode(Mc.prichZak);
		message.setAddressee(mySim().findAgent(Id.agentPozicovna));
		notice(message);
	}

	//meta! sender="AgentPozicovna", id="35", type="Response"
	public void processNastupPozicovna(MessageForm message) {
		message.setCode(Mc.presunMinibusu);
		message.setAddressee(mySim().findAgent(Id.agentMinibusov));
		request(message);
	}

	//meta! sender="AgentModelu", id="17", type="Notice"
	public void processPrichZakTerm2(MessageForm message) {
		message.setCode(Mc.prichZak);
		message.setAddressee(mySim().findAgent(Id.agentTerm2));
		notice(message);
	}

	//meta! sender="AgentTerm3", id="33", type="Response"
	public void processVystupTerm3(MessageForm message) {
		message.setCode(Mc.presunMinibusu);
		message.setAddressee(mySim().findAgent(Id.agentMinibusov));
		request(message);
	}

	//meta! sender="AgentModelu", id="19", type="Notice"
	public void processPrichZakTerm1(MessageForm message) {
		message.setCode(Mc.prichZak);
		message.setAddressee(mySim().findAgent(Id.agentTerm1));
		notice(message);
	}

	//meta! sender="AgentPozicovna", id="34", type="Response"
	public void processVystupPozicovna(MessageForm message) {
		message.setCode(Mc.presunMinibusu);
		message.setAddressee(mySim().findAgent(Id.agentMinibusov));
		request(message);
	}

	//meta! sender="AgentMinibusov", id="21", type="Response"
	public void processPresunMinibusu(MessageForm message) {
		switch (((MyMessage) message).getMinibus().getAktualnaZastavka()) {
			case "Terminal 1":
				message.setCode(Mc.nastupTerm1);
				message.setAddressee(mySim().findAgent(Id.agentTerm1));
				request(message);
				break;
			case "Terminal 2":
				message.setCode(Mc.nastupTerm2);
				message.setAddressee(mySim().findAgent(Id.agentTerm2));
				request(message);
				break;
			case "Terminal 3":
				message.setCode(Mc.vystupTerm3);
				message.setAddressee(mySim().findAgent(Id.agentTerm3));
				request(message);
				break;
			case "Pozicovna":
				if (((MyMessage) message).getMinibus().isVystup()){
					message.setCode(Mc.vystupPozicovna);
					message.setAddressee(mySim().findAgent(Id.agentPozicovna));
					request(message);
				}
				else {
					message.setCode(Mc.nastupPozicovna);
					message.setAddressee(mySim().findAgent(Id.agentPozicovna));
					request(message);
				}
				break;
		}
	}

	//meta! sender="AgentModelu", id="18", type="Notice"
	public void processPrichMinibusuAgentModelu(MessageForm message) {
		message.setCode(Mc.prichMinibusu);
		message.setAddressee(mySim().findAgent(Id.agentMinibusov));
		request(message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message) {
		switch (message.code()) {
		}
	}

	//meta! sender="AgentMinibusov", id="20", type="Response"
	public void processPrichMinibusuAgentMinibusov(MessageForm message) {
		message.setCode(Mc.nastupTerm1);
		message.setAddressee(mySim().findAgent(Id.agentTerm1));
		request(message);
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

		case Mc.prichMinibusu:
			switch (message.sender().id())
			{
			case Id.agentModelu:
				processPrichMinibusuAgentModelu(message);
			break;

			case Id.agentMinibusov:
				processPrichMinibusuAgentMinibusov(message);
			break;
			}
		break;

		case Mc.presunMinibusu:
			processPresunMinibusu(message);
		break;

		case Mc.nastupTerm1:
			processNastupTerm1(message);
		break;

		case Mc.prichZakPozicovna:
			processPrichZakPozicovna(message);
		break;

		case Mc.nastupPozicovna:
			processNastupPozicovna(message);
		break;

		case Mc.vystupPozicovna:
			processVystupPozicovna(message);
		break;

		case Mc.prichZakTerm2:
			processPrichZakTerm2(message);
		break;

		case Mc.vystupTerm3:
			processVystupTerm3(message);
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
	public AgentPohybu myAgent() {
		return (AgentPohybu) super.myAgent();
	}

}