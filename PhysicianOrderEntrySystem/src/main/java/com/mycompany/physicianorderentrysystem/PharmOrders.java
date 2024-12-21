package com.mycompany.physicianorderentrysystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PharmOrders extends PatOrders {
    protected String DispenseDateTime;
    protected int PharmTech;
    protected String Medinstructions;
    protected int Days;
    protected String Frequency;
    
    
    public PharmOrders(){
        super();
        this.PharmTech = 0;
        this.Medinstructions = "0";
        this.Days = 0;
        this.Frequency = "0";
        this.DispenseDateTime = "0"; 
    }

    public PharmOrders(int PharmTech, String Medinstructions, int Days, String Frequency, int MRN, int PhysicianID, int OrderNumber, String MedCode) {
        super(MRN, PhysicianID, OrderNumber, MedCode, "P");
        this.PharmTech = PharmTech;
        this.Medinstructions = Medinstructions;
        this.Days = Days;
        this.Frequency = Frequency;
    }
    
    public void GetInfoPharmOrder(PharmOrders POrder) {
        POrder.PharmTech = this.PharmTech;
        POrder.Medinstructions = this.Medinstructions;
        POrder.Days = this.Days;
        POrder.Frequency = this.Frequency;
        POrder.DispenseDateTime = this.DispenseDateTime;
        POrder.MRN = this.MRN;
        POrder.PhysicianID = this.PhysicianID;
        POrder.OrderNumber = this.OrderNumber;
        POrder.OrderType = this.OrderType;
        POrder.MedCode = this.MedCode;
        POrder.OrderStatus = this.OrderStatus;
        POrder.OrderDateTime = this.OrderDateTime; 
    }    
}