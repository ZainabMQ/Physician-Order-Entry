package com.mycompany.physicianorderentrysystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User {
    
    private int ID;
    private String Password;
    private boolean Active;
    private String Name;
    private String Role;
    private String Department;
    private String [] UserMenuItems = new String [20];
    private String CreationDateTime;
  
    public User(){
        this.ID = 0;
        this.Password = "";
        this.Active = true;
        this.Name = "";
        this.Role = "";
        this.Department = "";
        for(int i=0; i<20; i++)
            this.UserMenuItems[i] = "";
        this.CreationDateTime = "";
        
    }

    public User(int ID, String Password, boolean Active, String Name, String Role, String Department, String CreationDateTime) {
        this.ID = ID;
        this.Password = Password;
        this.Active = Active;
        this.Name = Name;
        this.Role = Role;
        this.Department = Department;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.CreationDateTime = date.format(cal.getTime());
    }


    public void GetUserInfo(User user) {
        user.ID = this.ID;
        user.Active = this.Active;
        user.Name = this.Name;
        user.Role = this.Role;
        user.Department = this.Department;
        user.UserMenuItems = this.UserMenuItems;
        user.CreationDateTime = this.CreationDateTime;
    }
    
    public boolean getActive() {
        return Active;
    }

    public void setActive(boolean Active) {
        this.Active = Active;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String[] getUserMenuItems() {
        return UserMenuItems;
    }

    public void setUserMenuItems(String[] UserMenuItems) {
        this.UserMenuItems = UserMenuItems;
    }

    public String getCreationDateTime() {
        return CreationDateTime;
    }

    public void setCreationDateTime(String CreationDateTime) {
        this.CreationDateTime = CreationDateTime;
    }
}