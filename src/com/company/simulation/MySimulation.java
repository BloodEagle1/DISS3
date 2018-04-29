package com.company.simulation;

import OSPABA.*;
import com.company.agents.*;

public class MySimulation extends Simulation
{
	public MySimulation()
	{
		init();
	}

	@Override
	public void prepareSimulation()
	{
		super.prepareSimulation();
		// Create global statistcis
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
}