
public interface MaxHeapInterface<T extends Comparable<? super T>>
{
    public int addEntry(T newEntry);
    public T removeMax();
    public T getMax();
    public boolean isEmpty();
    public int getSize();
    public void clear();
}