package hsr_java_gui_gradle;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

public class App {
    private static Component currentContent;
    private static JSplitPane splitPane;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setTitle("Honkai: Star Rail Characters");
            frame.setUndecorated(true);
            frame.setSize(1200, 800);
            frame.setLayout(new BorderLayout());

            // Top control bar (window controls + draggable)
            JPanel topWrapper = new JPanel(new BorderLayout());
            topWrapper.setBackground(Color.BLACK);
            WindowControlBar windowControlBar = new WindowControlBar(frame);
            topWrapper.add(windowControlBar, BorderLayout.NORTH);
            new Draggable(windowControlBar, frame);

            // Navbar below control bar
            NavbarPanel navbar = new NavbarPanel();
            topWrapper.add(navbar, BorderLayout.CENTER);

            frame.add(topWrapper, BorderLayout.NORTH);

            // Sidebar + Main Panel
            SidebarPanel sidebar = new SidebarPanel();
            currentContent = new HomePanel();

            // Split pane to separate sidebar and main content
            splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, currentContent);
            splitPane.setDividerLocation(180);
            splitPane.setOneTouchExpandable(true);
            splitPane.setUI(new BasicSplitPaneUI() {
                @Override
                public BasicSplitPaneDivider createDefaultDivider() {
                    return new BasicSplitPaneDivider(this) {
                        @Override
                        public void paint(Graphics g) {
                            g.setColor(new Color(0x2D2D2D));
                            g.fillRect(0, 0, getWidth(), getHeight());
                        }
                    };
                }
            });
            splitPane.setDividerSize(3);
            splitPane.setContinuousLayout(true);
            splitPane.setResizeWeight(0.0);
            splitPane.setBorder(null);
            splitPane.setFocusable(false);

            frame.add(splitPane, BorderLayout.CENTER);

            // Connect sidebar to panel switch logic
            sidebar.setNavigationListener(App::switchContentPanel);

            // Resizing overlay
            Resizable.install(frame);

            // Preload images for characters
            ImageCache.preload(CharacterGridPanel.characterImages, "./app/src/main/resources/images/");

            // Done
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    if ((frame.getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                        frame.setShape(null); // Remove rounded shape on maximize
                    } else {
                        frame.setShape(new RoundRectangle2D.Double(
                            0, 0, frame.getWidth(), frame.getHeight(), 30, 30));
                    }
                }
            });
            frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 30, 30));
            frame.repaint();
        });
    }

    public static void switchContentPanel(String name) {
        Component newPanel;

        switch (name) {
            // case "Guides" -> newPanel = new GuidesPanel();
            // case "Relics" -> newPanel = new RelicsPanel();
            // case "Light Cones" -> newPanel = new LightConesPanel();
            // case "Memory of Chaos" -> newPanel = new MemoryofChaosPanel();
            // case "Tier List" -> newPanel = new TierListPanel();
            case "Characters" -> newPanel = new CharacterGridPanel();
            case "Home" -> newPanel = new HomePanel();
            default -> {
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBackground(new Color(0x212121));

                // Large "404 - Page Not Found"
                JLabel error = new JLabel("404 - Page Not Found");
                error.setFont(new Font("SansSerif", Font.BOLD, 36));
                error.setForeground(new Color(0xD3D3D3));
                error.setHorizontalAlignment(SwingConstants.CENTER);

                // Sub message below
                JLabel subError = new JLabel("<html><div style='text-align: center;'>The page you were looking for could not be found. It might have been removed,<br>" + " renamed, or did not exist in the first place.</div></html>");
                subError.setFont(new Font("SansSerif", Font.PLAIN, 18));
                subError.setForeground(new Color(0xA9A9A9));
                subError.setHorizontalAlignment(SwingConstants.CENTER);

                JPanel textPanel = new JPanel();
                textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                textPanel.setOpaque(false);

                // Vertical glue to ensure the labels are centered
                textPanel.add(Box.createVerticalGlue());
                error.setAlignmentX(Component.CENTER_ALIGNMENT);
                subError.setAlignmentX(Component.CENTER_ALIGNMENT);
                textPanel.add(error);
                textPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                textPanel.add(subError);
                textPanel.add(Box.createVerticalGlue());

                panel.add(textPanel, BorderLayout.CENTER);
                newPanel = panel;
            }
        }

        int dividerLoc = splitPane.getDividerLocation();
        splitPane.setRightComponent(newPanel);
        splitPane.setDividerLocation(dividerLoc);
        splitPane.revalidate();
        splitPane.repaint();

        currentContent = newPanel;
    }

    public static void switchContentPanel(Component panel) {
        int dividerLoc = splitPane.getDividerLocation();
        splitPane.setRightComponent(panel);
        splitPane.setDividerLocation(dividerLoc);
        splitPane.revalidate();
        splitPane.repaint();
        currentContent = panel;
    }
}
