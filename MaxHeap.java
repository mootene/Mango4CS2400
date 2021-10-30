public class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface
{
    private T[] heap;
    private int lastIndex;
    private boolean initialized - false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeap()
    {
        this(DEFAULT_CAPACITY);
    }

    public MaxHeap(int initialCapacity);
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
    }

    public void checkInitialization() 
    {
        
    }

    public void checkCapacity(int index)
    {

    }
    
    public T getMax()
    {
        checkInitialization();
        T root = null;
        if (!isEmpty())
            root = heap[1];
        return root;
    }

    public boolean isEmpty()
    {
        return lastIndex < 1;
    }

    public int getSize()
    {
        return lastIndex;
    }

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
        int numberOfSwaps = 0;
        if (!isEmpty())
        {
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            numberOfSwaps = reheap(1);
        }
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
        if (orphan.compareTo(heap[largerChildIndex]) < 0)
        {
            heap[rootIndex] = heap[largerChildIndex];
            heap[largerChildIndex] = orphan;
            rootIndex = largerChildIndex;
            numberOfSwaps++;
            reheap(largerChildIndex);
        }
        return numberOfSwaps;
    }


    public int addEntry(T newEntry)
    {
        lastIndex++;
        heap[lastIndex] = newEntry;
        return upheap(lastIndex);
    }
    /**
     * performs upheap of heap from given child index
     * @param index index of child element of interest
     * @return number of swaps performed by algorithm
     */
    private int upheap(int index)
    {
        int numberOfSwaps = 1;
        T child = heap[index];
        int parentIndex = index / 2;
        if ((parentIndex >= 1) && (heap[parentIndex].compareTo(heap[index]) < 0))
        {
            heap[index] = heap[parentIndex];
            heap[parentIndex] = child;
            numberOfSwaps++;
            reheap(parentIndex);
        }
        return numberOfSwaps;
    }
}