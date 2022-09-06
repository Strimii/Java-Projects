package Organism.Plants;

import Organism.Animal.Organism;
import main.Position;
import main.World;


public class Grass extends Plant{
    public Grass(Position position, World world){

        super(0,0,position,6,3," G ",world, false, false);
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(6);
        this.setSign(" G ");
        this.setPowerToReproduce(3);
        this.setIsAlien(false);
        this.setIsTime(false);
    }

    @Override
    public Organism clone() {
        Grass grass=new Grass(getCalculatedPosition(),this.getWorld());
        return grass;
    }


}