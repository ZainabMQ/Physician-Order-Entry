package com.mycompany.physicianorderentrysystem;

public class Medication extends OrderCatalogue {
    
    protected String MedicationType;    
    
    public Medication() {
        super();
        this.MedicationType= ""; 
    }

    public Medication(String MedicationType, String MedCode, String MedName, String MedType) {
        super(MedCode, MedName, MedType);
        this.MedicationType = MedicationType;
    }

    public void GetMedInfo (Medication Med) {
        Med.MedicationType = this.MedicationType;
        Med.Code = this.Code;
        Med.Name = this.Name;
        Med.Type = this.Type;          
    }  
}
