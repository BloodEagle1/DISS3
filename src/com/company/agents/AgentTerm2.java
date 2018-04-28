package com.company.agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;
import com.company.instantAssistants.*;

//meta! id="27"
public class AgentTerm2 extends Agent
{

	private SimQueue<Zakaznik> radZakTerm2;

	public AgentTerm2(int id, Simulation mySim, Agent parent)
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
		new ManagerTerm2(Id.managerTerm2, mySim(), this);
		new ProcesNastupuTerm2(Id.procesNastupuTerm2, mySim(), this);
		addOwnMessage(Mc.nastupTerm2);
		addOwnMessage(Mc.prichZak);
		addOwnMessage(Mc.nastupTerm2Hotovy);
	}
	//meta! tag="end"


	public SimQueue<Zakaznik> getRadZakTerm2() {
		return radZakTerm2;
	}
}