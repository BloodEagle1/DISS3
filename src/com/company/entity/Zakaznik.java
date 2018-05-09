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
    private double vstupDoRaduTerm1;
    private double vstupDoRaduTerm2;
    private double vstupDoRaduPozicovna;

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

    public double getVstupDoRaduTerm1() {
        return vstupDoRaduTerm1;
    }

    public void setVstupDoRaduTerm1(double vstupDoRaduTerm1) {
        this.vstupDoRaduTerm1 = vstupDoRaduTerm1;
    }

    public double getVstupDoRaduTerm2() {
        return vstupDoRaduTerm2;
    }

    public void setVstupDoRaduTerm2(double vstupDoRaduTerm2) {
        this.vstupDoRaduTerm2 = vstupDoRaduTerm2;
    }

    public double getVstupDoRaduPozicovna() {
        return vstupDoRaduPozicovna;
    }

    public void setVstupDoRaduPozicovna(double vstupDoRaduPozicovna) {
        this.vstupDoRaduPozicovna = vstupDoRaduPozicovna;
    }
}
