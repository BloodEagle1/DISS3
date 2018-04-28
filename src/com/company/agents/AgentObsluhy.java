package com.company.agents;

import OSPABA.*;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;
import com.company.instantAssistants.*;

//meta! id="8"
public class AgentObsluhy extends Agent
{
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
}