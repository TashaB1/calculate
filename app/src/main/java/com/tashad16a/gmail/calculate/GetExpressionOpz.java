package com.tashad16a.gmail.calculate;

import java.util.Stack;
import java.util.StringTokenizer;

@ConstantName
class GetExpressionOpz {

    public static boolean isDelimiter(String delimeters) {
        if (ConstantName.DELIMETERS.indexOf(delimeters) != -1) {
            return true;
        }

        return false;
    }

    public static int priority(String operation) {
        switch (operation) {
            case ConstantName.ROUND_BRACKET_LEFT:
                return 1;
            case ConstantName.ADDITION_OPERATION:
                return 2;
            case ConstantName.SUBSTRACTION_OPERATION:
                return 2;
            case ConstantName.MULTIPLICATION_OPERATION:
                return 3;
            case ConstantName.DIVISION_OPERATION:
                return 3;
            case ConstantName.PERCENT_OPERATION:
                return 4;
            case ConstantName.INVOLUTION_OPERATION:
                return 5;
            default:
                return 6;
        }
    }

    public String ParseExpression(String infixNotation) {
        String word;
        String postfixNotation;

        postfixNotation = new String();
        Stack<String> stack = new Stack<String>();
        StringTokenizer tokenizer = new StringTokenizer(infixNotation, ConstantName.DELIMETERS, true);

        while (tokenizer.hasMoreTokens()) {
            word = tokenizer.nextToken();

            if (word.equals(ConstantName.SPACE)) {
                continue;
            }

            if (isDelimiter(word)) {
                if (word.equals(ConstantName.ROUND_BRACKET_LEFT)) {
                    stack.push(word);
                } else if (word.equals(ConstantName.ROUND_BRACKET_RIGHT)) {
                    while (!stack.peek().equals(ConstantName.ROUND_BRACKET_LEFT)) {
                        postfixNotation += stack.pop() + ConstantName.SPACE;
                    }
                    stack.pop();
                } else {
                    while (!stack.isEmpty() && (priority(word) <= priority(String.valueOf(stack.peek())))) {
                        postfixNotation += stack.pop() + ConstantName.SPACE;
                    }
                    stack.push(word);
                }
            } else {
                postfixNotation += word + ConstantName.SPACE;
            }
        }

        while (!stack.isEmpty()) {
            postfixNotation += stack.pop() + ConstantName.SPACE;
        }

        return postfixNotation;
    }
}
