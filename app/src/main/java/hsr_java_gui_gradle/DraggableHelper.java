package hsr_java_gui_gradle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DraggableHelper {

    private Point initialMouseLocation;
    private Point initialFrameLocation;

    public DraggableHelper(Component dragSource, JFrame frameToMove) {
        dragSource.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialMouseLocation = e.getLocationOnScreen();
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
