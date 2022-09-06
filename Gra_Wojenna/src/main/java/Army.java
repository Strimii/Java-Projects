import java.util.Random;
import java.util.Arrays;
public class Army {
    public int TotalNumberOfSoldiers = 0;
    public Soldier[] army = new Soldier[10000];

    public Army(){
        createArmy();
    }

    public void addSoldier(int rank, int xp, int power) {
        army[TotalNumberOfSoldiers] = new Soldier(rank, xp, power);
        TotalNumberOfSoldiers++;
    }
    public void createArmy() {
        for(int i=0; i<5; i++){
            addSoldier(1,1,1);
        }
    }
    public void winBattle(){
        for(int i = 0;i<TotalNumberOfSoldiers; i++) {
            army[i].Win();

        }
    }
    public void loseBattle(){
        int newIndex = 0;
        int l = 10000;
        Soldier[] newArmy = new Soldier[l];
        int newNumberOfSoldiers = 0;
        for (int i = 0; i<TotalNumberOfSoldiers; i++) {
            army[i].Lose();
            if(army[i].xp != 0) {
                newArmy[newIndex] = army[i];
                newIndex++;
                newNumberOfSoldiers++;
            }
        }
        army = newArmy;
        TotalNumberOfSoldiers = newNumberOfSoldiers;
    }

    public void randomVictim() {
        Random randomNumber = new Random();
        int randomSoldier = randomNumber.nextInt(TotalNumberOfSoldiers);
        int l = 10000;
        int newIndex = 0;
        Soldier[] newArmy = new Soldier[l];

        for (int i = 0; i<TotalNumberOfSoldiers; i++) {
            if(i != randomSoldier) {
                newArmy[newIndex] = army[i];
                newIndex++;
            }
        }
        army = newArmy;
        TotalNumberOfSoldiers--;
    }
    public static void main(String[] args) {




    }
}
