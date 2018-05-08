package com.company.managers;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;

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
		myAgent().getRadZakPozicovna().enqueue(((MyMessage)message).getZakaznik());
		message.setCode(Mc.pocetPracovnikovVolnych);
		message.setAddressee(Id.agentObsluhy);
		request(message.createCopy());
	}

	//meta! sender="AgentObsluhy", id="24", type="Response"
	public void processObsluzZak(MessageForm message)
	{
		if (!((MyMessage)message).getZakaznik().isPrichadzajuci()){
			myAgent().getRadZakNaOdvoz().enqueue(((MyMessage)message).getZakaznik());
		} else {
			message.setCode(Mc.odchodZakaznika);
			message.setAddressee(Id.agentPohybu);
			notice(message.createCopy());
		}
		message.setCode(Mc.pocetPracovnikovVolnych);
		message.setAddressee(Id.agentObsluhy);
		request(message);
	}

	//meta! sender="AgentPohybu", id="35", type="Request"
	public void processNastupPozicovna(MessageForm message)
	{
		message.setCode(Mc.start);
		message.setAddressee(Id.procesNastupuPozicovna);
		startContinualAssistant(message);
	}

	//meta! sender="AgentPohybu", id="34", type="Request"
	public void processVystupPozicovna(MessageForm message)
	{
		message.setCode(Mc.start);
		message.setAddressee(Id.procesVystupuPozicovna);
		startContinualAssistant(message);
	}

	//meta! sender="ProcesNastupuPozicovna", id="56", type="Finish"
	public void processFinishProcesNastupuPozicovna(MessageForm message)
	{
		if (!myAgent().getRadZakNaOdvoz().isEmpty() &&
				((MyMessage)message).getMinibus().getPocetVolnychMiest() >= myAgent().getRadZakNaOdvoz().peek().getPocetCestujucich()){
			message.setCode(Mc.start);
			message.setAddressee(Id.procesNastupuPozicovna);
			startContinualAssistant(message);
		}else {
			message.setCode(Mc.nastupPozicovna);
			response(message);
		}
	}

	//meta! sender="ProcesVystupuPozicovna", id="58", type="Finish"
	public void processFinishProcesVystupuPozicovna(MessageForm message)
	{
		message.setCode(Mc.pocetPracovnikovVolnych);
		message.setAddressee(Id.agentObsluhy);
		request(message.createCopy());

		if (!((MyMessage)message).getMinibus().getCestujuci().isEmpty()){
			message.setCode(Mc.start);
			message.setAddressee(Id.procesVystupuPozicovna);
			startContinualAssistant(message);
		}else {
			message.setCode(Mc.vystupPozicovna);
			response(message);
		}
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentObsluhy", id="102", type="Response"
	public void processPocetPracovnikovVolnych(MessageForm message)
	{
		if(!myAgent().getRadZakPozicovna().isEmpty() && ((MyMessage)message).getPocetVolnychPracovnikov() > 0 ){
			message.setCode(Mc.obsluzZak);
			message.setAddressee(Id.agentObsluhy);
			((MyMessage)message).setZakaznik(myAgent().getRadZakPozicovna().dequeue());
			request(message.createCopy());
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
		case Mc.nastupPozicovna:
			processNastupPozicovna(message);
		break;

		case Mc.obsluzZak:
			processObsluzZak(message);
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

		case Mc.pocetPracovnikovVolnych:
			processPocetPracovnikovVolnych(message);
		break;

		case Mc.vystupPozicovna:
			processVystupPozicovna(message);
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