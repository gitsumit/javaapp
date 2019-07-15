package HMS;

public class hmsReports {

    private int reportId;
    private int patientTreamentId;


    public hmsReports(int patientTreamentId, int reportId)
    {
        this.patientTreamentId = patientTreamentId;
        this.reportId = reportId;
    }


    public void setPatientTreatmentId(int patientTreamentId)
    {
        this.patientTreamentId = patientTreamentId;
    }
    public void setReportId(int reportId)
    {
        this.reportId = reportId;
    }

    public int getPatientTreatmentID()
    {
        return patientTreamentId;
    }
    public int getReportId()
    {
        return reportId;
    }

}
