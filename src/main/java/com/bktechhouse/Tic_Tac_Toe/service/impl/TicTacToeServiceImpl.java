package com.bktechhouse.Tic_Tac_Toe.service.impl;

import com.bktechhouse.Tic_Tac_Toe.io.model.Pattern;
import com.bktechhouse.Tic_Tac_Toe.service.TicTacToeService;
import com.bktechhouse.Tic_Tac_Toe.shared.contestant.Contestant;
import com.bktechhouse.Tic_Tac_Toe.shared.utils.Arranging;
import com.bktechhouse.Tic_Tac_Toe.shared.utils.Utils;
import com.bktechhouse.Tic_Tac_Toe.shared.utils.WinningPatterns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TicTacToeServiceImpl implements TicTacToeService {

    @Autowired
    Arranging arranging;

    @Autowired
    Utils utils;

    @Autowired
    WinningPatterns winningPatterns;

    @Autowired
    Contestant contestant;

    @Override
    public ResponseEntity<String> play(String board) {
        try {

            board = board.toUpperCase();

            // checking If the board string represent a valid tic-tac-toe board

            if (board.length() != 9) {
                return new ResponseEntity("Invalid tic-tac-toe board", HttpStatus.BAD_REQUEST);
            }

            char[][] boardArray = arranging.intoArray(board);

            //when computer plays first, it plays in one of the lucky pattern

            if (!board.contains("X")) {

                int positionToPlayInto = utils.getRandomLuckyPosition();

                boardArray = utils.playingIntoLuckyPosition(boardArray, positionToPlayInto);

                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
            }

            // if a player plays first

            long totalXInStringBoard = board.chars().filter(ch -> ch == contestant.getPlayer()).count();

            if (totalXInStringBoard == 1) {

                // get the position where the player has played
                int position = utils.findPosition(boardArray);

                // check if a player has played in one of the lucky positions
                if (utils.findLuckyPosition(position)) {

                    int randomLuckyPosition = utils.getRandomLuckyPosition();

                    while (utils.pos[randomLuckyPosition] == position)
                        randomLuckyPosition = utils.getRandomLuckyPosition();

                    boardArray = utils.playingIntoLuckyPosition(boardArray, randomLuckyPosition);

                    return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);

                } else {

                    // if a player has not played in lucky positions

                    // get positions where player has played
                    List<Pattern> X_Positions = utils.findingPositionsOfXorO(boardArray, contestant.getPlayer());

                    for (Pattern p : X_Positions) {

                        // get the position that the player has played into
                        int positionNumber = winningPatterns.convertPatternsIntoPositionNumber(p);


                        // after a player has not played in lucky positions, a computer plays in one of lucky positions

                        if (positionNumber == 2) {
                            if (boardArray[1][1] == ' ') {
                                boardArray[1][1] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[0][0] == ' ') {
                                boardArray[0][0] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[0][2] == ' ') {
                                boardArray[0][2] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[2][0] == ' ') {
                                boardArray[2][0] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[2][2] == ' ') {
                                boardArray[2][2] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            }
                        } else if (positionNumber == 4) {
                            if (boardArray[2][0] == ' ') {
                                boardArray[2][0] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[0][0] == ' ') {
                                boardArray[0][0] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[0][2] == ' ') {
                                boardArray[0][2] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[1][1] == ' ') {
                                boardArray[1][1] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[2][2] == ' ') {
                                boardArray[2][2] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            }
                        } else if (positionNumber == 6) {
                            if (boardArray[0][2] == ' ') {
                                boardArray[0][2] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[2][2] == ' ') {
                                boardArray[2][2] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[0][0] == ' ') {
                                boardArray[0][0] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[1][1] == ' ') {
                                boardArray[1][1] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[2][0] == ' ') {
                                boardArray[2][0] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            }
                        } else if (positionNumber == 8) {
                            if (boardArray[2][2] == ' ') {
                                boardArray[2][2] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[2][0] == ' ') {
                                boardArray[2][0] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[0][0] == ' ') {
                                boardArray[0][0] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[0][2] == ' ') {
                                boardArray[0][2] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            } else if (boardArray[1][1] == ' ') {
                                boardArray[1][1] = contestant.getComputer();
                                return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                            }
                        }
                    }

                }

            } else {

                // if a player plays more than one time at the first play

                // get all positions played into by a computer

                List<Pattern> O_Positions = utils.findingPositionsOfXorO(boardArray, contestant.getComputer());

                // get all positions played into by a player

                List<Pattern> X_Patterns = utils.findingPositionsOfXorO(boardArray, contestant.getPlayer());

                // checking if computer remains with only one time to play (o) and win.

                if (O_Positions.size() == 2) {

                    Pattern compWinningPatterns = utils.findWinningPatterns(O_Positions, boardArray);
                    if (compWinningPatterns != null && boardArray[compWinningPatterns.getRow()][compWinningPatterns.getCol()] == ' ') {
                        boardArray[compWinningPatterns.getRow()][compWinningPatterns.getCol()] = contestant.getComputer();

                        if (utils.findWinner(boardArray, contestant.getComputer())) {
                            return new ResponseEntity("Computer won !!! And board is => " + arranging.intoString(boardArray), HttpStatus.OK);
                        }

                        if (utils.findWinner(boardArray, contestant.getPlayer())) {
                            return new ResponseEntity("Congrats you won !!! And board is => " + arranging.intoString(boardArray), HttpStatus.OK);
                        }

                        return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                    }
                }

                //computer blocks a player from winning if it has no where else to play and win

                Pattern playerWinningPatterns = utils.findWinningPatterns(X_Patterns, boardArray);

                if (playerWinningPatterns != null && boardArray[playerWinningPatterns.getRow()][playerWinningPatterns.getCol()] == ' ') {
                    boardArray[playerWinningPatterns.getRow()][playerWinningPatterns.getCol()] = contestant.getComputer();

                    if (utils.findWinner(boardArray, contestant.getComputer())) {
                        return new ResponseEntity("Computer won !!! And board is =>" + arranging.intoString(boardArray), HttpStatus.OK);
                    }

                    if (utils.findWinner(boardArray, contestant.getPlayer())) {
                        return new ResponseEntity("Congrats you won !!! And board is => " + arranging.intoString(boardArray), HttpStatus.OK);
                    }

                    return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                }


                // when there is empty position for a comp to play into and win


                for (Pattern pattern : O_Positions) {
                    List<Pattern> compEmptyPattern = utils.findEmptyPosition(boardArray, contestant.getComputer(), pattern);

                    if (compEmptyPattern.size() > 1) {
                        Pattern p = compEmptyPattern.get(new Random().nextInt(compEmptyPattern.size()));
                        boardArray[p.getRow()][p.getCol()] = contestant.getComputer();


                        if (utils.findWinner(boardArray, contestant.getComputer())) {
                            return new ResponseEntity("Computer won !!! And board is => " + arranging.intoString(boardArray), HttpStatus.OK);
                        }

                        if (utils.findWinner(boardArray, contestant.getPlayer())) {
                            return new ResponseEntity("Congrats you won !!! And board is => " + arranging.intoString(boardArray), HttpStatus.OK);
                        }

                        return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                    }
                }

                // when there is empty position for a player to play into and win, computer blocks player from winning

                for (Pattern pattern : X_Patterns) {
                    List<Pattern> playerEmptyPattern = utils.findEmptyPosition(boardArray, contestant.getPlayer(), pattern);

                    if (playerEmptyPattern.size() > 1) {
                        Pattern p = playerEmptyPattern.get(new Random().nextInt(playerEmptyPattern.size()));
                        boardArray[p.getRow()][p.getCol()] = contestant.getComputer();

                        if (utils.findWinner(boardArray, contestant.getComputer())) {
                            return new ResponseEntity("Computer won !!! And board is => " + arranging.intoString(boardArray), HttpStatus.OK);
                        }

                        if (utils.findWinner(boardArray, contestant.getPlayer())) {
                            return new ResponseEntity("Congrats you won !!! And board is => " + arranging.intoString(boardArray), HttpStatus.OK);
                        }

                        return new ResponseEntity(arranging.intoString(boardArray), HttpStatus.OK);
                    }
                }

            }


        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return null;
    }

}

