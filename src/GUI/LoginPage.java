package GUI;

import Files.*;
import EntityList.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import GUI.Resource.MyFont;
import GUI.Resource.color.MyColor;

public class LoginPage extends JFrame implements ActionListener {
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton signInButton;

    private PatientList patientList;
    private DoctorList doctorList;
    private EmployeeList employeeList;

    public LoginPage() {
        super("Login Page");
        this.setSize(1230, 740);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize lists with larger size
        patientList = new PatientList(200);
        doctorList = new DoctorList(200);
        employeeList = new EmployeeList(200);

        // Create Files directory if it doesn't exist
        File filesDir = new File("Files");
        if (!filesDir.exists()) {
            filesDir.mkdirs();
            System.out.println("Created Files directory");
        }

        // Load data from files
        loadDataFromFiles();

        // Window Icon
        ImageIcon icon = new ImageIcon("src/GUI/Resource/Login.png");
        Image WImage = icon.getImage();
        this.setIconImage(WImage);

        // Background Image
        ImageIcon backgroundImage = new ImageIcon("src/GUI/Resource/background.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        Image scaledBgImage = backgroundImage.getImage().getScaledInstance(1230, 740, Image.SCALE_SMOOTH);
        backgroundLabel.setBounds(0, 0, 1230, 740);
        backgroundLabel.setOpaque(true);
        this.setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        // Hospital Logo
        ImageIcon originalLogo = new ImageIcon("src/GUI/Resource/logo.png");
        int logoWidth = 535;
        int logoHeight = 500;
        Image scaledImage = originalLogo.getImage().getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledLogo);
        logoLabel.setBounds(45, 100, logoWidth, logoHeight);
        backgroundLabel.add(logoLabel);

        // Login Card Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(630, 50, 530, 610);
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(BorderFactory.createLineBorder(MyColor.blue, 3));
        loginPanel.setLayout(null);
        loginPanel.setOpaque(false);
        backgroundLabel.add(loginPanel);

        // Sign In Title
        JLabel signInText = new JLabel("Login Page");
        signInText.setBounds(75, 57, 378, 69);
        signInText.setFont(MyFont.signInFont);
        signInText.setForeground(MyColor.blue);
        signInText.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(signInText);

        // Username Label
        JLabel usernameTFLabel = new JLabel("Username");
        usernameTFLabel.setBounds(30, 160, 150, 42);
        usernameTFLabel.setForeground(Color.BLACK);
        usernameTFLabel.setFont(MyFont.loginPageLabel);
        loginPanel.add(usernameTFLabel);

        // Username TextField
        usernameTextField = new JTextField("admin");
        usernameTextField.setBounds(30, 220, 470, 72);
        usernameTextField.setBorder(new RoundBorder(20, 3, MyColor.blue));
        usernameTextField.setFont(MyFont.textFieldFont);
        loginPanel.add(usernameTextField);

        // Password Label
        JLabel passwordTFLabel = new JLabel("Password");
        passwordTFLabel.setBounds(30, 320, 180, 35);
        passwordTFLabel.setForeground(Color.BLACK);
        passwordTFLabel.setFont(MyFont.loginPageLabel);
        loginPanel.add(passwordTFLabel);

        // Password TextField
        passwordTextField = new JPasswordField("admin");
        passwordTextField.setBounds(30, 370, 470, 72);
        passwordTextField.setBorder(new RoundBorder(20, 3, MyColor.blue));
        passwordTextField.setFont(MyFont.textFieldFont);
        passwordTextField.setEchoChar('.');
        loginPanel.add(passwordTextField);

        // Sign In Button
        signInButton = new JButton("Sign In");
        signInButton.setBounds(30, 475, 470, 90);
        signInButton.setFont(MyFont.managerButtonFont);
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(MyColor.blue);
        signInButton.setOpaque(true);
        signInButton.setBorderPainted(false);
        signInButton.setFocusable(false);
        signInButton.addActionListener(this);
        loginPanel.add(signInButton);

        this.setVisible(true);
    }

    private void loadDataFromFiles() {
        try {
            System.out.println("Loading data from files...");
            PatientFileIO.readFromFile("Files/PatientInfo.txt", patientList);
            DoctorFileIO.readFromFile("Files/DoctorInfo.txt", doctorList);
            EmployeeFileIO.readFromFile("Files/EmployeeInfo.txt", employeeList);
            System.out.println("Data loading completed!");
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (signInButton == e.getSource()) {
            String name = usernameTextField.getText();
            String pass = String.valueOf(passwordTextField.getPassword());

            try {
                if (name.equals("admin") && pass.equals("admin")) {
                    JOptionPane.showMessageDialog(this, "Login Successful");

                    usernameTextField.setText("");
                    passwordTextField.setText("");

                    HomePage homePage = new HomePage(this, patientList, doctorList, employeeList);
                    homePage.setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid User Name or Password");
                }
            } catch (Exception exp) {
                JOptionPane.showMessageDialog(this, "Invalid User Name or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}