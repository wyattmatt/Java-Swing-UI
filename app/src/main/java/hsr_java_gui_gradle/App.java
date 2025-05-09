package hsr_java_gui_gradle;

import javax.swing.*;
import java.awt.*;

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

            // Navbar
            NavbarPanel navbar = new NavbarPanel();
            frame.add(navbar, BorderLayout.NORTH);
            new DraggableHelper(navbar, frame);

            // Sidebar
            SidebarPanel sidebar = new SidebarPanel();

            // Initial right panel (Home)
            currentContent = new HomePanel();

            // JSplitPane
            splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, currentContent);
            splitPane.setDividerLocation(180);
            splitPane.setOneTouchExpandable(true);
            splitPane.setDividerSize(1);
            splitPane.setResizeWeight(0.0);
            splitPane.setBorder(null);
            splitPane.setFocusable(false);

            frame.add(splitPane, BorderLayout.CENTER);

            ResizableOverlayHelper.install(frame);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Allow sidebar to trigger view change
            sidebar.setNavigationListener(App::switchContentPanel);
        });
    }

    public static void switchContentPanel(String name) {
        Component newPanel;
        int dividerLoc = splitPane.getDividerLocation();

        switch (name) {
            case "Characters" -> newPanel = new CharacterGridPanel(); // returns JScrollPane
            case "Home" -> newPanel = new HomePanel(); // JPanel
            default -> {
                JLabel errorLabel = new JLabel(name + " not found.");
                errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
                errorLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(errorLabel, BorderLayout.CENTER);
                newPanel = panel;
            }
        }

        splitPane.setDividerLocation(dividerLoc);
        splitPane.setRightComponent(newPanel);
        currentContent = newPanel;
    }
}
