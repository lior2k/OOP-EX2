import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    LinkedList<Point3D> mPoints = new LinkedList<Point3D>();
    Point3D mPivot_point = null;
    boolean mDraw_pivot = false;
    boolean mMoving_point = false;
    private int kRADIUS = 5;
    private int mWin_h = 500;
    private int mWin_w = 500;
    private Image mBuffer_image;
    private Graphics mBuffer_graphics;

    public Window() {
        initGUI();
    }

    private void initGUI() {
        this.setSize(mWin_h, mWin_w);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        menuBar.add(menu);
        this.setMenuBar(menuBar);

        MenuItem item1 = new MenuItem("simpleTriangle");
        item1.addActionListener(this);

        MenuItem item2 = new MenuItem("clean-up");
        item2.addActionListener(this);

        menu.add(item1);
        menu.add(item2);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paintComponents(Graphics g) {
        Point3D prev = null;

        for (Point3D p : mPoints) {
            g.setColor(Color.BLUE);
            g.fillOval((int) p.x() - kRADIUS, (int) p.y() - kRADIUS,
                    2 * kRADIUS, 2 * kRADIUS);

            if (prev != null) {
                g.setColor(Color.RED);
                g.drawLine((int) p.x(), (int) p.y(),
                        (int) prev.x(), (int) prev.y());

                double dist = prev.distance3D(p);
                g.drawString(String.format("%.2f", dist),
                        (int) ((p.x() + prev.x()) / 2),
                        (int) ((p.y() + prev.y()) / 2));
            }

            prev = p;
        }

        if (mDraw_pivot
                && !mMoving_point) {
            g.setColor(Color.BLUE);
            g.fillOval((int) mPivot_point.x() - kRADIUS, (int) mPivot_point.y() - kRADIUS,
                    2 * kRADIUS, 2 * kRADIUS);
            if (prev != null) {
                g.setColor(Color.RED);
                g.drawLine((int) mPivot_point.x(), (int) mPivot_point.y(),
                        (int) prev.x(), (int) prev.y());


                float dist = (float)prev.distance3D(mPivot_point);
                float font_size = (float) Math.max(10.0f, Math.pow(dist,1.5f) / 100);
                font_size = Math.min(40.0f, font_size);

                Font font = g.getFont().deriveFont(font_size);
                g.setFont(font);
                g.drawString(String.format("%.2f", dist), (int) ((mPivot_point.x() + prev.x()) / 2), (int) ((mPivot_point.y() + prev.y()) / 2));
            }

        }
    }

    public void paint(Graphics g) {
        // Create a new "canvas"
        mBuffer_image = createImage(mWin_w,mWin_h );
        mBuffer_graphics = mBuffer_image.getGraphics();

        // Draw on the new "canvas"
        paintComponents(mBuffer_graphics);

        // "Switch" the old "canvas" for the new one
        g.drawImage(mBuffer_image, 0, 0, this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();

        if (str.equals("simpleTriangle")) {
            Point3D p1 = new Point3D(100, 100);
            Point3D p2 = new Point3D(50, 300);
            Point3D p3 = new Point3D(400, 150);

            mPoints.add(p1);
            mPoints.add(p2);
            mPoints.add(p3);
            mPoints.add(p1);

            repaint();
        } else if (str.equals("clean-up")) {
            mPoints.removeAll(mPoints);
            repaint();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point3D tmp = new Point3D(x, y);
        int min_dist = (int) (kRADIUS * 1.);
        double best_dist = 1000000;
        for (Point3D p : mPoints) {
            double dist = tmp.distance3D(p);
            if (dist < min_dist
                    && dist < best_dist) {
                mPivot_point = p;
                best_dist = dist;
                mMoving_point = true;
            }
        }

        if (mPivot_point == null) {
            mPivot_point = tmp;
        }
        mDraw_pivot = true;
        repaint();
        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
        if (!mMoving_point) {
            mPoints.add(new Point3D(mPivot_point));
        }
        mMoving_point = false;
        mPivot_point = null;
        mDraw_pivot = false;

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (mDraw_pivot) {
            mPivot_point.setX(x);
            mPivot_point.setY(y);

            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
