package com.github.beastyboo.warz.port;

import com.github.beastyboo.warz.entity.Food;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;

public interface FoodRepository {

    void load();

    void close();

    boolean createFood(Player player, double health, int delay);

    boolean deleteFood(Player player);

    boolean useFood(Player player);

    Food getFood(Material material);

    Map<Material, Food> getFoods();

}
