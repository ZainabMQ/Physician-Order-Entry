package com.mycompany.physicianorderentrysystem;

public class LabOrders extends PatOrders {
    protected String CollectionDateTime;
    protected String CollectedBy;
    protected double Result;
    protected String ResultFlag;
    protected String ResultDateTime;
    protected int ResultTech;

    public LabOrders() {
        super();  
        this.CollectionDateTime = "0";
        this.CollectedBy = "0";
        this.Result = 0;
        this.ResultFlag = "0";
        this.ResultDateTime = "0";
        this.ResultTech = 0;
    }

    public LabOrders(String CollectedBy, double Result, String ResultFlag, int ResultTech, int MRN, int PhysicianID, int OrderNumber, String MedCode, String OrderType, String OrderStatus) {
        super(MRN, PhysicianID, OrderNumber, MedCode, "L");
        this.CollectedBy = CollectedBy;
        this.Result = Result;
        this.ResultFlag = ResultFlag;
        this.ResultTech = ResultTech;
    }
    
    public void GetInfoLabOrder(LabOrders LOrder) {
        LOrder.CollectionDateTime = this.CollectionDateTime;
        LOrder.CollectedBy = this.CollectedBy;
        LOrder.Result = this.Result;
        LOrder.ResultFlag = this.ResultFlag;
        LOrder.ResultDateTime = this.ResultDateTime;
        LOrder.ResultTech = this.ResultTech;
        LOrder.MRN = this.MRN;
        LOrder.PhysicianID = this.PhysicianID;
        LOrder.OrderNumber = this.OrderNumber;
        LOrder.OrderType = this.OrderType;
        LOrder.MedCode = this.MedCode;
        LOrder.OrderStatus = this.OrderStatus;
        LOrder.OrderDateTime = this.OrderDateTime; 
    }   
}