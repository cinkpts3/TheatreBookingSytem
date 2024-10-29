package theatrebookingsystem.model;

public class CustomerModel {
    private String name;
    private String email;
    private int phoneNumber;

    public CustomerModel (String name, String email, int phoneNumber){
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        if (name != null && !name.isEmpty()){
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("name cannot be empty");
        }
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        if (email != null && email.contains("@") && email.contains(".") ){
            this.email = email;
        }
        else{
            throw new IllegalArgumentException("please enter correct email");
        }
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber){
        if (phoneNumber < 15 ){
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("please enter correct phone number");
        }
    }

    public String toString(){
        return "NAME: " + name +
                "; EMAIL: " + email +
                "; PHONE NUMBER: +350 " + phoneNumber;
    }

}
