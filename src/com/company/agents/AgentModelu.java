package com.company.agents;

import OSPABA.*;
import com.company.simulation.*;
import com.company.managers.*;

//meta! id="2"
public class AgentModelu extends Agent
{
	public AgentModelu(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
		MyMessage msg = new MyMessage(mySim());
		msg.setAddressee(this);
		msg.setCode(Mc.init);
		manager().notice(msg);
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerModelu(Id.managerModelu, mySim(), this);
		addOwnMessage(Mc.init);
		addOwnMessage(Mc.prichZakPozicovna);
		addOwnMessage(Mc.prichZakTerm2);
		addOwnMessage(Mc.prichZakTerm1);
		addOwnMessage(Mc.prichMinibusu);
		addOwnMessage(Mc.odchodZakaznika);
	}
	//meta! tag="end"

	public void spustiSimulaciu() {
		MyMessage message = new MyMessage(mySim());
		message.setCode(Mc.init);
		message.setAddressee(this);
		manager().notice(message);
	}
}