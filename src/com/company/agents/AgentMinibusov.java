package com.company.agents;

import OSPABA.*;
import com.company.entity.Minibus;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;

import java.util.LinkedList;

//meta! id="9"
public class AgentMinibusov extends Agent
{
	private LinkedList<Minibus> minibusy;

	public AgentMinibusov(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();

		this.minibusy = new LinkedList<>();
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


	public LinkedList<Minibus> getMinibusy() {
		return minibusy;
	}

	public void vlozMinibus(Minibus minibus){
		minibusy.add(minibus);
	}
}