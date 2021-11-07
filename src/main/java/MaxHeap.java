import java.util.Arrays;

public class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>
{
    private T[] heap;
    private int lastIndex = 0;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeap()
    {
        this(DEFAULT_CAPACITY);
    }

    public MaxHeap(int initialCapacity)
    {
        if (initialCapacity < DEFAULT_CAPACITY)
        {
            initialCapacity = DEFAULT_CAPACITY;
        }
        else
        {
            checkCapacity(initialCapacity);
        }
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    }

    /**
     * checks boolean private field, throws IllegalStateException if false
     */
    public void checkInitialization()
    {
        if (!initialized)
            throw new IllegalStateException("Heap not initialized");
    }

    private void checkCapacity(int index) throws IndexOutOfBoundsException
    {
        if(index > MAX_CAPACITY)
            throw new IndexOutOfBoundsException("Requested capacity " + index + " exceeds MAX_CAPACITY of " + MAX_CAPACITY);
    }

    /**
     * Ensures capacity for next addition by doubling heap capacity 
     * if next last index is greater than capacity
     */
    private void ensureCapacity()
    {
        // If the next index we use will be out of bounds for the array, expand:
        if ((lastIndex + 1) > (heap.length - 1))
        {
            checkCapacity(2 * lastIndex);
            heap = Arrays.copyOf(heap, 2 * lastIndex + 1); 
        }
    }

    /**
     * retrieves current heap capacity
     * @return current heap capacity
     */
    public int getCapacity()
    {
        return (heap.length - 1);
    }

    /**
     * returns the max node in the heap (element in heap(1))
     */
    public T getMax()
    {
        checkInitialization();
        T root = null;
        if (!isEmpty())
            root = heap[1];
        return root;
    }

    /**
     * detects if heap has no nodes
     */
    public boolean isEmpty()
    {
        return lastIndex < 1;
    }

    /**
     * gets the size of filled heap array
     * @return lastIndex of the heap
     */
    public int getSize()
    {
        return lastIndex;
    }

    /**
     * empties the heap
     */
    public void clear()
    {
        checkInitialization();
        while(lastIndex > -1)
        {
            heap[lastIndex] = null;
            lastIndex--;
        }
        lastIndex = 0;
    }

    /**
     * removes entry at heap[1] (root index) and replaces it with last element (heap [lastIndex])
     * @return entry removed
     */
    public T removeMax()
    {
        T root = null;
        if (!isEmpty())
        {
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        }
        return root;
    }

    /**
     * recursive reheap of heap contents located below a given root index
     * @param rootIndex index of interest node
     */
    private int reheap(int rootIndex)
    {
        int numberOfSwaps = 1;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        int rightChildIndex = 2 * rootIndex + 1;
        int largerChildIndex = leftChildIndex;
        if ((rightChildIndex <= lastIndex) && (heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0))
            largerChildIndex = rightChildIndex;
        if ((largerChildIndex <= lastIndex) && orphan.compareTo(heap[largerChildIndex]) < 0)
        {
            heap[rootIndex] = heap[largerChildIndex];
            heap[largerChildIndex] = orphan;
            numberOfSwaps++;
            reheap(largerChildIndex);
        }
        return numberOfSwaps;
    }

    /**
     * creates a new heap using "dumb" method of recursive add/reheap calls 
     * from given array
     * @param entries array to be initilaized into this heap
     * @return number of swaps completed creating the heap
     */
    public int dumbCreate(T[] entries)
    {
        checkCapacity(entries.length+1);
        int swaps = 0;
        for (int i = 0; i < entries.length; i++)
        {
            swaps += addEntry(entries[i]);
        }
        return swaps;
    }

    /**
     * creates a new heap using "smart" method of reorganizing an unorganized 
     * copy of a given array
     * @param entries array to be initialized to this heap
     * @return number of swaps completed in creating the heap
     */
    public int smartCreate(T[] entries)
    {
        checkCapacity(entries.length + 1);
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[
            Math.max(DEFAULT_CAPACITY + 1, entries.length + 1)
            ];
        int swaps = 0;
        for (int i = 0; i < entries.length; i++)
        {
            tempHeap[i+1] = entries[i];
        }
        heap = tempHeap;
        lastIndex = entries.length;
        int leaves = (lastIndex + 1) / 2;
        int lastNonleafIndex = lastIndex - leaves;
        for (int i = lastNonleafIndex; i > 0; i--)
        {
            swaps += reheap(i);
        }
        return swaps; 
    }
    

    @Override
    /**
     * adds new entry to the end of the heap, ensures capacity for following add call
     * then performs upheap to reorganize this heap on new addition
     * @param newEntry entry to be added to the heap
     * @return number of swaps performed in adding/upheaping
     */
    public int addEntry(T newEntry)
    {
        heap[++lastIndex] = newEntry;
        ensureCapacity();
        return upheap(lastIndex);
    }

    /**
     * performs upheap of heap from given child index
     * @param index index of child element of interest
     * @return number of swaps performed by algorithm
     */
    private int upheap(int index)
    {
        int numberOfSwaps = 0;
        T child = heap[index];
        int parentIndex = index / 2;
        if ((parentIndex >= 1) && (heap[parentIndex].compareTo(heap[index]) < 0))
        {
            heap[index] = heap[parentIndex];
            heap[parentIndex] = child;
            numberOfSwaps = 1 + upheap(parentIndex);
        }
        return numberOfSwaps;
    }

}