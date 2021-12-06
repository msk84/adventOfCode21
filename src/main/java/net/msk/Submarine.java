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

    public int calculatePowerConsumption(final List<String> diagnosticDataList, final int numberOfBits) {
        int gammaRate = 0;
        int epsilonRate = 0;

        for(int bitPos = 0; bitPos < numberOfBits; bitPos++) {
            int mask = 1 << bitPos;
            int zeroCount = 0;
            int oneCount = 0;

            for (String diagnosticData : diagnosticDataList) {
                int intData = Integer.parseUnsignedInt(diagnosticData, 2);
                if ((intData & mask) != 0) {
                    oneCount++;
                }
                else {
                    zeroCount++;
                }
            }

            if(zeroCount > oneCount) {
                gammaRate = gammaRate & ~0;
                epsilonRate = epsilonRate | (1 << bitPos);
            }
            else {
                gammaRate = gammaRate | (1 << bitPos);
                epsilonRate = epsilonRate & ~0;
            }
        }
        return gammaRate * epsilonRate;
    }
}
