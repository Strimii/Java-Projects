package Organism.Animal;
import Organism.Plants.Plant;
import main.Action;
import main.ActionEnum;
import main.Position;
import main.World;
import java.util.Random;
import java.util.ArrayList;

public class Wolf extends Animal{

    public Wolf(Position position, World world){
        super(6,5,position,18,18," W ",world, false, false);
        Animal wolf;
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
            Organism metOrganism = this.getWorld().getOrganismFromPosition(newPosition);
            if(metOrganism != null && !(metOrganism instanceof Plant) && !(metOrganism instanceof Alien)){
                result.addAll(metOrganism.consequences(this));
            }
        }
        return result;
    }

    @Override
    public void initParams() {
        this.setPower(6);
        this.setInitiative(5);
        this.setLiveLength(18);
        this.setPowerToReproduce(14);
        this.setSign(" W ");
        this.setIsAlien(false);
        this.setIsTime(false);
    }

    @Override
    public Organism clone() {
        Wolf wolf=new Wolf(getCalculatedPosition(),this.getWorld());
        return wolf;
    }

    public ArrayList<Position> getNeighboringPositions() {
        return this.getWorld().filterPositionsWithOtherSpecies(this.getWorld().getNeighboringPositions(this.getPosition()), Wolf.class, Alien.class);
    }
}