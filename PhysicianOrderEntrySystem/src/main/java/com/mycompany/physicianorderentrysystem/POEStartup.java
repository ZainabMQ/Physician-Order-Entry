
package com.mycompany.physicianorderentrysystem;
import static com.mycompany.physicianorderentrysystem.PhysicianOrderEntrySystem.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class POEStartup {
    
    static void WritingtoFiles()throws IOException{
        FileWriter writer = new FileWriter("DATA_Users.TXT");
        String UM ="";
        String Temp1 = "";
        for(User TempUser: Users) {
            UM="";
            if (TempUser.getActive()) Temp1="Y"; else Temp1="N";
            for (String temp : TempUser.getUserMenuItems())
                UM=UM.concat(temp).concat(";");
            UM = UM.substring(0,UM.length()-1);
            writer.write(TempUser.getID() + "," + TempUser.getPassword() + "," + Temp1 + "," +TempUser.getName() + ",");
            writer.write( TempUser.getRole()+ "," + TempUser.getCreationDateTime()+",");
            writer.write(TempUser.getDepartment() + "," + UM+"\n");
        }           
        writer.close();
        
        FileWriter writer1 = new FileWriter("DATA_Tests.TXT");
        for (LabTest Temp : Tests)
            writer1.write(Temp.Code+","+Temp.Name+","+Temp.normalLow+","+Temp.normalHigh+","+Temp.resultUnit+",L\n");
        writer1.close();
        
        FileWriter writer2 = new FileWriter("DATA_PharmOrders.TXT");    //DATA_PharmOrders.TXT
        for (PharmOrders Temp : PhOrders){
            writer2.write(Temp.OrderNumber+","+Temp.PhysicianID+","+Temp.MRN+","+Temp.PharmTech+","+Temp.MedCode+",");
            writer2.write(Temp.OrderType +","+Temp.Medinstructions+","+Temp.Days+","+Temp.Frequency+","+Temp.OrderStatus+",");    
            writer2.write(Temp.OrderDateTime+","+Temp.DispenseDateTime+"\n");        
        }            
        writer2.close();
    
        FileWriter writer3 = new FileWriter("DATA_Medications.TXT");
        for (Medication Temp : Meds)
            writer3.write(Temp.Code+","+Temp.Name+","+Temp.MedicationType+",M\n");
     
        writer3.close();
        
                
        FileWriter writer5 = new FileWriter("DATA_NextOrderNo.TXT");
        writer5.write(NextOrderNo+"");
        writer5.close(); 
        
        FileWriter writer4 = new FileWriter("DATA_LabOrders.TXT");
        for (LabOrders Temp : LOrders){
            writer4.write(Temp.OrderNumber+","+Temp.PhysicianID+","+Temp.MRN+","+Temp.CollectedBy+","+Temp.ResultTech+",");
            writer4.write(Temp.MedCode +","+Temp.OrderType+","+Temp.Result+","+Temp.ResultFlag+","+Temp.OrderStatus+",");    
            writer4.write(Temp.OrderDateTime+","+Temp.CollectionDateTime+","+Temp.ResultDateTime+"\n");        
        }            
        writer4.close();
    }

    static void ReadingFiles() throws IOException{
        //Reading To users array
         File MyFile6 = new File("DATA_Users.TXT");
        try (FileReader fr = new FileReader(MyFile6)) {
            BufferedReader FBuffer = new BufferedReader(fr);
            String FileRecord;
            FileRecord = FBuffer.readLine();
            String [] UserRec = new String [7];
            while( FileRecord!=null){
                UserRec = FileRecord.trim().split(",");
                User TempUser = new User();
                TempUser.setID(Integer.parseInt(UserRec[0]));
                TempUser.setPassword(UserRec[1]);
                
                if(UserRec[2].equals("Y"))
                    TempUser.setActive(true);
                else TempUser.setActive(false);
                
                TempUser.setName(UserRec[3]);
                TempUser.setRole(UserRec[4]);
                TempUser.setCreationDateTime(UserRec[5]);
                TempUser.setDepartment(UserRec[6]);
                TempUser.setUserMenuItems(UserRec[7].split(";"));
                Users.add(TempUser);
                FileRecord = FBuffer.readLine();
            }
        }
        //Reading To patient array
        File MyFile1 = new File("DATA_Patients.TXT");
        try (FileReader fr = new FileReader(MyFile1)) {
            BufferedReader FBuffer = new BufferedReader(fr);
            String FileRecord;
            FileRecord = FBuffer.readLine();
            String [] UserRec = new String [6];
            while( FileRecord!=null){
                UserRec = FileRecord.trim().split(",");
                Patient TempPatient = new Patient();
                TempPatient.MRN = Integer.parseInt(UserRec[0]);
                TempPatient.Name = UserRec[1];
                TempPatient.DateOfBirth = UserRec[2];
                TempPatient.Gender = UserRec[3].charAt(0);
                TempPatient.Address = UserRec[4];
                TempPatient.Phone = UserRec[5];
                POEPatient.add(TempPatient);
                FileRecord = FBuffer.readLine();
            }
        }
        
        //Reading to Pharmacy Orders array
        File MyFile2 = new File("DATA_PharmOrders.TXT");
        try (FileReader fr = new FileReader(MyFile2)) {
            BufferedReader FBuffer = new BufferedReader(fr);
            
            String FileRecord;
            
            FileRecord =FBuffer.readLine();
            String [] UserRec = new String [12];
            
            while( FileRecord!=null){
                UserRec = FileRecord.trim().split(",");
                PharmOrders TempOrder = new PharmOrders();
                TempOrder.OrderNumber = Integer.parseInt(UserRec[0]);
                TempOrder.PhysicianID = Integer.parseInt(UserRec[1]);
                TempOrder.MRN = Integer.parseInt(UserRec[2]);
                TempOrder.PharmTech = Integer.parseInt(UserRec[3]);
                TempOrder.MedCode = UserRec[4];
                TempOrder.OrderType = UserRec[5];
                TempOrder.Medinstructions = UserRec[6];
                TempOrder.Days = Integer.parseInt(UserRec[7]);
                TempOrder.Frequency = UserRec[8];
                TempOrder.OrderStatus = UserRec[9];
                TempOrder.OrderDateTime = UserRec[10];
                TempOrder.DispenseDateTime = UserRec[11];
                PhOrders.add(TempOrder);
                FileRecord = FBuffer.readLine();
            }
        }
        //Reading to Lab Orders array
        File MyFile3 = new File("DATA_LabOrders.TXT");
        try (FileReader fr = new FileReader(MyFile3)) {
            BufferedReader FBuffer = new BufferedReader(fr);
            
            String FileRecord;
            
            FileRecord =FBuffer.readLine();
            String [] UserRec = new String [13];
            
            while( FileRecord!=null){
                UserRec = FileRecord.trim().split(",");
                LabOrders TempOrder = new LabOrders();
                TempOrder.OrderNumber = Integer.parseInt(UserRec[0]);
                TempOrder.PhysicianID = Integer.parseInt(UserRec[1]);
                TempOrder.MRN = Integer.parseInt(UserRec[2]);
                TempOrder.CollectedBy = UserRec[3];
                TempOrder.ResultTech = Integer.parseInt(UserRec[4]);
                TempOrder.MedCode = UserRec[5];
                TempOrder.OrderType = UserRec[6];
                TempOrder.Result = Double.parseDouble(UserRec[7]);
                TempOrder.ResultFlag = UserRec[8];
                TempOrder.OrderStatus = UserRec[9];
                TempOrder.OrderDateTime = UserRec[10];
                TempOrder.CollectionDateTime = UserRec[11];
                TempOrder.ResultDateTime = UserRec[12];
                LOrders.add(TempOrder);
                FileRecord = FBuffer.readLine();
            }
        }
        
        //Reading to Tests array
        File MyFile4 = new File("DATA_Tests.TXT");
        try (FileReader fr = new FileReader(MyFile4)) {
            BufferedReader FBuffer = new BufferedReader(fr);
            
            String FileRecord;
            
            FileRecord = FBuffer.readLine();
            String [] UserRec = new String [6];
            
            while( FileRecord!=null){
                UserRec = FileRecord.trim().split(",");
                LabTest TempTest = new LabTest();
                TempTest.Code = UserRec[0];
                TempTest.Name = UserRec[1];
                TempTest.normalLow = Double.parseDouble(UserRec[2]);
                TempTest.normalHigh = Double.parseDouble(UserRec[3]);
                TempTest.resultUnit = UserRec[4];
                TempTest.Type = UserRec[5];
                Tests.add(TempTest);
                FileRecord = FBuffer.readLine();
            }
        }
        //Reading to Medication array
        File MyFile5 = new File("DATA_Medications.TXT");
        try (FileReader fr = new FileReader(MyFile5)) {
            BufferedReader FBuffer = new BufferedReader(fr);
            
            String FileRecord;
            
            FileRecord = FBuffer.readLine();
            String [] UserRec = new String [4];
            
            while( FileRecord!=null){
                UserRec = FileRecord.trim().split(",");
                Medication TempMed = new Medication();
                TempMed.Code = UserRec[0];
                TempMed.Name = UserRec[1];
                TempMed.MedicationType = UserRec[2];
                TempMed.Type = UserRec[3];
                Meds.add(TempMed);
                FileRecord = FBuffer.readLine();
            }
        }
        
        Scanner fs = new Scanner(new File("DATA_NextOrderNo.txt"));
        NextOrderNo = fs.nextInt();        
    }
    
    
    static void BuildMenuOptions() throws FileNotFoundException, IOException{
        for (int i=0; i<20; i++)
            for (int j=0; j<2; j++){
                POEOptions[i][j] = "";
                ActualMenu[i][j] = "";
            }
        File MyFile = new File("DATA_MenuOptions.TXT");
        try (FileReader fr = new FileReader(MyFile)) {
            BufferedReader FBuffer = new BufferedReader(fr);
            
            String FileRecord;
            int counter=0;
            FileRecord =FBuffer.readLine();
            while( FileRecord!=null){
                POEOptions[counter] =FileRecord.trim().split(",");
                counter++;
                FileRecord=FBuffer.readLine();
            }
            
        }
    }
    
 static int login2() throws FileNotFoundException, IOException{
        Scanner input = new Scanner(System.in);
        System.out.print("Enter username: ");
        int UserName = input.nextInt();
        System.out.print("Enter Password: ");
        String password = input.next();

        for(User TempUser : Users){
            if(UserName == TempUser.getID()){
                if (password.equals(TempUser.getPassword()))
                    if (TempUser.getActive()){
                            CurrentUser.setID(UserName);
                            CurrentUser.setName(TempUser.getName());
                            CurrentUser.setRole(TempUser.getRole());
                            CurrentUser.setDepartment(TempUser.getDepartment());
                            CurrentUser.setUserMenuItems(TempUser.getUserMenuItems());                             
                            return 1;
                        }
                    else return 2;
                else return 0;
            }         
        }           
    return 0;
    }   

static int login() throws FileNotFoundException, IOException{
        Scanner input = new Scanner(System.in);
        int j = 0, UserName;
        String password;
        do {
            System.out.print("Enter username: ");
            UserName = input.nextInt();
            System.out.print("Enter Password: ");
            password = input.next();

            for(User TempUser : Users){
                if(UserName == TempUser.getID()){
                    if (password.equals(TempUser.getPassword()))
                        if (TempUser.getActive()){
                            CurrentUser.setID(UserName);
                            CurrentUser.setName(TempUser.getName());
                            CurrentUser.setRole(TempUser.getRole());
                            CurrentUser.setDepartment(TempUser.getDepartment());
                            CurrentUser.setUserMenuItems(TempUser.getUserMenuItems());                             
                            return 1;
                        }
                        else{ 
                            System.out.println("User is inactive, contact system admin.");
                            return 0;
                        }
                    else break;
                }
            }     
            j++;
            System.out.println("UserName and/or Password are invalid.");
        } while (j<3);
    return 0;
}




// ***** end of class *****
}