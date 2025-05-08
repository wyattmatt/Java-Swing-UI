package hsr_java_gui_gradle;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setTitle("Honkai: Star Rail Characters");
            frame.setUndecorated(true);
            frame.setSize(1200, 800);
            frame.setLayout(new BorderLayout());

            frame.add(new NavbarPanel(), BorderLayout.NORTH);
            SidebarPanel sidebar = new SidebarPanel();
            CharacterGridPanel characterGrid = new CharacterGridPanel();

            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, characterGrid);
            splitPane.setDividerLocation(180);
            splitPane.setOneTouchExpandable(true);
            splitPane.setDividerSize(6);
            splitPane.setResizeWeight(0.0);
            splitPane.setBorder(null);

            frame.add(splitPane, BorderLayout.CENTER);

            ResizableFrameHelper.makeResizable(frame);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
