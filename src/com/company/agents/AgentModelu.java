package com.company.agents;

import OSPABA.*;
import OSPStat.Stat;
import com.company.simulation.*;
import com.company.managers.*;

//meta! id="2"
public class AgentModelu extends Agent
{
	private Stat statCasVSystemePrichZak;
	private Stat statCasVSystemeOdchZak;
	private int pocetObsluzZakPrich;
	private int pocetObsluzZakOdch;

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
		this.statCasVSystemePrichZak = new Stat();
		this.statCasVSystemeOdchZak = new Stat();
		this.pocetObsluzZakPrich = 0;
		this.pocetObsluzZakOdch = 0;
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

	public Stat getStatCasVSystemePrichZak() {
		return statCasVSystemePrichZak;
	}

	public Stat getStatCasVSystemeOdchZak() {
		return statCasVSystemeOdchZak;
	}

	public int getPocetObsluzZakPrich() {
		return pocetObsluzZakPrich;
	}

	public int getPocetObsluzZakOdch() {
		return pocetObsluzZakOdch;
	}

	public void zvysPocetObsluzZakPrich(){
		pocetObsluzZakPrich++;
	}

	public void zvysPocetObsluzZakOdch(){
		pocetObsluzZakOdch++;
	}

	public void pridajDoStatCasVSystemeZakPrich(double cas){
		if (mySim().currentTime() >= 60*60)
			statCasVSystemePrichZak.addSample(cas);
	}

	public void pridajDoStatCasVSystemeZakOdch(double cas){
		if (mySim().currentTime() >= 60*60)
			statCasVSystemeOdchZak.addSample(cas);
	}
}