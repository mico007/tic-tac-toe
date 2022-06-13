package com.bktechhouse.Tic_Tac_Toe.ui.controller;

import com.bktechhouse.Tic_Tac_Toe.service.TicTacToeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/tic-tac-toe")
public class TicTacToeController {

    @Autowired
    TicTacToeService ticTacToeService;

    @GetMapping("/play")
    public ResponseEntity<String> play(@RequestParam(name = "board",required = true) String board){
        return ticTacToeService.play(board);
    }

}
