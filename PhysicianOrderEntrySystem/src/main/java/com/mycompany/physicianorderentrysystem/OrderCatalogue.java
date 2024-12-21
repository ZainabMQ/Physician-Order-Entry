package com.mycompany.physicianorderentrysystem;
public class OrderCatalogue {
    protected String Name, Type, Code;
     
    public OrderCatalogue () {
        this.Name = "";
        this.Type = "";
        this.Code = "";
    }
    
    public OrderCatalogue(String Code, String Name, String Type) {
        this.Name = Name;
        this.Code = Code;
        this.Type = Type;
    }
}
