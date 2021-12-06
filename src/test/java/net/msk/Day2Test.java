package net.msk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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

public class Day2Test {

    @Disabled("No longer valid; See task2")
    @Test
    public void task1Test() throws Exception {
        final Submarine submarine = new Submarine();
        final List<NavData> data = this.getDay2Data();

        submarine.navigate(data);

        System.out.println("Result: " + submarine.getHorizontalPosition() * submarine.getDepth());
        Assertions.assertEquals(2083, submarine.getHorizontalPosition());
        Assertions.assertEquals(955, submarine.getDepth());
    }

    @Test
    public void task2Test() throws Exception {
        final Submarine submarine = new Submarine();
        final List<NavData> data = this.getDay2Data();

        submarine.navigate(data);

        System.out.println("Result: " + submarine.getHorizontalPosition() * submarine.getDepth());
        Assertions.assertEquals(2083, submarine.getHorizontalPosition());
        Assertions.assertEquals(1002964, submarine.getDepth());
    }

    private List<NavData> getDay2Data() throws IOException, URISyntaxException {
        final Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("day2_data")).toURI());

        final Stream<String> lines = Files.lines(path);
        final List<NavData> data = lines
                .map(s -> new NavData(NavDirection.valueOf(s.split(" ")[0]), Integer.parseInt(s.split(" ")[1])))
                .collect(Collectors.toList());
        lines.close();
        return data;
    }
}
