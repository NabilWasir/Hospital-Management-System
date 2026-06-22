package GUI;

import Files.*;
import Entity.*;
import EntityList.DoctorList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import GUI.Resource.MyFont;
import GUI.Resource.color.MyColor;
import javax.swing.table.*;

public class DoctorPage extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel label, bgColor;
    private JTextField nameTextfield, ageTextfield, idTextfield, genderTextfield, specializationTextfield, searchTextfield;
    private JButton addButton, removeButton, backButton, searchButton, updateButton;
    private DefaultTableModel model;
    private JTable table;
    private DoctorList doctorList;
    private HomePage home;

    public DoctorPage(HomePage home, DoctorList doctorList) {
        super("My Doctor Manager");
        this.home = home;
        this.doctorList = doctorList;

        initializeFrame();
        createTable();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveDataToFile();
            }
        });

        this.setVisible(true);
    }

    private void saveDataToFile() {
        try {
            tableModelToList();
            DoctorFileIO.saveDoctorListInFile(doctorList, "Files/DoctorInfo.txt", false);
            System.out.println("Doctor data saved successfully!");
        } catch (Exception ex) {
            System.out.println("Error saving doctor data: " + ex.getMessage());
        }
    }

    private String toUpperCase(String str) {
        return (str == null || str.isEmpty()) ? "" : str.toUpperCase();
    }

    public void initializeFrame() {
        this.setSize(1230, 740);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);

        ImageIcon backgroundImage = new ImageIcon("src/GUI/Resource/background.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        Image scaledBgImage = backgroundImage.getImage().getScaledInstance(1230, 740, Image.SCALE_SMOOTH);
        backgroundLabel.setBounds(0, 0, 1230, 740);
        backgroundLabel.setOpaque(true);
        this.setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        panel = new JPanel();
        panel.setBounds(870, 32, 330, 670);
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createLineBorder(MyColor.blue, 2));
        panel.setLayout(null);
        backgroundLabel.add(panel);

        ImageIcon icon = new ImageIcon("GUI/Resource/logo.png");
        Image WImage = icon.getImage();
        this.setIconImage(WImage);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(MyFont.labelFont);
        nameLabel.setBounds(15, 7, 300, 40);
        nameLabel.setForeground(MyColor.blue);
        panel.add(nameLabel);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setFont(MyFont.labelFont);
        ageLabel.setBounds(15, 89, 300, 40);
        ageLabel.setForeground(MyColor.blue);
        panel.add(ageLabel);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(MyFont.labelFont);
        genderLabel.setBounds(15, 171, 300, 40);
        genderLabel.setForeground(MyColor.blue);
        panel.add(genderLabel);

        JLabel idLabel = new JLabel("Doctor ID");
        idLabel.setFont(MyFont.labelFont);
        idLabel.setBounds(15, 253, 300, 40);
        idLabel.setForeground(MyColor.blue);
        panel.add(idLabel);

        JLabel specializationLabel = new JLabel("Specialization");
        specializationLabel.setFont(MyFont.labelFont);
        specializationLabel.setBounds(15, 334, 300, 40);
        specializationLabel.setForeground(MyColor.blue);
        panel.add(specializationLabel);

        nameTextfield = new JTextField();
        nameTextfield.setFont(MyFont.textFieldFont);
        nameTextfield.setBounds(15, 48, 300, 40);
        nameTextfield.setBorder(new RoundBorder(20, 1, MyColor.blue));
        panel.add(nameTextfield);

        ageTextfield = new JTextField();
        ageTextfield.setFont(MyFont.textFieldFont);
        ageTextfield.setBounds(15, 130, 300, 40);
        ageTextfield.setBorder(new RoundBorder(20, 1, MyColor.blue));
        panel.add(ageTextfield);

        genderTextfield = new JTextField();
        genderTextfield.setFont(MyFont.textFieldFont);
        genderTextfield.setBounds(15, 212, 300, 40);
        genderTextfield.setBorder(new RoundBorder(20, 1, MyColor.blue));
        panel.add(genderTextfield);

        idTextfield = new JTextField();
        idTextfield.setFont(MyFont.textFieldFont);
        idTextfield.setBounds(15, 295, 300, 40);
        idTextfield.setBorder(new RoundBorder(20, 1, MyColor.blue));
        panel.add(idTextfield);

        specializationTextfield = new JTextField();
        specializationTextfield.setFont(MyFont.textFieldFont);
        specializationTextfield.setBounds(15, 375, 300, 40);
        specializationTextfield.setBorder(new RoundBorder(20, 1, MyColor.blue));
        panel.add(specializationTextfield);

        addButton = new JButton("Add Doctor");
        addButton.setFont(MyFont.buttonFont);
        addButton.setBounds(15, 430, 300, 45);
        addButton.setForeground(MyColor.white);
        addButton.setBackground(MyColor.blue);
        addButton.setBorder(new RoundBorder(20, 3, MyColor.blue));
        addButton.setOpaque(true);
        addButton.setFocusable(false);
        addButton.addActionListener(this);
        panel.add(addButton);

        updateButton = new JButton("Update");
        updateButton.setFont(MyFont.buttonFont);
        updateButton.setBounds(15, 490, 300, 45);
        updateButton.setForeground(MyColor.white);
        updateButton.setBackground(MyColor.green);
        updateButton.setBorder(new RoundBorder(20, 3, MyColor.green));
        updateButton.setOpaque(true);
        updateButton.setFocusable(false);
        updateButton.addActionListener(this);
        panel.add(updateButton);

        removeButton = new JButton("Remove");
        removeButton.setFont(MyFont.labelFont);
        removeButton.setBounds(15, 550, 300, 45);
        removeButton.setForeground(MyColor.white);
        removeButton.setBackground(MyColor.red);
        removeButton.setBorder(new RoundBorder(20, 3, MyColor.red));
        removeButton.setOpaque(true);
        removeButton.setFocusable(false);
        removeButton.addActionListener(this);
        panel.add(removeButton);

        backButton = new JButton("Back");
        backButton.setBounds(15, 610, 300, 45);
        backButton.setFont(MyFont.buttonFont);
        backButton.setForeground(MyColor.blue);
        backButton.setBackground(MyColor.white);
        backButton.setBorder(new RoundBorder(20, 3, MyColor.blue));
        backButton.setOpaque(true);
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        panel.add(backButton);

        searchButton = new JButton("Search");
        searchButton.setFont(MyFont.buttonFont);
        searchButton.setBounds(540, 32, 300, 50);
        searchButton.setForeground(MyColor.blue);
        searchButton.setBackground(MyColor.white);
        searchButton.setBorder(new RoundBorder(20, 3, MyColor.blue));
        searchButton.setOpaque(true);
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);
        backgroundLabel.add(searchButton);

        searchTextfield = new JTextField();
        searchTextfield.setFont(MyFont.textFieldFont);
        searchTextfield.setBounds(26, 32, 490, 50);
        searchTextfield.setBorder(new RoundBorder(20, 3, MyColor.blue));
        backgroundLabel.add(searchTextfield);
    }

    public void createTable() {
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Gender");
        model.addColumn("ID");
        model.addColumn("Specialization");

        table = new JTable(model);
        table.setFont(MyFont.entryFont);
        table.setOpaque(false);
        table.setRowHeight(45);
        table.setSelectionBackground(new Color(247, 146, 146));
        table.getTableHeader().setFont(MyFont.entryFont);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setForeground(MyColor.blue);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 95, 815, 605);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(MyColor.blue, 2));

        Entity.Doctor doctors[];
        doctors = this.doctorList.getAllDoctor();

        if (doctors != null) {
            for (int i = 0; i < doctors.length; i++) {
                if (doctors[i] != null) {
                    model.addRow(new Object[]{
                            toUpperCase(doctors[i].getName()),
                            doctors[i].getAge(),
                            toUpperCase(doctors[i].getGender()),
                            doctors[i].getId(),
                            toUpperCase(doctors[i].getSpecialization())
                    });
                }
            }
        }

        this.add(scrollPane);
    }

    private void searchDoctor() {
        String searchText = searchTextfield.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID or Name to search", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean found = false;
        java.util.ArrayList<Integer> foundRows = new java.util.ArrayList<>();

        if (searchText.matches("\\d+")) {
            int searchId = Integer.parseInt(searchText);
            for (int i = 0; i < table.getRowCount(); i++) {
                Object idObject = table.getValueAt(i, 3);
                int tableId = Integer.parseInt(idObject.toString());
                if (searchId == tableId) {
                    foundRows.add(i);
                    found = true;
                }
            }
            if (found) {
                table.setRowSelectionInterval(foundRows.get(0), foundRows.get(0));
                table.scrollRectToVisible(table.getCellRect(foundRows.get(0), 0, true));
                table.setSelectionBackground(new Color(50, 240, 91));
                JOptionPane.showMessageDialog(this, "Found " + foundRows.size() + " match(es)!\nFirst match at row: " + (foundRows.get(0) + 1));
                return;
            }
        }

        String searchName = searchText.toUpperCase();
        for (int i = 0; i < table.getRowCount(); i++) {
            String tableName = table.getValueAt(i, 0).toString().toUpperCase();
            if (tableName.contains(searchName)) {
                foundRows.add(i);
                found = true;
            }
        }

        if (found) {
            table.setRowSelectionInterval(foundRows.get(0), foundRows.get(0));
            table.scrollRectToVisible(table.getCellRect(foundRows.get(0), 0, true));
            table.setSelectionBackground(new Color(50, 240, 91));
            JOptionPane.showMessageDialog(this, "Found " + foundRows.size() + " match(es)!\nFirst match at row: " + (foundRows.get(0) + 1));
        } else {
            JOptionPane.showMessageDialog(this, "No records found matching: " + searchText, "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (addButton == e.getSource()) {
            try {
                if (nameTextfield.getText().trim().isEmpty() || genderTextfield.getText().trim().isEmpty() || specializationTextfield.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter all details", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = toUpperCase(nameTextfield.getText().trim());
                int age = Integer.parseInt(ageTextfield.getText().trim());
                String gender = toUpperCase(genderTextfield.getText().trim());
                int id = Integer.parseInt(idTextfield.getText().trim());
                String specialization = toUpperCase(specializationTextfield.getText().trim());

                Entity.Doctor d = new Entity.Doctor(name, age, gender, id, specialization);
                doctorList.insertDoctor(d);

                model.addRow(new Object[]{
                        d.getName(),
                        d.getAge(),
                        d.getGender(),
                        d.getId(),
                        d.getSpecialization()
                });

                saveDataToFile();

                nameTextfield.setText("");
                ageTextfield.setText("");
                genderTextfield.setText("");
                idTextfield.setText("");
                specializationTextfield.setText("");

                JOptionPane.showMessageDialog(this, "Doctor added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Age and ID must be numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (updateButton == e.getSource()) {
            try {
                tableModelToList();
                saveDataToFile();
                JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (removeButton == e.getSource()) {
            try {
                int selectedRows[] = table.getSelectedRows();
                if (selectedRows.length == 0) {
                    JOptionPane.showMessageDialog(this, "Please select a row to remove", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Arrays.sort(selectedRows);

                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        Object idObject = table.getModel().getValueAt(selectedRows[i], 3);
                        int id = Integer.parseInt(idObject.toString());
                        doctorList.removeDoctorById(id);
                        model.removeRow(selectedRows[i]);
                    }
                    saveDataToFile();
                    JOptionPane.showMessageDialog(this, "Removed Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Enter ID in number format", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (searchButton == e.getSource()) {
            searchDoctor();
        } else if (backButton == e.getSource()) {
            saveDataToFile();
            home.setVisible(true);
            this.dispose();
        }
    }

    public void tableModelToList() {
        int rows = table.getRowCount();
        DoctorList updatedList = new DoctorList(100);

        for (int i = 0; i < rows; i++) {
            String name = toUpperCase(table.getModel().getValueAt(i, 0).toString());
            int age = Integer.parseInt(table.getModel().getValueAt(i, 1).toString());
            String gender = toUpperCase(table.getModel().getValueAt(i, 2).toString());
            int id = Integer.parseInt(table.getModel().getValueAt(i, 3).toString());
            String specialization = toUpperCase(table.getModel().getValueAt(i, 4).toString());

            Doctor doc = new Doctor(name, age, gender, id, specialization);
            updatedList.insertDoctor(doc);
        }
        doctorList.setAllDoctors(updatedList.getAllDoctor());
    }
}