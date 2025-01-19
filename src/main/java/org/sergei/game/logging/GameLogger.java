package org.sergei.game.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogger {

    private static final Logger logger = LogManager.getLogger(GameLogger.class);
    private static final List<String> messages = Collections.synchronizedList(new ArrayList<>());

    /**
     * Логирование сообщения.
     */
    public static void log(String msg) {
        logger.debug(msg);
        messages.add(msg);
    }

    /**
     * Получение всех сообщений.
     */
    public static List<String> getAllMessages() {
        return new ArrayList<>(messages);
    }

    /**
     * Очистка логов.
     */
    public static void clear() {
        messages.clear();
        logger.info("Логи очищены.");
    }

    public static void main(String[] args) {

            logger.info("Game started");
            logger.error("Game error");
            logger.debug("Debugging game start");
        if (!LogManager.getRootLogger().isDebugEnabled()) {
            System.err.println("Logger is not initialized correctly or debug level is not enabled.");
        }

    }
}
