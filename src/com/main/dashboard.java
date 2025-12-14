package com.main;

import com.compornent.PanelRound;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import punsisi.drawer.MyDrawerBuilder;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;

public class dashboard extends javax.swing.JFrame {

    public dashboard() {
        GlassPanePopup.install(this);
        MyDrawerBuilder myDrawerBuilder = new MyDrawerBuilder(this);
        Drawer.getInstance().setDrawerBuilder(myDrawerBuilder);
        myDrawerBuilder.getDrawerWidth();

        initComponents();
//        applyPanelStyle(panelRound1);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

//    private void applyPanelStyle(PanelRound panelRound1) {
//        panelRound1.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        side_medu = new javax.swing.JPanel();
        panelRound1 = new com.compornent.PanelRound();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setUndecorated(true);

        side_medu.setPreferredSize(new java.awt.Dimension(63, 393));

        panelRound1.setBackground(new java.awt.Color(30, 30, 30));
        panelRound1.setMinimumSize(new java.awt.Dimension(130, 100));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        jButton1.setBackground(new java.awt.Color(30, 30, 30));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/punsisi/image/more.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(337, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout side_meduLayout = new javax.swing.GroupLayout(side_medu);
        side_medu.setLayout(side_meduLayout);
        side_meduLayout.setHorizontalGroup(
            side_meduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(side_meduLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        side_meduLayout.setVerticalGroup(
            side_meduLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(side_meduLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(side_medu, java.awt.BorderLayout.LINE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Drawer.getInstance().showDrawer();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void addSideMenu() {
        // Add the side menu to the BorderLayout.WEST position
        getContentPane().add(side_medu, BorderLayout.WEST);
        revalidate();
        repaint();
    }

    public void loadPanel(int index, int subIndex) {
        Component currentCenterComponent = ((BorderLayout) getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER);
        // Hide all panels first
        getContentPane().removeAll();
        addSideMenu();
        switch (index) {

            case 0: //dashboard

                break;

            case 1: // chat

//                System.out.println("1");
//                Payroll panel = new Payroll();
//                this.add(panel, BorderLayout.CENTER);
//                SwingUtilities.updateComponentTreeUI(this);
                audit audit = new audit();
                this.add(audit, BorderLayout.CENTER);
                SwingUtilities.updateComponentTreeUI(this);

                break;

            case 2: // Calender

//                System.out.println("2");
//                Panel2 panel2 = new Panel2();
//                audit audit = new audit();
//                this.add(audit, BorderLayout.CENTER);
//                SwingUtilities.updateComponentTreeUI(this);
                production_and_stock_management_stock product = new production_and_stock_management_stock();
                this.add(product, BorderLayout.CENTER);
                SwingUtilities.updateComponentTreeUI(this);

                break;

            case 3:// hr Deparment
                System.out.println("3");

                switch (subIndex) {

                    case 1://Employee Managment 
                        employee_management employee = new employee_management();
                this.add(employee, BorderLayout.CENTER);
                SwingUtilities.updateComponentTreeUI(this);
                        System.out.println("3 1");

                        break;

                    case 2://Employee Training
                        System.out.println("3 2");

                        break;

                    case 3://employee Attendance
                        System.out.println("3 3");

                            attendance_manage_and_report att_manage =  new attendance_manage_and_report();
                            this.add(att_manage, BorderLayout.CENTER);
                            SwingUtilities.updateComponentTreeUI(this);
                        
                        break;
                    case 4://employee Attendance mark
                        System.out.println("3 4");
                        
                            attendance_mark att_mark =  new attendance_mark();
                            this.add(att_mark, BorderLayout.CENTER);
                            SwingUtilities.updateComponentTreeUI(this);                            

                        break;
                    case 5://employee Attendance leave
                        System.out.println("3 5");

                            leave_mark leave_mark = new leave_mark();
                            this.add(leave_mark, BorderLayout.CENTER);
                            SwingUtilities.updateComponentTreeUI(this);
                        
                        break;

                    case 6://payroll
                        System.out.println("3 6");

                        Payroll panel11 = new Payroll();
                        this.add(panel11, BorderLayout.CENTER);
                        SwingUtilities.updateComponentTreeUI(this);

                        break;

                }

                break;

            case 6:// Production Deparment
                System.out.println("6");

                switch (subIndex) {

                    case 1://Employee Managment 
                        System.out.println("6 1");

                        break;

                    case 2://Employee Training
                        System.out.println("6 2");

                        break;

                    case 3://Attendance
                        System.out.println("6 3");

                        break;

                    case 4://Leave Management
                        System.out.println("6 4");

                        break;

                }

                break;

            case 9: // Theme

                switch (subIndex) {

                    case 1:
                        System.out.println("9 1");

                        if (FlatLaf.isLafDark()) {
                            EventQueue.invokeLater(() -> {
                                FlatAnimatedLafChange.showSnapshot();
                                FlatMacLightLaf.setup();
                                FlatLaf.updateUI();
                                FlatAnimatedLafChange.hideSnapshotWithAnimation();
                            });

                            panelRound1.setBackground(new Color(30, 30, 30));
                            jButton1.setBackground(new Color(30, 30, 30));

                        }

                        break;

                    case 2:
                        System.out.println("9 2");

                        if (!FlatLaf.isLafDark()) {
                            EventQueue.invokeLater(() -> {
                                FlatAnimatedLafChange.showSnapshot();
                                FlatMacDarkLaf.setup();
                                FlatLaf.updateUI();
                                FlatAnimatedLafChange.hideSnapshotWithAnimation();
                            });

                            panelRound1.setBackground(new Color(246, 246, 246));
                            jButton1.setBackground(new Color(246, 246, 246));

                        }

                        break;

                }

                if (currentCenterComponent != null) {
                    getContentPane().add(currentCenterComponent, BorderLayout.CENTER);
                }
                SwingUtilities.updateComponentTreeUI(this);

                break;
            case 10: // Chat

                if (currentCenterComponent != null) {
                    getContentPane().add(currentCenterComponent, BorderLayout.CENTER);
                }
                SwingUtilities.updateComponentTreeUI(this);

                Icon icon = new ImageIcon(getClass().getResource("/punsisi/image/question-mark.png"));
                int option = JOptionPane.showConfirmDialog(this, "Do You Want To Exit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);

                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);

                }

                break;
            default:

                System.out.println(index);
                break;
        }
    }

    public static void main(String args[]) {

        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("com.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 14));

        FlatMacLightLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private com.compornent.PanelRound panelRound1;
    private javax.swing.JPanel side_medu;
    // End of variables declaration//GEN-END:variables

}
