package com.company.agents;

import OSPABA.*;
import com.company.simulation.*;
import com.company.managers.*;

//meta! id="3"
public class AgentPohybu extends Agent
{
	public AgentPohybu(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerPohybu(Id.managerPohybu, mySim(), this);
		addOwnMessage(Mc.nastupTerm1);
		addOwnMessage(Mc.nastupTerm2);
		addOwnMessage(Mc.prichZakPozicovna);
		addOwnMessage(Mc.nastupPozicovna);
		addOwnMessage(Mc.prichZakTerm2);
		addOwnMessage(Mc.prichZakTerm1);
		addOwnMessage(Mc.vystupTerm3);
		addOwnMessage(Mc.presunMinibusu);
		addOwnMessage(Mc.vystupPozicovna);
		addOwnMessage(Mc.prichMinibusu);
		addOwnMessage(Mc.odchodZakaznika);
	}
	//meta! tag="end"
}