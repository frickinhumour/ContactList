package com.example.contactlistupdate;

public class Contact {

    private String Name;
    private String PhoneNumber;
    private int id;

public Contact(){

    }
    public Contact(String name, String phone){
        this.Name=name;
        this.PhoneNumber=phone;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getId(){
        return id;
    }

    public void setId(int parseInt) {
        id=parseInt;
    }
}
