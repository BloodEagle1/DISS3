package com.company.continualAssistants;

import OSPABA.*;
import com.company.simulation.*;
import com.company.agents.*;

//meta! id="41"
public class PlanovacPrichZakPozicovna extends Scheduler {
    private double[] vstupy = {12.0, 9.0, 18.0, 28.0, 23.0, 21.0, 16.0, 11.0, 17.0, 22.0, 36.0, 24.0, 32.0, 16.0, 13.0, 13.0, 5.0, 4.0};

    public PlanovacPrichZakPozicovna(int id, Simulation mySim, CommonAgent myAgent) {
        super(id, mySim, myAgent);
    }

    @Override
    public void prepareReplication() {
        super.prepareReplication();
        // Setup component for the next replication
    }

	//meta! sender="AgentOkolia", id="42", type="Start"
	public void processStart(MessageForm message) {
    }

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message) {
        switch (message.code()) {
        }
    }

	//meta! sender="AgentOkolia", id="83", type="Notice"
	public void processNovyZakaznik(MessageForm message) {
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

		case Mc.novyZakaznik:
			processNovyZakaznik(message);
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