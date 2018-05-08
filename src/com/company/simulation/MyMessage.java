package com.company.simulation;

import OSPABA.*;
import com.company.entity.Minibus;
import com.company.entity.Zakaznik;

public class MyMessage extends MessageForm
{
	private Zakaznik zakaznik;
	private Minibus minibus;
	private int pocetVolnychPracovnikov;

	public MyMessage(Simulation sim)
	{
		super(sim);
	}

	public MyMessage(MyMessage original)
	{
		super(original);
		// copy() is called in superclass
		zakaznik = original.zakaznik;
		minibus = original.minibus;
	}

	@Override
	public MessageForm createCopy()
	{
		return new MyMessage(this);
	}

	@Override
	protected void copy(MessageForm message)
	{
		super.copy(message);
		MyMessage original = (MyMessage)message;
		// Copy attributes
	}

	public void setZakaznik(Zakaznik zakaznik) {
		this.zakaznik = zakaznik;
	}

	public void setMinibus(Minibus minibus) {
		this.minibus = minibus;
	}

	public Zakaznik getZakaznik() {
		return zakaznik;
	}

	public Minibus getMinibus() {
		return minibus;
	}

	public int getPocetVolnychPracovnikov() {
		return pocetVolnychPracovnikov;
	}

	public void setPocetVolnychPracovnikov(int pocetVolnychPracovnikov) {
		this.pocetVolnychPracovnikov = pocetVolnychPracovnikov;
	}
}