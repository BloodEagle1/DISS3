package com.company.entity;

import OSPABA.Entity;
import OSPABA.Simulation;
import OSPRNG.DeterministicRNG;
import OSPRNG.EmpiricPair;
import OSPRNG.EmpiricRNG;
import OSPRNG.UniformDiscreteRNG;
import com.company.simulation.MySimulation;

public class Zakaznik extends Entity {

    private static EmpiricRNG empiric = new EmpiricRNG(
            new EmpiricPair(new DeterministicRNG(0.0), 0.6),
            new EmpiricPair(new DeterministicRNG(1.0), 0.20),
            new EmpiricPair(new DeterministicRNG(2.0), 0.15),
            new EmpiricPair(new DeterministicRNG(3.0), 0.5)
    );
    private int pocetSpolucestujucich;
    private double vstupDoSystemu;

    public Zakaznik(Simulation mySim) {
        super(mySim);
        this.pocetSpolucestujucich = (int) empiric.sample();
        this.vstupDoSystemu = mySim.currentTime();
    }

    public int getPocetSpolucestujucich() {
        return pocetSpolucestujucich;
    }

    public void setPocetSpolucestujucich(int pocetSpolucestujucich) {
        this.pocetSpolucestujucich = pocetSpolucestujucich;
    }

    public double getVstupDoSystemu() {
        return vstupDoSystemu;
    }

    private MySimulation mySimulation() {
        return (MySimulation) mySim();
    }
}
