import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class WordleApp extends Application
{
    TextArea output;
    TextField input;
    GraphicsContext gc;
    Wordle game;

    public void start(Stage stage)
    {
        game = new Wordle();

        output = new TextArea();
        input = new TextField();

        //Redirect "System.out.println()" to print to the text display window
        Console console = new Console(output);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);

        output.setText("Welcome to Wordle\nThe secret word is " + game.secretWord + "\nEnter a guess:\n\n");
        output.setEditable(false);
        output.setMaxHeight(100);
        input.requestFocus();
        input.setOnAction(this::handleInput);

        Canvas canvas = new Canvas(700, 500);
        VBox container = new VBox();
        container.getChildren().addAll(canvas, output, input);
        gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(container);

        stage.setScene(scene);
        stage.setTitle("Wordle");
        stage.show();

        drawRows();
    }

    void handleInput(ActionEvent e)
    {
        String keyboard = input.getText().toUpperCase();
        input.clear();
        System.out.println("You guessed: " + keyboard);
        try
        {
            game.guessWordApp(keyboard);
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }

        drawWord();
    }

    void drawSquare(double x, double y, String letter)
    {
        // gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(x, y, 65, 65);
        gc.setFill(Color.LIGHTGRAY);
        gc.setFont(Font.font("Courier New", FontPosture.REGULAR, 50));
        gc.fillText(letter, x + 20, y + 55);

    }

    void drawWord()
    {
        double y = 0;
        for (String word : game.playerGuesses)
        {
            if (word == null)
            {
                return;
            }
            
            for (int i = 0; i < 5; i++)
            {
                double x = 75 + (100 * i);
                String letter = "" + word.charAt(i);
                if (game.secretWord.charAt(i) == word.charAt(i))
                {
                    gc.setFill(Color.LIMEGREEN);
                }
                else if (game.secretWord.contains("" + word.charAt(i))) // right letter, wrong spot
                {
                    gc.setFill(Color.BURLYWOOD);
                }
                else
                {
                    gc.setFill(Color.INDIANRED);
                }
                drawSquare(x, y, letter);
            }
            
            y += 80;
        }
    }

    void drawRows()
    {
        gc.setFill(Color.LIGHTGRAY);
        for (int i = 0; i < 6; i++)
        {
            double y = i * 80;
            drawRow(50,y);
        }
    }

    void drawRow(double x, double y)
    {
        for (int i = 0; i < 5; i++)
        {
            double z = 75 + (100 * i);

            drawSquare(z, y, "");
        }
    }
}

class Console extends OutputStream
{
    TextArea output;

    public Console(TextArea ta)
    {
        this.output = ta;
    }

    @Override
    public void write(int i) throws IOException
    {
        output.appendText(String.valueOf((char) i));
    }

}