import Organism.Animal.Alien;
import Organism.Animal.Sheep;
import Organism.Animal.Wolf;
import Organism.Plants.*;
import main.Action;
import main.Position;
import main.World;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class test{
    @Test
    public void testWolfAttack() {
        World jWorld = new World(8, 8);
        Wolf newOrgWolf = new Wolf(new Position(0, 1), jWorld);
        jWorld.addOrganism(newOrgWolf);

        Sheep newOrgSheep = new Sheep(new Position(0, 0), jWorld);
        jWorld.addOrganism(newOrgSheep);


        ArrayList<Action>result = new ArrayList<Action>(newOrgWolf.consequences(newOrgSheep));

        jWorld.makeMove(result.get(0));
        assertEquals(1, result.get(0).action());

    }

    @Test
    public void testSheepAttack() {
        World jWorld = new World(8, 8);
        Grass newOrgGrass = new Grass(new Position(0, 0), jWorld);

        jWorld.addOrganism(newOrgGrass);
        Sheep newOrgSheep = new Sheep(new Position(0, 1), jWorld);

        jWorld.addOrganism(newOrgSheep);
        ArrayList<Action>result = new ArrayList<Action>(newOrgGrass.consequences(newOrgSheep));

        jWorld.makeMove(result.get(0));
        assertEquals(1, result.get(0).action());

    }

    @Test
    public void testSheepMove() {
        World jWorld = new World(8, 8);
        Sheep newOrgSheep = new Sheep(new Position(0, 1), jWorld);

        jWorld.addOrganism(newOrgSheep);
        ArrayList<Action>result = new ArrayList<Action>(newOrgSheep.move());

        jWorld.makeMove(result.get(0));

        assertEquals(0, result.get(0).action());

    }

    @Test
    public void testFilterPositionsWithoutAnimals(){
        World jWorld = new World(8, 8);
        Sheep newOrgSheep = new Sheep(new Position(0, 0), jWorld);

        jWorld.addOrganism(newOrgSheep);
        Sheep newOrgSheep1 = new Sheep(new Position(0, 1), jWorld);

        jWorld.addOrganism(newOrgSheep1);

        assertEquals(2, newOrgSheep.getWorld().filterPositionsWithoutAnimals(newOrgSheep.getWorld().getNeighboringPositions(newOrgSheep.getPosition())).size());

    }

    @Test
    public void testStopTime() {
        World jWorld = new World(8, 8);
        Sheep newOrgSheep = new Sheep(new Position(0, 0), jWorld);

        jWorld.addOrganism(newOrgSheep);
        Alien newOrgAlien= new Alien(new Position(1, 1), jWorld);

        jWorld.addOrganism(newOrgAlien);
        jWorld.makeTurn();

        assertEquals(true, newOrgSheep.getIsTime());

    }

    @Test
    public void testReproduction(){
        World jWorld = new World(8, 8);
        Grass newOrgGrass = new Grass(new Position(0, 0), jWorld);

        jWorld.addOrganism(newOrgGrass);
        newOrgGrass.setPower(3);

        assertTrue(newOrgGrass.ifReproduce());
    }




}