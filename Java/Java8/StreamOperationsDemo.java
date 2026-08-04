package Java.Java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperationsDemo {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 4322, 9, 0, 5, 44, 23, 433);
        List<Integer> filteredList = list.stream()
                .filter(x -> x % 2 == 0).map(x -> x / 2)
                .distinct()
                .sorted((a, b) -> (b - a))
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());
        System.out.println(filteredList);

        Stream.iterate(0, x -> x + 1).limit(100).skip(1)
                .filter(x -> x % 2 == 0)
                .map(x -> x / 10)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}