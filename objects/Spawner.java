package org.intellidev.ispawners.objects;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public class Spawner {

    private EntityType entityType;
    private UUID owner;
    private Location spawnerLocation;
    private int amount;
    private int stackedAmount;
    private int id;

    public Spawner(EntityType entityType, UUID owner, Location spawnerLocation, int id, int amount, int stackedAmount){
        this.entityType = entityType;
        this.owner = owner;
        this.spawnerLocation = spawnerLocation;
        this.id = id;
        this.amount = amount;
        this.stackedAmount = stackedAmount;
    }

    public void setEntityType(EntityType entityType){
        this.entityType = entityType;
    }

    public UUID getOwner(){
        return this.owner;
    }

    public Location getSpawnerLocation(){
        return this.spawnerLocation;
    }

    public int getId(){
        return this.id;
    }

    public EntityType getEntityType(){
        return this.entityType;
    }

    public void setStackedAmount(int stackedAmount){
        this.stackedAmount = stackedAmount;
    }
    public int getAmount(){
        return this.amount;
    }
    public int getStackedAmount() { return this.stackedAmount; }
}
