package com.company.agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import OSPStat.WStat;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;

//meta! id="26"
public class AgentTerm1 extends Agent
{

	private SimQueue<Zakaznik> radZakTerm1;
	private Stat statCasVRade;

	public AgentTerm1(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
		this.radZakTerm1 = new SimQueue<>(new WStat(mySim()));
		this.statCasVRade = new Stat();
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

	public void pridajDoStatCasVRade(double cas){
		if (mySim().currentTime() >= 60*60)
			statCasVRade.addSample(cas);
	}

	public Stat getStatCasVRade() {
		return statCasVRade;
	}
}