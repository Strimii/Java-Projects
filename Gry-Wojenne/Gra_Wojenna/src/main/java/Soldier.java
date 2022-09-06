public class Soldier {
    public int rank;
    public int xp;
    public int power;

    public Soldier(int rank1, int xp1, int power1){
        rank = rank1;
        xp = xp1;
        power = power1;
    }
    public void checkRankUps(){
        double check = xp/5;
        if(check == rank) {
            rank ++;
            xp = 1;
        }
        power = rank * xp;
    }
    public void Lose(){
        xp --;
        checkRankUps();
    }
    public void Win(){
        xp ++;
        checkRankUps();
    }
}

