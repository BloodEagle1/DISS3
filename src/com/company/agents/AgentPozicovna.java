package com.company.agents;

import OSPABA.*;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;
import com.company.instantAssistants.*;

//meta! id="7"
public class AgentPozicovna extends Agent
{
	public AgentPozicovna(int id, Simulation mySim, Agent parent)
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
		new ManagerPozicovna(Id.managerPozicovna, mySim(), this);
		new ProcesVystupuPozicovna(Id.procesVystupuPozicovna, mySim(), this);
		new ProcesNastupuPozicovna(Id.procesNastupuPozicovna, mySim(), this);
		addOwnMessage(Mc.prichZak);
		addOwnMessage(Mc.obsluzZak);
		addOwnMessage(Mc.nastupPozicovna);
		addOwnMessage(Mc.vystupPozicovnaHotovy);
		addOwnMessage(Mc.nastupPoziconaHotovy);
		addOwnMessage(Mc.vystupPozicovna);
	}
	//meta! tag="end"
}