package org.academiadecodigo.thefellowshift;

import org.academiadecodigo.thefellowshift.grid.GfxGrid;
import org.academiadecodigo.thefellowshift.grid.Grid;

public class Main {
    public static void main(String[] args) {
        Grid grid = new GfxGrid();
        Game game = new Game(grid);
        game.start();
    }
}