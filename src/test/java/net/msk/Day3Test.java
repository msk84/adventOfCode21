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

public class Day3Test {

    @Test
    public void task1Test() throws IOException, URISyntaxException {
        final Submarine submarine = new Submarine();
        final int powerConsumption = submarine.calculatePowerConsumption(this.getDay3Data(), 12);
        Assertions.assertEquals(2724524, powerConsumption);
    }

    private List<String> getDay3Data() throws IOException, URISyntaxException {
        final Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("day3_data")).toURI());

        final Stream<String> lines = Files.lines(path);
        final List<String> data = lines
                .collect(Collectors.toList());
        lines.close();
        return data;
    }
}
