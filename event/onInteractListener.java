package org.intellidev.ispawners.events;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.BlockStateMeta;
//more imports

public class onInteractListener implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        Player p = e.getPlayer();



            if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){

                if(e.getClickedBlock().getType().equals(Material.MOB_SPAWNER)) {

                    if(p.getItemInHand().getType().equals(Material.MOB_SPAWNER)){

                        CreatureSpawner placedSpawner = (CreatureSpawner) e.getClickedBlock().getState();
                        EntityType entityType = placedSpawner.getSpawnedType();

                        BlockStateMeta meta = (BlockStateMeta) p.getItemInHand().getItemMeta();
                        BlockState state = meta.getBlockState();
                        CreatureSpawner spawner_in_hand = (CreatureSpawner) state;
                        EntityType entityType_in_hand = spawner_in_hand.getSpawnedType();


                        if(entityType != entityType_in_hand){
                            p.sendMessage(StringUtils.format("&cThese are different types of spawners."));
                            e.setCancelled(true);
                        } else{

                            for(Spawner spawners : SpawnerManager.SPAWNERS){
                                if(spawners.getSpawnerLocation().equals(e.getClickedBlock().getLocation())){
                                    spawners.setStackedAmount(spawners.getStackedAmount() + 1);
                                    if(p.getInventory().getItemInHand().getAmount() > 1) {

                                        p.getInventory().getItem(p.getInventory().getHeldItemSlot()).setAmount(p.getItemInHand().getAmount() - 1);

                                    } else{
                                        p.getInventory().remove(p.getItemInHand());
                                        p.updateInventory();
                                    }
                                    SpawnerManager.saveSpawner(spawners);
                                    int current = ISpawners.getInstance().getIslandLevel(p.getUniqueId());
                                    ISpawners.getInstance().setIslandLevel(p.getUniqueId(), current + 1);

                                    SpawnerManager.register(e.getPlayer().getUniqueId(), "STACKED: " + entityType.name() + " at: x: "
                                                    + spawners.getSpawnerLocation().getBlock().getX() + ", y: " + spawners.getSpawnerLocation().getBlock().getY()
                                    + ", z: " + spawners.getSpawnerLocation().getBlock().getZ());

                                }
                            }
                            e.setCancelled(true);
                        }

                        e.setCancelled(true);
                    }
                    }

        }

    }
}
