package com.bktechhouse.Tic_Tac_Toe.shared.utils;

import com.bktechhouse.Tic_Tac_Toe.io.model.Pattern;
import com.bktechhouse.Tic_Tac_Toe.shared.contestant.Contestant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Utils {

    // lucky positions
    public int[] pos = {1, 3, 5, 7, 9};

    @Autowired
    Contestant contestant;

    @Autowired
    WinningPatterns winningPatterns;

    //    function to find position where the player has played

    public int findPosition(char[][] boardArray) {
        int position = 0;

        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 3; y++) {
                if (boardArray[i][y] == contestant.getPlayer()) {
                    Pattern p = new Pattern(i, y);
                    position = winningPatterns.convertPatternsIntoPositionNumber(p);
                }
            }
        }
        return position;
    }

    // find whether the position played is in the lucky position or not

    public boolean findLuckyPosition(int position) {
        for (int i : pos) {
            if (i == position) return true;
        }
        return false;
    }

    //   function to get  a random lucky position

    public int getRandomLuckyPosition() {
        return new Random().nextInt(pos.length);
    }

    // method for playing into lucky positions

    public char[][] playingIntoLuckyPosition(char[][] boardArray, int position) {

        if (position == 0) {
            boardArray[0][0] = contestant.getComputer();
        } else if (position == 1) {
            boardArray[0][2] = contestant.getComputer();
        } else if (position == 2) {
            boardArray[1][1] = contestant.getComputer();
        } else if (position == 3) {
            boardArray[2][0] = contestant.getComputer();
        } else if (position == 4) {
            boardArray[2][2] = contestant.getComputer();
        } else {
            return null;
        }

        return boardArray;
    }

    // method to find positions x or o in the board received

    public List<Pattern> findingPositionsOfXorO(char[][] boardArray, char c) {
        List<Pattern> patterns = new ArrayList<>();
        Pattern pattern;
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 3; y++) {
                if (boardArray[i][y] == c) {
                    pattern = new Pattern(i, y);
                    patterns.add(pattern);
                }
            }
        }

        return patterns;
    }

    public boolean containsWinPosition(List<Pattern> patterns, Pattern pattern) {
        for (Pattern pp : patterns) {
            if (pp.getRow() == pattern.getRow() && pp.getCol() == pattern.getCol()) {
                return true;
            }
        }
        return false;
    }

    // method for finding the winner

    public boolean findWinner(char[][] boardArray, char c) {


        List<Pattern> patterns = findingPositionsOfXorO(boardArray, c);


        List<List<Pattern>> allWinPositions = winningPatterns.getAllWinningPositions();
        int count = 0;

        for (List<Pattern> ps : allWinPositions) {

            count = 0;
            for (int i = 0; i < ps.size(); i++) {

                if (containsWinPosition(patterns, ps.get(i))) {
                    count++;
                }
            }

            // if we find three positions arranged together in the winning patterns, then we have a winner
            if (count == 3) {
                return true;

            }

        }

        return false;

    }

    // method for comp to find which position to block from winning when we have more than one X in the board
    // finding if we have two x that need only one X to win and then comp plays to block them

    public Pattern findWinningPatterns(List<Pattern> patterns, char[][] boardArray) {

        List<List<Pattern>> allWinPositions = winningPatterns.getAllWinningPositions();

        int count = 0;

        for (List<Pattern> ps : allWinPositions) {

            // check if we have at least two positions and only remain one to win

            Pattern pp = null;

            count = 0;

            for (int i = 0; i < ps.size(); i++) {

                if (containsWinPosition(patterns, ps.get(i))) {
                    count++;
                } else {

                    pp = ps.get(i);
                }
            }

            if (count == 2) {

                if (boardArray[pp.getRow()][pp.getCol()] == ' ') {
                    return pp;
                }

            }


        }
        return null;
    }

    // method to find the empty position to play into and win

    public List<Pattern> findEmptyPosition(char[][] boardArray, char c, Pattern pattern) {

        List<Pattern> emptyPattern = new ArrayList<>();

        List<List<Pattern>> allWinPositions = winningPatterns.getAllWinningPositions();

        for (List<Pattern> patterns : allWinPositions) {


            if (containsWinPosition(patterns, pattern) && boardArray[pattern.getRow()][pattern.getCol()] == c) {

                // check if there is an empty position to play into and win
                int emptyPos = 0;
                for (int i = 0; i < patterns.size(); i++) {
                    if (boardArray[patterns.get(i).getRow()][patterns.get(i).getCol()] == ' ') {
                        emptyPattern.add(patterns.get(i));
                        emptyPos++;
                    }
                }
                if (emptyPos == 2) {
                    return emptyPattern;
                } else {
                    emptyPattern.clear();
                }
            }

        }

        return emptyPattern;
    }

}
