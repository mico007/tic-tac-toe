package com.bktechhouse.Tic_Tac_Toe.shared.utils;

import com.bktechhouse.Tic_Tac_Toe.io.model.Pattern;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WinningPatterns {

    List<List<Pattern>> positions = new ArrayList<>();

    public List<List<Pattern>> getAllWinningPositions() {

        // rows

        List<Pattern> firstRowPatterns = new ArrayList<>();
        firstRowPatterns.add(new Pattern(0, 0));
        firstRowPatterns.add(new Pattern(0, 1));
        firstRowPatterns.add(new Pattern(0, 2));

        positions.add(firstRowPatterns);

        List<Pattern> secondRowPatterns = new ArrayList<>();
        secondRowPatterns.add(new Pattern(1, 0));
        secondRowPatterns.add(new Pattern(1, 1));
        secondRowPatterns.add(new Pattern(1, 2));

        positions.add(secondRowPatterns);

        List<Pattern> thirdRowPatterns = new ArrayList<>();
        thirdRowPatterns.add(new Pattern(2, 0));
        thirdRowPatterns.add(new Pattern(2, 1));
        thirdRowPatterns.add(new Pattern(2, 2));

        positions.add(thirdRowPatterns);


        // columns


        List<Pattern> firstColPatterns = new ArrayList<>();
        firstColPatterns.add(new Pattern(0, 0));
        firstColPatterns.add(new Pattern(1, 0));
        firstColPatterns.add(new Pattern(2, 0));

        positions.add(firstColPatterns);


        List<Pattern> secondColPatterns = new ArrayList<>();
        secondColPatterns.add(new Pattern(0, 1));
        secondColPatterns.add(new Pattern(1, 1));
        secondColPatterns.add(new Pattern(2, 1));

        positions.add(secondColPatterns);


        List<Pattern> thirdColPatterns = new ArrayList<>();
        thirdColPatterns.add(new Pattern(0, 2));
        thirdColPatterns.add(new Pattern(1, 2));
        thirdColPatterns.add(new Pattern(2, 2));

        positions.add(thirdColPatterns);

        // diagonals

        List<Pattern> firstDiagonalPatterns = new ArrayList<>();
        firstDiagonalPatterns.add(new Pattern(0, 0));
        firstDiagonalPatterns.add(new Pattern(1, 1));
        firstDiagonalPatterns.add(new Pattern(2, 2));

        positions.add(firstDiagonalPatterns);

        List<Pattern> secondDiagonalPatterns = new ArrayList<>();
        secondDiagonalPatterns.add(new Pattern(0, 2));
        secondDiagonalPatterns.add(new Pattern(1, 1));
        secondDiagonalPatterns.add(new Pattern(2, 0));

        positions.add(secondDiagonalPatterns);


        return positions;
    }


    public int convertPatternsIntoPositionNumber(Pattern pattern) {

        int positionNumber = 0;

        if (pattern.getRow() == 0 && pattern.getCol() == 0) {
            positionNumber = 1;
        } else if (pattern.getRow() == 0 && pattern.getCol() == 1) {
            positionNumber = 2;
        } else if (pattern.getRow() == 0 && pattern.getCol() == 2) {
            positionNumber = 3;
        } else if (pattern.getRow() == 1 && pattern.getCol() == 0) {
            positionNumber = 4;
        } else if (pattern.getRow() == 1 && pattern.getCol() == 1) {
            positionNumber = 5;
        } else if (pattern.getRow() == 1 && pattern.getCol() == 2) {
            positionNumber = 6;
        } else if (pattern.getRow() == 2 && pattern.getCol() == 0) {
            positionNumber = 7;
        } else if (pattern.getRow() == 2 && pattern.getCol() == 1) {
            positionNumber = 8;
        } else if (pattern.getRow() == 2 && pattern.getCol() == 2) {
            positionNumber = 9;
        }
        return positionNumber;
    }
}
