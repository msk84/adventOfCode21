package net.msk;

public class BingoBoard {

    private static final int EDGE_LENGTH = 5;

    private boolean isAlreadyBingo = false;
    private final int[][] bingoData;
    private final boolean[][] bingoHitMap = new boolean[EDGE_LENGTH][EDGE_LENGTH];

    public BingoBoard(int[][] bingoData) {
        this.bingoData = bingoData;
    }

    public boolean isAlreadyBingo() {
        return this.isAlreadyBingo;
    }

    public boolean isBingo(final int number) {
        if (this.isAlreadyBingo) {
            return true;
        }

        for (int y = 0; y < EDGE_LENGTH; y++) {
            int rowHits = 0;
            int columnHits = 0;

            for (int x = 0; x < EDGE_LENGTH; x++) {
                if (this.bingoData[y][x] == number) {
                    this.bingoHitMap[y][x] = true;
                }

                if (this.bingoHitMap[y][x]) {
                    rowHits++;
                }
                if (this.bingoHitMap[x][y]) {
                    columnHits++;
                }

                if (rowHits == EDGE_LENGTH || columnHits == EDGE_LENGTH) {
                    this.isAlreadyBingo = true;
                    return true;
                }
            }
        }
        return false;
    }

    public int getCalculateScore(final int winningNumber) {
        int score = 0;

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (!this.bingoHitMap[y][x]) {
                    score += this.bingoData[y][x];
                }
            }
        }

        return score * winningNumber;
    }
}
