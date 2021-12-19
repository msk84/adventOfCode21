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

public class Day5Test {

    @Test
    public void testGetHydrothermalVentCount() throws IOException, URISyntaxException {
        final Submarine submarine = new Submarine();
        final List<VentMapDataEntry> data = this.getDay5Data();
        long hydrothermalVentCount = submarine.getHydrothermalVentCount(data, 2);
        Assertions.assertEquals(18674, hydrothermalVentCount);
    }

    @Test
    public void testCoveredVentCoordinates() {
        final VentMapDataEntry entry1 = new VentMapDataEntry(new VentCoordinate(1,1), new VentCoordinate(1,3));
        final List<VentCoordinate> result1 = entry1.getCoveredVentCoordinates();
        final VentCoordinate coordinate11 = result1.get(0);
        Assertions.assertEquals(1, coordinate11.getX());
        Assertions.assertEquals(1, coordinate11.getY());
        final VentCoordinate coordinate12 = result1.get(1);
        Assertions.assertEquals(1, coordinate12.getX());
        Assertions.assertEquals(2, coordinate12.getY());
        final VentCoordinate coordinate13 = result1.get(2);
        Assertions.assertEquals(1, coordinate13.getX());
        Assertions.assertEquals(3, coordinate13.getY());

        final VentMapDataEntry entry2 = new VentMapDataEntry(new VentCoordinate(9,7), new VentCoordinate(7,7));
        final List<VentCoordinate> result2 = entry2.getCoveredVentCoordinates();
        final VentCoordinate coordinate21 = result2.get(0);
        Assertions.assertEquals(9, coordinate21.getX());
        Assertions.assertEquals(7, coordinate21.getY());
        final VentCoordinate coordinate22 = result2.get(1);
        Assertions.assertEquals(8, coordinate22.getX());
        Assertions.assertEquals(7, coordinate22.getY());
        final VentCoordinate coordinate23 = result2.get(2);
        Assertions.assertEquals(7, coordinate23.getX());
        Assertions.assertEquals(7, coordinate23.getY());

        final VentMapDataEntry entry3 = new VentMapDataEntry(new VentCoordinate(1,1), new VentCoordinate(3,3));
        final List<VentCoordinate> result3 = entry3.getCoveredVentCoordinates();
        final VentCoordinate coordinate31 = result3.get(0);
        Assertions.assertEquals(1, coordinate31.getX());
        Assertions.assertEquals(1, coordinate31.getY());
        final VentCoordinate coordinate32 = result3.get(1);
        Assertions.assertEquals(2, coordinate32.getX());
        Assertions.assertEquals(2, coordinate32.getY());
        final VentCoordinate coordinate33 = result3.get(2);
        Assertions.assertEquals(3, coordinate33.getX());
        Assertions.assertEquals(3, coordinate33.getY());

        final VentMapDataEntry entry4 = new VentMapDataEntry(new VentCoordinate(9,7), new VentCoordinate(7,9));
        final List<VentCoordinate> result4 = entry4.getCoveredVentCoordinates();
        final VentCoordinate coordinate41 = result4.get(0);
        Assertions.assertEquals(7, coordinate41.getX());
        Assertions.assertEquals(9, coordinate41.getY());
        final VentCoordinate coordinate42 = result4.get(1);
        Assertions.assertEquals(8, coordinate42.getX());
        Assertions.assertEquals(8, coordinate42.getY());
        final VentCoordinate coordinate43 = result4.get(2);
        Assertions.assertEquals(9, coordinate43.getX());
        Assertions.assertEquals(7, coordinate43.getY());
    }

    private List<VentMapDataEntry> getDay5Data() throws IOException, URISyntaxException {
        final Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("day5_data")).toURI());

        final Stream<String> lines = Files.lines(path);
        final List<VentMapDataEntry> data = lines
                .map(this::createVentMapDataEntry)
                .collect(Collectors.toList());
        lines.close();
        return data;
    }

    private VentMapDataEntry createVentMapDataEntry(final String line) {
        final String[] coordinates = line.split(" -> ");
        final VentCoordinate co1 = createVentCoordinate(coordinates[0]);
        final VentCoordinate co2 = createVentCoordinate(coordinates[1]);

        return new VentMapDataEntry(co1, co2);
    }

    private VentCoordinate createVentCoordinate(final String valuePair) {
        final String[] xy = valuePair.split(",");
        return new VentCoordinate(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
    }
}
