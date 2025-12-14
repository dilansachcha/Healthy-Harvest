package com.compornent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class HoverPanelRound extends PanelRound {

    private boolean hovering = false;
    private boolean clicked = false;
    private Color clickColor = new Color(206, 223, 211); // Color to fill when clicked

    public HoverPanelRound() {
        super();

        // Add mouse listener to detect hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
                repaint(); // Repaint to update appearance
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
                repaint(); // Repaint to update appearance
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked = !clicked;
                repaint(); // Repaint to update appearance
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        
        if (clicked) {
            // Fill with click color if clicked
            grphcs.setColor(clickColor);
            grphcs.fillRect(0, 0, getWidth(), getHeight());
        } else if (hovering) {
            // Draw hover border
            Graphics2D g2 = (Graphics2D) grphcs.create();
            int shadowSize = 10; // Adjust the size of the shadow as needed
            int shadowOpacity = 100; // Adjust the opacity of the shadow (0-255)

            // Create a semi-transparent shadow color
            Color shadowColor = new Color(206, 223, 211,shadowOpacity);

            // Draw the shadow
            g2.setColor(shadowColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getRoundTopLeft(), getRoundTopLeft()); // Rounded rectangle for the hover border
            g2.dispose();
        }

//        if (hovering) {
//            Graphics2D g2 = (Graphics2D) grphcs.create();
//            int shadowSize = 2; // Adjust the size of the shadow as needed
//            int shadowOpacity = 50; // Adjust the opacity of the shadow (0-255)
//
//            // Create a semi-transparent shadow color
//            Color shadowColor = new Color(0, 0, 0, shadowOpacity);
//
//            // Draw the shadow
////            g2.setColor(shadowColor);
////            g2.fillRect(0, 0, getWidth(), shadowSize); // Top shadow
////            g2.fillRect(0, shadowSize, shadowSize, getHeight() - shadowSize); // Left shadow
////            g2.fillRect(getWidth() - shadowSize, shadowSize, shadowSize, getHeight() - shadowSize); // Right shadow
////            g2.fillRect(shadowSize, getHeight() - shadowSize, getWidth() - 2 * shadowSize, shadowSize); // Bottom shadow
////            g2.dispose();
//            g2.setColor(shadowColor);
//            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getRoundTopLeft(), getRoundTopLeft()); // Rounded rectangle for the hover border
//            g2.dispose();
//        }
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("HoverPanelRound Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 200);
//
//        HoverPanelRound panel = new HoverPanelRound();
//        panel.setBackground(Color.WHITE);
//        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Initial border color
//        frame.add(panel);
//
//        frame.setVisible(true);
//    }
}
