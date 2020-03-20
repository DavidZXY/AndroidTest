import java.util.Random;

interface Food{
    enum Fruit implements Food {
        Apple, Orange, Peach
    }
    enum Meat implements Food {
        Pork, Beef, Mutton
    }
    enum Vegetable implements Food {
        Cabbage, Spinach, Celery
    }
}

enum Test {
    FRUIT(Food.Fruit.class),
    MEAT(Food.Meat.class);

    private Food[] values;
    Test(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randSelection() {
        return values[new Random(100).nextInt(values.length)];
    }
}
