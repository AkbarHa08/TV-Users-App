package com.login;

import com.dao.Dao;
import com.dao.DaoImpl;
import com.model.Users;
import com.page.PageController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Label loginWarningLbl;
    @FXML
    private Button loginBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }      
    
    DaoImpl dao = new DaoImpl();

    @FXML
    private void loginBtnOnAction(ActionEvent event) {
        String username = usernameTF.getText().trim();
        String password = passwordTF.getText().trim();
        if (username.equalsIgnoreCase("")||password.equalsIgnoreCase("")) {
            loginWarningLbl.setText("Please, fill in empty fields!");
        } else {
            Users user = dao.checkUser(username, password);
            if (user == null) {
                loginWarningLbl.setText("Invalid username or password!");
            } else {
                try {
                    Stage s = new Stage();
                    s.setTitle("TV Users App");
                    s.getIcons().add(new Image("/com/images/icon.png"));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/page/page.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    s.setScene(scene);
                    s.show();
                    
                    Stage oldStage = (Stage) loginBtn.getScene().getWindow();
                    oldStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}