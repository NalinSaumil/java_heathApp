import java.util.Date;

class DailyData {
   
   private Date date;
   private int Bcals;
   private int Lcals;
   private int Dcals;
   private int waterIntake;
   private int exerciseCals;
   
   public DailyData(Date date) {
      this.date = date;
      this.Bcals = 0;
      this.Lcals = 0;
      this.Dcals = 0;
      this.waterIntake = 0;
      this.exerciseCals = 0;
   }
   
   public Date getDate() {
      return date;
   }
   
   public int getBCals() {
      return Bcals;
   }
   
   public void addBCals(int Bcals) {
      this.Bcals += Bcals;
   }
   
   public void setBCals(int Bcals) {
      this.Bcals = Bcals;
   }
   
   public int getLCals() {
      return Lcals;
   }
   
   public void addLCals(int Lcals) {
      this.Lcals += Lcals;
   }
   
   public void setLCals(int Lcals) {
      this.Lcals = Lcals;
   }
   
   public int getDCals() {
      return Dcals;
   }
   
   public void addDCals(int Dcals) {
      this.Dcals += Dcals;
   }
   
   public void setDCals(int Dcals) {
      this.Dcals = Dcals;
   }
   
   public int getWaterIntake() {
      return waterIntake;
   }
   
   public void addWaterIntake(int waterIntake) {
      this.waterIntake += waterIntake;
   }
   
   public void setWaterIntake(int waterIntake) {
      this.waterIntake = waterIntake;
   }
   
   public int getEx() {
      return exerciseCals;
   }
   
   public void addEx(int exerciseCals) {
      this.exerciseCals += exerciseCals;
   }
   
   public void setEx(int exerciseCals) {
      this.exerciseCals = exerciseCals;
   }
}
