package Organism.Animal;

import javafx.geometry.Pos;
import main.Action;
import main.ActionEnum;
import main.Position;
import main.World;

import java.util.ArrayList;
import java.util.Random;

public class Alien extends Animal {
    public Alien(Position position, World world){

        super(0,6,position,200,200," A ",world, true, false);
    }




    @Override
    public ArrayList<Action> move(){

        for(int i = 0; i<getWorld().organisms.size(); i++) {
            getWorld().organisms.get(i).setIsTime(false);
        }

        ArrayList<Action> result = new ArrayList<Action>();
        ArrayList<Position> pomPosition = this.getWorld().getAllFreePosition(this.getPosition());
        Position newPosition;

        int l = pomPosition.size()-1;
        if(!pomPosition.isEmpty()){
            int r = 0;
            if(l==0){
                r = 0;
            }
            else{
                Random rand = new Random();
                r = rand.nextInt(l);
            }

            newPosition = pomPosition.get(r);
            result.add(new Action(ActionEnum.A_MOVE, newPosition, 0, this));
            this.setLastPosition(this.getPosition());
        }

        ArrayList<Position> neighboringPositions = this.getWorld().getNeighboringPositionsAlien(this.getPosition());

        for(int i = 0; i<neighboringPositions.size(); i++) {
            Organism organism = getWorld().getOrganismFromPosition(neighboringPositions.get(i));
            if(organism != null) {
                organism.setIsTime(true);
            }
        }

        return result;
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(6);
        this.setLiveLength(200);
        this.setPowerToReproduce(200);
        this.setSign(" A ");
        this.setIsAlien(true);
        this.setIsTime(false);
    }

    @Override
    public ArrayList<Action> action() {
        return null;
    }

    @Override
    public Organism clone() {
        return null;
    }
}
