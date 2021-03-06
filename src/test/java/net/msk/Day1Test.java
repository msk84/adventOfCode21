package net.msk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1Test {

    @Test
    public void task1Test() throws IOException, URISyntaxException {
        final List<Integer> data = this.getDay1DataAsIntList();
        int countHigherThanPrevious = 0;

        for(int i = 0; i < data.size() - 1; i++) {
            if(data.get(i) < data.get(i + 1)) {
                countHigherThanPrevious++;
            }
        }

        System.out.println("Result: " + countHigherThanPrevious);
        Assertions.assertEquals(1665, countHigherThanPrevious);
    }

    @Test
    public void task2Test() throws IOException, URISyntaxException {
        final List<Integer> data = this.getDay1DataAsIntList();
        int countHigherThanPreviousThree = 0;
        Integer previousSum = null;

        for(int i = 0; i < data.size() - 2; i++) {
            int sum = data.get(i) + data.get(i+1) + data.get(i+2);

            if(previousSum == null) {
                previousSum = data.get(i) + data.get(i+1) + data.get(i+2);
            }
            else {
                if(sum > previousSum) {
                    countHigherThanPreviousThree++;
                }
                previousSum = sum;
            }
        }

        System.out.println("Result: " + countHigherThanPreviousThree);
        Assertions.assertEquals(1702, countHigherThanPreviousThree);
    }

    private List<Integer> getDay1DataAsIntList() throws IOException, URISyntaxException {
        final Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("day1_data")).toURI());

        final Stream<String> lines = Files.lines(path);
        final List<Integer> dataAsInt = lines.mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        lines.close();
        return  dataAsInt;
    }
}
