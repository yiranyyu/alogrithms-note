package Context.EventDrivenSimulation;

import Stuff.Rand;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

public class Particle
{
    private static final double DefaultRadius = 0.008D;
    private static final double DefaultMass = 1.0D;
    private static final Color[] Colors = {
            Color.RED, Color.GREEN, Color.ORANGE,
            Color.BLACK, Color.CYAN, Color.MAGENTA,
            Color.YELLOW, Color.GRAY, Color.BLUE
    };
    private static int NextID = 1;

    private double rx, ry;
    private double vx, vy;
    private final double radius;
    private int count = 0;
    private double mass = DefaultMass;
    private Color color;
    private int ID;
    private boolean hitUp       = false;
    private boolean hitBottom   = false;
    private boolean hitRight    = false;
    private boolean hitLeft     = false;

    public Particle()
    {
        mass = DefaultMass * StdRandom.uniform(0.2, 1.0);
        radius = DefaultRadius * mass;
        rx = StdRandom.uniform(radius, 1.0 - radius);
        ry = StdRandom.uniform(radius, 1.0 - radius);
        vx = StdRandom.uniform();
        vy = StdRandom.uniform();
        color = randomColor();
        ID = NextID++;
    }

    public Particle(double rx, double ry, double vx, double vy, double radius, double mass)
    {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.mass = mass;
        color = randomColor();
        ID = NextID++;
    }

    private Color randomColor()
    {
        int index = Rand.nextInt(0, 9);
        return Colors[index];
    }

    public int getCount()
    {
        return count;
    }

    public Color getColor()
    {
        return color;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Particle particle = (Particle) o;

        return ID == particle.ID;
    }

    @Override
    public int hashCode()
    {
        return ID;
    }


    public int getID()
    {
        return ID;
    }

    public double timeToHit(Particle that)
    {
        if (this == that)   return Double.NaN;

        double dx  = that.rx - this.rx;
        double dy  = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0)   return Double.NaN;
        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0)  return Double.NaN;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitHorizontalWall()
    {
        if (vy == 0.0)    return Double.NaN;
        if (vy >  0.0)    return (1.0 - radius - ry) / vy;
        else              return (ry - radius) / (-vy);
    }

    public double timeToHitVerticalWall()
    {
        if (vx == 0.0)    return Double.NaN;
        if (vx >  0.0)    return (1.0 - radius - rx) / vx;
        else            return (rx - radius) / (-vx);
    }

    public void bounceOff(Particle that)
    {
        double dx  = that.rx - this.rx;
        double dy  = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;
        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        that.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;
        this.count++;
        that.count++;
        hitBottom = hitUp = hitLeft = hitRight = false;
    }


    public double getRx()
    {
        return rx;
    }

    public double getRy()
    {
        return ry;
    }

    public double getVx()
    {
        return vx;
    }

    public double getVy()
    {
        return vy;
    }

    public void bounceOffHorizontalWall()
    {
        vy = -vy;
    }

    public void bounceOffVerticalWall()
    {
        vx = -vx;
    }

    public void move(double dt)
    {
        // hit left wall
        if (rx + dt*vx <= (radius) && !hitLeft)
        {
            vx = -vx;
            hitLeft  = true;
            hitBottom = hitUp = hitRight = false;
            count++;
        }

        // hit right wall
        if (rx + dt*vx >= 1.0 - radius && !hitRight)
        {
            vx = -vx;
            hitRight = true;
            hitBottom = hitUp = hitLeft = false;
            count++;
        }

        // hit bottom
        if (ry + dt*vy <= radius && !hitBottom)
        {
            vy = -vy;
            hitBottom = true;
            hitUp = hitLeft = hitRight = false;
            count++;
        }

        // hit up wall
        if (ry + dt*vy >= 1.0 - radius && !hitUp)
        {
            vy = -vy;
            hitBottom = hitLeft = hitRight = false;
            hitUp     = true;
            count++;
        }
        // TODO fix (the first move makes point out of range BUG!!)
        rx += dt*vx;
        ry += dt*vy;
    }

    public void draw()
    {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }
}
