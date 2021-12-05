package net.msk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {

    @Test
    public void task1Test() throws IOException, URISyntaxException {
        final List<NavData> data = this.getDay2Data();

        int horizontalPosition = 0;
        int depth = 0;

        for(final NavData navData : data) {
            if(Day2NavDirection.forward == navData.direction) {
                horizontalPosition += navData.value;
            }
            else if(Day2NavDirection.down == navData.direction) {
                depth += navData.value;
            }
            else if (Day2NavDirection.up == navData.direction) {
                depth -= navData.value;
            }
            else {
                Assertions.fail("Invalid navigation direction.");
            }
        }

        System.out.println("Result: " + horizontalPosition * depth);
        Assertions.assertEquals(2083, horizontalPosition);
        Assertions.assertEquals(955, depth);
    }

    @Test
    public void task2Test() throws IOException, URISyntaxException {
        final List<NavData> data = this.getDay2Data();

        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;

        for(final NavData navData : data) {
            if(Day2NavDirection.forward == navData.direction) {
                horizontalPosition += navData.value;
                depth += aim * navData.value;
            }
            else if(Day2NavDirection.down == navData.direction) {
                aim += navData.value;
            }
            else if (Day2NavDirection.up == navData.direction) {
                aim -= navData.value;
            }
            else {
                Assertions.fail("Invalid navigation direction.");
            }
        }

        System.out.println("Result: " + horizontalPosition * depth);
        Assertions.assertEquals(2083, horizontalPosition);
        Assertions.assertEquals(1002964, depth);
    }

    private List<NavData> getDay2Data() throws IOException, URISyntaxException {
        final Path path = Paths.get(getClass().getClassLoader()
                .getResource("day2_data").toURI());

        final Stream<String> lines = Files.lines(path);
        final List<NavData> data = lines
                .map(s -> new NavData(Day2NavDirection.valueOf(s.split(" ")[0]), Integer.parseInt(s.split(" ")[1])))
                .collect(Collectors.toList());
        lines.close();
        return data;
    }

    private class NavData {
        Day2NavDirection direction;
        int value;

        public NavData(final Day2NavDirection direction, final int value) {
            this.direction = direction;
            this.value = value;
        }
    }
}
