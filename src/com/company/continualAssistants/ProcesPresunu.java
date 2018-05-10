package com.company.continualAssistants;

import OSPABA.*;
import com.company.entity.Minibus;
import com.company.simulation.*;
import com.company.agents.*;
import OSPABA.Process;

//meta! id="63"
public class ProcesPresunu extends Process
{
	public ProcesPresunu(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentMinibusov", id="64", type="Start"
	public void processStart(MessageForm message)
	{
		Minibus minibus = ((MyMessage)message).getMinibus();
		switch (minibus.getAktualnaZastavka()) {
			case "Terminal 1":
				message.setCode(Mc.presunHotovy);
				hold(dajCasHoldu(0.5), message);
				minibus.zvysPrejdeneKilometre(0.5);
				minibus.setAktualnaZastavka("Terminal 2");
				break;
			case "Terminal 2":
				message.setCode(Mc.presunHotovy);
				hold(dajCasHoldu(3.4), message);
				minibus.zvysPrejdeneKilometre(3.4);
				minibus.setAktualnaZastavka("Pozicovna");
				break;
			case "Terminal 3":
				message.setCode(Mc.presunHotovy);
				hold(dajCasHoldu(0.9),message);
				minibus.zvysPrejdeneKilometre(0.9);
				minibus.setAktualnaZastavka("Terminal 1");
				break;
			case "Pozicovna":
				if (minibus.isVystup()) {
					message.setCode(Mc.presunHotovy);
					hold(dajCasHoldu(0.0),message);
					minibus.setVystup(false);
				}else {
					if (!minibus.getCestujuci().isEmpty()){
						message.setCode(Mc.presunHotovy);
						hold(dajCasHoldu(2.9), message);
						minibus.zvysPrejdeneKilometre(2.9);
						minibus.setVystup(true);
						minibus.setAktualnaZastavka("Terminal 3");
					}else{
						message.setCode(Mc.presunHotovy);
						hold(dajCasHoldu(2.5), message);
						minibus.zvysPrejdeneKilometre(2.5);
						minibus.setVystup(true);
						minibus.setAktualnaZastavka("Terminal 1");
					}

				}
				break;
		}
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentMinibusov", id="98", type="Notice"
	public void processPresunHotovy(MessageForm message)
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

		case Mc.presunHotovy:
			processPresunHotovy(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentMinibusov myAgent()
	{
		return (AgentMinibusov)super.myAgent();
	}

	private double dajCasHoldu(double vzdialenost){
		return (vzdialenost * 3600)/35;
	}

}