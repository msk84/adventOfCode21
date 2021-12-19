package net.msk;

import java.util.ArrayList;
import java.util.List;

public class VentMapDataEntry {
    private final VentCoordinate from;
    private final VentCoordinate to;

    public VentMapDataEntry(final VentCoordinate from, final VentCoordinate to) {
        this.from = from;
        this.to = to;
    }

    public List<VentCoordinate> getCoveredVentCoordinates() {
        List<VentCoordinate> result = new ArrayList<>();

        // straight lines
        if(this.from.getX() == this.to.getX() || this.from.getY() == this.to.getY()) {
            if (this.from.getX() <= this.to.getX()) {
                for (int x = this.from.getX(); x <= this.to.getX(); x++) {
                    this.calculateY(x, result);
                }
            } else {
                for (int x = this.from.getX(); x >= this.to.getX(); x--) {
                    this.calculateY(x, result);
                }
            }
        }
        // diagonal lines
        else {
            final VentCoordinate newFrom, newTo;
            if(this.from.getX() < this.to.getX()) {
                newFrom = this.from;
                newTo = this.to;
            }
            else {
                newFrom = this.to;
                newTo = this.from;
            }

            double m = (newTo.getY() - newFrom.getY()) / (newTo.getX() - newFrom.getX());
            int steps = 0;
            if(m >= 0) {
                for(int x=newFrom.getX(); x <= newTo.getX(); x++) {
                    result.add(new VentCoordinate(newFrom.getX() + steps, newFrom.getY() + steps));
                    steps++;
                }
            }
            else {
                for(int x=newFrom.getX(); x <= newTo.getX(); x++) {
                    result.add(new VentCoordinate(newFrom.getX() + steps, newFrom.getY() - steps));
                    steps++;
                }
            }
        }

        return result;
    }

    private void calculateY(final int x, final List<VentCoordinate> result) {
        if(this.from.getY() <= this.to.getY()) {
            for(int y=this.from.getY(); y <= this.to.getY(); y++) {
                result.add(new VentCoordinate(x,y));
            }
        }
        else {
            for(int y=this.from.getY(); y >= this.to.getY(); y--) {
                result.add(new VentCoordinate(x,y));
            }
        }
    }
}
