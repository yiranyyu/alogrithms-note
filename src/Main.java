import Stuff.Alphabet;
import Strings.RegularExpression.NFA;
import Stuff.ArrayFactor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] argv)
    {
        String[] strings = ArrayFactor.randomStrings(10000, 100, Alphabet.LOWERCASE);
        Pattern pattern = Pattern.compile("loveis");

        for (String str : strings)
        {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find())
            {
                System.out.println(str);
                for (int i = 0; i < matcher.start(); ++i)
                    System.out.print(" ");
                System.out.println(matcher.group() + " is " + NFA.recognize(str, ".*she.*"));
                System.out.println();
            }

        }
    }
}

