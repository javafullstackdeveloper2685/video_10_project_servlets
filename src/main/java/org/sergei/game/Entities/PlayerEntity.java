package org.sergei.game.Entities;


import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="player_entity")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="player_type", discriminatorType = DiscriminatorType.STRING)
public abstract class PlayerEntity {

    @Id
    @Column(name ="uuid", nullable = false, updatable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "lvl")
    private Integer level;

    @Column(name="exp")
    private Integer experience;

    @Column(name="health_power")
    private Integer healthPower;

    @Column(name="attack_power")
    private Integer attackPower;

    // Конструктор без аргументов (обязателен для JPA)
    public PlayerEntity() {
    }

    public PlayerEntity(UUID uuid, String name, Integer level, Integer experience, Integer healthPower, Integer attackPower) {
        this.uuid = uuid;
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.healthPower = healthPower;
        this.attackPower = attackPower;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getHealthPower() {
        return healthPower;
    }

    public void setHealthPower(Integer healthPower) {
        this.healthPower = healthPower;
    }

    public Integer getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(Integer attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(name, that.name) && Objects.equals(level, that.level) && Objects.equals(experience, that.experience) && Objects.equals(healthPower, that.healthPower) && Objects.equals(attackPower, that.attackPower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, level, experience, healthPower, attackPower);
    }
}
