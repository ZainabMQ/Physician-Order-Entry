package com.mycompany.physicianorderentrysystem;

public class LabTest extends OrderCatalogue {
    protected double normalLow;
    protected double normalHigh;
    protected String resultUnit;

    
    public LabTest(){
        super();
        this.normalLow = 0.0;
        this.normalHigh = 0.0;
        this.resultUnit = "";
    }
    
    public LabTest(double normalLow, double normalHigh, String resultUnit, String Code, String Name, String Type) {
        super(Code, Name, Type);
        this.normalLow = normalLow;
        this.normalHigh = normalHigh;
        this.resultUnit = resultUnit;
    }
    
    public void GetTestInfo (LabTest test) {
        test.normalLow = this.normalLow;
        test.normalHigh = this.normalHigh;
        test.resultUnit = this.resultUnit;
        test.Code = this.Code;
        test.Name = this.Name;
        test.Type = this.Type;          
    } 
    
    public boolean isValidTest (String code) {
        boolean isValid;
        if(this.Code.equals(code)) {
            isValid = true;
        }
        else {
            isValid = false;
        }
        return isValid;
    }    
}

