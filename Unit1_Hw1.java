import java.util.Scanner;

class Main{
    public static void main(String[] args){
        
        //scanner import
        Scanner wack = new Scanner(System.in);
        
        // credit hour value
        double cost = 120.25;
        double fees = 35.00;
        
        // user input section
        System.out.print("Enter the students ID: ");
        String ID = wack.nextLine();
        
        System.out.print("Enter the students full name: ");
        String Name = wack.nextLine();
        
        System.out.println("      ");

        
        System.out.print("Enter crn/credit hours for the first class (like 5665/3): ");
        String ClassOne = wack.nextLine();
        
        System.out.print("Enter crn/credit hours for the first class (like 5665/3): ");
        String ClassTwo = wack.nextLine();
        
        System.out.println("      ");

        
        System.out.println("Thank you!");
        System.out.println("Fee invoice prepared for " + Name + "\n");
        
        System.out.println("      ");
        System.out.println("      ");

        //output section
        System.out.println("        SIMPLE COLLEGE");
        System.out.println("        Orlando FL 10101");
        System.out.println("        *************************"+"\n");
        System.out.println("        Fee Invoice Prepared for:");
        System.out.println("        ["+Name+"]"+"["+ID+"]"+"\n");
        System.out.println("        1 credit hour + $120.25"+"\n");
        System.out.println("        CRN            Credit Hours");
        
        //Class one CRN and credit hours
        String inputOne = ClassOne;
        String[] partsOne = inputOne.split("/");
        int CRNOne = Integer.parseInt(partsOne[0]);
        int creditHoursOne =Integer.parseInt(partsOne[1]);
        
        double costone = creditHoursOne * cost;
        
        //class two CRN and credit hours
        String inputTwo = ClassTwo;
        String[] partsTwo = inputTwo.split("/");
        int CRNTwo = Integer.parseInt(partsTwo[0]);
        int creditHoursTwo =Integer.parseInt(partsTwo[1]);
        
        double costtwo = creditHoursTwo * cost;
        
        // host per out thingy
        System.out.print("        " + CRNOne);
        System.out.print("           ");
        System.out.print(creditHoursOne);
        System.out.print("             $");
        System.out.println(costone);
        
        System.out.print("        " + CRNTwo);
        System.out.print("           ");
        System.out.print(creditHoursTwo);
        System.out.print("             $");
        System.out.println(costtwo);
        
        // fees added/ total created
        System.out.println("              ");
        System.out.println("                Health & id fees     $" + fees + "\t");
        System.out.println("              ");
        System.out.println("        -------------------------------------");
        
        Double Total = fees + costone + costtwo;
        
        System.out.println("                Total payments       $" + Total);
        
        
    }
}