package com.github.beastyboo.warz.entity;

import org.bukkit.Material;

import java.util.Objects;

public class Food {

    private final Material material;
    private final double health;
    private final int delay;

    public Food(Material material, double health, int delay) {
        this.material = material;
        this.health = health;
        this.delay = delay;
    }

    public Material getMaterial() {
        return material;
    }

    public double getHealth() {
        return health;
    }

    public int getDelay() {
        return delay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Double.compare(food.health, health) == 0 && delay == food.delay && material == food.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, health, delay);
    }

    @Override
    public String toString() {
        return "Food{" +
                "material=" + material +
                ", health=" + health +
                ", delay=" + delay +
                '}';
    }
}
