package Stuff;

/*
 * Created by yirany on 2017/10/15.
 */
public class Stopwatch
{
    private long star;
    private long lastPause;
    private boolean paused;
    private long pauseCost;

    public Stopwatch()
    {
        star = current();
    }

    public double elapsedTime()
    {
        return elapsedNanoTime() / 1000_000.0;
    }

    public long elapsedNanoTime()
    {
        return current() - star + pauseCost;
    }

    public void pause()
    {
        if (!paused)
        {
            paused = true;
            lastPause = current();
        }
    }

    public void start()
    {
        if (paused)
        {
            paused = false;
            pauseCost += current() - lastPause;
        }
    }

    private long current()
    {
        return System.nanoTime();
    }
}
