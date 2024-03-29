
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/*
 * Programmer Name: Nick Abegg
 * Date Created: 4/9/2023
 * Last Modified: 4/25/2023
 * 
 * Overview: This program takes in toxens from our lexical analyzer assignment output file and parses the program
 *           to see if it is syntatically correct.
 *           The program will ask you to enter the name of the file that contains the tokens. Ensure that the token file
 *           is located in the same folder as the program. 
 *           The input file should be a list of the tokens with a newline between each individual token.
 *           The program will output if the input tokens are successful or not and report where a failure/error has occured (if any).
 *           
 *           Note: The ENCODE_FORMAT_TYPE and LINE_FORMAT_TYPE grammar rules were transformed so as to fix the disjointment and ensure
 *                 that the program is appropriate format for a recursive decent parser.
 *                 
 *                 Also, the grammar rules ENCODE_FILE, NAME, and OUTPUT_FILE have been updated on 4/24 to reflect an alteration to the grammar
 *                 rules. This is done to simplify the original grammar rule.
 * 
 */

public class Prog3NA
{
    // create input and output stream
    private static PrintWriter outStream = null;
    private static Scanner inStream = null;
    
    private static String tempToken;
    
    /*
     * Function Name: main
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: 
     * 
     * Returns: nothing
     * Example: main()
     * Description: This function is the main function of the program. It will ask the user to input the name of the file
     *              that contians the tokens. Then will call the Start grammar rule which will then parse through the entire input file of code to check
     *              if it is syntatically correct.
     */
    public static void main(String[] args)    
    {
        // sets up input stream and gets the users input and output file names
        Scanner input = new Scanner(System.in); 
        System.out.print("Enter the name of the file that contains the tokens: ");
        String fileName = input.nextLine();
        
        System.out.print("Enter the name of the output file: ");
        String outName = input.nextLine();
        
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
          outStream = new PrintWriter(outName);     
        } // end try
        catch(FileNotFoundException e)
        {
            System.out.println("error opening the file" + outName);
            System.exit(0);
        } // end catch
        
        System.out.println("Checking " + fileName + " now!");
        System.out.println("Enter any value to continue: ");
        
        input.nextLine();
        
        // reads the first token and calls the START grammar rule
        token = ReadToken();
        
        // if start returns true, then the program is legal as all the subsequent rules returned true as well.
        if (Start(token))
            System.out.println("Successful Program! The program is legal!");
        else
            System.out.println("Unsuccessful parse attempt. The input file " + fileName + " did not parse successfully and is not a legal program.");
        
    // then close the files
    outStream.close();
    inStream.close();
        
    }
    
    /*
     * Function Name: ReadToken
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: none
     * 
     * Returns: the next line or token in the input file
     * Example: ReadToken()
     * Description: Reads in the next line in the input file and returns that line as a string.
     */
    public static String ReadToken()
    {
        return inStream.nextLine();     // simply returns the next line in the input stream
    }
    
    /*
     * Function Name: Start
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: a bool value of true or false
     * Example: Start(token)
     * Description: This function represents the START grammar rule of the language. Calls the necessary rules and returns true if
     *              the code is syntactically correct and false if not.
     */
    public static boolean Start(String token)
    {
        System.out.println("Entering function: Start");
        outStream.println("Entering function: Start");
        
        if (encodeFile(token))
            if (lineFormat(token = ReadToken()))            // here i am calling a grammar rule and at the same time reading in the next token
                if (encodeFormat(token = ReadToken()))      // this is done throughout the rest of the program
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
    
    /*
     * Function Name: encodeFile
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/24/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: returns a true or false bool value
     * Example: encodeFile(token)
     * Description: This function represents the ENCODE_FILE grammar rule of the language. Calls the necessary rules and returns true if
     *              the code is syntactically correct and false if not.
     */
    public static boolean encodeFile(String token)
    {
        System.out.println("Entering function: encodeFile");
        outStream.println("Entering function: encodeFile");
        
        if ((token.equals("encode_file")))
            if(name(token = ReadToken()))
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
    
    /*
     * Function Name: name
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/25/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: returns a true or false bool value
     * Example: name(token)
     * Description: this function checks to see if the given token is a valid value as described in the grammar.
     *              Specifically, it checks to see if the given token has at least one letter and then any number of digits or letters after.
     *              
     *              Note: Also checks for ' ' or '.' as a result of a alteration to the grammar rule on 4/24
     */
    public static boolean name(String token)
    {
        System.out.println("Entering function: name");
        outStream.println("Entering function: name");
        int i = token.length();
        
        if (i == 1)     // if just 1 we don't need a loop
        {
            if (letter(token.charAt(0)))
            {
                System.out.println("Leaving function: name (Success)");
                outStream.println("Leaving function: name (Success)");
                return true;
            }
        }
        else
            {   // multiple characters in the name. Checks for both letter and digit as described by grammar rule
                int x = 0;
                if (letter(token.charAt(x)))
                {   
                    while (x < i)
                    {
                        if (!(token.charAt(x) == ' ' || token.charAt(x) == '.' || letter(token.charAt(x)) || digit(token.charAt(x)))) //ensures at least one of these
                            return false;                                                                                             //is true
                        x++;    // if at least one is true goes on to the next char
                    }
                }
                
                System.out.println("Leaving function: name (Success)");
                outStream.println("Leaving function: name (Success)");
                return true;
            }
            
        System.out.println("Leaving function: name (failed)");
        outStream.println("Leaving function: name (failed)");
        return false;
    }
    
    /*
     * Function Name: lineFormat
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: returns either a true or false bool value
     * Example: lineFormat(token)
     * Description: this is the function for the LINE_FORMAT grammar rule. Calls the necessary functions to check if the
     *              language has the proper tokens for the LINE_FORMAT grammar rule.
     */
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
    
    /*
     * Function Name: encodeFormat
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: returns a true or false bool value
     * Example: encodeFormat(token)
     * Description: This function checks the ENCODE_FORMAT grammar rule and calls the necessary functions to check if the language is
     *              syntatically correct in respect to the grammar rule.
     */
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
    
    /*
     * Function Name: outputFile
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/24/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: a true or false bool value
     * Example: outputFile(token)
     * Description: This function checks for the OUTPUT_FILE in the input code. Calls necessary functions to see if the OUTPUT_FILE grammar rule is
     *              correct.
     */
    public static boolean outputFile(String token)
    {
        
        System.out.println("Entering function: outputFile");
        outStream.println("Entering function: outputFile");
        
        if (token.equals("output_file"))        //checks for literal string as described in grammar rule
            if (name(token = ReadToken()))
                if(stmtTerminator(token = ReadToken()))
                {
                    System.out.println("Leaving function: outputFile (Success)");
                    outStream.println("Leaving function: outputFile (Success)");
                    return true;
                }
                
        System.out.println("Leaving function: outputFile (failed)");
        outStream.println("Leaving function: outputFile (failed)");
        return false;
    }
    
    /*
     * Function Name: stmtTerminator
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: return a true or false bool value
     * Example: stmtTerminator(token)
     * Description: Checks to see if current token is equal to the languages statement terminator. Ensures the language has correct syntax for the STMT_TERMINATOR
     *              grammar rule.
     */
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
    
    /*
     * Function Name: lineFormatType
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: retunrs either a true or false bool value
     * Example: lineFormatType(token)
     * Description: checks the LINE_FORMAT_TYPE grammar rule. Checks first for literal "string" or "number" then calls lineFormatTypePrime 
     *              to check if there are more literal "string" or "number" tokens.
     */
    public static boolean lineFormatType(String token)
    {
        System.out.println("Entering function: lineFormatType");
        outStream.println("Entering function: lineFormatType");
        
        if (token.equals("string") || token.equals("number"))
        {
            if (lineFormatTypePrime(token = ReadToken()))           // calls prime to fix the disjointness of original grammar rule
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
    
    
    /*
     * Function Name: lineFormatTypePrime
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: either a true or false bool value
     * Example: lineFormatTypePrime(token)
     * Description: Used to fix the mutually disjoint. Checks to see for a comma and then a literal "string" or "number" string for any number
     *              of times.
     */
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
    
    
    /*
     * Function Name: encodeFormatType
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: either a true or false bool value
     * Example: encodeFormatType(token)
     * Description: checks for literal "none" string or an op char followed by a number and then calls encodeFormatTypePrime
     *              to fix the mutual disjointment of the grammar rule. 
     *              
     */
    public static boolean encodeFormatType(String token)
    {
        System.out.println("Entering function: encodeFormatType");
        outStream.println("Entering function: encodeFormatType");
        
        if (token.equals("none"))   // checks for just literal 'none'
        {
            if (encodeFormatTypePrime(token = ReadToken()))
                {
                    System.out.println("Leaving function: encodeFormatType (Success)");
                    outStream.println("Leaving function: encodeFormatType (Success)");
                    return true;   
                }
        }
        
        // checks for ops and digit and calls prime again to check for more
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
    
    /*
     * Function Name: encodeFormatTypePrime
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: returns either a true or false bool value
     * Example: encodeFormatTypePrime(token)
     * Description: Used to fix the mutual disjointment for the original grammar rule. Checks for any number of ',' followed by 
     *              a literal "none" string or an op char followed by a number.
     */
    public static boolean encodeFormatTypePrime(String token)
    {
        System.out.println("Entering function: encodeFormatTypePrime");
        outStream.println("Entering function: encodeFormatTypePrime");
        tempToken = token;
        
        // checks for literal ','
        if (token.charAt(0) == ',')
            {
                
                token = ReadToken();
                if (token.equals("none"))
                {
                    if (encodeFormatTypePrime(token = ReadToken()))     // calls again to see if there are more and fix disjointness
                    {
                            System.out.println("Leaving function: encodeFormatTypePrime (Success)");
                            outStream.println("Leaving function: encodeFormatTypePrime (Success)");
                            return true;                        
                    }
                }
                
                // checks for ops and digits following. then calls prime again to check for more.
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
    
    /*
     * Function Name:
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: a true or false bool value
     * Example: digits(token)
     * Description: checks to see if the token is at least one digit long for the DIGITS grammar rule.
     */
    public static boolean digits(String token)
    {
        System.out.println("Entering function: digits");
        outStream.println("Entering function: digits");
        
        int i = token.length(); // if just 1 no need for loop
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
                // more than 1 so will loop through all digits in the number
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
    
    /*
     * Function Name:
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/23/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: either a true or false bool value
     * Example: letter(token)
     * Description: Checks if the current token is a valid character that are defined in the LETTER grammar rule.
     */
    public static boolean letter(char tokenChar)
    {
        System.out.println("Entering function: letter");
        outStream.println("Entering function: letter");
        
        // checks if a valid letter in the alphabet as described in the grammar rule
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
    
    /*
     * Function Name:
     * Programmer: Nick Abegg
     * Data Created: 4/9/2023
     * Last Modified: 4/25/2023
     * 
     * Arguments: token - a string that contains the current token
     * 
     * Returns: a true or false bool value
     * Example: digit(token)
     * Description: checks to see if the given token is a valid integer as defined in the languages DIGIT grammar rule.
     */
    public static boolean digit(char tokenChar)
    {
        System.out.println("Entering function: digit");
        outStream.println("Entering function: digit");
        
        if (tokenChar >= '0' && tokenChar <= '9')
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
