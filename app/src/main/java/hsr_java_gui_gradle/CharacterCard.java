package hsr_java_gui_gradle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CharacterCard extends JPanel {
    public CharacterCard(String name, Runnable onClick) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 200));
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icon = ImageCache.get(name);
        if (icon != null) {
            imageLabel.setIcon(icon);
        } else {
            imageLabel.setText("Image not found");
            imageLabel.setForeground(Color.BLACK);
        }

        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        add(imageLabel, BorderLayout.CENTER);
        add(nameLabel, BorderLayout.SOUTH);

        // Clickable behavior
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (onClick != null) onClick.run();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int arc = 20;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(223, 223, 223));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        g2.dispose();
        super.paintComponent(g);
    }
}
