package Utils;

import java.util.Scanner;

/**
 * @Author ziangliang
 * @Date 2022-03-14
 * @Version 1.0
 */
public class IOUtils {
    //define the static
    private static Scanner scanner = new Scanner(System.in);


    /**
     * Just read from the list 1:5
     * @return a number between 1 and 5
     */
    public static char readMenuSelection() {
        char c;
        while (true) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);//transfer from the string to char
            if (c != '1' && c != '2' &&
                    c != '3' && c != '4' && c != '5') {
                System.out.print("Invalid input, please enter again：");
            } else break;
        }
        return c;
    }

    /**
     * Function read a char from the scanner
     * @return One char
     */
    public static char readChar() {
        String str = readKeyBoard(1, false);
        return str.charAt(0);
    }
    /**
     * Function: read one char from the input
     * if pressing enter then return default value
     * if pressing other keys then output that char
     * @param defaultValue nominated default return value
     * @return defaultValue or the specified char
     */

    public static char readChar(char defaultValue) {
        String str = readKeyBoard(1, true);//要么是空字符串，要么是一个字符
        return (str.length() == 0) ? defaultValue : str.charAt(0);
    }

    /**
     * Function: read a number less and equal to two digits
     * @return integer
     */
    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, false);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, please re-enter：");
            }
        }
        return n;
    }

    /**
     * Function: read one integer from the input
     * if pressing enter then return default value
     * if pressing other keys then output that integer
     * @param defaultValue nominated default return value
     * @return defaultValue or the specified integer
     */
    public static int readInt(int defaultValue) {
        int n;
        while (true) {
            String str = readKeyBoard(10, true);
            if (str.equals("")) {
                return defaultValue;
            }

            //catch the input exception
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Wrong number format, please re-enter：");
            }
        }
        return n;
    }

    /**
     * Function: read a string with limitation from keyboard
     * @param limit limit length
     * @return String
     */

    public static String readString(int limit) {
        return readKeyBoard(limit, false);
    }

    /**
     * Function: read from the keyboard, return default value if pressing enter otherwise return input
     * @param limit limit length
     * @param defaultValue nominated default value
     * @return String
     */

    public static String readString(int limit, String defaultValue) {
        String str = readKeyBoard(limit, true);
        return str.equals("")? defaultValue : str;
    }


    /**
     * FUnction: read Y/N from the keyboard
     * @return Y or N
     */
    public static char readConfirmSelection() {
        System.out.print("Please Input your choice(Y/N): ");
        char c;
        while (true) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("Wrong format, please re-enter：");
            }
        }
        return c;
    }

    /**
     * Function: Input a string
     * @param limit The limit of input length
     * @param blankReturn If true: allow reading space
     *                    if false: not allow
     *
     *	If the input does not meet the requirement, then ask to re-enter
     * @return String
     */
    private static String readKeyBoard(int limit, boolean blankReturn) {

        //define the String
        String line = "";

        //scanner.hasNextLine() judge if the next line exist
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();//read next line

            //if line.length then the user press enter directly
            if (line.length() == 0) {
                if (blankReturn) return line;//Just return the empty
                else continue; //Re-roll the process
            }

            //capture the length exception
            if (line.length() < 1 || line.length() > limit) {
                System.out.print("Input Length（can not be greater than " + limit + ")，re-enter please：");
                continue;
            }
            break;
        }

        return line;
    }

    /**
     * Function: Input a string, information with a certain format
     * @param info the String contains info
     */

    public static void printFormattedInfo(String info){
        //The length of the title could not be longer than 30
        String s1 = "";
        String s2 = "";
        int spaceSize = (40-info.length())/2;
        for(int i=0; i<25;i++){
            s1+='-';
            if(i<spaceSize){
                s2+=' ';
            }
        }
        System.out.println(s1+s2+info+s2+s1);
    }

    /**
     * Function: Input a string, information with a certain format
     * @param title the String contains info
     */

    public static void printFormattedTitle(String title){
        //The length of the title could not be longer than 30
        String s1 = "";
        String s2 = "";
        int spaceSize = (40-title.length())/2;
        for(int i=0; i<25;i++){
            s1+='=';
            if(i<spaceSize){
                s2+=' ';
            }
        }
        System.out.println(s1+s2+title+s2+s1);
    }
}
