package com.mycompany.physicianorderentrysystem;
public class Patient {
    protected int MRN;
    protected String Name;
    protected String DateOfBirth;
    protected char Gender;
    protected String Address;
    protected String Phone;

    public Patient() {
        this.MRN = 0;
        this.Name = "";
        this.DateOfBirth = "";
        this.Gender = ' ';
        this.Address = "";
        this.Phone = "";
    }

    public Patient(int MRN, String Name, String DateOfBirth, char Gender, String Address, String Phone) {
        this.MRN = MRN;
        this.Name = Name;
        this.DateOfBirth = DateOfBirth;
        this.Gender = Gender;
        this.Address = Address;
        this.Phone = Phone;
    }
    
    public void GetPatInfo(Patient Pat) {
        Pat.MRN = this.MRN;
        Pat.Name = this.Name;
        Pat.DateOfBirth = this.DateOfBirth;
        Pat.Gender = this.Gender;
        Pat.Address = this.Address;
        Pat.Phone = this.Phone; 
    }  
}
