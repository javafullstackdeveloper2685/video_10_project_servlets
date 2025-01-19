package org.sergei.game.DTOs;

import org.sergei.game.Entities.MageEntity;
import org.sergei.game.logging.GameLogger;

import java.util.Random;
import java.util.UUID;

public class Mage extends Player {

    public Mage() {
    }

    /**
     * Создаём нового мага
     */
    public Mage(String name) {
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
    public Mage(MageEntity mageEntity) {
        super(
                mageEntity.getUuid(),
                mageEntity.getName(),
                mageEntity.getLevel(),
                mageEntity.getExperience(),
                mageEntity.getAttackPower(),
                mageEntity.getHealthPower()
        );

        /*GameLogger.log("Mage was loaded from DB: " + this.toString());*/
    }

    /**
     * Конвертируем обратно в MageEntity для сохранения в БД
     */
    public MageEntity createEntity() {
        MageEntity mageEntity = new MageEntity();
        mageEntity.setUuid(this.uuid);
        mageEntity.setName(this.name);
        mageEntity.setLevel(this.lvl);
        mageEntity.setExperience(this.exp);
        mageEntity.setAttackPower(this.attackPower);
        mageEntity.setHealthPower(this.healthPower);
        return mageEntity;
    }


    @Override
    public void attack(Player player) {
        int hit = this.attackPower + new Random().nextInt(50);
        player.setHealthPower(player.getHealthPower()-hit);
        GameLogger.log(String.format(
                "Маг %s атакует %s на %d урона. Здоровье оппонента HP составляет: %d",
                this.name, player.getName(), hit, player.getHealthPower()
                ));
    }
}
