import exceptions.ArrayIndexOutOfBoundsEx;
import exceptions.NoSuchElementEx;

import java.util.NoSuchElementException;

public class StringListImpl implements StringList {
    private String[] arrays;
    private final int initialCapacity = 10;
    private int size;

    public StringListImpl() {
        this.arrays = new String[initialCapacity];
        size = 0;
    }

    public StringListImpl(int capacity) {
        this.arrays = new String[capacity];
        size = 0;
    }

    @Override
    public String add(String item) {
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
    public String add(int index, String item) {
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
    public String set(int index, String item) {
        if (index >= 0 && index <= arrays.length) {
            arrays[index] = item;
        } else {
            throw new ArrayIndexOutOfBoundsEx("Индекс за пределами массива");
        }
        return item;
    }

    @Override
    public String remove(String item) {
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
    public String remove(int index) {
        if (index < 0 || index > arrays.length) {
            throw new NoSuchElementEx("Элемент отсутствует!");
        }
        String out = "";
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
    public boolean contains(String item) {
        boolean result = false;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == item) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public int indexOf(String item) {
        int result = -1;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == item) {
                result = i;
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(String item) {
        int result = -1;
        for (int i = arrays.length - 1; i >= 0; i--) {
            if (arrays[i] == item) {
                result = i;
            }
        }
        return result;
    }

    @Override
    public String get(int index) {
        if (index >= 0 && index <= arrays.length) {
            return arrays[index];
        } else {
            throw new ArrayIndexOutOfBoundsEx("Индекс за пределами массива");
        }
    }

    @Override
    public boolean equals(StringList otherList) {
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
        for (String s : arrays) {
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
    public String[] toArray() {
        String[] out = new String[size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = arrays[i];
        }
        return out;
    }

    private String[] copyArrayToShiftRight(int index, String[] in) {
        String[] out = new String[in.length + 2];
        for (int i = 0; i < arrays.length; i++) {
            if (i < index) {
                out[i] = arrays[i];
            } else {
                out[i + 1] = arrays[i];
            }
        }
        return out;
    }

    private String[] copyArrayToShiftLeft(int index, String[] in) {
        for (int i = index; i < in.length - 1; i++) {
            in[i] = in[i + 1];
            in[i + 1] = null;
        }
        return in;
    }

    private String[] upSizeArray(String[] in) {
        int newSize = in.length + in.length / 2;
        String[] out = new String[newSize];
        for (int i = 0; i < in.length; i++) {
            out[i] = in[i];
        }
        return out;
    }
}