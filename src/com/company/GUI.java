package com.company;

import OSPABA.ISimDelegate;
import OSPABA.SimState;
import OSPABA.Simulation;
import com.company.entity.Minibus;
import com.company.entity.Pracovnik;
import com.company.simulation.MySimulation;
import org.jfree.chart.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class GUI implements ISimDelegate {
    private JButton btnStart;
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JTextField jtfPocetMinibusov;
    private JTextField jtfPocetPracovnikov;
    private JTextField jtfPocetReplikacii;
    private JLabel jlRepStatPriemCasVSystemePrich;
    private JLabel jlRepStatPriemRadObsluhy;
    private JLabel jlRepStatPriemCasVRadeNaObsluhu;
    private JLabel jlRepStatPriemObsadenostPracovnikov;
    private JPanel jpReplikacie;
    private JPanel jpReplikacia;
    private JPanel jpZavislostMinibusov;
    private JPanel jpZavislostPracovnikov;
    private JLabel jlVelkostRaduTerminal1;
    private JLabel jlVelkostRaduTerminal2;
    private JLabel jlVelkostRaduPozicovna;
    private JLabel jlVolnyPracovnici;
    private JLabel jlPocetZakVstup;
    private JLabel jlVybavenyZakPrich;
    private JButton btnStartReplikacia;
    private JLabel jlStatPriemCasVSystemePrich;
    private JLabel jlStatPriemRadObsluha;
    private JLabel jlStatPriemCasVRadeObsluha;
    private JLabel jlStatPriemObsadenostPrac;
    private JTable jtMinibusy;
    private JLabel jlStatPriemRadTerminal1;
    private JLabel jlStatPriemRadTerminal2;
    private JLabel jlRepStatPriemRadTerminal1;
    private JLabel jlRepStatPriemRadTerminal2;
    private JSlider sliderRychlost;
    private JSlider sliderFrekvencia;
    private JLabel jlIntervalSpolahlivostiCasVSysPrich;
    private JLabel jlRepStatPriemCasVRadeTerminal1;
    private JLabel jlRepStatPriemCasVRadeTerminal2;
    private JLabel jlStatPriemCasVRadeTerminal1;
    private JLabel jlStatPriemCasVRadeTerminal2;
    private JLabel jlCas;
    private JButton btnPauza;
    private JTable jtPracovnici;
    private JLabel jlVybavenyZakOdch;
    private JLabel jlStatPriemCasVSystemeOdch;
    private JLabel jlRepStatPriemCasVSystemeOdch;
    private JLabel jlPocetReplikacii;
    private JLabel jlAktualnaReplikacia;
    private JButton pauzaButton;
	private JComboBox comboTypAuta;
    private JLabel jlIntervalSpolahlivostiCasVSysOdch;
    private JButton stopButton;
    private JButton stopButton1;
    private JLabel jlIntervalSpolahlivostiObsPrac;
    private JLabel jlIntervalSpolahlivostiRadTerm1;
    private JLabel jlIntervalSpolahlivostiRadTerm2;
    private JLabel jlIntervalSpolahlivostiRadPozic;
    private JLabel jlIntervalSpolahlivostiCasVRadTerm1;
    private JLabel jlIntervalSpolahlivostiCasVRadTerm2;
    private JLabel jlIntervalSpolahlivostiCasVRadPozic;
    private JFreeChart lineChart;
    private XYSeries series;
    private JFreeChart lineChart2;
    private XYSeries series2;
    private double spodna1;
    private double spodna2;
    private double horna1;
    private double horna2;
    private DefaultTableModel tableModel;
    private DefaultTableModel tableModel1;
    private SimpleDateFormat df;
    private Calendar cal;
    private MySimulation pozicovna;
    private boolean zastavenie = false;

    public GUI() {
        series = new XYSeries("zavislostMinibusy");
        series2 = new XYSeries("zavislostPracovnici");
        this.spodna1 = Double.MAX_VALUE;
        this.spodna2 = Double.MAX_VALUE;
        this.horna1 = Double.MIN_VALUE;
        this.horna2 = Double.MIN_VALUE;
        String col[] = {"Minibus", "Predosla zastavka", "Cielova zastavka", "Pocet cestujucich", "Pozicica presunu", "Prejdene kilometre"};
        tableModel = new DefaultTableModel(col, 0);
        jtMinibusy.setModel(tableModel);
        String col1[] = {"Pracovnik", "Obsadenost"};
        tableModel1 = new DefaultTableModel(col1, 0);
        jtPracovnici.setModel(tableModel1);

        sliderRychlost.setValue(60);
        sliderFrekvencia.setValue(1);

        pozicovna = new MySimulation();
        pozicovna.registerDelegate(this);
        pozicovna.onReplicationWillStart(sim -> pozicovna.setSimSpeed(sliderFrekvencia.getValue(), (double)sliderRychlost.getValue()/100));

        jpZavislostMinibusov.setLayout(new java.awt.BorderLayout());
        XYSeriesCollection dataset = new XYSeriesCollection();
        lineChart = ChartFactory.createXYLineChart("zavislost minibusov", "pocet minibusov", "priemerny cas v systeme", dataset);
        dataset.addSeries(series);
        ChartPanel CP = new ChartPanel(lineChart);
        CP.addChartMouseListener(new ChartMouseListener() {

            @Override
            public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {
                if (!jtfPocetPracovnikov.getText().equals("") && !jtfPocetReplikacii.getText().equals("")) {
                    series.clear();
                    int pocetPracovnikov = Integer.parseInt(jtfPocetPracovnikov.getText());
                    int pocetMiest;

                    if (comboTypAuta.getSelectedItem().equals("typ A")){
                        pocetMiest = 12;
                    } else if (comboTypAuta.getSelectedItem().equals("typ B")){
                        pocetMiest = 18;
                    }else {
                        pocetMiest = 30;
                    }

                    for (int i = 1; i < 30; i++) {
                        pozicovna.setPocetMinibusov(i);
                        pozicovna.setPocetPracovnikov(pocetPracovnikov);
                        pozicovna.setPocetMiestMinibusu(pocetMiest);
                        pozicovna.onReplicationWillStart(sim -> pozicovna.setSimSpeed(100000, 1/100));
                        pozicovna.onSimulationDidFinish(GUI.this::refresh);
                        pozicovna.simulate(10, 5.5*60*60);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Nespravne parametre");
                }
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {

            }

        });
        jpZavislostMinibusov.add(CP, BorderLayout.CENTER);
        jpZavislostMinibusov.validate();


        jpZavislostPracovnikov.setLayout(new java.awt.BorderLayout());
        XYSeriesCollection dataset1 = new XYSeriesCollection();
        lineChart2 = ChartFactory.createXYLineChart("zavislost pracovnikov", "pocet pracovnikov", "priemerny cas v systeme", dataset1);
        dataset1.addSeries(series2);
        ChartPanel CP1 = new ChartPanel(lineChart2);
        CP1.addChartMouseListener(new ChartMouseListener() {

            @Override
            public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {
                if (!jtfPocetMinibusov.getText().equals("") && !jtfPocetReplikacii.getText().equals("")) {
                    series2.clear();
                    int pocetMinibusov = Integer.parseInt(jtfPocetMinibusov.getText());
                    int pocetMiest;

                    if (comboTypAuta.getSelectedItem().equals("typ A")){
                        pocetMiest = 12;
                    } else if (comboTypAuta.getSelectedItem().equals("typ B")){
                        pocetMiest = 18;
                    }else {
                        pocetMiest = 30;
                    }

                    for (int i = 1; i < 30; i++) {
                        pozicovna.setPocetMinibusov(pocetMinibusov);
                        pozicovna.setPocetPracovnikov(i);
                        pozicovna.setPocetMiestMinibusu(pocetMiest);
                        pozicovna.onReplicationWillStart(sim -> pozicovna.setSimSpeed(100000, 1/100));
                        pozicovna.onSimulationDidFinish(GUI.this::refresh);
                        pozicovna.simulate(10, 5.5*60*60);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Nespravne parametre");
                }
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent chartMouseEvent) {

            }
        });
        jpZavislostPracovnikov.add(CP1, BorderLayout.CENTER);
        jpZavislostPracovnikov.validate();

        btnStart.addActionListener(e -> {
            if (!jtfPocetMinibusov.getText().equals("") && !jtfPocetPracovnikov.getText().equals("") && !jtfPocetReplikacii.getText().equals("")) {
                int pocetMinibusov = Integer.parseInt(jtfPocetMinibusov.getText());
                int pocetPracovnikov = Integer.parseInt(jtfPocetPracovnikov.getText());
                int pocetReplikacii = Integer.parseInt(jtfPocetReplikacii.getText());
				int pocetMiest;

				if (comboTypAuta.getSelectedItem().equals("typ A")){
					pocetMiest = 12;
				} else if (comboTypAuta.getSelectedItem().equals("typ B")){
					pocetMiest = 18;
				}else {
					pocetMiest = 30;
				}

                jlRepStatPriemCasVRadeNaObsluhu.setText("");
                jlRepStatPriemRadObsluhy.setText("");
                jlRepStatPriemCasVSystemePrich.setText("");
                jlRepStatPriemCasVSystemeOdch.setText("");
                jlRepStatPriemObsadenostPracovnikov.setText("");
                jlRepStatPriemRadTerminal1.setText("");
                jlRepStatPriemRadTerminal2.setText("");
                jlRepStatPriemCasVRadeTerminal1.setText("");
                jlRepStatPriemCasVRadeTerminal2.setText("");
                jlAktualnaReplikacia.setText("0");
                jlPocetReplikacii.setText(pocetReplikacii+"");
                jlIntervalSpolahlivostiCasVSysPrich.setText("< , >");
                jlIntervalSpolahlivostiCasVSysOdch.setText("< , >");
                jlIntervalSpolahlivostiObsPrac.setText("< , >");
                jlIntervalSpolahlivostiRadTerm1.setText("< , >");
                jlIntervalSpolahlivostiRadTerm2.setText("< , >");
                jlIntervalSpolahlivostiRadPozic.setText("< , >");
                jlIntervalSpolahlivostiCasVRadTerm1.setText("< , >");
                jlIntervalSpolahlivostiCasVRadTerm2.setText("< , >");
                jlIntervalSpolahlivostiCasVRadPozic.setText("< , >");

//                for (int i = 2; i < 21; i++) {
//                    for (int j = 2; j < 21; j++) {
//                        for (int k = 0; k < 3; k++) {
//                            if (k == 0)
//                            {
//                                pocetMiest = 12;
//                            }else if (k == 1){
//                                pocetMiest = 18;
//                            }else {
//                                pocetMiest = 30;
//                            }
//
//                            pozicovna = new MySimulation();
//                            pozicovna.setPocetMinibusov(i);
//                            pozicovna.setPocetPracovnikov(j);
//                            pozicovna.setPocetMiestMinibusu(pocetMiest);
//                            pozicovna.setSimSpeed(0,0);
//                            pozicovna.simulate(10, 5.5*60*60);
//                        }
//                    }
//                }

                pozicovna.setPocetMinibusov(pocetMinibusov);
                pozicovna.setPocetPracovnikov(pocetPracovnikov);
                pozicovna.setPocetMiestMinibusu(pocetMiest);
                pozicovna.onReplicationDidFinish(this::refresh);
                pozicovna.simulateAsync(pocetReplikacii, 5.5*60*60);
            } else {
                JOptionPane.showMessageDialog(panel, "Nespravne parametre");
            }
        });
        btnStartReplikacia.addActionListener(e -> {
            if (!jtfPocetMinibusov.getText().equals("") && !jtfPocetPracovnikov.getText().equals("")) {
                int pocetMinibusov = Integer.parseInt(jtfPocetMinibusov.getText());
                int pocetPracovnikov = Integer.parseInt(jtfPocetPracovnikov.getText());
				int pocetMiest;

				if (comboTypAuta.getSelectedItem().equals("typ A")){
					pocetMiest = 12;
				} else if (comboTypAuta.getSelectedItem().equals("typ B")){
					pocetMiest = 18;
				}else {
					pocetMiest = 30;
				}

                jlVelkostRaduTerminal1.setText("");
                jlVelkostRaduTerminal2.setText("");
                jlVelkostRaduPozicovna.setText("");
                jlVolnyPracovnici.setText("");
                jlPocetZakVstup.setText("");
                jlVybavenyZakPrich.setText("");
                jlVybavenyZakOdch.setText("");
                jlStatPriemCasVSystemePrich.setText("");
                jlStatPriemCasVSystemeOdch.setText("");
                jlStatPriemRadTerminal1.setText("");
                jlStatPriemRadTerminal2.setText("");
                jlStatPriemRadObsluha.setText("");
                jlStatPriemCasVRadeObsluha.setText("");
                jlStatPriemObsadenostPrac.setText("");
                jlStatPriemCasVRadeTerminal1.setText("");
                jlStatPriemCasVRadeTerminal2.setText("");

                tableModel = new DefaultTableModel(col, 0);
                jtMinibusy.setModel(tableModel);

                for (int i = 0; i < pocetMinibusov; i++) {
                    Object[] data = {"Minibus" + i, "", "", "", ""};
                    tableModel.addRow(data);
                }

                tableModel1 = new DefaultTableModel(col1, 0);
                jtPracovnici.setModel(tableModel1);

                for (int i = 0; i < pocetPracovnikov; i++) {
                    Object[] data = {"Pracovnik" + i, ""};
                    tableModel1.addRow(data);
                }

                try {
                    df = new SimpleDateFormat("HH:mm:ss");
                    cal = Calendar.getInstance();
                    String myTime = "16:00:00";
                    Date d = df.parse(myTime);
                    cal.setTime(d);
                    String newTime = df.format(cal.getTime());
                    jlCas.setText(newTime);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

                pozicovna.setPocetMinibusov(pocetMinibusov);
                pozicovna.setPocetPracovnikov(pocetPracovnikov);
                pozicovna.setPocetMiestMinibusu(pocetMiest);
                pozicovna.simulateAsync(1, 5.5*60*60);
            } else {
                JOptionPane.showMessageDialog(panel, "Nespravne parametre");
            }
        });
        btnPauza.addActionListener(e -> {
            if (!zastavenie){
                zastavenie = true;
                pozicovna.pauseSimulation();
                btnPauza.setText("pokracuj");
            }else {
                zastavenie = false;
                pozicovna.resumeSimulation();
                btnPauza.setText("pauza");
            }
        });
        sliderRychlost.addChangeListener(e -> pozicovna.setSimSpeed(sliderFrekvencia.getValue(), (double)sliderRychlost.getValue() / 100));
        sliderFrekvencia.addChangeListener(e -> pozicovna.setSimSpeed(sliderFrekvencia.getValue(), (double)sliderRychlost.getValue() / 100));
        pauzaButton.addActionListener(e -> {
            if (!zastavenie){
                zastavenie = true;
                pozicovna.pauseSimulation();
                pauzaButton.setText("pokracuj");
            }else {
                zastavenie = false;
                pozicovna.resumeSimulation();
                pauzaButton.setText("pauza");
            }
        });
        stopButton.addActionListener(e -> {
            pozicovna.stopSimulation();
        });
        stopButton1.addActionListener(e -> {
            pozicovna.stopSimulation();
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1300, 700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void vypisVysledkyReplikacii(MySimulation pozicovna) {
        jlAktualnaReplikacia.setText(pozicovna.currentReplication() + 1 + "");
        jlRepStatPriemCasVSystemePrich.setText((Math.round((pozicovna.getCasVSystemePrichZak().mean()/60) * 10000d) / 10000d) + " min");
        jlRepStatPriemCasVSystemeOdch.setText(((Math.round(pozicovna.getCasVSystemeOdchZak().mean()/60) * 10000d) / 10000d) + " min");
        jlRepStatPriemRadObsluhy.setText((Math.round(pozicovna.getVelkostRaduStatPozicovna().mean() * 10000d) / 10000d) + "");
        jlRepStatPriemCasVRadeNaObsluhu.setText(((Math.round(pozicovna.getCasVRadePozicovna().mean()/60) * 10000d) / 10000d) + " min");
        jlRepStatPriemObsadenostPracovnikov.setText((Math.round(pozicovna.getObsadenostPracovnikov().mean() * 10000d) / 10000d) + "");
        jlRepStatPriemRadTerminal1.setText((Math.round(pozicovna.getVelkostRaduStatTerm1().mean() * 10000d) / 10000d) + "");
        jlRepStatPriemRadTerminal2.setText((Math.round(pozicovna.getVelkostRaduStatTerm2().mean() * 10000d) / 10000d) + "");
        if(pozicovna.currentReplication() > 1){
            jlIntervalSpolahlivostiCasVSysPrich.setText("< " + (Math.round((pozicovna.getCasVSystemePrichZak().confidenceInterval_90()[0]/60) * 10000d) / 10000d) + ","
                    + (Math.round((pozicovna.getCasVSystemePrichZak().confidenceInterval_90()[1]/60) * 10000d) / 10000d) + " >");
            jlIntervalSpolahlivostiCasVSysOdch.setText("< " + (Math.round((pozicovna.getCasVSystemeOdchZak().confidenceInterval_90()[0]/60) * 10000d) / 10000d) + ","
                    + (Math.round((pozicovna.getCasVSystemeOdchZak().confidenceInterval_90()[1]/60) * 10000d) / 10000d) + " >");
            jlIntervalSpolahlivostiObsPrac.setText("< " + (Math.round((pozicovna.getObsadenostPracovnikov().confidenceInterval_90()[0]) * 10000d) / 10000d) + ","
                    + (Math.round((pozicovna.getObsadenostPracovnikov().confidenceInterval_90()[1]) * 10000d) / 10000d) + " >");
            jlIntervalSpolahlivostiRadTerm1.setText("< " + (Math.round((pozicovna.getVelkostRaduStatTerm1().confidenceInterval_90()[0]) * 10000d) / 10000d) + ","
                    + (Math.round((pozicovna.getVelkostRaduStatTerm1().confidenceInterval_90()[1]) * 10000d) / 10000d) + " >");
            jlIntervalSpolahlivostiRadTerm2.setText("< " + (Math.round((pozicovna.getVelkostRaduStatTerm2().confidenceInterval_90()[0]) * 10000d) / 10000d) + ","
                    + (Math.round((pozicovna.getVelkostRaduStatTerm2().confidenceInterval_90()[1]) * 10000d) / 10000d) + " >");
            jlIntervalSpolahlivostiRadPozic.setText("< " + (Math.round((pozicovna.getVelkostRaduStatPozicovna().confidenceInterval_90()[0]) * 10000d) / 10000d) + ","
                    + (Math.round((pozicovna.getVelkostRaduStatPozicovna().confidenceInterval_90()[1]) * 10000d) / 10000d) + " >");
            jlIntervalSpolahlivostiCasVRadTerm1.setText("< " + (Math.round((pozicovna.getCasVRadeTerm1().confidenceInterval_90()[0]/60) * 10000d) / 10000d) + ","
                    + (Math.round((pozicovna.getCasVRadeTerm1().confidenceInterval_90()[1]/60) * 10000d) / 10000d) + " >");
            jlIntervalSpolahlivostiCasVRadTerm2.setText("< " + (Math.round((pozicovna.getCasVRadeTerm2().confidenceInterval_90()[0]/60) * 10000d) / 10000d) + ","
                    + (Math.round((pozicovna.getCasVRadeTerm2().confidenceInterval_90()[1]/60) * 10000d) / 10000d) + " >");
            jlIntervalSpolahlivostiCasVRadPozic.setText("< " + (Math.round((pozicovna.getCasVRadePozicovna().confidenceInterval_90()[0]/60) * 10000d) / 10000d) + ","
                    + (Math.round((pozicovna.getCasVRadePozicovna().confidenceInterval_90()[1]/60) * 10000d) / 10000d) + " >");
        }
        jlRepStatPriemCasVRadeTerminal1.setText((Math.round((pozicovna.getCasVRadeTerm1().mean()/60) * 10000d) / 10000d) + " min");
        jlRepStatPriemCasVRadeTerminal2.setText((Math.round((pozicovna.getCasVRadeTerm2().mean()/60) * 10000d) / 10000d) + " min");
    }

//    @Override
//    public void refresh(MySimulation sim) {
//        MySimulation pozicovna = (MySimulation) sim;
//        JPanel activePanel = (JPanel) tabbedPane1.getSelectedComponent();
//        if (activePanel == jpReplikacia) {
////            pozicovna.setRychlost((double) sliderRychlost.getValue());
////            pozicovna.setFrekvencia((double) sliderFrekvencia.getValue());
//            vypisVysledkyVCase(pozicovna);
//        } else if (activePanel == jpReplikacie) {
//            vypisVysledkyReplikacii(pozicovna);
//        } else if (activePanel == jpZavislostMinibusov) {
////            vykresliGraf(pozicovna.getMinibusy(), pozicovna.repStatCasuVSysteme());
//        } else if (activePanel == jpZavislostPracovnikov) {
////            vykresliGraf2(pozicovna.getPracovnici(), pozicovna.repStatCasuVSysteme());
//        }

//    }

    private void vypisVysledkyVCase(MySimulation pozicovna) {

        jlVelkostRaduTerminal1.setText((Math.round(pozicovna.agentTerm1().getRadZakTerm1().size() * 10000d) / 10000d) + "");
        jlVelkostRaduTerminal2.setText((Math.round(pozicovna.agentTerm2().getRadZakTerm2().size() * 10000d) / 10000d) + "");
        jlVelkostRaduPozicovna.setText((Math.round(pozicovna.agentPozicovna().getRadZakPozicovna().size() * 10000d) / 10000d) + "");
        jlVolnyPracovnici.setText(pozicovna.agentObsluhy().getPocetVolnychPracovnikov() + "");
        jlPocetZakVstup.setText(pozicovna.agentOkolia().getPocetZakaznikov() + "");
        jlVybavenyZakPrich.setText(pozicovna.agentModelu().getPocetObsluzZakPrich() + "");
        jlVybavenyZakOdch.setText(pozicovna.agentModelu().getPocetObsluzZakOdch() + "");
        jlStatPriemCasVSystemePrich.setText((Math.round((pozicovna.agentModelu().getStatCasVSystemePrichZak().mean()/60)* 10000d) / 10000d) + " min");
        jlStatPriemCasVSystemeOdch.setText((Math.round((pozicovna.agentModelu().getStatCasVSystemeOdchZak().mean()/60)* 10000d) / 10000d) + " min");
        jlStatPriemObsadenostPrac.setText((Math.round(pozicovna.agentObsluhy().getVytazeniePracovnikov().mean() * 10000d) / 10000d) + "");
        jlStatPriemRadTerminal1.setText((Math.round(pozicovna.agentTerm1().getRadZakTerm1().lengthStatistic().mean() * 10000d) / 10000d) + "");
        jlStatPriemRadTerminal2.setText((Math.round(pozicovna.agentTerm2().getRadZakTerm2().lengthStatistic().mean() * 10000d) / 10000d) + "");
        jlStatPriemRadObsluha.setText((Math.round(pozicovna.agentPozicovna().getRadZakPozicovna().lengthStatistic().mean() * 10000d) / 10000d) + "");
        jlStatPriemCasVRadeTerminal1.setText((Math.round((pozicovna.agentTerm1().getStatCasVRade().mean()/60) * 10000d) / 10000d) + " min");
        jlStatPriemCasVRadeTerminal2.setText((Math.round((pozicovna.agentTerm2().getStatCasVRade().mean()/60) * 10000d) / 10000d) + " min");
        jlStatPriemCasVRadeObsluha.setText((Math.round((pozicovna.agentPozicovna().getStatCasVRade().mean()/60) * 10000d) / 10000d) + " min");

        LinkedList<Minibus> minibusy = pozicovna.agentMinibusov().getMinibusy();

        if (minibusy != null){
            Minibus minibus;
            for (int i = 0; i < minibusy.size(); i++) {
                minibus = minibusy.get(i);
                if (minibusy.get(i) != null){
                    tableModel.setValueAt(minibus.getPredoslaZastavka(), i, 1);
                    tableModel.setValueAt(minibus.getCielovaZastavka(), i, 2);
                    tableModel.setValueAt(minibus.getCestujuci().size(), i, 3);
                    if (minibus.isPresuvaSa()){
						double percentoPozicie = ((pozicovna.currentTime() - minibus.getCasZacPresunu()) / (minibus.getCasKedyMaSkoncitPresun() - minibus.getCasZacPresunu())) * 100;
						tableModel.setValueAt((Math.round(percentoPozicie * 100d) / 100d) + " %", i, 4);
					}else {
						tableModel.setValueAt("", i, 4);
					}
					tableModel.setValueAt((Math.round(minibus.getPrejdeneKilometre() * 100d) / 100d), i, 5);
                }
            }
        }

        Pracovnik[] pracovnici = pozicovna.agentObsluhy().getPracovnici();

        if (pracovnici != null){
            Pracovnik pracovnik;
            for (int i = 0; i < pracovnici.length; i++) {
                pracovnik = pracovnici[i];
                if (pracovnik != null){
                    tableModel1.setValueAt(pracovnik.isObsadeny(), i, 1);
                }
            }
        }

        cal.set(0,0,0,0,0,0);
        cal.add(Calendar.SECOND, (int)(pozicovna.currentTime()) + 15*60*60);
        String newTime = df.format(cal.getTime());
        jlCas.setText(newTime);
        pozicovna.setSimSpeed(sliderFrekvencia.getValue(), (double)sliderRychlost.getValue()/100);
//        for (int i = model.getRowCount() -1; i >= 0; i--) {
//            Object[] row = getNewRow();
//            model.setValueAt(row[0], i, 0);
//            model.setValueAt(row[1], i, 1);
//        }
    }

    private void vykresliGraf(int minibusy, double vysledok) {
        if (vysledok < spodna1) {
            spodna1 = vysledok - 0.000001;
        }
        if (vysledok > horna1) {
            horna1 = vysledok + 0.000001;
        }
        this.lineChart.getXYPlot().getRangeAxis().setRange(spodna1, horna1);
        this.series.add(minibusy, vysledok);
    }

    private void vykresliGraf2(int pracovnici, double vysledok) {
        if (vysledok < spodna2) {
            spodna2 = vysledok - 0.000001;
        }
        if (vysledok > horna2) {
            horna2 = vysledok + 0.000001;
        }
        this.lineChart2.getXYPlot().getRangeAxis().setRange(spodna2, horna2);
        this.series2.add(pracovnici, vysledok);
    }

    @Override
    public void simStateChanged(Simulation simulation, SimState simState) {
    }

    @Override
    public void refresh(Simulation simulation) {
        MySimulation simulation1 = (MySimulation) simulation;
        JPanel activePanel = (JPanel) tabbedPane1.getSelectedComponent();
        if (activePanel == jpReplikacia) {
            vypisVysledkyVCase(simulation1);
        } else if (activePanel == jpReplikacie) {
            if (!simulation1.isReplicationRunning())
                vypisVysledkyReplikacii(simulation1);
        } else if (activePanel == jpZavislostMinibusov) {
            if (!simulation1.isRunning())
                vykresliGraf(simulation1.getPocetMinibusov(), simulation1.getCasVSystemePrichZak().mean() /60);
        } else if (activePanel == jpZavislostPracovnikov) {
            if (!simulation1.isRunning())
                vykresliGraf2(simulation1.getPocetPracovnikov(), simulation1.getCasVSystemePrichZak().mean() /60);
        }
    }
}
