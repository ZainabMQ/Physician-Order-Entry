
package com.mycompany.physicianorderentrysystem;
import static com.mycompany.physicianorderentrysystem.PhysicianOrderEntrySystem.*;
import java.util.Scanner;

public class Functions_2 {
    
    public static void Pause(){
        System.out.print("\nPress Enter to continue ....  ");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
    
    public static boolean ReadMRN(Patient tempPat){
        Scanner input = new Scanner(System.in);
        String MRN;
        do{
            System.out.print("Enter MRN ( 0 to exit ) : ");
            MRN = input.next();
            if (MRN.equals("0")) return false;
            tempPat.MRN = Integer.parseInt(MRN);
         for (Patient x : POEPatient){
            if (x.MRN == tempPat.MRN){
                tempPat.Name = x.Name;
                tempPat.Gender = x.Gender;
                tempPat.DateOfBirth= x.DateOfBirth;
                System.out.print("                               Name : "+tempPat.Name+"    DOB : "+tempPat.DateOfBirth);
                System.out.println("    Gender: "+tempPat.Gender);
                return true;
            }
        }
        System.out.println("            Invalid MRN entered.");
        }while (true);
    }
    
    public static boolean ValidLabTest(LabTest tempTest){
        for (LabTest x : Tests){
            if (x.Code.equals(tempTest.Code)){
                tempTest.Name = x.Name;
                return true;
            }
        }
        return false;
    }
    
    public static boolean ValidMed(Medication tempMed){
        for (Medication x : Meds){
            if (x.Code.equals(tempMed.Code)){
                tempMed.Name = x.Name;
                return true;
            }
        }
        return false;
    }
        
}
