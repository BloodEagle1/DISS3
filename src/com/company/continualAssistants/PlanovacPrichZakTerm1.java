package com.company.continualAssistants;

import OSPABA.*;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.agents.*;

//meta! id="37"
public class PlanovacPrichZakTerm1 extends Scheduler {

	private double[] vstupy = {4.0,8.0,12.0,15.0,18.0,14.0,13.0,10.0,4.0,6.0,10.0,14.0,16.0,15.0,7.0,3.0,4.0,2.0};

    public PlanovacPrichZakTerm1(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

	//meta! sender="AgentOkolia", id="38", type="Start"
	public void processStart(MessageForm message) {
    }

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message) {
        switch (message.code()) {
        }
    }

	//meta! sender="AgentOkolia", id="80", type="Notice"
	public void processNovyZakaznik(MessageForm message)
	{
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.novyZakaznik:
			processNovyZakaznik(message);
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
    public AgentOkolia myAgent() {
        return (AgentOkolia) super.myAgent();
    }

}