package hsr_java_gui_gradle;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ToolTipManager;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class WindowControlBar extends JPanel {
    public WindowControlBar(JFrame frame) {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0)); // Right-align without spacing
        setPreferredSize(new Dimension(0, 32));
        setBackground(new Color(45, 45, 45));
        UIManager.put("ToolTip.background", new Color(60, 60, 60));
        UIManager.put("ToolTip.foreground", Color.WHITE);
        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.GRAY));
        ToolTipManager.sharedInstance().setInitialDelay(800); // Set how long to wait before showing the tooltip
        ToolTipManager.sharedInstance().setDismissDelay(4500); // Set how long the tooltip stays visible
        // ToolTipManager.sharedInstance().setReshowDelay(0); // Set the delay between re-showing tooltips

        add(createButton("-", "Minimize", frame, () -> frame.setState(Frame.ICONIFIED)));
        add(createButton("🗗︎", "Maximize", frame, () -> {
            int state = frame.getExtendedState();
            boolean willMaximize = (state & Frame.MAXIMIZED_BOTH) == 0;
            frame.setExtendedState(willMaximize ? Frame.MAXIMIZED_BOTH : Frame.NORMAL);
            if (willMaximize) {
                frame.setShape(null);
            } else {
                frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 30, 30));
            }
            frame.repaint();
        }));
        add(createButton("🗙︎", "Close", frame, frame::dispose));
    }

    private JButton createButton(String text, String tooltip, JFrame frame, Runnable action) {
        JButton button = new JButton(text);
        button.setToolTipText(tooltip);
        button.setFont(new Font("SansSerif", Font.BOLD, tooltip.equals("Minimize") ? 29 : 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(45, 45, 45)); // Default background color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 1)); // Padding control
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setPreferredSize(new Dimension(45, 32)); // Fixed size for alignment

        button.addActionListener(e -> action.run());

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if ("Close".equals(tooltip)) {
                    button.setBackground(new Color(200, 50, 50)); // Red for close
                } else {
                    button.setBackground(new Color(65, 65, 65)); // Darker for minimize/maximize
                }
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(45, 45, 45)); // Reset to default
            }
        });

        return button;
    }
}
