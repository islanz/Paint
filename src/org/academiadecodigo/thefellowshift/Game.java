package org.academiadecodigo.thefellowshift;

import org.academiadecodigo.thefellowshift.constant.Constant;
import org.academiadecodigo.thefellowshift.grid.GfxGrid;
import org.academiadecodigo.thefellowshift.grid.Grid;

public class Game {
    private Grid grid;
    private BasicTimer timer;

    public Game(Grid grid) {
        this.grid = grid;
        timer = new BasicTimer(Constant.FRAMES_PER_SECOND);
    }

    public void start() {
        while(true) {
            timer.sync();
            grid.update();
        }
    }
}
