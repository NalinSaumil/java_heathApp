import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

//Class to enter a new date for data viewing/editing
class ChangeDate {
    //Class atrributes
   private JFrame frame;
   private Dashboard dashboard;

    //class constructor to initiate page
   public ChangeDate(Dashboard dashboard) {
      this.dashboard = dashboard;
     
      frame = new JFrame("Change Date");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800, 600);
      centerFrame(frame);
     
      JPanel panel = new JPanel();
      frame.add(panel);
      placeComponents(panel);
     
      frame.setVisible(true);
   }
     
     //Build change date page
   private void placeComponents(JPanel panel) {
      panel.setLayout(null);
     
      ImageIcon logoIcon = new ImageIcon("v147_81.png");
      Image logoImage = logoIcon.getImage().getScaledInstance(155, 100, Image.SCALE_DEFAULT);
      ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
     
      JLabel logoLabel = new JLabel(scaledLogoIcon);
      logoLabel.setBounds(10, 10, 100, 100);
      panel.add(logoLabel);
     
      JLabel promptLabel = new JLabel("Enter Date [mm/dd/yyyy]: ");
      promptLabel.setBounds(10, 110, 150, 25);
      panel.add(promptLabel);
     
      JTextField dateField = new JTextField(20);
      dateField.setBounds(175, 110, 100, 25);
      panel.add(dateField);
      
      JLabel msgLabel = new JLabel("You can also type \"CD\" to return dashboard to current date.");
      msgLabel.setBounds(10, 140, 800, 25);
      panel.add(msgLabel);
     
      JButton saveButton = new JButton("Save");
      saveButton.setBounds(10, 170, 80, 25);
      panel.add(saveButton);
        
      JButton backButton = new JButton("Back");
      backButton.setBounds(110, 170, 80, 25);
      panel.add(backButton);
        
        //assign actions to button
      saveButton.addActionListener(
           new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                 Date currentDate = new Date();
                 if(isValidDate(dateField.getText())) {
                    try {
                       Date newDate = dateFormat.parse(dateField.getText());
                       if(currentDate.after(newDate) && !isCurrentDate(newDate)){
                          dashboard.showFrame();
                          dashboard.setDate(newDate);
                          dashboard.past = true;
                          dashboard.evalFwdButton();
                          dashboard.updateDailyData();
                          frame.dispose(); // Close the date change page
                       } else {
                          JOptionPane.showMessageDialog(panel, "You can only change to dates in the past.");
                       }
                    } catch (ParseException exception) {
                       JOptionPane.showMessageDialog(panel, "Please only enter correct dates in given format or CD.");
                    }
                 } else if (dateField.getText().toLowerCase().equals("cd")) {
                    Date nowDate = new Date();
                    dashboard.setDate(nowDate);
                    dashboard.past = false;
                    dashboard.evalFwdButton();
                    dashboard.updateDailyData();
                    frame.dispose(); // Close the date change page
                 } else {
                    JOptionPane.showMessageDialog(panel, "Please only enter correct dates in given format or CD.");
                 }
              }
           });
           
      backButton.addActionListener(
           new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 dashboard.working = false;
                 dashboard.showFrame();
                 frame.dispose(); // Close the current dashboard.
              }
           });
   }
   
   public static boolean isValidDate(String date) {
      String datePattern = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$";
   
      if (!date.matches(datePattern)) {
         return false; // Invalid format
      }
      
      String[] parts = date.split("/");
   
        // Extract month, day, and year
      int month = Integer.parseInt(parts[0]);
      int day = Integer.parseInt(parts[1]);
      int year = Integer.parseInt(parts[2]);
   
        // Validate days in each month
      if (day > 0 && day <= getDaysInMonth(month, year)) {
         return true; // Valid date
      } else {
         return false; // Invalid day for the given month
      }
   }

   private static int getDaysInMonth(int month, int year) {
      switch (month) {
         case 4:
            case 6:
            case 9:
            case 11:
            return 30;
         case 2:
            return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
         default:
            return 31;
      }
   }
   
   private boolean isCurrentDate(Date date) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      if(sdf.format(date).equals(sdf.format(new Date()))) {
         return true;
      }
      return false;
   }
     
   private void centerFrame(JFrame frame) {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (dim.width - frame.getWidth()) / 2;
      int y = (dim.height - frame.getHeight()) / 2;
      frame.setLocation(x, y);
   }
}
