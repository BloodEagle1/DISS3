package com.company.agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;

//meta! id="26"
public class AgentTerm1 extends Agent
{

	private SimQueue<Zakaznik> radZakTerm1;

	public AgentTerm1(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		this.radZakTerm1 = new SimQueue<>();
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
		new ManagerTerm1(Id.managerTerm1, mySim(), this);
		new ProcesNastupuTerm1(Id.procesNastupuTerm1, mySim(), this);
		addOwnMessage(Mc.nastupTerm1);
		addOwnMessage(Mc.nastupTerm1Hotovy);
		addOwnMessage(Mc.prichZak);
	}
	//meta! tag="end"


	public SimQueue<Zakaznik> getRadZakTerm1() {
		return radZakTerm1;
	}
}