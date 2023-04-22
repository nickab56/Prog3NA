
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
    
    private static String tempToken;
    
    
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
        System.out.println("Entering function: Start");
        outStream.println("Entering function: Start");
        if (encodeFile(token))
            if (lineFormat(token = ReadToken()))
                if (encodeFormat(token = ReadToken()))
                    if (outputFile(token = ReadToken()))
                    {
                    System.out.println("Leaving function: Start (Success)");
                    outStream.println("Leaving function: Start (Success)");
                    return true;
                    }
        System.out.println("Leaving function: Start (failed)");
        outStream.println("Leaving function: Start (failed)"); 
        return false;
    }
    
    public static boolean encodeFile(String token)
    {
        System.out.println("Entering function: encodeFile");
        outStream.println("Entering function: encodeFile");
        if (name(token = ReadToken()))
            if((token = ReadToken()).charAt(0) == '.')
                if (name(token = ReadToken()))
                    if (stmtTerminator(token = ReadToken()))
                    {
                    System.out.println("Leaving function: encodeFile (Success)");
                    outStream.println("Leaving function: encodeFile (Success)");
                    return true;
                    }
        System.out.println("Leaving function: encodeFile (failed)");
        outStream.println("Leaving function: encodeFile (failed)");            
        return false;
    }
    
    public static boolean name(String token)
    {
        System.out.println("Entering function: name");
        outStream.println("Entering function: name");
        int i = token.length();
        if (i == 1)
        {
            if (letter(token.charAt(0)))
            {
                System.out.println("Leaving function: name (Success)");
                outStream.println("Leaving function: name (Success)");
                return true;
            }
        }
        else
            {
                int x = 0;
                while (x < i)
                {
                    letter(token.charAt(x));
                    x++;
                }
                System.out.println("Leaving function: name (Success)");
                outStream.println("Leaving function: name (Success)");
                return true;
            }
        System.out.println("Leaving function: name (failed)");
        outStream.println("Leaving function: name (failed)");
        return false;
    }
    
    public static boolean lineFormat(String token)
    {
        System.out.println("Entering function: lineFormat");
        outStream.println("Entering function: lineFormat");
        if (token.equals("line_format"))
            if ((token = ReadToken()).equals("is"))
                if (lineFormatType(token = ReadToken()))
                {
                    if(stmtTerminator(tempToken))
                    {
                        System.out.println("Leaving function: lineFormat (Success)");
                        outStream.println("Leaving function: lineFormat (Success)");
                        return true;
                    }
                }
        System.out.println("Leaving function: lineFormat (failed)");
        outStream.println("Leaving function: lineFormat (failed)");        
        return false;
    }
    
    public static boolean encodeFormat(String token)
    {
        System.out.println("Entering function: encodeFormat");
        outStream.println("Entering function: encodeFormat");
        if (token.equals("encode_format"))
            if((token = ReadToken()).equals("is"))
                if (encodeFormatType(token = ReadToken()))
                {
                    if(stmtTerminator(tempToken))
                    {
                        System.out.println("Leaving function: encodeFormat (Success)");
                        outStream.println("Leaving function: encodeFormat (Success)");
                        return true;
                    }
                }
        System.out.println("Leaving function: encodeFormat (failed)");
        outStream.println("Leaving function: encodeFormat (failed)");        
        return false;
    }
    
    public static boolean outputFile(String token)
    {
        System.out.println("Entering function: outputFile");
        outStream.println("Entering function: outputFile");
        if (name(token = ReadToken())){
            if((token = ReadToken()).charAt(0) == '.')
                if(name(token = ReadToken()))
                    if(stmtTerminator(token = ReadToken()))
                    {
                        System.out.println("Leaving function: outputFile (Success)");
                        outStream.println("Leaving function: outputFile (Success)");
                        return true;
                    }
                }
        System.out.println("Leaving function: outputFile (failed)");
        outStream.println("Leaving function: outputFile (failed)");
        return false;
    }
    
    public static boolean stmtTerminator(String token)
    {
        System.out.println("Entering function: stmtTerminator");
        outStream.println("Entering function: stmtTerminator");    
        if (token.charAt(0) == '!')
        {
            System.out.println("Leaving function: stmtTerminator (Success)");
            outStream.println("Leaving function: stmtTerminator (Success)");
            return true;
        }
        System.out.println("Leaving function: stmtTerminator (failed)");
        outStream.println("Leaving function: stmtTerminator (failed)");
        return false;
    }
    
    public static boolean lineFormatType(String token)
    {
        System.out.println("Entering function: lineFormatType");
        outStream.println("Entering function: lineFormatType");
        if (token.equals("string") || token.equals("number"))
        {
            if (lineFormatTypePrime(token = ReadToken()))
            {
                System.out.println("Leaving function: lineFormatType (Success)");
                outStream.println("Leaving function: lineFormatType (Success)");
                return true;
            }
        }
        System.out.println("Leaving function: lineFormatType (failed)");
        outStream.println("Leaving function: lineFormatType (failed)");
        return false;
    }
    
    
    public static boolean lineFormatTypePrime(String token)
    {
        System.out.println("Entering function: lineFormatTypePrime");
        outStream.println("Entering function: lineFormatTypePrime");
        tempToken = token;
        if (token.charAt(0) == ',')
        {
            token = ReadToken();
            if (token.equals("string") || token.equals("number"))
            {
                if(lineFormatTypePrime(token = ReadToken()))
                {
                    System.out.println("Leaving function: lineFormatTypePrime (Success)");
                    outStream.println("Leaving function: lineFormatTypePrime (Success)");
                    return true;
                }
            }
        }
        System.out.println("Leaving function: lineFormatTypePrime (failed)");
        outStream.println("Leaving function: lineFormatTypePrime (failed)");
        return true;
    }
    
    
    public static boolean encodeFormatType(String token)
    {
        System.out.println("Entering function: encodeFormatType");
        outStream.println("Entering function: encodeFormatType");
        if (token.equals("none"))
        {
            if (encodeFormatTypePrime(token = ReadToken()))
                {
                    System.out.println("Leaving function: encodeFormatType (Success)");
                    outStream.println("Leaving function: encodeFormatType (Success)");
                    return true;   
                }
        }
        
        if (token.charAt(0) == '+' || token.charAt(0) == '-' || token.charAt(0) == '*')
        {
            if (digits(token = ReadToken()))
            {
                if (encodeFormatTypePrime(token = ReadToken()))
                {
                    System.out.println("Leaving function: encodeFormatType (Success)");
                    outStream.println("Leaving function: encodeFormatType (Success)");
                    return true;
                }
            }
        }
        System.out.println("Leaving function: encodeFormatType (failed)");
        outStream.println("Leaving function: encodeFormatType (failed)");
        return false;
    }
    
    public static boolean encodeFormatTypePrime(String token)
    {
        System.out.println("Entering function: encodeFormatTypePrime");
        outStream.println("Entering function: encodeFormatTypePrime");
        tempToken = token;
        if (token.charAt(0) == ',')
            {
                token = ReadToken();
                if (token.charAt(0) == '+' || token.charAt(0) == '-' || token.charAt(0) == '*')
                {
                    if (digits(token = ReadToken()))
                    {
                        if (encodeFormatTypePrime(token = ReadToken()))
                        {
                            System.out.println("Leaving function: encodeFormatTypePrime (Success)");
                            outStream.println("Leaving function: encodeFormatTypePrime (Success)");
                            return true;
                        }
                    }
                }
            }
        System.out.println("Leaving function: encodeFormatTypePrime (failed)");
        outStream.println("Leaving function: encodeFormatTypePrime (failed)");
        return true;
    }
    
    public static boolean digits(String token)
    {
        System.out.println("Entering function: digits");
        outStream.println("Entering function: digits");
        int i = token.length();
        if (i == 1)
        {
            if (digit(token.charAt(0)))
            {
                System.out.println("Leaving function: digits (Success)");
                outStream.println("Leaving function: digits (Success)");
                return true;
            }
        }
        else
            {
                int x = 0;
                while (x < i)
                {
                    digit((token.charAt(x)));
                    x++;
                }
                System.out.println("Leaving function: digits (Success)");
                outStream.println("Leaving function: digits (Success)");
                return true;
            }
        System.out.println("Leaving function: digits (failed)");
        outStream.println("Leaving function: digits (failed)");
        return false;
    }
    
    public static boolean letter(char tokenChar)
    {
        System.out.println("Entering function: letter");
        outStream.println("Entering function: letter");
        if (Character.isAlphabetic(tokenChar))
        {
            System.out.println("Leaving function: letter (Success)");
            outStream.println("Leaving function: letter (Success)");
            return true;
        }
        else
        {
            System.out.println("Leaving function: letter (failed)");
            outStream.println("Leaving function: letter (failed)");
            return false;
        }
    }
    
    public static boolean digit(char tokenChar)
    {
        System.out.println("Entering function: digit");
        outStream.println("Entering function: digit");
        int tokenInt = Character.getNumericValue(tokenChar);
        if (tokenInt >= 0)
        {
            System.out.println("Leaving function: digit (Success)");
            outStream.println("Leaving function: digit (Success)");
            return true;
        }
        else
        {
            System.out.println("Leaving function: digit (failed)");
            outStream.println("Leaving function: digit (failed)");
            return false;
        }

    }
    
    
}
