public class Main {
    public static void main(String[] args) {
        StringList list = new StringListImpl();
        for (int i = 0; i < 25; i++) {
            System.out.println("list.add(i) = " + list.add("" + i));
        }
        System.out.println("list.size() = " + list.size());

        System.out.println("list.add(15, \"add to index\") = " + list.add(15, "add to index"));

        System.out.println("list.remove(10) = " + list.remove(10));
        System.out.println("list.size() = " + list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println("после удаления list.get(i) = " + list.get(i));
        }

        System.out.println("list.size() = " + list.size());
        list.clear();
        System.out.println("list.size() = " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println("после очищения коллекции list.get(i) = " + list.get(i));
        }

    }
}