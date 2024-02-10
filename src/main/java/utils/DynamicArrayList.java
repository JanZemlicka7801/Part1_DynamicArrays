package utils;

import java.util.Arrays;

/**
 *
 * @author jan_zemlicka
 */
public class DynamicArrayList {
    // The default capacity of the array.
    private static final int DEFAULT = 10;
    // The current size of an array.
    private int size;
    // An array to store elements.
    private String[] array;

    /**
     * Constructs an empty DynamicArrayList with the default capacity.
     */
    public DynamicArrayList() {
        this.array = new String[DEFAULT];
        //Number of Strings in the DynamicArrayList.
        this.size = 0;
    }

    /**
     * Returns the current size of the DynamicArrayList.
     *
     * @return The current size of the DynamicArrayList.
     */
    public int size() {
        return this.size;
    }

    /**
     * Retrieves the element at the specified position in the DynamicArrayList.
     *
     * @param position The index of the element to retrieve.
     * @return The element at the specified position.
     * @throws IndexOutOfBoundsException if the specified position is out of bounds.
     */
    public String get(int position) {
        //Position needs to be bigger than 0 and less than the size(tracker).
        if (position >= 0 && position < this.size) {
            return array[position];
        }
        throw new IndexOutOfBoundsException("Index is out of bound");
    }

    /**
     * Finds the index of the first occurrence of the specified element in the DynamicArrayList.
     *
     * @param parameter The element to search for.
     * @return The index of the first occurrence of the specified element, or -1 if not found.
     */
    public int indexOf(String parameter) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(parameter)) {
                return i;
            }
        }
        // Returns -1 if the element is not found.
        return -1;
    }

    /**
     * Adds the specified element to the end of the DynamicArrayList.
     * If the array is full, it is extended to double its current capacity.
     *
     * @param toAdd The element to be added.
     * @return true if the element is successfully added.
     */
    // Method to add an element to the DynamicArrayList.
    public boolean add(String toAdd) {
        if (size == array.length) {
            //If the array is full, extend its capacity by doubling it.
            int extend = array.length * 2;
            String[] newArray = new String[extend];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        //Add the element to the DynamicArrayList at the current size index and increment the size.
        array[size++] = toAdd;
        return true;
    }


    /**
     * Constructs an empty DynamicArrayList with the specified capacity.
     *
     * @param capacity The initial capacity of the DynamicArrayList.
     * @throws IllegalArgumentException if the specified capacity is less than or equal to 0.
     */
    public DynamicArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity needs to be greater than 0.");
        }
        this.array = new String[capacity];
        this.size = 0;
    }

    /**
     * Adds the specified element at the specified position in the DynamicArrayList.
     * If the array is full, it is extended to double its current capacity.
     *
     * @param parameter The element to be added.
     * @param pos       The index at which the element is to be added.
     * @return true if the element is successfully added.
     * @throws IndexOutOfBoundsException if the specified position is out of bounds.
     */
    public boolean add(String parameter, int pos) {
        if (pos > size || pos < 0) {
            throw new IndexOutOfBoundsException("Index is out of bound");
        }
        if (size == array.length) {
            //If the array is full, extend its capacity by doubling it.
            int extend = array.length * 2;
            String[] newArray = new String[extend];
            System.arraycopy(array, 0, newArray, 0, pos);
            //Copy remaining elements from the original array to the new array, starting after the specified position.
            System.arraycopy(array, pos, newArray, pos + 1, size - pos);
            array = newArray;
        }
        //Shift existing elements to make space for the new element.
        for (int i = size; i > pos; i--) {
            array[i] = array[i - 1];
        }
        array[pos] = parameter;
        size++;
        return true;
    }

    /**
     * Constructs a DynamicArrayList from the specified array of elements.
     *
     * @param passedArray The array of elements to be used for construction.
     * @throws IllegalArgumentException if the specified array is null.
     */
    public DynamicArrayList(String[] passedArray) {
        if (passedArray == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        this.array = new String[passedArray.length];
        for (int i = 0; i < passedArray.length; i++) {
            this.array[i] = passedArray[i];
        }

        this.size = passedArray.length;
    }

    /**
     * Checks if the DynamicArrayList is empty.
     *
     * @return true if the DynamicArrayList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Removes all occurrences of the specified element from the DynamicArrayList.
     *
     * @param element The element to be removed.
     * @return true if at least one occurrence of the element is removed.
     * @throws IllegalArgumentException if the specified element is null.
     */
    public boolean removeAll(String element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot remove null");
        }
        boolean removed = false;

        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                remove(i);
                removed = true;
                i--;
            }
        }

        return removed;
    }

    /**
     * Removes the element at the specified index from the DynamicArrayList.
     *
     * @param index The index of the element to be removed.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        // Use System.arraycopy to shift elements to the left, removing the element at the specified index.
        // Shift elements starting from index + 1 to index, covering the elements after the removed one.
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        // Set the last element to null and decrement the size.
        array[--size] = null;
    }

    /**
     * Removes the element at the specified index if it matches the specified text.
     *
     * @param text  The text to compare against.
     * @param index The index from which to start searching.
     * @return true if the element is successfully removed.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     * @throws IllegalArgumentException  if the specified text is null.
     */
    public boolean remove(String text, int index) {
        boolean removed = false;

        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid starting position");
        } else if (text == null) {
            throw new IllegalArgumentException("Invalid passed argument");
        }

        for (int i = index; i < this.size; i++) {
            if (this.array[i].equals(text)) {
                //If found, remove the element at the current index.
                remove(i);
                removed = true;
                return removed;
            }
        }
        return removed;
    }

    /**
     * Removes the first occurrence of the specified text from the DynamicArrayList.
     *
     * @param text The text to be removed.
     * @return true if the text is successfully removed.
     * @throws IllegalArgumentException if the specified text is null.
     */
    public boolean remove(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Invalid parameter cannot be null");
        }

        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(text)) {
                //If found, remove the element at the current index.
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds all the elements from the specified array to the end of the DynamicArrayList.
     * If the array is full, it is extended to accommodate the new elements.
     *
     * @param data The array of elements to be added.
     * @return true if the elements are successfully added.
     */
    public boolean addAll(String[] data) {
        if (data == null || data.length == 0) {
            return false;
        }
        // Check if adding the elements would exceed the current array capacity.
        if (data.length + size >= this.array.length) {
            int extend = this.array.length + data.length;
            // Create a new array with the extended capacity.
            String[] newArray = new String[extend];
            // Copy the existing elements from the original array to the new array.
            System.arraycopy(this.array, 0, newArray, 0, this.size);
            // Copy the elements from the specified array to the end of the new array.
            System.arraycopy(data, 0, newArray, this.size, data.length);
            this.array = newArray;
        } else {
            // If not exceeding the capacity, copy the elements from the specified array to the end of the original array.
            System.arraycopy(data, 0, this.array, this.size, data.length);
        }
        this.size += data.length;
        return true;
    }

    /**
     * Sets the element at the specified index to the specified element.
     *
     * @param index   The index of the element to be set.
     * @param element The element to set at the specified index.
     * @return The previous value at the specified index.
     * @throws IllegalArgumentException  if the specified element is null.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    public String set(int index, String element) {
        if (element == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        } else if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index is out of bound.");
        }
        String old = this.array[index];
        this.array[index - 1] = element;
        return old;
    }

    /**
     * Counts the occurrences of the specified element in the DynamicArrayList.
     *
     * @param element The element to count.
     * @param flag    If true, performs case-insensitive comparison.
     * @return The count of occurrences of the specified element.
     */
    public int count(String element, boolean flag) {
        if (this.size == 0) {
            return 0;
        }
        int count = 0;
        // Iterate through the elements of the DynamicArrayList.
        for (int i = 0; i < size; i++) {
            // Check the flag to determine whether the comparison is case-insensitive.
            if (flag) {
                // If case-insensitive, compare ignoring case.
                if (element.equalsIgnoreCase(array[i])) {
                    // Increment the count if the elements match.
                    count++;
                }
            } else {
                // If case-sensitive, compare with case sensitivity.
                if (element.equals(array[i])) {
                    // Increment the count if the elements match.
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Clears all elements from the DynamicArrayList.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Creates a shallow copy of the DynamicArrayList.
     *
     * @return A new DynamicArrayList containing the same elements.
     */
    public DynamicArrayList clone() {
        DynamicArrayList clone = new DynamicArrayList();
        clone.array = new String[this.size];

        for (int i = 0; i < this.size; i++) {
            clone.array[i] = this.array[i];
        }

        // Set the size of the clone to match the size of the original DynamicArrayList.
        clone.size = this.size;
        return clone;
    }

    /**
     * Finds the index of the last occurrence of the specified text in the DynamicArrayList.
     *
     * @param text The text to search for.
     * @return The index of the last occurrence of the specified text, or -1 if not found.
     * @throws IllegalArgumentException if the specified text is null.
     */
    public int lastIndexOf(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Cannot be null");
        }

        //Iterate backward through the array starting from the last element.
        for (int i = size - 1; i >= 0; i--) {
            //Check if the current element is null.
            if (this.array[i] == null) {
                //Do nothing if the element is null.
            } else if (this.array[i].equals(text)) {
                //If the current element equals the specified text, return its index.
                return i;
            }
        }
        return -1;
    }
}

