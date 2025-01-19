package org.sergei.game.web;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.sergei.game.logging.GameLogger;
import org.sergei.game.logic.Game;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private static final Gson gson = new Gson();

    /**
     * Метод GET:
     * Отображает страницу с двумя кнопками: "Новая игра" и "Продолжить игру".
     *
     * @param request
     * @param response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Перенаправляем на статическую страницу index.html
        request.getRequestDispatcher("/index.html").forward(request, response);

    }

    /**
     * Метод POST:
     * Обрабатывает выбор пользователя и запускает соответствующую игровую логику.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        boolean isNewGame = "new".equalsIgnoreCase(action);

        //Запуск игры
        Game.startGame(isNewGame);

        //Получение логов
        List<String> logs = GameLogger.getAllMessages();

        // Подготовка данных для JSON ответа
        Map<String, List<String>> logMap = new HashMap<>();
        logMap.put("logs", logs);

        String jsonResponse = gson.toJson(logMap);

        // Отправка логов как JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(jsonResponse);
    }


}
