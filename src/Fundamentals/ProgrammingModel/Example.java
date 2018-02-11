package Fundamentals.ProgrammingModel;

/*
 * Created by yirany on 2017/9/30.
 */
public class Example
{
    private class Widget
    {
        private String name;

        Widget(String name)
        {
            this.name = name;
        }

        void fun()
        {
            System.out.println(name);
        }
    }

    private void usingArray()
    {
        // Initial the elements in Object array
        Widget[] widgets = new Widget[100];
        try
        {
            widgets[0].fun();
        }catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
            widgets[0] = new Widget("This is the first Widget");
            widgets[0].fun();
        }// you have to initialize every element in the array

    }
    public static void main(String[] args)
    {
        Example example = new Example();
        example.usingArray();
    }
}
