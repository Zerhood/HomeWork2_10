package integerList;

import exceptions.ArrayIndexOutOfBoundsEx;
import exceptions.NoSuchElementEx;

public class IntegerListImpl implements IntegerList {
    private Integer[] arrays;
    private final int initialCapacity = 10;
    private int size;

    public IntegerListImpl() {
        this.arrays = new Integer[initialCapacity];
        size = 0;
    }

    public IntegerListImpl(int capacity) {
        this.arrays = new Integer[capacity];
        size = 0;
    }

    @Override
    public Integer add(Integer item) {
        if (arrays.length == size()) {
            arrays = upSizeArray(arrays);
        }
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == null) {
                arrays[i] = item;
                size++;
                break;
            }
        }
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index < 0 || index > arrays.length) {
            throw new ArrayIndexOutOfBoundsEx("Индекс за пределами массива");
        }
        if (arrays.length == size()) {
            arrays = upSizeArray(arrays);
        }
        if (index >= 0 && index <= arrays.length) {
            arrays = copyArrayToShiftRight(index, arrays);
            arrays[index] = item;
            size++;
        }
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index >= 0 && index <= arrays.length) {
            arrays[index] = item;
        } else {
            throw new ArrayIndexOutOfBoundsEx("Индекс за пределами массива");
        }
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int indexDeleteItem = -1;
        boolean isElement = true;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == item || arrays[i] != null) {
                indexDeleteItem = i;
                arrays[i] = null;
                size--;
                isElement = false;
            }
        }
        if (isElement) {
            throw new NoSuchElementEx("Элемент отсутствует!");
        }
        if (indexDeleteItem != -1) {
            arrays = copyArrayToShiftLeft(indexDeleteItem, arrays);
        }
        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index > arrays.length) {
            throw new NoSuchElementEx("Элемент отсутствует!");
        }
        Integer out = 0;
        int indexDeleteItem = -1;
        for (int i = 0; i < arrays.length; i++) {
            if (i == index && arrays[i] != null) {
                indexDeleteItem = index;
                out = arrays[i];
                arrays[i] = null;
                size--;
            }
        }
        if (indexDeleteItem != -1) {
            arrays = copyArrayToShiftLeft(index, arrays);
        }
        return out;
    }

    @Override
    public boolean contains(Integer item) {
        boolean result = false;
        int i = binarySearch(arrays, item);
        if (i != -1) {
            result = true;
        }
        return result;
    }

    @Override
    public int indexOf(Integer item) {
        int result = -1;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == item) {
                result = i;
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(Integer item) {
        int result = -1;
        for (int i = arrays.length - 1; i >= 0; i--) {
            if (arrays[i] == item) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer get(int index) {
        if (index >= 0 && index <= arrays.length) {
            return arrays[index];
        } else {
            throw new ArrayIndexOutOfBoundsEx("Индекс за пределами массива");
        }
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullPointerException();
        }
        boolean result = false;
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < otherList.size(); j++) {
                if (this.get(i) == otherList.get(j)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean result = true;
        for (Integer s : arrays) {
            if (s != null) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] out = new Integer[size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = arrays[i];
        }
        return out;
    }

    private Integer[] copyArrayToShiftRight(int index, Integer[] in) {
        Integer[] out = new Integer[in.length + 2];
        for (int i = 0; i < arrays.length; i++) {
            if (i < index) {
                out[i] = arrays[i];
            } else {
                out[i + 1] = arrays[i];
            }
        }
        return out;
    }

    private Integer[] copyArrayToShiftLeft(int index, Integer[] in) {
        for (int i = index; i < in.length - 1; i++) {
            in[i] = in[i + 1];
            in[i + 1] = null;
        }
        return in;
    }

    private Integer[] upSizeArray(Integer[] in) {
        int newSize = in.length + in.length / 2;
        Integer[] out = new Integer[newSize];
        for (int i = 0; i < in.length; i++) {
            out[i] = in[i];
        }
        return out;
    }

    private int binarySearch(Integer[] in, int i) {
        sortInsertion(in);
        int index = -1;
        int low = 0;
        int high = size();

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (in[mid] < i) {
                low = mid + 1;
            } else if (in[mid] > i) {
                high = mid - 1;
            } else if (in[mid] == i) {
                index = mid;
                break;
            }
        }
        return index;
    }

    private void sortInsertion(Integer[] arr) {
        for (int i = 1; i < size(); i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
