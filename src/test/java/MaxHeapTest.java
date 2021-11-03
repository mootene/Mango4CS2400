import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MaxHeapTest
{
   public static void main(String args[])
   {
        Scanner inputScnr = new Scanner(System.in);
        System.out.println("Enter file name:");
        String fileName = inputScnr.nextLine();
        File inputFile = new File(fileName);
        Scanner fileScnr = new Scanner(inputFile);
   } 
}