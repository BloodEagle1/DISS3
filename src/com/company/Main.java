package com.company;

import com.company.simulation.Config;
import com.company.simulation.MySimulation;

public class Main {

    public static void main(String[] args) {
        MySimulation sim = new MySimulation();

        sim.onSimulationWillStart(s ->{
            System.out.println("Simulating...");
        });

        sim.simulate(1, 4.5*60*10);
    }
}
