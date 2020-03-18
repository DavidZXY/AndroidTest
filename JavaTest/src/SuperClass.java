class SuperClass {
    public String getName() {
        return "Super";
    }

    public static void main(String[] args) {
        SuperClass superClass = new SubClass();
        printHello(superClass);
    }

    public static void printHello(SuperClass superClass) {
        System.out.println("Hello" + superClass.getName());
    }

    public static void printHello(SubClass subClass) {
        System.out.println("Hello" + subClass.getName());
    }
}

class SubClass extends SuperClass {
    public String getName() {
        return "Sub";
    }
}