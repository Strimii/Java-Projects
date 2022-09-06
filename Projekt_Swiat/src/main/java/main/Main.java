package main;
import java.util.Scanner;

import Organism.Animal.Alien;
import Organism.Animal.Sheep;
import Organism.Animal.Wolf;
import Organism.Plants.Dendelion;
import Organism.Plants.Grass;
import Organism.Plants.Toadstool;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        World jWorld = new World(8, 8);

        Dendelion newOrgDendelion = new Dendelion(new Position(4, 2), jWorld);
        jWorld.addOrganism(newOrgDendelion);


        Grass newOrgGrass = new Grass(new Position(5, 1), jWorld);
        jWorld.addOrganism(newOrgGrass);


        Toadstool newOrgToadstool = new Toadstool(new Position(4, 5), jWorld);
        jWorld.addOrganism(newOrgToadstool);


        Sheep newOrgSheep = new Sheep(new Position(0, 1), jWorld);
        jWorld.addOrganism(newOrgSheep);


        Wolf newOrgWolf = new Wolf(new Position(1, 6), jWorld);
        jWorld.addOrganism(newOrgWolf);




        System.out.println();
        System.out.println(jWorld.__str__());
        for(int i = 0; i<100; i++) {

            System.out.println("");
            Scanner myObj = new Scanner(System.in);
            String x;
            x = myObj.nextLine();

            jWorld.makeTurn();
            System.out.println(jWorld.__str__());
        }
    }
}
