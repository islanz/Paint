package org.academiadecodigo.thefellowshift.io.keyboard;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.util.HashMap;
import java.util.Map;

public class KeyboardInputMapper implements KeyboardHandler {

    private Map<Integer, Boolean> keyStates = new HashMap<>();
    private Keyboard keyboard = new Keyboard(this);

    private static final int[] KEY_CODES = {
          KeyboardEvent.KEY_UP,
            KeyboardEvent.KEY_DOWN,
            KeyboardEvent.KEY_LEFT,
            KeyboardEvent.KEY_RIGHT,
            KeyboardEvent.KEY_SPACE,
            KeyboardEvent.KEY_C,
            KeyboardEvent.KEY_L,
            KeyboardEvent.KEY_S
    };

    public KeyboardInputMapper() {
        setup();
    }

    public void setup() {
        for (int key : KEY_CODES) {
            for (KeyboardEventType type : KeyboardEventType.values()) {
                subscribe(key, type);
            }
        }
    }

    public void subscribe(int keyCode, KeyboardEventType type) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKeyboardEventType(type);
        event.setKey(keyCode);
        keyboard.addEventListener(event);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        keyStates.put(keyboardEvent.getKey(), true);
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        keyStates.put(keyboardEvent.getKey(), false);
    }

    public boolean isKeyPressed(int keyCode) {
        return keyStates.getOrDefault(keyCode, false);
    }
}
