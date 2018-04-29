package com.company.entity;

import OSPABA.Entity;
import OSPABA.Simulation;
import OSPRNG.DeterministicRNG;
import OSPRNG.EmpiricPair;
import OSPRNG.EmpiricRNG;
import com.company.simulation.MySimulation;

public class Zakaznik extends Entity {

    private static EmpiricRNG empiric = new EmpiricRNG(
            new EmpiricPair(new DeterministicRNG(0.0), 0.6),
            new EmpiricPair(new DeterministicRNG(1.0), 0.20),
            new EmpiricPair(new DeterministicRNG(2.0), 0.15),
            new EmpiricPair(new DeterministicRNG(3.0), 0.5)
    );
    private final double pocetCestujucich;
    private final double vstupDoSystemu;
    private boolean prichadzajuci;

    public Zakaznik(Simulation mySim) {
        super(mySim);
        this.pocetCestujucich = empiric.sample().intValue() + 1;
        this.vstupDoSystemu = mySim.currentTime();
        this.prichadzajuci = true;
    }

    public double getPocetCestujucich() {
        return pocetCestujucich;
    }

    public double getVstupDoSystemu() {
        return vstupDoSystemu;
    }

    private MySimulation mySimulation() {
        return (MySimulation) mySim();
    }

    public boolean isPrichadzajuci() {
        return prichadzajuci;
    }

    public void setPrichadzajuci(boolean prichadzajuci) {
        this.prichadzajuci = prichadzajuci;
    }
}
