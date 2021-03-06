package LiveMonitoringPage;

import Database.DBConnect;
import Model.Patient;
import RecordsPage.RecordsPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfilePanel extends JPanel  {
    //Declaration of Panels
    private JPanel patientProfile;
    private JPanel info, heartrate,bodytemp,bloodpress,resprate;
    DBConnect database;
    private Patient patient;
    RecordsPanel recordsPanel;

    //Sample values to be replaced with database values
    private double normalvalues[];

    private ResultSet res;
    private String request;
    String familyname;
    String surname;
    String admdate;
    String idno;
    private Connection conn;

    Double hrcurrent;
    Double btcurrent;
    Double rrcurrent;
    Double bpcurrent;

    public ProfilePanel() {
        patient = new Patient();
        familyname = patient.getFamilyname();
        surname = patient.getGivenname();
        admdate = patient.getDate();
        idno = patient.getId();
        recordsPanel=new RecordsPanel(patient);

        normalvalues= new double[]{1, 23, 23, 323, 23};


        //Instantiation of panels
        patientProfile =new JPanel();
        info = new JPanel();

        //Layout of PatientProfile
        patientProfile.setLayout(new GridLayout(10,1));
        patientProfile.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE) );


        JLabel namelabel= new JLabel("<html> <font color=white> Name: ");
        JLabel idlabel=new JLabel("<html> <font color=white>ID ");
        JLabel datelabel=new JLabel("<html> <font color=white>Admission Date ");
        JLabel name= new JLabel("<html> <font color=white>" +familyname+ " " + "<html> <font color=white>"+surname);
        JLabel id=new JLabel("<html> <font color=white>" + idno);
        JLabel date=new JLabel("<html> <font color=white>" + admdate);

        //Vital Signs Panels and Layouts
        heartrate=new JPanel();
        heartrate.setLayout(new GridLayout(1,2));
        bodytemp=new JPanel();
        bodytemp.setLayout(new GridLayout(1,2));
        resprate=new JPanel();
        resprate.setLayout(new GridLayout(1,2));
        bloodpress=new JPanel();
        bloodpress.setLayout(new GridLayout(1,2));

        //Add values to info + 5 Vital Signs Panel *to be replaced with database fetched values*
        info.setLayout(new GridLayout(3, 2));
        info.add(namelabel);
        info.add(name);
        info.add(idlabel);
        info.add(id);
        info.add(datelabel);
        info.add(date);

        heartrate.add(new JLabel("<html> <h4> <font color=white> Heart Rate  </h4>"));
        heartrate.add(new JLabel("<html> <font color=white>" + String.valueOf(recordsPanel.getAvgheartrate())+" bpm", SwingConstants.LEFT));

        bodytemp.add(new JLabel("<html> <h4> <font color=white> Body Temperature  </h4>", SwingConstants.LEFT));
        bodytemp.add(new JLabel("<html> <font color=white>" +  String.valueOf(recordsPanel.getAvgbodytemperature())+ " °C"));


        resprate.add(new JLabel("<html> <h4>  <font color=white> Respiratory Rate: </h4>"));
        resprate.add(new JLabel("<html> <font color=white>" +  String.valueOf(recordsPanel.getAvgrespiratoryrate())+ " breaths/min"));

        bloodpress.add(new JLabel("<html> <h4> <font color=white> Blood Pressure:   </h4>"));
        bloodpress.add(new JLabel(("<html> <font color=white>" + patient.getDBpcurrent())+ "/"+recordsPanel.getAvgbloodpressure()+ " mmHg (systolic/diastolic)"));

        info.setBackground(Color.black);
        heartrate.setBackground(Color.black);
        bodytemp.setBackground(Color.black);
        resprate.setBackground(Color.black);
        bloodpress.setBackground(Color.black);

        //Add all information to patientProfile Panel
        patientProfile.add(info);
        patientProfile.add(heartrate);
        patientProfile.add(bodytemp);
        patientProfile.add(resprate);
        patientProfile.add(bloodpress);
        patientProfile.setBackground(Color.black);


        patientProfile.setPreferredSize(new Dimension(300,640));

        hrcurrent = patient.getHrcurrent();
        btcurrent = patient.getBtcurrent();
        rrcurrent = patient.getRrcurrent();
        bpcurrent = patient.getDBpcurrent();


    }
    public ProfilePanel(Patient p) {
        patient = p;

        familyname = patient.getFamilyname();
        surname = patient.getGivenname();
        admdate = patient.getDate();
        idno = patient.getId();

        //Instantiation of panels
        patientProfile =new JPanel();
        info = new JPanel();

        //Layout of PatientProfile
        patientProfile.setLayout(new GridLayout(10,1));
        patientProfile.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE) );

        //Creates JLabels to be added to the JPanel info.
        JLabel namelabel= new JLabel("<html> <font color=white> Name: ");
        JLabel idlabel=new JLabel("<html> <font color=white>ID ");
        JLabel datelabel=new JLabel("<html> <font color=white>Admission Date ");
        JLabel name= new JLabel("<html> <font color=white>" +familyname+ " " + "<html> <font color=white>"+surname);
        JLabel id=new JLabel("<html> <font color=white>" + idno);
        JLabel date=new JLabel("<html> <font color=white>" + admdate);

        //Vital Signs Panels and Layouts
        heartrate=new JPanel();
        heartrate.setLayout(new GridLayout(1,2));
        bodytemp=new JPanel();
        bodytemp.setLayout(new GridLayout(1,2));
        resprate=new JPanel();
        resprate.setLayout(new GridLayout(1,2));
        bloodpress=new JPanel();
        bloodpress.setLayout(new GridLayout(1,2));

        //Add values to info + 5 Vital Signs Panel *to be replaced with database fetched values*
        info.setLayout(new GridLayout(3, 2));
        info.add(namelabel);
        info.add(name);
        info.add(idlabel);
        info.add(id);
        info.add(datelabel);
        info.add(date);

        //Add for each vital sign the current values
        heartrate.add(new JLabel("<html> <h4> <font color=white> Heart Rate  </h4>"));
        heartrate.add(new JLabel("<html> <font color=white>" + String.valueOf(patient.getHrcurrent())+" bpm", SwingConstants.LEFT));

        bodytemp.add(new JLabel("<html> <h4> <font color=white> Body Temperature  </h4>", SwingConstants.LEFT));
        bodytemp.add(new JLabel("<html> <font color=white>" + patient.getBtcurrent()+ " °C"));


        resprate.add(new JLabel("<html> <h4>  <font color=white> Respiratory Rate: </h4>"));
        resprate.add(new JLabel(("<html> <font color=white>" + patient.getRrcurrent())+ " breaths/min"));

        bloodpress.add(new JLabel("<html> <h4> <font color=white> Blood Pressure:   </h4>"));
        bloodpress.add(new JLabel(("<html> <font color=white>" + patient.getDBpcurrent())+ "/"+patient.getDpbcurrent()+ " mmHg (systolic/diastolic)"));

        //change background to black
        info.setBackground(Color.black);
        heartrate.setBackground(Color.black);
        bodytemp.setBackground(Color.black);
        resprate.setBackground(Color.black);
        bloodpress.setBackground(Color.black);

        //Add all information to patientProfile Panel
        patientProfile.add(info);
        patientProfile.add(heartrate);
        patientProfile.add(bodytemp);
        patientProfile.add(resprate);
        patientProfile.add(bloodpress);
        patientProfile.setBackground(Color.black);


        patientProfile.setPreferredSize(new Dimension(300,640));

        hrcurrent = patient.getHrcurrent();
        btcurrent = patient.getBtcurrent();
        rrcurrent = patient.getRrcurrent();
        bpcurrent = patient.getDBpcurrent();
    }

    //functions return the patient profile so it can be called in LiveMonitoringPageView
    public JPanel getPatientProfile() {
        return patientProfile;
    }


    //updates and repaints the panel
    public void Update() {

        double hrnext = patient.getHrcurrent();
        double btnext = patient.getBtcurrent();
        double rrnext = patient.getRrcurrent();
        double bpnext = patient.getDBpcurrent();
        double dpbnext = patient.getDpbcurrent();
        if(hrnext != hrcurrent){ //repaints each time the values changes
            heartrate.removeAll();
            heartrate.add(new JLabel("<html> <h4> <font color=white> Heart Rate  </h4>"));
            heartrate.add(new JLabel("<html> <font color=white>" + hrnext + " bpm", SwingConstants.LEFT));
            hrcurrent = hrnext;
        }
        if(btnext != btcurrent) {
            bodytemp.removeAll();
            bodytemp.add(new JLabel("<html> <h4> <font color=white> Body Temperature  </h4>", SwingConstants.LEFT));
            bodytemp.add(new JLabel("<html> <font color=white>" + btnext + " °C"));
            btcurrent = btnext;
        }
        if(rrnext!=rrcurrent){
            resprate.removeAll();
            resprate.add(new JLabel("<html> <h4>  <font color=white> Respiratory Rate: </h4>"));
            resprate.add(new JLabel(("<html> <font color=white>" + rrnext + " breaths/min")));
            rrcurrent = rrnext;
        }
        if(bpnext!=bpcurrent) {
            bloodpress.removeAll();
            bloodpress.add(new JLabel("<html> <h4> <font color=white> Blood Pressure:   </h4>"));
            bloodpress.add(new JLabel(("<html> <font color=white>" + bpnext + "/" + dpbnext + " mmHg (systolic/diastolic)")));
            bpcurrent = bpnext;
        }

        patientProfile.repaint();
    }

}
