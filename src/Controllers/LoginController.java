package Controllers;

import application.Main;
import application.StudentsDao;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class LoginController implements Initializable {

    @FXML
    private CheckBox remember;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView logo;

    @FXML
    private TextField label;

    @FXML
    private Button login;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private TextField username;

    @FXML
    private Button forgotpassword;

    @FXML
    private ProgressBar progressBar;


    private String myUsername;

    @FXML
    private AnchorPane pane;

    StudentsDao studentsDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //logo = new ImageView(new Image(getClass().getResourceAsStream("/Images/moodle-logo-2019.png")));
        progressBar.setProgress(0);
        File file = new File("Images/moodle-logo-2019.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
        //logo.setLayoutX(55);

        //logo.setLayoutY(14);

        studentsDao = new StudentsDao();
        try {
            studentsDao.fillStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //progress.setVisible(false);
        username.setStyle("-fx-text-inner-color: #a0a2ab;");
        password.setStyle("-fx-text-inner-color:#a0a2ab;");
    }
    @FXML
    MainPageController mainPage = new MainPageController();
    @FXML
    public void loginAction() throws Exception {



    //System.out.println("Working");
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Fields cannot be empty");
    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
    alert1.setHeaderText("Fail.No such user");
    String tmpName = username.getText();
    String tmpPass = password.getText();
    setMyUsername(tmpName);
    if (tmpName.isEmpty()||tmpPass.isEmpty()){
        alert.showAndWait();
    }
        System.out.println(tmpName);
        System.out.println(tmpPass);

    if(studentsDao.check(tmpName,tmpPass)){
        // pauseTransition not working yet???
        progressBar.setVisible(true);

        //PauseTransition pauseTransition = new PauseTransition();
        //pauseTransition.setDuration(Duration.seconds(3));


        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(progressBar.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(2),
                        new KeyValue(progressBar.progressProperty(), 1)
                )
        );

        Timeline task2 = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(progressIndicator.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(2),
                        new KeyValue(progressIndicator.progressProperty(), 1)
                )
        );

        task.playFromStart();
        task2.playFromStart();
        //pauseTransition.play();
        System.out.println("login successfully");

        Stage MainPage = new Stage();
        //FXMLLoader loader = FXMLLoader.load(getClass().getResource("/FXML/MainPage.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("/FXML/MainPage.fxml"));
        //FXMLLoader loader= new FXMLLoader(getClass().getResource("calc1.fxml"));
        //pane = (AnchorPane) loader.load();
       // mainPage.getUserText(tmpName);
        //root2.getChildren().add();
       // Label lblData = (Label) root2.lookup(".myLabel");

       // if (lblData!=null)
        //lblData.setText(tmpName);

        Scene MainScene = new Scene(root2);


        Label myLb = (Label)MainScene.lookup("#myLabel");
        myLb.getText();
        myLb.setText(tmpName);
        //MainPage.setUserData(tmpName);
        

        MainPage.setScene(MainScene);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        //lambda function

            pause.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    login.getScene().getWindow().hide();
                    MainPage.show();
                }
            });

        pause.play();
    }
    else {
        //System.out.println("login failed there is no such user");
        alert1.showAndWait();
    };
    }

    public void setMyUsername(String input){
        myUsername = input;
        System.out.println("myUsername is" + myUsername);
    }

    public void signUp() throws IOException {
        login.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Signup.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
    }

}




