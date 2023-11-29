import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Запустить игру в цикле на 1000 и вывести итоговый счет
 */
public class MontyHall {

    private final int [] door;
    private final Random random;

    public MontyHall() {
        this.door = new int[3];
        this.random = new Random();
        this.door[random.nextInt(0, door.length)] = 1;
    }

    /**
     * Метод игры.
     * @param countRound количество раундов.
     */
    public void game(int countRound){
        int countWinner = 0;
        int changeChoice = 0;
        HashMap<Integer, String> resultGame = new HashMap<>();
        for (int i = 0; i < countRound; i++) {
            System.out.println("Начинается раунд " + (i + 1));
            int chooseGamer = random.nextInt(0, door.length);
            System.out.println("Игрок выбирает дверь " + (chooseGamer + 1));
            int doorGoat = getDoorGoat(chooseGamer);
            System.out.printf("Ведущий открывает дверь %d и там находится КОЗА!!.\n", doorGoat + 1);
            System.out.printf(
                    "Итак, игрок выбрал дверь %d, как мы видим за дверью %d находится коза, хочет ли игрок изменить свой выбор?\n",
                    chooseGamer + 1,
                    doorGoat + 1);
            boolean changeDoor = random.nextBoolean();
            if (changeDoor){
                chooseGamer = getFinalDoor(chooseGamer, doorGoat);
                changeChoice++;
                System.out.printf("Игрок решил изменить свой выбор и открывает дверь. %d\n", chooseGamer + 1);
            }else{
                System.out.println("Игрок решил оставить свой выбор неизменным и открывает выбранную дверь.");
            }
            if (isWinner(chooseGamer)){
                countWinner++;
                resultGame.put(i + 1, "car");
                System.out.println("АВТОМОБИЛЬ!!! Поздравляем!!!");
            }else{
                System.out.println("КОЗА!!! Поздравляем!");
                resultGame.put(i + 1, "goat");
            }
            System.out.println();
        }
        System.out.printf("\nИгрок смог выиграть автомобиль %d раз(а)\n", countWinner);
        System.out.print("Игрок выиграл в раундах: ");
        for(Map.Entry<Integer, String> round : resultGame.entrySet()){
            if (round.getValue().equals("car"))
                System.out.print(round.getKey() + " ");
        }
        System.out.printf("\nИгрок менял свой выбор %d раз(а)\n", changeChoice);
    }

    /**
     * Получение двери с козой, для открытия ведущим.
     * @param chooseGamer дверь выбранная игроком.
     * @return дверь с козой.
     */
    private int getDoorGoat(int chooseGamer){
        int doorGoat;
        while (true){
            doorGoat = random.nextInt(0,3);
            if (doorGoat != chooseGamer && door[doorGoat] == 0)
                return doorGoat;
        }
    }

    /**
     * Смена двери игроком.
     * @param chooseGamer первоначальный выбор игрока.
     * @param doorGoat дверь с козой, открытая ведущим.
     * @return оставшаяся дверь.
     */
    private int getFinalDoor(int chooseGamer, int doorGoat){
        int finalDoor;
        while (true){
            finalDoor = random.nextInt(0,3);
            if (finalDoor != chooseGamer && finalDoor != doorGoat) {
                return finalDoor;
            }
        }
    }

    /**
     * Проверка победы.
     * @param chooseGamer выбор игрока.
     * @return true - если в массиве под индексом выбора игрока 1, иначе false.
     */
    private boolean isWinner(int chooseGamer){
        return door[chooseGamer] == 1;
    }
}