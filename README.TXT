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

NOTE: This program was created for my Programming Languages Course during my Spring Semester of 2023.

A simple tokenizer for a very simple grammar.
