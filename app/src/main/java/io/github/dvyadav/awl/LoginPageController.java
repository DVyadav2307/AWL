package io.github.dvyadav.awl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginPageController implements Initializable{

    FromDatabase fromDatabase = new FromDatabase();
    
    @FXML
    AnchorPane loginAnchorPane;

    @FXML
    AnchorPane signUpAnchorPane;


    @FXML
    TextField usernameTextFieldOnLoginPage;

    @FXML
    PasswordField passwordFieldOnLoginPage;

    @FXML
    TextField exposedPasswordTextFieldOnLoginPage;

    @FXML
    Label alertInfoLabelOnLoginPage;

    @FXML
    CheckBox showPasswordCheckBoxOnLoginPage;
    
    @FXML
    Button LginButtonOnLoginPage;

    @FXML
    Hyperlink registerHereHyperlinkOnLoginPage;


    // following are signupPage nodes

    @FXML
    Label alertInfoLabelOnSignupPage;

    @FXML
    TextField userNameTextFieldOnSignupPage;

    @FXML
    PasswordField passwordFieldOnSignupPage;

    @FXML
    PasswordField confirmPasswordFeildOnSignupPage;

    @FXML
    Button registerButtonOnSignupPage;

    @FXML
    Hyperlink loginHereHyperlinkOnSignupPage;


    // registers the user and redirects to login page
    public void registerAndRedirect(ActionEvent e) throws Exception{

        // proceed to register only if password feilds are matched
        if(checkIfMatched(null) && !userNameTextFieldOnSignupPage.getText().isBlank() && !passwordFieldOnSignupPage.getText().isEmpty() ){

            // if username is already used
             if(fromDatabase.isNameOccupied(userNameTextFieldOnSignupPage.getText())){
                alertInfoLabelOnSignupPage.setText("Username already exists");
                alertInfoLabelOnSignupPage.setVisible(true);
             }else{

            // if username is unique proceed normally (Adding user)
                fromDatabase.addNewUser(userNameTextFieldOnSignupPage.getText(), passwordFieldOnSignupPage.getText());
                // create user's table
                // fromDatabase.createUserTable(userNameTextFieldOnSignupPage.getText());  -- this must be called in the from database class

                // alert on success registration
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("SUCCESS");
                alert.setContentText("Registration Successfull\nYou Can Login Now");
                alert.show();

                // redirecting to login
                loginAnchorPane.setVisible(true);
                signUpAnchorPane.setVisible(false);
                // autofill username text
                usernameTextFieldOnLoginPage.setText(userNameTextFieldOnSignupPage.getText());
            }

        }

        
    }

    // validates the password confirmation
    public boolean checkIfMatched(KeyEvent ke){
        // password matches on both textox
        if(passwordFieldOnSignupPage.getText().equals(confirmPasswordFeildOnSignupPage.getText())){
            alertInfoLabelOnSignupPage.setVisible(false);
            return true;
        }
        else{
            // password not matches in both textbox
            alertInfoLabelOnSignupPage.setText("Password didn't match");
            alertInfoLabelOnSignupPage.setVisible(true);
            return false;
        }
    }


    // calls method to authenticate user on login
    public void authenticateLogin (ActionEvent e)throws Exception{

        // skip if username or pasword feild empty
        if(!usernameTextFieldOnLoginPage.getText().isEmpty()){
            if(!passwordFieldOnLoginPage.getText().isBlank()){

                // loads eacher interface if authentication successfull
                if(fromDatabase.isUserValid(usernameTextFieldOnLoginPage.getText(), passwordFieldOnLoginPage.getText())){
                    
                    Stage stage = ((Stage)((Node)e.getSource()).getScene().getWindow());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Teacher-Page.fxml"));
                    Parent root = loader.load();
                    stage.setScene(new Scene(root));
                    stage.centerOnScreen();
                    stage.show();
                    TeacherPageController controller = loader.getController();
                    controller.usernameOrTableNameLabel.setText(usernameTextFieldOnLoginPage.getText());
                }
                else{
                    // show error on unsuccessfull authentication
                    alertInfoLabelOnLoginPage.setVisible(true);
                }

            }
        }

    }


    // maintaining same value btween hidden & unhidden password field
    public void updatePasswordFeild(KeyEvent ke){
        // update exposing feild on every keyhit on password feild
        if(ke.getSource() == passwordFieldOnLoginPage ){
            exposedPasswordTextFieldOnLoginPage.setText(passwordFieldOnLoginPage.getText());
        }
        // update password feild on every key hit on exposing feild
        else{
            passwordFieldOnLoginPage.setText(exposedPasswordTextFieldOnLoginPage.getText());
        }
    }


    // show password & hide password
    public void showAndHidePassword(ActionEvent e){
        if(showPasswordCheckBoxOnLoginPage.isSelected()){
            exposedPasswordTextFieldOnLoginPage.setVisible(true);
            passwordFieldOnLoginPage.setVisible(false);
        }
        else{
            exposedPasswordTextFieldOnLoginPage.setVisible(false);
            passwordFieldOnLoginPage.setVisible(true);
        }
    }


    // switching login & signUpupPage
    public void switchPage(ActionEvent e){

        if(e.getSource() == registerHereHyperlinkOnLoginPage){
            signUpAnchorPane.setVisible(true);
            loginAnchorPane.setVisible(false);
        }else{
            signUpAnchorPane.setVisible(false);
            loginAnchorPane.setVisible(true);

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginAnchorPane.setVisible(true);
        signUpAnchorPane.setVisible(false);
    }



}
