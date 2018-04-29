package com.company.continualAssistants;

import OSPABA.*;
import OSPRNG.TriangularRNG;
import com.company.entity.Zakaznik;
import com.company.simulation.*;
import com.company.agents.*;
import OSPABA.Process;

import java.util.Random;

//meta! id="60"
public class ProcesObsluhy extends Process
{
	//in_prvapolka TRIA(1.6, 2.02, 3)
	//in_druhapolka TRIA(3.1, 4.58, 5.1)
	//out_prva TRIA(0.999, 1.25, 2.1)
	//out_druha TRIA(2.9, 4.3, 4.8)
	private static Random pravdepodobnostIn = new Random();
	private static Random pravdepodobnostOut = new Random();
	private static TriangularRNG triangularRNGInPrvaPolka = new TriangularRNG(1.6,2.02,3.0);
	private static TriangularRNG triangularRNGInDruhaPolka = new TriangularRNG(3.1,4.58,5.1);
	private static TriangularRNG triangularRNGOutPrvaPolka = new TriangularRNG(0.999,1.25,2.1);
	private static TriangularRNG triangularRNGOutDruhaPolka = new TriangularRNG(2.9,4.3,4.8);

 	public ProcesObsluhy(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! sender="AgentObsluhy", id="61", type="Start"
	public void processStart(MessageForm message)
	{
		message.setCode(Mc.obsluhaHotova);
		hold(dajCasHoldu(((MyMessage)message).getZakaznik()),message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! sender="AgentObsluhy", id="95", type="Notice"
	public void processObsluhaHotova(MessageForm message)
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

		case Mc.obsluhaHotova:
			processObsluhaHotova(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentObsluhy myAgent()
	{
		return (AgentObsluhy)super.myAgent();
	}

	private double dajCasHoldu(Zakaznik zakaznik){
 		double pravdepodobnost;
 		if (zakaznik.isPrichadzajuci()){
			pravdepodobnost = pravdepodobnostIn.nextDouble();
 			if ( pravdepodobnost <= (15/36)){
 				return triangularRNGInPrvaPolka.sample();
			}else {
				return triangularRNGInDruhaPolka.sample();
			}
		}else {
 			pravdepodobnost = pravdepodobnostOut.nextDouble();
			if ( pravdepodobnost <= (12/32)){
				return triangularRNGOutPrvaPolka.sample();
			}else {
				return triangularRNGOutDruhaPolka.sample();
			}
		}
	}

}