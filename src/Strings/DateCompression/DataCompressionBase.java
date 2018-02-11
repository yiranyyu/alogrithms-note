package Strings.DateCompression;
// Created by yirany on 2018/2/4

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

abstract public class DataCompressionBase
{
    abstract public void doCompress();
    abstract public void doExpand();
    public void test(String inputPath, String resultPath)
    {
        try
        {
            FileInputStream readOrigin = new FileInputStream(inputPath);
            PrintStream writeCompress = new PrintStream(new FileOutputStream("compressedFile"));
            System.setIn(readOrigin);
            System.setOut(writeCompress);
            doCompress();

            FileInputStream readCompress = new FileInputStream("compressedFile");
            PrintStream writeExpand = new PrintStream(new FileOutputStream(resultPath));
            System.setIn(readCompress);
            System.setOut(writeExpand);
            doExpand();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
