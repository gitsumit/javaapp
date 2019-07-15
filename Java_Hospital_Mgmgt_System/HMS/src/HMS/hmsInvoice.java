package HMS;
import java.util.Date;
public class hmsInvoice {

    private int invoiceId;
    private int patientId;
    private int treatmentId;
    private double amountInvoice;
    private Date dateInvoice;



    public hmsInvoice(int iId, int pId, int tId, double aInvoice, Date dInvoice )
    {
        this.invoiceId = iId;
        this.patientId = pId;
        this.treatmentId = tId;
        this.amountInvoice = aInvoice;
        this.dateInvoice = dInvoice;
    }

    //setter
    public void setInvoiceID(int invoiceID) {
        this.invoiceId = invoiceID;
    }

    public void setPatientID(int patientID) {
        this.patientId = patientID;
    }

    public void setTreatmentID(int treatmentID) {
        this.treatmentId = treatmentID;
    }
    public void setVisitedDate(Date visitedDate){

        this.dateInvoice=visitedDate;
    }

    public void setAmountInvoice(double amountInvoice){

        this.amountInvoice=amountInvoice;
    }
    //getter
    public int getInvoiceID(){
        return this.invoiceId;
    }
    public int getPatientID(){
        return this.patientId;
    }
    public int getTreatmentID(){
        return this.treatmentId;
    }
    public Date getVisitedDate(){
        return this.dateInvoice;
    }
    public double getAmountInvoice(){
        return this.amountInvoice;
    }
}
