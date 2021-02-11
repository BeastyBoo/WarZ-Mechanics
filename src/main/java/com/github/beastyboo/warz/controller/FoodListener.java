package com.github.beastyboo.warz.controller;

import com.github.beastyboo.warz.application.WarZ;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class FoodListener implements Listener {

    private final WarZ core;

    public FoodListener(WarZ core) {
        this.core = core;
    }

    @EventHandler
    public void useFood(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if(!event.getHand().equals(EquipmentSlot.HAND)) {
                return;
            }

            core.getAPI().useFood(event.getPlayer());
        }
    }

}
