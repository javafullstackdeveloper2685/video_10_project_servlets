package org.sergei.game.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Mage")
public class MageEntity extends PlayerEntity {
}
