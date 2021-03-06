package Alarm;


import Model.Patient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Alarm {
    private double warning_bound_low;
    private double warning_bound_high;
    private double danger_bound_low;
    private double danger_bound_high;
    Patient patient;

    private double[] values;
    private JPanel alarmcontainer;
    private JPanel message;
    private JPanel mainalarmpanel;


    public Alarm(Patient p, double warning_bound_low, double warning_bound_high, double danger_bound_low, double danger_bound_high, double value) {
        //this.patient=p;
        //this.value=value;
        this.warning_bound_low = warning_bound_low;
        this.warning_bound_high = warning_bound_high;
        this.danger_bound_low = danger_bound_low;
        this.danger_bound_high = danger_bound_high;

        message=new JPanel();
        alarmcontainer = new JPanel();
        mainalarmpanel=new JPanel();
        alarmcontainer.setOpaque(true);
        mainalarmpanel.setLayout(new GridLayout(5,1));

        alarmcontainer.setBackground(Color.black);
        message.setBackground(Color.black);

    }

    public JPanel getAlarm(double value){
        update(value);
        return mainalarmpanel;
    }


    public JPanel addText(double value){
        alarmcontainer.updateUI();
        alarmcontainer.removeAll();
            JLabel label=new JLabel();
            label.setVerticalAlignment(JLabel.CENTER);
            label.setText("<html><h2><font color=white>"+(String.valueOf(value))+"</font></h2>");
        alarmcontainer.add(label);
        return alarmcontainer;
    }

    //Message warning system instead of colored backgrounds
    public double getValue(int i){
       return values[i];
    }

    public JPanel update(double value){
        message.removeAll();
        if (value > danger_bound_high || value < danger_bound_low) {
            message.add(new JLabel("<html><h2><font color=red>Danger</font></h2>"));
            message.setVisible(true);
        }
        else if ((value > warning_bound_high && value < danger_bound_high) || (value < warning_bound_low && value > danger_bound_low)) {
            message.add(new JLabel("<html><h2><font color=orange>Warning</font></h2>"));
            message.setVisible(true);

        } else {
            message.add(new JLabel("<html><h2><font color=green>Normal</font></h2>"));
            message.setVisible(true);
        }
        message.updateUI();
        return alarmcontainer;
        //mainalarmpanel.add(alarmcontainer);
        //mainalarmpanel.add(message);
    }

}