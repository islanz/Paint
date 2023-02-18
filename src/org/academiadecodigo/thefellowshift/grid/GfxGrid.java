package org.academiadecodigo.thefellowshift.grid;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.thefellowshift.constant.Constant;
import org.academiadecodigo.thefellowshift.grid.node.Position;
import org.academiadecodigo.thefellowshift.grid.node.Square;
import org.academiadecodigo.thefellowshift.io.FileLoader;
import org.academiadecodigo.thefellowshift.io.FileSaver;
import org.academiadecodigo.thefellowshift.io.keyboard.KeyboardInputMapper;

import java.util.ArrayList;
import java.util.List;

public class GfxGrid implements Grid {

    private Rectangle grid;
    private Square[][] squares = new Square[Constant.COLS][Constant.ROWS];
    private Square cursor;
    private KeyboardInputMapper keyboardInputMapper;
    private FileSaver fileSaver;
    private FileLoader fileLoader;
    public GfxGrid() {
        grid = new Rectangle(Constant.PADDING, Constant.PADDING, Constant.COLS * Constant.CELL_SIZE, Constant.ROWS * Constant.CELL_SIZE);
        grid.draw();
        keyboardInputMapper = new KeyboardInputMapper();
        fileSaver = new FileSaver();
        fileLoader = new FileLoader();
        init();
    }

    @Override
    public void init() {
        for (int i = 0; i < Constant.COLS; i++) {
            for (int j = 0; j < Constant.ROWS; j++) {
                Position position = new Position(i, j);
                squares[i][j] = new Square(position);
            }
        }
        cursor = squares[0][0];
        cursor.showCursor();
    }

    @Override
    public void update() {
        if (keyboardInputMapper.isKeyPressed(KeyboardEvent.KEY_LEFT)) {
            if (!isCursorGoingOutOfBounds(-1, 0)) {
                moveCursor(-1, 0);
            }
        }
        if (keyboardInputMapper.isKeyPressed(KeyboardEvent.KEY_RIGHT)) {
            if (!isCursorGoingOutOfBounds(1, 0)) {
                moveCursor(1, 0);
            }
        }
        if (keyboardInputMapper.isKeyPressed(KeyboardEvent.KEY_UP)) {
            if (!isCursorGoingOutOfBounds(0, -1)) {
                moveCursor(0, -1);
            }
        }
        if (keyboardInputMapper.isKeyPressed(KeyboardEvent.KEY_DOWN)) {
            if (!isCursorGoingOutOfBounds(0, 1)) {
                moveCursor(0, 1);
            }
        }
        if (keyboardInputMapper.isKeyPressed(KeyboardEvent.KEY_SPACE)) {
            cursor.paint();
        }
        if (keyboardInputMapper.isKeyPressed(KeyboardEvent.KEY_C)) {
            clear();
        }
        if (keyboardInputMapper.isKeyPressed(KeyboardEvent.KEY_S)) {
            saveData();
        }
        if (keyboardInputMapper.isKeyPressed(KeyboardEvent.KEY_L)) {
            loadData();
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void moveCursor(int x, int y) {
        cursor.hideCursor();
        cursor = squares[cursor.getPosition().getX() + x][cursor.getPosition().getY() + y];
        cursor.showCursor();
    }

    private boolean isCursorGoingOutOfBounds(int x, int y) {

        int newX = cursor.getPosition().getX() + x;
        int newY = cursor.getPosition().getY() + y;

        return (newX < 0 || newX > Constant.COLS - 1) || (newY < 0 || newY > Constant.ROWS - 1);
    }

    private void clear() {
        for (int i = 0; i < Constant.COLS; i++) {
            for (int j = 0; j < Constant.ROWS; j++) {
               if (squares[i][j] == cursor) {
                   cursor.setFilled(false);
                   cursor.showCursor();
                   continue;
               }
                squares[i][j].clear();
            }
        }
    }

    public void saveData() {
        fileSaver.write(this.toString());
    }

    public void loadData() {
        List<String> data = fileLoader.load();

        List<Position> positions = new ArrayList<>();
        for (String d : data) {
            String[] splitCoordinates = d.split(" ");
            positions.add(new Position(Integer.parseInt(splitCoordinates[0]), Integer.parseInt(splitCoordinates[1])));

        }
        clear();
        for (int i = 0; i < Constant.COLS; i++) {
            for (int j = 0; j < Constant.ROWS; j++) {
                for (Position position : positions) {
                    if (position.getX() == squares[i][j].getPosition().getX() && position.getY() == squares[i][j].getPosition().getY()) {
                        squares[i][j].setColorAndFill();
                    }
                }
            }
        }
        cursor.showCursor();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Constant.COLS; i++) {
            for (int j = 0; j < Constant.ROWS; j++) {
                if(squares[i][j].isFilled()) {
                    result.append(squares[i][j]).append("\n");
                }
            }
        }
        return result.toString();
    }
}
