package entity.plant;

import javax.swing.ImageIcon;

import entity.zombie.Zombie;
// package src.entity;

public class Pumpkin extends Plant{
    public Pumpkin(int row, int col) {
        super("Pumpkin", 125, 4000, 0, 0, 0, 20, new ImageIcon("/resources/images/plant/Pumpkin.png"), row, col);
    }

    public void attack(Zombie zombie)
    {
        zombie.setHealth(getHealth()-getAttackDamage());
    }

    public String getImagePath() {
        return "/resources/images/plant/Pumpkin.png";
    }



}