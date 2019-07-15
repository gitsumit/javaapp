package HMS;

import java.util.Date;

public class hmsVisitation {

    private int vID;
    private String patientName;
    private String visitDate;
    private String visitToDoctor;
    private String visitPurpose;


    public hmsVisitation(int vID,String patientName, String visitDate, String visitToDoctor, String visitPurpose)
    {
        this.vID = vID;
        this.patientName = patientName;
        this.visitDate = visitDate;
        this.visitToDoctor = visitToDoctor;
        this.visitPurpose = visitPurpose;
    }

    /* setter */

    public void setvID(int vID)
    {
        this.vID = vID;
    }
    public void setVisitPatientName(String patientName)
    {
        this.patientName = patientName;
    }
    public void setVisitDate(String visitDate)
    {
        this.visitDate = visitDate;
    }
    public void setVisitToDoctor(String visitToDoctor)
    {
        this.visitToDoctor = visitToDoctor;
    }
    public void setVisitPurpose(String visitPurpose)
    {
        this.visitPurpose = visitPurpose;
    }


    //getter

    public int getvID(){ return vID;}
    public String getVisitPatientName() { return patientName; }
    public String getVisitDate()
    {
        return visitDate;
    }
    public String getVisitToDoctor()
    {
        return visitToDoctor;
    }
    public String getVisitPurpose()
    {
        return visitPurpose;
    }

}
