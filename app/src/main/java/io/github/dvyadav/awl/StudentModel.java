package io.github.dvyadav.awl;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentModel {
    private IntegerProperty rollNumber;
    private StringProperty name;
    private StringProperty department;
    private StringProperty section;
    private IntegerProperty semester;
    private BooleanProperty isPresent;

    private StringProperty day1;
    private StringProperty day2;
    private StringProperty day3;
    private StringProperty day4;
    private StringProperty day5;
    private StringProperty day6;
    private StringProperty day7;


    // constructor for student management table
    public StudentModel(int rollnumber, String name, String department, String section, int semester){
        this.rollNumber = new SimpleIntegerProperty(rollnumber);
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleStringProperty(department);
        this.section = new SimpleStringProperty(section);
        this.semester = new SimpleIntegerProperty(semester);

        System.out.println("Object Cretaed");
    }

    // overloaded constructor for mark attendence table
    public StudentModel(int rollNumber, String name, boolean isPresent){
        this.rollNumber = new SimpleIntegerProperty(rollNumber);
        this.name = new SimpleStringProperty(name);
        this.isPresent = new SimpleBooleanProperty(isPresent);

        System.out.println("MA: Object Created");
    }

    // overloaded constructor for view attendence table
    public StudentModel(int rollNumber, String name, String day1, String day2, String day3, String day4, String day5, String day6, String day7){
        this.rollNumber = new SimpleIntegerProperty(rollNumber);
        this.name = new SimpleStringProperty(name);
        this.day1 = new SimpleStringProperty(day1);
        this.day2 = new SimpleStringProperty(day2);
        this.day3 = new SimpleStringProperty(day3);
        this.day4 = new SimpleStringProperty(day4);
        this.day5 = new SimpleStringProperty(day5);
        this.day6 = new SimpleStringProperty(day6);
        this.day7 = new SimpleStringProperty(day7);
    }


    public IntegerProperty getRollNumberProperty(){
        return rollNumber;
    }
    public int getRollNumber(){
        return rollNumber.get();
    }


    public StringProperty getNameProperty (){
        return  name;
    }
    public String getName(){
        return name.get();
    }


    public StringProperty getDepartmentProperty (){
        return  department;
    }
    public String getDepartment(){
        return department.get();
    }


    public StringProperty getSectionProperty (){
        return  section;
    }
    public String getSection(){
        return section.get();
    }
    
    public IntegerProperty getSemesterProperty(){
        return semester;
    }
    public int getSemester(){
        return semester.get();
    }
    
    public BooleanProperty isPresentProperty(){
        return isPresent;
    }
    public boolean isPresent(){
        return isPresent.get();
    }


    public StringProperty getDay1Property(){
        return day1;
    }
    public String getDay1(){
        return day1.get();
    }

    public StringProperty getDay2Property(){
        return day2;
    }
    public String getDay2(){
        return day2.get();
    }
    
    public StringProperty getDay3Property(){
        return day3;
    }
    public String getDay3(){
        return day3.get();
    }
    
    public StringProperty getDay4Property(){
        return day4;
    }
    public String getDay4(){
        return day4.get();
    }
    
    public StringProperty getDay5Property(){
        return day5;
    }
    public String getDay5(){
        return day5.get();
    }
    
    public StringProperty getDay6Property(){
        return day6;
    }
    public String getDay6(){
        return day6.get();
    }
    
    public StringProperty getDay7Property(){
        return day7;
    }
    public String getDay7(){
        return day7.get();
    }
    
}
