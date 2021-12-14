package net.msk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day4Test {

    private int[] numbers;
    private List<BingoBoard> boards;

    @Test
    public void testWinningBingoBoard() throws Exception {
        this.initializeDay4Data();
        final Bingo bingo = new Bingo(this.boards, this.numbers);
        final int score = bingo.getWinningBoardScore();
        Assertions.assertEquals(16674, score);
    }

    @Test
    public void testLastWinningBingoBoard() throws Exception {
        this.initializeDay4Data();
        final Bingo bingo = new Bingo(this.boards, this.numbers);
        final int score = bingo.getLastWinningBoardScore();
        Assertions.assertEquals(7075, score);
    }

    private void initializeDay4Data() throws IOException, URISyntaxException {
        final Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("day4_data")).toURI());

        final List<BingoBoard> testBoards = new ArrayList<>();

        final List<String> allLines = Files.lines(path)
                .filter(s -> s.length() > 0)
                .toList();

        final String numbersString = allLines.get(0);
        this.numbers = Arrays.stream(numbersString.split(",")).mapToInt(Integer::parseInt).toArray();

        for (int i = 1; i <= allLines.size() / 5; i++) {
            int[][] boardData = new int[5][5];
            for (int n = 1; n <= 5; n++) {
                final String lineData = allLines.get(((i - 1) * 5) + n).trim().replaceAll(" {2}", " ");
                boardData[n - 1] = Arrays.stream(lineData.split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            testBoards.add(new BingoBoard(boardData));
        }

        this.boards = testBoards;
    }
}
