package HMS;

public abstract class hmsStaff {

    protected int staffId;
    protected String loginId;
    protected String password;
    protected String fullName;
    protected String role;

    public hmsStaff(){
        this.staffId=0;
        this.loginId="";
        this.password="";
        this.fullName="";
        this.role="";
    }
    public hmsStaff(int staffId,String loginId,String password,String fullName,String role){
        this.staffId= staffId;
        this.loginId=loginId;
        this.password=password;
        this.fullName=fullName;
        this.role=role;
    }
    //setter
    public void setEmployeeId(int staffId){
        staffId=staffId;
    }
    public void setPassword(String password){
        password=password;
    }
    public void setLoginId(String loginId){
        loginId=loginId;
    }
    public void setFullName(String fullName){
        fullName=fullName;
    }
    public void setRole(String role){
        role=role;
    }

    //getter
    public int getStaffId(){
        return staffId;
    }
    public String getLoginId(){
        return loginId;
    }
    public String getPassword(){
        return password;
    }
    public String getFullName(){
        return fullName;
    }
    public String getRole(){
        return role;
    }


}
