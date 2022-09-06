package main;

public class Position {
    public int x;
    public int y;

    public Position(Integer xPosition, Integer yPosition){

        this.x = xPosition;
        this.y = yPosition;


        }


    public int x(){
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int y() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean eq(Position other) {
        return (this.x == other.x) && (this.y == other.y);
    }

    public String __str__(){
        return "({" + this.x + "}, {"+this.y+"})";
    }
}
