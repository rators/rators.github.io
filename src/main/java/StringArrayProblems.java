import java.util.Arrays;

/**
 */
public class StringArrayProblems {
    public static void main(String[] args) {
        char[] input = {'M', 'r', ' ', 'J', 'o', 'h', 'n', ' ', 'S', 'm', 'i', 't', 'h', ' ', ' ', ' ', ' '};
        System.out.println(Arrays.toString(webSpace(input)));
        System.out.println(compressString("aabcccccaaa"));
    }

    static char[] webSpace(char[] input){
        int outterBound = getOutterBound(input);
        for(int i = 0; i < input.length; i ++){
            char currentChar = input[i];
            if(currentChar == ' '){
                shiftOver(outterBound, i , input);
                input[i] = '%';
                input[i + 1] = '2';
                input[i + 2] = '0';
                i += 2;
                outterBound += 2;
            }
        }
        return input;
    }

    private static void shiftOver(int upperBound, int start, char[] input){
        for(int i = upperBound - 1; i > start; i --){
            input[i + 2] = input[i];
        }
    }

    private static int getOutterBound(char[] input){
        for(int i = input.length - 1; i > -1; i --){
            char currentChar = input[i];
            if(currentChar != ' ') {
                System.out.println(i + 1);
                return i + 1;
            }
        }
        return -1;
    }

    private static String compressString(String input){
        if(input.length() == 0) return input;
        StringBuilder builder = new StringBuilder();

        int count = 1;
        char countChar = input.charAt(0);

        for(int i = 1; i < input.length(); i ++){ // this will miss the count for the last char
            System.out.println(i);
            char currChar = input.charAt(i);
            if(currChar != countChar){
                builder.append(countChar).append(count);
                countChar = currChar;
                count = 1;
            } else {
                count ++;
            }
        }
        // get the count for the last repetition.
        builder.append(countChar).append(count);

        String compressedString = builder.toString();
        return compressedString.length() < input.length() ? compressedString : input;
    }

    private static boolean isRotation(String toCheck, String bench){
        int benchLength = bench.length();
        if(benchLength == toCheck.length() && benchLength > 0){
            StringBuilder builder = new StringBuilder();
            String benchX2 = builder.append(bench).append(bench).toString();
            return isSubstring(toCheck, benchX2);
        }
        return false;
    }

    private static boolean isSubstring(String toCheck, String bench){
        return bench.contains(toCheck);
    }


}
