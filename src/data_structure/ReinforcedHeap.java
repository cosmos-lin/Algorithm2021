package data_structure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ReinforcedHeap<T> {
    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comp;

    public ReinforcedHeap(Comparator<? super T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize-1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    // O(logN)
    public void remove(T obj) {
        // 拿到heap中最后一个位置上的数
        T replace = heap.get(heapSize - 1);
        //获取移除对象的索引
        int index = indexMap.get(obj);
        // 索引map中移除该对象
        indexMap.remove(obj);
        // 移除最后一个对象
        heap.remove(--heapSize);
        // 移除对象与最大位置的对象不相等： 说明移除的不是最后一个对象；堆结构进行调整
        if (replace != obj) {
            // 用最后位置的对象替换index位置上的数
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    // O(N): 任何调整只进行其中一个
    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    private void heapify(int index) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && comp.compare(heap.get(left+1), heap.get(left)) < 0 ? left + 1 : left;
            if (largest == index) {
                break;
            }
            swap(index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private void heapInsert(int index) {

        while (comp.compare(heap.get(index), heap.get((index-1)/2)) < 0) {
            swap(index, (index-1) / 2);
            index = (index-1) / 2;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }
}
