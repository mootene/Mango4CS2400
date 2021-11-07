import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.function.Executable;

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
      assertEquals(25, heap1.getCapacity());
      assertEquals(26, heap2.getCapacity());
   }

   @Test
   void testCheckInitialization()
   {
      MaxHeap<Integer> heap1 = new MaxHeap<>();
      heap1.checkInitialization();
   }

   @Test
   void testCapacityLimit()
   {
      // Should not throw an exception, because 2000 is within the allowed limit
      MaxHeap<Integer> heap1 = new MaxHeap<>();
      for (int i = 0; i < 2000; i++)
      {
            heap1.addEntry(i);
      }

      // Should throw an exception, because 20000 is outside the allowed limit
      assertThrows(IndexOutOfBoundsException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable 
                    {
                        MaxHeap<Integer> heap1 = new MaxHeap<>();
                        for (int i = 0; i < 20000; i++)
                        {
                              heap1.addEntry(i);
                        }
                    }
                });
   }

   @Test 
   void testGetCapacity()
   {
      MaxHeap<Integer> heap1 = new MaxHeap<>();
      MaxHeap<Integer> heap2 = new MaxHeap<>(32);
      
      assertEquals(32, heap2.getCapacity());
      assertEquals(25, heap1.getCapacity());
   }

   @Test
   void testGetMax()
   {
      MaxHeap<Integer> heap1 = new MaxHeap<>();
      heap1.addEntry(17);
      heap1.addEntry(28);
      heap1.addEntry(3);

      assertEquals(28, heap1.getMax());
   }

   @Test
   void testIsEmpty()
   {
      MaxHeap<Integer> heap1 = new MaxHeap<>();

      assertTrue(heap1.isEmpty());

      heap1.addEntry(17);

      assertFalse(heap1.isEmpty());
   }

   @Test
   void testGetSize()
   {
      MaxHeap<Integer> heap1 = new MaxHeap<>();
      heap1.addEntry(17);
      heap1.addEntry(28);
      heap1.addEntry(3);
      MaxHeap<Integer> heap2 = new MaxHeap<>();

      assertEquals(3, heap1.getSize());
      assertEquals(0, heap2.getSize());
   }

   @Test
   void testClear()
   {
      MaxHeap<Integer> heap1 = new MaxHeap<>();
      heap1.addEntry(17);
      heap1.addEntry(28);
      heap1.addEntry(3);
      heap1.clear();

      assertEquals(0, heap1.getSize());

   }

   @Test
   void testRemoveMax()
   {
      MaxHeap<Integer> heap1 = new MaxHeap<>();
      for (int i = 1; i < 26; i ++)
      {
         heap1.addEntry(i);
      }

      assertEquals(25, heap1.removeMax());
      assertEquals(24, heap1.removeMax());

      heap1.addEntry(49);

      assertEquals(49, heap1.removeMax());
   }

   @Test
   void testDumbCreate()
   {
      Integer heapEntries[] = {1, 37, 5, 200, 515};
      MaxHeap<Integer> heap1 = new MaxHeap<>();
      heap1.dumbCreate(heapEntries);

      assertFalse(heap1.isEmpty());
      assertEquals(515, heap1.getMax());
      assertEquals(5, heap1.getSize());
      assertEquals(25, heap1.getCapacity());
      assertEquals(515, heap1.removeMax());
      assertEquals(200, heap1.removeMax());
      assertEquals(37, heap1.removeMax());
      assertEquals(5, heap1.removeMax());
      assertEquals(1, heap1.removeMax());
   }

   @Test
   void testSmartCreate()
   {
      Integer heapEntries[] = {1, 37, 5, 200, 515};
      MaxHeap<Integer> heap1 = new MaxHeap<>();
      heap1.smartCreate(heapEntries);

      assertFalse(heap1.isEmpty());
      assertEquals(515, heap1.getMax());
      assertEquals(5, heap1.getSize());
      assertEquals(25, heap1.getCapacity());
      assertEquals(515, heap1.removeMax());
      assertEquals(200, heap1.removeMax());
      assertEquals(37, heap1.removeMax());
      assertEquals(5, heap1.removeMax());
      assertEquals(1, heap1.removeMax());
   }

   @Test
   void testAddEntry()
   {
      MaxHeap<Integer> heap1 = new MaxHeap<>();
      heap1.addEntry(3);

      assertFalse(heap1.isEmpty());

      heap1.addEntry(15);

      assertEquals(15, heap1.getMax());

      heap1.addEntry(1);

      assertEquals(25, heap1.getCapacity());
      assertEquals(3, heap1.getSize());
   }
   public static void main(String args[])
   {

   } 
}