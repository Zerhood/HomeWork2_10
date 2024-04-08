package integerList;

import exceptions.ArrayIndexOutOfBoundsEx;
import exceptions.NoSuchElementEx;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerListImplTest {
    private IntegerListImpl out;
    private final Integer expected = 5;

    @BeforeEach
    public void setUp() {
        out = new IntegerListImpl();
    }

    @Test
    @DisplayName("добавление объекта в коллекцию")
    public void addTestNoParameter() {
        Integer actual = out.add(expected);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("добавление объекта в коллекцию по индексу")
    public void addTestAtParameter() {
        Integer actual = out.add(0, expected);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("добавляем элемент по индексу который выходит за пределы количества элементов")
    public void addTestThrow() {
        assertThrows(ArrayIndexOutOfBoundsEx.class, () -> out.add(-1, 5));
        assertThrows(ArrayIndexOutOfBoundsEx.class, () -> out.add(15, 5));
    }

    @Test
    @DisplayName("замена объекта в коллекции по индексу")
    public void setTestAtParameter() {
        out.add(expected);
        out.add(1);
        Integer actual = out.set(0, expected);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("замена объекта в коллекции по несуществующему индексу")
    public void setTestAtThrow() {
        assertThrows(ArrayIndexOutOfBoundsEx.class, () -> out.set(15, 5));
    }

    @Test
    @DisplayName("удаление объекта в коллекции по ссылке")
    public void removeTestAtObject() {
        out.add(expected);
        out.remove(expected);

        assertEquals(out.size(), 0);
    }

    @Test
    @DisplayName("удаление объекта в коллекции по индексу")
    public void removeTestAtIndex() {
        out.add(expected);
        out.remove(0);

        assertEquals(0, out.size());
    }

    @Test
    @DisplayName("удаление объекта с ошибкой")
    public void removeTestAtThrow() {
        assertThrows(NoSuchElementEx.class, () -> out.remove(-1));
        assertThrows(NoSuchElementEx.class, () -> out.remove(new Integer(5)));
    }

    @Test
    @DisplayName("проверка на существование объекта в коллекции")
    public void containsTest() {
        out.add(expected);
        boolean actual = out.contains(expected);

        assertTrue(actual);
    }

    @Test
    @DisplayName("поиск объекта в коллекции и возврат индекса")
    public void indexOfTest() {
        out.add(expected);
        int actual = out.indexOf(expected);

        assertEquals(0, actual);
    }

    @Test
    @DisplayName("поиск объекта в коллекции и возврат индекса при отсутствии его")
    public void indexOfTestAtWrong() {
        int actual = out.indexOf(5);

        assertEquals(-1, actual);
    }

    @Test
    @DisplayName("поиск объекта в коллекции с конца и возврат индекса")
    public void lastIndexOfTest() {
        out.add(5);
        out.add(expected);
        int actual = out.lastIndexOf(expected);

        assertEquals(1, actual);
    }

    @Test
    @DisplayName("получение объекта по индексу")
    public void getTest() {
        out.add(5);
        out.add(expected);
        Integer actual = out.get(1);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("получение объекта по индексу который выходит за пределы коллекции")
    public void getTestAtWrong() {
        assertThrows(ArrayIndexOutOfBoundsEx.class, () -> out.get(-1));
        assertThrows(ArrayIndexOutOfBoundsEx.class, () -> out.get(15));
    }

    @Test
    @DisplayName("сравнение коллекций")
    public void equalsTest() {
        Integer a = 1;
        Integer b = 2;

        IntegerListImpl expected = new IntegerListImpl(20);
        expected.add(a);
        expected.add(b);

        out.add(a);
        out.add(b);

        assertTrue(out.equals(expected));
    }

    @Test
    @DisplayName("сравнение коллекций которые не равны")
    public void equalsTestAtWrong() {
        Integer a = 1;
        Integer b = 2;

        IntegerListImpl expected = new IntegerListImpl();
        expected.add(a);
        expected.add(b);

        assertFalse(out.equals(expected));
    }

    @Test
    @DisplayName("сравнение коллекций с NULL")
    public void equalsTestAtThrow() {
        assertThrows(NullPointerException.class, () -> out.equals(null));
    }

    @Test
    @DisplayName("возвращаем размер коллекции")
    public void sizeTest() {
        Integer a = 1;
        Integer b = 2;

        out.add(a);
        out.add(b);

        int actual = out.size();

        assertEquals(2, actual);
    }

    @Test
    @DisplayName("проверяем пустая ли коллекция")
    public void isEmptyTest() {
        out.add(expected);

        assertFalse(out.isEmpty());
    }

    @Test
    @DisplayName("очищаем коллекцию")
    public void clearTest() {
        out.add(expected);
        out.clear();

        assertEquals(0, out.size());
    }

    @Test
    @DisplayName("возвращаем массив")
    public void toArrayTest() {
        out.add(expected);
        Integer[] actual = out.toArray();

        assertTrue(actual.getClass().isArray());
    }

    @Test
    @DisplayName("проверяем динамический ли у нас массив")
    public void upSizeArrayTest() {
        for (int i = 0; i < 15; i++) {
            out.add(i);
        }

        assertEquals(15, out.size());
    }
}