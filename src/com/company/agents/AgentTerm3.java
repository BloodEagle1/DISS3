package com.company.agents;

import OSPABA.*;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;
import com.company.instantAssistants.*;

//meta! id="30"
public class AgentTerm3 extends Agent
{
	public AgentTerm3(int id, Simulation mySim, Agent parent)
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
		new ManagerTerm3(Id.managerTerm3, mySim(), this);
		new ProcesVystupuTerm3(Id.procesVystupuTerm3, mySim(), this);
		addOwnMessage(Mc.vystupTerm3);
		addOwnMessage(Mc.vystupTerm3Hotovy);
	}
	//meta! tag="end"
}