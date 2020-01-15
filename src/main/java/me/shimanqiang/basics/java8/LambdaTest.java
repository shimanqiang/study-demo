package me.shimanqiang.basics.java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author shimanqiang
 * @since 2020/1/15 11:04
 */
public class LambdaTest {

    public static void main(String[] args) {
        System.out.println("-------Supplier(1)-------");
        Supplier<LambdaTest> supplier = LambdaTest::new;
        supplier.get().lambdaTest();
    }

    public void lambdaTest() {
        System.out.println("-------Supplier(2)-------");
        Supplier<Integer> supplier = () -> 5;
        System.out.println(supplier.get());
        System.out.println("-------Function-------");
        Function<Integer, Integer> funcDouble = (n) -> n * 2;
        Function<Integer, Integer> funcPlus2 = LambdaTest::testFunction;
        System.out.println(funcDouble.apply(3));
        System.out.println(funcPlus2.apply(3));
        System.out.println(funcDouble.andThen(funcPlus2).apply(3));
        System.out.println(funcDouble.compose(funcPlus2).apply(3));
        System.out.println(Function.identity().compose(funcDouble).apply(3));
        System.out.println("-------Consumer-------");
        Consumer<Integer> consumer = LambdaTest::testConsumer;
        consumer.accept(5);
        Consumer<Integer> consumer2 = (x) -> {
            int num = x * 2;
            System.out.println(num);
        };
        consumer2.andThen(consumer).accept(2);
        System.out.println("-------Predicate-------");
        Predicate<Integer> predicate = (n) -> n > 4;
        System.out.println(predicate.test(4));
        Predicate<Integer> predicate2 = LambdaTest::testPredicate;
        System.out.println(predicate2.test(5));
    }

    private static int testFunction(int n) {
        return n + 2;
    }

    private static void testConsumer(int n) {
        System.out.println(n * 3);
    }

    private static boolean testPredicate(int n) {
        return n > 4;
    }
}
