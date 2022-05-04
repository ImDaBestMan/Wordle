import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

class Wordle
{
    int guesses;
    String secretWord;
    String[] playerGuesses;
    Wordle()
    {
        this.guesses = 6;

        try {
            this.secretWord = chooseWord();
        } catch (FileNotFoundException e) {
            System.out.println("Word list file not found.");
        }

        this.playerGuesses = new String[6];
    }
    
    void guessWord() throws FileNotFoundException
    {
        Scanner s = new Scanner(System.in);
        
        System.out.println("Enter a guess");
        
        String guess = s.nextLine();
        
        while (guess.length() != 5 || isInList(guess) == false)
        {
            if (guess.length() != 5)
            {
                System.out.println("5 letters please!");
            }
            if (isInList(guess) == false)
            {
                System.out.println("What is that word?");
            }
            System.out.println("Guess again.");
            
            guess = s.nextLine();
        }
        
        playerGuesses[6 - guesses] = guess;
        guesses--;
    }

    static void wordList() throws FileNotFoundException
    {
        int number = 0;
        File f = new File("wordlelist.txt");
        Scanner s = new Scanner(f);

        while(s.hasNextLine())
        {
            String data = s.nextLine();
            number++;
            if (data.length() == 5)
            {
                System.out.println(data);
            }
        }

        System.out.println(number);

        s.close();
    }

    static void typeLetters()
    {
        Scanner s = new Scanner(System.in);

        String keyboard = s.next();

        if (keyboard.length() == 5)
        {
            System.out.println(keyboard);
        }

        s.close();
    }

    static boolean isInList(String word) throws FileNotFoundException
    {
        File f = new File("wordlist.txt");
        Scanner s = new Scanner(f);
        
        while(s.hasNextLine())
        {
            String data = s.next();

            if(data.equals(word.toUpperCase()))
            {
                return true;
            }
        }
        return false;
    }

    static String chooseWord() throws FileNotFoundException
    {
        Random r = new Random();
        int randIndex = r.nextInt(5757);
        int number = 0;

        File f = new File("wordlelist.txt");
        Scanner s = new Scanner(f);

        while(s.hasNextLine())
        {
            String data = s.nextLine();
            number++;
            if (number == randIndex)
            {
                return data;
            }
        }

        System.out.println(number);

        s.close();

        return null;
    }
    
    void play() throws FileNotFoundException
    {
        for (int i = 0; i < 6; i++)
        {
            guessWord();
            System.out.println(playerGuesses[i]);
        }
    }
}
