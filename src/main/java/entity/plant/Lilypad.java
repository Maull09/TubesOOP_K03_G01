package entity.plant;

import javax.swing.ImageIcon;

import entity.zombie.Zombie;

public class Lilypad extends Plant{
    public Lilypad() {
        super("Lilypad", 25, 100, 0, 0, 0, 10, new ImageIcon("/resources/images/plant/Lilypad.png"));
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    @Override
    public ImageIcon getIcon() {
        return super.getIcon();
    }

    public String getImagePath() {
        return "/resources/images/plant/Lilypad.png";
    }





}