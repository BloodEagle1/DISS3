package com.company.entity;

import OSPABA.Entity;
import OSPABA.Simulation;
import OSPDataStruct.SimQueue;
import com.company.simulation.Config;
import com.company.simulation.MySimulation;

public class Minibus extends Entity {

    private SimQueue<Zakaznik> cestujuci;
    private final int pocetMiest;
    private String predoslaZastavka;
    private String aktualnaZastavka;
    private int pocetVolnychMiest;
    private boolean vystup;
    private double prejdeneKilometre;

    public Minibus(Simulation mySim) {
        super(mySim);
        this.cestujuci = new SimQueue<>();
        this.pocetMiest = ((MySimulation) mySim()).getPocetMiestMinibusu();
        this.pocetVolnychMiest = pocetMiest;
        this.vystup = true;
        this.predoslaZastavka = "dafa";
        this.prejdeneKilometre = 0.0;
    }

    public SimQueue<Zakaznik> getCestujuci() {
        return cestujuci;
    }

    public int getPocetMiest() {
        return pocetMiest;
    }

    public void nastupZakaznika(Zakaznik zakaznik) {
        cestujuci.enqueue(zakaznik);
        pocetVolnychMiest -= zakaznik.getPocetCestujucich();
    }

    public Zakaznik vystupZakaznika() {
        Zakaznik zakaznik = cestujuci.dequeue();
        pocetVolnychMiest += zakaznik.getPocetCestujucich();
        return zakaznik;
    }

    public String getPredoslaZastavka() {
        return predoslaZastavka;
    }

    public String getAktualnaZastavka() {
        return aktualnaZastavka;
    }

    public void setAktualnaZastavka(String aktualnaZastavka) {
        this.predoslaZastavka = this.aktualnaZastavka;
        this.aktualnaZastavka = aktualnaZastavka;
    }

    public int getPocetVolnychMiest() {
        return pocetVolnychMiest;
    }

    public boolean isVystup() {
        return vystup;
    }

    public void setVystup(boolean vystup) {
        this.vystup = vystup;
    }

    public double getPrejdeneKilometre() {
        return prejdeneKilometre;
    }

    public void zvysPrejdeneKilometre(double km){
        prejdeneKilometre +=km;
    }
}
