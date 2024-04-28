package io.github.dvyadav.awl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FromDatabase {

    private final String URL = "jdbc:mysql:// sql6.freesqldatabase.com:3306/sql6702642";
    private final String USERNAME = "sql6702642";
    private final String PASSWORD =System.getenv("MYSQL_FREEDB_PASSWORD");  //please contact for DBMS password

    private Connection connection ;
    private PreparedStatement statement ;
    private ResultSet resultSet;



    public FromDatabase(){
        // NOTE: the remote server do not allow prolonged connections, hence created connection each time of query excetution
    }


    // savinf profile data into the db
    public void setProfileDataOfUser(String username, String name, String designation, String collegeName, String email, Long phoneNumber){
        try {
           connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("UPDATE Credential_Table  SET Name = ?, Designation = ?, College_Name = ?, Email = ? , Phone_Number = ? WHERE username = ?");
        statement.setString(1, name);
        statement.setString(2, designation);
        statement.setString(3, collegeName);
        statement.setString(4, email);
        statement.setLong(5, phoneNumber);
        statement.setString(6, username);

        System.out.println(statement.toString());
        statement.executeUpdate();

       } catch (Exception e) {
        System.out.println("Exception in SETPROFILEDATA");
        e.printStackTrace();
       }
    }

    // deleteing user credentials
    public boolean deleteUserAccount(String userName){
        
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // deleting credentials
            statement  =connection.prepareStatement("DELETE FROM Credential_Table WHERE username = ?");
            statement.setString(1, userName);
            System.out.println(statement.toString());
            statement.executeUpdate();
            
            userName = dbSuitableStringOf(userName);
            // deleting user's personal table
            statement = connection.prepareStatement("DROP TABLE "+userName+"");
            System.out.println(statement.toString());
            statement.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Execption in DELETE USER");
            e.printStackTrace();
            return false;
        }
    }


    // retrieving number of the User
    public String[] getProfileDataOfTheUser(String userName){
        String[] data = new String[5];
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("SELECT Name, Designation, College_Name, Email, Phone_Number FROM Credential_Table WHERE  username = \'"+userName+"\'");

            System.out.println(statement.toString());
            resultSet = statement.executeQuery();


            while (resultSet.next()) {
                for(int i =0; i < data.length; i++){
                    data[i] = resultSet.getString(i+1);
                }
            }

        } catch (Exception e) {
            System.out.println("Exception in GET Phone_Number");
        }

        return data;
    }


    // returns 7 days of student attendence
    public ObservableList<StudentModel> retrieveAttendenceOf7Days(String tableName, String department, String section, int semester, LocalDate dateInStringAsYYYY_MM_DD){
        ObservableList<StudentModel> attendeceDataOf7Days = FXCollections.observableArrayList();
        tableName = dbSuitableStringOf(tableName);

        try {
            // getting list of roll numbers as result set for retrival operation
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD) ;
            statement = connection.prepareStatement("SELECT Roll_Number, Name FROM "+tableName+" WHERE Department = ? AND Section = ? AND Semester= ? ORDER BY Roll_Number");
            statement.setString(1, department);
            statement.setString(2, section);
            statement.setInt(3, semester);
            
            System.out.println(statement.toString());
            resultSet = statement.executeQuery();

            // using the select roll numbers to retrieve attendnce data from student table
            while (resultSet.next()) {

                ResultSet rsTemp = null;
                String[] isPresentOnOtherDays = new String[7];
               //retrieving 7 consecutive data for each student table 
               for (int i = 0; i < isPresentOnOtherDays.length; i++) {
                try {
                    statement = connection.prepareStatement("SELECT Is_Present FROM Attendence_of_"+resultSet.getInt("Roll_Number")+"_By_"+tableName+" WHERE Dates = \'"+dateInStringAsYYYY_MM_DD.plusDays(i)+"\'");
                    System.out.println(statement.toString());
                    rsTemp = statement.executeQuery();

                    // saving attendence data foe each day in a an aray
                    while (rsTemp.next()) {
                        if(rsTemp.getBoolean("Is_Present")){
                            isPresentOnOtherDays[i] = "Present";
                        }
                        else{
                            isPresentOnOtherDays[i] = "Absent";
                        }
                    }
                } catch (Exception e) {
                    // save null to the attendence if attendece row not found for that day
                    isPresentOnOtherDays[i] = null;
                }
               }

            //    creating observable list for table view
               attendeceDataOf7Days.add(new StudentModel(resultSet.getInt("Roll_Number"), resultSet.getString("Name"), isPresentOnOtherDays[0], isPresentOnOtherDays[1], isPresentOnOtherDays[2], isPresentOnOtherDays[3], isPresentOnOtherDays[4], isPresentOnOtherDays[5], isPresentOnOtherDays[6]));                
            }

        } catch (Exception e) {
            System.out.println("Exception in RETRIVE ATTENDENCE OF 7 DAYS");
            e.printStackTrace();
            return null;
        }

        return attendeceDataOf7Days;
    }

   
    // update the attendence
    public void updateAttendence(int rollNumber, boolean isPresent, String dateInStringAsYYYY_MM_DD, String userName){

        userName = dbSuitableStringOf(userName);
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("UPDATE Attendence_of_"+rollNumber+"_By_"+userName+" SET Is_Present = "+isPresent+" WHERE Dates = \'"+dateInStringAsYYYY_MM_DD+"\'");

            System.out.println(statement.toString());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception in UPDATE ATTENDECE");
            e.printStackTrace();
        }
    }

    // return attendence data from db
    public ObservableList<StudentModel> retrieveStudentAttendence(String tableName, String department, String section, int semester, String dateInStringAsYYYY_MM_DD){

        ObservableList<StudentModel> markAttendenceData = FXCollections.observableArrayList();
        tableName = dbSuitableStringOf(tableName);

        try {
            // fetching roll numbers to insert Present
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("SELECT Roll_Number FROM "+tableName+" WHERE Department = ? AND Section = ? AND Semester= ? ORDER BY Roll_Number");
            statement.setString(1, department);
            statement.setString(2, section);
            statement.setInt(3, semester);
            
            System.out.println(statement.toString());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // inserting present in each table iteratively
               statement = connection.prepareStatement("Select Roll_Number, Name, Is_present FROM "+tableName+", Attendence_of_"+resultSet.getString("Roll_Number")+"_By_"+tableName+" WHERE Roll_Number = "+resultSet.getInt("Roll_Number")+" AND Department = ? AND Section = ? AND Semester = ? AND Dates = ?");
               statement.setString(1, department);
               statement.setString(2, section);
               statement.setInt(3, semester);
               statement.setString(4, dateInStringAsYYYY_MM_DD);

               System.out.println(statement.toString());
               ResultSet rsTemp = statement.executeQuery();

               while (rsTemp.next()) {
                   markAttendenceData.add(new StudentModel(rsTemp.getInt("Roll_Number"), rsTemp.getString("Name"), rsTemp.getBoolean("Is_Present")));
               }


                }

            }catch (Exception e) {
                System.out.println("Exception in RETRIVE STUDENT ATTENDENCE");
                e.printStackTrace();
                return null;
        }

        return markAttendenceData;
    }
    

    // Insert the default present value and return false if date is alreqady there
    public boolean fillDateRowIfEmpty(String tableName, String department, String section, int semester, String dateInStringAsYYYY_MM_DD){
        tableName = dbSuitableStringOf(tableName);
        try {
            // fetching roll numbers to insert Present
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("SELECT Roll_Number FROM "+tableName+" WHERE Department = ? AND Section = ? AND Semester= ? ORDER BY Roll_Number");
            statement.setString(1, department);
            statement.setString(2, section);
            statement.setInt(3, semester);
            
            System.out.println(statement.toString());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // inserting present in each table iteratively
               statement = connection.prepareStatement("INSERT INTO Attendence_of_"+resultSet.getString("Roll_Number")+"_By_"+tableName+" (Dates, Is_Present) values(?, true)");
               statement.setString(1, dateInStringAsYYYY_MM_DD);

               System.out.println(statement.toString());
               statement.executeUpdate();
                }

            }catch (Exception e) {
                System.out.println("Exception In FILLDATEROWIFEMPTY\n Date is already there");
                return false;
        }

        return true;
    }



    // deleting student table
    public void deleteStudentTable(int rollNumber, String userName){
        userName = dbSuitableStringOf(userName);
        String tableName = "Attendence of "+rollNumber+" By "+userName;
        tableName = dbSuitableStringOf(tableName);

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("DROP TABLE "+tableName+"");
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception in DELETE STUDENT TABLE");;
            e.printStackTrace();
        }
    }

    // deleting indivisual student
    public boolean deleteIndivisualStudentData(int rollNumber, String tableName){
        // processing tablename
        tableName = dbSuitableStringOf(tableName);

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("DELETE FROM "+tableName+" WHERE Roll_Number = ?");
            statement.setInt(1, rollNumber);

            System.out.println(statement.toString());
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Exception in DELETEINDIVISUALSTUDENTDATA");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // retrieving indivisual student details for delete operation
    public String[] retrieveIndivisualStudentData(int rollNumber, String tableName){
        String[] arrayOfStudentData = new String[4];
        
        // processing table name for db suitability
        tableName = dbSuitableStringOf(tableName);

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("SELECT Name, Department, Section, Semester FROM  "+tableName+" WHERE Roll_Number = ?");
            statement.setInt(1, rollNumber);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                arrayOfStudentData[0] = resultSet.getString("Name");
                arrayOfStudentData[1] = resultSet.getString("Department");
                arrayOfStudentData[2] = resultSet.getString("Section");
                arrayOfStudentData[3] = resultSet.getString("Semester");
            }
            
        } catch (Exception e) {
            System.out.println("Exception in RETRIVEINDIVISUALSTUDENTDATA");
            e.printStackTrace();
        }

        return arrayOfStudentData;
    }

    // return student details from DB as observable list
    public ObservableList<StudentModel> retrieveStudentsData(String tableName){
        tableName = dbSuitableStringOf(tableName);

        ObservableList<StudentModel> studentDataList = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("SELECT Roll_Number, Name,  Department, Section, Semester FROM "+tableName+"");

            System.out.println(statement.toString());
            resultSet =  statement.executeQuery();
            
            while (resultSet.next()) {
                studentDataList.add(new StudentModel(resultSet.getInt("Roll_Number"), resultSet.getString("Name"), resultSet.getString("Department"), resultSet.getString("Section"), resultSet.getInt("Semester")));
            }

        } catch (Exception e) {
            System.out.println("Exception in RETRIEVESTUDENTDATA");
            e.printStackTrace();
        }

        return studentDataList;
    }

    // creating student own attendence table
    public void createStudentTable(int rollNumber, String userName){
        userName = dbSuitableStringOf(userName);
        String tableName = "Attendence of "+rollNumber+" By "+userName;
        tableName = dbSuitableStringOf(tableName);

        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("CREATE TABLE "+tableName+" ( Dates DATE PRIMARY KEY, Is_Present BOOLEAN, Issue VARCHAR(100))");

            System.out.println(statement.toString());
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Exception in CREATE STUDENT TABLE");
            e.printStackTrace();
        }
    }

    // adding student details to DB
    public boolean isAddingStudentSuccessfull(String tableName, int rollNumber, String name, String department, String section, int semester){
        // removing whitespace and replacing with underscore
        tableName = dbSuitableStringOf(tableName);
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("INSERT INTO "+tableName+" (Roll_Number, Name, Department, Section, Semester) VALUES (?,?,?,?,?)");
            statement.setInt(1, rollNumber);
            statement.setString(2, name);
            statement.setString(3, department);
            statement.setString(4, section);
            statement.setInt(5, semester);

            System.out.println(statement.toString());
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Exception in ISADDINGSTUDENTSUCCESSFULL");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returing semester list
    public ObservableList<String> getSemester(){

        ObservableList<String> semList = FXCollections.observableArrayList();
        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("SELECT Semester_No from Semester_Table");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                semList.addAll(resultSet.getString("Semester_No"));
            }

        } catch (Exception e) {
            System.out.println("Exception in GETSEMESTER");
            e.printStackTrace();
        }

        return semList;
    }

    // returning list of sections
    public ObservableList<String> getSections(String departmentName){

        ObservableList<String> sectionList = FXCollections.observableArrayList();
        try {
            
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement  = connection.prepareStatement("SELECT  Section from Section_List WHERE "+departmentName+" = "+true+"");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
               sectionList.add(resultSet.getString("Section"));
            }

        } catch (Exception e) {
            System.out.println("Exception in GETSECTIONS");
            e.printStackTrace();
        }
        return sectionList;
    }

    // returning list of departments
    public ObservableList<String> getDepartments(){

        ObservableList<String> departmentList = FXCollections.observableArrayList();
        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("SELECT Department_Name FROM Department");
            resultSet = statement.executeQuery();
           while (resultSet.next()) {
            departmentList.add(resultSet.getString("Department_Name"));
           }
           
        } catch (Exception e) {
                System.out.println("Execption in GETDEPARTMENT METHOD");
                e.printStackTrace();
            }
            return departmentList;
    }
    
    // creating users's table
    public void createUserTable(String tableName){
        // removing space and replacing with underscore
        tableName = dbSuitableStringOf(tableName);
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("CREATE TABLE "+tableName+"( Roll_Number int primary key, Name varchar(50), Department varchar(50), Section varchar(2), Semester int )");

            System.out.println(statement.toString());
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Exception in CREATEUSERTABLE");
            e.printStackTrace();
        }
    }

    // adding new user credentials
    public void addNewUser(String username, String password){
        try {

            // adding user
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("INSERT INTO  Credential_Table (username, password, Name, Designation, College_Name, Email, Phone_Number) values (\""+username+"\",\""+password+"\", ?, ?,?,?,?)");
            statement.setString(1, "Please Fill Your Name");
            statement.setString(2, "Your Designation Here");
            statement.setString(3, "College/University Name");
            statement.setString(4, "Your Email and Phone Number");
            statement.setInt(5, 1234567890);

            System.out.println(statement.toString());
            statement.executeUpdate();



            // creating user table on successful creation
            createUserTable(username);
        } catch (Exception e) {
            System.out.println("Exception in ADDNEWUSER METHOD");
            e.printStackTrace();
        }
    }

    // checks if the username is already there in the table
    public boolean isNameOccupied(String username){
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("SELECT * FROM  Credential_Table WHERE  username = \""+username+"\"");
            resultSet = statement.executeQuery();

            // name found since table is not null
            if(resultSet.next()){
                return true;
            }
            // table is null name not found
            else{
                return false;
            }

        }catch(Exception e){
            System.out.println("Exception in ISNAMEOCCUPIED METHOD");
            e.printStackTrace();
            return true;
        }
    }



    // This method validates login of user
    public boolean isUserValid(String username, String password){
        try{

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("connection established");

            statement = connection.prepareStatement("SELECT * FROM Credential_Table WHERE BINARY username = \"" + username+ "\" AND BINARY password = \"" +password+ "\"");

           resultSet = statement.executeQuery();

        // following method will return true if table is not null
           return resultSet.next();

        }catch(Exception e){

            System.out.println("Exception in ISUSERVALID METHOD");
            e.printStackTrace();
            return false;
        }

    }



    // processing string for databse compatibilty
    public String dbSuitableStringOf(String unproccessedString){
        unproccessedString.trim();
        return unproccessedString.replace(" ", "_");
    }
}
