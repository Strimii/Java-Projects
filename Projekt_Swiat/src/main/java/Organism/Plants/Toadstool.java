package Organism.Plants;

import Organism.Animal.Organism;
import main.Action;
import main.ActionEnum;
import main.Position;
import main.World;


import java.util.ArrayList;

public class Toadstool extends Plant {
    public Toadstool(Position position, World world) {

        super(2, 0, position, 9, 6, " T ", world, false, false);
    }

    public ArrayList<Action> consequences(Organism atackingOrganism) {
        ArrayList<Action> result = new ArrayList<Action>();

        if (this.getPower() > atackingOrganism.getPower()) {
            result.add(new Action(ActionEnum.A_REMOVE, new Position(-1, -1), 0, atackingOrganism));
        } else {
            result.add(new Action(ActionEnum.A_REMOVE, new Position(-1, -1), 0, this));
            result.add(new Action(ActionEnum.A_REMOVE, new Position(-1, -1), 0, atackingOrganism));
        }

        return result;
    }

    @Override
    public void initParams() {
        this.setInitiative(0);
        this.setLiveLength(9);
        this.setPower(0);
        this.setSign(" T ");
        this.setPowerToReproduce(7);
        this.setIsAlien(false);
        this.setIsTime(false);
    }

    @Override
    public Organism clone() {
        Toadstool toadstool = new Toadstool(getCalculatedPosition(), this.getWorld());
        return toadstool;
    }


}




