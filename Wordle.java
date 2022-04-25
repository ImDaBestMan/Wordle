import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Wordle
{
    static void wordList() throws FileNotFoundException
    {
        File f = new File("wordlist.txt");
        Scanner s = new Scanner(f);
        
        while(s.hasNextLine())
        {
            String data = s.nextLine();
            
            System.out.println(data);
        }
        
        s.close();
    }
}
