package hsr_java_gui_gradle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResizableFrameHelper {
    private static final int BORDER_DRAG_THICKNESS = 8;
    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 300;

    public static void makeResizable(JFrame frame) {
        ResizeListener resizeListener = new ResizeListener(frame);
        frame.getRootPane().addMouseListener(resizeListener);
        frame.getRootPane().addMouseMotionListener(resizeListener);
    }

    private static class ResizeListener extends MouseAdapter {
        private final JFrame frame;
        private Point clickPoint;
        private int dragCursor = Cursor.DEFAULT_CURSOR;

        public ResizeListener(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int cursorType = getCursorType(e.getPoint(), frame);
            frame.setCursor(Cursor.getPredefinedCursor(cursorType));
        }

        @Override
        public void mousePressed(MouseEvent e) {
            clickPoint = e.getPoint();
            dragCursor = getCursorType(clickPoint, frame);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (dragCursor != Cursor.DEFAULT_CURSOR) {
                Rectangle bounds = frame.getBounds();
                Point screenPoint = e.getLocationOnScreen();
                Point frameLoc = frame.getLocationOnScreen();

                int newWidth = bounds.width;
                int newHeight = bounds.height;

                switch (dragCursor) {
                    case Cursor.E_RESIZE_CURSOR:
                        newWidth = screenPoint.x - frameLoc.x;
                        break;
                    case Cursor.S_RESIZE_CURSOR:
                        newHeight = screenPoint.y - frameLoc.y;
                        break;
                    case Cursor.SE_RESIZE_CURSOR:
                        newWidth = screenPoint.x - frameLoc.x;
                        newHeight = screenPoint.y - frameLoc.y;
                        break;
                    case Cursor.W_RESIZE_CURSOR:
                        int dx = screenPoint.x - frameLoc.x;
                        newWidth = bounds.width - dx;
                        if (newWidth >= MIN_WIDTH) {
                            bounds.x += dx;
                            bounds.width = newWidth;
                        }
                        break;
                    case Cursor.N_RESIZE_CURSOR:
                        int dy = screenPoint.y - frameLoc.y;
                        newHeight = bounds.height - dy;
                        if (newHeight >= MIN_HEIGHT) {
                            bounds.y += dy;
                            bounds.height = newHeight;
                        }
                        break;
                    case Cursor.NW_RESIZE_CURSOR:
                        dx = screenPoint.x - frameLoc.x;
                        dy = screenPoint.y - frameLoc.y;
                        newWidth = bounds.width - dx;
                        newHeight = bounds.height - dy;
                        if (newWidth >= MIN_WIDTH) {
                            bounds.x += dx;
                            bounds.width = newWidth;
                        }
                        if (newHeight >= MIN_HEIGHT) {
                            bounds.y += dy;
                            bounds.height = newHeight;
                        }
                        break;
                    case Cursor.NE_RESIZE_CURSOR:
                        newHeight = bounds.height - (screenPoint.y - frameLoc.y);
                        dy = screenPoint.y - frameLoc.y;
                        dx = screenPoint.x - frameLoc.x;
                        if (newHeight >= MIN_HEIGHT) {
                            bounds.y += dy;
                            bounds.height = newHeight;
                        }
                        newWidth = dx;
                        break;
                    case Cursor.SW_RESIZE_CURSOR:
                        dx = screenPoint.x - frameLoc.x;
                        dy = screenPoint.y - frameLoc.y;
                        newWidth = bounds.width - dx;
                        newHeight = dy;
                        if (newWidth >= MIN_WIDTH) {
                            bounds.x += dx;
                            bounds.width = newWidth;
                        }
                        break;
                }

                if (dragCursor == Cursor.E_RESIZE_CURSOR ||
                    dragCursor == Cursor.S_RESIZE_CURSOR ||
                    dragCursor == Cursor.SE_RESIZE_CURSOR ||
                    dragCursor == Cursor.NE_RESIZE_CURSOR ||
                    dragCursor == Cursor.SW_RESIZE_CURSOR) {

                    bounds.width = Math.max(MIN_WIDTH, newWidth);
                    bounds.height = Math.max(MIN_HEIGHT, newHeight);
                }

                frame.setBounds(bounds);
                frame.revalidate();
            }
        }

        private int getCursorType(Point p, JFrame frame) {
            int width = frame.getWidth();
            int height = frame.getHeight();

            boolean left = p.x < BORDER_DRAG_THICKNESS;
            boolean right = p.x > width - BORDER_DRAG_THICKNESS;
            boolean top = p.y < BORDER_DRAG_THICKNESS;
            boolean bottom = p.y > height - BORDER_DRAG_THICKNESS;

            if (left && top) return Cursor.NW_RESIZE_CURSOR;
            if (right && top) return Cursor.NE_RESIZE_CURSOR;
            if (left && bottom) return Cursor.SW_RESIZE_CURSOR;
            if (right && bottom) return Cursor.SE_RESIZE_CURSOR;
            if (left) return Cursor.W_RESIZE_CURSOR;
            if (right) return Cursor.E_RESIZE_CURSOR;
            if (top) return Cursor.N_RESIZE_CURSOR;
            if (bottom) return Cursor.S_RESIZE_CURSOR;

            return Cursor.DEFAULT_CURSOR;
        }
    }
}
