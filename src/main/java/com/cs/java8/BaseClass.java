package com.cs.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BaseClass {
public static void main(String[] args) {
	
	List<String> list = 
			Arrays.stream("BiConsumer<T,U>#BiFunction<T,U,R>#BinaryOperator<T>#BiPredicate<T,U>#BooleanSupplier#Consumer<T>#DoubleBinaryOperator#DoubleConsumer#DoubleFunction<R>#DoublePredicate#DoubleSupplier#DoubleToIntFunction#DoubleToLongFunction#DoubleUnaryOperator#Function<T,R>#IntBinaryOperator#IntConsumer#IntFunction<R>#IntPredicate#IntSupplier#IntToDoubleFunction#IntToLongFunction#IntUnaryOperator#LongBinaryOperator#LongConsumer#LongFunction<R>#LongPredicate#LongSupplier#LongToDoubleFunction#LongToIntFunction#LongUnaryOperator#ObjDoubleConsumer<T>#ObjIntConsumer<T>#ObjLongConsumer<T>#Predicate<T>#Supplier<T>#ToDoubleBiFunction<T,U>#ToDoubleFunction<T>#ToIntBiFunction<T,U>#ToIntFunction<T>#ToLongBiFunction<T,U>#ToLongFunction<T>#UnaryOperator<T>".split("#")).collect(Collectors.toList());
	
	//list.forEach(System.out::println);
	System.out.println();
	
	//list.stream().filter(s->s.contains("Consumer")).forEach(System.out::println);
	System.out.println();
//	list.stream().filter(s->s.contains("Function")||s.contains("Operator")).forEach(System.out::println);
	list.stream().filter(s->s.contains("Function")||s.contains("Operator")).filter(s->s.contains("Bi")).forEach(System.out::println);
	System.out.println();
	//list.stream().filter(s->s.contains("Predicate")).forEach(System.out::println);
	System.out.println();
	//list.stream().filter(s->s.contains("Supplier")).forEach(System.out::println);
}
}
