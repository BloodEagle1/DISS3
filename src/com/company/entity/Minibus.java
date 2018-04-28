package com.company.entity;

import OSPABA.Entity;
import OSPABA.Simulation;
import OSPDataStruct.SimQueue;
import com.company.simulation.Config;

public class Minibus extends Entity {

    private SimQueue<Zakaznik> cestujuci;
    private int pocetMiest = Config.pocetMiestMinibusu;
    private String predoslaZastavka;
    private String aktualnaZastavka;

    public Minibus(Simulation mySim) {
        super(mySim);
    }

    public SimQueue<Zakaznik> getCestujuci() {
        return cestujuci;
    }

    public int getPocetMiest() {
        return pocetMiest;
    }

    public void pridajZakaznika(Zakaznik zakaznik) {
        cestujuci.enqueue(zakaznik);
    }

    public Zakaznik vyberZakaznikaZRadu() {
        return cestujuci.dequeue();
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
}
