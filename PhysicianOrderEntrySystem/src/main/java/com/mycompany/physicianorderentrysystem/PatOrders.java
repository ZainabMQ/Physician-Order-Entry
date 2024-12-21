package com.mycompany.physicianorderentrysystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//Zainab
public class PatOrders {
    protected int MRN;
    protected int PhysicianID;
    protected int OrderNumber;
    protected String MedCode;
    protected String OrderType;
    protected String OrderDateTime;
    protected String OrderStatus;

    public PatOrders() {
        this.MRN = 0;
        this.PhysicianID = 0;
        this.OrderNumber = 0;
        this.MedCode = "0";
        this.OrderType = "0";
        this.OrderStatus = "0";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.OrderDateTime = date.format(cal.getTime());
    }

    public PatOrders(int MRN, int PhysicianID, int OrderNumber, String OrderCode, String OrderType) {
        this.MRN = MRN;
        this.PhysicianID = PhysicianID;
        this.OrderNumber = OrderNumber;
        this.MedCode = OrderCode;
        this.OrderType = OrderType;
        this.OrderStatus = "Pending";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.OrderDateTime = date.format(cal.getTime());
    }    
}
 