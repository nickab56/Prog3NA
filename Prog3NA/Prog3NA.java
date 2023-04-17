
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Prog3NA
{
    // instance variables - replace the example below with your own
    private int x;
    private static PrintWriter outStream = null;
    private static Scanner inStream = null;
    
    
    public static void main(String[] args)    
    {
        Scanner input = new Scanner(System.in); 
        System.out.print("Enter the name of the .txt file that contains the code");
        String fileName = input.nextLine();
        String token;
        
        try
        {
            inStream = new Scanner(new File(fileName));
        } // end try
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening the file " + fileName);
            System.exit(0);
        
        } // end catch
        try
        {
          outStream = new PrintWriter("Prog3OutNA.txt");     
        } // end try
        catch(FileNotFoundException e)
        {
            System.out.println("error opening the file" + fileName);
            System.exit(0);
        } // end catch
        
        token = ReadToken();
    
        if (Start(token))
            System.out.println("Successful Program");
        else
            System.out.println("Not Successful Program");
        
    // then close the files
    outStream.close();
    inStream.close();
        
    }
    
    public static String ReadToken()
    {
        return inStream.nextLine();
    }
    
    public static boolean Start(String token)
    {
        System.out.println("In Start");
        if (encodeFile(token = ReadToken()))
            if (lineFormat(token = ReadToken()))
                if (encodeFormat(token = ReadToken()))
                    if (outputFile(token = ReadToken()))
                    {
                    System.out.println("Start-success");
                    outStream.println("Start-success");
                    return true;
                    }
            return false;
    }
    
    public static boolean encodeFile(String token)
    {
        System.out.println("In encodeFile");
        if (name(token = ReadToken()))
            if((token = ReadToken()).charAt(0) == '.')
                if (name(token = ReadToken()))
                    if (stmtTerminator(token = ReadToken()))
                    {
                    System.out.println("encodeFile-success");
                    outStream.println("encodeFile-success");
                    return true;
                    }
        return false;
    }
    
    public static boolean name(String token)
    {
        System.out.println("In name");
        if (toekn
        return false;
    }
    
    public static boolean lineFormat(String token)
    {
        System.out.println("In lineFormat");
        if (lineFormatType(token = ReadToken()))
            if(stmtTerminator(token = ReadToken()))
            {
                System.out.println("lineFormat-success");
                outStream.println("lineFormat-success");
                return true;
            }
        return false;
    }
    
    public static boolean encodeFormat(String token)
    {
        System.out.println("In encodeFormat(String token");
        if (encodeFormatType(token = ReadToken()))
            if(stmtTerminator(token = ReadToken()))
            {
                System.out.println("encodeFormat-success");
                outStream.println("encodeFormat-success");
                return true;
            }
        return false;
    }
    
    public static boolean outputFile(String token)
    {
        System.out.println("In outputFile");
        if (name(token = ReadToken())){
            System.out.println("about to check for '.'");
            if((token = ReadToken()).charAt(0) == '.')
                if(name(token = ReadToken()))
                    if(stmtTerminator(token = ReadToken()))
                    {
                        System.out.println("outputFile-success");
                        outStream.println("outputFile-success");
                        return true;
                    }
                }
        return false;
    }
    
    public static boolean stmtTerminator(String token)
    {
        System.out.println("In stmtTerminator");
        if (token == "!")
        {
            System.out.println("stmtTerminator-success");
            outStream.println("stmtTerminator-success");
            return true;
        }
        return false;
    }
    
    public static boolean lineFormatType(String token)
    {
        System.out.println("In lineFormatType");
        if (token == "string" || token == "number")
        {
            System.out.println("lineFormatType-success");
            outStream.println("lineFormatType-success");
            return true;
        }
        return false;
    }
    
    
    public static boolean encodeFormatType(String token)
    {
        System.out.println("In encodeFormatType");
        if (token.charAt(0) == '+' || token.charAt(0) == '-' || token.charAt(0) == '*')
        {
            if (digits(token = ReadToken()))
            {
            System.out.println("encodeFormatType-success");
            outStream.println("encodeFormatType-success");
            return true;
            }
        }
        return false;
    }
    
    public static boolean digits(String token)
    {
        System.out.println("In digits");
        int i = token.length();
        
        if (i == 1)
            if (digit((token = ReadToken()).charAt(0)))
            {
                System.out.println("digits-success");
                outStream.println("digits-success");
                return true;
            }
        else
            {
                int x = 0;
                while (x < i)
                {
                    digit((token = ReadToken()).charAt(i));
                    i--;
                }
                return true;
            }
        return false;
    }
    
    public static boolean letter(char tokenChar)
    {
        System.out.println("In letter");
        if (Character.isAlphabetic(tokenChar))
        {
            System.out.println("letter-success");
            outStream.println("letter-success");
            return true;
        }
        else
            return false;
    }
    
    public static boolean digit(char tokenChar)
    {
        System.out.println("In digit");
        int tokenInt = Character.getNumericValue(tokenChar);
        if (tokenInt >= 0)
        {
            System.out.println("digit-success");
            outStream.println("digit-success");
            return true;
        }
        else
            return false;
    }
    
    
}
