package singleton;

class A {
    private static A instance = null;

    private A() {
    }

    public static A getInstance() {
        if (null == instance) {
            instance = new A();
        }
        return instance;
    }
}

public class Singleton {
    public static void main(String[] args) {
        A o1 = A.getInstance();
        A o2 = A.getInstance();
        if (o1.equals(o2)) {
            System.out.println("o1与o2是同一个对象！");
        }
    }
}
