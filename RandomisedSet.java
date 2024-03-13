import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * For this problem,
 * we use dynamic array.
 * 
 * But if the constraint is n, we can use primitive array as well.
 */

class RandomizedSet {
    // hash map
    private Map<Integer, Integer> map;
    // dynamic array. for the tutorial problem, we can use primitive array as well
    // random generator
    private ArrayList<Integer> array;
    // random generator
    private Random random;
    // current size of the array
    // the actually occupied size of the array
    private int currentSize;

    public RandomizedSet() {
        map = new HashMap<>();
        array = new ArrayList<>();
        random = new Random();
        currentSize = 0;
    }

    public boolean insert(int val) {
        // check if already exists
        if (map.containsKey(val)) {
            return false;
        }
        // add the value to the map
        map.put(val, currentSize);

        // add the value to the array
        array.add(val);

        // increment the size (this will be useful later)
        currentSize++;

        return true;
    }

    public boolean remove(int val) {
        // check if value already exists
        if (!map.containsKey(val)) {
            return false;
        }
        // get the index of the value
        int index = map.get(val);

        // swap the value with the last value in the array (why !!??)
        int lastValue = array.get(currentSize - 1);
        array.set(index, lastValue);

        // update the index of the last value in the map
        map.put(lastValue, index);

        // remove the last value from the array
        array.remove(currentSize - 1);

        // remove the value from the map
        map.remove(val);

        // decrement the size
        currentSize--;

        return true;
    }

    public int getRandom() {
        return array.get(random.nextInt(currentSize));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */