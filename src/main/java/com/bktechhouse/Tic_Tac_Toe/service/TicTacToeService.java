package com.bktechhouse.Tic_Tac_Toe.service;

import org.springframework.http.ResponseEntity;

public interface TicTacToeService {

    ResponseEntity<String> play(String board);

}
