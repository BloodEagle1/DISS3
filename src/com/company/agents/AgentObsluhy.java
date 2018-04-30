package com.company.agents;

import OSPABA.*;
import OSPStat.Stat;
import OSPStat.WStat;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;

//meta! id="8"
public class AgentObsluhy extends Agent
{
	private int pocetPracovnikov;
	private int pocetVolnychPracovnikov;
	private Stat vytazeniePracovnikov;

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
		this.vytazeniePracovnikov = new Stat();
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
		vytazeniePracovnikov.addSample(pocetPracovnikov - pocetVolnychPracovnikov);
		pocetVolnychPracovnikov++;
	}

	public void znizPocetVolnychPracovnikov(){
		vytazeniePracovnikov.addSample(pocetPracovnikov - pocetVolnychPracovnikov);
		pocetVolnychPracovnikov--;
	}

	public Stat getVytazeniePracovnikov() {
		return vytazeniePracovnikov;
	}
}