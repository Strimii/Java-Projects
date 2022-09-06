package main;

import Organism.Animal.Alien;
import Organism.Animal.Organism;
import Organism.Plants.Plant;
import Organism.Animal.Animal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;


public class World {
    public int worldX;
    public int worldY;
    public int turn;
    public ArrayList<Organism>organisms = new ArrayList<Organism>();
    public ArrayList<Organism>newOrganisms = new ArrayList<Organism>();
    public String separator;

    public World(int worldX, int worldY){
        this.worldX = worldX;
        this.worldY = worldY;
        this.turn = 0;
        this.separator = " - ";
    }

    public int worldX() {
        return this.worldX;
    }

    public int worldY() {
        return this.worldY;
    }

    public int turn() {
        return this.turn;
    }

    public ArrayList<Organism> organisms() {
        return this.organisms;
    }

    public void setOrganisms(ArrayList<Organism> organisms) {
        this.organisms = organisms;
    }

    public ArrayList<Organism> newOrganisms(){
        return this.newOrganisms;
    }

    public void setNewOrganisms(ArrayList<Organism> newOrganisms) {
        this.newOrganisms = newOrganisms;
    }

    public String separator(){
        return this.separator;
    }

    public void makeTurn(){
        this.addAlien();
        ArrayList<Action> actions = new ArrayList<Action>();

        for (Organism organism : this.organisms) {
            if (this.positionOnBoard(organism.getPosition()) && organism.getIsTime() == false) {
                actions.addAll(organism.move());
                for (int j = 0; j < actions.size(); j++) {
                    this.makeMove((Action) actions.get(j));
                }

                actions.clear();

                if (this.positionOnBoard(organism.getPosition()) && !organism.getIsAlien()) {
                    actions.addAll(organism.action());

                    for (int j = 0; j < actions.size(); j++) {
                        this.makeMove((Action) actions.get(j));
                    }

                    actions.clear();
                }
            }
        }

        ArrayList<Organism>organisms2 = new ArrayList<Organism>();

        for (int i = 0; i<this.organisms.size(); i++) {
            if(this.positionOnBoard(this.organisms.get(i).getPosition())){
                organisms2.add(this.organisms.get(i));
            }
        }
        this.organisms = organisms2;

        for(int i = 0; i<this.organisms.size(); i++) {
            this.organisms.get(i).roundComplete(this.organisms.get(i));

            if(this.organisms.get(i).getLiveLength() < 1){
                System.out.println(this.organisms.get(i).getSign()+" Died of old age at: " + this.organisms.get(i).getPosition().x + " " + this.organisms.get(i).getPosition().y);
            }
        }

        ArrayList<Organism>organisms3 = new ArrayList<Organism>();
        for (int i = 0; i<this.organisms.size(); i++) {
            if(this.organisms.get(i).getLiveLength() > 0){
                organisms3.add(this.organisms.get(i));
            }
        }
        this.organisms = organisms3;

        ArrayList<Organism>newOrganisms2 = new ArrayList<Organism>();

        for(int i = 0; i< this.newOrganisms.size(); i++) {
            if(this.positionOnBoard(this.newOrganisms.get(i).getPosition())){
                newOrganisms2.add(this.newOrganisms.get(i));
            }
        }
        this.newOrganisms = newOrganisms2;
        this.organisms.addAll(this.newOrganisms);
        this.organisms.sort(Comparator.comparing(Organism::getInitiative).reversed());
        this.newOrganisms.clear();

        this.turn += 1;
        this.removeAlien();
    }
//ok
    public void makeMove(Action action) {
        System.out.println(action.__str__());
        if(action.__action == ActionEnum.A_ADD){
            this.newOrganisms.add(action.__organism);
        }else if(action.__action == ActionEnum.A_INCREASEPOWER){
            int x = action.__organism.getPower();
            action.__organism.setPower(action.__value + x); ;
        }else if(action.__action == ActionEnum.A_MOVE) {
            action.__organism.setPosition(action.__position);
        }else if(action.__action == ActionEnum.A_REMOVE){
            action.__organism.setPosition(new Position(-1, -1)) ;
        }
    }

    public boolean addOrganism(Organism newOrganisms){
        Position newOrganismPosition = new Position(newOrganisms.getPosition().x, newOrganisms.getPosition().y);

        if(this.positionOnBoard(newOrganismPosition)) {
            this.organisms.add(newOrganisms);
            this.organisms.sort(Comparator.comparing(Organism::getInitiative).reversed());
            return true;
        }

        return false;
    }

    public boolean positionOnBoard(Position position) {
        return position.x >= 0 && position.y >= 0 && position.x < this.worldX && position.y < this.worldY;
    }

    public ArrayList<Position> getAllFreePosition(Position position){
        ArrayList<Position> freePos =new ArrayList<>();
        int posX=position.x;
        int posY=position.y;
        for(int y=-1;y<=1;y++){
            for(int x=-1;x<=1;x++){
                Organism organism=getOrganismFromPosition(new Position(posX+x,posY+y));
                if(this.positionOnBoard(new Position(posX+x,posY+y))) {
                    if (organism == null) {
                        freePos.add(new Position(posX + x, posY + y));
                    }
                }
            }
        }
        if(freePos.isEmpty()){
        }
        return freePos;
    }
    public ArrayList<Position> getNeighboringPositions(Position position) {
        ArrayList<Position> result = new ArrayList<Position>();
        Position pomPosition = null;

        for(int y = -1; y<2; y++){
            for(int x = -1; x<2; x++) {
                pomPosition = new Position(position.x+x, position.y+y);
                if(this.positionOnBoard(pomPosition) && !(y == 0 && x == 0 )){
                    result.add(pomPosition);
                }
            }
        }
        return result;
    }

    public ArrayList<Position> getNeighboringPositionsAlien(Position position) {
        ArrayList<Position> result = new ArrayList<Position>();
        Position pomPosition = null;

        for(int y = -2; y<3; y++){
            for(int x = -2; x<3; x++) {
                pomPosition = new Position(position.x+x, position.y+y);
                if(this.positionOnBoard(pomPosition) && !(y == 0 && x == 0 )){
                    result.add(pomPosition);
                }
            }
        }
        return result;
    }

    public Organism getOrganismFromPosition(Position position){
        Organism pomOrganism = null;

        for (Organism organism : this.organisms) {
            if (organism.getPosition().x == position.x && organism.getPosition().y == position.y) {
                pomOrganism = organism;
                break;
            }
        }

        if(pomOrganism == null) {
            for (Organism newOrganism : this.newOrganisms) {
                if (newOrganism.getPosition().x == position.x && newOrganism.getPosition().y == position.y) {
                    pomOrganism = (newOrganism);
                    break;
                }
            }
        }

        return pomOrganism;
    }

    public void addAlien() {
        boolean flag = true;

        for (Organism organism : this.organisms) {
            if (organism.getIsAlien()) {
                flag = false;
                break;
            }
        }

        if(flag) {
            Random rand = new Random();
            int chance = rand.nextInt(4);
            if(chance == 1) {
                ArrayList<Position> freePosition = new ArrayList<Position>(this.filterFreePositions());
                int number = rand.nextInt(freePosition.size());
                int x = freePosition.get(number).x;
                int y =  freePosition.get(number).y;
                Alien newOrgAlien = new Alien(new Position(x, y), this);
                newOrgAlien.initParams();
                this.addOrganism(newOrgAlien);
                System.out.println("ALIEN APPEARED ON BOARD (" + x + ", " + y + ")");
            }
        }
    }

    public void removeAlien() {
        boolean flag = false;

        for (Organism organism : this.organisms) {
            if (organism.getIsAlien()) {
                flag = true;
                break;
            }
        }

        if(flag) {
            Random rand = new Random();
            int chance = rand.nextInt(4);
            ArrayList<Organism> organisms = new ArrayList<Organism>();
            if (chance == 1) {
                for(int i = 0; i<this.organisms.size(); i++) {
                    if(!this.organisms.get(i).getIsAlien()){
                        organisms.add(this.organisms.get(i));
                    }else {
                        System.out.println("ALIEN VANISHED FROM BOARD (" + this.organisms.get(i).getPosition().x + ", " + this.organisms.get(i).getPosition().y + ")");
                    }
                }

                this.organisms.clear();
                this.organisms.addAll(organisms);
                for(int i = 0; i<this.organisms.size(); i++) {
                    this.organisms.get(i).setIsTime(false);
                }
            }
        }
    }


    public ArrayList<Position> filterFreePositions() {
        ArrayList<Position> freePositions = new ArrayList<Position>();

        for(int i = 0; i<8; i++) {
            for(int j = 0; j<8; j++) {
                Organism organism = getOrganismFromPosition(new Position(i, j));
                if(organism == null) {
                    freePositions.add(new Position(i, j));
                }
            }
        }
        return freePositions;
    }

    public ArrayList<Position> filterPositionsWithoutAnimals(ArrayList<Position> fields) {
        ArrayList<Position> result = new ArrayList<Position>();

        Organism pomOrganism = null;
        for (Position filed : fields) {
            pomOrganism = this.getOrganismFromPosition(filed);
            if (pomOrganism == null || pomOrganism instanceof Plant) {
                result.add(filed);
            }
        }

        return result;
    }

    public ArrayList<Position> filterPositionsWithOtherSpecies(ArrayList<Position> fields, Class<?> cl, Class<?> ca) {
        ArrayList<Position> result = new ArrayList<Position>();
        Organism pomOrganism = null;

        for (Position field : fields) {
            pomOrganism = this.getOrganismFromPosition(field);
            if (!cl.isInstance(pomOrganism) && !ca.isInstance(pomOrganism)) {
                result.add(field);
            }
        }
        return result;
    }

    public String __str__() {
        String result = "\nturn: " + this.turn + "\n";

        for(int wY = 0; wY<this.worldY; wY++){
            for(int wX = 0; wX<this.worldX; wX++) {
                Organism org = this.getOrganismFromPosition(new Position(wX, wY));
                if(org != null){
                    result += org.getSign();
                }else {
                    result += this.separator;
                }
            }
            result+= "\n";
        }
        return result;
    }
}
