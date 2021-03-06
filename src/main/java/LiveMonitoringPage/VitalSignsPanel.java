package LiveMonitoringPage;

import Alarm.*;
import Model.ECG;
import Model.Patient;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static java.lang.StrictMath.round;


public class VitalSignsPanel extends JPanel {

    int locator = 0;
    int zoom = 99;
    Alarmpanel alarmpanel;
    Patient patient;

    // panels
    JPanel graphPanel;
    JPanel mainPanel;
    JPanel ecgPanel;
    JPanel hrPanel;
    JPanel tempPanel;
    JPanel bpPanel;
    JPanel rrPanel;

    // charts
    XYChart ecgChart;
    XYChart hrChart;
    XYChart tempChart;
    XYChart bpsChart;
    XYChart bpdChart;
    XYChart rrChart;

    //minmax
    double[] minmaxECG;


    // METHODS

    // constructor method

    public VitalSignsPanel() {
        locator=0;
        patient = new Patient();
        double phase = 0;
        double[][] data_ECG = getECGData(locator);
        double[][] data_HR = getHRData(locator);
        double[][] data_temp = getTempData(locator);
        double[][] data_BPS =  getBPSData(locator);
        //double[][] data_BPD =  getBPDData();
        double[][] data = getRespiratoryData(locator);

        // min max info
        double[] minmaxECG = patient.minmaxECG();

        // create charts
        ecgChart = QuickChart.getChart("ECG", "Time /s", "Voltage /mV", "sine", data_ECG[0], data_ECG[1]);
        hrChart = QuickChart.getChart("Heart Rate", "Time /s", "Rate /BPM", "sine", data_HR[0], data_HR[1]);
        tempChart = QuickChart.getChart("Surface Body Temperature", "Time /s", "Temperature /C", "sine", data_temp[0], data_temp[1]);
        bpsChart = QuickChart.getChart("Systolic Blood Pressure", "Time /s", "Pressure /mmHg", "sine", data_BPS[0], data_BPS[1]);
        //bpdChart = QuickChart.getChart("Systolic Blood Pressure", "Time /s", "Pressure /mmHg", "sine", data_BPD[0], data_BPD[1]);
        rrChart = QuickChart.getChart("Respiratory Rate", "Time /s", "Rate /BrPM", "sine", data[0], data[1]);
        ecgChart.getStyler().setPlotBackgroundColor(Color.black);
        alarmpanel=new Alarmpanel(patient);

        // create panels
        graphPanel = new JPanel();
        ecgPanel = new XChartPanel(ecgChart);
        hrPanel = new XChartPanel(hrChart);
        tempPanel = new XChartPanel(tempChart);
        bpPanel = new XChartPanel(bpsChart);
        rrPanel = new XChartPanel(rrChart);
        mainPanel=new JPanel();

        // add chart panels to main panel
        graphPanel.setLayout(new GridLayout(5, 2));
        this.graphPanel.add(ecgPanel);
        this.graphPanel.add(hrPanel);
        this.graphPanel.add(tempPanel);
        this.graphPanel.add(bpPanel);
        this.graphPanel.add(rrPanel);
        graphPanel.setPreferredSize(new Dimension (800,640));
        graphPanel.validate();
        graphPanel.setVisible(true);

        this.mainPanel.add(graphPanel);
        this.mainPanel.add(alarmpanel.getValuesPanel());
        mainPanel.validate();
        mainPanel.setVisible(true);

    }
    public VitalSignsPanel(Patient p) {

        //create patient
        patient = p;


        // data for sine wave
        double phase = 0;
        double[][] data_ECG = getECGData(locator);
        double[][] data_HR = getHRData(locator);
        double[][] data_temp = getTempData(locator);
        double[][] data_BPS =  getBPSData(locator);
        //double[][] data_BPD =  getBPDData();
        double[][] data = getRespiratoryData(locator);

        // min max info
        double[] minmaxECG = patient.minmaxECG();

        // create charts
        ecgChart = QuickChart.getChart("ECG", "Time /s", "Voltage /mV", "sine", data_ECG[0], data_ECG[1]);
        hrChart = QuickChart.getChart("Heart Rate", "Time /s", "Rate /BPM", "sine", data_HR[0], data_HR[1]);
        tempChart = QuickChart.getChart("Surface Body Temperature", "Time /s", "Temperature /C", "sine", data_temp[0], data_temp[1]);
        bpsChart = QuickChart.getChart("Systolic Blood Pressure", "Time /s", "Pressure /mmHg", "sine", data_BPS[0], data_BPS[1]);
        //bpdChart = QuickChart.getChart("Systolic Blood Pressure", "Time /s", "Pressure /mmHg", "sine", data_BPD[0], data_BPD[1]);
        rrChart = QuickChart.getChart("Respiratory Rate", "Time /s", "Rate /BrPM", "sine", data[0], data[1]);


        ecgChart.getStyler().setPlotBackgroundColor(Color.black);
        alarmpanel=new Alarmpanel(p);

        // create panels
        graphPanel = new JPanel();
        ecgPanel = new XChartPanel(ecgChart);
        hrPanel = new XChartPanel(hrChart);
        tempPanel = new XChartPanel(tempChart);
        bpPanel = new XChartPanel(bpsChart);
        rrPanel = new XChartPanel(rrChart);
        mainPanel=new JPanel();

        // add chart panels to main panel
        graphPanel.setLayout(new GridLayout(5, 2));
        this.graphPanel.add(ecgPanel);
        this.graphPanel.add(hrPanel);
        this.graphPanel.add(tempPanel);
        this.graphPanel.add(bpPanel);
        this.graphPanel.add(rrPanel);
        graphPanel.setPreferredSize(new Dimension (800,640));
        graphPanel.validate();
        graphPanel.setVisible(true);

        //mainPanel.setLayout(new GridLayout(1,1));
        this.mainPanel.add(graphPanel);
        this.mainPanel.add(alarmpanel.getValuesPanel());
        //mainPanel.setPreferredSize(new Dimension (1000,640));
        mainPanel.validate();
        mainPanel.setVisible(true);

    }

    // get main panel method
    public JPanel getMainPanel() {
        return mainPanel;
    }

    //gets data from patient class
    protected  double[][] getRespiratoryData(int locator) {
        return patient.Respiratoryrategetsnippet(locator);
    }
    protected  double[][] getRespiratoryData(int locator, int zoom) {
        return patient.Respiratoryrategetsnippet(locator, zoom);
    }
    protected  double[][] getTempData(int locator) {
        return patient.Bodytemperaturegetsnippet(locator);
    }
    protected  double[][] getTempData(int locator, int zoom) {
        return patient.Bodytemperaturegetsnippet(locator, zoom);
    }

    protected double[][] getBPSData(int locator) {
        return patient.Bloodpressuregetsnippet(locator);
    }
    protected double[][] getBPSData(int locator, int zoom) {
        return patient.Bloodpressuregetsnippet(locator, zoom);
    }

    protected double[][] getECGData(int locator) {
        return patient.ECGgetsnippet(locator);
    }
    protected double[][] getECGData(int locator, int zoom) {
        return patient.ECGgetsnippet(locator, zoom);
    }

    protected double[][] getHRData(double phase) {
        return patient.Heartbeatgetsnippet(locator);
    }
    protected double[][] getHRData(int locator, int zoom) {
        return patient.Heartbeatgetsnippet(locator, zoom);
    }

    protected  double[][] getDiaData()
    {
        return patient.Diastolidgetsnippet(locator);
    }
    protected  double[][] getDiaData(int locator, int zoom)
    {
        return patient.Diastolidgetsnippet(locator, zoom);
    }

    public void updatePanel(){
        //System.out.println("updated " + patient.getGivenname());
        double[][] data_ECG = getECGData(++locator, zoom);
        double[] minmaxECG = patient.minmaxECG();
        ecgChart = QuickChart.getChart("ECG", "Time /s", "Voltage /mV", "sine", data_ECG[0], data_ECG[1]);
        ecgPanel = new XChartPanel(ecgChart);
        ecgChart.addSeries("minmax", new double[]{data_ECG[0][0]-2,data_ECG[0][0]-2}, minmaxECG);

        //Design
        ecgChart.getStyler().setPlotBackgroundColor(Color.black);
        ecgChart.getStyler().setChartBackgroundColor(Color.black);
        ecgChart.getStyler().setChartFontColor(Color.white);
        ecgChart.getStyler().setAxisTickLabelsColor(Color.white);
        ecgChart.getStyler().setSeriesColors(new Color[]{Color.blue, Color.black});
        JPanel ecgPanel2 = new XChartPanel(ecgChart);

        double[][] data_Heartbeat = getHRData(locator, zoom);
        double[] minmaxHR = patient.minmaxHR();
        hrChart = QuickChart.getChart("Heart Rate", "Time /s", "Rate /BPM", "sine", data_Heartbeat[0], data_Heartbeat[1]);
        hrPanel = new XChartPanel(hrChart);
        hrChart.addSeries("minmax", new double[]{data_Heartbeat[0][0]-2,data_Heartbeat[0][0]-2}, minmaxHR);

        //Design
        hrChart.getStyler().setPlotBackgroundColor(Color.black);
        hrChart.getStyler().setChartBackgroundColor(Color.black);
        hrChart.getStyler().setChartFontColor(Color.white);
        hrChart.getStyler().setAxisTickLabelsColor(Color.white);
        hrChart.getStyler().setSeriesColors(new Color[]{Color.magenta,Color.black});
        JPanel hrPanel2 = new XChartPanel(hrChart);


        double[][] data_Bloodpressure = getBPSData(locator, zoom);
        double[][] data_diastolid = getDiaData();
        double[] minmaxBP = patient.minmaxBP();
        bpsChart = QuickChart.getChart("Blood Pressure", "Time/s", "Pressure /mmHg", "sine", data_Bloodpressure[0], data_Bloodpressure[1]);
        bpsChart.addSeries("minmax", new double[]{data_Bloodpressure[0][0]-2,data_Bloodpressure[0][0]-2}, minmaxBP);
        bpsChart.addSeries("diastolid", data_diastolid[0], data_diastolid[1]);

        //Design
        bpsChart.getStyler().setPlotBackgroundColor(Color.black);
        bpsChart.getStyler().setChartBackgroundColor(Color.black);
        bpsChart.getStyler().setChartFontColor(Color.white);
        bpsChart.getStyler().setAxisTickLabelsColor(Color.white);
        bpsChart.getStyler().setSeriesColors(new Color[]{Color.pink,Color.black, Color.yellow});
        bpPanel = new XChartPanel(bpsChart);

        double[][] data_Bodytemperature = getTempData(locator, zoom);
        double [] minmaxBT = patient.minmaxBT();
        tempChart = QuickChart.getChart("Body Temperature", "Time/s", "Temperature Celsius", "sine", data_Bodytemperature[0], data_Bodytemperature[1]);
        tempChart.addSeries("minmax", new double[]{data_Bodytemperature[0][0]-2,data_Bodytemperature[0][0]-2}, minmaxBT);

        //Design
        tempChart.getStyler().setPlotBackgroundColor(Color.black);
        tempChart.getStyler().setChartBackgroundColor(Color.black);
        tempChart.getStyler().setChartFontColor(Color.white);
        tempChart.getStyler().setAxisTickLabelsColor(Color.white);
        tempChart.getStyler().setSeriesColors(new Color[]{Color.red,Color.black});
        tempPanel = new XChartPanel(tempChart);

        double[][] data_RespiratoryRate = getRespiratoryData(locator, zoom);
        double [] minmaxRR = patient.minmaxRR();
        rrChart = QuickChart.getChart("Respiratory Rate", "Time/s", "Breaths /min", "sine", data_RespiratoryRate[0], data_RespiratoryRate[1]);
        rrChart.addSeries("minmax", new double[]{data_RespiratoryRate[0][0]-2,data_RespiratoryRate[0][0]-2}, minmaxRR);
        //Design
        rrChart.getStyler().setPlotBackgroundColor(Color.black);
        rrChart.getStyler().setChartBackgroundColor(Color.black);
        rrChart.getStyler().setChartFontColor(Color.white);
        rrChart.getStyler().setAxisTickLabelsColor(Color.white);
        rrChart.getStyler().setSeriesColors(new Color[]{Color.green,Color.black});
        rrPanel = new XChartPanel(rrChart);

        graphPanel.removeAll();
        graphPanel.add(ecgPanel);
        graphPanel.add(hrPanel);
        graphPanel.add(tempPanel);
        graphPanel.add(bpPanel);
        graphPanel.add(rrPanel);


        //locator = locator+1;
    }

    public void Update()
    {
        alarmpanel.Update();

        updatePanel();

    }
    public void Augment(){
        patient.Augment(locator+99);
    }
    public void Decrement(){
        patient.Decrement(locator+99);
    }
    public void Zoomout(){zoom=zoom+100;}
    public void ZoomIn(){
        if(zoom>100)
        {
            zoom=zoom-100;
        }
        else if (zoom>20){zoom=round(zoom/2);}
    }



}

