/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.main;

//import com.model.MySQL;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import com.model.MySQL;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Chamika
 */
public class employee_management extends javax.swing.JPanel {

    /**
     * Creates new form Employee_Management1
     */
    public employee_management() {
        initComponents();
        loadEmployee("SELECT * FROM `employee` INNER JOIN `employee_position` ON "
                + "`employee`.`employee_position_id` = `employee_position`.`id` INNER JOIN `status` ON "
                + "`employee`.`status_id` = `status`.`id` INNER JOIN `gender` ON"
                + "`employee`.`gender_id`=`gender`.`id` INNER JOIN `department` ON"
                + "`employee`.`department_id`=`department`.`id`");
        load_Employee_Position();
        load_Employee_Status();
        load_Employee_gender();
        load_departments();
        reset();
    }

    private void loadEmployee(String quary) {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            ResultSet resultSet = MySQL.execute(quary);

            while (resultSet.next()) {
                Vector v = new Vector();
                v.add(resultSet.getString("id"));
                v.add(resultSet.getString("fname"));
                v.add(resultSet.getString("lname"));
                v.add(resultSet.getString("email"));
                v.add(resultSet.getString("mobile"));
//                v.add(resultSet.getString("username"));
                v.add(resultSet.getString("department"));
                v.add(resultSet.getString("position"));
                v.add(resultSet.getString("status"));
                v.add(resultSet.getString("gender"));
                v.add(resultSet.getString("registerd_date"));
                model.addRow(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private HashMap<String, String> EmployeePositionMap = new HashMap<>();

    private void load_Employee_Position() {
        try {

            ResultSet resultSet = MySQL.execute("SELECT * FROM `employee_position`");

            Vector v = new Vector();
            v.add("Select");

            while (resultSet.next()) {

                v.add(resultSet.getString("position"));
                EmployeePositionMap.put(resultSet.getString("position"), resultSet.getString("id"));

            }
            DefaultComboBoxModel model = (DefaultComboBoxModel) jComboBox1.getModel();
            model.removeAllElements();

            model.addAll(v);
            jComboBox1.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> EmployeeStatusMap = new HashMap<>();

    private void load_Employee_Status() {
        try {

            ResultSet resultSet = MySQL.execute("SELECT * FROM `status`");

            Vector v = new Vector();
            v.add("Select");

            while (resultSet.next()) {

                v.add(resultSet.getString("status"));
                EmployeeStatusMap.put(resultSet.getString("status"), resultSet.getString("id"));

            }
            DefaultComboBoxModel model = (DefaultComboBoxModel) jComboBox2.getModel();
            model.removeAllElements();

            model.addAll(v);
            jComboBox2.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> EmployeeGenderMap = new HashMap<>();

    private void load_Employee_gender() {

        try {

            ResultSet resultSet = MySQL.execute("SELECT * FROM `gender`");

            Vector v = new Vector();
            v.add("Select");

            while (resultSet.next()) {

                v.add(resultSet.getString("gender"));
                EmployeeGenderMap.put(resultSet.getString("gender"), resultSet.getString("id"));

            }
            DefaultComboBoxModel model = (DefaultComboBoxModel) jComboBox3.getModel();
            model.removeAllElements();

            model.addAll(v);
            jComboBox3.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   

    private void load_departments() {

        try {

            ResultSet resultSet = MySQL.execute("SELECT * FROM `department`");

            Vector v = new Vector();
            v.add("Select");

            while (resultSet.next()) {

                v.add(resultSet.getString("id"));
               

            }
            DefaultComboBoxModel model = (DefaultComboBoxModel) jComboBox4.getModel();
            model.removeAllElements();

            model.addAll(v);
            jComboBox4.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
//        jTextField5.setText("");
        jTextField7.setText("");
//        jPasswordField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
    }

    private String generateUniqueID(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // Generate a random digit from 0 to 9 and append it to the StringBuilder
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new org.netbeans.modules.form.InvalidComponent();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelRound2 = new org.netbeans.modules.form.InvalidComponent();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField7 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 153, 51));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Email", "Mobile", "Generated_Id", "Position", "Status", "Gender", "Registerd_date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
            jTable1.getColumnModel().getColumn(9).setResizable(false);
        }

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
        );

        jLabel1.setText("First Name");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Last Name");

        jLabel3.setText("E-mail");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Mobile");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jLabel5.setText("Position");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Status");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Gender");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Create Account");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Update Account");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Upload Image");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jTextField7.setEditable(false);
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, 245, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(157, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel3);

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        if (jComboBox4.getSelectedIndex() == 0) {

            String uniqueID = generateUniqueID(4);
            String prefixedID = "hr-" + uniqueID;

            jTextField7.setText(prefixedID);
            System.out.println("Generated Unique ID: " + prefixedID);
        } else if (jComboBox4.getSelectedIndex() == 1) {

            String uniqueID = generateUniqueID(4);
            String prefixedID = "fi-" + uniqueID;

            jTextField7.setText(prefixedID);
            System.out.println("Generated Unique ID: " + prefixedID);
        } else if (jComboBox4.getSelectedIndex() == 2) {

            String uniqueID = generateUniqueID(4);
            String prefixedID = "mr-" + uniqueID;

            jTextField7.setText(prefixedID);
            System.out.println("Generated Unique ID: " + prefixedID);
        } else if (jComboBox4.getSelectedIndex() == 3) {

            String uniqueID = generateUniqueID(4);
            String prefixedID = "pr-" + uniqueID;

            jTextField7.setText(prefixedID);
            System.out.println("Generated Unique ID: " + prefixedID);
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //        JFileChooser chooser = new JFileChooser();
        //        chooser.showOpenDialog( null);
        //        File f = chooser.getSelectedFile();
        //        String path = f.getAbsolutePath();
        //        try {
            //            BufferedImage bi = ImageIO.read(new File(path));
            //            Image img = bi.getScaledInstance(134, 172, Image.SCALE_SMOOTH);
            //            ImageIcon icon = new ImageIcon(img);
            //            jLabel8.setIcon(icon);
            //        } catch (Exception e) {
            //            e.printStackTrace();
            //        }

        // Create a file chooser
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();

        // Set file filter for images
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

        // Open the file chooser dialog
        int returnValue = fileChooser.showOpenDialog(null);

        // Check if the user has selected a file
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                // Get the selected file
                File selectedFile = fileChooser.getSelectedFile();

                // Read the image into a BufferedImage
                BufferedImage bi = ImageIO.read(selectedFile);

                // Resize the image to fit the JLabel
                Image img = bi.getScaledInstance(134, 172, Image.SCALE_SMOOTH);

                // Set the resized image as the icon of jLabel8
                jLabel8.setIcon(new ImageIcon(img));

                // Get the project path and target directory
                String projectPath = System.getProperty("user.dir");
                String targetDirPath = projectPath + "/src/healthy_harvest/punsisi/image";

                // Ensure the target directory exists
                File targetDir = new File(targetDirPath);
                if (!targetDir.exists()) {
                    targetDir.mkdirs();
                }

                // Copy the selected file to the target directory
                File targetFile = new File(targetDir, selectedFile.getName());
                Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Store the image path relative to the project
                String imagePath = targetFile.getAbsolutePath();

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error uploading image: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow != -1) {

            String id = jTable1.getValueAt(selectedRow, 0).toString();
            String firstName = jTextField1.getText();
            String lastName = jTextField2.getText();
            String email = jTextField3.getText();
            String mobile = jTextField4.getText();
            String department = jTextField7.getText();
            //            String username = jTextField5.getText();
            //            String password = String.valueOf(jPasswordField1.getPassword());
            String position = jComboBox1.getSelectedItem().toString();
            String status = jComboBox2.getSelectedItem().toString();
            String gender = jComboBox3.getSelectedItem().toString();

            //            String registerd_date = jTextField1.getText();
            MySQL.execute("UPDATE `employee` SET `fname`='" + firstName + "',"
                + "`lname`='" + lastName + "',"
                + "`email`='" + email + "',"
                + "`mobile`='" + mobile + "',"
                + "`department_id`='" + department + "',"
                + "`employee_position_id`='" + EmployeePositionMap.get(position) + "',"
                + "`status_id`='" + EmployeeStatusMap.get(status) + "', "
                + "`gender_id`='" + EmployeeGenderMap.get(gender) + "'"
                + "WHERE `id`='" + id + "'");

            loadEmployee("SELECT * FROM `employee` INNER JOIN `employee_position` ON "
                + "`employee`.`employee_position_id` = `employee_position`.`id` INNER JOIN `status` ON "
                + "`employee`.`status_id` = `status`.`id` INNER JOIN `gender` ON"
                + "`employee`.`gender_id`=`gender`.`id` INNER JOIN `department` ON"
                + "`employee`.`department_id`=`department`.`id`");
            reset();

            //            }
        } else {

            JOptionPane.showMessageDialog(this, "Select a row", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //            String id = jTable1.getValueAt(selectedRow, 0).toString();
        String firstName = jTextField1.getText();
        String lastName = jTextField2.getText();
        String email = jTextField3.getText();
        String mobile = jTextField4.getText();
        String department = String.valueOf(jComboBox4.getSelectedIndex()) ;
        String employee_id = jTextField7.getText();
        //        String username = jTextField5.getText();
        //        String password = String.valueOf(jPasswordField1.getPassword());
        String position = jComboBox1.getSelectedItem().toString();
        String status = jComboBox2.getSelectedItem().toString();
        String gender = jComboBox3.getSelectedItem().toString();
        String image = jLabel8.getText();

        try {

            ResultSet rs = MySQL.execute("SELECT * FROM `employee` WHERE `email`='" + email + "' OR `mobile`='" + mobile + "'");

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Already registerd user.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                LocalDate date = LocalDate.now();

                MySQL.execute("INSERT INTO `employee`"
                    + "(`fname`,`lname`,`email`,`mobile`,`department_id`,`employee_position_id`,`status_id`,`gender_id`,`registerd_date`,`employee_id`)"
                    + "VALUES('" + firstName + "','" + lastName + "','" + email + "','" + mobile + "','" + department + "',"
                    + "'" + EmployeePositionMap.get(position) + "','" + EmployeeStatusMap.get(status) + "','" + EmployeeGenderMap.get(gender) + "','" + date + "','"+employee_id+"')");
                //

                loadEmployee("SELECT * FROM `employee` INNER JOIN `employee_position` ON "
                    + "`employee`.`employee_position_id` = `employee_position`.`id` INNER JOIN `status` ON "
                    + "`employee`.`status_id` = `status`.`id` INNER JOIN `gender` ON"
                    + "`employee`.`gender_id`=`gender`.`id` INNER JOIN `department` ON"
                    + "`employee`.`department_id`=`department`.`id`");

                reset();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:

        //        String text = jTextField4.getText();
        //        loadEmployee("SELECT * FROM `employee` INNER JOIN `employee_position` ON "
            //                + "`employee`.`employee_position_id` = `employee_position`.`id` INNER JOIN `status` ON "
            //                + "`employee`.`status_id` = `status`.`id` INNER JOIN `gender` ON"
            //                + "`employee`.`gender_id`=`gender`.`id` INNER JOIN `department` ON"
            //                + "`employee`.`department_id`=`department`.`id`"
            //                + "WHERE `mobile` LIKE '" + text + "%'");
        //        reset();
        //        jTextField4.setText(text);
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            int selectedRow = jTable1.getSelectedRow();

            if (selectedRow != -1) {
                String firstName = jTable1.getValueAt(selectedRow, 1).toString();
                jTextField1.setText(firstName);

                String lastName = jTable1.getValueAt(selectedRow, 2).toString();
                jTextField2.setText(lastName);

                String email = jTable1.getValueAt(selectedRow, 3).toString();
                jTextField3.setText(email);

                String mobile = jTable1.getValueAt(selectedRow, 4).toString();
                jTextField4.setText(mobile);

                String department = jTable1.getValueAt(selectedRow, 5).toString();
                jTextField7.setText(department);

                String type = jTable1.getValueAt(selectedRow, 6).toString();
                jComboBox1.setSelectedItem(type);

                String status = jTable1.getValueAt(selectedRow, 7).toString();
                jComboBox2.setSelectedItem(status);

                String gender = jTable1.getValueAt(selectedRow, 8).toString();
                jComboBox3.setSelectedItem(gender);

                String reg_name = jTable1.getValueAt(selectedRow, 9).toString();
            } else if (evt.getClickCount() == 2) {
                //            String email = String.valueOf(jTable1.getValueAt(selectedRow, 0));
                //                AddressView addressView = new AddressView();
                //                addressView.setVisible(true);
                //            AddressViewPanel address = new AddressViewPanel();
                //            address.MdiPare

            }
    }//GEN-LAST:event_jTable1MouseClicked
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField7;
    private org.netbeans.modules.form.InvalidComponent panelRound1;
    private org.netbeans.modules.form.InvalidComponent panelRound2;
    // End of variables declaration//GEN-END:variables
}
