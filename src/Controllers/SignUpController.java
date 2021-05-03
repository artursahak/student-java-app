package Controllers;

import application.Db;
import application.Students;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.StudentsDao;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUpController implements Initializable{

    @FXML
    private Button signUpBtn;

    @FXML
    private PasswordField userPass;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private TextField locationT;

    @FXML
    private TextField userName;

    private StudentsDao studentsDao = new StudentsDao();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        progressIndicator.setVisible(true);
        System.out.println("initialized");


        try{ studentsDao.fillStudents();
           studentsDao.getStudentsList().forEach(System.out::println);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }



    @FXML
    public void signUp() throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Fields cannot be empty");
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setHeaderText("Fail.No such user");



        String userPass = this.userPass.getText();
        String userName = this.userName.getText();
        String locationT = this.locationT.getText();

        if(userPass.isEmpty()||userName.isEmpty()||locationT.isEmpty()){
            alert.showAndWait();
        }

        System.out.println(userName +" :: " + userPass + ":" + locationT);

        try {
            studentsDao.insert(new Students(null,userName,userPass,locationT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Timeline task = new Timeline(
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

        Stage Login = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene2 = new Scene(root2);

        Login.setScene(scene2);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        //lambda function

        pause.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                signUpBtn.getScene().getWindow().hide();
                Login.show();
            }
        });

        pause.play();


    }
}
