package com.bktechhouse.Tic_Tac_Toe.shared.contestant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Contestant {

    @Value("X")
    char player;

    @Value("O")
    char computer;

    public char getPlayer() {
        return player;
    }

    public char getComputer() {
        return computer;
    }

}
