package entity.zombie;

import util.ListOf;
import util.Moveable;
import data.GameState;
import data.TimeKeeper;
import entity.Entity;
import entity.plant.Plant;
import manager.Tile;

public class Zombie extends Entity implements Moveable{
    private boolean is_aquatic;
    private boolean isFaster;
    private boolean isSlowed;
    private int lastMoveTime;
    private double partialMove;


    public Zombie(String name, int health, int attackDamage, int attackSpeed, boolean is_aquatic, int row, int col, boolean isAlive, boolean isFaster, boolean isSlowed) {
        super(name, health, attackDamage, attackSpeed, row, col, true);
        this.is_aquatic = is_aquatic;
        this.isFaster = false;
        this.isSlowed = false;
    }

    public boolean getIsAquatic() {
        return is_aquatic;
    }

    public void setIsAquatic(boolean is_aquatic) {
        this.is_aquatic = is_aquatic;
    }

    public boolean getIsFaster() {
        return isFaster;
    }

    public void setIsFaster(boolean isFaster) {
        this.isFaster = isFaster;
    }

    public boolean getIsSlowed() {
        return isSlowed;
    }

    public void setIsSlowed(boolean isSlowed) {
        this.isSlowed = isSlowed;
    }

    public int getLastMoveTime() {
        return lastMoveTime;
    }

    public void setLastMoveTime(int lastMoveTime) {
        this.lastMoveTime = lastMoveTime;
    }

    public double getPartialMove() {
        return partialMove;
    }

    public void setPartialMove(double partialMove) {
        this.partialMove = partialMove;
    }

    public boolean canMove(int currentTime) {
        int moveInterval = 5; // 5 seconds
        if (isFaster) {
            moveInterval /= 1.5; // Increase speed by 50%
        } else if (isSlowed) {
            moveInterval *= 1.5; // Decrease speed by 50%
        }
        return (currentTime - lastMoveTime) >= moveInterval;
    }

    public void move() {
        this.partialMove += 1.0 / 5.0; // Move 1/5 of a tile
        if (this.partialMove >= 1.0) {
            this.setCol(this.getCol() - 1);
            this.partialMove = 0.0;
        }
        this.lastMoveTime = TimeKeeper.getInstance().getCurrentTime();
    }

    public void die() {
        super.setIsAlive(false);
        System.out.println(this.getName() + " died!");
    }

    @Override
    public void move(Zombie zombie) {}
    public void attack(Plant plant){}
    public void attack(ListOf<Entity> tile){}
    
    @Override
    public void attackPlants() {
        int currentTime = TimeKeeper.getInstance().getCurrentTime();
        if (currentTime - this.getlastAttackTime() >= this.getAttackSpeed()) {
            for (int col = this.getCol() + 1; col < GameState.getInstance().getGameMap().getCols(); col++) {
                Tile tile = GameState.getInstance().getGameMap().getTile(this.getRow(), col);
                if (!tile.getPlants().isEmpty()) {
                    // Directly attack the first zombie in the tile
                    Plant targetPlant = tile.getPlants().get(0);
                    targetPlant.takeDamage(this.getAttackDamage());
                    if (!targetPlant.getIsAlive()) {
                        tile.removePlant(targetPlant);
                    }
                    this.setlastAttackTime(currentTime); // Update last attack time
                    break;
                }
            }
        }
    }}

