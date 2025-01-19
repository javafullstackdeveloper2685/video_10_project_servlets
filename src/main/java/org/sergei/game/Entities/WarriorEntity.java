package org.sergei.game.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Warrior")
public class WarriorEntity extends PlayerEntity{
}
