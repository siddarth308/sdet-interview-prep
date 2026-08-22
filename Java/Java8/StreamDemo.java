// https://www.youtube.com/watch?v=DYyhFLC2eJ0&t=340s

package Java.Java8;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.Arrays;
import java.util.List;

public class StreamDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "grapes");
        Stream<String> myStream = list.stream();

        String[] array = { "apple", "banana", "grapes" };
        Stream<String> arrayStream = Arrays.stream(array);

        Stream<Integer> intStream = Stream.of(1, 2, 3, 4);

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 4322, 9, 0, 5, 44, 23, 433);

        List<Integer> filteredlList = list1.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(filteredlList);

        List<Integer> MappedList = filteredlList.stream().map(x -> x / 2).collect(Collectors.toList());

        // OR
        list1.stream().filter(x -> x % 2 == 0).map(x -> x / 2).collect(Collectors.toList());

    }
}
