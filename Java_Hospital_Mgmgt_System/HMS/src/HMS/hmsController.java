package HMS;


import java.text.ParseException;
import java.util.*;
import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;
import java.lang.Double;


public class hmsController {

    //declare arraylist
    private ArrayList arrHrList = new ArrayList();
    private ArrayList arrDoctorList = new ArrayList();
    private ArrayList arrReceptionistList = new ArrayList();
    private ArrayList arrPatientList = new ArrayList();
    private ArrayList arrPatientTreatmentList = new ArrayList();
    private ArrayList arrTreatmentList = new ArrayList();
    private ArrayList arrVisitedPatientList = new ArrayList();
    private ArrayList arrPaymentList = new ArrayList();
    private ArrayList arrInvoiceList = new ArrayList();
    private ArrayList arrWardList = new ArrayList();
    private ArrayList arrWardPatientList = new ArrayList();
    private ArrayList arrReportsList = new ArrayList();



    //declare files as constants(final)
    private final String txtHrFile = "hr.txt";
    private final String txtDoctorFile = "doctor.txt";
    private final String txtPatientFile = "patient.txt";
    private final String txtReceptionistFile = "receptionist.txt";
    private final String txtTreatmentFile = "treatment.txt";
    private final String txtVisitedPatientFile = "visitedPatient.txt";
    private final String txtPatientTreatmentFile = "patienttreatment.txt";
    private final String txtPaymentFile = "payment.txt";
    private final String txtInvoiceFile = "invoice.txt";
    private final String txtWardFile = "ward.txt";
    private final String txtWardPatientList = "wardpatient.txt";




    //declare Buffered Reader/writer
    private BufferedReader reader;
    private BufferedWriter writer;
    private Formatter output;


    // declare authentication variables
    private int hmsHRId = 0;
    private int hmsDoctorId = 0;
    private int hmsReceptionistId = 0;

    //class object initialization
    private hmsHR objHMSHr;
    private hmsDoctor objHMSDoctor;
    private hmsReceptionist objHMSReceptionist;
    private hmsPatient objHMSPatient;
    private hmsTreatment objHMSTreatment;
    private hmsVisitation objVisitation;
    private hmsPatientTreatment objPatientTreatment;
    private hmsPayment objPayment;
    private hmsInvoice objInvoice;
    private hmsWard objWard;
    private hmsWardPatient objWardPatient;


    private hmsStaff loggedUser = null;

    private boolean isLoggedIn = false;
    private boolean isHR = false;
    private boolean isDoctor = false;
    private boolean isReceptionist = false;

    private Scanner console;

    private static int loginFailed = 0;

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final String MONTH_FORMAT = "MM/yyyy";
    private SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT);
    private SimpleDateFormat datetimeformat = new SimpleDateFormat(DATETIME_FORMAT);
    private SimpleDateFormat monthformat = new SimpleDateFormat(MONTH_FORMAT);


    // default constructor
    public hmsController() {
        console = new Scanner(System.in);
        console.useDelimiter("\n");

    }


    // clear screen
    public void clearScreen() {
        System.out.println('\u000c');
    }

    // create pause for some seconds
    public void pause(long secs) {
        try {
            Thread.currentThread().sleep(secs*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // return all the doctor in the list


    // return all the hr in the list
    public ArrayList getHrList() {
        return arrHrList;
    }

    public ArrayList getDoctorList() {return arrDoctorList;}

    // return all the receptionist in the list
    public ArrayList getReceptionistList() { return arrReceptionistList; }

    // return all the patientlist in the list
    public ArrayList getPatientList() { return arrPatientList; }


    // return all the treatment in the list
    public ArrayList getTreamentList() {
        return arrTreatmentList;
    }

    //get payment in the list
    public ArrayList getPaymentList() {
        return arrPaymentList;
    }


    // return all the visited patient in the list
    public ArrayList getVisitedPatientList(){ return  arrVisitedPatientList;}

    //return all patient under treatment details
    public ArrayList getPatientTreatmentList(){return  arrPatientTreatmentList;}


    public ArrayList getInvoiceList(){return  arrInvoiceList;}
    public ArrayList getWardList(){return  arrWardList;}
    public ArrayList getWardPatientList(){return  arrWardPatientList;}

    public ArrayList getReports()
    {
        return arrReportsList;
    }

    public boolean loadWardFile()
    {
        try {
            arrWardList.clear();
            reader = new BufferedReader(new FileReader(new File(txtWardFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int wardID = Integer.parseInt(record[0].trim());
                String wardName = record[1].trim();


                hmsWard theWard = new hmsWard(wardID, wardName);
                arrWardList.add(theWard);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }

    }

    //Patient ward file
    public boolean loadWardPatientFile() {
        try {
            arrWardPatientList.clear();
            reader = new BufferedReader(new FileReader(new File(txtWardPatientList)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int admitID = Integer.parseInt(record[0].trim());
                int wardID = Integer.parseInt(record[1].trim());
                int patientID = Integer.parseInt(record[2].trim());
                String dateAdmitted = record[3].trim();
                String dateDischarged = record[4].trim();

                hmsWardPatient wp = new hmsWardPatient(admitID, wardID, patientID, dateAdmitted, dateDischarged);
                arrWardPatientList.add(wp);
                line = reader.readLine();
            }
            reader.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    //load payment file
    public boolean loadInvoiceFile() {
        try {
            arrInvoiceList.clear();
            reader = new BufferedReader(new FileReader(new File(txtInvoiceFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int invoiceID = Integer.parseInt(record[0].trim());
                int patientID = Integer.parseInt(record[1].trim());
                int treatmentID = Integer.parseInt(record[2].trim());
                double amountInvoice = Double.parseDouble(record[3].trim());
                Date dateInvoice = dateformat.parse(record[4].trim());

                hmsInvoice thePayment = new hmsInvoice(invoiceID, patientID, treatmentID, amountInvoice,dateInvoice);
                arrInvoiceList.add(thePayment);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.toString() + ": " + e.getMessage());
            return false;
        }
    }


    //load payment file
    public boolean loadpaymentFile() {
        try {
            arrPaymentList.clear();
            reader = new BufferedReader(new FileReader(new File(txtPaymentFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int paymentID = Integer.parseInt(record[0].trim());
                int patientID = Integer.parseInt(record[1].trim());
                double paymentAmount = Double.parseDouble(record[2].trim());
                Date paymentDateTime = datetimeformat.parse(record[3].trim());

                hmsPayment thePayment = new hmsPayment(paymentID, patientID, paymentAmount, paymentDateTime);
                arrPaymentList.add(thePayment);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.toString() + ": " + e.getMessage());
            return false;
        }
    }

    //load visited Patient List file
    public boolean loadVisitedPatientList()
    {
        try
        {
            arrVisitedPatientList.clear();
            reader = new BufferedReader(new FileReader(new File(txtVisitedPatientFile)));
            String line = reader.readLine();

            while (line  != null)
            {
                String[] record = line.split(",");

                int vID = Integer.parseInt(record[0].trim());
                String pName = record[1].trim();
                String vDate = record[2].trim();
                String vDoctor = record[3].trim();
                String vPurpose = record[4].trim();

                objVisitation = new hmsVisitation(vID,pName,vDate,vDoctor,vPurpose);
                arrVisitedPatientList.add(objVisitation);
                line = reader.readLine();
            }
            reader.close();
            return true;

        }catch (IOException e)
        {
            System.out.println(e.toString() + ":" +e.getMessage());
            return false;
        }
    }

    // load HR file
    public boolean loadHRFile() {
        try {
            arrHrList.clear();
            reader = new BufferedReader(new FileReader(new File(txtHrFile)));
            String line = reader.readLine();
            while(line != null) {
                String[] record = line.split(",");

                int staffId = Integer.parseInt(record[0].trim());
                String loginId = record[1].trim();
                String password = record[2].trim();
                String fullName = record[3].trim();
                String role = record[4].trim();

                objHMSHr = new hmsHR(staffId,loginId,password,fullName,role);
                arrHrList.add(objHMSHr);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    // load patient file
    public boolean loadPatientFile() {
        try {
            arrPatientList.clear();
            reader = new BufferedReader(new FileReader(new File(txtPatientFile)));
            String line = reader.readLine();
            while(line != null) {
                String[] record = line.split(",");

                int patientId = Integer.parseInt(record[0].trim());
                String fullName = record[1].trim();
                String PhoneNumber = record[2].trim();
                String PatientStatus = record[3].trim();

                objHMSPatient = new hmsPatient(patientId, fullName, PhoneNumber, PatientStatus);
                arrPatientList.add(objHMSPatient);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }



    // load patient treatment file
    public boolean loadPatientTreatmentListFile()
    {
        try {
            arrPatientTreatmentList.clear();

            reader = new BufferedReader(new FileReader(new File(txtPatientTreatmentFile)));
            String line = reader.readLine();
            while(line != null) {
                String[] record = line.split(",");

                int patientId = Integer.parseInt(record[0].trim());
                int patientName = Integer.parseInt(record[1].trim());
                int doctorName = Integer.parseInt(record[2].trim());
                int treatmentName = Integer.parseInt(record[3].trim());
                String treatmentStartDate = record[4].trim();
                String treatmentEndDate = record[5].trim();
                String treatmentStatus = record[6].trim();
                double treatmentCost = Double.parseDouble(record[7].trim());


                objPatientTreatment = new hmsPatientTreatment(patientId, patientName, doctorName, treatmentName,treatmentStartDate,treatmentEndDate,treatmentStatus,treatmentCost);
                arrPatientTreatmentList.add(objPatientTreatment);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }

    }


    public int checkLoadingStatus()
    {
        if(!loadHRFile()) {
            return -1;
        }
        if(!loadDoctorFile()) {
            return -2;
        }
        if(!loadReceptionistFile()) {
            return -3;
        }
        if(!loadPatientFile()) {
            return -4;
        }

        if(!loadPatientTreatmentListFile()) {
            return -5;
        }
        if(!loadTreatmentFile()) {
            return -6;
        }
        if (!loadVisitedPatientList()){
            return -7;
        }
        if (!loadpaymentFile())
        {
            return -8;
        }
        if (!loadInvoiceFile())
        {
            return -9;
        }
        if (!loadWardFile())
        {
            return -10;
        }
        if (!loadWardPatientFile())
        {
            return -11;
        }



        return 1;
    }



    // HR authentication
    public void authenticateHR() {
        //Scanner console = new Scanner(System.in);
        boolean authenticate = false;

        if (loginFailed < 3) {
            hmsHR hrm = null;
            boolean found = false;
            arrHrList = getHrList();
            Iterator it = arrHrList.iterator();

            String loginId = "";
            String password = "";
            System.out.println("Please enter HR ID: ");
            loginId = console.nextLine();
            System.out.println("Please enter HR Password: ");
            password = console.nextLine();

            while(it.hasNext() && !found) {
                hrm = (hmsHR) it.next();
                if(hrm.getLoginId().equals(loginId) && hrm.getPassword().equals(password)) {
                    authenticate = true;
                    loggedUser = hrm;
                    hmsHRId = hrm.getStaffId();
                    found = true;
                }
            }

            if(authenticate == true) {
                System.out.println("Login successful! Please make a selection.");

                isLoggedIn = true;
                isHR = true;
                pause(1);
                clearScreen();
                showMenu();
            } else {
                authenticate = false;
                loginFailed = loginFailed + 1;

                System.out.println("Login failed. Please try again!!!");

                pause(1);
                clearScreen();
                authenticateHR();
            }
        } else {
            System.out.println("You have violated the login rule: \"3 failed login attempted!!!\"");
            pause(1);
            System.out.println("System will shutdown now!!!");
            System.exit(1);
        }

    }


    // doctor authentication
    public void authenticateDoctor()
    {
        boolean authenticate = false;

        if (loginFailed < 3) {
            hmsDoctor d = null;
            boolean found = false;
            arrDoctorList = getDoctorList();
            Iterator it = arrDoctorList.iterator();

            String loginId = "";
            String password = "";
            System.out.println("Please enter Doctor ID: ");
            loginId = console.nextLine();
            System.out.println("Please enter Doctor Password: ");
            password = console.nextLine();

            while (it.hasNext() && !found) {
                d = (hmsDoctor) it.next();
                if (d.getLoginId().equals(loginId) && d.getPassword().equals(password)) {
                    authenticate = true;
                    loggedUser = d;
                    hmsDoctorId = d.getStaffId();
                    found = true;
                }
            }


            if (authenticate == true) {
                System.out.println("Login successful! Please make a selection.");

                isLoggedIn = true;
                isDoctor = true;
                pause(1);
                clearScreen();
                showMenu();

            } else {
                authenticate = false;
                loginFailed = loginFailed + 1;

                System.out.println("Login failed. Please try again!!!");

                pause(1);
                clearScreen();
                authenticateDoctor();
            }
        } else {
            System.out.println("You have violated the login rule: \"3 failed login attempted!!!\"");
            pause(1);
            System.out.println("System will shutdown now!!!");
            System.exit(1);
        }

    }

    // receptionist authentication
    public void authenticateReceptionist() {
        //Scanner console = new Scanner(System.in);
        boolean authenticate = false;

        if (loginFailed < 3) {
            hmsReceptionist d = null;
            boolean found = false;
            arrReceptionistList = getReceptionistList();
            Iterator it = arrReceptionistList.iterator();

            String loginId = "";
            String password = "";
            System.out.println("Please enter Receptionist ID: ");
            loginId = console.nextLine();
            System.out.println("Please enter Receptionist Password: ");
            password = console.nextLine();

            while (it.hasNext() && !found) {
                d = (hmsReceptionist) it.next();
                if (d.getLoginId().equals(loginId) && d.getPassword().equals(password)) {
                    authenticate = true;
                    loggedUser = d;
                    hmsReceptionistId = d.getStaffId();
                    found = true;
                }
            }


            if (authenticate == true) {
                System.out.println("Login successful! Please make a selection.");

                isLoggedIn = true;
                isReceptionist = true;
                pause(1);
                clearScreen();
                showMenu();

            } else {
                authenticate = false;
                loginFailed = loginFailed + 1;

                System.out.println("Login failed. Please try again!!!");

                pause(1);
                clearScreen();
                authenticateReceptionist();
            }
        } else {
            System.out.println("You have violated the login rule: \"3 failed login attempted!!!\"");
            pause(1);
            System.out.println("System will shutdown now!!!");
            System.exit(1);
        }

    }


    // logout
    public void logout()
    {
        hmsHRId = 0;
        hmsDoctorId = 0;
        hmsReceptionistId = 0;

        isLoggedIn = false;

        isHR = false;
        isDoctor = false;
        isReceptionist = false;

        clearScreen();
        showChooseMenu();
    }


    // check anthenticate
    public void checkIfAuthenticate() {
        if (isLoggedIn == false) {
            System.out.println("Please login first!!!");
            pause(2);
            showChooseMenu();
        }
    }

        //check if the user is HR,doctor or Receptionist
    public void checkUser()
    {
        if (isHR == false)
        {
            showMenu();
        }
        else if (isDoctor == false)
        {
        }
        else if (isReceptionist == false)
        {
        }
    }




    public void showChooseMenu() {

        console = new Scanner(System.in);
        System.out.println("**************************************************************");
        System.out.println("*                        HMS System                         *");
        System.out.println("**************************************************************");
        System.out.println("");

        System.out.println("Please choose from --(H)R | (D)octor | (R)eceptionist | (Q)uit :");
        String choice = console.nextLine();

        switch (choice.toLowerCase()) {
            case "h" :
                clearScreen();
                authenticateHR();
                break;
            case "d" :
                clearScreen();
                authenticateDoctor();
                break;
            case "r" :
                clearScreen();
                authenticateReceptionist();
                break;
            case "q" :
                System.out.println("");
                System.out.println("                 ======Program Ended=======               ");
                System.exit(1);
                break;
            default :
                System.out.println("Your choice is not applicable. Please try again!");
                pause(2);
                clearScreen();
                showChooseMenu();
        }
    }

    // show manager menu
    public void showMenu()
    {
        //Scanner console = new Scanner(System.in);
        String selection = "";

        System.out.println("*****************************************************************");
        System.out.println("*                       [HMS System]                           *");
        System.out.println("*                         Main Menu                             *");
        System.out.println("*****************************************************************");
        System.out.println("");


        if (isHR)
        {
            System.out.println("What do you want to do?");
            System.out.println("[1] Add new doctor");
            System.out.println("[2] Update doctor details");
            System.out.println("[3] Search doctor records");
            System.out.println("[4] Delete doctor records");
            System.out.println("[5] View doctor list");
            System.out.println("[6] Add receptionist details");
            System.out.println("[7] Update receptionist details");
            System.out.println("[8] Search receptionist details");
            System.out.println("[9] Delete receptionist details");
            System.out.println("[10] View receptionist list");

        }

        if (isDoctor)
        {

            System.out.println("What do you want to do?");
            System.out.println("[11] Add new patient");
            System.out.println("[12] Update patient details");
            System.out.println("[13] Search patient records");
            System.out.println("[14] Delete patient records");
            System.out.println("[15] View patient list");
            System.out.println("[16] Add new treatment");
            System.out.println("[17] Update treatment details");
            System.out.println("[18] Search treatment details");
            System.out.println("[19] Delete treatment details");
            System.out.println("[20] View treatment list");
            System.out.println("[21] Add new visitation");
            System.out.println("[22] Update visitation");
            System.out.println("[23] Delete visitation");
            System.out.println("[24] Search visitation");
            System.out.println("[25] Add patient treatment");
            System.out.println("[26] Update patient treatment");
            System.out.println("[27] Delete patient treatment");
            System.out.println("[28] Search patient treatment");
            System.out.println("[29] Make a Payment");
            System.out.println("[30] Make an Invoice");
            System.out.println("[31] Add patient ward");
            System.out.println("[32] View patient ward");
            System.out.println("[33] View Reports");


        }


        if(isReceptionist)
        {
            System.out.println("What do you want to do?");
            System.out.println("[011] Add new patient");
            System.out.println("[012] Update patient details");
            System.out.println("[013] Search patient records");
            System.out.println("[014] Delete patient records");
            System.out.println("[015] View patient list");
            System.out.println("[016] Add new treatment");
            System.out.println("[017] Update treatment details");
            System.out.println("[018] Search treatment details");
            System.out.println("[019] Delete treatment details");
            System.out.println("[020] View treatment list");
            System.out.println("[021] Add new visitation");
            System.out.println("[022] Update visitation");
            System.out.println("[023] Delete visitation");
            System.out.println("[024] Search visitation");
            System.out.println("[025] Add patient treatment");
            System.out.println("[026] Update patient treatment");
            System.out.println("[027] Delete patient treatment");
            System.out.println("[028] Search patient treatment");
            System.out.println("[029] Make a Payment");
            System.out.println("[030] Make an Invoice");
            System.out.println("[031] Add patient ward");
            System.out.println("[032] View patient ward");
            System.out.println("[033] View Reports");

        }


        System.out.println("[m] Return to Main Menu");
        System.out.println("[o] Log out");
        System.out.println("[q] Quit the program");
        System.out.println("");
        System.out.println("Please enter the menu code: >");


        try {
            selection = console.nextLine();

        } catch (Exception e)

        {
            System.out.println("Please enter the number listed on the menu.");
            System.out.println(e.toString() + ": " + e.getMessage());
            pause(2);
            clearScreen();
            showMenu();
        }

        switch (selection) {


            case "1" :
                addNewDoctor();
                clearScreen();
                showMenu();
                break;
            case "2" :
                try {
                    updateDoctor();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "3" :

                searchDoctor();
                clearScreen();
                showMenu();
                break;

            case "4" :

                try {
                    deleteDoctor();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;

            case "5" :
                viewDoctorlist();
                clearScreen();
                showMenu();
                break;

            case "6" :
                addNewReceptionist();
                clearScreen();
                showMenu();
                break;
            case "7" :
                try {
                    updateReceptionist();
                } catch (Exception e) {
                    System.out.println(e.toString() + ": " + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;

            case "8" :
                checkUser();
                searchReceptionist();
                clearScreen();
                showMenu();
                break;
            case "9" :
                checkUser();
                try {
                    deleteReceptionist();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;
            case "10" :
                viewReceptionisList();
                clearScreen();
                showMenu();
                break;

            case "11" :
            case "011" :
                addNewPatient();
                clearScreen();
                showMenu();
                break;

            case "12" :
            case "012" :
                try {
                    updatePatientDetail();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;

            case "13" :
            case "013" :
                searchPatient();
                clearScreen();
                showMenu();
                break;

            case "14" :
            case "014" :
                try {
                    deletePatient();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;

            case "15" :
            case "015" :
                viewPatientList();
                clearScreen();
                showMenu();
                break;
            case "16" :
            case "016" :
                addNewTreatment();
                clearScreen();
                showMenu();
                break;

            case "17" :
            case "017" :
                try {
                    updateTreatmentDetail();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }

                clearScreen();
                showMenu();
                break;
            case "18" :
            case "018" :
                //search treatment details
                searchTreatment();
                clearScreen();
                showMenu();
                break;

            case "19" :
            case "019" :
                //delete treatment details
                try {
                    deleteTreatment();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }

                clearScreen();
                showMenu();
                break;

            case "20":
            case "020":
                //view treatment list
                showTreatmentList();
                clearScreen();
                showMenu();
                break;



            case "21":
            case "021":
                //add visited patient details
                try {
                    addNewVisitation();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }

                clearScreen();
                showMenu();
                break;

            case "22":
            case "022":
                //update visited patient details
                try {
                    updateVisitedPatient();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }

                clearScreen();
                showMenu();
                break;

            case "23":
            case "023":
                //delete visited patient details
                try {
                    deleteVisitedPatient();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;

            case "24" :
            case "024" :
                //search single patient details
                searchVisitedPatient();
                clearScreen();
                showMenu();
                break;

            case "25" :
            case "025" :
                try {
                    addPatientTreatment();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;


            case "26" :
            case "026" :
                try {
              //      updatePatientTreatment();
                } catch (Exception e) {
               //     System.out.println(e.toString() + ":" + e.getMessage());
                }
             //   clearScreen();
               // showMenu();
                break;

            case "27" :
            case "027" :
                try {
                    deletePatientTreatment();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;

            case "28" :
            case "028" :

                searchPatientTreatment();
                clearScreen();
                showMenu();
                break;

            case "29" :
            case "029" :

                try {
                    makePayment();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;

            case "30" :
            case "030" :

                try {
                    makeInvoice();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;

            case "31" :
            case "031" :

                try {
                    addPatientToWard();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;

            case "32" :
            case "032" :

                try {
                    viewPatientByWard();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;

            case "33" :
            case "033" :

                try {
                    viewReports();
                } catch (Exception e) {
                    System.out.println(e.toString() + ":" + e.getMessage());
                }
                clearScreen();
                showMenu();
                break;




            case "m" :
                pause(2);
                clearScreen();
                showChooseMenu();
                break;
            case "o" :
                logout();
                break;
            case "q" :
                quitProgram();
                break;

            default :
                clearScreen();
                break;
        }

    }

    // add new doctor
    public void addNewDoctor() {
        clearScreen();
        Scanner console = new Scanner(System.in);
        Iterator it = arrDoctorList.iterator();
        boolean loop = false;

        int tempID = 0;
        while (it.hasNext()) {
            hmsDoctor d = (hmsDoctor) it.next();
            tempID = d.getStaffId();
        }
        tempID++;
        int doctorID = tempID;

        System.out.println("Please enter doctor's login ID: ");
        String loginID = getLoginId();

        String password = "";
        String comparePassword = "";
        boolean isMatched = false;
        do {
            System.out.println("Please enter doctor's password: ");
            password = getPassword();
            System.out.println("Please confirm doctor's password: ");
            comparePassword = getPassword();

            if (password.equals(comparePassword)) {
                isMatched = true;
            } else {
                System.out.println("The password does not match. Please try again!");
            }

        } while (!isMatched);

        System.out.println("Please enter doctor's fullname: ");
        String fullName = console.nextLine();
        String role = "Doctor";


        try {
            output = new Formatter(new FileWriter(txtDoctorFile, true));
            output.format("%d,%s,%s,%s,%s\r\n", doctorID, loginID, password, fullName, role);
            output.close();
            System.out.println("Doctor added successfully!");
        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        arrDoctorList.clear();
        loadDoctorFile();
    }

    // add new receptionist
    public void addNewReceptionist()
    {
        clearScreen();
        Scanner console = new Scanner(System.in);
        Iterator it = arrReceptionistList.iterator();
        boolean loop = false;

        int tempID = 0;
        while (it.hasNext()) {
            hmsReceptionist d = (hmsReceptionist) it.next();
            tempID = d.getStaffId();
        }
        tempID++;
        int receptionistID = tempID;

        System.out.println("Please enter receptionist login ID: ");
        String loginID = getLoginId();

        String password = "";
        String comparePassword = "";
        boolean isMatched = false;
        do {
            System.out.println("Please enter receptionist password: ");
            password = getPassword();
            System.out.println("Please confirm receptionist password: ");
            comparePassword = getPassword();

            if (password.equals(comparePassword)) {
                isMatched = true;
            } else {
                System.out.println("The password does not match. Please try again!");
            }

        } while (!isMatched);

        System.out.println("Please enter receptionist fullname: ");
        String fullName = console.nextLine();
        String role = "Receptionist";

        try {
            output = new Formatter(new FileWriter(txtReceptionistFile, true));
            output.format("%d,%s,%s,%s,%s\r\n", receptionistID, loginID, password, fullName, role);
            output.close();
            System.out.println("Receptionist added successfully!");
        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        arrReceptionistList.clear();
        loadReceptionistFile();
    }

    // load receptionist file
    public boolean loadReceptionistFile() {
        try {
            arrReceptionistList.clear();
            reader = new BufferedReader(new FileReader(new File(txtReceptionistFile)));
            String line = reader.readLine();
            while(line != null) {
                String[] record = line.split(",");

                int receptionistId = Integer.parseInt(record[0].trim());
                String loginId = record[1].trim();
                String password = record[2].trim();
                String fullName = record[3].trim();
                String role = record[4].trim();

                objHMSReceptionist = new hmsReceptionist(receptionistId, loginId, password, fullName,role);
                arrReceptionistList.add(objHMSReceptionist);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    public int inputReceptionistIDFromConsole() throws Exception {
        int receptionistID = Integer.parseInt(console.nextLine());

        Iterator<hmsReceptionist> it = arrReceptionistList.iterator();
        boolean found = false;

        while (it.hasNext()) {
            hmsReceptionist d = (hmsReceptionist) it.next();
            if (receptionistID == d.getStaffId()) {
                found = true;
                break;
            }
        }
        if (found) return receptionistID;
        else {
            throw new Exception("Cannot find the Doctor with doctorId '" + receptionistID + "'");
        }
    }

    // get receptionist by receptionistID
    private hmsReceptionist getReceptionist(int receptionistId) {
        hmsReceptionist selected = null;

        Iterator<hmsReceptionist> it = arrReceptionistList.iterator();

        while (it.hasNext()) {
            hmsReceptionist d = (hmsReceptionist) it.next();
            if (d.getStaffId() == receptionistId) {
                selected = d;
                break;
            }
        }

        return selected;
    }


    // update receptionist
    public void updateReceptionist() throws Exception {
        try {

            boolean done = false;

            System.out.print("Please enter the Receptionist ID that you with to update : ");
            int dID = inputReceptionistIDFromConsole();
            hmsReceptionist d = getReceptionist(dID);

            System.out.println("------------------------------------------------------------");

            String loginId ="";
            String password = "";
            String comparePassword = "";
            boolean isValid = false;
            hmsPasswordValidator passwordValidator = new hmsPasswordValidator();

            do {

                System.out.print("@ Receptionist's login Id:");
                loginId = console.nextLine();

                System.out.print("@ Receptionist's password (leave blank if not changed): ");
                password = console.nextLine();
                if (password.length() > 0) {
                    if (passwordValidator.validate(password)) {
                        System.out.println("@ Confirm password: ");
                        comparePassword = getPassword();
                        if (password.equals(comparePassword)) {
                            isValid = true;
                        } else {
                            System.out.println("The password does not match. Please try again!");
                        }
                    } else {
                        System.out.println("Invalid password! Password length must be between 6 and 20 at least 1 digit, 1 capital letter and 1 special character (@#$%)");
                    }
                } else {
                    isValid = true;
                }

            } while (!isValid);

            System.out.print("@ Receptionist's fullname : [" + d.getFullName() + "] ");
            String fullName = console.nextLine();
            String role = "Receptionist";


            if (password.length() <= 0 || "".equals(password)) password = d.getPassword();
            if (fullName.length() <= 0 || "".equals(fullName)) fullName = d.getFullName();
            if (loginId.length() <= 0 || "".equals(loginId)) loginId = d.getLoginId();

            hmsReceptionist uReceptionist = new hmsReceptionist(d.getStaffId(), loginId, password, fullName, role);

            done = updateReceptionist(uReceptionist);

            if (done) {
                System.out.println("Receptionist Detail updated successfully!!!");

                loadReceptionistFile();
            } else {
                System.out.println("Receptionist error occured!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean updateReceptionist(hmsReceptionist receptionist) throws Exception
    {
        output = new Formatter(new FileWriter("receptionistChanged.txt"));

        Iterator<hmsReceptionist> it = arrReceptionistList.iterator();
        while (it.hasNext()) {
            hmsReceptionist d = (hmsReceptionist) it.next();
            if (d.getStaffId() == receptionist.getStaffId()) {
                output.format("%d,%s,%s,%s,%s\r\n", receptionist.getStaffId(), receptionist.getLoginId(), receptionist.getPassword(), receptionist.getFullName(), receptionist.getRole());
            } else {
                output.format("%d,%s,%s,%s,%s\r\n", d.getStaffId(), d.getLoginId(), d.getPassword(), d.getFullName(), d.getRole());
            }
        }
        output.close();

        // delete old file
        File fileToDelete = new File(txtReceptionistFile);
        boolean delete = fileToDelete.delete();

        // renames receptionistChanged.txt to receptionist.txt
        File fileToRename = new File("receptionistChanged.txt");
        File renamedFile = new File(txtReceptionistFile);

        boolean rename = fileToRename.renameTo(renamedFile);

        return rename;
    }


    // search receptionist by id
    public void searchReceptionist()
    {
        clearScreen();

        // delete a record from the text file based on the patientId
        System.out.println("Please enter the receptionist's ID number to search the record: ");
        int recID = console.nextInt();

        //delte this code start
        Iterator it = getReceptionistList().iterator();
        while (it.hasNext()) {
            hmsReceptionist p = (hmsReceptionist) it.next();
            if ((p.getStaffId() == (recID))) {

                System.out.println(String.format("%-30s: %s", "@ Receptionist Id", p.getStaffId()));
                System.out.println(String.format("%-30s: %s", "@ Receptionist Full Name", p.getFullName()));
                System.out.println(String.format("%-30s: %s", "@ Receptionist Role", p.getRole()));


                System.out.println("-----------------------------------------------------------------------");
            }
        }
        System.out.println("");
    }

    // delete receptionist
    public void deleteReceptionist() throws Exception {
        clearScreen();
        //Scanner console = new Scanner(System.in);

        boolean done = false;
        output = new Formatter(new FileWriter("receptionistChanged.txt", true));

        System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", "ID", "login", "Password", "FullName", "Role"));

        System.out.println("--------------------------------------------------------------------");

        Iterator it1 = getReceptionistList().iterator();
        while (it1.hasNext()) {
            hmsReceptionist p = (hmsReceptionist) it1.next();
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", p.getStaffId(), p.getLoginId(), p.getPassword(),p.getFullName(),p.getRole()));
        }
        System.out.println();

        // delete a record from the text file based on the patientId
        System.out.println("Please enter the receptionist's ID number to delete the record: ");
        int recID = console.nextInt();

        Iterator it = getReceptionistList().iterator();
        while (it.hasNext()) {
            hmsReceptionist p = (hmsReceptionist) it.next();
            if (!(p.getStaffId() == (recID))) {
                output.format("%d,%s,%s,%s,%s\r\n", p.getStaffId(), p.getLoginId(), p.getPassword(),p.getFullName(),p.getRole());

            } else {
                done = true;
            }
        }

        output.close();
        if (done == true)
        {
            //clearScreen();
            System.out.println("Receptionist deleted successfully!");

            // delete old file
            File fileToDelete = new File("receptionist.txt");
            boolean delete = fileToDelete.delete();

            // rename patientChanged.txt to patient.txt
            File fileToRename = new File("receptionistChanged.txt");
            File renamedFile = new File("receptionist.txt");
            boolean rename = fileToRename.renameTo(renamedFile);

            arrReceptionistList.clear();
            loadReceptionistFile();
        }
        else
            {
            System.out.println("Receptionist not found!!!");
        }

    }


    // view receptionist list
    public void viewReceptionisList() {
        clearScreen();
        int count = 0;
        Iterator it = getReceptionistList().iterator();

        System.out.println("----------------------------------------------------------------------");

        while (it.hasNext()) {

            count++;
            hmsReceptionist d = (hmsReceptionist) it.next();
            System.out.println(String.format("%-30s: %s", "@ Staff Id", d.getStaffId()));
            System.out.println(String.format("%-30s: %s", "@ login Id", d.getLoginId()));
            System.out.println(String.format("%-30s: %s", "@ Password", d.getPassword()));
            System.out.println(String.format("%-30s: %s", "@ Full Name", d.getFullName()));
            System.out.println(String.format("%-30s: %s", "@ Role", d.getRole()));

            System.out.println("-----------------------------------------------------------------------");

        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s", "@ Total number of Receptionist", count));
        System.out.println("");
    }




    // load doctor file
    public boolean loadDoctorFile() {
        try {
            arrDoctorList.clear();
            reader = new BufferedReader(new FileReader(new File(txtDoctorFile)));
            String line = reader.readLine();
            while(line != null) {
                String[] record = line.split(",");

                int doctorId = Integer.parseInt(record[0].trim());
                String loginId = record[1].trim();
                String password = record[2].trim();
                String fullName = record[3].trim();
                String role = record[4].trim();

                objHMSDoctor = new hmsDoctor(doctorId, loginId, password, fullName,role);
                arrDoctorList.add(objHMSDoctor);
                line = reader.readLine();
            }
            reader.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }

    //get doctor id from console
    public int inputDoctorIDFromConsole() throws Exception {
        int doctorID = Integer.parseInt(console.nextLine());

        Iterator<hmsDoctor> it = arrDoctorList.iterator();
        boolean found = false;

        while (it.hasNext()) {
            hmsDoctor d = (hmsDoctor) it.next();
            if (doctorID == d.getStaffId()) {

                d.getFullName();
                found = true;
                break;
            }
        }
        if (found) {

            return doctorID;
        }
        else {
            throw new Exception("Cannot find the Doctor with doctorId '" + doctorID + "'");
        }
    }



    // get Doctor by Doctor from arr
    private hmsDoctor getDoctor(int doctorID) {
        hmsDoctor selected = null;

        Iterator<hmsDoctor> it = arrDoctorList.iterator();

        while (it.hasNext()) {
            hmsDoctor d = (hmsDoctor) it.next();
            if (d.getStaffId() == doctorID) {
                selected = d;
                break;
            }
        }

        return selected;
    }


    // update Doctor by entering doctor id
    public void updateDoctor() throws Exception {
        try {

            boolean done = false;

            System.out.print("Please enter the Doctor ID that you with to update : ");
            int dID = inputDoctorIDFromConsole();
            hmsDoctor d = getDoctor(dID);

            System.out.print("@ Doctor's fullname : [" + d.getFullName() + "] ");
            String fullName = console.nextLine();

            System.out.println("------------------------------------------------------------");

            String password = "";
            String comparePassword = "";
            boolean isValid = false;
            hmsPasswordValidator passwordValidator = new hmsPasswordValidator();

            do {
                System.out.print("@ Doctor's password (leave blank if not changed): ");
                password = console.nextLine();
                if (password.length() > 0) {
                    if (passwordValidator.validate(password)) {
                        System.out.println("@ Confirm password: ");
                        comparePassword = getPassword();
                        if (password.equals(comparePassword)) {
                            isValid = true;
                        } else {
                            System.out.println("The password does not match. Please try again!");
                        }
                    } else {
                        System.out.println("Invalid password! Password length must be between 6 and 20 at least 1 digit, 1 capital letter and 1 special character (@#$%)");
                    }
                } else {
                    isValid = true;
                }

            } while (!isValid);


            String role = "Doctor";


            if (password.length() <= 0 || "".equals(password)) password = d.getPassword();
            if (fullName.length() <= 0 || "".equals(fullName)) fullName = d.getFullName();


            hmsDoctor uDoctor = new hmsDoctor(d.getStaffId(), d.getLoginId(), password, fullName, role);

            done = updateDoctor(uDoctor);

            if (done) {
                System.out.println("Doctor Detail updated successfully!!!");

                loadDoctorFile();
            } else {
                System.out.println("System error occured!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //update doctor record
    public boolean updateDoctor(hmsDoctor doctor) throws Exception
    {
        output = new Formatter(new FileWriter("doctorChanged.txt"));

        Iterator<hmsDoctor> it = arrDoctorList.iterator();
        while (it.hasNext()) {
            hmsDoctor d = (hmsDoctor) it.next();
            if (d.getStaffId() == doctor.getStaffId()) {
                output.format("%d,%s,%s,%s,%s\r\n", doctor.getStaffId(), doctor.getLoginId(), doctor.getPassword(), doctor.getFullName(), doctor.getRole());
            } else {
                output.format("%d,%s,%s,%s,%s\r\n", d.getStaffId(), d.getLoginId(), d.getPassword(), d.getFullName(), d.getRole());
            }
        }
        output.close();

        // delete old file
        File fileToDelete = new File(txtDoctorFile);
        boolean delete = fileToDelete.delete();

        // renames doctorChanged.txt to doctor.txt
        File fileToRename = new File("doctorChanged.txt");
        File renamedFile = new File(txtDoctorFile);

        boolean rename = fileToRename.renameTo(renamedFile);

        return rename;

    }

    //search doctor by id
    public void searchDoctor() {
        clearScreen();

        System.out.print("Please enter doctor id to search the record: ");
        int docId = console.nextInt();

        Iterator it = arrDoctorList.iterator();
        int count = 0;

        System.out.println("-------------------------------------------------------------------------");

        while(it.hasNext()) {

            hmsDoctor c = (hmsDoctor) it.next();

            if((c.getStaffId() == (docId)))
            {
                count++;
                System.out.println(String.format("%-30s: %d","@ doctor Id",c.getStaffId()));
                System.out.println(String.format("%-30s: %s","@ Doctor First Name",c.getFullName()));
                System.out.println(String.format("%-30s: %s","@ Doctor Role",c.getRole()));
                System.out.println("-----------------------------------------------------------------------");
            }
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s","@ Total number of Doctor",count));
        System.out.println("");

    }



    //show patient list
    public void viewPatientList()
    {
        clearScreen();
        int count = 0;
        Iterator it = getPatientList().iterator();

        System.out.println("----------------------------------------------------------------------");

        while(it.hasNext()) {
            count++;
            hmsPatient c = (hmsPatient) it.next();
            System.out.println(String.format("%-30s: %s","@ Patient Id",c.getPatientID()));
            System.out.println(String.format("%-30s: %s","@ First Name",c.getFullName()));
            System.out.println(String.format("%-30s: %s","@ Phone Number",c.getPhoneNumber()));
            System.out.println(String.format("%-30s: %s","@ Patient Status",c.getPatientStatus()));

            System.out.println("-----------------------------------------------------------------------");

        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s","@ Total number of Patients",count));
        System.out.println("");
    }

    // add new patient
    public void addNewPatient() {
        clearScreen();
        //Scanner console = new Scanner(System.in);
        Iterator it = arrPatientList.iterator();
        boolean loop = false;

        int tempID = 0;
        while(it.hasNext()) {
            hmsPatient c = (hmsPatient) it.next();
            tempID = c.getPatientID();
        }
        tempID++;
        int patientId = tempID;

        System.out.println("Please enter patient name: ");
        String patientName = console.nextLine();
        System.out.println("Please enter patient phone number: ");
        String patientPhone = console.nextLine();

       // System.out.println("Please enter patient status(New/Existing): ");
        String patientStatus = "New";


        try {
            output = new Formatter(new FileWriter(txtPatientFile,true));
            output.format("%d,%s,%s,%s\r\n", patientId,patientName,patientPhone,patientStatus);
            output.close();
            System.out.println("Patient added successfully!");

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        arrPatientList.clear();
        loadPatientFile();
    }


    // delete doctor record
    public void deleteDoctor() throws Exception {
        clearScreen();
        //Scanner console = new Scanner(System.in);

        boolean done = false;
        output = new Formatter(new FileWriter("doctorChanged.txt", true));

        System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", "ID", "login", "Password", "FullName", "Role"));


        System.out.println("--------------------------------------------------------------------");

        Iterator it1 = getDoctorList().iterator();


        System.out.println(arrDoctorList);

        while (it1.hasNext()) {


            hmsDoctor d = (hmsDoctor) it1.next();
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", d.getStaffId(), d.getLoginId(), d.getPassword(),d.getFullName(),d.getRole()));
            System.out.println();}



        // delete a record from the text file based on the patientId
        System.out.println("Please enter the doctor's ID number to delete the doctor: ");
        int DoctorId = Integer.parseInt(console.nextLine());

        Iterator it = getDoctorList().iterator();
        while (it.hasNext()) {
            hmsDoctor d = (hmsDoctor) it.next();
            if (!(d.getStaffId() == (DoctorId))) {
                output.format("%d,%s,%s,%s,%s\r\n", d.getStaffId(), d.getLoginId(), d.getPassword(), d.getFullName(), d.getRole());
            } else {
                done = true;
            }
        }

        output.close();
        if (done == true) {
            //clearScreen();
            System.out.println("Doctor deleted successfully!");


            // delete old file
            File fileToDelete = new File("doctor.txt");
            boolean delete = fileToDelete.delete();

            // rename doctorChanged.txt to doctor.txt
            File fileToRename = new File("doctorChanged.txt");
            File renamedFile = new File("doctor.txt");
            boolean rename = fileToRename.renameTo(renamedFile);

            arrDoctorList.clear();
            loadDoctorFile();
        } else {
            System.out.println("Doctor not found!!!");
        }

    }

    // show doctor list
    public void viewDoctorlist() {
        clearScreen();
        int count = 0;
        Iterator it = getDoctorList().iterator();

        System.out.println("----------------------------------------------------------------------");

        while (it.hasNext()) {

            count++;
            hmsDoctor d = (hmsDoctor) it.next();
            System.out.println(String.format("%-30s: %s", "@ Doctor Id", d.getStaffId()));
            System.out.println(String.format("%-30s: %s", "@ Full Name", d.getFullName()));
            System.out.println(String.format("%-30s: %s", "@ Role", d.getRole()));

            System.out.println("-----------------------------------------------------------------------");

        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s", "@ Total number of Doctor", count));
        System.out.println("");
    }



    // delete patient
    public void deletePatient() throws Exception {
        clearScreen();
        //Scanner console = new Scanner(System.in);

        boolean done = false;
        output = new Formatter(new FileWriter("patientChanged.txt",true));

        System.out.println(String.format("%-20s%-20s%-20s","Patient ID","Patient Name", "Phone Number"));

        System.out.println("--------------------------------------------------------------------");

        Iterator it1 = getPatientList().iterator();
        while(it1.hasNext()) {
            hmsPatient c = (hmsPatient) it1.next();
            System.out.println(String.format("%-20s%-20s%-20s",c.getPatientID(),c.getFullName(),c.getPhoneNumber()));
        }
        System.out.println();

        // delete a record from the text file based on the patientId
        System.out.println("Please enter the patient ID number to delete the patient record: ");
        int patientId = console.nextInt();

        Iterator it = getPatientList().iterator();
        while(it.hasNext()) {
            hmsPatient c = (hmsPatient) it.next();
            if(!(c.getPatientID() == (patientId))) {
                output.format("%d,%s,%s,%s\r\n",c.getPatientID(),c.getFullName(),c.getPhoneNumber(),c.getPatientStatus());

            } else {
                done = true;
            }
        }

        output.close();
        if(done == true) {
            //clearScreen();
            System.out.println("Patient deleted successfully!");


            // delete old file
            File fileToDelete = new File("patient.txt");
            boolean delete = fileToDelete.delete();

            // rename patientChanged.txt to patient.txt
            File fileToRename = new File("patientChanged.txt");
            File renamedFile = new File("patient.txt");
            boolean rename = fileToRename.renameTo(renamedFile);

            arrPatientList.clear();
            loadPatientFile();
        } else {
            System.out.println("Patient not found!!!");
        }

    }

    //input patient id from console
    public int inputPatientIDFromConsole() throws Exception {
        int patientId = Integer.parseInt(console.nextLine());

        Iterator<hmsPatient> it = getPatientList().iterator();
        boolean found = false;

        while(it.hasNext()) {
            hmsPatient c = (hmsPatient) it.next();
            if(patientId == c.getPatientID()) {
                found = true;
                break;
            }
        }
        if(found) return patientId;
        else {
            throw new Exception("Cannot find the Patient with patientId '"+patientId+"'");
        }
    }

    //patient status
    public String getPatientStatus(String oStatus) {
        //Scanner console = new Scanner(System.in);

        boolean loop = true;

        System.out.print("@ Patient Status : ["+oStatus+"] ");
        String ans = console.nextLine();

        if(ans.length() <= 0 || ans == "") ans = oStatus;

        while(loop) {
            switch (ans) {
                case "New" :
                case "Existing" :
                    loop = false;
                    break;
                default :
                    System.out.print("New/Existing?");
                    ans = console.nextLine();
            }
        }
        return ans;
    }

    // get Patient by patientId
    private hmsPatient getPatient(int patientId) {
        hmsPatient selected = null;

        Iterator<hmsPatient> it = arrPatientList.iterator();

        while(it.hasNext()) {
            hmsPatient c = (hmsPatient) it.next();
            if(c.getPatientID() == patientId) {
                selected = c;
                break;
            }
        }

        return selected;
    }


    //update patient record by entering id
    public boolean updatePatient(hmsPatient patient) throws Exception {
        output = new Formatter(new FileWriter("patientChanged.txt"));

        Iterator<hmsPatient> it = arrPatientList.iterator();
        while(it.hasNext()) {
            hmsPatient c = (hmsPatient) it.next();
            if(c.getPatientID() == patient.getPatientID()) {
                output.format("%d,%s,%s,%s\r\n",patient.getPatientID(),patient.getFullName(),patient.getPhoneNumber(),patient.getPatientStatus());
            } else {
                output.format("%d,%s,%s,%s\r\n",c.getPatientID(),c.getFullName(),c.getPhoneNumber(),c.getPatientStatus());
            }
        }
        output.close();

        // delete old file
        File fileToDelete = new File(txtPatientFile);
        boolean delete = fileToDelete.delete();

        // renames patientChanged.txt to patient.txt
        File fileToRename = new File("patientChanged.txt");
        File renamedFile = new File(txtPatientFile);

        boolean rename = fileToRename.renameTo(renamedFile);

        return rename;

    }

    // update patient detail
    public void updatePatientDetail() throws Exception {
        //Scanner console = new Scanner(System.in);
        try {
            boolean done = false;

            System.out.println("Please enter the Patient ID that you wish to update : ");
            int patientId = inputPatientIDFromConsole();
//        console.nextLine();
            hmsPatient c = getPatient(patientId);
            System.out.println("------------------------------------------------------------");
            System.out.print("@ Patient First Name : [" + c.getFullName() + "] ");
            String firstName = console.nextLine();
            System.out.print("@ Patient Phone Number : [" + c.getPhoneNumber() + "] ");
            String phoneNumber = console.nextLine();

            String patientStatus = getPatientStatus(c.getPatientStatus());

            System.out.println("Patient Status : " + patientStatus);

            if (firstName.length() <= 0 || firstName == "") firstName = c.getFullName();
            if (phoneNumber.length() <= 0 || phoneNumber == "") phoneNumber = c.getPhoneNumber();
            if (patientStatus.length() <= 0 || patientStatus == "") patientStatus = c.getPatientStatus();

            hmsPatient uPatient = new hmsPatient(c.getPatientID(),firstName,phoneNumber,patientStatus);

            done = updatePatient(uPatient);

            if (done) {
                System.out.println("Patient Detail updated successfully!!!");

                loadPatientFile();
            } else {
                System.out.println("System error occured!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    // get Login ID
    public String getLoginId()
    {
        boolean loop = true;

        String ans = "";
        hmsUserNameValidator usernameValidator = new hmsUserNameValidator();

        while(loop) {
            ans = console.nextLine();
            if(usernameValidator.validate(ans)) {
                loop = false;
            } else {
                System.out.println("Invalid loginID! loginID length must be between 3 and 15 without space.");
                System.out.print("Try again : ");
            }
        }
        return ans;
    }


    // get Password
    public String getPassword() {
        boolean loop = true;

        String ans = "";
        hmsPasswordValidator passwordValidator = new hmsPasswordValidator();

        while(loop) {
            ans = console.nextLine();
            if(passwordValidator.validate(ans)) {
                loop = false;
            } else {
                System.out.println("Invalid password! Password length must be between 6 and 20 at least 1 digit, 1 capital letter and 1 special character (@#$%)");
                System.out.print("Try again : ");
            }
        }

        return ans;
    }




    // search patient
    public void searchPatient() {
        clearScreen();


        Iterator it = arrPatientList.iterator();
        int count = 0;

        System.out.println("-------------------------------------------------------------------------");


        System.out.println("Please enter the patient ID number to search the record: ");
        int recID = console.nextInt();

        while (it.hasNext()) {
            hmsPatient p = (hmsPatient) it.next();
            if ((p.getPatientID() == (recID))) {

                System.out.println(String.format("%-30s: %s", "@ Patient Id", p.getPatientID()));
                System.out.println(String.format("%-30s: %s", "@ Full Name", p.getFullName()));
                System.out.println(String.format("%-30s: %s", "@ Phone", p.getPhoneNumber()));
                System.out.println(String.format("%-30s: %s", "@ Patient Status", p.getPatientStatus()));



                System.out.println("-----------------------------------------------------------------------");
            }
        }
        System.out.println("");
    }

    // show all treatment list
    public void showTreatmentList()
    {
        clearScreen();
        int count = 0;
        Iterator it = arrTreatmentList.iterator();


        System.out.println("------------------------------------------------------------------------");

        while(it.hasNext()) {

            count++;
            hmsTreatment g = (hmsTreatment) it.next();
            System.out.println(String.format("%-30s: %d","@ Treatment Id",g.getTreatmentID()));
            System.out.println(String.format("%-30s: %s","@ Name",g.getTreatmentName()));
            System.out.println(String.format("%-30s: %s","@ Description",g.getTreatmentDescription()));
            System.out.println(String.format("%-30s: %s","@ Cost",g.getTreatmentCost()));

            System.out.println("---------------------------------------------------------------------------");
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s","@ Total number of Treatment",count));
        System.out.println("");

    }


    // add new treatment
    public void addNewTreatment() {
        clearScreen();

        Scanner console = new Scanner(System.in);
        Iterator it = arrTreatmentList.iterator();

        int tempID = 0;
        while (it.hasNext()) {
            hmsTreatment t = (hmsTreatment) it.next();
            tempID = t.getTreatmentID();
        }
        tempID++;
        int treatmentID = tempID;

        System.out.print("Please enter treatment name: ");
        String treatName = console.nextLine();

        System.out.print("Please enter treatment description: ");
        String treatDesc = console.nextLine();

        System.out.print("Please enter total cost: ");
        double treatCost = getPriceFromConsole();

        try {
            output = new Formatter(new FileWriter(txtTreatmentFile, true));
            output.format("%d,%s,%s,%.2f\r\n", treatmentID,treatName, treatDesc, treatCost);
            output.close();
            System.out.println("Treatment added successfully!");

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        arrTreatmentList.clear();
        loadTreatmentFile();
    }


    // get amount from console
    public double getPriceFromConsole() {
        //Scanner console = new Scanner(System.in);

        double ret = 0.00;

        boolean done = false;

        while(!done) {
            try {
                double amount = Double.parseDouble(console.nextLine());
                ret = amount;
                done = true;
            } catch (Exception e) {
                System.out.print("You entered the invalid amount. Please try again!");
            }
        }

        return ret;
    }

    // load treatment text file
    public boolean loadTreatmentFile() {
        try {
            arrTreatmentList.clear();
            reader = new BufferedReader(new FileReader(new File(txtTreatmentFile)));
            String line = reader.readLine();
            while (line != null) {
                String[] record = line.split(",");

                int treatmentNumber = Integer.parseInt(record[0].trim());
                String treatmentName = record[1].trim();
                String description = record[2].trim();
                double treatCost = Double.parseDouble(record[3].trim());

                hmsTreatment theTreat = new hmsTreatment(treatmentNumber, treatmentName, description,treatCost);
                arrTreatmentList.add(theTreat);
                line = reader.readLine();
            }
            reader.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
            return false;
        }
    }


    // get treatment by treatNumber
    private hmsTreatment getTreatment(int tNumber) {
        hmsTreatment selected = null;

        Iterator<hmsTreatment> it = arrTreatmentList.iterator();

        while(it.hasNext()) {
            hmsTreatment g = (hmsTreatment) it.next();
            if(g.getTreatmentID() == tNumber) {
                selected = g;
                break;
            }
        }

        return selected;
    }


    public int inputTreatmentIDFromConsole() throws Exception {
        int treatId = Integer.parseInt(console.nextLine());

        Iterator<hmsTreatment> it = arrTreatmentList.iterator();
        boolean found = false;

        while(it.hasNext()) {
            hmsTreatment g = (hmsTreatment) it.next();
            if(treatId == g.getTreatmentID()) {
                found = true;
                break;
            }
        }
        if(found) return treatId;
        else {
            throw new Exception("Cannot find the treatment number with treatment number '"+treatId+"'");
        }
    }


    // update treatment detail by entering treatment id
    public void updateTreatmentDetail() throws Exception
    {

        System.out.print("Please enter the treatment ID that you with to update : ");
        int dID = inputTreatmentIDFromConsole();
        hmsTreatment d = getTreatment(dID);
        boolean done = false;

        System.out.print("@ treatment name : [" + d.getTreatmentName() + "] ");
        String treatName = console.nextLine();
        System.out.print("@ treatment description : [" + d.getTreatmentDescription() + "] ");
        String treatDesc = console.nextLine();
        System.out.print("@ treatment cost : [" + d.getTreatmentCost() + "] ");
        Double treatCost = Double.parseDouble(console.nextLine());


        if (treatName.length() <= 0 || "".equals(treatName)) treatName = d.getTreatmentName();
      //  if (treatCost.length() <= 0 || "".equals(treatCost)) treatCost = d.getTreatmentCost();


      //  hmsTreatment uTreatment = new hmsTreatment(d.getTreatmentID(), d.getTreatmentName(), d.getTreatmentDescription(), d.getTreatmentCost());

        hmsTreatment uTreatment = new hmsTreatment(d.getTreatmentID(), treatName, treatDesc, treatCost);


        done = updateTreatment(uTreatment);

        if (done) {
            System.out.println("Treatment Detail updated successfully!!!");

            loadTreatmentFile();

        }
    }

    //update treatment record
    public boolean updateTreatment(hmsTreatment treat) throws Exception
    {
        output = new Formatter(new FileWriter("treatmentChanged.txt"));

        Iterator<hmsTreatment> it = arrTreatmentList.iterator();
        while (it.hasNext()) {
            hmsTreatment d = (hmsTreatment)it.next();
            if (d.getTreatmentID() ==treat.getTreatmentID()) {

                output.format("%d,%s,%s,%s\r\n", treat.getTreatmentID(), treat.getTreatmentName(), treat.getTreatmentDescription(), treat.getTreatmentCost());
            } else {
                output.format("%d,%s,%s,%s\r\n", d.getTreatmentID(), d.getTreatmentName(), d.getTreatmentDescription(), d.getTreatmentCost());
            }
        }
        output.close();

        // delete old file
        File fileToDelete = new File(txtTreatmentFile);
        boolean delete = fileToDelete.delete();

        // renames treatChanged.txt to doctor.txt
        File fileToRename = new File("treatmentChanged.txt");
        File renamedFile = new File(txtTreatmentFile);

        boolean rename = fileToRename.renameTo(renamedFile);

        return rename;

    }

    // search treatment by id
    public void searchTreatment()
    {
        clearScreen();

        System.out.println("Please enter the treatment ID number to search the record: ");
        int recID = console.nextInt();

        //delte this code start
        Iterator it = getTreamentList().iterator();
        while (it.hasNext()) {
            hmsTreatment p = (hmsTreatment) it.next();
            if ((p.getTreatmentID() == (recID))) {

                System.out.println(String.format("%-30s: %s", "@ Treatment Id", p.getTreatmentID()));
                System.out.println(String.format("%-30s: %s", "@ Name", p.getTreatmentName()));
                System.out.println(String.format("%-30s: %s", "@ Description", p.getTreatmentDescription()));
                System.out.println(String.format("%-30s: %s", "@ Cost", p.getTreatmentCost()));


                System.out.println("-----------------------------------------------------------------------");
            }
        }
        System.out.println("");
    }


    // delete treatment record by entering treatment id
    public void deleteTreatment() throws Exception {
        clearScreen();
        Scanner console = new Scanner(System.in);

        boolean done = false;
        output = new Formatter(new FileWriter("treatmentChanged.txt", true));

        System.out.println(String.format("%-20s%-20s%-20s%-20s", "ID", "Name","Description", "Cost"));


        System.out.println("--------------------------------------------------------------------");

        Iterator it1 = getTreamentList().iterator();

        System.out.println(arrTreatmentList);

        while (it1.hasNext())
        {
            hmsTreatment d = (hmsTreatment) it1.next();
            System.out.println(String.format("%-20s%-20s%-20s%-20s", d.getTreatmentID(), d.getTreatmentName(), d.getTreatmentDescription(),d.getTreatmentCost()));
            System.out.println();}


        // delete a record from the text file based on the patientId
        System.out.println("Please enter the treatment's ID number to delete the treatment: ");
        int treatId = Integer.parseInt(console.nextLine());

        Iterator it = getTreamentList().iterator();
        while (it.hasNext()) {
            hmsTreatment d = (hmsTreatment) it.next();
            if (!(d.getTreatmentID() == (treatId))) {
                output.format("%d,%s,%s,%s\r\n",d.getTreatmentID(), d.getTreatmentName(), d.getTreatmentDescription(), d.getTreatmentCost());
            } else {
                done = true;
            }
        }

        output.close();
        if (done == true) {
            //clearScreen();
            System.out.println("Treatment deleted successfully!");

            // delete old file
            File fileToDelete = new File("treatment.txt");
            boolean delete = fileToDelete.delete();

            // rename doctorChanged.txt to doctor.txt
            File fileToRename = new File("treatmentChanged.txt");
            File renamedFile = new File("treatment.txt");
            boolean rename = fileToRename.renameTo(renamedFile);

            arrTreatmentList.clear();
            loadTreatmentFile();
        } else {
            System.out.println("Treatment not found!!!");
        }

    }

    //start

    // add new Visitation
    public void addNewVisitation() throws Exception {
        clearScreen();
        //Scanner console = new Scanner(System.in);
        Iterator it = arrVisitedPatientList.iterator();

        int tempID = 0;
        while(it.hasNext()) {
            hmsVisitation g = (hmsVisitation) it.next();
            tempID = g.getvID();
        }
        tempID++;
        int visitNumber = tempID;

        System.out.print("Please select Patient ("+printPatient()+"):");
        int patientID = inputPatientID();

//        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//        Date dateobj = new Date();
//        String visitDate = df.format(dateobj);

        System.out.print("Please enter visitation date: ");
        String vDate = console.nextLine();

        System.out.print("Please enter visitation purpose: ");
        String purpose = console.nextLine();
        System.out.print("Please select the Doctor: ("+printDoctor()+ ")");
        int doctorID = inputDoctorIDFromConsole();

        try {
            output = new Formatter(new FileWriter(txtVisitedPatientFile,true));
            output.format("%d,%d,%s,%s,%d\r\n", visitNumber,patientID,vDate,purpose,doctorID);
            output.close();
            System.out.println("Visitation details added successfully!");

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        arrVisitedPatientList.clear();
        loadVisitedPatientList();
    }

    public String printDoctor() {
        Iterator it = arrDoctorList.iterator();
        String doctorStr = "";

        while(it.hasNext()) {
            hmsDoctor c = (hmsDoctor) it.next();
            if(doctorStr != "") {
                doctorStr += ", " + c.getStaffId() + ":" + c.getFullName();
            } else {
                doctorStr = c.getStaffId() + ":" + c.getFullName();
            }
        }
        return doctorStr;
    }


    public String printPatient() {
        Iterator it = arrPatientList.iterator();
        String patientStr = "";

        while(it.hasNext()) {
            hmsPatient c = (hmsPatient) it.next();
            if(patientStr != "") {
                patientStr += ", " + c.getPatientID() + ":" + c.getFullName();
            } else {
                patientStr = c.getPatientID() + ":" + c.getFullName();
            }
        }
        return patientStr;
    }

    public String printTreatment() {
        Iterator it = arrTreatmentList.iterator();
        String treatmentStr = "";

        while(it.hasNext()) {
            hmsTreatment c = (hmsTreatment) it.next();
            if(treatmentStr != "") {
                treatmentStr += ", " + c.getTreatmentID() + ":" + c.getTreatmentName();
            } else {
                treatmentStr = c.getTreatmentID() + ":" + c.getTreatmentName();
            }
        }
        return treatmentStr;
    }






    // get patient from
    public int inputPatientID() {
        //Scanner console = new Scanner(System.in);

        boolean loop = true;
        int ans = 0;

        while(loop) {
            try {
                int input = Integer.parseInt(console.nextLine());
                if (isValidPatient(input)) {
                    ans = input;
                    loop = false;
                } else
                    {
                    System.out.println("Please choose valid patient id (" + printPatient() + "):");
                }
            } catch (Exception e) {
                System.out.println("Wrong data type is entered!!!");
            }
        }
        return ans;
    }



    public boolean isValidPatient(int patientID) {

        Iterator it = arrPatientList.iterator();
        boolean valid = false;

        while(it.hasNext()) {
            hmsPatient c = (hmsPatient) it.next();
            if(patientID == c.getPatientID()) {

                valid = true;
            }
        }
        return valid;
    }

//    //get visitation patient id from console
    public int inputVisitationPatientIDFromConsole() throws Exception {


        int vpatientID = Integer.parseInt(console.nextLine());

        Iterator<hmsVisitation> it = arrVisitedPatientList.iterator();
        boolean found = false;

        while (it.hasNext()) {
            hmsVisitation d = (hmsVisitation) it.next();
            if (vpatientID == d.getvID()) {
                found = true;
                break;
            }
        }
        if (found) return vpatientID;
        else {
            throw new Exception("Cannot find the Visitor Patient with ID '" + vpatientID + "'");
        }
    }



    // get visistation patient id from array of visitation
    private hmsVisitation getVisitedPatient(int vpatientID) {
        hmsVisitation selected = null;

        Iterator<hmsVisitation> it = arrVisitedPatientList.iterator();

        while (it.hasNext()) {
            hmsVisitation d = (hmsVisitation) it.next();
            if (d.getvID() == vpatientID) {
                selected = d;
                break;
            }
        }

        return selected;
    }



   // update visitation record
    public void updateVisitedPatient() throws Exception {
        //Scanner console = new Scanner(System.in);
        try {
            boolean done = false;

            System.out.println("Please enter the Visitation ID that you wish to update : ");
            int vPatientId = inputVisitationPatientIDFromConsole();
            console.nextLine();

            hmsVisitation c = getVisitedPatient(vPatientId);
            System.out.println("------------------------------------------------------------");
            System.out.print("@ Patient Name : [" + c.getVisitPatientName() + "] ");
            String firstName = console.nextLine();

            System.out.print("@ Visit Date : [" + c.getVisitDate() + "] ");
            String visitDate = console.nextLine();

            System.out.print("@ Visit Doctor : [" + c.getVisitToDoctor() + "] ");
            String visitDoctor = console.nextLine();

            System.out.print("@ Visit Purpose : [" + c.getVisitPurpose() + "] ");
            String visitPurpose = console.nextLine();



            if (firstName.length() <= 0 || firstName == "") firstName = c.getVisitPatientName();
            if (visitDate.length() <= 0 || visitDate == "") visitDate = c.getVisitDate();
            if (visitDoctor.length() <= 0 || visitDoctor == "") visitDoctor = c.getVisitToDoctor();
            if (visitPurpose.length() <= 0 || visitPurpose == "") visitPurpose = c.getVisitPurpose();


            hmsVisitation vPatient = new hmsVisitation(vPatientId,firstName,visitDate,visitDoctor,visitPurpose);

            System.out.println(vPatient);

            done = updateDoneVisitPatient(vPatient);

            if (done) {
                System.out.println("Visitors Detail updated successfully!!!");
                loadVisitedPatientList();
            } else {
                System.out.println("System error occured!!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
//
//    //update patient record by entering id
    public boolean updateDoneVisitPatient(hmsVisitation patient) throws Exception {
        output = new Formatter(new FileWriter("visitedPatientChanged.txt"));

        Iterator<hmsVisitation> it = arrVisitedPatientList.iterator();
        while(it.hasNext()) {
            System.out.println("in while loop");

            hmsVisitation c = (hmsVisitation) it.next();
            if(c.getvID() == patient.getvID()) {

                System.out.println("equal");
                output.format("%d,%s,%s,%s,%s\r\n",patient.getvID(),patient.getVisitPatientName(),patient.getVisitDate(),patient.getVisitToDoctor(),patient.getVisitPurpose());
            } else {
                System.out.println("not equal");

                output.format("%d,%s,%s,%s,%s\r\n",c.getvID(),c.getVisitPatientName(),c.getVisitDate(),c.getVisitToDoctor(),c.getVisitPurpose());
            }
        }

        output.close();

        // delete old file
        File fileToDelete = new File("visitedPatient.txt");
        boolean delete = fileToDelete.delete();

        // rename patientChanged.txt to patient.txt
        File fileToRename = new File("visitedPatientChanged.txt");
        File renamedFile = new File("visitedPatient.txt");
        boolean rename = fileToRename.renameTo(renamedFile);


        return rename;

    }

    //delete visitation details
    public void deleteVisitedPatient() throws Exception {
        clearScreen();
        Scanner console = new Scanner(System.in);

        boolean done = false;
        output = new Formatter(new FileWriter("visitedPatientChanged.txt", true));

        System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", "ID", "Name","Date", "Doctor visited","Purpose of visit"));


        System.out.println("--------------------------------------------------------------------");

        Iterator it1 = getVisitedPatientList().iterator();

        while (it1.hasNext())
        {
            hmsVisitation v = (hmsVisitation) it1.next();
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s", v.getvID(), v.getVisitPatientName(),v.getVisitDate(),v.getVisitToDoctor(),v.getVisitPurpose()));
            System.out.println();}


        // delete a record from the text file based on the visitationid
        System.out.println("Please enter the Visitation ID number to delete the visitation details: ");
        int vId = Integer.parseInt(console.nextLine());

        Iterator it = getVisitedPatientList().iterator();
        while (it.hasNext()) {
            hmsVisitation v = (hmsVisitation) it.next();
            if (!(v.getvID() == (vId))) {
                output.format("%d,%s,%s,%s,%s\r\n",vId, v.getVisitPatientName(), v.getVisitDate(),v.getVisitToDoctor(),v.getVisitPurpose());
            } else {
                done = true;
            }
        }

        output.close();
        if (done == true) {
            //clearScreen();
            System.out.println("Visited Patient deleted successfully!");

            // delete old file
            File fileToDelete = new File("visitedPatient.txt");
            boolean delete = fileToDelete.delete();

            // rename patientChanged.txt to patient.txt
            File fileToRename = new File("visitedPatientChanged.txt");
            File renamedFile = new File("visitedPatient.txt");
            boolean rename = fileToRename.renameTo(renamedFile);

            arrVisitedPatientList.clear();
            loadVisitedPatientList();
        } else {
            System.out.println("Visitation detail not found!!!");
        }

    }



    //Search visitation details
// search visitation by id
    public void searchVisitedPatient()
    {
        clearScreen();

        System.out.println("Please enter the visitation ID number to search the record: ");
        int td = console.nextInt();

        //delete this code start
        Iterator it = getVisitedPatientList().iterator();
        while (it.hasNext()) {
            hmsVisitation v = (hmsVisitation) it.next();
            if ((v.getvID() == (td))) {

                System.out.println(String.format("%-30s: %s", "@ Visitation Id", v.getvID()));
                System.out.println(String.format("%-30s: %s", "@ Name", v.getVisitPatientName()));
                System.out.println(String.format("%-30s: %s", "@ Date", v.getVisitDate()));
                System.out.println(String.format("%-30s: %s", "@ Doctor", v.getVisitToDoctor()));
                System.out.println(String.format("%-30s: %s", "@ Purpose of visit", v.getVisitPurpose()));


                System.out.println("-----------------------------------------------------------------------");
            }
        }
        System.out.println("");
    }

    //add patient treatment

    public void addPatientTreatment() throws Exception {
        clearScreen();
        //Scanner console = new Scanner(System.in);
        Iterator it = arrPatientTreatmentList.iterator();

        int tempID = 0;

        while(it.hasNext()) {
            hmsPatientTreatment g = (hmsPatientTreatment) it.next();
            tempID = g.getPatientTreatmentID();
        }
        tempID++;
        int objPatientNumber = tempID;


        System.out.print("Please select patient ("+printPatient()+"):");
        int patientID = inputPatientID();

        System.out.print("Please select the doctor: ("+printDoctor()+ ")");
        int doctorID = inputDoctorIDFromConsole();

        System.out.print("Please select the treatment name: ("+printTreatment()+ ")");
        int treatmentID = inputTreatmentIDFromConsole();

        System.out.print("Please enter start date");
        String startDate = console.nextLine();

        System.out.print("Please enter end date");
        String endDate = console.nextLine();

        System.out.print("Please enter tretment status(Ongoing/Done)");
        String treatStatus = console.nextLine();

        System.out.print("Please enter total cost");
        Double totalCost = console.nextDouble();



//        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//        Date dateobj = new Date();
//        String visitDate = df.format(dateobj);


        try {
            output = new Formatter(new FileWriter(txtPatientTreatmentFile,true));
            output.format("%d,%d,%d,%d,%s,%s,%s,%.2f\r\n", objPatientNumber,patientID,doctorID,treatmentID,startDate,endDate,treatStatus,totalCost);
            output.close();
            System.out.println("Patient visitation added successfully!");

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        arrPatientTreatmentList.clear();
        loadPatientTreatmentListFile();

    }


    public void deletePatientTreatment() throws Exception {

        {

        clearScreen();
        Scanner console = new Scanner(System.in);

        boolean done = false;
        output = new Formatter(new FileWriter("patienttreatmentChanged.txt", true));

        System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", "ID", "Patient ID","Treatment Name", "Doctor ID","Total Cost","Start date","End date","Status"));


        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        Iterator it1 = getPatientTreatmentList().iterator();

        System.out.println(arrPatientTreatmentList);

        while (it1.hasNext())
        {
            hmsPatientTreatment v = (hmsPatientTreatment) it1.next();
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", v.getPatientTreatmentID(), v.getPatientId(),v.getDoctorId(),v.getTreatmentId(),v.getTreatmentStartDate(),v.getTreatmentEndDate(),v.getTreatmentStatus(),v.getTotalCost()));
            System.out.println();}


        // delete a record from the text file based on the patient treatmentid
        System.out.println("Please enter the Patient Treatment ID number to delete the record: ");
        int vId = Integer.parseInt(console.nextLine());

        Iterator it = getPatientTreatmentList().iterator();
        while (it.hasNext()) {
            hmsPatientTreatment v = (hmsPatientTreatment) it.next();
            if (!(v.getPatientTreatmentID() == (vId))) {
                output.format("%d,%d,%d,%d,%s,%s,%s,%f\n",vId, v.getPatientId(), v.getDoctorId(),v.getTreatmentId(),v.getTreatmentStartDate(),v.getTreatmentEndDate(),v.getTreatmentStatus(),v.getTotalCost());
            } else {
                done = true;
            }
        }

        output.close();
        if (done == true) {
            //clearScreen();
            System.out.println("Patient Treatment detail deleted successfully!");


            // delete old file
            File fileToDelete = new File("patienttreatment.txt");
            boolean delete = fileToDelete.delete();

            // rename patientTreatmentChanged.txt to patientTreatment.txt
            File fileToRename = new File("patienttreatmentChanged.txt");
            File renamedFile = new File("patienttreatment.txt");
            boolean rename = fileToRename.renameTo(renamedFile);

            arrPatientTreatmentList.clear();
            loadPatientTreatmentListFile();
        } else {
            System.out.println("Patient Treatment detail not found!!!");
        }

    }
    }

    public void searchPatientTreatment()
    {
        clearScreen();

        System.out.println("Please enter the Patient Treatment ID number to search the record: ");
        int td = console.nextInt();

        //delete this code start
        Iterator it = getPatientTreatmentList().iterator();
        while (it.hasNext()) {
            hmsPatientTreatment pt = (hmsPatientTreatment) it.next();
            if ((pt.getPatientTreatmentID() == (td))) {

                System.out.println(String.format("%-30s: %s", "@ Patient Treatment ID", pt.getPatientTreatmentID()));
                System.out.println(String.format("%-30s: %s", "@ Patient ID", pt.getPatientId()));
                System.out.println(String.format("%-30s: %s", "@ Treatment ID", pt.getTreatmentId()));
                System.out.println(String.format("%-30s: %s", "@ Doctor ID", pt.getDoctorId()));
                System.out.println(String.format("%-30s: %s", "@ Cost of treatment", pt.getTotalCost()));
                System.out.println(String.format("%-30s: %s", "@ Treatment start date", pt.getTreatmentStartDate()));
                System.out.println(String.format("%-30s: %s", "@ Treatment end date", pt.getTreatmentEndDate()));
                System.out.println(String.format("%-30s: %s", "@ Treatment Status", pt.getTreatmentStatus()));




                System.out.println("--------------------------------------------------------------------------------------");
            }
        }
        System.out.println("");

    }



    // update Patient Treatment by entering patienttreatment id
//    public void updatePatientTreatment() throws Exception {
//        try {
//
//            boolean done = false;
//
//            System.out.print("Please enter the Patient treatment ID that you with to update : ");
//            int dID = inputTreatmentIDFromConsole();
//            hmsPatientTreatment d = getDoctor(dID);
//
//            System.out.print("@ Doctor's fullname : [" + d.getFullName() + "] ");
//            String fullName = console.nextLine();
//
//            System.out.println("------------------------------------------------------------");
//
//            String password = "";
//            String comparePassword = "";
//            boolean isValid = false;
//            hmsPasswordValidator passwordValidator = new hmsPasswordValidator();
//
//            do {
//                System.out.print("@ Doctor's password (leave blank if not changed): ");
//                password = console.nextLine();
//                if (password.length() > 0) {
//                    if (passwordValidator.validate(password)) {
//                        System.out.println("@ Confirm password: ");
//                        comparePassword = getPassword();
//                        if (password.equals(comparePassword)) {
//                            isValid = true;
//                        } else {
//                            System.out.println("The password does not match. Please try again!");
//                        }
//                    } else {
//                        System.out.println("Invalid password! Password length must be between 6 and 20 at least 1 digit, 1 capital letter and 1 special character (@#$%)");
//                    }
//                } else {
//                    isValid = true;
//                }
//
//            } while (!isValid);
//
//
//            String role = "Doctor";
//
//
//            if (password.length() <= 0 || "".equals(password)) password = d.getPassword();
//            if (fullName.length() <= 0 || "".equals(fullName)) fullName = d.getFullName();
//
//
//            hmsDoctor uDoctor = new hmsDoctor(d.getStaffId(), d.getLoginId(), password, fullName, role);
//
//            done = updateDoctor(uDoctor);
//
//            if (done) {
//                System.out.println("Doctor Detail updated successfully!!!");
//
//                loadDoctorFile();
//            } else {
//                System.out.println("System error occured!!!");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//    //update patient treatment record
//    public boolean updatePatientTreatment(hmsDoctor doctor) throws Exception
//    {
//        output = new Formatter(new FileWriter("doctorChanged.txt"));
//
//        Iterator<hmsDoctor> it = arrDoctorList.iterator();
//        while (it.hasNext()) {
//            hmsDoctor d = (hmsDoctor) it.next();
//            if (d.getStaffId() == doctor.getStaffId()) {
//                output.format("%d,%s,%s,%s,%s\r\n", doctor.getStaffId(), doctor.getLoginId(), doctor.getPassword(), doctor.getFullName(), doctor.getRole());
//            } else {
//                output.format("%d,%s,%s,%s,%s\r\n", d.getStaffId(), d.getLoginId(), d.getPassword(), d.getFullName(), d.getRole());
//            }
//        }
//        output.close();
//
//        // delete old file
//        File fileToDelete = new File(txtDoctorFile);
//        boolean delete = fileToDelete.delete();
//
//        // renames doctorChanged.txt to doctor.txt
//        File fileToRename = new File("doctorChanged.txt");
//        File renamedFile = new File(txtDoctorFile);
//
//        boolean rename = fileToRename.renameTo(renamedFile);
//
//        return rename;
//
//    }

    // managerQuitProgram
    public void quitProgram() {
        //Scanner console = new Scanner(System.in);
        String input = "";
        System.out.println("Are you sure you want to quit the program (Y/N)?");

        try {
            input = console.nextLine();

            if(input.equalsIgnoreCase("Y")) {
                clearScreen();
                System.out.println("Program ended!!! You can close the console now");
                System.exit(0);
            } else if(input.equalsIgnoreCase("N")) {
                clearScreen();
                showMenu();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Enter 'Y' to quit or 'N' to continue the program");
            System.out.println("");
            quitProgram();
        }
    }


    //make payment
    private void makePayment() throws Exception {


        System.out.println("------------------------------------------------------------------");
        System.out.println("                        Make a payment                            ");
        System.out.println("------------------------------------------------------------------");

        int patientID;
        try {
            System.out.print("Please enter the Patient's ID to make a payment : ");
            try {
                patientID = inputPatientID();
            } catch (Exception e) {
                throw new Exception("Patient's ID Not Found!");
            }
            System.out.print("Please enter the amount : ");
            Double paymentAmount = getPriceFromConsole();

            Iterator<hmsPayment> it1 = arrPaymentList.iterator();
            int tempID = 0;
            while (it1.hasNext()) {
                hmsPayment payment = (hmsPayment) it1.next();
                tempID = payment.getPaymentID();
            }
            tempID++;
            int paymentID = tempID;
            try {
                output = new Formatter(new FileWriter(txtPaymentFile, true));
                output.format("%d,%d,%.2f,%s\r\n", paymentID, patientID, paymentAmount, datetimeformat.format(new Date()));
                output.close();
                arrPaymentList.clear();
                loadpaymentFile();
            } catch (IOException e) {
                System.out.println(e.toString() + ":" + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    //make payment
    private void makeInvoice() throws Exception {



        System.out.println("------------------------------------------------------------------");
        System.out.println("                        Make an Invoice                            ");
        System.out.println("------------------------------------------------------------------");

        try
        {

            System.out.print("Please enter the Patient ID for creating invoice :");
            int patientId = inputPatientIDFromConsole();

            System.out.print("Please enter the Treatment ID for creating invoice :");
            int treatmentdetailId = inputTreatmentIDFromConsole();

            double invoicedAmount;

            Iterator<hmsPatientTreatment> it = arrPatientTreatmentList.iterator();



            while (it.hasNext()) {
                hmsPatientTreatment cg = (hmsPatientTreatment) it.next();
                if (cg.getPatientId() == patientId && cg.getPatientTreatmentID() == treatmentdetailId) {
                    hmsTreatment objT = getTreatment(cg.getTreatmentId());
                  ;

                    loadPatientTreatmentListFile();
                    loadTreatmentFile();
                }
            }



            Iterator<hmsInvoice> it1 = arrInvoiceList.iterator();
            int tempID = 0;
            while (it.hasNext()) {
                hmsInvoice invoice = (hmsInvoice) it1.next();
                tempID = invoice.getInvoiceID();
            }
            tempID++;
            int invoiceId = tempID;



            hmsTreatment treatDetail = getTreatment(treatmentdetailId);
            invoicedAmount = treatDetail.getTreatmentCost();

            double finalAmount = invoicedAmount + (invoicedAmount*10/100);

            System.out.println("final amount : "+finalAmount);

            try {
                output = new Formatter(new FileWriter(txtInvoiceFile, true));

                output.format("%d,%d,%d,%.2f,%s\r\n", invoiceId, patientId, treatmentdetailId, invoicedAmount, datetimeformat.format(new Date()));
                System.out.println("Invoice created successfully!!!");

                System.out.println("----------------------------------------------------------------------");

                System.out.println(String.format("%-30s: %s","@ Invoice Id", invoiceId));
                System.out.println(String.format("%-30s: %s","@ Patient Id",patientId));
                System.out.println(String.format("%-30s: %s","@ Treatment Id",treatmentdetailId));
                System.out.println(String.format("%-30s: %s","@ Amount(Includes tax)",finalAmount));
                System.out.println(String.format("%-30s: %s","@ Date",datetimeformat.format(new Date())));

                System.out.println("-----------------------------------------------------------------------");


                output.close();
                arrInvoiceList.clear();
                loadInvoiceFile();

            } catch (IOException e) {
                System.out.println(e.toString() + ":" + e.getMessage());
            }


            loadPatientFile();
            loadTreatmentFile();

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }


    }



    // add patient to ward
    public void addPatientToWard() {
        clearScreen();
        //Scanner console = new Scanner(System.in);
        Iterator it = arrWardPatientList.iterator();

        int tempID = 0;
        String treatmentStartDate = "";
        String treatmentEndDate = "";

        while(it.hasNext()) {
            hmsWardPatient g = (hmsWardPatient) it.next();
            tempID = g.getAdmitID();
        }
        tempID++;
        int patientWardRecordID = tempID;

        System.out.print("Please select the Ward ("+printWard()+"):");
        int wardID = inputWardID();

        System.out.print("Please select Patient ("+printPatient()+"):");
        int patientID = inputPatientID();

        System.out.print("Please enter admit date (dd/mm/yyyy) : ");
        boolean isValid = false;
        do {
            try {
                String input = console.nextLine();
                hmsDateValidator dateValidator = new hmsDateValidator();
                treatmentStartDate = dateValidator.validate(input);
                isValid = true;
            } catch (ParseException e) {
                System.out.println("Input date is invalid. Please enter the 'dd/mm/yyyy' format!");
                System.out.print("Please enter admit date : ");
            }
        } while (!isValid);

        System.out.print("Please enter discharge date (dd/mm/yyyy) : ");
        boolean isValidEndDate = false;
        boolean isendDateGreater = false;
        do {
            try {
                String input = console.nextLine();

                if (input.isEmpty()){
                    isendDateGreater = true;
                    isValidEndDate = true;
                    treatmentEndDate = "Ongoing";

                }else {

                    hmsDateValidator dateValidator = new hmsDateValidator();
                    treatmentEndDate = dateValidator.validate(input);
                    isValidEndDate = true;
                    isendDateGreater = dateValidator.CompareDate(treatmentStartDate, treatmentEndDate);

                    if (!isendDateGreater){
                        System.out.println("Discharge date cannot be earlier than admit date. Please enter a valid discharge in 'dd/mm/yyyy' format!");
                        System.out.print("Please enter discharge date : ");
                    }
                }


            } catch (ParseException e) {
                System.out.println("Input date is invalid. Please enter the 'dd/mm/yyyy' format!");
                System.out.print("Please enter discharge date : ");
            }
        } while (!isValidEndDate || !isendDateGreater);

        try {
            output = new Formatter(new FileWriter(txtWardPatientList,true));
            output.format("%d,%d,%d,%s,%s\r\n", patientWardRecordID,wardID,patientID,treatmentStartDate,treatmentEndDate);
            output.close();
            System.out.println("Patient added to ward successfully!");

        } catch (Exception e) {
            System.out.println(e.toString() + ":" + e.getMessage());
        }
        arrWardPatientList.clear();
        loadWardPatientFile();
    }
    // view patient by ward
    public void viewPatientByWard() {
        clearScreen();
        System.out.print("Please enter Patient's Name to search : ");
        String findStr = console.nextLine();

        Iterator it = getWardPatientList().iterator();
        int count = 0;

        System.out.println("-------------------------------------------------------------------------");

        while(it.hasNext()) {
            hmsWardPatient g = (hmsWardPatient) it.next();
            hmsPatient c = getPatient(g.getPatientID());
            hmsWard d = getWard(g.getWardID());

            if(c.getFullName().toLowerCase().contains(findStr.toLowerCase())) {
                count++;
                System.out.println(String.format("%-30s: %s","@ Patient admit ID",g.getAdmitID()));
                System.out.println(String.format("%-30s: %s","@ Patient Name",c.getFullName()));
                System.out.println(String.format("%-30s: %s","@ Ward",d.getWardName()));
                System.out.println(String.format("%-30s: %s","@ Admit date",g.getDateTransferred()));
                System.out.println(String.format("%-30s: %s","@ Discharge date",g.getDateDischarged()));

                System.out.println("---------------------------------------------------------------------------");
            }
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-30s: %s","@ Total number of records",count));
        System.out.println("");

    }
    // get Ward by wardID
    private hmsWard getWard(int wardID) {
        hmsWard selected = null;

        Iterator<hmsWard> it = arrWardList.iterator();

        while(it.hasNext()) {
            hmsWard a = (hmsWard) it.next();
            if(a.getWardID() == wardID) {
                selected = a;
                break;
            }
        }

        return selected;
    }
    public String printWard() {
        Iterator it = arrWardList.iterator();
        String wardStr = "";

        while(it.hasNext()) {
            hmsWard c = (hmsWard) it.next();
            if(wardStr != "") {
                wardStr += ", " + c.getWardID() + ":" + c.getWardName();
            } else {
                wardStr = c.getWardID() + ":" + c.getWardName();
            }
        }
        return wardStr;
    }
    public int inputWardID() {
        //Scanner console = new Scanner(System.in);

        boolean loop = true;
        int ans = 0;

        while(loop) {
            try {
                int input = Integer.parseInt(console.nextLine());
                if (isValidWard(input)) {
                    ans = input;
                    loop = false;
                } else {
                    System.out.println("Please choose valid Ward id (" + printWard() + "):");
                }
            } catch (Exception e) {
                System.out.println("Wrong data type is entered!!!");
            }
        }
        return ans;
    }

    public boolean isValidWard(int wardID) {

        Iterator it = arrWardList.iterator();
        boolean valid = false;

        while(it.hasNext()) {
            hmsWard c = (hmsWard) it.next();
            if(wardID == c.getWardID()) {
                valid = true;
            }
        }
        return valid;
    }

    public void viewReports()
    {

        int patienttreatmentId;


        try {
            try
            {
                System.out.print("Please enter Patient treatment ID for the Report : ");

                patienttreatmentId = inputPatientTreatmentIDFromConsole();
            } catch (Exception e)
            {
                throw new Exception("Patient treatment ID Not Found!");
            }

            hmsPatientTreatment patienttreat = getPatientTreatment(patienttreatmentId);


            System.out.println("----------------------------------------------------------------------");
            System.out.println(String.format("%-30s: %s","@ Patient Name",getPatientNamefromPatientId(patienttreat.getPatientId())));
            System.out.println(String.format("%-30s: %s","@ Doctor Name", getDoctorNamefromdDoctorId(patienttreat.getDoctorId())));
            System.out.println(String.format("%-30s: %s","@ Treatment Name",getTreatmentNamefromTreatmentId(patienttreat.getTreatmentId())));
            System.out.println(String.format("%-30s: %s","@ Start date",patienttreat.getTreatmentStartDate()));
            System.out.println(String.format("%-30s: %s","@ End date",patienttreat.getTreatmentEndDate()));
            System.out.println(String.format("%-30s: %s","@ Treatment Cost",patienttreat.getTotalCost()));
            System.out.println("-----------------------------------------------------------------------");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //patient name from patient id
    public String getPatientNamefromPatientId(int pid)throws Exception
    {
        Iterator<hmsPatient> it = getPatientList().iterator();
        boolean found = false;
        String pName = null;
        while(it.hasNext())
        {
            hmsPatient obj = (hmsPatient) it.next();
            if(pid == obj.getPatientID())
            {
                found = true;
                pName = obj.getFullName();
                break;
            }
        }
        if(found) return pName;

        else {
            throw new Exception("Cannot find the Patient name with patientId '"+pid+"'");
        }
    }

    //doctro name from doctor id
    public String getDoctorNamefromdDoctorId(int did)throws Exception
    {
        Iterator<hmsDoctor> it = getDoctorList().iterator();
        boolean found = false;
        String dName = null;
        while(it.hasNext())
        {
            hmsDoctor obj = (hmsDoctor) it.next();
            if(did == obj.getStaffId())
            {
                found = true;
                dName = obj.getFullName();
                break;
            }
        }
        if(found) return dName;

        else {
            throw new Exception("Cannot find the doctor name with doctorid '"+did+"'");
        }
    }

    //treatment name from treatment id
    public String getTreatmentNamefromTreatmentId(int tid)throws Exception
    {
        Iterator<hmsTreatment> it = getTreamentList().iterator();
        boolean found = false;
        String tName = null;
        while(it.hasNext())
        {
            hmsTreatment obj = (hmsTreatment) it.next();
            if(tid == obj.getTreatmentID())
            {
                found = true;
                tName = obj.getTreatmentName();
                break;
            }
        }
        if(found) return tName;

        else {
            throw new Exception("Cannot find the treatment name with treatment id '"+tid+"'");
        }
    }


    //input patienttreatment id from console
    public int inputPatientTreatmentIDFromConsole() throws Exception {
        int patientId = Integer.parseInt(console.nextLine());

        Iterator<hmsPatientTreatment> it = getPatientTreatmentList().iterator();
        boolean found = false;

        while(it.hasNext()) {
            hmsPatientTreatment c = (hmsPatientTreatment) it.next();
            if(patientId == c.getPatientTreatmentID()) {
                found = true;
                break;
            }
        }
        if(found) return patientId;
        else {
            throw new Exception("Cannot find the Patient treatment with patientId '"+patientId+"'");
        }
    }



    // get Patienttreatment by patientId
    private hmsPatientTreatment getPatientTreatment(int patientId) {
        hmsPatientTreatment selected = null;

        Iterator<hmsPatientTreatment> it = arrPatientTreatmentList.iterator();

        while(it.hasNext()) {
            hmsPatientTreatment c = (hmsPatientTreatment) it.next();
            if(c.getPatientTreatmentID() == patientId) {
                selected = c;
                break;
            }
        }

        return selected;
    }





}
