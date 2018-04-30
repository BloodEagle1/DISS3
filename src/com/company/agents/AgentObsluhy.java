package com.company.agents;

import OSPABA.*;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;

//meta! id="8"
public class AgentObsluhy extends Agent
{
	private int pocetPracovnikov;
	private int pocetVolnychPracovnikov;

	public AgentObsluhy(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		this.pocetPracovnikov = ((MySimulation) mySim()).getPocetPracovnikov();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
		this.pocetVolnychPracovnikov = ((MySimulation) mySim()).getPocetPracovnikov();
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


	public int getPocetPracovnikov() {
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