import java.util.Random;

public class Player {
    // Перечисление вариантов игры
    public enum VARIANTS {
        ROCK, PAPER, SCISSORS
    }

    // Поля класса Player
    private VARIANTS choice;
    private String name;

    // Конструктор по умолчанию для создания "бота"
    public Player() {
        this.name = "Bot";
        this.choice = randomChoice();
    }

    // Конструктор для создания игрока с заданным именем и выбором
    public Player(VARIANTS choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    // Метод для случайного выбора варианта игры
    private VARIANTS randomChoice() {
        VARIANTS[] values = VARIANTS.values();
        int randomIndex = new Random().nextInt(values.length);
        return values[randomIndex];
    }

    // Метод для определения победителя
    public String whoWins(Player p1, Player p2) {
        if (p1.choice == p2.choice) {
            return "Ничья";
        }

        if ((p1.choice == VARIANTS.ROCK && p2.choice == VARIANTS.SCISSORS) ||
            (p1.choice == VARIANTS.PAPER && p2.choice == VARIANTS.ROCK) ||
            (p1.choice == VARIANTS.SCISSORS && p2.choice == VARIANTS.PAPER)) {
            return "Победил игрок с именем: " + p1.name;
        } else {
            return "Победил игрок с именем: " + p2.name;
        }
    }
}
