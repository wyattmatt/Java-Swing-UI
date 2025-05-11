package hsr_java_gui_gradle;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class ArcherPanel extends JPanel {

    public ArcherPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(27, 27, 27));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Top Panel with border
        ImageIcon backgroundImg = new ImageIcon("./app/src/main/resources/images/HSRBackground.png");
        JPanel topPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Rounded shape clipping
                RoundRectangle2D.Float round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
                g2d.setClip(round);

                // Draw background image with 65% opacity
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.65f));
                g2d.drawImage(backgroundImg.getImage(), 0, 0, getWidth(), getHeight(), this);

                // Semi-transparent black overlay
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                g2d.dispose();
                super.paintComponent(g); // Call after to keep layout children
            }
        };
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));

        // Title Label
        JLabel titleLabel = new JLabel("<html><div style='font-size:12px; color:#DFDFDF;'>HONKAI: STAR RAIL<br></div><div style='font-size:36px; color:#B85EFF;'>Archer</div></html>");
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 20, 10, 10));

        // Right side: Layered image + parallelogram
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(250, 150));

        // Parallelogram background panel
        ParallelogramPanel parallelogram = new ParallelogramPanel(new Color(130, 70, 190));
        parallelogram.setBounds(30, 27, 184, 110); // Parallelogram position

        // Image label
        ImageIcon acheronImage = new ImageIcon("./app/src/main/resources/images/archer.png");
        JLabel imageLabel = new JLabel(new ImageIcon(acheronImage.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
        imageLabel.setBounds(60, 17, 120, 120); // Image position

        layeredPane.add(parallelogram, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(imageLabel, JLayeredPane.PALETTE_LAYER);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(layeredPane, BorderLayout.EAST);

        // Introduction Section
        JPanel introPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                RoundRectangle2D.Float round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
                g2.setColor(getBackground());
                g2.fill(round);

                g2.dispose();
                super.paintComponent(g); // Paint children after
            }
        };
        introPanel.setOpaque(false);
        introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));
        introPanel.setBackground(new Color(40, 40, 40));
        introPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel introTitle = new JLabel("INTRODUCTION");
        introTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        introTitle.setForeground(Color.WHITE);
        introTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextPane introText = new JTextPane();
        introText.setContentType("text/html");
        introText.setText("""
            <html><body style='color:white; font-family:sans-serif; font-size:14px;'>
            <b style='color:#B85EFF;'>Aglaea</b> is a <b style='color:#FFD700;'>5★</b> character from the <b style='color:#B85EFF;'>Quantum</b> element who follows the <b>Path of Hunt</b>.<br><br>
            In that holy city kissed by the dawn, the weaver caresses the golden thread and connects destiny. Aglaea the Goldweaver, 
            the Chrysos Heir bearing the Coreflame of "Romance"... You shall summon the heroes of this world, and lead them to once 
            again embark on an endless journey where they will smite down the gods, return the divine fire, and grant rebirth to the dying land of Amphoreus.
            </body></html>
        """);
        introText.setEditable(false);
        introText.setOpaque(false);
        introText.setBorder(null);
        introText.setAlignmentX(Component.LEFT_ALIGNMENT);

        introPanel.add(introTitle);
        introPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        introPanel.add(introText);

        // Assemble main layout
        add(topPanel, BorderLayout.NORTH);

        // Wrapper with MatteBorder as divider
        JPanel introWrapper = new JPanel(new BorderLayout());
        introWrapper.setOpaque(false);
        introWrapper.setBorder(BorderFactory.createMatteBorder(12, 0, 0, 0, new Color(27, 27, 27)));
        introWrapper.add(introPanel, BorderLayout.CENTER);

        add(introWrapper, BorderLayout.CENTER);
    }

    // Custom panel for drawing a parallelogram
    static class ParallelogramPanel extends JPanel {
        private final Color color;

        public ParallelogramPanel(Color color) {
            this.color = color;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int offset = 30;

            Polygon p = new Polygon();
            p.addPoint(offset, 0);
            p.addPoint(width, 0);
            p.addPoint(width - offset, height);
            p.addPoint(0, height);

            g2.setColor(color);
            g2.fillPolygon(p);
            g2.dispose();
        }
    }
}
