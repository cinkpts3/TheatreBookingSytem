package theatrebookingsystem.model;

public class CustomerModel {
    private String name;
    private String email;
    private String phoneNumber;

    public CustomerModel (String name, String email, String phoneNumber){
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
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        // Add any phone number validation you prefer here
        if (phoneNumber != null ) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Please enter a correct phone number");
        }
    }

    public String toString(){
        return "NAME: " + name +
                "; EMAIL: " + email +
                "; PHONE NUMBER: +350 " + phoneNumber;
    }

}
