package com.company.agents;

import OSPABA.*;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;
import com.company.instantAssistants.*;

//meta! id="1"
public class AgentOkolia extends Agent
{

	private int pocetZakaznikov;
	private int pocetZakaznikovVratenia;

	public AgentOkolia(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		this.pocetZakaznikov = 0;
		this.pocetZakaznikovVratenia = 0;
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerOkolia(Id.managerOkolia, mySim(), this);
		new PlanovacPrichZakTerm2(Id.planovacPrichZakTerm2, mySim(), this);
		new PlanovacPrichMinibusov(Id.planovacPrichMinibusov, mySim(), this);
		new PlanovacPrichZakTerm1(Id.planovacPrichZakTerm1, mySim(), this);
		new PlanovacPrichZakPozicovna(Id.planovacPrichZakPozicovna, mySim(), this);
		addOwnMessage(Mc.init);
		addOwnMessage(Mc.novyAutobus);
		addOwnMessage(Mc.novyZakaznik);
	}
	//meta! tag="end"

	public int getPocetZakaznikov() {
		return pocetZakaznikov;
	}

	public int getPocetZakaznikovVratenia() {
		return pocetZakaznikovVratenia;
	}

	public void zvysPocetZakaznikov() {
		pocetZakaznikov++;
	}

	public void zvysPocetZakaznikovVratenia() {
		pocetZakaznikovVratenia++;
	}
}