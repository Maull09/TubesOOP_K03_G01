package entity;

public class Sun {
    private static int totalSun;

    public Sun ()
    {
        Sun.totalSun = 25;
    }

    public void addSun(){
        Sun.totalSun += 25;
    }
    
    public void reduceSun(int sun){
        if (totalSun > sun)
        {
            Sun.totalSun -= sun;
        }
        else {
            return;
        }

    }
}