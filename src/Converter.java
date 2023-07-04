import java.io.*;
import java.util.Scanner;
import java.util.Stack;

//test input 1. A+B*(C^D-E)  , 2. (A+B^C)*D+E^5
// my file location C:\\Users\\user\\Desktop\\file.txt

public class Converter {

    static int precedence(char c){  //making precedence
        switch (c){
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    static String infToPost(String expression){
        String output = new String("");
        //System.out.println(output);
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<expression.length(); i++){
            //System.out.println("inside forrrrrrrrrrrrrrrr");
            char ch = expression.charAt(i);

            if (Character.isLetterOrDigit(ch)){
                //System.out.println("first if ..................");
                output = output+ch;
            }
            else if (ch == '('){
                //System.out.println("second else ifffffff...................");
                stack.push(ch);
            }
            else if (ch == ')'){
                //System.out.println("3rd else if..................");
                while (stack.peek() != '(' && !stack.isEmpty()){
                    output = output + stack.pop();
                }
                stack.pop();
            }
            else {                                                                           //genjam.......................
                //System.out.println("inside elseeeeeeeeeeeeeeeeeee");
                while ( !stack.isEmpty() && precedence(ch) <= precedence(stack.peek())){
                    //output = output+stack.pop();
                    output = output+stack.pop();
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()){
            output = output + stack.pop();
        }

        return output;
    }

    static StringBuffer reverseString(String expression){
        String str = expression;
        StringBuffer str1 = new StringBuffer(str);
        str1.reverse();
        return str1;
    }

    static StringBuffer infToPre(String expression){

        StringBuffer revExp = reverseString(expression);
        String newRevExp = new String("");

        for (int i=0; i<revExp.length(); i++){            //bracket gula reverse hocchee.............................
            if (revExp.charAt(i) == '('){
                newRevExp+= ')';
            }
            else if (revExp.charAt(i) == ')'){
                newRevExp += '(';
            }
            else {
                newRevExp += revExp.charAt(i);
            }
        }

        String output = new String("");
        //System.out.println(output);
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<newRevExp.length(); i++){
            //System.out.println("inside forrrrrrrrrrrrrrrr");
            char ch = newRevExp.charAt(i);

            if (Character.isLetterOrDigit(ch)){
                //System.out.println("first if ..................");
                output = output+ch;
            }
            else if (ch == '('){
                //System.out.println("second else ifffffff...................");
                stack.push(ch);
            }
            else if (ch == ')'){
                //System.out.println("3rd else if..................");
                while (stack.peek() != '(' && !stack.isEmpty()){
                    output = output + stack.pop();
                }
                stack.pop();
            }
            else {                                                                           //genjam.......................
                //System.out.println("inside elseeeeeeeeeeeeeeeeeee");
                while ( !stack.isEmpty() && precedence(ch) <= precedence(stack.peek())){
                    //output = output+stack.pop();
                    output = output+stack.pop();
                }
                stack.push(ch);
            }

        }

        while (!stack.isEmpty()){
            output = output + stack.pop();
        }

        StringBuffer finalOutput = reverseString(output);        //abaaar reverse

        return finalOutput;

    }


    public static void main(String[] args) throws IOException {

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nFrom where do you want to take your input?");
            System.out.println("Enter a command \"console\", \"file\" or \"exit\" ");
            String command = sc.nextLine();

            if (command.equals("console") || command.equals("file") || command.equals("exit")) {
                if (command.equals("console")) {

                    //System.out.println("consoleeeee");
                    System.out.println("Do you want to convert the expression to postfix or prefix?");
                    System.out.println("Enter \"postfix\" or \"prefix\"   ");
                    command = sc.nextLine();

                    if (command.equals("postfix")){
                            System.out.println("Enter the infix expression: ");
                            String data1 = sc.nextLine();
                            System.out.println(infToPost(data1));
                    }
                    else if (command.equals("prefix")){
                        System.out.println("Enter the infix expression: ");
                        String data1 = sc.nextLine();
                        System.out.println(infToPre(data1));
                    }
                    else {
                        System.out.println("Invalid Command!!!");
                    }

                }

                else if (command.equals("file")) {
                    //System.out.println("fileeeeeeee");

                    System.out.println("Enter the file path: ");
                        String path = sc.nextLine();
                    //File file = new File("C:\\Users\\user\\Desktop\\file.txt");
                    File file = new File(path);
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String data = br.readLine();

                    System.out.println("Do you want to convert the expression to postfix or prefix?");
                    System.out.println("Enter \"postfix\" or \"prefix\"   ");
                    command = sc.nextLine();

                    if (command.equals("postfix")){
                        //System.out.println(infToPost(data));
                        String newData = infToPost(data);
                        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                        writer.write(String.valueOf(newData));
                        writer.close();
                        System.out.println("The output has been written in the same file!!!");
                    }
                    else if (command.equals("prefix")){
                        //System.out.println(infToPre(data));
                        StringBuffer newData = infToPre(data);
                        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                        writer.write(String.valueOf(newData));
                        writer.close();
                        System.out.println("The output has been written in the same file!!!");
                    }
                    else {
                        System.out.println("Invalid Command!!!");
                    }

                }

                else if (command.equals("exit")) {
                    System.out.println("EXITING...");
                    break;
                }
            }
            else {
                System.out.println("Enter a valid command");
            }
        }
    }
}
