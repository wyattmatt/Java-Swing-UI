package hsr_java_gui_gradle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResizableOverlayHelper {
    private static final int BORDER = 8;
    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 300;

    public static void install(JFrame frame) {
        JPanel glass = new JPanel(null) {
            @Override
            public boolean contains(int x, int y) {
                return x < BORDER || x > getWidth() - BORDER ||
                       y < BORDER || y > getHeight() - BORDER;
            }
        };

        glass.setOpaque(false);
        ResizeListener listener = new ResizeListener(frame, glass);
        glass.addMouseListener(listener);
        glass.addMouseMotionListener(listener);

        frame.setGlassPane(glass);
        glass.setVisible(true);
    }

    private static class ResizeListener extends MouseAdapter {
        private final JFrame frame;
        private final JPanel glass;
        private Point clickPoint;
        private int dragCursor = Cursor.DEFAULT_CURSOR;

        public ResizeListener(JFrame frame, JPanel glass) {
            this.frame = frame;
            this.glass = glass;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            dragCursor = getCursor(e.getPoint());
            glass.setCursor(Cursor.getPredefinedCursor(dragCursor));
        }

        @Override
        public void mousePressed(MouseEvent e) {
            clickPoint = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (dragCursor == Cursor.DEFAULT_CURSOR || clickPoint == null) return;

            Point screenPoint = e.getLocationOnScreen();
            Point frameLoc = frame.getLocationOnScreen();
            Rectangle bounds = frame.getBounds();

            int dx = screenPoint.x - frameLoc.x;
            int dy = screenPoint.y - frameLoc.y;

            switch (dragCursor) {
                case Cursor.E_RESIZE_CURSOR -> bounds.width = Math.max(MIN_WIDTH, dx);
                case Cursor.S_RESIZE_CURSOR -> bounds.height = Math.max(MIN_HEIGHT, dy);
                case Cursor.SE_RESIZE_CURSOR -> {
                    bounds.width = Math.max(MIN_WIDTH, dx);
                    bounds.height = Math.max(MIN_HEIGHT, dy);
                }
                case Cursor.W_RESIZE_CURSOR -> {
                    int newWidth = bounds.width - dx;
                    if (newWidth >= MIN_WIDTH) {
                        bounds.x += dx;
                        bounds.width = newWidth;
                    }
                }
                case Cursor.N_RESIZE_CURSOR -> {
                    int newHeight = bounds.height - dy;
                    if (newHeight >= MIN_HEIGHT) {
                        bounds.y += dy;
                        bounds.height = newHeight;
                    }
                }
                case Cursor.NW_RESIZE_CURSOR -> {
                    int newWidth = bounds.width - dx;
                    int newHeight = bounds.height - dy;
                    if (newWidth >= MIN_WIDTH) {
                        bounds.x += dx;
                        bounds.width = newWidth;
                    }
                    if (newHeight >= MIN_HEIGHT) {
                        bounds.y += dy;
                        bounds.height = newHeight;
                    }
                }
                case Cursor.NE_RESIZE_CURSOR -> {
                    bounds.width = Math.max(MIN_WIDTH, dx);
                    int newHeight = bounds.height - dy;
                    if (newHeight >= MIN_HEIGHT) {
                        bounds.y += dy;
                        bounds.height = newHeight;
                    }
                }
                case Cursor.SW_RESIZE_CURSOR -> {
                    int newWidth = bounds.width - dx;
                    if (newWidth >= MIN_WIDTH) {
                        bounds.x += dx;
                        bounds.width = newWidth;
                    }
                    bounds.height = Math.max(MIN_HEIGHT, dy);
                }
            }

            frame.setBounds(bounds);
            frame.revalidate();
        }

        private int getCursor(Point p) {
            int w = frame.getWidth();
            int h = frame.getHeight();

            boolean left = p.x < BORDER;
            boolean right = p.x > w - BORDER;
            boolean top = p.y < BORDER;
            boolean bottom = p.y > h - BORDER;

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
