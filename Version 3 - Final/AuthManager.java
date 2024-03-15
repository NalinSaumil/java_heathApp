import java.util.ArrayList;
import java.util.List;

//This is the AuthManager Class. 
//It is used to keep a list of users that have accounts on the app.
class AuthManager {
   //AuthManager Attributes
   public List<User> users;
   
   //Authmanager constructor
   public AuthManager() {
      users = new ArrayList<>();
        // Add some sample users
      users.add(new User("admin1", "password1", "One", "Male", 160.2, 24, 75));
      users.add(new User("admin2", "password2", "Two", "Female", 120.5, 23, 68));
      users.add(new User("Saumiln", "Bonzi123", "Saumil Nalin", "Male", 211.6, 21, 68.7));
   }
   
   //used to authenticate a log in. 
   //Learn how to add encryption to this???
   public boolean authenticate(String username, String password) {
      for (User user : users) {
         if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return true;
         }
      }
      return false;
   }
   
   //Return user based on username
   public User returnUser(String username) {
      for(User user : users) {
         if(user.getUsername().equals(username)) {
            return user;
         }
      }
      return null;
   }
   
   //Add user during sign up process. 
   //Update this later on for user information expansion.
   public String addUser(String username, String password, String name, String gender, double weight, int age, double height) {
        // Check if the username already exists
      for (User user : users) {
         if (user.getUsername().equals(username)) {
            return "UserN"; // Username already taken
         }
      }
      
      if(height == -1 || weight == -1 || age == -1) {
         return "ValueN";
      }
        // Add the new user
      users.add(new User(username, password, name, gender, weight, age, height));
      return "UserY";
   }
   
   //update user method allows for secure 
   //user updates to the user account information
   public String updateUser(String username, String realName, double height, double weight, int age, String gender) {
      
      if(height == -1 || weight == -1 || age == -1) {
         return "ValueN";
      }
      
      for (User user : users) {
         if (user.getUsername().equals(username)) {
            user.setName(realName);
            user.setHeight(height);
            user.setWeight(weight);
            user.setAge(age);
            user.setGender(gender);
            return "UserY";
         }
      }
      
      return "UserN";
   }
}
