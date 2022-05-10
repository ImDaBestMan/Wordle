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
        
        output.setText("Welcome to Wordle\nEnter a guess:");
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
        
    }
    
    void drawSquare(double x, double y, String letter)
    {
        // gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(x, y, 65, 65);
        gc.setFill(Color.LIGHTGRAY);
        gc.setFont(Font.font("Courier New", FontPosture.REGULAR, 50));
        gc.fillText(letter, x + 20, y + 55);
        
    }
    
    void drawWord(String word)
    {
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
            drawSquare(x, 75, letter);
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
            double z = 75 + (120 * i);
            
            drawSquare(z, y, "");
        }
    }
}
