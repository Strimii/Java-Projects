public class General {
    public double money = 1000;
    public Army army = new Army();

    public void maneuvers(int numberOfSoldiers, int rank) {
        int i = 0;
        int j = 0;
        while (j < numberOfSoldiers && i < army.TotalNumberOfSoldiers) {
            if (army.army[i].rank == rank && this.money >= army.army[i].rank) {
                army.army[i].xp++;
                this.money -= army.army[i].rank;
                army.army[i].checkRankUps();
                j++;
            }
            i++;

        }
    }
    public boolean buySoldier(int rank){
        int cost = rank * 10;

        if(cost <= this.money){
            army.addSoldier(rank,1, rank);
            this.money -= cost;
            return true;
        }
        else{
            return false;
        }
    }
    public void showArmy(){
        for(int i = 0;i< army.TotalNumberOfSoldiers;i++){
            System.out.println(i+1+") Stopień : " + army.army[i].rank + "\tXP : " + army.army[i].xp + "\tSiła : " + army.army[i].power+"\n");
        }
        System.out.println("Stan złotych monet generała - " + this.money);
    }
    public void attack(General enemyGeneral){
        int armyPower = getArmyPower();
        int enemyArmyPower = enemyGeneral.getArmyPower();
        if(enemyArmyPower > armyPower){
            enemyGeneral.money += this.money * 0.1;
            this.money -= this.money * 0.1 ;
            army.loseBattle();
            enemyGeneral.army.winBattle();

        }else if(enemyArmyPower == armyPower){
            army.randomVictim();
            enemyGeneral.army.randomVictim();
        }else{
            this.money += enemyGeneral.money * 0.1;
            enemyGeneral.money -= enemyGeneral.money * 0.1;
            army.winBattle();
            enemyGeneral.army.loseBattle();
        }

    }
    public int getArmyPower(){
        int totalPower = 0;
        for(int i=0; i < army.TotalNumberOfSoldiers;i++){
            totalPower += army.army[i].power;
        }
        return totalPower;
    }

}
