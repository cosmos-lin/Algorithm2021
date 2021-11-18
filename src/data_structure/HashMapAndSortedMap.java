package data_structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class HashMapAndSortedMap {
    
    public static class Node {
        public int value;
        
        public Node(int value) {
            this.value = value;
        }
    }

    public static class Help {
        public int value;
        
        public Help(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        HashMap<Integer, String> test = new HashMap<>();

        int x1 = 100;
        int x2 = 100;
        System.out.println(x1 == x2);  // true 比较值
        Integer a = 19000000;
        Integer b = 19000000;
        System.out.println(a == b); // false 比较内存地址
        System.out.println(a.equals(b));  // true 比较两个对象是否相等

        test.put(a, "1900000");
        System.out.println(test.containsKey(b)); // true

        HashMap<Help, String> test2 = new HashMap<>();
        Help h1 = new Help(1);
        Help h2 = new Help(1);
        test2.put(h1, "h1");
        System.out.println(test2.containsKey(h2)); // false 引用类型， h1 和h2指向不同对象


        HashSet<String> set = new HashSet<>();
        set.add("abd");

        // 哈希表， 增、删、改、查， 都为O(1)
        System.out.println("==========有序表===========");
        TreeMap<Integer, String> treeMap = new TreeMap<>();

		treeMap.put(3, "我是3");
		treeMap.put(4, "我是4");
		treeMap.put(8, "我是8");
		treeMap.put(5, "我是5");
		treeMap.put(7, "我是7");
		treeMap.put(1, "我是1");
		treeMap.put(2, "我是2");

		System.out.println(treeMap.containsKey(1));
		System.out.println(treeMap.containsKey(10));

		System.out.println(treeMap.get(4));
		System.out.println(treeMap.get(10));

		treeMap.put(4, "他是4");
		System.out.println(treeMap.get(4));

		// treeMap.remove(4);
		System.out.println(treeMap.get(4));

		System.out.println("新鲜：");

		System.out.println(treeMap.firstKey());
		System.out.println(treeMap.lastKey());
		// <= 4
		System.out.println(treeMap.floorKey(4));
		// >= 4
		System.out.println(treeMap.ceilingKey(4));
		// O(logN)
    }
}
