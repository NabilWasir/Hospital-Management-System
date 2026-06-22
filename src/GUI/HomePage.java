package GUI;

import Files.*;
import EntityList.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import java.io.*;

import GUI.Resource.MyFont;
import GUI.Resource.color.MyColor;

public class HomePage extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton patientButton, doctorButton, employeeButton, logoutButton;
    private PatientList patientList;
    private DoctorList doctorList;
    private EmployeeList employeeList;
    private LoginPage loginPage;

    public HomePage(LoginPage loginPage, PatientList patientList, DoctorList doctorList, EmployeeList employeeList){
        super("Home Page");

        // Initialize the lists
        this.loginPage = loginPage;
        this.patientList = patientList;
        this.doctorList = doctorList;
        this.employeeList = employeeList;

        this.setSize(1230, 740);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(640, 130, 490, 450);
        buttonPanel.setLayout(null);
        buttonPanel.setOpaque(false);
        backgroundLabel.add(buttonPanel);

        // Patient Button
        patientButton = new JButton("Patient");
        patientButton.setBounds(0, 0, 490, 90);
        patientButton.setFont(MyFont.managerButtonFont);
        patientButton.setForeground(MyColor.blue);
        patientButton.setBackground(MyColor.white);
        patientButton.setBorder(new RoundBorder(20, 3, MyColor.blue));
        patientButton.setOpaque(true);
        patientButton.setFocusable(false);
        patientButton.addActionListener(this);
        buttonPanel.add(patientButton);

        // Doctor Button
        doctorButton = new JButton("Doctor");
        doctorButton.setBounds(0, 120, 490, 90);
        doctorButton.setFont(MyFont.managerButtonFont);
        doctorButton.setForeground(MyColor.blue);
        doctorButton.setBackground(MyColor.white);
        doctorButton.setBorder(new RoundBorder(20, 3, MyColor.blue));
        doctorButton.setOpaque(true);
        doctorButton.setFocusable(false);
        doctorButton.addActionListener(this);
        buttonPanel.add(doctorButton);

        // Employee Button
        employeeButton = new JButton("Employee");
        employeeButton.setBounds(0, 240, 490, 90);
        employeeButton.setFont(MyFont.managerButtonFont);
        employeeButton.setForeground(MyColor.blue);
        employeeButton.setBackground(MyColor.white);
        employeeButton.setBorder(new RoundBorder(20, 3, MyColor.blue));
        employeeButton.setOpaque(true);
        employeeButton.setFocusable(false);
        employeeButton.addActionListener(this);
        buttonPanel.add(employeeButton);

        // Logout Button
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(0, 360, 490, 90);
        logoutButton.setFont(MyFont.managerButtonFont);
        logoutButton.setForeground(MyColor.white);
        logoutButton.setBackground(MyColor.blue);
        logoutButton.setBorder(new RoundBorder(20, 3, MyColor.blue));
        logoutButton.setOpaque(true);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);
        buttonPanel.add(logoutButton);

        this.setVisible(true);
    }

    // actionPerformed method
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == patientButton){
            new PatientPage(this, patientList);
            this.setVisible(false);
        }
        else if(e.getSource() == doctorButton){
            new DoctorPage(this, doctorList);
            this.setVisible(false);
        }
        else if(e.getSource() == employeeButton){
            new EmployeePage(this, employeeList);
            this.setVisible(false);
        }
        else if(e.getSource() == logoutButton){
            int option = JOptionPane.showConfirmDialog(this, "Do you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                this.dispose();
                if(loginPage != null){
                    loginPage.setVisible(true);
                }
            }
        }
    }
}