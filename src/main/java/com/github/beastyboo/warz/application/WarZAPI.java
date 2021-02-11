package com.github.beastyboo.warz.application;

import com.github.beastyboo.warz.entity.Food;
import com.github.beastyboo.warz.port.FoodRepository;
import com.github.beastyboo.warz.service.FoodService;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;

public class WarZAPI {

    private final WarZ core;

    private FoodRepository foodRepository;

    public WarZAPI(WarZ core) {
        this.core = core;
    }

    void start() {
        initialize();
        foodRepository.load();
    }

    void close() {
        foodRepository.close();
    }

    //API start
    public boolean createFood(Player player, double health, int delay) {
        return foodRepository.createFood(player, health, delay);
    }

    public boolean deleteFood(Player player) {
        return foodRepository.deleteFood(player);
    }

    public boolean useFood(Player player) {
        return foodRepository.useFood(player);
    }

    public Food getFood(Material material) {
        return foodRepository.getFood(material);
    }

    public Map<Material, Food> getFoods() {
        return foodRepository.getFoods();
    }
    //API END

    private void initialize() {
        foodRepository = new FoodService(core);
    }

}
