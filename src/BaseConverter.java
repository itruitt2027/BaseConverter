import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Prints a conversion for a variety of numbers as specified by a data file.
 * @version 11/30/2022
 * @author 23truitt
 */

public class BaseConverter {
    private final String DIGITS = "0123456789ABCDEF";
    /**
     * Convert a String num in fromBase to base-1o int.
     * @param num the original number
     * @param fromBase the original from base
     * @return a base-10 int of num base fromBase
     */
    public int strToInt(String num, String fromBase) {
        int value = 0, exp = 0;
        for(int i = num.length()-1; i >= 0; i--) {
            double base;
            value += DIGITS.indexOf(num.charAt(i)) * Math.pow(Integer.parseInt(fromBase), exp);
            exp++;
        }
        /*
        Start at the rightmost digit of num
        run a loop for i digits of num -- backwards
            pull out the character at index i
            find the indexOf that character in DIGITS
            value += indexOf character * Math.power(fromBase, exponent);
            exp++ or exp += 1
         */
        return value;
    }
    /**
     * intToStr converts ints to strings so the numbers in DIGITS are able to be used as a string
     * @param num the original number
     * @param toBase the original base
     * @return a string
     */
    public String intToStr(int num, int toBase) {
        String toNum = new String();
        int index = -1;
        while(num > 0) {
            index = num % toBase;
            toNum = DIGITS.charAt(index) + toNum;
            num /= toBase;
        }
        return (toNum.equals("")) ? "0" : toNum;
    }

    /**
     * inputConvertPrintWrite is a method used to convert values
     */
    public void inputConvertPrintWrite()    {
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(new File("datafiles/values20.dat"));
            out = new PrintWriter(new File("datafiles/converted.dat"));
            String[] line;
            String output;
            while(in.hasNext()) {
                line = in.nextLine().split("\t");
                // System.out.println(line[1]); //
                // System.out.println(line[2]);
                // System.out.println(line[3]);
                if(Integer.parseInt(line[1]) < 2 || Integer.parseInt(line[1]) > 16)
                    System.out.println("Invalid input Base " + line[1]);
                else if(Integer.parseInt(line[2]) < 2 || Integer.parseInt(line[2]) > 16)
                    System.out.println("Invalid output Base " + line[2]);
                else {
                    output = intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2]));
                    out.println(line[0] + "\t" + line[1] + "\t" + output + "\t" + line[2]);
                    System.out.println(line[0] + " Base " + line[1] + " = " + output + " Base " + line[2]);
                }
            }
            //out.println("Does this work?");
            //out.println("Is AP Comp Sci truly the BEST class at DA?");
            if(out != null)
                out.close();
            if(in != null)
                in.close();
            //System.out.println("The revolution will not be televised.");

        }
        catch(Exception e) {
            System.out.println("Something bad happened. Details here: " + e);
        }
    }

    /**
     * Main is to run the BaseConverter function and then uses the String to int to make the numbers,
     * then writes that information to a new file with the converted numbers with their new baes
     * @param args
     */
    public static void main(String[] args) {
        BaseConverter bc = new BaseConverter();
        bc.strToInt("", "");
        bc.inputConvertPrintWrite();
    }
}
