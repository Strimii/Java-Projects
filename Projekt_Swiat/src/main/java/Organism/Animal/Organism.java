package Organism.Animal;

import main.Action;
import main.ActionEnum;
import main.Position;
import main.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Organism {
    int power;
    int initiative;
    Position position;
    int liveLength;
    int powerToReproduce;
    String sign;
    World world;
    Position lastPosition;
    boolean isAlien;
    boolean isTime;


    public Organism(int power, int initiative, Position position, int liveLength, int powerToReproduce, String sign,World world,  boolean isAlien, boolean isTime) {
        this.power = power;
        this.initiative = initiative;
        this.position = position;
        this.liveLength = liveLength;
        this.powerToReproduce = powerToReproduce;
        this.sign = sign;
        this.world = world;
        this.lastPosition=this.position;
        this.isAlien = isAlien;
        this.isTime = isTime;
    }

    public void setIsAlien(boolean alien) {
        this.isAlien = alien;
    }

    public boolean getIsAlien() {
        return isAlien;
    }

    public void setIsTime(boolean time) {
        this.isTime = time;
    }

    public boolean getIsTime() {
        return this.isTime;
    }


    public Position getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Position position) {this.lastPosition = lastPosition; }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
       this.power = power;
    }

    public int getInitiative(){
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getLiveLength() {
        return liveLength;
    }

    public void setLiveLength(int liveLength) {
        this.liveLength = liveLength;
    }

    public int getPowerToReproduce() {
        return powerToReproduce;
    }

    public void setPowerToReproduce(int powerToReproduce) {
        this.powerToReproduce = powerToReproduce;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public abstract ArrayList move();
    public abstract void initParams();
    public abstract ArrayList action();
    public abstract Organism clone();

    @Override
    public String toString() {
        return String.format("Power: "+this.power+" Initiative: "+this.initiative+" Live Length: "+this.liveLength+" Position: "+this.position.toString());
    }

    public void roundComplete(Organism organism){
        if(!organism.getIsTime()){
            organism.setPower(organism.getPower()+1);
            organism.setLiveLength(organism.getLiveLength()-1);
        }


    }

    public boolean ifReproduce() {
        boolean result = false;

        if(this.getPower() >= this.getPowerToReproduce()) {
            result = true;
        }

        return result;
    }



    public ArrayList<Action> consequences(Organism atackingOrganism) {
        ArrayList<Action> result = new ArrayList<Action>();

        if(this.getPower() > atackingOrganism.getPower()) {
            result.add(new Action(ActionEnum.A_REMOVE, new Position( -1, -1), 0, atackingOrganism));
        }else {
            result.add(new Action(ActionEnum.A_REMOVE, new Position(-1, -1), 0, this));
        }
        return result;
    }


    public Position getCalculatedPosition(){
        Random ra=new Random();
        List<Position> freePositionsList = getWorld().getAllFreePosition(this.getPosition());
        int randomElement;
        if (freePositionsList.size() > 1) {
            randomElement = ra.nextInt(freePositionsList.size()-1);
        }
        else if (freePositionsList.size() == 1){
            randomElement = 0;
        }
        else {
            return null;
        }
        ArrayList<Action> result =new ArrayList<>();
        result.add(new Action(ActionEnum.A_INCREASEPOWER,freePositionsList.get(randomElement),this));
        return freePositionsList.get(randomElement);
    }

}