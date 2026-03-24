import java.util.*;

public class GuessingGame {

    Scanner dzh = new Scanner(System.in);
    Random rand = new Random();

    String playerName;
    int secretNumber;
    int attempts;
    int maxAttempts = 10;

    public void startGame() {

        System.out.println("========================================");
        System.out.println("    WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");

        System.out.print("Enter your name: ");
        playerName = dzh.nextLine();

        boolean playAgain = true;

        while (playAgain) {
            displayWelcome();
            playGame();
            displayStats();
            playAgain = askPlayAgain();
        }

        System.out.println("\n========================================");
        System.out.println("Thanks for playing, " + playerName + "!");
        System.out.println("See you next time!");
        System.out.println("========================================");
    }

    void displayWelcome() {
        System.out.println("\n========================================");
        System.out.println("Hello, " + playerName + "!");
        System.out.println("\nI'm thinking of a number between 1 and 100.");
        System.out.println("You have 10 attempts to guess it.");
        System.out.println("I'll give you a hint after each guess.");
        System.out.println("\nLet's begin!");
        System.out.println("========================================");

        secretNumber = generateSecretNumber();
        attempts = 0;
    }

    int generateSecretNumber() {
        return rand.nextInt(100) + 1;
    }

    int getUserGuess() {
        int guess;

        while (true) {
            System.out.print("Enter your guess (1-100): ");
            guess = dzh.nextInt();

            if (guess >= 1 && guess <= 100) {
                break;
            } else {
                System.out.println("Invalid! Please enter a number between 1 and 100.");
            }
        }
        return guess;
    }

    void giveHint(int guess) {
        if (guess < secretNumber) {
            System.out.println("Too low! Try a higher number.");
        } else if (guess > secretNumber) {
            System.out.println("Too high! Try a lower number.");
        }
    }

    void playGame() {
        boolean guessed = false;

        while (attempts < maxAttempts) {
            attempts++;

            System.out.println("\n--- Attempt #" + attempts + " ---");

            int guess = getUserGuess();

            if (guess == secretNumber) {
                System.out.println("\nCongratulations " + playerName + "!");
                System.out.println("You guessed the number " + secretNumber + " in " + attempts + " attempts!");
                guessed = true;
                break;
            } else {
                giveHint(guess);
            }
        }

        if (!guessed) {
            System.out.println("\nGAME OVER!");
            System.out.println("You've used all " + maxAttempts + " attempts.");
            System.out.println("The secret number was " + secretNumber + ".");
            System.out.println("Better luck next time, " + playerName + "!");
        }
    }

    void displayStats() {
        System.out.println("\n========================================");
        System.out.println("            GAME STATISTICS");
        System.out.println("========================================");
        System.out.println("Player: " + playerName);
        System.out.println("Secret Number: " + secretNumber);
        System.out.println("Attempts Used: " + attempts);
        System.out.println("Rating: " + getRating());
        System.out.println("========================================");
    }

    String getRating() {
        if (attempts == 1) return "Perfect!";
        else if (attempts <= 3) return "Excellent!";
        else if (attempts <= 6) return "Good job!";
        else if (attempts <= 10) return "Nice try!";
        else return "Better luck next time!";
    }

    boolean askPlayAgain() {
        System.out.print("\nWould you like to play again, " + playerName + "? (Y/N): ");
        String ans = dzh.next();
        return ans.equalsIgnoreCase("Y");
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        game.startGame();
    }
}