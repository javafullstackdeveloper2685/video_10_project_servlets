package org.sergei.game.DTOs;

import org.sergei.game.Entities.WarriorEntity;
import org.sergei.game.logging.GameLogger;

import java.util.Random;
import java.util.UUID;

public class Warrior extends Player{
    public Warrior() {
    }

    /**
     * Создаём нового мага
     */
    public Warrior(String name) {
        super(
                UUID.randomUUID(),
                name,
                0,
                0,
                10 + new Random().nextInt(6),
                255 + new Random().nextInt(16)
        );
    }

    /**
     * Восстанавливаем данные мага из MageEntity (БД)
     */
    public Warrior(WarriorEntity warriorEntity) {
        super(
                warriorEntity.getUuid(),
                warriorEntity.getName(),
                warriorEntity.getLevel(),
                warriorEntity.getExperience(),
                warriorEntity.getAttackPower(),
                warriorEntity.getHealthPower()
        );

        /*GameLogger.log("Warrior was loaded from DB: " + this.toString());*/
    }

    /**
     * Конвертируем обратно в MageEntity для сохранения в БД
     */
    public WarriorEntity createEntity() {
        WarriorEntity warriorEntity = new WarriorEntity();
        warriorEntity.setUuid(this.uuid);
        warriorEntity.setName(this.name);
        warriorEntity.setLevel(this.lvl);
        warriorEntity.setExperience(this.exp);
        warriorEntity.setAttackPower(this.attackPower);
        warriorEntity.setHealthPower(this.healthPower);
        return warriorEntity;
    }


    @Override
    public void attack(Player player) {
        int hit = this.attackPower + new Random().nextInt(50);
        player.setHealthPower(player.getHealthPower()-hit);
        GameLogger.log(String.format(
                "Воин %s атакует %s на %d урона. Здоровье оппонента HP составляет: %d",

                this.name, player.getName(), hit, player.getHealthPower()
        ));
    }
}
