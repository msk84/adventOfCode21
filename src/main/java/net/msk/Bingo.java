package net.msk;

import java.util.List;

public class Bingo {
    final List<BingoBoard> boards;
    final int[] numbers;

    public Bingo(List<BingoBoard> boards, int[] numbers) {
        this.boards = boards;
        this.numbers = numbers;
    }

    public int getWinningBoardScore() throws Exception {
        for (int number : this.numbers) {
            for (BingoBoard board : this.boards)
                if (board.isBingo(number)) {
                    return board.getCalculateScore(number);
                }
        }

        throw new Exception("Something went wrong, not winning board.");
    }

    public int getLastWinningBoardScore() throws Exception {
        BingoBoard lastWinningBoard = null;
        int lastWinningNumber = 0;

        for (int number : this.numbers) {
            for (BingoBoard board : this.boards)
                if (!board.isAlreadyBingo() && board.isBingo(number)) {
                    lastWinningBoard = board;
                    lastWinningNumber = number;
                }
        }

        if (lastWinningBoard != null) {
            return lastWinningBoard.getCalculateScore(lastWinningNumber);
        }
        throw new Exception("Something went wrong, not winning board.");
    }
}
