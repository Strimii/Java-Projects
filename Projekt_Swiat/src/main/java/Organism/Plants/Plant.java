package Organism.Plants;

import Organism.Animal.Organism;
import main.Action;
import main.ActionEnum;
import main.Position;
import main.World;

import java.util.ArrayList;

public abstract class Plant extends Organism {
    public Plant(int power, int initiative, Position position, int liveLength, int powerToReproduce, String sign, World world, boolean isAlien, boolean isTime){
        super(power,initiative,position,liveLength,powerToReproduce,sign,world, isAlien, isTime);
    }

    @Override
    public ArrayList<Action> action() {
        ArrayList<Action> result = new ArrayList<Action>();
        Organism newPlant;
        ArrayList<Position> birthPositions = getWorld().getAllFreePosition(this.getPosition());

        if(this.ifReproduce() && !birthPositions.isEmpty()) {

            Position newAnimalPosition = this.getCalculatedPosition();
            newPlant = this.clone();
            newPlant.initParams();
            newPlant.setPosition(newAnimalPosition);
            this.setPower(this.getPower()/2);

            result.add(new Action(ActionEnum.A_ADD, newAnimalPosition, 0, newPlant));
        }
        return result;
    }

    @Override
    public ArrayList move() {
        return new ArrayList<>();
    }


}