package com.company.agents;

import OSPABA.*;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;

//meta! id="8"
public class AgentObsluhy extends Agent
{
	private static final int pocetPracovnikov = Config.pocetPracovnikov;
	private int pocetVolnychPracovnikov;

	public AgentObsluhy(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		this.pocetVolnychPracovnikov = pocetPracovnikov;
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
		new ManagerObsluhy(Id.managerObsluhy, mySim(), this);
		new ProcesObsluhy(Id.procesObsluhy, mySim(), this);
		addOwnMessage(Mc.obsluzZak);
		addOwnMessage(Mc.obsluhaHotova);
	}
	//meta! tag="end"


	public static int getPocetPracovnikov() {
		return pocetPracovnikov;
	}

	public int getPocetVolnychPracovnikov() {
		return pocetVolnychPracovnikov;
	}

	public void zvysPocetVolnychPracovnikov(){
		pocetVolnychPracovnikov++;
	}

	public void znizPocetVolnychPracovnikov(){
		pocetVolnychPracovnikov++;
	}
}