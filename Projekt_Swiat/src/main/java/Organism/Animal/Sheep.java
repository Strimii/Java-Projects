package Organism.Animal;
import main.Action;
import main.ActionEnum;
import main.Position;
import main.World;
import java.util.Random;
import java.util.ArrayList;


public class Sheep extends Animal {
    public Sheep(Position position, World world){
        super(3,3,position,7,6," S ",world, false, false);

    }

    @Override
    public ArrayList<Action> move(){
        ArrayList<Action> result = new ArrayList<Action>();
        ArrayList<Position> pomPosition = this.getNeighboringPositions();
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
            Organism metOrganism = this.world.getOrganismFromPosition(newPosition);
            if(metOrganism != null){
                result.addAll(metOrganism.consequences(this));
            }
        }
        return result;
    }

    @Override
    public void initParams() {
        this.setPower(3);
        this.setInitiative(3);
        this.setLiveLength(7);
        this.setPowerToReproduce(6);
        this.setSign(" S ");
        this.setIsAlien(false);
        this.setIsTime(false);
    }

    @Override
    public Organism clone() {
        Sheep sheep=new Sheep(getCalculatedPosition(),this.getWorld());
        return sheep;
    }
    public ArrayList<Position> getNeighboringPositions() {
        return this.getWorld().filterPositionsWithoutAnimals(this.getWorld().getNeighboringPositions(this.getPosition()));
    }
}