package org.sergei.game.logic;

import jakarta.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sergei.game.DTOs.Mage;
import org.sergei.game.DTOs.Warrior;
import org.sergei.game.Entities.MageEntity;
import org.sergei.game.Entities.WarriorEntity;
import org.sergei.game.logging.GameLogger;
import org.sergei.game.serviceDb.EntitiesDbService;

import java.util.Random;

public class Game {

    private final static Logger logger = LogManager.getLogger(Game.class);
    /**
     * Запуск игровой логики (сражения).
     *
     * @param newGame true, если начать новую игру, false — если продолжить
     */
    public static void startGame(boolean newGame) {
        // Очищаем лог
        GameLogger.clear();

        // // Инициализируем сервис для работы с БД
        EntitiesDbService dbService = new EntitiesDbService();
        EntityManager em = dbService.getEntityManager();

        // Заранее объявляем
        Warrior warrior;
        Mage mage;

        if (newGame) {
            //  Новые персонажи
            warrior = new Warrior("Aragorn");
            mage = new Mage("Gandalf");
           /* GameLogger.log("Созданы новые персонажи: " + warrior + " и " + mage);*/
        } else {
            // Продолжить игру
            GameLogger.log("Загружаю персонажей из БД..");
            MageEntity loadedMageEntity = EntitiesDbService.loadPlayer(em, MageEntity.class, "Gandalf");
            WarriorEntity loadedWarriorEntity = EntitiesDbService.loadPlayer(em, WarriorEntity.class, "Aragorn");

            if (loadedMageEntity == null || loadedWarriorEntity == null) {
                // // Если кого-то не нашли
                GameLogger.log("Не удалось найти персонажей в БД! Начинаем заново.");
                warrior = new Warrior("Aragorn");
                mage = new Mage("Gandalf");
            } else {
                mage = new Mage(loadedMageEntity);
                warrior = new Warrior(loadedWarriorEntity);
                /*GameLogger.log("Текущие данные персонажей:\\n\"" + mage + "\n" + warrior);*/

                // Если у кого-то HP <= 0, даём зелье
                if (mage.getHealthPower() <= 0) {
                    mage.setHealthPower(givePotion());
                    GameLogger.log("Здоровье мага было восстановлено!");
                }
                if (warrior.getHealthPower() <= 0) {
                    warrior.setHealthPower(givePotion());
                    GameLogger.log("Здоровье воина  было восстановлено!");
                }
            }
        }
        logger.debug("This is a debug message.");

        // Сам бой
        runBattle(mage, warrior);

        // Сохраняем результат обратно в БД
        persistPlayers(em, warrior, mage);

        // Закрываем EntityManager
        em.close();

    }

    /**
     * Сохранение/обновление игроков в БД
     */
    private static void persistPlayers(EntityManager em, Warrior warrior, Mage mage) {
        em.getTransaction().begin();
        em.merge(warrior.createEntity());
        em.merge(mage.createEntity());
        em.getTransaction().commit();
        GameLogger.log("Персонажи сохранены в БД.");
    }

    /**
     * Бой мага и воина
     */
    private static void runBattle(Mage mage, Warrior warrior) {
        GameLogger.log("Начинается сражение!");

        while (mage.getHealthPower() > 0 && warrior.getHealthPower() > 0) {
            // Атака мага
            mage.attack(warrior);
            mage.addExperience(10);

            if(warrior.getHealthPower()<=0){
                GameLogger.log(String.format("%s побеждает %s!", mage.getName(), warrior.getName()));
                break;
            }

            // Атака воина
            warrior.attack(mage);
            warrior.addExperience(10);

            if(mage.getHealthPower()<=0){
                GameLogger.log(String.format("%s побеждает %s!", warrior.getName(), mage.getName()));
                break;
            }
        }
    }


    private static int givePotion() {
        return 255 + new Random().nextInt(16);
    }


}
