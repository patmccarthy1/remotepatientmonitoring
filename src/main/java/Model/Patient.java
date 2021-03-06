package Model;

import Database.DBConnect;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static array.dou_ListtoArray.dou_ListtoArray;
import static array.str_ListtoArray.str_ListtoArray;
import static array.subArray.subArray;

public class Patient {
    private String givenname;
    private String familyname;
    private String id;
    private String age;
    private String admdate;
    private ECG ecg;
    private Heartbeat heartbeat;
    private Bodytemperature bodytemperature;
    private Bloodpressure bloodpressure;
    private Diastolicpressure diastolicpressure;
    private Respiratoryrate respiratoryrate;
    private int locator=0;
    private double hrcurrent;
    private double btcurrent;
    private double bpcurrent;
    private double dpbcurrent;
    private double rrcurrent;


    public  Patient ()
    {
        double[] xtemp = new double[3600];
        double[] ytemp = new double[3600];
        for(int i=0; i<xtemp.length; i++)
        {
            xtemp[i] = i;
            ytemp[i] = 1;
        }
        ecg = new ECG(new double[][]{xtemp, ytemp});
        for(int i=0; i<xtemp.length; i++)
        {
            ytemp[i] = 75;
        }
        heartbeat = new Heartbeat(new double[][]{xtemp, ytemp});
        for(int i=0; i<xtemp.length; i++)
        {
            ytemp[i] = 117.5;
        }
        bloodpressure = new Bloodpressure(new double[][]{xtemp, ytemp});
        for(int i=0; i<xtemp.length; i++)
        {
            ytemp[i] = 37;
        }
        diastolicpressure = new Diastolicpressure(new double[][]{xtemp, ytemp});
        for(int i=0; i<xtemp.length; i++)
        {
            ytemp[i] = 80;
        }
        bodytemperature = new Bodytemperature(new double[][]{xtemp, ytemp});
        for (int i=0; i<xtemp.length; i++)
        {
            ytemp[i] = 12.5;
        }
        respiratoryrate = new Respiratoryrate(new double[][]{xtemp, ytemp});
    }
    public Patient(int i)
    {
        ArrayList<String> ECGxdata_list = new ArrayList<String>();
        ArrayList<Double> ECGydata_list = new ArrayList<Double>();
        ArrayList<Double> Heartbeatxdata_list = new ArrayList<Double>();
        ArrayList<Double> Heartbeatydata_list = new ArrayList<Double>();
        ArrayList<Double> Bodytemperaturexdata_list = new ArrayList<Double>();
        ArrayList<Double> Bodytemperatureydata_list = new ArrayList<Double>();
        ArrayList<Double> Bloodpressurexdata_list = new ArrayList<Double>();
        ArrayList<Double> Bloodpressureydata_list = new ArrayList<Double>();
        ArrayList<Double> Diastolicpressurexdata_list = new ArrayList<Double>();
        ArrayList<Double> Diastolicpressureydata_list = new ArrayList<Double>();
        ArrayList<Double> Respiratoryratexdata_list = new ArrayList<Double>();
        ArrayList<Double> Respiratoryrateydata_list = new ArrayList<Double>();

        DBConnect database = new DBConnect();
        try {
            //Requests & Execution (SQL)
            String request = "select * from graphofpatient inner join patients on(patients.id=graphofpatient.patientid)\n" +
                    "                             inner join graphs on(graphs.id=graphofpatient.graphid)\n" +
                    "                             inner join heartbeatgraphs on(heartbeatgraphs.id=graphofpatient.graphid)\n "+
                    "inner join bodytemperaturegraphs on (bodytemperaturegraphs.id = graphofpatient.graphid)\n"+
                    "inner join bloodpressuregraph on (bloodpressuregraph.id = graphofpatient.graphid)\n"+
                    "inner join systolicpressuregraphs on (systolicpressuregraphs.id = graphofpatient.graphid)\n"+
                    "inner join respiratoryrategraphs on (respiratoryrategraphs.id = graphofpatient.graphid)\n"+
                    "where patientid="+i+";"; //could have any SQL command
            Statement stmt = database.getconnection().createStatement(); //what executes command
            ResultSet res = stmt.executeQuery(request);  //what executes command

            //reads through column of table
            while (res.next()) {
                Array ECGxdata_temp = res.getArray("ecg_xdata"); // put result of request in a string
                Array ECGydata_temp = res.getArray("ecg_ydata"); // put result of request in a string
                Array Heartbeatxdata_temp = res.getArray("heartbeat_xdata"); // put result of request in a string
                Array Heartbeatydata_temp = res.getArray("heartbeat_ydata"); // put result of request in a string
                Array Bodytemperaturexdata_temp = res.getArray("bodytemperaturexdata");
                Array Bodytemperatureydata_temp = res.getArray("bodytemperatureydata");
                Array Bloodpressurexdata_temp = res.getArray("bloodpressurexdata");
                Array Bloodpressureydata_temp = res.getArray("bloodpressureydata");
                Array Respiratoryratexdata_temp = res.getArray("respiratoryratexdata");
                Array Respiratoryrateydata_temp = res.getArray("respiratoryrateydata");
                Array Diastolicpressurexdata_temp = res.getArray("systolic_xdata");
                Array Diastolicpressureydata_temp = res.getArray("systolic_ydata");
                String givenname_temp = res.getString("givenname");
                String familyanme_temp = res.getString("familyname");
                String id_temp = res.getString("id");
                String age_temp = res.getString("age");
                String admdate_temp = res.getString("admissiondate");

                //Adds data to temporary array _temp
                for (String strTemp : (String[]) ECGxdata_temp.getArray()) {
                    ECGxdata_list.add(strTemp);
                }
                for (Double douTemp : (Double[]) ECGydata_temp.getArray()) {
                    ECGydata_list.add(douTemp);
                }
                for (Double douTemp : (Double[]) Heartbeatxdata_temp.getArray()) {
                    Heartbeatxdata_list.add(douTemp);
                }
                for (Double douTemp : (Double[]) Heartbeatydata_temp.getArray()) {
                    Heartbeatydata_list.add(douTemp);
                }
                for (Double douTemp : (Double[]) Bodytemperaturexdata_temp.getArray()) {
                    Bodytemperaturexdata_list.add(douTemp);
                }
                for (Double douTemp : (Double[]) Bodytemperatureydata_temp.getArray()) {
                    Bodytemperatureydata_list.add(douTemp);
                }
                for (Double douTemp : (Double[]) Bloodpressurexdata_temp.getArray()) {
                    Bloodpressurexdata_list.add(douTemp);
                }
                for (Double douTemp : (Double[]) Bloodpressureydata_temp.getArray()) {
                    Bloodpressureydata_list.add(douTemp);
                }
                for (Double douTemp : (Double[]) Respiratoryratexdata_temp.getArray()) {
                    Respiratoryratexdata_list.add(douTemp);
                }
                for (Double douTemp : (Double[]) Respiratoryrateydata_temp.getArray()) {
                    Respiratoryrateydata_list.add(douTemp);
                }
                for (Double douTemp : (Double[]) Diastolicpressurexdata_temp.getArray()) {
                    Diastolicpressurexdata_list.add(douTemp);
                }
                for (Double douTemp : (Double[]) Diastolicpressureydata_temp.getArray()) {
                    Diastolicpressureydata_list.add(douTemp);
                }


                givenname = givenname_temp;
                familyname = familyanme_temp;
                id = id_temp;
                age = age_temp;
                admdate = admdate_temp;

                System.out.println(givenname);


            }
            stmt.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.toString());
        }

        //adds xdata and ydata
        double[] xdata = str_ListtoArray(ECGxdata_list);
        double[] ydata = dou_ListtoArray(ECGydata_list);
        ecg = new ECG(new double[][]{xdata, ydata});

        xdata = dou_ListtoArray(Heartbeatxdata_list);
        ydata = dou_ListtoArray(Heartbeatydata_list);
        heartbeat = new Heartbeat(new double[][]{xdata, ydata});
        hrcurrent = ydata[99];

        xdata = dou_ListtoArray(Bodytemperaturexdata_list);
        ydata = dou_ListtoArray(Bodytemperatureydata_list);
        bodytemperature = new Bodytemperature(new double[][]{xdata, ydata});
        btcurrent = ydata[99];

        xdata = dou_ListtoArray(Bloodpressurexdata_list);
        ydata = dou_ListtoArray(Bloodpressureydata_list);
        bloodpressure = new Bloodpressure(new double[][]{xdata, ydata});
        bpcurrent = ydata[99];

        xdata = dou_ListtoArray(Respiratoryratexdata_list);
        ydata = dou_ListtoArray(Respiratoryrateydata_list);
        respiratoryrate = new Respiratoryrate(new double[][]{xdata, ydata});
        rrcurrent = ydata[99];

        xdata = dou_ListtoArray(Diastolicpressurexdata_list);
        ydata = dou_ListtoArray(Diastolicpressureydata_list);
        diastolicpressure = new Diastolicpressure(new double[][]{xdata, ydata});
        dpbcurrent = ydata[99];

        System.out.println(Arrays.toString(ecg.get_xdata()));


    }

    //following functions return ydata of each vital sign, so it can be read in the recordspanel
    public double[] getHRdata(){
        return(heartbeat.get_ydata());
    }
    public double[] getBPdata(){
        return(bloodpressure.get_ydata());
    }
    public double[] getBTdata(){
        return(bodytemperature.get_ydata());
    }
    public double[] getRRdata(){
        return(respiratoryrate.get_ydata());
    }
    public double[] getDBPdata() {return (diastolicpressure.get_ydata());}
    public double[] getTimedata(){
        return(heartbeat.get_xdata());
    }

    //Accessors: return the data so it can be plotted live in VitalSignsPanel
    public double[][] ECGgetsnippet(int locator)
    {
        //System.out.println(Arrays.toString(subArray(ecg.get_xdata(), locator, locator+99)));
        return(new double[][]{subArray(ecg.get_xdata(), locator, locator+99), subArray(ecg.get_ydata(), locator, (locator++)+99)});
    }
    public double[][] ECGgetsnippet(int locator, int zoom)
    {
        //System.out.println(Arrays.toString(subArray(ecg.get_xdata(), locator, locator+99)));
        return(new double[][]{subArray(ecg.get_xdata(), locator, locator+zoom), subArray(ecg.get_ydata(), locator, (locator++)+zoom)});
    }

    public double[][] Heartbeatgetsnippet(int locator)
    {
        hrcurrent = heartbeat.get_ydata()[locator+99];
        return(new double[][]{subArray(heartbeat.get_xdata(), locator, locator+99), subArray(heartbeat.get_ydata(), locator, (locator++)+99)});
    }
    public double[][] Heartbeatgetsnippet(int locator, int zoom)
    {
        hrcurrent = heartbeat.get_ydata()[locator+zoom];
        return(new double[][]{subArray(heartbeat.get_xdata(), locator, locator+zoom), subArray(heartbeat.get_ydata(), locator, (locator++)+zoom)});
    }

    public double[][] Bodytemperaturegetsnippet (int locator)
    {
        btcurrent = bodytemperature.get_ydata()[locator+99];
        return(new double[][]{subArray(bodytemperature.get_xdata(), locator, locator+99), subArray(bodytemperature.get_ydata(), locator, (locator++)+99)});
    }
    public double[][] Bodytemperaturegetsnippet (int locator, int zoom)
    {
        btcurrent = bodytemperature.get_ydata()[locator+zoom];
        return(new double[][]{subArray(bodytemperature.get_xdata(), locator, locator+zoom), subArray(bodytemperature.get_ydata(), locator, (locator++)+zoom)});
    }

    public double[][] Bloodpressuregetsnippet (int locator)
    {
        bpcurrent = bloodpressure.get_ydata()[locator+99];
        return(new double[][]{subArray(bloodpressure.get_xdata(), locator, locator+99), subArray(bloodpressure.get_ydata(), locator, (locator++)+99)});
    }
    public double[][] Bloodpressuregetsnippet (int locator, int zoom)
    {
        bpcurrent = bloodpressure.get_ydata()[locator+zoom];
        return(new double[][]{subArray(bloodpressure.get_xdata(), locator, locator+zoom), subArray(bloodpressure.get_ydata(), locator, (locator++)+zoom)});
    }

    public double[][] Respiratoryrategetsnippet (int locator)
    {
        rrcurrent = respiratoryrate.get_ydata()[locator+99];
        return(new double[][]{subArray(respiratoryrate.get_xdata(), locator, locator+99), subArray(respiratoryrate.get_ydata(), locator, (locator++)+99)});
    }
    public double[][] Respiratoryrategetsnippet (int locator, int zoom)
    {
        rrcurrent = respiratoryrate.get_ydata()[locator+zoom];
        return(new double[][]{subArray(respiratoryrate.get_xdata(), locator, locator+zoom), subArray(respiratoryrate.get_ydata(), locator, (locator++)+zoom)});
    }
    public double[][] Diastolidgetsnippet (int locator)
    {
        rrcurrent = respiratoryrate.get_ydata()[locator+99];
        return(new double[][]{subArray(diastolicpressure.get_xdata(), locator, locator+99), subArray(diastolicpressure.get_ydata(), locator, (locator++)+99)});
    }
    public double[][] Diastolidgetsnippet (int locator, int zoom)
    {
        rrcurrent = respiratoryrate.get_ydata()[locator+zoom];
        return(new double[][]{subArray(diastolicpressure.get_xdata(), locator, locator+zoom), subArray(diastolicpressure.get_ydata(), locator, (locator++)+zoom)});
    }


    //Accessors: return strings so they can be printed in the profile panel and records panel
    public String getGivenname()
    {
        return givenname;
    }
    public String getFamilyname()
    {
        return familyname;
    }
    public String getId() {return  id;}
    public String getAge() { return age;}
    public String getDate() { return admdate;}

    //finds the minimum and maximum of each vital sign, to set the boundaries of the plots
    public double[] minmaxECG(){
        double min=0;
        double max=0;
        for (double douTemp : ecg.get_ydata()) {
            if (douTemp<min || min==0)
            {
                min = douTemp;
            }
            else if (douTemp>max || max==0)
            {
                max = douTemp;
            }
        }
        return new double[]{min, max};
    }

    public double[] minmaxHR(){
        double min=0;
        double max=0;
        for (double douTemp : heartbeat.get_ydata()) {
            if (douTemp<min || min==0)
            {
                min = douTemp;
            }
            else if (douTemp>max || max==0)
            {
                max = douTemp;
            }
        }
        return new double[]{min, max};
    }

    public double[] minmaxBT(){
        double min=0;
        double max=0;
        for (double douTemp : bodytemperature.get_ydata()) {
            if (douTemp<min || min==0)
            {
                min = douTemp;
            }
            else if (douTemp>max || max==0)
            {
                max = douTemp;
            }
        }
        return new double[]{min, max};
    }

    public double[] minmaxBP(){
        double min=0;
        double max=0;
        for (double douTemp : bloodpressure.get_ydata()) {
            if (douTemp<min || min==0)
            {
                min = douTemp;
            }
            else if (douTemp>max || max==0)
            {
                max = douTemp;
            }
        }
        return new double[]{min, max};
    }

    public double[] minmaxRR(){
        double min=0;
        double max=0;
        for (double douTemp : respiratoryrate.get_ydata()) {
            if (douTemp<min || min==0)
            {
                min = douTemp;
            }
            else if (douTemp>max || max==0)
            {
                max = douTemp;
            }
        }
        return new double[]{min, max};
    }

    public double getHrcurrent() {return hrcurrent;}
    public double getDBpcurrent() {
        return bpcurrent;
    }
    public double getBtcurrent() {
        return btcurrent;
    }
    public double getRrcurrent() {
        return rrcurrent;
    }
    public double getDpbcurrent() {return  dpbcurrent;}

    public void Augment(int locator){
        ecg.Augment(locator);
        bloodpressure.Augment(locator);
        bodytemperature.Augment(locator);
        diastolicpressure.Augment(locator);
        heartbeat.Augment(locator);
        respiratoryrate.Augment(locator);
    }
    public void Decrement(int locator){
        ecg.Decrement(locator);
        bloodpressure.Decrement(locator);
        bodytemperature.Decrement(locator);
        diastolicpressure.Decrement(locator);
        heartbeat.Decrement(locator);
        respiratoryrate.Decrement(locator);
    }
}

