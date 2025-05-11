package hsr_java_gui_gradle;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class Draggable {
    private Point initialMouseLocation;
    private Point initialFrameLocation;
    private boolean wasMaximized;

    public Draggable(Component dragSource, JFrame frameToMove) {
        dragSource.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                wasMaximized = (frameToMove.getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH;
                initialMouseLocation = e.getLocationOnScreen();

                if (wasMaximized) {
                    // Exit maximized state
                    frameToMove.setExtendedState(JFrame.NORMAL);

                    // Optional: add some padding offset so the cursor appears to drag from the same position
                    int cursorX = e.getXOnScreen();
                    int cursorY = e.getYOnScreen();
                    int newX = cursorX - frameToMove.getWidth() / 2;
                    int newY = cursorY - 16; // rough height of title bar
                    frameToMove.setLocation(newX, newY);
                }

                initialFrameLocation = frameToMove.getLocation();
            }
        });

        dragSource.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point finalMouseLocation = e.getLocationOnScreen();
                int deltaX = finalMouseLocation.x - initialMouseLocation.x;
                int deltaY = finalMouseLocation.y - initialMouseLocation.y;
                Point newLocation = new Point(initialFrameLocation.x + deltaX, initialFrameLocation.y + deltaY);
                frameToMove.setLocation(newLocation);
            }
        });
    }
}
