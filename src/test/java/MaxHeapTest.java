import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class MaxHeapTest
{
   @Test
   void testConstructors()
   {
      MaxHeap<Integer> heap1 = new MaxHeap<>();
      MaxHeap<Integer> heap2 = new MaxHeap<>(26);

      assertEquals(0, heap1.getSize());
      assertEquals(heap1.getSize(), heap2.getSize());
   }
   public static void main(String args[])
   {
        Scanner inputScnr = new Scanner(System.in);
        System.out.println("Enter file name:");
        String fileName = inputScnr.nextLine();
        File inputFile = new File(fileName);
        Scanner fileScnr = new Scanner(inputFile);
   } 
}