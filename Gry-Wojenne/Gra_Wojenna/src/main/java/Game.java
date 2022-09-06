import com.google.gson.Gson;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Game {
    public static General g1 = new General();
    public static General g2 = new General();
    public static Scanner scan = new Scanner(System.in);
    public static Gson gson = new Gson();

    public static void main(String[] args) throws IOException {
        tour();
    }
        public static int game() {
            System.out.println("" +
                    "1)Wykonaj manewry\n" +
                    "2)Zaatakuj wrogiego generała\n" +
                    "3)Zakup żołnierza\n" +
                    "4)Wyświetl stan armi\n" +
                    "5)Zapisz stan armi\n" +
                    "6)Wczytaj stan armi\n" +
                    "7)--->>>\n" +
                    "8)Zakończ\n");

            int n = scan.nextInt();
            return n;
        }

        public static void tour() throws IOException {
            int player = 1;
            int a = 0;
            while(a != 8){
                a = game();
                System.out.println("Obecny general - " + player);
                switch(a) {
                    case 1: {
                        System.out.println("Ilu żołnierzy wysłać do manewrów?");
                        int count = scan.nextInt();
                        System.out.println("Podaj range żołnierza");
                        int rank = scan.nextInt();
                        if(player == 1) g1.maneuvers(count, rank);
                        else g2.maneuvers(count, rank);
                    }break;
                    case 2:{
                        if(player == 1) g1.attack(g2);
                        else g2.attack(g1);
                    }break;
                    case 3:{
                        System.out.println("Podaj range żołnierza");
                        int rank = scan.nextInt();
                        if(player == 1) g1.buySoldier(rank);
                        else g2.buySoldier(rank);
                    }break;
                    case 4:{
                        if(player==1) g1.showArmy();
                        else g2.showArmy();
                    }break;
                    case 5:{
                        save();
                    }break;
                    case 6: {
                        load();
                    }break;
                    case 7:{
                        if(player == 1) player = 2;
                        else player = 1;
                    }break;
                }




            }
        }

        public static void save() throws IOException {
            gson.toJson(g1, new FileWriter("C:\\Users\\Strimi\\Desktop\\data1.json"));
            gson.toJson(g2, new FileWriter("C:\\Users\\Strimi\\Desktop\\data2.json"));
        }
        public static void load() throws IOException {
            g1 = gson.fromJson(new FileReader("C:\\Users\\Strimi\\Desktop\\data1.json"), General.class);
            g2 = gson.fromJson(new FileReader("C:\\Users\\Strimi\\Desktop\\data2.json"), General.class);
        }



}
