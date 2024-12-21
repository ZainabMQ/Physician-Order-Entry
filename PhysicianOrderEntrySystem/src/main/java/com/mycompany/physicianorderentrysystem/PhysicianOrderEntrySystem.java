package com.mycompany.physicianorderentrysystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static com.mycompany.physicianorderentrysystem.POEStartup.*;
import static com.mycompany.physicianorderentrysystem.Functions_2.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class PhysicianOrderEntrySystem {
    public static String [][] POEOptions = new String [20][2];  
    public static String [][] ActualMenu = new String [20][2];
    public static User CurrentUser = new User();
    public static ArrayList <Patient> POEPatient = new ArrayList <>();
    public static ArrayList <PharmOrders> PhOrders = new ArrayList <>();
    public static ArrayList <LabOrders> LOrders = new ArrayList <>();
    public static ArrayList <LabTest> Tests = new ArrayList <>();
    public static ArrayList <Medication> Meds = new ArrayList <>();
    public static ArrayList <User> Users = new ArrayList <>();
    public static int NextOrderNo;
    
    
    
    public static String ViewPatientOrders(int Mtype, int category){
       // Mtype      0: all orders  1: lab     2: Pharm
       // category   0: all cats  1:pending   2:Collected
       //            3: completed 4:cancelled
       
        Patient tempPat = new Patient();
        String ordName="", targetCats="", patOrders="";
        
        if (!ReadMRN(tempPat)) return patOrders;
        
        if (category==0) targetCats = "Pending Collected Completed Issued Cancelled";
        if (category==1) targetCats = "Pending";
        if (category==2) targetCats = "Collected";
        if (category==3) targetCats = "Completed Issued";
        
        System.out.println("Srvs.  Order#  Code    Order Name            Ord.By  Date/Time            Satus");
        System.out.println("------------------------------------------------------------------------------------");
                
        if (Mtype==0 || Mtype==1){
            for (LabOrders x: LOrders){
                if (x.MRN == tempPat.MRN && targetCats.contains(x.OrderStatus) ){
                    for (LabTest y: Tests){
                        if (y.Code.equals(x.MedCode)){ 
                            ordName = y.Name;
                            break;
                        }
                    }    
                    patOrders = patOrders.concat(x.OrderNumber+"L ");
                    System.out.printf("LAB   %5d",x.OrderNumber);
                    System.out.printf("    %-6S",x.MedCode);
                    System.out.printf("  %-20.20S",ordName);
                    System.out.printf("  %-6S",x.PhysicianID);
                    System.out.printf("  %-15S",x.OrderDateTime);
                    System.out.printf("  %-10S",x.OrderStatus);
                    System.out.println();
                }    
            }    
        }
        if (Mtype==0 || Mtype==2){
            for (PharmOrders x: PhOrders){        
                if (x.MRN == tempPat.MRN && targetCats.contains(x.OrderStatus) ){
                    for (Medication y: Meds){
                        if (y.Code.equals(x.MedCode)){ 
                            ordName = y.Name;
                            break;
                        }
                    }    
                    patOrders = patOrders.concat(x.OrderNumber+"P ");
                    System.out.printf("PHAR %6d", x.OrderNumber);
                    System.out.printf("    %-6S", x.MedCode);
                    System.out.printf("  %-20.20S", ordName);
                    System.out.printf("  %-6S", x.PhysicianID);
                    System.out.printf("  %-15S", x.OrderDateTime);
                    System.out.printf("  %-10S", x.OrderStatus);
                    System.out.println();
                }    
            }    
        }
        if(ordName.equals(""))
            System.out.println("Patient has no orders.");
        
        System.out.println("\n");
        return patOrders;
    }
    
    public static String ViewOrders(int Mtype, int category){
       // Mtype      0: all orders  1: lab     2: Pharm
       // category   0: all cats  1:pending   2:Collected
       //            3: completed 4:cancelled
       
        Patient tempPat = new Patient();
        String ordName="", targetCats="", patOrders="";
                     
        if (category==0) targetCats = "Pending Collected Completed Issued Cancelled";
        if (category==1) targetCats = "Pending";
        if (category==2) targetCats = "Collected";
        if (category==3) targetCats = "Completed Issued";
        
        System.out.println(" MRN    Order#  Code    Order Name            Ord.By  Date/Time            Satus");
        System.out.println(" ------------------------------------------------------------------------------------");
                
        if (Mtype==0 || Mtype==1){
            for (LabOrders x: LOrders){
                if (targetCats.contains(x.OrderStatus) ){
                    for (LabTest y: Tests){
                        if (y.Code.equals(x.MedCode)){ 
                            ordName = y.Name;
                            break;
                        }
                    }    
                    patOrders = patOrders.concat(x.OrderNumber+"L ");
                    System.out.printf("%5d",x.MRN);
                    System.out.printf("  %5d",x.OrderNumber);
                    System.out.printf("    %-6S",x.MedCode);
                    System.out.printf("  %-20.20S",ordName);
                    System.out.printf("  %-6S",x.PhysicianID);
                    System.out.printf("  %-15S",x.OrderDateTime);
                    System.out.printf("  %-10S",x.OrderStatus);
                    System.out.println();
                }    
            }    
        }
        if (Mtype==0 || Mtype==2){
            for (PharmOrders x: PhOrders){        
                if (targetCats.contains(x.OrderStatus) ){
                    for (Medication y: Meds){
                        if (y.Code.equals(x.MedCode)){ 
                            ordName = y.Name;
                            break;
                        }
                    }    
                    patOrders = patOrders.concat(x.OrderNumber+"P ");
                    System.out.printf("%5d",x.MRN);
                    System.out.printf("  %5d", x.OrderNumber);
                    System.out.printf("    %-6S", x.MedCode);
                    System.out.printf("  %-20.20S", ordName);
                    System.out.printf("  %-6S", x.PhysicianID);
                    System.out.printf("  %-15S", x.OrderDateTime);
                    System.out.printf("  %-10S", x.OrderStatus);
                    System.out.println();
                }    
            }    
        }
        if(ordName.equals(""))
            System.out.println("Patient has no orders.");
        
        System.out.println("\n");
        return patOrders;
    }
   

    public static void addMedication() {
        Scanner input = new Scanner(System.in);
        boolean found = false;
        
        System.out.print("Enter the Medication Code: ");
        String code = input.nextLine().toUpperCase();
        
        for(Medication med : Meds) {
            if (med.Code.equals(code)) {
                System.out.println("This medication already exists.");
                found = true;
                break;
            }
        }

        if (!found) {
            Medication newMed = new Medication();
            newMed.Code=code;
            
            System.out.print("Enter the Medication Name: ");
            newMed.Name = input.nextLine();
           
            System.out.print("Enter the Medication Type: ");
            newMed.MedicationType = input.nextLine();
            Meds.add(newMed);
            System.out.println("\t\tMedication "+ newMed.Code +" was added successfully!");
            Pause();
        }
    }
    
    
    public static void addUser() {
        Scanner input = new Scanner(System.in);
        boolean found = false;
        
        System.out.print("Enter the User ID: ");
        int id = input.nextInt();
        
        for(User user : Users) {
            if (user.getID() == id) {
                System.out.println("This user already exists.");
                found = true;
                break;
            }
        }

        if (!found) {
            User newUser = new User();
            newUser.setID(id);
            
            System.out.print("Enter the User Password: ");
            newUser.setPassword(input.next());

            newUser.setActive(true);
            
            System.out.print("Enter the User Name: ");
            newUser.setName(input.next());
            
            System.out.println("Enter the User Role: ");
            System.out.println("1. Admin    2. Physician    3. Laboratory Tech    4. Pharmacy Tech");
            
            int x = 1;
            do{
                System.out.print(">> ");
                int Role = input.nextInt();
                switch(Role){
                    case 1:
                        newUser.setRole("Admin");
                        newUser.setUserMenuItems(new String[] {"UN","UA","UD","NM","NL"});
                        x = 0;
                        break;
                    case 2:
                        newUser.setRole("Physician");
                        newUser.setUserMenuItems(new String[] {"OL","OP","CO","VO","VR"});
                        x = 0;
                        break;
                    case 3:
                        newUser.setRole("Lab Tech");
                        newUser.setUserMenuItems(new String[] {"PC","PR","CB","RE"});
                        x = 0;
                        break;
                    case 4:
                        newUser.setRole("Pharm Tech");
                        newUser.setUserMenuItems(new String[] {"PM","PO","IM"});
                        x = 0;
                        break;
                    default: System.out.println("Invalid Role");   
                }
            }while(x==1);

            System.out.print("Enter the User Department: ");
            newUser.setDepartment(input.next());
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newUser.setCreationDateTime(date.format(cal.getTime()));
            
            Users.add(newUser);
            System.out.println("User added successfully!");
        }
    }
     
    public static void cancelOrder() {
        String CurrOrders = ViewPatientOrders(0,1);  // 0 =all types  1 = pending
        if (CurrOrders.equals(""))return;
        System.out.println(CurrOrders);
        Scanner scanner = new Scanner(System.in);
        int ON;
        do {
            System.out.print("\nEnter the order number to cancel ( 0 to exit ) : ");
            ON = scanner.nextInt();
            
            if (CurrOrders.contains(ON+"L")){
                for (LabOrders LO : LOrders)
                    if (LO.OrderNumber == ON){
                        LO.OrderStatus = "Cancelled";
                        System.out.println("\nOrder:  "+ON+"  "+LO.MedCode +"  has been cancelled.");
                        ON=0;
                        break;
                    }      
            }       
            else if(CurrOrders.contains(ON+"P")){
                for (PharmOrders PO : PhOrders)
                    if (PO.OrderNumber == ON){
                        PO.OrderStatus = "Cancelled";
                        System.out.println("\nOrder has been cancelled.");
                        ON=0;
                        break;
                    }   
            }    
            else
                System.out.println("Invalid patient order.");
                        
        } while ( ON != 0);
        Pause();
    }

   
     public static void enterResult() {
        
        String CurrOrders = ViewPatientOrders(1,2);
        if (CurrOrders.equals(""))return;
        
        Scanner scanner = new Scanner(System.in);
        int ON; 
        Double Result, NLow=0d, NHigh=0d;
        
        do {
            System.out.print("\nEnter the order number for resulting ( 0 to exit ) : ");
            ON = scanner.nextInt();
            if (ON==0) break;
            if (CurrOrders.contains(ON+"L")){
                for (LabOrders LO : LOrders)
                    if (LO.OrderNumber == ON){
                        for (LabTest TempTest:Tests)
                            if (TempTest.Code.equals(LO.MedCode)){
                                NLow = TempTest.normalLow;
                                NHigh = TempTest.normalHigh;
                                break;
                            }
                        System.out.print("\nResult for "+LO.MedCode+" Normal ramge  "+NLow+" - "+NHigh);
                        System.out.print("\nEnter Result : ");
                        Result = scanner.nextDouble();
                        if (Result >= NLow && Result <= NHigh){
                            System.out.println( "Nomral Result");
                            LO.ResultFlag = "Normal";
                        }
                        else if (Result > NHigh) {
                            System.out.println( "Abnormal High Result");
                            LO.ResultFlag = "Abnormal High";
                        }
                        else if (Result < NLow) {
                            System.out.println( "Abnomral Low Result");
                            LO.ResultFlag = "Abnormal Low";
                        }
                        LO.Result = Result;
                        LO.OrderStatus = "Completed";
                        LO.ResultTech = CurrentUser.getID();
                        
                        Calendar cal = Calendar.getInstance();
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        LO.ResultDateTime = (date.format(cal.getTime()));
                                                
                        System.out.println("\nOrder:  "+ON+"  "+LO.MedCode +"  has been resulted");
                        ON=0;
                        break;
                    }      
            }       
            else
                System.out.println("Invalid patient order.");
        } while ( ON != 0);
        Pause();

        
    }
 
    public static void issueMedication() {
	
        String CurrOrders = ViewPatientOrders(2,1);  // 2 = pharm orders  1 = pending
        if (CurrOrders.equals(""))return;
        
        Scanner scanner = new Scanner(System.in);
        int ON;
        do {
            System.out.print("\nEnter the order number to issue ( 0 to exit ) : ");
            ON = scanner.nextInt();
            if (ON==0) break;
            if(CurrOrders.contains(ON+"P")){
                for (PharmOrders PO : PhOrders)
                    if (PO.OrderNumber == ON){
                        PO.OrderStatus = "Issued";
                        System.out.println("\nOrder:  "+ON+"  "+PO.MedCode +"  has been Issued.");
                        PO.PharmTech=CurrentUser.getID();
                        
                        Calendar cal = Calendar.getInstance();
                        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        PO.DispenseDateTime = date.format(cal.getTime());
                       
                        ON=0;
                        break;
                    }   
            }    
            else
                System.out.println("Invalid patient order.");
                        
        } while ( ON != 0);
        Pause();
    }
        
    public static void activateUser() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        int userID = input.nextInt();
        for (User user : Users) {
            if (user.getID() == userID) {
                if (user.getActive()) {
                    System.out.println("\t\tUser with ID " + userID + " is already active.");
                    Pause();
                } else {
                    user.setActive(true);
                    System.out.println("\t\tUser with ID " + userID + " has been activated.");
                    Pause();
                }
                return;
            }
        }
        System.out.println("\t\tUser with ID " + userID + " does not exist.");
        Pause();
    }
    
    public static void deactivateUser() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter User ID:");
        int userID = input.nextInt();
        for (User user : Users) {
            if (user.getID() == userID) {
                if (!user.getActive()) {
                    System.out.println("\t\tUser with ID " + userID + " is already inactive.");
                    Pause();
                } else {
                    user.setActive(false);
                    System.out.println("\t\tUser with ID " + userID + " has been deactivated.");
                    Pause();
                }
                return;  
            }
        }
        System.out.println("\t\tUser with ID " + userID + " does not exist.");
    }
     
    static void CollectBlood() {
        String CurrOrders = ViewPatientOrders(1,1);  // 1 =lab  1 = pending
        if (CurrOrders.equals(""))return;
        
        Scanner scanner = new Scanner(System.in);
        int ON;
        do {
            System.out.print("\nEnter the order number to cancel ( 0 to exit ) : ");
            ON = scanner.nextInt();
            if (ON==0) break;
            if (CurrOrders.contains(ON+"L")){
                for (LabOrders LO : LOrders)
                    if (LO.OrderNumber == ON){
                        LO.OrderStatus = "Collected";
                        System.out.println("\nOrder:  "+ON+"  "+LO.MedCode +"  has been collected.");
                        ON=0;
                        break;
                    }      
            }       
            else
                System.out.println("Invalid patient order.");
        } while ( ON != 0);
        Pause();
    }

    public static void viewResults() {
        Patient tempPat = new Patient();
        String ordName="", TRange="";
        
        if (!ReadMRN(tempPat)) return;
        
        System.out.println("Order#  Code    Order Name            Ord.By  Date/Time            Result  Rslt Flag      Normal Range   By");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
                    
        for (LabOrders x: LOrders){
            if (x.MRN == tempPat.MRN && x.OrderStatus.equals("Completed") ){
                for (LabTest y: Tests){
                    if (y.Code.equals(x.MedCode)){ 
                        ordName = y.Name;
                        TRange = "("+y.normalLow+" - "+y.normalHigh+")";
                        break;
                    }
                }    
                
                System.out.printf("%-5d",x.OrderNumber);
                System.out.printf("   %-6S",x.MedCode);
                System.out.printf("  %-20.20S",ordName);
                System.out.printf("  %-6S",x.PhysicianID);
                System.out.printf("  %-15S",x.OrderDateTime);
                System.out.printf("  %6.1f",x.Result);
                System.out.printf("  %-13.13s",x.ResultFlag);
                System.out.printf("  %-15.15s",TRange);
                System.out.printf("%-4d",x.ResultTech);
                System.out.println();
            }    
        }    
        
        if (ordName.equals(""))
            System.out.println("Patient does not have completed results.");
        System.out.println();
        Pause();
    }
    
    public static void ViewMedications(){
        Patient tempPat = new Patient();
        String ordName="";
        
        if (!ReadMRN(tempPat)) return;
        
        System.out.println("Order#  Code    Order Name            Ord.By  Date/Time            Days   Frequency    By");
        System.out.println("-------------------------------------------------------------------------------------------");
                    
        for (PharmOrders x : PhOrders){
            if (x.MRN == tempPat.MRN && x.OrderStatus.equals("Issued") ){
                for (Medication y: Meds){
                    if (y.Code.equals(x.MedCode)){ 
                        ordName = y.Name;
                        break;
                    }
                }    
                System.out.printf("%-5d",x.OrderNumber);
                System.out.printf("   %-6S",x.MedCode);
                System.out.printf("  %-20.20S",ordName);
                System.out.printf("  %-6S",x.PhysicianID);
                System.out.printf("  %-15S",x.OrderDateTime);
                System.out.printf("  %3d",x.Days);
                System.out.printf("  %10.10s",x.Frequency);
                System.out.printf("     %-4d",x.PharmTech);
                System.out.println();
                System.out.println("\t\t"+x.Medinstructions);
            }    
        }    
        
        if (ordName.equals(""))
            System.out.println("Patient does not have completed results.");

        Pause();  
    }

    public static void PlaceLabOrder(){
        Patient tempPat = new Patient();
        Scanner input = new Scanner(System.in);

        if (!ReadMRN(tempPat)) return;
        
        String Temp;
        LabTest tempTest = new LabTest();
         do{
            System.out.print("\nEnter lab test code ( 0 to exit ) : ");
            Temp = input.next().toUpperCase();
            if (Temp.equals("0")) return;
            tempTest.Code = Temp;
            if (!ValidLabTest(tempTest))
                System.out.println("            Invalid Lab Test entered.");
            else
                break;
        }while (true);
         
        System.out.println("    Test Name : "+tempTest.Name);
        System.out.print("Please confirm placing order (Y/N) : ");
        Temp = input.next().toUpperCase();
        if (Temp.equals("Y")){
            LabOrders labOrder = new LabOrders();
            labOrder.MRN = tempPat.MRN;
            labOrder.PhysicianID= CurrentUser.getID();
            labOrder.OrderNumber=NextOrderNo++;
            labOrder.MedCode=tempTest.Code;
            labOrder.OrderStatus ="Pending";

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            labOrder.OrderDateTime = date.format(cal.getTime());
            
            LOrders.add(labOrder);
            System.out.println("\tOrder has been placed, Assigned Order Number : "+ labOrder.OrderNumber);
        }
        else
            System.out.println("\tOrder entry has been cancelled.");
        
        Pause();
    }      

    public static void PlacePharmOrder(){
        Patient tempPat = new Patient();
        Scanner input = new Scanner(System.in);

        if (!ReadMRN(tempPat)) return;
        
        String Temp;
        Medication tempMed = new Medication();
         do{
            System.out.print("\nEnter Medication code ( 0 to exit ) : ");
            Temp = input.next().toUpperCase();
            if (Temp.equals("0")) return;
            tempMed.Code = Temp;
            if (!ValidMed(tempMed))
                System.out.println("            Invalid Medication entered.");
            else
                break;
        }while (true);
         
        System.out.println("    Medication Name : "+tempMed.Name);
        
        System.out.print("Enter # of days    : ");
        int DaysN = input.nextInt();
        input.nextLine();
        System.out.print("Enter times/day    : ");
        String freq = input.nextLine();
        System.out.print("Enter instructions : ");
        String instrs = input.nextLine();
        System.out.print("Please confirm placing order (Y/N) : ");
        Temp = input.next().toUpperCase();
        if (Temp.equals("Y")){
            PharmOrders pharmOrder = new PharmOrders();
            pharmOrder.MRN = tempPat.MRN;
            pharmOrder.PhysicianID= CurrentUser.getID();
            pharmOrder.OrderNumber=NextOrderNo++;
            pharmOrder.MedCode=tempMed.Code;
            pharmOrder.OrderStatus ="Pending";
            pharmOrder.Frequency=freq;
            pharmOrder.Days=DaysN;
            pharmOrder.Medinstructions=instrs;

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pharmOrder.OrderDateTime = date.format(cal.getTime());
            
            PhOrders.add(pharmOrder);
            System.out.println("\tOrder has been placed, Assigned Order Number : "+ pharmOrder.OrderNumber);
        }
        else
            System.out.println("\tOrder entry has been cancelled.");  
        
        Pause();
    }

    static void NewTest(){
        Scanner input = new Scanner(System.in);
        boolean found = false;
        System.out.print("Enter the Test Code: ");
        String code = input.nextLine().toUpperCase();
        for(LabTest test : Tests){
            if(test.Code.equals(code)){
                System.out.print("This test already exist");
                found = true;
                break;
            }
        }
        
        if(!found){
            LabTest TempTest = new LabTest();
            TempTest.Code = code;
            System.out.print("Enter The Test name: ");
            TempTest.Name = input.nextLine();
            System.out.print("Enter normal range - HIGH value : ");
            TempTest.normalHigh = input.nextDouble();
            System.out.print("Enter normal range - LOW value : ");
            double L = input.nextDouble();
            TempTest.normalLow = L;
            input.nextLine();
            System.out.print("Enter The Result Unit: ");
            TempTest.resultUnit= input.nextLine();
                      
            Tests.add(TempTest);
            System.out.println("\nLab Test " + TempTest.Code + "was added successfuly");
            Pause();
        }
    }

    static int UserMenu() throws IOException{
        BuildMenuOptions();
        
        boolean found = false;
        Scanner input = new Scanner(System.in);
        String MyOption = "";
        
        System.out.println("\nPysician Order Entry System");
        System.out.println("------------------------------------");
        System.out.println("UserName: " + CurrentUser.getName() + " | " + CurrentUser.getRole());
        System.out.println("------------------------------------");
        
        int counter = 0;
        int UserMenuLength = CurrentUser.getUserMenuItems().length;
        int POEOptionsLength = POEOptions.length; 
        for(int i=0; i< UserMenuLength; i++){
            for (int j = 0; j<POEOptionsLength; j++){
                if(POEOptions[j][0].equals(CurrentUser.getUserMenuItems()[i])){
                    ActualMenu[counter][0] = POEOptions[j][0];
                    ActualMenu[counter][1] = POEOptions[j][1];
                    counter++;
                    break;
                }
            }
        }
        ActualMenu[counter][0] = "Q";
        ActualMenu[counter][1] = " Quit";
        int ActualMenuLength = ActualMenu.length;
        for(int i=0; i<ActualMenuLength; i++){
            if (! ActualMenu[i][0].equals(""))
                System.out.println(ActualMenu[i][0] + ": "+ ActualMenu[i][1]);
        }
        
        while(!found){
            System.out.print("Choose from the list: ");
            MyOption = input.nextLine();
            MyOption = MyOption.trim();
            MyOption = MyOption.toUpperCase();
            for(int i=0; i<ActualMenuLength;i++){
                if(ActualMenu[i][0].equals(MyOption.toUpperCase())){
                    found = true;
                    break;
                }
            }
            if(!found) System.out.println("Invalid Entry");
            if(MyOption.equals("Q"))return 0;
        }
        
                
        switch (MyOption) {
            case "UN": addUser(); break;
            case "UA": activateUser(); break;
            case "UD": deactivateUser(); break;
            case "NM": addMedication(); break;
            case "NL": NewTest(); break;
            case "OL": PlaceLabOrder(); break;
            case "OP": PlacePharmOrder(); break;
            case "CO": cancelOrder(); break;
            case "VO": ViewPatientOrders(0,0); Pause(); break;
            case "VR": viewResults(); break;
            case "PR": ViewOrders(1,2); Pause(); break;  //collected lab
            case "PC": ViewOrders(1,1); Pause(); break;  //pending lab for collection
            case "CB": CollectBlood(); break;
            case "RE": enterResult(); break;
            case "PO": ViewOrders(2,1); Pause(); break;  // pharm pending
            case "IM": issueMedication(); break;
            case "PM": ViewMedications(); break;      
            case "Q":  System.out.println("Quit Selected"); return 0; 
            default: System.out.println("Invalid selection"); 
        }
        return 1;
    }
        
    public static void main(String[] args) throws IOException {    
        ReadingFiles();

        int TheUser = login();
        if(TheUser == 1){
             do{} while(UserMenu() != 0);
        WritingtoFiles();
    }
}
}