package com.company.entity;

import OSPABA.Entity;
import OSPABA.Simulation;
import OSPDataStruct.SimQueue;
import com.company.simulation.MySimulation;

public class Minibus extends Entity {

    private SimQueue<Zakaznik> cestujuci;
    private final int pocetMiest;
    private String predoslaZastavka;
    private String cielovaZastavka;
    private int pocetVolnychMiest;
    private boolean vystup;
    private double prejdeneKilometre;
    private double casZacPresunu;
    private double casKedyMaSkoncitPresun;
    private boolean presuvaSa;

    public Minibus(Simulation mySim) {
        super(mySim);
        this.cestujuci = new SimQueue<>();
        this.pocetMiest = ((MySimulation) mySim()).getPocetMiestMinibusu();
        this.pocetVolnychMiest = pocetMiest;
        this.vystup = true;
        this.predoslaZastavka = "dafa";
        this.prejdeneKilometre = 0.0;
        this.presuvaSa = false;
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

    public String getCielovaZastavka() {
        return cielovaZastavka;
    }

    public void setCielovaZastavka(String cielovaZastavka) {
        this.predoslaZastavka = this.cielovaZastavka;
        this.cielovaZastavka = cielovaZastavka;
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

    public double getCasZacPresunu() {
        return casZacPresunu;
    }

    public void setCasZacPresunu(double casZacPresunu) {
        this.casZacPresunu = casZacPresunu;
    }

    public double getCasKedyMaSkoncitPresun() {
        return casKedyMaSkoncitPresun;
    }

    public void setCasKedyMaSkoncitPresun(double casKedyMaSkoncitPresun) {
        this.casKedyMaSkoncitPresun = casKedyMaSkoncitPresun;
    }

    public boolean isPresuvaSa() {
        return presuvaSa;
    }

    public void setPresuvaSa(boolean presuvaSa) {
        this.presuvaSa = presuvaSa;
    }
}


