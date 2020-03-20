public class OuterClass {

    private String name;
    private int age;

    static class InnerClass {
        private int a = 2;
        private InnerClass() {}
    }

    class InnerClass2 {
        private int a = 3;
    }

    public void method() {
        System.out.println(new InnerClass().a);
        System.out.println(new InnerClass2().a);
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        outerClass.method();
    }
}
