import javax.swing.*;
import javax.swing.JFrame;import javax.swing.JPanel;import javax.swing.Timer;
import java.awt.*;
import java.awt.Color;import java.awt.Graphics;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.Double;import java.lang.Math;import java.lang.Override;import java.lang.String;import java.util.*;
import java.util.LinkedList;import java.util.List;

/**
 * Created by Alex on 18/05/2015.
 */
public class Sim extends JPanel implements ActionListener {

    private double k = 0.02;
    private double x = 100.0;
    private double y = -50.0;
    private double vx;
    private double vy;
    private double ax;
    private double ay;
    private List<Double> xData = new LinkedList<Double>();

    public Sim() {
        Timer timer = new Timer(10, this);
        timer.setInitialDelay(250);
        timer.start();
    }

    public int plotY(double p) {
        return (int) (this.getHeight() - (p + this.getHeight() / 2));
    }

    public int plotX(double p) {
        return (int) (p + this.getWidth() / 2);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
        g.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
        g.drawLine(plotX(250.0), plotY(0), plotX(x), plotY(y));
        g.drawLine(plotX(-250.0), plotY(0), plotX(x), plotY(y));
        g.drawLine(plotX(0), plotY(250.0), plotX(x), plotY(y));
        g.drawLine(plotX(0), plotY(-250.0), plotX(x), plotY(y));
        g.fillOval(plotX(x - 5.0), plotY(y + 5.0), 10, 10);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Spring simulation");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.add(new Sim());
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        xData.add(x);
        ax = -2 * k * x - 2 * k * x * Math.abs(Math.sin(Math.atan(x / Math.abs(250.0 - y))));
        ay = -2 * k * y - 2 * k * y * Math.abs(Math.sin(Math.atan(y / Math.abs(250.0 - x))));
        vx += ax;
        vy += ay;
        vx *= 0.995;
        vy *= 0.995;
        x += vx;
        y += vy;

        repaint();

    }
}
