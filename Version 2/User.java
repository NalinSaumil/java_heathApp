import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//This is the user class to store user information
class User {

   //user class attributes
   private String username;
   private String password;
   private String name;
   private String gender;
   private double weight;
   private double height;
   private int age;
   private int Bcals;
   private int Lcals;
   private int Dcals;
   private int waterIntake;
   private int exerciseCals;
   
   //User class constructor
   public User(String username, String password, String name, String gender, double weight, int age, double height) {
      this.username = username;
      this.password = password;
      this.name = name;
      this.age = age;
      this.weight = weight;
      this.height = height;
      this.gender = gender;
      this.Bcals = 0;
      this.Lcals = 0;
      this.Dcals = 0;
      this.waterIntake = 0;
      this.exerciseCals = 0;
   }
   
   //user class get and set methods
   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }
    
   public int getBCals() {
      return Bcals;
   }
    
   public void addBCals(int Bcals) {
      this.Bcals += Bcals;
   }
   
   public int getLCals() {
      return Lcals;
   }
    
   public void addLCals(int Lcals) {
      this.Lcals += Lcals;
   }

   public int getDCals() {
      return Dcals;
   }
    
   public void addDCals(int Dcals) {
      this.Dcals += Dcals;
   }
   
   public int getWaterIntake() {
      return waterIntake;
   }
    
   public void addWaterIntake(int waterIntake) {
      this.waterIntake += waterIntake;
   }
   
   public String getName() {
      return name;
   }
    
   public void setName(String name) {
      this.name = name;
   }
   
   public String getGender() {
      return gender;
   }
    
   public void setGender(String gender) {
      this.gender = gender;
   }
   
   public double getWeight() {
      return weight;
   }
   
   public void setWeight(double weight) {
      this.weight = weight;
   }
   
   public double getHeight() {
      return height;
   }
   
   public void setHeight(double height) {
      this.height = height;
   }
   
   public int getAge() {
      return age;
   }
   
   public void setAge(int age) {
      this.age = age;
   }
   
   public int getEx() {
      return exerciseCals;
   }
   
   public void addEx(int ex) {
      this.exerciseCals += ex;
   }

   //Method to Calculate BMR
   public int calcBMR() {
      if(gender.equals("Male")) {
         return (int)(88.362 + (13.397 * (this.weight/2.205)) + (4.799 * (this.height * 2.54)) - (5.677 * this.age));
      } else {
         return (int)(447.593 + (9.247 * (this.weight/2.205)) + (3.098 * (this.height * 2.54)) - (4.330 * this.age));
      }
   }
}