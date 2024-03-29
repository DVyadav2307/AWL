package io.github.dvyadav.awl;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TeacherPageController implements Initializable {

    
    // creating dbhandling class object
    FromDatabase fromDatabase = new FromDatabase();

    // controls on the student manasgement anchor pane
    @FXML
    AnchorPane studentManagementAnchorPane;
    @FXML
    TableView<StudentModel> tableOnViewStudent;
    @FXML
    TableColumn<StudentModel, Integer> rollNumberColumnOnViewStudent;
    @FXML
    TableColumn<StudentModel, String> nameColumnOnViewStudent;
    @FXML
    TableColumn<StudentModel,String> deapartmentColumnOnViewStudent;
    @FXML
    TableColumn <StudentModel,String> sectionColumnOnViewStudent;
    @FXML
    TableColumn <StudentModel,Integer> semesterColumnOnViewStudent;
    @FXML
    Button addStudentButton;
    @FXML
    Button deleteStudentButton;
    
    // controsl on the add student anchor pane
    @FXML
    AnchorPane addStudentAnchorPane;
    @FXML
    TextField rollNumberTextFieldOnAddStudent;
    @FXML
    TextField nameTextFieldOnAddStudent;
    @FXML
    ComboBox<String> departmentComboBoxOnAddStudent;
    @FXML
    ComboBox<String> sectionComboBoxOnAddStudent;
    @FXML
    ComboBox<String> semesterComboBoxOnAddStundent;
    @FXML
    Button confirmButtonOnAddStudent;

    // controls on delete student anchorPane
    @FXML
    AnchorPane deleteStudentAnchorPane;
    @FXML
    TextField rollNumberTextFieldOnDeleteStudent;
    @FXML
    Button checkButtonOnDeleteStudent;
    @FXML
    Label nameLabelOnDeleteStudent;
    @FXML
    Label departmentLabelOnDeleteStudent;
    @FXML
    Label sectionLabelOnDeletStudent;
    @FXML
    Label semesterLabelOnDeleteStudent;
    @FXML
    Button confirmButtonOnDeleteStudent;

    // controls on attendence management anchorPane
    @FXML
    AnchorPane attendenceManagementAnchorPane;
    @FXML
    Button markAttendenceButtonOnAttendenceManagement;
    @FXML
    Button viewAttendenceButtonOnAttendenceManagement;
    @FXML
    TableView<StudentModel> tableOnAttendenceManagement;
    @FXML
    TableColumn<StudentModel, Integer> rollNumberColumnOnMarkAttendence;
    @FXML
    TableColumn<StudentModel, String> nameColumnOnMarkAttendence;
    @FXML
    TableColumn<StudentModel, Boolean> isPresentColumnOnMarkAttendence;
    @FXML
    TableColumn<StudentModel, String> day1Column;
    @FXML
    TableColumn<StudentModel, String> day2Column;
    @FXML
    TableColumn<StudentModel, String> day3Column;
    @FXML
    TableColumn<StudentModel, String> day4Column;
    @FXML
    TableColumn<StudentModel, String> day5Column;
    @FXML
    TableColumn<StudentModel, String> day6Column;
    @FXML
    TableColumn<StudentModel, String> day7Column;
     
    
    // controls on the mark attendence pane
    @FXML
    AnchorPane markAttendenceSubAnchorPane;
    @FXML
    ComboBox<String> departmentComboBoxOnMarkAttendence;
    @FXML
    ComboBox<String> sectionComboBoxOnMarkAttendence;
    @FXML
    ComboBox<String> semsterComboBoxOnMarkAttendence;
    @FXML
    DatePicker datePickerOnMarkAttendence;
    @FXML
    Button loadButtonOnMarkAttendence;
    @FXML
    Button submitButtonOnMarkAttendence;

    // controls on the view attendence Anchor Pane
    @FXML
    AnchorPane viewAttendenceAnchorPane;
    @FXML
    ComboBox<String> departmentComboBoxOnViewAttendence;
    @FXML
    ComboBox<String> sectionComboBoxOnViewAttendence;
    @FXML
    ComboBox<String> semesterComboBoxOnViewAttendence;
    @FXML
    DatePicker fromDatePickerOnViewAttendence;
    @FXML
    DatePicker toDatePickerOnViewAttendence;
    @FXML
    Button loadButtonOnViewAttendence;

    // following are controls on profile managemnet
    @FXML
    AnchorPane profileManagementAnchorPane;
    @FXML
    Button editProfileButtonOnProfileManagement;
    @FXML
    Button logoutButtonOnProfileManagement;
    @FXML
    Button deleteAccountButtonOnProfileManagemnt;
    @FXML
    Hyperlink contactUsHyperlinkOnProfileManagement;
    @FXML
    AnchorPane editProfileAchorPaneOnProfileManagement;

    // following controls are on editProfileAchorPane
    @FXML
    Button changeDpImageButtononEditProfile;
    @FXML
    Rectangle dpImageRectangleOnEditProfile;
    Image dpImage = new Image(getClass().getResourceAsStream("images/user-data/userImage.jpg"));
    @FXML
    Label nameLabelOnEditProfile;
    @FXML
    Label designationLabelOnEditProfile;
    @FXML
    Label collegeNameOnEditProfile;
    @FXML
    Label mailLabelOnEditProfile;
    @FXML
    Label phoneNumberLabelOnEditProfile;
    @FXML
    TextField nameTextFieldOnEditProfile;
    @FXML
    TextField designationTextFeildOnEditProfile;
    @FXML
    TextField collegeNameTextFeildOnEditProfile;
    @FXML
    TextField emailTextFieldOnEditProfile;
    @FXML
    TextField phoneNumberTextFieldOnEditProfile;
    @FXML
    Button saveButtonOnEditProfile;
    @FXML
    Button cancelButtonOnEditProfile;

    // folowing are the homepage anchorpane controls
    @FXML
    AnchorPane homePageAnchorPane;
    @FXML
    Rectangle apotdImageRectangleOnHomePage;
    @FXML
    Label titleLabelOnHomePage;
    @FXML
    Label explanationLabelOnHomePage;
    @FXML
    Label copyrightLabelOnHomePage;
    @FXML
    Label dateLabelOnHomePage;


    // following are teacher interface background componets
    @FXML
    Label usernameOrTableNameLabel;
    @FXML
    Button studentManagementButton;
    @FXML
    Button attendenceManagementButton;
    @FXML
    Button profileManagementButton;
    @FXML
    Label infoLabelOnStudentMangement;
    @FXML
    ImageView infoLabelImageView;

    // showing alert or success info and hiding after 4 secs
    public void showThenHideAlert(Boolean isWarning, String Messege){

        // setting the images according to context
        if(isWarning){
            // for warning
            infoLabelImageView.setImage(new Image(getClass().getResourceAsStream("images/interface/warning.gif")));
        }else{
            // for generel success info
            infoLabelImageView.setImage(new Image(getClass().getResourceAsStream("images/interface/info.gif")));
        }
        // setting measseges text
        infoLabelOnStudentMangement.setText(Messege);
        // showing the alert
        infoLabelImageView.setVisible(true);
        infoLabelOnStudentMangement.setVisible(true);

        // hide after 4 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), e -> {
            infoLabelImageView.setVisible(false);
            infoLabelOnStudentMangement.setVisible(false);
        }   ));
        timeline.play();
    }


    // choosing photo from deviec
    public void selectImage(ActionEvent e){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File imageFile = fileChooser.showOpenDialog(((Stage)((Node)e.getSource()).getScene().getWindow()));

        // setting image to dp
        if(imageFile != null){
            dpImage = new Image(imageFile.toURI().toString());
            dpImageRectangleOnEditProfile.setFill(new ImagePattern(dpImage));
        }
    }


    // saving profile changes
    public void saveProfileChanges(ActionEvent e){

        // geting all the values from edit profile
        String name = nameTextFieldOnEditProfile.getText().trim();
        String designation = designationTextFeildOnEditProfile.getText().trim();
        String collegeName = collegeNameTextFeildOnEditProfile.getText().trim();
        String email = emailTextFieldOnEditProfile.getText().trim();
        Long phoneNumber;
        try {
             phoneNumber = Long.parseLong(phoneNumberTextFieldOnEditProfile.getText().trim());
        } catch (Exception ex) {
            // return if exception in number Since string is invalid to be a numerical value
            return;
        }


        // validating and saving all changes
        if(!name.isEmpty()){
            if(!designation.isEmpty()){
                if(!collegeName.isEmpty()){
                    if(isEmailValid()){
                        if(isPhoneNumberValid()){
                            // sending information to save in db 
                            fromDatabase.setProfileDataOfUser(usernameOrTableNameLabel.getText().trim(), name, designation, collegeName, email, phoneNumber);
                            setProfileManagement(e);
                            showThenHideAlert(false, "Profile Updated Sucessfully");
                            return;
                        }
                        // when worng phone num
                        showThenHideAlert(true, "Inavlid Phone Number");
                        return;
                    }
                    // when email worng
                    showThenHideAlert(true, "Invalid Email address");
                    return;
                }
            }
        }
        // in case of anything empty
        showThenHideAlert(true, "Please Fill all Details");

    }


    // validating number feild
    public boolean isPhoneNumberValid(){
        Pattern pattern =  Pattern.compile("^[0-9]{10}$");
        if(!phoneNumberTextFieldOnEditProfile.getText().isEmpty()){
            Matcher match = pattern.matcher(phoneNumberTextFieldOnEditProfile.getText().trim());
            return match.matches();
        }
        return false;
    }


    // validating email feild
    public boolean isEmailValid(){
        Pattern pattern =  Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        if(!emailTextFieldOnEditProfile.getText().isEmpty()){
            Matcher match = pattern.matcher(emailTextFieldOnEditProfile.getText().trim());
            return match.matches();
        }
        return false;
    }

    // cancelling profile changes
    public void cancelProfileChanges(ActionEvent e){

        // loading profile managemnet anchoir pane
        setProfileManagement(e);
    }

    // opening mail contact developer action 
    public void contactDeveloper(ActionEvent e){
        String mailAddress = "mailto:divyanshuy858@gmail.com";

        if(Desktop.isDesktopSupported()){
            
            try {
               Desktop.getDesktop().browse(new URI(mailAddress));              
            } catch (Exception ex) {
               System.out.println("Exception in Desktop Browse!");
            }

        }else{
            // error -> Mailing via link is not supported
           showThenHideAlert(true, "Mailing via link is not upported");
        }
    }


    // Deletin usaer account
    public void deleteAccount(ActionEvent e){


        // showing warning about deletion account
        Alert deleteAlert = new Alert(AlertType.WARNING);
        deleteAlert.setHeaderText("DELETION of Account is a Self-Destructive task , You will LOOSE your Student Records");
        deleteAlert.setContentText("It is recommended to delete all students from your record manually. Incase of its avoidance, students will be still able to see their records");
        deleteAlert.showAndWait();

        // asking for user confiramtion
        deleteAlert.setAlertType(AlertType.CONFIRMATION);
        deleteAlert.setHeaderText("You are about to DELETE your Account");
        deleteAlert.setContentText("Are you sure to delete your account?");
        // on confiramtion
        if(deleteAlert.showAndWait().get() == ButtonType.OK){
            System.out.println("delete confirmed!");

            // if deleted successfully
            if(fromDatabase.deleteUserAccount(usernameOrTableNameLabel.getText())){

                //  redirecting login interface
                Stage stage = ((Stage)((Node)e.getSource()).getScene().getWindow());

                // loading the Login interface Fxml
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Sigin-Login-Page.fxml"));
                    stage.setScene(new Scene(root));
                } catch (Exception ex) {
                    System.out.println("EXCEPTION IN Delete Account coudn't load login interdace fxml file");
                    ex.printStackTrace();
                }
    
                // centering the window and showing
                stage.centerOnScreen();
                stage.show();
    
    
                // informing user about logout action
                deleteAlert.setAlertType(AlertType.INFORMATION);
                deleteAlert.setContentText("Account Deleted Successfully!");
                deleteAlert.setHeaderText("SUCCESS ");
                deleteAlert.showAndWait();

            }else{
                // if cannot delted due to execption
                deleteAlert.setAlertType(AlertType.ERROR);
                deleteAlert.setHeaderText("Something went wrong!");
                deleteAlert.setContentText("We coudn't Delete your Account. Please try again.");
                deleteAlert.showAndWait();
            }
        }else{
            System.out.println("delete cancelled");
        }

    }

    // logout function
    public void logout(ActionEvent e){

        // show warning messege for logout confirmation
        Alert logoutWarning = new Alert(AlertType.CONFIRMATION);
        logoutWarning.setHeaderText("You are about to Logout!");
        logoutWarning.setContentText("Are you sure to Logout of AWL?");

        if(logoutWarning.showAndWait().get() == ButtonType.OK ){
            // on confirmation

            // gettting stage from button
            Stage stage = ((Stage)((Node)e.getSource()).getScene().getWindow());

            // loading the Login interface Fxml
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Sigin-Login-Page.fxml"));
                stage.setScene(new Scene(root));
            } catch (Exception ex) {
               System.out.println("EXCEPTION IN LOGOUT coudn't load login interdace fxml file");
               ex.printStackTrace();
            }

            // centering the window and showing
            stage.centerOnScreen();
            stage.show();


            // informing user about logout action
            logoutWarning.setAlertType(AlertType.INFORMATION);
            logoutWarning.setContentText("You have Logout Successfully!");
            logoutWarning.setHeaderText("SUCCESS ^-^ ");
            logoutWarning.showAndWait();

        }else{
            System.out.println("Logout cancelled!");
        }
    }


    // showing interface for profile editing
    public void editEnable(ActionEvent e){


        // setting text feild values to the previous values from labels
        nameTextFieldOnEditProfile.setText(nameLabelOnEditProfile.getText());
        designationTextFeildOnEditProfile.setText(designationLabelOnEditProfile.getText());
        collegeNameTextFeildOnEditProfile.setText(collegeNameOnEditProfile.getText());
        emailTextFieldOnEditProfile.setText(mailLabelOnEditProfile.getText());
        phoneNumberTextFieldOnEditProfile.setText(phoneNumberLabelOnEditProfile.getText());

        // unhiding buttons on edit profile
        changeDpImageButtononEditProfile.setVisible(true);
        saveButtonOnEditProfile.setVisible(true);
        cancelButtonOnEditProfile.setVisible(true);

        // showing/ unhiding  the TextFeilds
        nameTextFieldOnEditProfile.setVisible(true);
        designationTextFeildOnEditProfile.setVisible(true);
        collegeNameTextFeildOnEditProfile.setVisible(true);
        emailTextFieldOnEditProfile.setVisible(true);
        phoneNumberTextFieldOnEditProfile.setVisible(true);
    }
    

    // settting and showing profileManagement
    public void setProfileManagement(ActionEvent e){
        
        // setting Dp picture
        dpImageRectangleOnEditProfile.setFill(new ImagePattern(dpImage));


        // setting name desingnation etc
        String[] userData = new String[5];
        userData = fromDatabase.getProfileDataOfTheUser(usernameOrTableNameLabel.getText());
        nameLabelOnEditProfile.setText(userData[0]);
        designationLabelOnEditProfile.setText(userData[1]);
        collegeNameOnEditProfile.setText(userData[2]);
        mailLabelOnEditProfile.setText(userData[3]);
        phoneNumberLabelOnEditProfile.setText(userData[4]);

        // hiding textFeilds for editing
        nameTextFieldOnEditProfile.setVisible(false);
        designationTextFeildOnEditProfile.setVisible(false);
        collegeNameTextFeildOnEditProfile.setVisible(false);
        emailTextFieldOnEditProfile.setVisible(false);
        phoneNumberTextFieldOnEditProfile.setVisible(false);
        
        // hiding file choosing button
        changeDpImageButtononEditProfile.setVisible(false);
        // hiding save OR cancel buttons
        saveButtonOnEditProfile.setVisible(false);
        cancelButtonOnEditProfile.setVisible(false);

        // hiding other interfaces
        hideAllPanes();

        // showing profile interface
        profileManagementAnchorPane.setVisible(true);
    }


    // restricting  to datepicker days selection (Current operation:Only setiing values, User is jot allowed)
    public void restrictToDatePicker(ActionEvent e){

        // setting last date value for to ddate picker
        toDatePickerOnViewAttendence.setValue(fromDatePickerOnViewAttendence.getValue().plusDays(6));
        toDatePickerOnViewAttendence.setOpacity(2);
    }


    // Setting attendence managemnet table vlaues to view attendence
    public void loadViewAttendemce(ActionEvent e){

        // skip  retrivweing attendence if all details are not filled
        if(departmentComboBoxOnViewAttendence != null){
            if(sectionComboBoxOnViewAttendence != null){
                if( semesterComboBoxOnViewAttendence != null){
                    if(fromDatePickerOnViewAttendence != null){

                        // setting Column head content
                        day1Column.setText(fromDatePickerOnViewAttendence.getValue().plusDays(0).toString());
                        day2Column.setText(fromDatePickerOnViewAttendence.getValue().plusDays(1).toString());
                        day3Column.setText(fromDatePickerOnViewAttendence.getValue().plusDays(2).toString());
                        day4Column.setText(fromDatePickerOnViewAttendence.getValue().plusDays(3).toString());
                        day5Column.setText(fromDatePickerOnViewAttendence.getValue().plusDays(4).toString());
                        day6Column.setText(fromDatePickerOnViewAttendence.getValue().plusDays(5).toString());
                        day7Column.setText(fromDatePickerOnViewAttendence.getValue().plusDays(6).toString());

                        // binding columns
                        rollNumberColumnOnMarkAttendence.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));
                        nameColumnOnMarkAttendence.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("Name"));
                        day1Column.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("day1"));
                        day2Column.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("day2"));
                        day3Column.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("day3"));
                        day4Column.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("day4"));
                        day5Column.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("day5"));
                        day6Column.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("day6"));
                        day7Column.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("day7"));


                        // retrieving observatble lsit from db
                        ObservableList<StudentModel>  atendenceOf7Days = fromDatabase.retrieveAttendenceOf7Days(usernameOrTableNameLabel.getText(), departmentComboBoxOnViewAttendence.getValue(), sectionComboBoxOnViewAttendence.getValue(), Integer.parseInt(semesterComboBoxOnViewAttendence.getValue()), fromDatePickerOnViewAttendence.getValue());
                        // populating table cells
                        tableOnAttendenceManagement.setItems(atendenceOf7Days);

                    }
                }
            }
        }

    }



    // submitting attendence
    public void submitMarkAttendence(ActionEvent e){
        // List for iteration of table items
        ObservableList<StudentModel> attendenceDataList = tableOnAttendenceManagement.getItems();
        
        // if there is no table, no updation/insertion will be done
        if(!attendenceDataList.isEmpty()){
            
            // updating attendence iteratively
            for (StudentModel student : attendenceDataList) {
                fromDatabase.updateAttendence(student.getRollNumber(), student.isPresent(), datePickerOnMarkAttendence.getValue().toString(), usernameOrTableNameLabel.getText());
            }

            // prompt on successfull updation -> Attendece Marked Successfully
            showThenHideAlert(false, "Attendence Marked Sucessfully");
        }

        // clear the table to prevent possible error
        tableOnAttendenceManagement.getItems().clear();
    }


    // Loading the attendence table fro marking purpose
    public void loadAttendenceTable(ActionEvent e){

        // code to ensure null security
        if(departmentComboBoxOnMarkAttendence.getValue() != null){
            if(sectionComboBoxOnMarkAttendence.getValue() != null){
                if(semsterComboBoxOnMarkAttendence.getValue() != null){
                    if(datePickerOnMarkAttendence.getValue() != null){

                        // inserting the date rows in the student tables if empty
                        if(!fromDatabase.fillDateRowIfEmpty(usernameOrTableNameLabel.getText(), departmentComboBoxOnMarkAttendence.getValue(), sectionComboBoxOnMarkAttendence.getValue(), Integer.parseInt(semsterComboBoxOnMarkAttendence.getValue()), datePickerOnMarkAttendence.getValue().toString())){
                            // if row is already there then
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setHeaderText("Marked Already!");
                            alert.setContentText("You have marked the attendence of "+datePickerOnMarkAttendence.getValue()+" already!\n Continue if you want to update.");
                            alert.show();
                        }

                        // binding columns of the mark attendence table
                        rollNumberColumnOnMarkAttendence.setCellValueFactory(new PropertyValueFactory<StudentModel, Integer>("rollNumber"));
                        nameColumnOnMarkAttendence.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("name"));
                        isPresentColumnOnMarkAttendence.setCellValueFactory(new PropertyValueFactory<StudentModel, Boolean>("isPresent"));
                        isPresentColumnOnMarkAttendence.setCellFactory(CheckBoxTableCell.forTableColumn(isPresentColumnOnMarkAttendence));

                        // recieving attendence from db
                        ObservableList<StudentModel> markAttendenceData = fromDatabase.retrieveStudentAttendence(usernameOrTableNameLabel.getText(), departmentComboBoxOnMarkAttendence.getValue(), sectionComboBoxOnMarkAttendence.getValue(), Integer.parseInt(semsterComboBoxOnMarkAttendence.getValue()) , datePickerOnMarkAttendence.getValue().toString());
                        // values set to the table
                        tableOnAttendenceManagement.setItems(markAttendenceData);


                    }
                }
            }
        }

        


        
    }

    // setting view attendence sub pane
    public void showViewAttendenceSubPane(ActionEvent e){

        // hiding irrelevent columns
        isPresentColumnOnMarkAttendence.setVisible(false);
        // unhiding relevent columns
        day1Column.setVisible(true);
        day2Column.setVisible(true);
        day3Column.setVisible(true);
        day4Column.setVisible(true);
        day5Column.setVisible(true);
        day6Column.setVisible(true);
        day7Column.setVisible(true);

        // hiding other sub panes
        markAttendenceSubAnchorPane.setVisible(false);

        // unhiding view attendence sub pane
        viewAttendenceAnchorPane.setVisible(true);
    }

    // setting mark Attendence pane
    public void showMarkAttendenceSubPane(ActionEvent e){

        // hiding irrelevent columns
        day1Column.setVisible(false);
        day2Column.setVisible(false);
        day3Column.setVisible(false);
        day4Column.setVisible(false);
        day5Column.setVisible(false);
        day6Column.setVisible(false);
        day7Column.setVisible(false);

        // unhiding relevent column
        isPresentColumnOnMarkAttendence.setVisible(true);

        // hiding other sub panes
        viewAttendenceAnchorPane.setVisible(false);

        // unhiding mark attendence sub-pane
        markAttendenceSubAnchorPane.setVisible(true);
    }


    // setting attendence maagement anchorpane and vsibility too
    public void showAttendenceManagementPane(ActionEvent e){
        
        // setting department combox box of mark attendence & view attendence
        departmentComboBoxOnMarkAttendence.setItems(fromDatabase.getDepartments());
        departmentComboBoxOnViewAttendence.setItems(departmentComboBoxOnMarkAttendence.getItems());

        // setting sections combo box of mark & viewattendence DEFAULT: SELECT DEPARTMENT FIRST
        sectionComboBoxOnMarkAttendence.setItems(FXCollections.observableArrayList("Please Select Department First"));
        sectionComboBoxOnViewAttendence.setItems(sectionComboBoxOnMarkAttendence.getItems());

        // setting semeseter combo boxes on both sub panes
        semsterComboBoxOnMarkAttendence.setItems(fromDatabase.getSemester());
        semesterComboBoxOnViewAttendence.setItems(semsterComboBoxOnMarkAttendence.getItems());       

        // hiding other main panes
        hideAllPanes();
        // unhiding attendence managemnet panes
        attendenceManagementAnchorPane.setVisible(true);

    }

    // deleteing indivisual student data after confirmation
    public void deleteStudent(ActionEvent e){

        // poceed if roll number is valid
        if(!rollNumberTextFieldOnDeleteStudent.getText().isEmpty()){

            int rollNumber = Integer.parseInt(rollNumberTextFieldOnDeleteStudent.getText());
            if(fromDatabase.deleteIndivisualStudentData(rollNumber, usernameOrTableNameLabel.getText())){
                // deleting Student Data too
                fromDatabase.deleteStudentTable(rollNumber, usernameOrTableNameLabel.getText());

                showAndPopulateStudentViewTable(null);

                // msg -> Student deleted Sucessfully
                showThenHideAlert(false, "Student Deleted Sucessfully");

            }else{
                // error -> something went wrong
                showThenHideAlert(true, "Something Went Wrong");
            }
            return;
        }
    }


    // showing indivusual student data for confiramtion of deletion
    public void showStudentDataForConfirmDeleteion(ActionEvent e){

        // proceed if only contains a number
        if(!rollNumberTextFieldOnDeleteStudent.getText().isEmpty()){

            int rollNumber = Integer.parseInt(rollNumberTextFieldOnDeleteStudent.getText());
            String [] studentDataFromDB = fromDatabase.retrieveIndivisualStudentData(rollNumber, usernameOrTableNameLabel.getText());

            // if string is empty i.e. roll number not found
            if(studentDataFromDB[0] == null){
                // error -> roll number does not exist
               showThenHideAlert(true, "Roll Number doesnot exist");
            }
            // if returned data normally
            nameLabelOnDeleteStudent.setText(studentDataFromDB[0]);
            departmentLabelOnDeleteStudent.setText(studentDataFromDB[1]);
            sectionLabelOnDeletStudent.setText(studentDataFromDB[2]);
            semesterLabelOnDeleteStudent.setText(studentDataFromDB[3]);
        }
    }


    // showing delete student pane amd setting its controls default value
    public void showDeleteStudentPane(ActionEvent e){

        // setting default values
        rollNumberTextFieldOnDeleteStudent.clear();
        nameLabelOnDeleteStudent.setText("...");;
        departmentLabelOnDeleteStudent.setText("...");
        sectionLabelOnDeletStudent.setText("...");
        semesterLabelOnDeleteStudent.setText("...");

        // hiding add student sun anchorpane
        addStudentAnchorPane.setVisible(false);
        // unhiding delete student sub anchor pane
        deleteStudentAnchorPane.setVisible(true);
    }


    // showing and populating values for student View Table
    public void showAndPopulateStudentViewTable(ActionEvent e){
        // binding colums with the student model properties
        rollNumberColumnOnViewStudent.setCellValueFactory(new PropertyValueFactory<StudentModel, Integer>("rollNumber"));
        nameColumnOnViewStudent.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("name"));
       deapartmentColumnOnViewStudent.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("department"));
        sectionColumnOnViewStudent.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("section"));
        semesterColumnOnViewStudent.setCellValueFactory(new PropertyValueFactory<StudentModel, Integer>("semester"));

        // recieving list of objects of StudentModel Type ; sending tableName in the parameter
        ObservableList<StudentModel> studentData = fromDatabase.retrieveStudentsData(usernameOrTableNameLabel.getText());
        //setting the student object list to the table
        tableOnViewStudent.setItems(studentData);

        // setting sub anchorpanes
        // setting department list
        departmentComboBoxOnAddStudent.setItems(fromDatabase.getDepartments());
        // setting section list (DEFAULT: Select department first)
        sectionComboBoxOnAddStudent.setItems(FXCollections.observableArrayList("Please Select Department First"));
        // setting semester list
        semesterComboBoxOnAddStundent.setItems(fromDatabase.getSemester());

        // hiding other main  anchoepanes
        hideAllPanes();
        // showing the view student Pane
        studentManagementAnchorPane.setVisible(true);
    }

    // validate the new student entry and add new student to list
    public void validateAndAddStudent(){
        // check rollnumber is not empty
        if(!rollNumberTextFieldOnAddStudent.getText().isEmpty()){
            // check if name isnot empty
            if(!nameTextFieldOnAddStudent.getText().isEmpty()){
                // check if combo boxes are selected
                if(departmentComboBoxOnAddStudent.getValue() != null){
                    if (sectionComboBoxOnAddStudent.getValue() != null) {
                        if (semesterComboBoxOnAddStundent.getValue() != null) {

                            String tableName = usernameOrTableNameLabel.getText();
                            int rollNumber = Integer.parseInt(rollNumberTextFieldOnAddStudent.getText());
                            String name = nameTextFieldOnAddStudent.getText();
                            String department = departmentComboBoxOnAddStudent.getValue();
                            String section =  sectionComboBoxOnAddStudent.getValue();
                            int semester = Integer.parseInt(semesterComboBoxOnAddStundent.getValue());

                             // Add student to DB
                             if(fromDatabase.isAddingStudentSuccessfull(tableName, rollNumber, name, department, section, semester)){
                                // creating student;s presonal attendence table
                                fromDatabase.createStudentTable(rollNumber, usernameOrTableNameLabel.getText());

                                // clearing previous values of the data feilds
                                nameTextFieldOnAddStudent.clear();
                                rollNumberTextFieldOnAddStudent.clear();
                                departmentComboBoxOnAddStudent.getSelectionModel().clearSelection();
                                sectionComboBoxOnAddStudent.getSelectionModel().clearSelection();
                                semesterComboBoxOnAddStundent.getSelectionModel().clearSelection();

                                // showing the updated table
                                showAndPopulateStudentViewTable(null);

                                System.out.println("BEFORE");
                                // msg -> Student Added Successfully
                                showThenHideAlert(false, "Student Added Sucessfully");
                                System.out.println("AFTER");
                             }else{
                                // error-> The Roll Number is already used
                                showThenHideAlert(true, "The Roll Number already in use");
                             }
                            return;
                        }
                    }
                }
            }
        }

        // error -> Please fill All details
        showThenHideAlert(true, "Please fill all details");
    }

    // restricts the input of only nukmerical value in roll number
    public void allowOnlyNumbers(KeyEvent ke){

        // System.out.println("typed");
        String input = ke.getCharacter();

        if( ! input.matches("[0-9\b]")){
            ((TextField)ke.getSource()).setText("");
            // error->Only Numberical input allowed
            showThenHideAlert(true, "Only numerical input allowed");
        }
    }


    // loads sections after selecting department
    public void loadSections(ActionEvent e){
        
        // setting section combo box of student management
        if(e.getSource() == departmentComboBoxOnAddStudent){
            sectionComboBoxOnAddStudent.setItems(fromDatabase.getSections(departmentComboBoxOnAddStudent.getValue()));
        }
        // setting section sombo box of attendence management
        else if(e.getSource() == departmentComboBoxOnMarkAttendence){
            sectionComboBoxOnMarkAttendence.setItems(fromDatabase.getSections(departmentComboBoxOnMarkAttendence.getValue()));
        }
        else if(e.getSource() == departmentComboBoxOnViewAttendence){
            sectionComboBoxOnViewAttendence.setItems(fromDatabase.getSections(departmentComboBoxOnViewAttendence.getValue()));
        }


    }

    


    // settting student management anchorpane
    public void showAddStudentPane(ActionEvent e){
        
        // hiding deletestudent sub AnchorPane
        deleteStudentAnchorPane.setVisible(false);

        // unhiding add student subAnchorPane
        addStudentAnchorPane.setVisible(true);
    }


    // hides all of the anchorpanes
    public void hideAllPanes(){
        // hiding  student managemnet
        studentManagementAnchorPane.setVisible(false);

        // hiding attendence management 
        attendenceManagementAnchorPane.setVisible(false);

        // hiding profile management 
        profileManagementAnchorPane.setVisible(false);

        //hide homePageAnchorpane
        homePageAnchorPane.setVisible(false);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // hding all the panes
        hideAllPanes();

        // setting homepage anchorpane visible
        homePageAnchorPane.setVisible(true);
        
    }


}
