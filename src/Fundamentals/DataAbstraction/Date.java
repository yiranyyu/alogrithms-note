package Fundamentals.DataAbstraction;

/*
 * Created by yirany on 2017/10/1.
 */


/**
 * @deprecated this class is just for learning, get a useful Date abstraction
 * @see java.time.LocalDate
 */
public class Date
{
    private static final int[] DAY_OF_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] DAY_OF_WEEK = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    private static final String ILLEGAL_DAY = "day out of range";
    private static final String ILLEGAL_MONTH = "month must be in range [1, 12]";


    private final int day;
    private final int month;
    private final int year;

    public Date(int year, int month, int dayOfMonth)
    {
        day = dayOfMonth;
        this.month = month;
        this.year = year;
        checkDate();
    }

    private void checkDate()
    {
        if (month <= 0 || month > 12)
            throw new IllegalArgumentException(ILLEGAL_MONTH);
        checkDayOfMonth();
    }

    private void checkDayOfMonth()
    {
        if (isLeapYear() && month == 2 && day == 29)
            return;
        if (day <= 0 || day > DAY_OF_MONTH[month])
            throw new IllegalArgumentException(ILLEGAL_DAY);
    }

    public boolean isLeapYear()
    {
        return ( (year%4 == 0 && year%100 != 0) || year%400 == 0);
    }

    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    @Override
    public String toString()
    {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date date = (Date) o;

        if (day != date.day) return false;
        if (month != date.month) return false;
        return year == date.year;
    }

    @Override
    public int hashCode()
    {
        int result = day;
        result = 31 * result + month;
        result = 31 * result + year;
        return result;
    }
}
