/*******************************************
* Author: Mabry Cervin
* Date: 04/09/2016
* Purpose: Takes an integer between -999999
*   and 999999 as an argument and outputs 
*   the English natural language form
*******************************************/

class Converter {
  // Public dispatch method that checks for the case zero and otherwise calls intToStringRec
  public static String intToString(Integer input) {
    if (input.equals(0)) {
      return "zero";
    } else {
      return intToStringRec(input, new StringBuilder("")).toString();
    }
  }

  // Conversion table for digit to string
  private static String digitToString(int digit) {
    switch (digit) {
      case 1: return "one ";
      case 2: return "two ";
      case 3: return "three ";
      case 4: return "four ";
      case 5: return "five ";
      case 6: return "six ";
      case 7: return "seven ";
      case 8: return "eight ";
      case 9: return "nine ";
      default: return "";
    }
  }

  // Conversion table for digits to tens place name
  private static String tensToString(int tens) {
    switch (tens) {
      case 1: return "ten ";
      case 2: return "twenty ";
      case 3: return "thirty ";
      case 4: return "forty ";
      case 5: return "fifty ";
      case 6: return "sixty ";
      case 7: return "seventy ";
      case 8: return "eighty ";
      case 9: return "ninety ";
      default: return "";
    }
  }

  // Recursive method for printing out human readable integer string, uses StringBuilder
  // to prevent quadratic penalty when appending large strings
  private static StringBuilder intToStringRec(Integer input, StringBuilder string) {
    int thousands;
    int hundreds;
    int tens;

    // If the number to convert is over 1000, it gets split into thousands and hundreds
    // to reuse the same code for printing out hundreds
    if (input > 999) {
      thousands = input / 1000;
      hundreds = input % 1000;
      return string.append(intToStringRec(thousands, new StringBuilder(""))).append("thousand ").append(intToStringRec(hundreds, new StringBuilder("")));
    } else if (input > 99) {
      hundreds = input / 100;
      return string.append(digitToString(hundreds)).append("hundred ").append(intToStringRec(input % 100, new StringBuilder("")));
    } else if (input > 9) {
      tens = input / 10;
      return string.append(tensToString(tens)).append(intToStringRec(input % 10, new StringBuilder("")));
    } else if (input > 0) {
      return string.append(digitToString(input));
    } else {
      return string;
    }
  }
}


public class ConvertNumber {
  public static void main(String[] args) {
    int input;
    
    // Verify that an argument is given
    if (args.length == 0) {
      System.out.println("Program must have one argument that is an integer between -999999 and 999999");
      return;
    }

    // Verify that only one argument is given
    if (args.length > 1) {
      System.out.println("Program must have exactly one argument");
      return;
    }

    // Check that input can be properly parsed to a string
    try {
      input = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      System.out.println("Input must be an integer");
      return;
    }

    // Verify that input is within bounds given in requirements
    if (input > 999999 || input < -999999) {
      System.out.println("Input must be an integer between -999999 and 999999");
      return;
    }

    System.out.println(Converter.intToString(input));

  }
}

/*********************************************************************
* Assumptions:
*   Largest assumption that the program makes is that the requirements
*   are fairly fixed. The recursive implementation makes solving the
*   problem straightforward, and is reasonably performant for the 
*   required sizes of integer. If the requirements changed such that
*   it needed to be able to print the number of atoms in the 
*   universe, the call stack would almost certainly overflow. Changes
*   such as that to the requirements seem unlikely, however.
*
*   The program also assumes that it will not be run in a memory 
*   constrained environment. The intToStringRec() function allocates
*   a new StringBuilder object each step of the recursion as a 
*   tradeoff for cheaper string appends.
*
*   The final assumption is that the Converter class is not to be 
*   used separately. All of the error handling is done in the main
*   method for simplicity, so the Converter class is highly unsafe.
********************************************************************/
