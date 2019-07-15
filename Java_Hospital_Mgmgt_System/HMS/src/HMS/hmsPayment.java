package HMS;
import java.util.Date;
public class hmsPayment { private int paymentID;
    private int patientID;
    private double paymentAmount;
    private Date paymentDateTime;


    public hmsPayment(int paymentID,int patientID,double paymentAmount,Date paymentDateTime){
        this.paymentID=paymentID;
        this.patientID=patientID;
        this.paymentAmount=paymentAmount;
        this.paymentDateTime=paymentDateTime;
    }
    //setter
    public void setPaymentID(int paymentID){
        this.paymentID=paymentID;
    }
    public void setPatientID(int patientID){
        this.patientID=patientID;
    }
    public void setPaymentAmount(double paymentAmount){
        this.paymentDateTime=paymentDateTime;
    }
    public void setPaymentDateTime(Date paymentDateTime){
        this.paymentDateTime=paymentDateTime;
    }

    public int getPaymentID(){
        return this.paymentID;
    }
    public int getPatientID(){
        return this.patientID;
    }
    public double getPaymentAmount(){
        return this.paymentAmount;
    }
    public Date getPaymentDateTime(){
        return this.paymentDateTime;
    }
}
