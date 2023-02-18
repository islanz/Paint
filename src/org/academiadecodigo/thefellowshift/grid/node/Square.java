package org.academiadecodigo.thefellowshift.grid.node;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.thefellowshift.constant.Constant;

public class Square {

    private Rectangle square;
    private Position position;
    private boolean isFilled;

    public Square(Position position) {
        this.position = position;
        square = new Rectangle(position.getX() * Constant.CELL_SIZE + Constant.PADDING, position.getY() * Constant.CELL_SIZE + Constant.PADDING, Constant.CELL_SIZE, Constant.CELL_SIZE);
        square.draw();
    }

    public void showCursor() {
        if (isFilled) {
            square.setColor(Constant.CURSOR_OVERLAPPING_COLOR);
        } else {
            square.setColor(Constant.CURSOR_COLOR);
        }
        square.fill();
    }

    public void hideCursor() {
        if (isFilled) {
            square.setColor(Constant.SQUARE_FILL_COLOR);
        } else {
            square.setColor(Constant.GRID_COLOR);
            square.draw();
        }
    }

    public void paint() {
        if (isFilled) {
            clear();
        } else {
            square.setColor(Constant.SQUARE_FILL_COLOR);
            isFilled = true;
        }
        showCursor();
    }

    public void clear() {
        square.setColor(Constant.GRID_COLOR);
        square.draw();
        isFilled = false;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public void setColorAndFill() {
        square.setColor(Constant.SQUARE_FILL_COLOR);
        square.fill();
        isFilled = true;
    }
    @Override
    public String toString() {
            return String.format("%s %s", position.getX(), position.getY());
    }
}
