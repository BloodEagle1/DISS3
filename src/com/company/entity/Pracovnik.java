package com.company.entity;

import OSPABA.Entity;
import OSPABA.Simulation;

public class Pracovnik extends Entity {

	private boolean obsadeny;

	public Pracovnik(int id, Simulation mySim) {
		super(id, mySim);
	}

	public boolean isObsadeny() {
		return obsadeny;
	}

	public void setObsadeny(boolean obsadeny) {
		this.obsadeny = obsadeny;
	}
}
