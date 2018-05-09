package com.company.continualAssistants;

import OSPABA.*;
import OSPRNG.UniformContinuousRNG;
import com.company.entity.Minibus;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.agents.*;
import OSPABA.Process;

//meta! id="57"
public class ProcesVystupuPozicovna extends Process
{

	private static UniformContinuousRNG genVystupu = new UniformContinuousRNG(2.0,10.0);

	public ProcesVystupuPozicovna(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentPozicovna", id="58", type="Start"
	public void processStart(MessageForm message)
	{
		double casVystupu = 0.0;
		Minibus minibus = ((MyMessage)message).getMinibus();

		if (!minibus.getCestujuci().isEmpty()){
			Zakaznik zakaznik = minibus.vystupZakaznika();
			for (int i = 0; i < (zakaznik.getPocetCestujucich()); i++) {
				casVystupu += genVystupu.sample();
			}
			myAgent().getRadZakPozicovna().enqueue(zakaznik);
			((MyMessage) message).setZakaznik(zakaznik);

		}
		message.setCode(Mc.vystupPozicovnaHotovy);
		hold(casVystupu, message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentPozicovna", id="93", type="Notice"
	public void processVystupPozicovnaHotovy(MessageForm message)
	{
		assistantFinished(message);
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.start:
			processStart(message);
		break;

		case Mc.vystupPozicovnaHotovy:
			processVystupPozicovnaHotovy(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentPozicovna myAgent()
	{
		return (AgentPozicovna)super.myAgent();
	}

}