package com.company.agents;

import OSPABA.*;
import OSPStat.Stat;
import OSPStat.WStat;
import com.company.entity.Pracovnik;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;

//meta! id="8"
public class AgentObsluhy extends Agent
{
	private int pocetPracovnikov;
	private int pocetVolnychPracovnikov;
	private Stat vytazeniePracovnikov;
	private Pracovnik[] pracovnici;

	public AgentObsluhy(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
		this.pocetPracovnikov = ((MySimulation) mySim()).getPocetPracovnikov();
		this.pocetVolnychPracovnikov = pocetPracovnikov;
		this.vytazeniePracovnikov = new Stat();
		this.pracovnici = new Pracovnik[pocetPracovnikov];
		for (int i = 0; i < pocetPracovnikov; i++) {
			pracovnici[i] = new Pracovnik(i, mySim());
		}

	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerObsluhy(Id.managerObsluhy, mySim(), this);
		new ProcesObsluhy(Id.procesObsluhy, mySim(), this);
		addOwnMessage(Mc.obsluzZak);
		addOwnMessage(Mc.obsluhaHotova);
		addOwnMessage(Mc.pocetPracovnikovVolnych);
	}
	//meta! tag="end"


	public Pracovnik[] getPracovnici() {
		return pracovnici;
	}

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

	public Pracovnik dajVolnehoPracovnika(){
		Pracovnik volnyPracovnik = null;
		for (int i = 0; i < pocetPracovnikov; i++) {
			if (!pracovnici[i].isObsadeny()){
				volnyPracovnik = pracovnici[i];
				break;
			}
		}
		return volnyPracovnik;
	}
}