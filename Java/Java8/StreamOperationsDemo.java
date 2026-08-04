package Java.Java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamOperationsDemo {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 4322, 9, 0, 5, 44, 23, 433);

        List<Integer> filteredlList = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(filteredlList);

    }
}