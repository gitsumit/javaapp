package HMS;

public class hmsUserInterface {


    //declare instance variable
    private hmsController theController = new hmsController();

    //default constructor
    public hmsUserInterface() {

    }

    //Main method which is the starting point of the program
    public void startUp() {
        theController.clearScreen();

        int status = theController.checkLoadingStatus();


        if (status < 0) {
            if (status == -1) {
                System.out.println("There was a problem loading the HR data file!!!");
                System.exit(1);
            } else if (status == -2) {
                System.out.println("There was a problem loading the Doctor data file!!!");
                System.exit(1);
            } else if (status == -3) {
                System.out.println("There was a problem loading the Receptionist data file!!!");
                System.exit(1);
            }else if (status == -4) {
                System.out.println("There was a problem loading the Patient data file!!!");
                System.exit(1);
            } else if (status == -5) {
                System.out.println("There was a problem loading the PatientTreatment List data file!!!");
                System.exit(1);
            } else if (status == -6) {
                System.out.println("There was a problem loading the treatment data file!!!");
                System.exit(1);
            }
            else if (status == -7) {
                System.out.println("There was a problem loading the Visited Patient List data file!!!");
                System.exit(1);
            }
            else if (status == -8) {
                System.out.println("There was a problem loading the payment file!!!");
                System.exit(1);
            }
            else if (status == -9) {
                System.out.println("There was a problem loading the Invoice file!!!");
                System.exit(1);
            }
            else if (status == -10) {
                System.out.println("There was a problem loading the ward file!!!");
                System.exit(1);
            }
            else if (status == -11) {
                System.out.println("There was a problem loading the patient ward file!!!");
                System.exit(1);
            }

        } else {
            theController.showChooseMenu();
        }
    }
}
