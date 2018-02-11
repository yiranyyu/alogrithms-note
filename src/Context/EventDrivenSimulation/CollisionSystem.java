package Context.EventDrivenSimulation;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;

public class CollisionSystem
{
    private MinPQ<Event> events;
    private double t = 0.0;         // simulation clock time
    private Context.EventDrivenSimulation.Particle[] particles;
    private Event curEvent;
    private double limit = 1000;
    private double Hz = 100;

    public CollisionSystem(Context.EventDrivenSimulation.Particle[] particles)
    {
        this.particles = particles;
    }

    public CollisionSystem(Context.EventDrivenSimulation.Particle[] particles, double limit, double hz)
    {
        this.particles = particles;
        this.limit = limit;
        Hz = hz;
    }

    public void simulate()
    {
        init();
        runSimulate();
    }

    private void init()
    {
        events = new MinPQ<>();
        for (Context.EventDrivenSimulation.Particle p : particles) predict(p);
        events.insert(new Event(0, null, null));
        StdDraw.enableDoubleBuffering();
    }

    private void runSimulate()
    {
        assertNotNull(events);
        while (!events.isEmpty())
        {
            curEvent = events.delMin();
            if (!eventIsValid())       continue;

            move();
            changeCurrentTime();
            handleValidBounce();
            predictEvents();
        }
    }

    private boolean eventIsValid()
    {
        if (curEvent.a == null && curEvent.b == null)
            return true;
        return curEvent.isValid() && curEvent.time - t > 0.0;
    }

    private void move()
    {
        for (Context.EventDrivenSimulation.Particle p : particles)
            p.move(curEvent.time - t);
    }

    private void changeCurrentTime()
    {
        t = curEvent.time;
    }

    private void handleValidBounce()
    {
        Context.EventDrivenSimulation.Particle a = curEvent.a;
        Context.EventDrivenSimulation.Particle b = curEvent.b;
        if (a != null && b != null) a.bounceOff(b);
        else if (a != null )        a.bounceOffVerticalWall();
        else if (b != null)         b.bounceOffHorizontalWall();
        else                        redraw();
    }

    private void redraw()
    {
        StdDraw.clear();
        for (Context.EventDrivenSimulation.Particle p : particles)
            p.draw();
        StdDraw.show();
        StdDraw.pause(1);
        events.insert(new Event(t + 1.0/Hz, null, null));
    }

    private void predictEvents()
    {
        predict(curEvent.a);
        predict(curEvent.b);
    }

    // add all the possible collision to the events
    private void predict(Context.EventDrivenSimulation.Particle a)
    {
        if (a == null) return;


        for (Context.EventDrivenSimulation.Particle p : particles)
        {
            double dt = a.timeToHit(p);
            if (!Double.isNaN(dt) && dt > 0.0 && t+dt < limit)
                events.insert(new Event(t + dt, a, p));
        }

        double dtX = a.timeToHitVerticalWall();
        if (dtX >= 0 && t+dtX <= limit)
            events.insert(new Event(t + dtX, a, null));

        double dtY = a.timeToHitHorizontalWall();
        if (dtY >= 0 && t+dtY <= limit)
            events.insert(new Event(t + dtY, null, a));

        if (dtX < 0.0 || dtY < 0.0)
        {
            System.out.println(a.timeToHitHorizontalWall() + "\n" + a.timeToHitVerticalWall());
            System.out.println(a.getRx() + "," + a.getRy() + ";");
            System.out.println("v: " + a.getVx() + "," + a.getVy() + ";");
        }
    }

    private void assertNotNull(Object o)
    {
        if (o == null) throw new NullPointerException();
    }

    public static void main(String[] argv)
    {
        final int size = 100;
        Context.EventDrivenSimulation.Particle[] particles = new Context.EventDrivenSimulation.Particle[size];
        for (int i = 0; i < size; ++i)
            particles[i] = new Context.EventDrivenSimulation.Particle();
        CollisionSystem system = new CollisionSystem(particles, 1000, 1000);
        system.simulate();
        System.out.println("end");
    }

    private class Event implements Comparable<Event>
    {
        private double time;
        private Context.EventDrivenSimulation.Particle a, b;
        private int countA, countB;

        /**
         * Create a new Event if neither a or b is null
         * Add a hit the wall Event if a or b is null
         * otherwise, just redraw
         */
        public Event(double t, Context.EventDrivenSimulation.Particle a, Context.EventDrivenSimulation.Particle b)
        {
            this.time = t;
            this.a = a;
            this.b = b;
            if (a != null) countA = a.getCount();
            if (b != null) countB = b.getCount();
        }

        @Override
        public int compareTo(Event that)
        {
            return Double.compare(this.time, that.time);
        }

        public boolean isValid()
        {
            if (a != null && a.getCount() != countA) return false;
            if (b != null && b.getCount() != countB) return false;
            return true;
        }
    }
}
