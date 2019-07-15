package HMS;

import java.util.Date;

public class hmsPatientTreatment
{
    private int patientTreatmentID;
    private int patientId;
    private int doctorId;
    private int treatmentName;
    private String treatmentStartDate;
    private String treatmentEndDate;
    private String treatmentStatus;
    private double totalCost;


    //constructor
    public hmsPatientTreatment(int patientTreatmentID, int patientId,int doctorId, int surgeryName, String surgeryStartDate, String surgeryEndDate, String surgeryStatus, double totalCost){
        this.patientTreatmentID=patientTreatmentID;
        this.patientId=patientId;
        this.doctorId=doctorId;
        this.treatmentName=surgeryName;
        this.treatmentStartDate=surgeryStartDate;
        this.treatmentEndDate=surgeryEndDate;
        this.treatmentStatus=surgeryStatus;
        this.totalCost=totalCost;

    }

    //setter
    public void setPatientTreatmentID(int pTreatID)
    {
        this.patientTreatmentID = pTreatID;
    }
    public void setPatientId(int pName)
    {
        this.patientId = patientId;
    }
    public void setDoctorId(int dName)
    {
        this.doctorId = doctorId;
    }
    public void setTreatmentName(int sName)
    {
        this.treatmentName = sName;
    }
    public void setTreatmentStartDate(String sDate)
    {
        this.treatmentStartDate = sDate;
    }
    public void setTreatmentEndDate(String eDate)
    {
        this.treatmentEndDate = eDate;
    }
    public void setTreatmentStatus(String sStatus)
    {
        this.treatmentStatus = sStatus;
    }
    public void setTotalCost(double tCost)
    {
        this.totalCost = tCost;
    }

    //getter
    public int getPatientTreatmentID() {return patientTreatmentID; }
    public int getPatientId()
    {
        return patientId;
    }
    public int getDoctorId()
    {
        return doctorId;
    }
    public int getTreatmentId()
    {
        return treatmentName;
    }
    public String getTreatmentStartDate()
    {
        return treatmentStartDate;
    }
    public String getTreatmentEndDate()
    {
        return treatmentEndDate;
    }
    public String getTreatmentStatus() { return treatmentStatus; }
    public double getTotalCost()
    {
        return totalCost;
    }
}
