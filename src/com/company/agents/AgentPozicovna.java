package com.company.agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;

//meta! id="7"
public class AgentPozicovna extends Agent
{

	private SimQueue<Zakaznik> radZakPozicovna;

	public AgentPozicovna(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
		this.radZakPozicovna = new SimQueue<>();
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


	public SimQueue<Zakaznik> getRadZakPozicovna() {
		return radZakPozicovna;
	}
}