package josefmayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Josef Mayer on 07.07.2017.
 */
public class JavaFXApp extends Application {
    private int counter = 0;

    private NetClient netClient = new NetClient();

    public static void main(String[] args) {
        launch();
    }

    @SuppressWarnings("static-access")
    public void start(Stage stage) {
        stage.setTitle("GaussianSolver Rest Client");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Rectangle r1 = new Rectangle();
        //r1.setFill(Color.web("b894cc"));
        r1.setFill(Color.web("LEMONCHIFFON"));

        r1.setHeight(100);
        r1.setWidth(1000);

        Rectangle r2 = new Rectangle();
        r2.setFill(Color.web("LIGHTGREEN"));
        r2.setHeight(400);
        r2.setWidth(1000);

        grid.add(r1, 0, 0);
        grid.add(r2, 0, 1);



        // Post
        final TextField textField = new TextField();
        textField.setMaxWidth(600);

        Label label1 = new Label();
        label1.setFont(new Font("Calibri", 15));
        label1.setText("Enter Calculation Input Data: ");

        Button btnPost = new Button();
        btnPost.setFont(new Font("Calibri", 15));
        btnPost.setText("Rest Request POST");

        btnPost.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String str = textField.getText();
                System.out. println(str);
                netClient.setPOSTConnection(str);
                //netClient.setPOSTConnection("{"a":[2.0,4.0,5.0,-6.0],"b":[8.0,4.0]}");
            }
        });

        BorderPane bpPost = new BorderPane();
        //ListView list = new ListView();
        //bpPost.setMargin(list, new Insets(30,030,30,30));
        //bpPost.setStyle("-fx-background-colour: #FFFFFF;");

        bpPost.setBottom(btnPost);
        bpPost.setAlignment(btnPost, Pos.CENTER);

        bpPost.setTop(label1);
        bpPost.setAlignment(label1, Pos.TOP_CENTER);

        bpPost.setCenter(textField);
        bpPost.setAlignment(textField, Pos.BOTTOM_CENTER);

        grid.add(bpPost,0,0);


        // Get
        final Label l = new Label();
        l.setFont(new Font("Calibri", 15));
        l.setTextFill(Color.BLACK);

        Button btnGet = new Button();
        btnGet.setFont(new Font("Calibri", 15));
        btnGet.setText("Rest Request GET");

        btnGet.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                StringBuffer sb = new StringBuffer();
                sb.append(netClient.setGETConnection());
                l.setText(sb.toString());
            }
        });


        BorderPane bpGet = new BorderPane();
        bpGet.setTop(btnGet);
        bpGet.setAlignment(btnGet, Pos.CENTER);

        bpGet.setCenter(l);
        //bpGet.setAlignment(l, Pos.CENTER);
        bpGet.setAlignment(l, Pos.TOP_CENTER);
        //bp.setCenter(button);
        grid.add(bpGet, 0, 1);

        //
        StackPane root = new StackPane();
        //root.getChildren().add(r5);
        root.getChildren().add(grid);

        stage.setScene(new Scene(root, 1000, 510));
        stage.show();

    }

}
