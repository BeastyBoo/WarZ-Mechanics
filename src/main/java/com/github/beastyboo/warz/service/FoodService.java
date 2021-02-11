package com.github.beastyboo.warz.service;

import com.github.beastyboo.warz.application.WarZ;
import com.github.beastyboo.warz.entity.Food;
import com.github.beastyboo.warz.port.FoodRepository;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FoodService implements FoodRepository {

    private final WarZ core;
    private final Map<Material, Food> foodMap;
    private final Map<UUID, Long> delayMap;

    public FoodService(WarZ core) {
        this.core = core;
        foodMap = new HashMap<>();
        delayMap = new HashMap<>();
    }

    @Override
    public void load() {
        //Load cache
    }

    @Override
    public void close() {
        //Save cache
    }

    @Override
    public boolean createFood(Player player, double health, int delay) {
        if(health <= 0) {
            return false;
        }

        Material material = player.getInventory().getItemInMainHand().getType();

        if(this.getFood(material) != null) {
            return false;
        }

        Food food = new Food(material, health, delay);
        foodMap.put(material, food);
        return true;
    }

    @Override
    public boolean deleteFood(Player player) {
        Material material = player.getInventory().getItemInMainHand().getType();
        Food food = this.getFood(material);

        if(food == null) {
            return false;
        }

        foodMap.remove(material, food);
        return true;
    }

    @Override
    public boolean useFood(Player player) {
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        Food food = this.getFood(itemInMainHand.getType());

        if(food == null) {
            return false;
        }

        if(delayMap.containsKey(player.getUniqueId())) {
            if(System.currentTimeMillis() < delayMap.get(player.getUniqueId())) {
                return false;
            }
            delayMap.remove(player.getUniqueId());
        }

        double health = player.getHealth();
        player.setHealth(health + food.getHealth());

        long delay = System.currentTimeMillis() + (food.getDelay() * 1000L);
        delayMap.put(player.getUniqueId(), delay);

        int amount = itemInMainHand.getAmount();
        if(amount > 1) {
            itemInMainHand.setAmount(amount - 1);
        } else {
            player.getInventory().remove(itemInMainHand);
        }
        return true;
    }

    @Override
    public Food getFood(Material material) {
        return foodMap.get(material);
    }

    @Override
    public Map<Material, Food> getFoods() {
        return foodMap;
    }
}
