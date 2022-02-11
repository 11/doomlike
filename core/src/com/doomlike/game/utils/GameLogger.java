package com.doomlike.game.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class GameLogger {
    private Logger logger = null;
    private File logFile;

    private GameLogger(String filename) {
        this.logFile = new File(filename);
    }

    public GameLogger create() {
        if (this.logger == null) {
            // this.logger = new GameLogger();
        }
        // return this.logger;
        return null;
    }

    public void destroy() {
        this.logger = null;
    }

    public void warning(String log) {
    }
}
