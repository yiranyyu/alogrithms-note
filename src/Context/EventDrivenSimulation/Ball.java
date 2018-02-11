package Context.EventDrivenSimulation;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ball
{
    private double rx, ry;
    private double vx, vy;
    private final double radius;
    private static final double DefaultRadius = 0.004;

    /**
     * Default construct ball, init rx, ry, vx, vy a random num of [0, 1.0)
     */
    Ball()
    {
        rx = StdRandom.uniform();
        ry = StdRandom.uniform();
        vx = StdRandom.uniform();
        vy = StdRandom.uniform();
        radius = DefaultRadius;
    }

    public Ball(double rx, double ry, double vx, double vy, double radius)
    {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    public Ball(double rx, double ry, double vx, double vy)
    {
        this(rx, ry, vx, vy, DefaultRadius);
    }

    public void move(double dt)
    {
        // if hit left wall or hit right wall
        if (rx + dt * vx < radius || (rx + vx * dt > 1.0 - radius)) {vx = -vx;}

        // if hit up wall or hit bottom
        if (ry + dt * vy < radius || (ry + vy * dt > 1.0 - radius)) {vy = -vy;}
        rx += vx * dt;
        ry += vy * dt;
    }
    public void draw()
    {
        StdDraw.filledCircle(rx, ry, radius);
    }
}
