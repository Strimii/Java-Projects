package Organism.Plants;

import Organism.Animal.Organism;
import main.Position;
import main.World;


public class Dendelion extends Plant {
    public Dendelion(Position position, World world){
        super(0,0,position,6,4," D ",world, false, false);

    }

    @Override
    public void initParams() {
        this.setInitiative(0);
        this.setLiveLength(6);
        this.setPower(0);
        this.setSign(" D ");
        this.setPowerToReproduce(3);
        this.setIsAlien(false);
        this.setIsTime(false);
    }

    @Override
    public Organism clone() {
        Dendelion dendelion=new Dendelion(getCalculatedPosition(),this.getWorld());
        return dendelion;
    }
}