import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest
{
    @Test
    public void testGetArmyPower(){
        int power = Game.g1.getArmyPower();

        assertEquals(5, power);
    }

    @Test
    public void testManevuerst() {
        Game.g1.maneuvers(1,1);
        int expectedXP = Game.g1.army.army[0].xp;

        assertEquals(2, expectedXP);
    }

    @Test
    public void testBuySoldier(){
        Game.g2.buySoldier(3);
        int Soldier = Game.g2.army.army[5].rank;

        assertEquals(3,Soldier);
    }
    @Test
    public void testMoneyAfterBuy(){
        Game.g2.buySoldier(3);
        double money_count = Game.g2.money;

        assertEquals(970, money_count,0.0);

    }
    @Test
    public void testLose(){
        Game.g1.buySoldier(4);
        Game.g1.attack(Game.g2);

        assertEquals(0,Game.g2.army.TotalNumberOfSoldiers);
    }
    @Test
    public void testMoneyAfterLose(){
        Game.g1.buySoldier(4);
        Game.g1.attack(Game.g2);

        assertEquals(900, Game.g2.money, 0.0);
    }
    @Test
    public void testMoneyAfterWin(){
        Game.g1.buySoldier(4);
        Game.g1.attack(Game.g2);

        assertEquals(1060, Game.g1.money, 0.0);
    }
    @Test
    public void testXpAfterWin(){
        Game.g1.buySoldier(4);
        Game.g1.attack(Game.g2);

        assertEquals(2, Game.g1.army.army[0].xp);
    }
    @Test
    public void testDraw(){
        Game.g1.attack(Game.g2);
        assertEquals(Game.g1.army.TotalNumberOfSoldiers, Game.g2.army.TotalNumberOfSoldiers);
    }
    @Test
    public void testCreateArmy(){
        Game.g1.army.createArmy();
        assertEquals(10, Game.g1.army.TotalNumberOfSoldiers);
    }
}
