package Stuff;

public class myAssert
{
    public static void assertTrue(boolean condition, String msg)
    {
        if (!condition)
            throw new AssertionError(msg);
    }

    public static void assertTrue(boolean condition)
    {
        assertTrue(condition, null);
    }

    public static void assertFalse(boolean condition, String msg)
    {
        assertTrue(!condition, msg);
    }

    public static void assertFalse(boolean condition)
    {
        assertTrue(!condition);
    }

    public static void assertIsNull(Object o, String msg)
    {
        assertTrue(o == null, msg);
    }

    public static void assertIsNull(Object o)
    {
        assertTrue(o == null);
    }

    public static void assertNotNull(Object o, String msg)
    {
        assertTrue(o != null, msg);
    }

    public static void assertNotNull(Object o)
    {
        assertTrue(o != null);
    }
}
