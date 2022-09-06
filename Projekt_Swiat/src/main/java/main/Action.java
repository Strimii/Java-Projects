package main;

import Organism.Animal.Organism;

public class Action {
    int __action;
    Position __position;
    int __value;
    Organism __organism;

    public Action(int action, Position position, int value, Organism organism){
        this.__action = action;
        this.__organism = organism;
        this.__value = value;
        this.__position = position;
    }

    public Action(int aIncreasepower, Position position, Organism organism) {
    }

    public int action() {
        return this.__action;
    }

    public Position position(){
        return this.__position;
    }

    public int value() {
        return this.__value;
    }

    public Organism organism() {
        return this.__organism;
    }

    public String __str__() {
        String[] choice = new String[4];

        choice[0] = this.__organism.getSign()+" Move from " + this.__organism.getPosition().x + ", " + this.__organism.getPosition().y + " to " + this.__position.x + ", "+this.__position.y;
        choice[1] = this.__organism.getSign()+" Remove from " + this.__organism.getPosition().x + ", " + this.__organism.getPosition().y;
        choice[2] = this.__organism.getSign()+" Add at " + this.__position.x + ", " + this.__position.y;
        choice[3] = this.__organism.getSign()+" Increase power" + this.__value;

        return choice[this.__action];
    }
}
