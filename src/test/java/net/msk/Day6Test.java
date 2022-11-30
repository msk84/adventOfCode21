package net.msk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class Day6Test {

    @Test
    public void testLaternfishReproduction() throws Exception {
        final int[] data = this.getDay6Data();
        final LaternfishSchoolSlim laternfishSchoolSlim = new LaternfishSchoolSlim(data);
        final int schoolSize = laternfishSchoolSlim.addDays(256);
        System.out.println("SchoolSize: " + schoolSize);
        //Assertions.assertEquals(380758, schoolSize);
    }

    private int[] getDay6Data() throws Exception {
        final Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("day6_data")).toURI());

        final Stream<String> lines = Files.lines(path);
        final Optional<String> line = lines.findFirst();
        lines.close();
        if(line.isPresent()) {
            return Arrays.asList(line.get().split(",")).stream().mapToInt(Integer::parseInt).toArray();
        }

        throw new Exception("Failed to parse data.");
    }

}
