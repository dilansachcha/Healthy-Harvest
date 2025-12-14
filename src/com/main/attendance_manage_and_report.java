/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.main;

import net.proteanit.sql.DbUtils;
import com.compornent.PanelRound;
import com.formdev.flatlaf.FlatClientProperties;
import com.model.MySQL;
import com.sun.jdi.connect.spi.Connection;
import com.toedter.calendar.demo.TestDateEvaluator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.math3.analysis.function.Exp;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import java.sql.PreparedStatement;
//import com.toedter.calendar.JDateChooser;
//import java.sql.DriverManager;

//import java.sql.Connection;
/**
 *
 * @author Manujaya
 */
public class attendance_manage_and_report extends javax.swing.JPanel {

    HashMap<String, Integer> attendTypeMap = new HashMap<>();

    /**
     * Creates new form attendance_manage_and_report
     */
    public attendance_manage_and_report() {
        initComponents();
        applyPanelStyle();
        applyTableStyle();

        loadAttenType();
        loadManageAttendance("SELECT * FROM `employee_attendance` INNER JOIN `atten_status_type` ON "
                + "`employee_attendance`.`atten_status_type_id`=`atten_status_type`.`id` INNER JOIN `employee` ON "
                + "`employee_attendance`.`employee_id`=`employee`.`id` ORDER BY `employee_attendance`.`atten_id` DESC");
        
        loadReportAttendance("SELECT * FROM `employee_attendance` INNER JOIN `atten_status_type` ON "
                + "`employee_attendance`.`atten_status_type_id`=`atten_status_type`.`id` INNER JOIN `employee` ON "
                + "`employee_attendance`.`employee_id`=`employee`.`id` ORDER BY `employee_attendance`.`atten_id` DESC");

        showDate();
        showTime();
        disableTextFeild();
        loadReportAttenType();
    }

    private void applyPanelStyle() {
        if (panelRound3 != null) {
            panelRound3.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        }
        if (panelRound6 != null) {
            panelRound6.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        }
        if (panelRound7 != null) {
            panelRound7.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        }
        if (panelRound9 != null) {
            panelRound9.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        }
        if (panelRound10 != null) {
            panelRound10.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        }
    }

    private void applyTableStyle() {
        //Change Scroll Style
        if (jTable1 != null) {
            JScrollPane scroll = (JScrollPane) jTable1.getParent().getParent();
            scroll.setBorder(BorderFactory.createEmptyBorder());
            scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                    + "background:$Table.background;"
                    + "track:$Table.background;"
                    + "trackArc:999");

            jTable1.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
            jTable1.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        }
        if (jReportTable != null) {
            JScrollPane scroll = (JScrollPane) jReportTable.getParent().getParent();
            scroll.setBorder(BorderFactory.createEmptyBorder());
            scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                    + "background:$Table.background;"
                    + "track:$Table.background;"
                    + "trackArc:999");

            jReportTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
            jReportTable.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        }

    }
    
    private void disableTextFeild(){
        
        if (maName != null){
                maName.setEditable(false);
        }
        if (maATime != null){
                maATime.setEditable(false);
        }
        if (maLTime != null){
                maLTime.setEditable(false);
        }
    }
    

    private void showDate() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        maDateTF.setText(sf.format(d));
        maDateTF.setEditable(false);

    }

    Timer t;
    SimpleDateFormat st;

    private void showTime() {

        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

                Date dt = new Date();
                st = new SimpleDateFormat("HH:mm:ss");

                String tt = st.format(dt);
                maATime.setText(tt);

            }
        });

        t.start();

    }

    private void stopTimer() {
        if (t != null && t.isRunning()) {
            t.stop();
        }
    }

    private void loadAttenType() {
        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `atten_status_type`");

            Vector<String> vector = new Vector<>();
//            vector.add("Select");

            while (resultset.next()) {
                vector.add(resultset.getString("status_type"));
                attendTypeMap.put(resultset.getString("status_type"), resultset.getInt("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox2.getModel();
            comboBoxModel.removeAllElements();

            comboBoxModel.addAll(vector);
            jComboBox2.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadReportAttenType() {
        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `atten_status_type`");

            Vector<String> vector = new Vector<>();
//            vector.add("Select");

            while (resultset.next()) {
                vector.add(resultset.getString("status_type"));
                attendTypeMap.put(resultset.getString("status_type"), resultset.getInt("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox1.getModel();
            comboBoxModel.removeAllElements();

            comboBoxModel.addAll(vector);
            jComboBox1.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private HashMap<String, Integer> attenMap = new HashMap<>();
    
    private void loadManageAttendance(String query) {
        try {
            ResultSet resultset = MySQL.execute(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultset.next()) {
//                int attendid = resultset.getInt("employee_attendance.atten_id");
                
//                attenMap.put(resultset.getString("employee_attendace.employee_id"),resultset.getInt("employee_attendance.atten_id"));
                
                
                String firstName = resultset.getString("fname");
                String lastName = resultset.getString("lname");

                String fullName = firstName + " " + lastName;

                Vector<String> vector = new Vector<>();

                vector.add(resultset.getString("atten_date"));
                vector.add(resultset.getString("employee_id"));
                vector.add(fullName);
                vector.add(resultset.getString("arrival_time"));
                vector.add(resultset.getString("leaving_time"));
                vector.add(resultset.getString("status_type"));
//                vector.add(resultset.getString("atten_id"));

                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetMA() {
        maEmpID.setText("");
        maName.setText("");
//        maATime.setText("");
        maLTime.setText("");
        jComboBox2.setSelectedIndex(0);
        showTime();

    }

    private void loadReportAttendance(String query) {
        try {
            ResultSet resultset = MySQL.execute(query);

            DefaultTableModel model = (DefaultTableModel) jReportTable.getModel();
            model.setRowCount(0);

            while (resultset.next()) {
                String firstName = resultset.getString("fname");
                String lastName = resultset.getString("lname");

                String fullName = firstName + " " + lastName;

                Vector<String> vector = new Vector<>();
                vector.add(resultset.getString("atten_date"));
                vector.add(resultset.getString("employee_id"));
                vector.add(fullName);
                vector.add(resultset.getString("status_type"));

                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void showData(){
        
//        Connection con = getConnection();
//        ResultSet rs = MySQL.execute("SELECT * FROM `employee_attendance` INNER JOIN `atten_status_type` ON "
//                + "`employee_attendance`.`atten_status_type_id`=`atten_status_type`.`id` INNER JOIN `employee` ON "
//                + "`employee_attendance`.`employee_id`=`employee`.`id` ORDER BY `employee_attendance`.`atten_id` DESC");
//        PreparedStatement st;
//        
//        
//        try {
//            
//            if(d1.equals("") || d2.equals(""))
//            {           
//                
//            }
//            
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        
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
        panelRound2 = new org.netbeans.modules.form.InvalidComponent();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        panelRound4 = new org.netbeans.modules.form.InvalidComponent();
        panelRound6 = new org.netbeans.modules.form.InvalidComponent();
        panelRound8 = new org.netbeans.modules.form.InvalidComponent();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panelRound3 = new org.netbeans.modules.form.InvalidComponent();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        maEmpID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        maName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        maATime = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        maDateTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        maLTime = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        panelRound7 = new org.netbeans.modules.form.InvalidComponent();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelRound5 = new org.netbeans.modules.form.InvalidComponent();
        panelRound9 = new org.netbeans.modules.form.InvalidComponent();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        fromDate = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jExportButton = new javax.swing.JButton();
        toDate = new com.toedter.calendar.JDateChooser();
        panelRound10 = new org.netbeans.modules.form.InvalidComponent();
        jScrollPane2 = new javax.swing.JScrollPane();
        jReportTable = new javax.swing.JTable();

        setBackground(new Color(0, 0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Attendance");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        jTabbedPane2.setBackground(new Color (0, 0, 0, 0));
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Total Employee Count");

        jLabel8.setFont(new java.awt.Font("MS PGothic", 1, 36)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("0");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("No of Emp. Attendance");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("0");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/punsisi/image/staff (1).png"))); // NOI18N

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound8Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound8Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound8Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addContainerGap(7, Short.MAX_VALUE))
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel2.setText("Date");

        jLabel3.setText("Employee ID");

        maEmpID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                maEmpIDKeyReleased(evt);
            }
        });

        jLabel4.setText("Name");

        jLabel5.setText("Arrival Time");

        jLabel6.setText("Status");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Present", "Absent" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(38, 192, 61));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("ADD");
        jButton2.setPreferredSize(new java.awt.Dimension(72, 35));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel16.setText("Leave Time");

        jButton7.setBackground(new java.awt.Color(0, 0, 0));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("RESET");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addComponent(maEmpID)
                    .addComponent(maName)
                    .addComponent(maATime)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(maLTime)
                    .addComponent(maDateTF)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel16))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maName, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maATime, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maLTime, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel2);

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Employee ID", "Name", "Arrival Time", "Leave Time", "Status", "atten_id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 976, Short.MAX_VALUE)
            .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 795, Short.MAX_VALUE)
            .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(987, 987, 987))
            .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                    .addGap(347, 347, 347)
                    .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane2.addTab("Manage Attendance", panelRound4);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel12.setText("From");

        jLabel13.setText("To");

        jLabel15.setText("Status");

        jButton6.setBackground(new java.awt.Color(0, 51, 204));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("SEARCH");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jExportButton.setText("Export");
        jExportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(fromDate, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jExportButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(toDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 55, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(jExportButton)
                .addGap(100, 100, 100))
        );

        jScrollPane4.setViewportView(jPanel3);

        javax.swing.GroupLayout panelRound9Layout = new javax.swing.GroupLayout(panelRound9);
        panelRound9.setLayout(panelRound9Layout);
        panelRound9Layout.setHorizontalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound9Layout.setVerticalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                .addContainerGap())
        );

        jReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Employee ID", "Name", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jReportTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jReportTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jReportTable);

        javax.swing.GroupLayout panelRound10Layout = new javax.swing.GroupLayout(panelRound10);
        panelRound10.setLayout(panelRound10Layout);
        panelRound10Layout.setHorizontalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        panelRound10Layout.setVerticalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound9, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Report", panelRound5);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane2)
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:

        resetMA();

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        ////        int id = attenMap.get("employee_attendace.employee_id");
        //
        //
        //
        ////        System.out.println(id);
        //
        //
        //        int selectedRow = jTable1.getSelectedRow();
        //
        //        if (selectedRow != -1) {
            //
            ////            String id =
            //            String date = maDateTF.getText();
            //            String employeeID = maEmpID.getText();
            //            String arrivalTime = maATime.getText();
            //            String leaveTime = maLTime.getText();
            //            String status = jComboBox2.getSelectedItem().toString();
            //            String attenid = jTextField1.getText();
            //
            //                MySQL.execute("UPDATE `employee_attendance` SET `atten_date`='" + date + "',"
                //                        + "`employee_id`='" + employeeID + "',"
                //                        + "`arrival_time`='" + arrivalTime + "',"
                //                        + "`leaving_time`='" + leaveTime + "',"
                //                        + "`atten_status_type_id`='" + attendTypeMap.get(status) + "', "
                //                        + "WHERE `atten_id`='" + attenid + "'");
            //
            //                loadManageAttendance("SELECT * FROM `employee_attendance` INNER JOIN `atten_status_type` ON "
                //                        + "`employee_attendance`.`atten_status_type_id`=`atten_status_type`.`id` INNER JOIN `employee` ON "
                //                        + "`employee_attendance`.`employee_id`=`employee`.`id`");
            //
            //                resetMA();
            //
            //
            //
            //        } else {
            //
            //            JOptionPane.showMessageDialog(this, "Select a row", "Warning", JOptionPane.WARNING_MESSAGE);
            //        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ////        String emp_id = maEmpID.getText();
        ////
        ////        try {
            ////            MySQL.execute("INSERT INTO `employee_attendance` (`atten_date`, `arrival_time`, `atten_status_type_id`, `employee_id`) "
                ////                    + "VALUES (CURDATE() ,CURTIME(), '1', '" + emp_id + "')");
            ////        } catch (Exception e) {
            ////            e.printStackTrace();
            ////        }

        //        MySQL.execute("INSERT INTO employee"
            //                            + "(atten_date, arrival_time, leaving_time, atten_status_type_id, employee_id)"
            //                            + "VALUES('"  "')");
        //            String date = maDateTF.getText();
        String employeeID = maEmpID.getText();
        String name = maName.getText();
        String arrivalTime = maATime.getText();
        String leaveTime = maLTime.getText();
        String status = jComboBox2.getSelectedItem().toString();

        try {

            ResultSet rs = MySQL.execute("SELECT * FROM `employee_attendance` WHERE `atten_date`= CURDATE() AND `employee_id`='" + employeeID + "'");

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Already marked.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                MySQL.execute("INSERT INTO `employee_attendance`"
                    + "(`atten_date`,`arrival_time`,`leaving_time`,`atten_status_type_id`,`employee_id`)"
                    + "VALUES(CURDATE(),'" + arrivalTime + "','" + leaveTime + "','" + attendTypeMap.get(status) + "', '" + employeeID + "')");

                loadManageAttendance("SELECT * FROM `employee_attendance` INNER JOIN `atten_status_type` ON "
                    + "`employee_attendance`.`atten_status_type_id`=`atten_status_type`.`id` INNER JOIN `employee` ON "
                    + "`employee_attendance`.`employee_id`=`employee`.`id`");

                resetMA();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

//    private JDateChooser fromDate;
//    private JDateChooser toDate;
//    private JTable jReportTable;
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:

        //        Connection con = MySQL.getConnection();
        //
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //
        //        String jdStr = sdf.format(fromDate.getDate());
        //        String jdlStr = sdf.format(toDate.getDate());
        //
        //        try{
            //            PreparedStatement pstmt;
            //            String sql = ("SELECT * FROM sales WHERE Date >= '" + jdStr + "' AND Date <= '" + jdlStr + "' ");
            //        pstmt = con.prepareStatement (sql);
            //
            //            ResultSet rs = pstmt.executeQuery();
            //            jReportTable.setModel(DbUtils.resultSetToTableModel(rs));
            //        }catch(SQLException ex){
            //            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //        }

        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //        String jdStr = sdf.format(fromDate.getDate());
        //        String jdlStr = sdf.format(toDate.getDate());
        //
        //        String sql = "SELECT * FROM  WHERE Date >= ? AND Date <= ?";

        //        Connection con = null;
        //        PreparedStatement pstmt = null;
        //        ResultSet rs = null;
        //
        //        try {
            //
            //
            ////            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthy_harvest", "root", "Duminda@0121");
            //            pstmt = MySQL;
            //
            //            pstmt.setString(1, jdStr);
            //            pstmt.setString(2, jdlStr);
            //
            //            rs = pstmt.executeQuery();
            //            jReportTable.setModel(DbUtils.resultSetToTableModel(rs));
            //        } catch (Exception e) {
            //            e.printStackTrace();
            //        } finally {
            //            try {
                //                if (rs != null) rs.close();
                //                if (pstmt != null) pstmt.close();
                //                if (con != null) con.close();
                //            } catch (Exception e) {
                //                e.printStackTrace();
                //            }
            //        }

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void maEmpIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maEmpIDKeyReleased
        // TODO add your handling code here:
        //        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //            String employeeid = maEmpID.getText();
            //            loadManageAttendance("SELECT * FROM `employee_attendance` INNER JOIN `atten_status_type` ON "
                //                    + "`employee_attendance`.`atten_status_type_id`=`atten_status_type`.`id` INNER JOIN `employee` ON "
                //                    + "`employee_attendance`.`employee_id`=`employee`.`id` WHERE `employee_id` LIKE '" + employeeid + "'");
            //            resetMA();
            //            maEmpID.setText(employeeid);
            //        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String employeeid = maEmpID.getText();

            try {
                ResultSet resultSet = MySQL.execute("SELECT * FROM `employee_attendance` INNER JOIN `atten_status_type` ON "
                    + "`employee_attendance`.`atten_status_type_id`=`atten_status_type`.`id` INNER JOIN `employee` ON "
                    + "`employee_attendance`.`employee_id`=`employee`.`id` WHERE `employee_id` LIKE '" + employeeid + "'");

                if (resultSet.next()) {
                    String firstName = resultSet.getString("fname");
                    String lastName = resultSet.getString("lname");

                    String fullName = firstName + " " + lastName;

                    maName.setText(fullName);

                } else {

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_maEmpIDKeyReleased

    private void jReportTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jReportTableMouseClicked
        if (evt.getClickCount() == 2) {
            int selectedRow = jReportTable.getSelectedRow();

            if (selectedRow != -1) {
                String date = String.valueOf(jReportTable.getValueAt(selectedRow, 0));
                maDateTF.setText(date);
                String employeeID = String.valueOf(jReportTable.getValueAt(selectedRow, 1));
                maEmpID.setText(employeeID);
                String name = String.valueOf(jReportTable.getValueAt(selectedRow, 2));
                maName.setText(name);
                String atime = String.valueOf(jReportTable.getValueAt(selectedRow, 3));
                maATime.setText(atime);
                String ltime = String.valueOf(jReportTable.getValueAt(selectedRow, 4));
                maLTime.setText(ltime);
                String type = String.valueOf(jReportTable.getValueAt(selectedRow, 5));
                jComboBox2.setSelectedItem(type);

            }
        }
    }//GEN-LAST:event_jReportTableMouseClicked

    private void jExportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExportButtonActionPerformed
        // TODO add your handling code here:

        try{
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if(saveFile != null){
                saveFile = new File(saveFile.toString()+".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("customer");

                Row rowCol = sheet.createRow(0);
                for(int i=0;i<jReportTable.getColumnCount();i++){
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(jReportTable.getColumnName(i));
                }

                for(int j=0;j<jReportTable.getRowCount();j++){
                    Row row = sheet.createRow(j+1);
                    for(int k=0;k<jReportTable.getColumnCount();k++){
                        Cell cell = row.createCell(k);
                        if(jReportTable.getValueAt(j, k)!=null){
                            cell.setCellValue(jReportTable.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            }else{
                JOptionPane.showMessageDialog(null,"Error");
            }
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException io){
            System.out.println(io);
        }

    }//GEN-LAST:event_jExportButtonActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        try{

            DefaultTableModel model = (DefaultTableModel) jReportTable.getModel();
            model.setRowCount(0);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String jdStr = sdf.format(fromDate.getDate());
            String jdlStr = sdf.format(toDate.getDate());

            System.out.println(jdStr);
            System.out.println(jdlStr);

            ResultSet resultSet = MySQL.execute("SELECT *  FROM `employee_attendance` "
                + "INNER JOIN `employee` ON `employee_attendance`.`employee_id` = `employee`.`id` "
                + "INNER JOIN `atten_status_type` ON `employee_attendance`.`atten_status_type_id` = `atten_status_type`.`id` "
                + "WHERE `atten_date` >= '" + jdStr + "' AND `atten_date` <= '" + jdlStr + "' ORDER BY `employee_attendance`.`atten_id` DESC");

            while (resultSet.next()) {
                String firstName = resultSet.getString("fname");
                String lastName = resultSet.getString("lname");
                String fullName = firstName + " " + lastName;

                String date =  resultSet.getString("atten_date");
                String employeeID =  resultSet.getString("employee_id");
                String name =  fullName;
                String status =  resultSet.getString("status_type");

                Vector vector = new Vector();   //Row
                vector.add(date);
                vector.add(employeeID);
                vector.add(name);
                vector.add(status);

                model.addRow(vector);

            }

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    model.fireTableDataChanged();
                    jReportTable.revalidate();
                    jReportTable.repaint();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {
            int selectedRow = jTable1.getSelectedRow();

            if (selectedRow != -1) {
                String date = String.valueOf(jTable1.getValueAt(selectedRow, 0).toString());
                maDateTF.setText(date);
                String employeeID = String.valueOf(jTable1.getValueAt(selectedRow, 1));
                maEmpID.setText(employeeID);
                String name = String.valueOf(jTable1.getValueAt(selectedRow, 2));
                maName.setText(name);
                stopTimer();
                String atime = String.valueOf(jTable1.getValueAt(selectedRow, 3));
                maATime.setText(atime);
                String ltime = String.valueOf(jTable1.getValueAt(selectedRow, 4));
                maLTime.setText(ltime);
                String type = String.valueOf(jTable1.getValueAt(selectedRow, 5));
                jComboBox2.setSelectedItem(type);
                //                String attenid = String.valueOf(jTable2.getValueAt(selectedRow, 6));
                //                jTextField1.setText(attenid);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    
    public void openFile(String file){
        try{
            File path = new File(file);
            Desktop.getDesktop().open(path);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JButton jExportButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTable jReportTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField maATime;
    private javax.swing.JTextField maDateTF;
    private javax.swing.JTextField maEmpID;
    private javax.swing.JTextField maLTime;
    private javax.swing.JTextField maName;
    private org.netbeans.modules.form.InvalidComponent panelRound1;
    private org.netbeans.modules.form.InvalidComponent panelRound10;
    private org.netbeans.modules.form.InvalidComponent panelRound2;
    private org.netbeans.modules.form.InvalidComponent panelRound3;
    private org.netbeans.modules.form.InvalidComponent panelRound4;
    private org.netbeans.modules.form.InvalidComponent panelRound5;
    private org.netbeans.modules.form.InvalidComponent panelRound6;
    private org.netbeans.modules.form.InvalidComponent panelRound7;
    private org.netbeans.modules.form.InvalidComponent panelRound8;
    private org.netbeans.modules.form.InvalidComponent panelRound9;
    private com.toedter.calendar.JDateChooser toDate;
    // End of variables declaration//GEN-END:variables
}
