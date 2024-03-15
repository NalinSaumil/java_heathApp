import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

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
   private List<DailyData> listOfDailyData;
   
   //User class constructor
   public User(String username, String password, String name, String gender, double weight, int age, double height) {
      this.username = username;
      this.password = password;
      this.name = name;
      this.age = age;
      this.weight = weight;
      this.height = height;
      this.gender = gender;
      this.listOfDailyData = new ArrayList<DailyData>();
   }
   
   //user class get and set methods
   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }
   
   public List<DailyData> getListOfDailyData() {
      return listOfDailyData;
   }
   
   public DailyData getDailyDataByDate(Date date) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      for(DailyData data : listOfDailyData) {
         if(sdf.format(data.getDate()).equals(sdf.format(date))) {
            return data;
         }
      }
      
      DailyData newData = new DailyData(date);
      listOfDailyData.add(newData);
      return newData;
   }
   
   public void resetDailyData() {
      Calendar currentDate = Calendar.getInstance();
      Calendar dataDate = Calendar.getInstance();
   
      for (DailyData dailyData : listOfDailyData) {
         dataDate.setTime(dailyData.getDate());
      
            // Check if the data date is the same as the current date
         if (dataDate.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR)
                    && dataDate.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)
                    && dataDate.get(Calendar.DAY_OF_MONTH) == currentDate.get(Calendar.DAY_OF_MONTH)) {
            dailyData.setBCals(0);
            dailyData.setLCals(0);
            dailyData.setDCals(0);
            dailyData.setWaterIntake(0);
            dailyData.setEx(0);
         }
      }
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

   //Method to Calculate BMR
   public int calcBMR() {
      if(gender.equals("Male")) {
         return (int)(88.362 + (13.397 * (this.weight/2.205)) + (4.799 * (this.height * 2.54)) - (5.677 * this.age));
      } else {
         return (int)(447.593 + (9.247 * (this.weight/2.205)) + (3.098 * (this.height * 2.54)) - (4.330 * this.age));
      }
   }
}
