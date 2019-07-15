package HMS;

public class hmsPatient {

    private int patientID;
    private String fullName;
    private String phoneNumber;
    private String patientStatus;

    //constructor with initialisation
    public hmsPatient(){
        this.patientID=0;
        this.fullName="";
        this.phoneNumber="";
        this.patientStatus="New";
    }
    //constructor with parameterisation
    public hmsPatient(int patientID,String fullName,String phoneNumber,String patientStatus){
        this.patientID=patientID;
        this.fullName=fullName;
        this.phoneNumber=phoneNumber;
        this.patientStatus=patientStatus;
    }
    //setter
    public void setPatientID(int patientID){
        this.patientID=patientID;
    }
    public void setFullName(String fullName){
        this.fullName=fullName;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public void setPatientStatus(String patientStatus){
        this.patientStatus=patientStatus;
    }
    //getter
    public int getPatientID(){
        return patientID;
    }
    public String getFullName(){
        return fullName;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getPatientStatus(){
        return patientStatus;
    }

}
