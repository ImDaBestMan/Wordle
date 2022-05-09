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

public class WordleApp extends Application
{
    GraphicsContext gc;
    Wordle game;
    public void start(Stage stage)
    {
        game = new Wordle();
        
        Canvas canvas = new Canvas(700, 700);
        VBox container = new VBox();
        container.getChildren().addAll(canvas);
        gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(container);

        stage.setScene(scene);
        stage.setTitle("Wordle");
        stage.show();

        drawSquare(75, 75, "a");
    }

    void drawSquare(double x, double y, String letter)
    {
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(x, y, 75, 75);
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Courier New", FontPosture.REGULAR, 50));
        gc.fillText(letter, x + 20, y + 55);
        
        for (int i = 0; i < 5; i++)
        {
            drawSquare;
        }
        
    }
}
