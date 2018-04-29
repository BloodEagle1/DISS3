package com.company.agents;

import OSPABA.*;
import com.company.entity.Minibus;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;

//meta! id="9"
public class AgentMinibusov extends Agent
{
	private Minibus[] minibusy;
	private int vypusteneMinibusy;

	public AgentMinibusov(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		this.minibusy = new Minibus[Config.pocetMinibusov];
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
		new ManagerMinibusov(Id.managerMinibusov, mySim(), this);
		new ProcesPresunu(Id.procesPresunu, mySim(), this);
		addOwnMessage(Mc.presunHotovy);
		addOwnMessage(Mc.presunMinibusu);
		addOwnMessage(Mc.prichMinibusu);
	}
	//meta! tag="end"


	public Minibus[] getMinibusy() {
		return minibusy;
	}

	public int getVypusteneMinibusy() {
		return vypusteneMinibusy;
	}

	public void zvysVypusteneMinibusy(){
		vypusteneMinibusy++;
	}
}