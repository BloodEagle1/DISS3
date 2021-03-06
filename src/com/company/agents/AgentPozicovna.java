package com.company.agents;

import OSPABA.*;
import OSPDataStruct.SimQueue;
import OSPStat.Stat;
import OSPStat.WStat;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.managers.*;
import com.company.continualAssistants.*;

//meta! id="7"
public class AgentPozicovna extends Agent
{

	private SimQueue<Zakaznik> radZakPozicovna;
	private SimQueue<Zakaznik> radZakNaOdvoz;
	private Stat statCasVRade;

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
		this.radZakPozicovna = new SimQueue<>(new WStat(mySim()));
		this.radZakNaOdvoz = new SimQueue<>(new WStat(mySim()));
		this.statCasVRade = new Stat();
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
		addOwnMessage(Mc.pocetPracovnikovVolnych);
		addOwnMessage(Mc.vystupPozicovna);
	}
	//meta! tag="end"


	public SimQueue<Zakaznik> getRadZakPozicovna() {
		return radZakPozicovna;
	}

	public SimQueue<Zakaznik> getRadZakNaOdvoz() {
		return radZakNaOdvoz;
	}

	public void pridajDoStatCasVRade(double cas){
		if (mySim().currentTime() >= 60*60)
			statCasVRade.addSample(cas);
	}

	public Stat getStatCasVRade() {
		return statCasVRade;
	}
}