package Organism.Animal;
import main.Action;
import main.ActionEnum;
import main.Position;
import main.World;

import java.util.ArrayList;

public abstract class Animal extends Organism {
    Position lastPosition;

    public Animal(int power, int initiative, Position position, int liveLength, int powerToReproduce, String sign, World world, boolean isAlien, boolean isTime) {
        super(power, initiative, position, liveLength, powerToReproduce, sign, world, isAlien, isTime);
        this.lastPosition = position;
    }
    @Override
    public ArrayList<Action> action() {
        ArrayList<Action> result = new ArrayList<Action>();
        Organism newAnimal;
        ArrayList<Position> birthPositions = getWorld().getAllFreePosition(this.getPosition());

        if(this.ifReproduce() && !birthPositions.isEmpty()) {

            Position newAnimalPosition = this.getCalculatedPosition();
            newAnimal = this.clone();
            newAnimal.initParams();
            newAnimal.setPosition(newAnimalPosition);
            this.setPower(this.getPower()/2);

            result.add(new Action(ActionEnum.A_ADD, newAnimalPosition, 0, newAnimal));
        }
        return result;
    }
}
