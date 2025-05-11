package hsr_java_gui_gradle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomePanel extends JPanel {
    private BufferedImage backgroundImage;

    public HomePanel() {
        try {
            backgroundImage = ImageIO.read(new File("./app/src/main/resources/images/HomePanelBackground.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        setOpaque(false);

        JLabel title = new JLabel("Welcome to Honkai: Star Rail wiki and database");
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitle = new JLabel("<html><div style='text-align: center;'>This is a wiki and database for Honkai: Star Rail, a HoYoverse turn-based space fantasy RPG. " + "Check out our guides, character reviews, tier list and more!</div></html>");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subtitle.setForeground(Color.LIGHT_GRAY);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        textPanel.add(title);
        textPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        textPanel.add(subtitle);

        add(textPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Draw the background image
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            // Add a dark translucent overlay
            g2d.setColor(new Color(0, 0, 0, 192)); // RGBA - 128 = 50% alp
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.dispose();
        }
    }
}
