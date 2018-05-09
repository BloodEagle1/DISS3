package com.company.simulation;

import OSPABA.*;
import OSPStat.Stat;
import OSPStat.WStat;
import com.company.agents.*;

public class MySimulation extends Simulation
{
	private int pocetMiestMinibusu;
	private int pocetPracovnikov;
	private int pocetMinibusov;

	private Stat velkostRaduStatTerm1;
	private Stat velkostRaduStatTerm2;
	private Stat velkostRaduStatPozicovna;
	private Stat casVSystemePrichZak;
	private Stat casVSystemeOdchZak;
	private Stat casVRadeTerm1;
	private Stat casVRadeTerm2;
	private Stat casVRadePozicovna;
	private Stat obsadenostPracovnikov;

	public MySimulation()
	{
		init();
	}

	@Override
	public void prepareSimulation()
	{
		super.prepareSimulation();
		// Create global statistcis
		velkostRaduStatTerm1 = new Stat();
		velkostRaduStatTerm2 = new Stat();
		velkostRaduStatPozicovna = new Stat();
		casVSystemePrichZak = new Stat();
		casVSystemeOdchZak = new Stat();
		casVRadeTerm1 = new Stat();
		casVRadeTerm2 = new Stat();
		casVRadePozicovna = new Stat();
		obsadenostPracovnikov = new Stat();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Reset entities, queues, local statistics, etc...
		agentModelu().spustiSimulaciu();
	}

	@Override
	public void replicationFinished()
	{
		// Collect local statistics into global, update UI, etc...
		super.replicationFinished();
		WStat radStat = agentTerm1().getRadZakTerm1().lengthStatistic();
		velkostRaduStatTerm1.addSample(radStat.mean());
		WStat radStat1 = agentTerm2().getRadZakTerm2().lengthStatistic();
		velkostRaduStatTerm2.addSample(radStat1.mean());
		WStat radStat2 = agentPozicovna().getRadZakPozicovna().lengthStatistic();
		velkostRaduStatPozicovna.addSample(radStat2.mean());
		casVSystemePrichZak.addSample(agentModelu().getStatCasVSystemePrichZak().mean());
		casVSystemeOdchZak.addSample(agentModelu().getStatCasVSystemeOdchZak().mean());
		casVRadeTerm1.addSample(agentTerm1().getStatCasVRade().mean());
		casVRadeTerm2.addSample(agentTerm2().getStatCasVRade().mean());
		casVRadePozicovna.addSample(agentPozicovna().getStatCasVRade().mean());
		obsadenostPracovnikov.addSample(agentObsluhy().getVytazeniePracovnikov().mean());
	}

	@Override
	public void simulationFinished()
	{
		// Dysplay simulation results
		super.simulationFinished();
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		setAgentModelu(new AgentModelu(Id.agentModelu, this, null));
		setAgentPohybu(new AgentPohybu(Id.agentPohybu, this, agentModelu()));
		setAgentPozicovna(new AgentPozicovna(Id.agentPozicovna, this, agentPohybu()));
		setAgentObsluhy(new AgentObsluhy(Id.agentObsluhy, this, agentPozicovna()));
		setAgentMinibusov(new AgentMinibusov(Id.agentMinibusov, this, agentPohybu()));
		setAgentTerm1(new AgentTerm1(Id.agentTerm1, this, agentPohybu()));
		setAgentTerm2(new AgentTerm2(Id.agentTerm2, this, agentPohybu()));
		setAgentTerm3(new AgentTerm3(Id.agentTerm3, this, agentPohybu()));
		setAgentOkolia(new AgentOkolia(Id.agentOkolia, this, agentModelu()));
	}

	private AgentModelu _agentModelu;

public AgentModelu agentModelu()
	{ return _agentModelu; }

	public void setAgentModelu(AgentModelu agentModelu)
	{_agentModelu = agentModelu; }

	private AgentPohybu _agentPohybu;

public AgentPohybu agentPohybu()
	{ return _agentPohybu; }

	public void setAgentPohybu(AgentPohybu agentPohybu)
	{_agentPohybu = agentPohybu; }

	private AgentPozicovna _agentPozicovna;

public AgentPozicovna agentPozicovna()
	{ return _agentPozicovna; }

	public void setAgentPozicovna(AgentPozicovna agentPozicovna)
	{_agentPozicovna = agentPozicovna; }

	private AgentObsluhy _agentObsluhy;

public AgentObsluhy agentObsluhy()
	{ return _agentObsluhy; }

	public void setAgentObsluhy(AgentObsluhy agentObsluhy)
	{_agentObsluhy = agentObsluhy; }

	private AgentMinibusov _agentMinibusov;

public AgentMinibusov agentMinibusov()
	{ return _agentMinibusov; }

	public void setAgentMinibusov(AgentMinibusov agentMinibusov)
	{_agentMinibusov = agentMinibusov; }

	private AgentTerm1 _agentTerm1;

public AgentTerm1 agentTerm1()
	{ return _agentTerm1; }

	public void setAgentTerm1(AgentTerm1 agentTerm1)
	{_agentTerm1 = agentTerm1; }

	private AgentTerm2 _agentTerm2;

public AgentTerm2 agentTerm2()
	{ return _agentTerm2; }

	public void setAgentTerm2(AgentTerm2 agentTerm2)
	{_agentTerm2 = agentTerm2; }

	private AgentTerm3 _agentTerm3;

public AgentTerm3 agentTerm3()
	{ return _agentTerm3; }

	public void setAgentTerm3(AgentTerm3 agentTerm3)
	{_agentTerm3 = agentTerm3; }

	private AgentOkolia _agentOkolia;

public AgentOkolia agentOkolia()
	{ return _agentOkolia; }

	public void setAgentOkolia(AgentOkolia agentOkolia)
	{_agentOkolia = agentOkolia; }
	//meta! tag="end"

	public int getPocetMiestMinibusu() {
		return pocetMiestMinibusu;
	}

	public int getPocetPracovnikov() {
		return pocetPracovnikov;
	}

	public int getPocetMinibusov() {
		return pocetMinibusov;
	}

	public void setPocetMiestMinibusu(int pocetMiestMinibusu) {
		this.pocetMiestMinibusu = pocetMiestMinibusu;
	}

	public void setPocetPracovnikov(int pocetPracovnikov) {
		this.pocetPracovnikov = pocetPracovnikov;
	}

	public void setPocetMinibusov(int pocetMinibusov) {
		this.pocetMinibusov = pocetMinibusov;
	}

	public Stat getVelkostRaduStatTerm1() {
		return velkostRaduStatTerm1;
	}

	public Stat getVelkostRaduStatTerm2() {
		return velkostRaduStatTerm2;
	}

	public Stat getVelkostRaduStatPozicovna() {
		return velkostRaduStatPozicovna;
	}

	public Stat getCasVSystemePrichZak() {
		return casVSystemePrichZak;
	}

	public Stat getCasVSystemeOdchZak() {
		return casVSystemeOdchZak;
	}

	public Stat getCasVRadeTerm1() {
		return casVRadeTerm1;
	}

	public Stat getCasVRadeTerm2() {
		return casVRadeTerm2;
	}

	public Stat getCasVRadePozicovna() {
		return casVRadePozicovna;
	}

	public Stat getObsadenostPracovnikov() {
		return obsadenostPracovnikov;
	}
}