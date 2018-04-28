package com.company.managers;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;
import com.company.continualAssistants.*;
import com.company.instantAssistants.*;

//meta! id="7"
public class ManagerPozicovna extends Manager
{
	public ManagerPozicovna(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentPohybu", id="22", type="Notice"
	public void processPrichZak(MessageForm message)
	{
	}

	//meta! sender="AgentObsluhy", id="24", type="Response"
	public void processObsluzZak(MessageForm message)
	{
	}

	//meta! sender="AgentPohybu", id="35", type="Request"
	public void processNastupPozicovna(MessageForm message)
	{
		((MyMessage) message).getMinibus().setAktualnaZastavka("Pozicovna");
	}

	//meta! sender="AgentPohybu", id="34", type="Request"
	public void processVystupPozicovna(MessageForm message)
	{
	}

	//meta! sender="ProcesNastupuPozicovna", id="56", type="Finish"
	public void processFinishProcesNastupuPozicovna(MessageForm message)
	{
	}

	//meta! sender="ProcesVystupuPozicovna", id="58", type="Finish"
	public void processFinishProcesVystupuPozicovna(MessageForm message)
	{
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.obsluzZak:
			processObsluzZak(message);
		break;

		case Mc.vystupPozicovna:
			processVystupPozicovna(message);
		break;

		case Mc.nastupPozicovna:
			processNastupPozicovna(message);
		break;

		case Mc.finish:
			switch (message.sender().id())
			{
			case Id.procesNastupuPozicovna:
				processFinishProcesNastupuPozicovna(message);
			break;

			case Id.procesVystupuPozicovna:
				processFinishProcesVystupuPozicovna(message);
			break;
			}
		break;

		case Mc.prichZak:
			processPrichZak(message);
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