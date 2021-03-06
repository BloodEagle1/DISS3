package com.company.continualAssistants;

import OSPABA.*;
import com.company.entity.Minibus;
import com.company.simulation.*;
import com.company.agents.*;

//meta! id="43"
public class PlanovacPrichMinibusov extends Scheduler
{
	public PlanovacPrichMinibusov(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentOkolia", id="44", type="Start"
	public void processStart(MessageForm message)
	{
		myAgent().zvysVypusteneMinibusy();
		if (myAgent().getVypusteneMinibusy() <= ((MySimulation) mySim()).getPocetMinibusov()) {
			message.setCode(Mc.novyAutobus);
			hold((15*60)*myAgent().getVypusteneMinibusy(), message);
		}
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentOkolia", id="84", type="Notice"
	public void processNovyAutobus(MessageForm message)
	{
		Minibus minibus = new Minibus(mySim());
		if (myAgent().getVypusteneMinibusy() < ((MySimulation) mySim()).getPocetMinibusov()){
			myAgent().zvysVypusteneMinibusy();
			MyMessage msg = new MyMessage((MyMessage) message);
			hold((15*60)*myAgent().getVypusteneMinibusy(), msg);
		}

		((MyMessage)message).setMinibus(minibus);
		assistantFinished(message);
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.novyAutobus:
			processNovyAutobus(message);
		break;

		case Mc.start:
			processStart(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentOkolia myAgent()
	{
		return (AgentOkolia)super.myAgent();
	}

}