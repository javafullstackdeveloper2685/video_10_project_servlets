package org.sergei.game.DTOs;

import java.util.UUID;

public abstract class Player {

    protected UUID uuid;
    protected String name;
    protected int lvl;
    protected int exp;
    protected int attackPower;
    protected int healthPower;

    public Player() {
    }

    public Player(UUID uuid, String name, int lvl, int exp, int attackPower, int healthPower) {
        this.uuid = uuid;
        this.name = name;
        this.lvl = lvl;
        this.exp = exp;
        this.attackPower = attackPower;
        this.healthPower = healthPower;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getHealthPower() {
        return healthPower;
    }

    public void setHealthPower(int healthPower) {
        this.healthPower = healthPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getExperience() {
        return exp;
    }

    public int getLevel() {
        return lvl;
    }

    public void setExperience(int experience) {
        this.exp = experience;
    }

    public void addExperience(int amount) {
        int newExp = this.exp + amount;
        if (newExp >= 100) {
            this.attackPower += 3;
            this.lvl++;
            this.exp = newExp - 100;
        } else {
            this.exp = newExp;
        }
    }

    public abstract void attack(Player player);

    @Override
    public String toString() {
        return "Player{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", lvl=" + lvl +
                ", exp=" + exp +
                ", attackPower=" + attackPower +
                ", healthPower=" + healthPower +
                '}';
    }
}
