

import java.util.Scanner;

public class EmailValidation {

    public static void main(String[] args) {
        Scanner Input = new Scanner(System.in);
        System.out.println("What is your email address? ");
        String email = Input.nextLine();
        System.out.println(isValidEmail(email)); // Display the final true/false result
        Input.close();
    }

    public static boolean isAlphanumeric(char x) {
        boolean isAlpha = Character.isLetterOrDigit(x); // Verifying if the character is a letter or digit.
        return isAlpha;
    }

    public static boolean isValidPrefixChar(char y) {
        boolean isValid = isAlphanumeric(y) || y == '_' || y == '.' || y == '-'; // Verify if prefix is Alphanumeric with the 3 exceptions.
        return isValid;

    }

    public static boolean isValidDomainChar(char z) {
        boolean isValidDomain = isAlphanumeric(z) || z == '.' || z == '-'; // Verify if the domain is Alphanumeric with the 2 exceptions.

        return isValidDomain;

    }

    public static boolean exactlyOneAt(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) { // Loop to see how many @ there are.
            if (s.charAt(i) == '@') {
                count++;
            }
        }
        if (count != 1) { // If the loop ends up with a result different to 1 it returns false.
            return false;
        }
        return true;
    }

    public static String getPrefix(String a) {
        if (exactlyOneAt(a)) {
            return (a.substring(0, a.indexOf('@'))); // Getting the string (username) before the @

        } else {
            return "Missing @ in the email";

        }
    }

    public static String getDomain(String b) {
        int aLength = b.length();
        int indexAt = b.indexOf("@") + 1; // Using the part after the @ to identify the domain.
        return (b.substring(indexAt, aLength));

    }

    public static boolean isValidPrefix(String d) {
        String prefix = getPrefix(d);
        for (int i = 0; i < prefix.length(); i++)
        {
            if (!(isValidPrefixChar(prefix.charAt(i)))) { // Verify each character of the prefix
                return false;
            }
        }
        if (prefix.contains("..") || prefix.contains("--")) { // If these 2 prefix string exceptions are met, exit the method and return false
            return false;
        }
        if (!isAlphanumeric(prefix.charAt(0)) || !isAlphanumeric(prefix.charAt(prefix.length() - 1))) {
            return false;
        }
        return true;
    }
    public static boolean isValidDomain(String e)
    {
        String domain = getDomain(e);
        for (int i = 0; i < domain.length(); i++) // Alphanumeric loop for the domain string
        {
            if (!isValidDomainChar(domain.charAt(i)))
            {
                return false;
            }
        }
        String lastFour = domain.substring(domain.length() - 4);
        if (!lastFour.contains(".")) // Verify if there's a '.' in the last 4 characters of the domain.
        {
            return false;
        }
        if (domain.contains("..") || domain.contains("--")) // If these 2 domain string exceptions are met, exit the method and return false
        {
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(String f) {

        if ((isValidDomain(f)) && (isValidPrefix(f)) && (exactlyOneAt(f))) { // Final validation, the 3 main methods must return true for the email to be valid.
            return true;
        }
        return false;
    }

}
