package HMS;

public class hmsTreatment {


    private int treatmentID;
    private String treatmentName;
    private String treatmentDescription;
    private double treatmentCost;

    //constructor with initialisation
    public hmsTreatment(){
        this.treatmentID=0;
        this.treatmentName="";
        this.treatmentDescription="";
        this.treatmentCost=0.0;
    }
    //constructor with parameterisation
    public hmsTreatment(int treatmentID,String treatmentName,String treatmentDescription,double treatmentCost){
        this.treatmentID=treatmentID;
        this.treatmentName=treatmentName;
        this.treatmentDescription=treatmentDescription;
        this.treatmentCost=treatmentCost;
    }

    //setter
    public void setTreatmentID(int treatmentID){
        this.treatmentID=treatmentID;
    }
    public void setTreatmentName(String treatmentName){
        this.treatmentName=treatmentName;
    }
    public void setTreatmentDescription(String treatmentDescription){
        this.treatmentDescription=treatmentDescription;
    }
    public void setTreatmentCost(double cost){
        this.treatmentCost=treatmentCost;
    }

    //getter
    public int getTreatmentID(){ return treatmentID; }
    public String getTreatmentName(){
        return treatmentName;
    }
    public String getTreatmentDescription(){
        return treatmentDescription;
    }
    public double getTreatmentCost (){return treatmentCost;}


}
