package com.company.entity;

import OSPABA.Entity;
import OSPABA.Simulation;
import OSPDataStruct.SimQueue;
import com.company.simulation.Config;

public class Minibus extends Entity {

    private SimQueue<Zakaznik> cestujuci;
    private static final int pocetMiest = Config.pocetMiestMinibusu;
    private String predoslaZastavka;
    private String aktualnaZastavka;
    private int pocetVolnychMiest;

    public Minibus(Simulation mySim) {
        super(mySim);
        this.cestujuci = new SimQueue<>();
        this.pocetVolnychMiest = pocetMiest;
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
}
