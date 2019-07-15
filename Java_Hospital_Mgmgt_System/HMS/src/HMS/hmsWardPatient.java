package HMS;

public class hmsWardPatient
{
    private int admitID;
    private int wardID;
    private int patientID;
    private String dateAdmitted;
    private String dateDischarged;

    public hmsWardPatient(int admitID, int wardID,int patientID, String dateAdmitted, String dateDischarged) {
        this.admitID = admitID;
        this.wardID = wardID;
        this.patientID = patientID;
        this.dateAdmitted = dateAdmitted;
        this.dateDischarged = dateDischarged;
    }

    //setter
    public void setAdmitID(int admitID) {
        this.admitID = admitID;
    }

    public void setWardID(int wardID) {
        this.wardID = wardID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public void setDateTransferred(String transferDate) {
        this.dateAdmitted = dateAdmitted;
    }

    public void setDateDischarged(String dischargeDate) {
        this.dateDischarged = dischargeDate;
    }

    public int getAdmitID()
    {
        return admitID;
    }
    public int getWardID()
    {
        return wardID;
    }
    public int getPatientID(){return  patientID;}
    public String getDateTransferred() {return dateAdmitted;}
    public String getDateDischarged() {return dateDischarged;}



}
