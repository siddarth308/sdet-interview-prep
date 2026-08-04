// https://www.youtube.com/watch?v=DYyhFLC2eJ0&t=340s

package Java.Java8;

import java.util.stream.Stream;

import java.util.Arrays;
import java.util.List;

public class StreamDemo {
    List<String> list = Arrays.asList("apple", "banana", "grapes");
    Stream<String> myStream = list.stream();

    String[] array = { "apple", "banana", "grapes" };
    Stream<String> Stream = Arrays.stream(array);

    Stream<Integer> IntStream = Stream.of(1, 2, 3, 4);

}
