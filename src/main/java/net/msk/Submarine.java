package net.msk;

import java.util.List;

public class Submarine {

    private int horizontalPosition = 0;
    private int depth = 0;
    private int aim = 0;

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public int getDepth() {
        return depth;
    }

    public void navigate(final List<NavData> navDataList) throws Exception {
        for(final NavData navData : navDataList) {
            if(NavDirection.forward == navData.getDirection()) {
                horizontalPosition += navData.getValue();
                depth += aim * navData.getValue();
            }
            else if(NavDirection.down == navData.getDirection()) {
                aim += navData.getValue();
            }
            else if (NavDirection.up == navData.getDirection()) {
                aim -= navData.getValue();
            }
            else {
                throw new Exception("Invalid NavData: " + navData);
            }
        }
    }
}
