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
            final int mask = 1 << bitPos;
            final int mostCommonBit = this.getMostCommonBit(diagnosticDataList, mask);

            if(mostCommonBit == 0) {
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

    public int calculateLifeSupportRating(final List<String> diagnosticDataList, final int numberOfBits) throws Exception {
        int oxygenGeneratorRating;
        int co2scrubberRating;

        List<String> filteredOxygenGeneratorRatingDiagnosticData = diagnosticDataList;
        for(int bitPos = numberOfBits - 1; bitPos >= 0; bitPos--) {
            final int mask = 1 << bitPos;
            final int mostCommonBit = this.getMostCommonBit(filteredOxygenGeneratorRatingDiagnosticData, mask);
            filteredOxygenGeneratorRatingDiagnosticData = filteredOxygenGeneratorRatingDiagnosticData.stream()
                    .filter(d -> this.isDataMatchingBitValueCriteria(d, mostCommonBit, mask))
                    .toList();
            if(filteredOxygenGeneratorRatingDiagnosticData.size() == 1) {
                break;
            }
            else if(filteredOxygenGeneratorRatingDiagnosticData.size() < 1) {
                throw new Exception("Failed to determine oxygenGeneratorRating. No values left after filtering.");
            }
        }

        if(filteredOxygenGeneratorRatingDiagnosticData.size() == 1) {
            oxygenGeneratorRating = Integer.parseUnsignedInt(filteredOxygenGeneratorRatingDiagnosticData.get(0), 2);
        }
        else {
            throw new Exception("Failed to determine oxygenGeneratorRating. Too many values left after filtering.");
        }


        List<String> filteredCo2scrubberRatingDiagnosticData = diagnosticDataList;
        for(int bitPos = numberOfBits - 1; bitPos >= 0; bitPos--) {
            final int mask = 1 << bitPos;
            final int mostCommonBit = this.getMostCommonBit(filteredCo2scrubberRatingDiagnosticData, mask);
            final int leastCommonBit = mostCommonBit == 1 ? 0 : 1;
            filteredCo2scrubberRatingDiagnosticData = filteredCo2scrubberRatingDiagnosticData.stream()
                    .filter(d -> this.isDataMatchingBitValueCriteria(d, leastCommonBit, mask))
                    .toList();
            if(filteredCo2scrubberRatingDiagnosticData.size() == 1) {
                break;
            }
            else if(filteredCo2scrubberRatingDiagnosticData.size() < 1) {
                throw new Exception("Failed to determine co2scrubberRating. No values left after filtering.");
            }
        }

        if(filteredCo2scrubberRatingDiagnosticData.size() == 1) {
            co2scrubberRating = Integer.parseUnsignedInt(filteredCo2scrubberRatingDiagnosticData.get(0), 2);
        }
        else {
            throw new Exception("Failed to determine co2scrubberRating. Too many values left after filtering.");
        }

        return oxygenGeneratorRating * co2scrubberRating;
    }

    private int getMostCommonBit(final List<String> diagnosticDataList, final int mask) {
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
            return 0;
        }
        else {
            // Return "1" also in case of equal count
            return 1;
        }
    }

    private boolean isDataMatchingBitValueCriteria(final String diagnosticData, final int expectedBitValue, final int mask) {
        final int intData = Integer.parseUnsignedInt(diagnosticData, 2);
        if ((intData & mask) != 0) {
            return expectedBitValue == 1;
        }
        else {
            return expectedBitValue == 0;
        }
    }
}
