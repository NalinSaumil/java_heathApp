import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ChooseMeal class is for the user to be able 
//to choose which meal they want to add calories for.
class ChooseMeal {
    //ChooseMeal Attributes
   private JFrame frame;
   private Dashboard dashboard;
   private String meal;
    
    //ChooseMeal  Contrustor to initiate Page
   public ChooseMeal(Dashboard dashboard) {
      this.dashboard = dashboard;
      this.meal = "";
   
      frame = new JFrame("Choose Meal");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      centerFrame(frame);
   
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
   
      frame.setVisible(true);
   }
    
    //Build Choose Meal page
   private void placeComponents(JPanel panel) {
      panel.setLayout(null);
   
      ImageIcon logoIcon = new ImageIcon("v147_81.png");
      Image logoImage = logoIcon.getImage().getScaledInstance(155, 100, Image.SCALE_DEFAULT);
      ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
   
      JLabel logoLabel = new JLabel(scaledLogoIcon);
      logoLabel.setBounds(10, 10, 100, 100);
      panel.add(logoLabel);
        
      JLabel promptLabel = new JLabel("Choose Meal to enter calories:");
      promptLabel.setBounds(10, 110, 200, 25);
      panel.add(promptLabel);
   
      JButton BButton = new JButton("Breakfast");
      BButton.setBounds(10, 140, 100, 25);
      panel.add(BButton);
        
      JButton LButton = new JButton("Lunch");
      LButton.setBounds(115, 140, 80, 25);
      panel.add(LButton);
        
      JButton DButton = new JButton("Dinner");
      DButton.setBounds(200, 140, 80, 25);
      panel.add(DButton);
      
      JButton backButton = new JButton("Back");
      backButton.setBounds(10, 170, 80, 25);
      panel.add(backButton);
        
        //Assign action to buttons
      BButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               meal = "Breakfast";
               frame.dispose(); // Close the current dashboard
               new CalorieEntryPage(dashboard, ChooseMeal.this, meal); // Open the calorie entry page
            }
         });
        
      LButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               meal = "Lunch";
               frame.dispose(); // Close the current dashboard
               new CalorieEntryPage(dashboard, ChooseMeal.this, meal); // Open the calorie entry page
            }
         });
        
      DButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               meal = "Dinner";
               frame.dispose(); // Close the current dashboard
               new CalorieEntryPage(dashboard, ChooseMeal.this, meal); // Open the calorie entry page
            }
         });
         
      backButton.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dashboard.working = false;
               dashboard.showFrame();
               frame.dispose(); // Close the current dashboard
            }
         });
   }

   public void showFrame() {
      frame.setVisible(true);
   }

   private void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
}
