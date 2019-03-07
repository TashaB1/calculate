package com.tashad16a.gmail.calculate;

import android.util.Log;

import java.util.Stack;
import java.util.StringTokenizer;

@ConstantName
class CalcExpression {
    public double CalcExpression(String postfixNotation) {
        Double secondNumberInStack, firstNumberInStack;
        String word;
        Stack<Double> stack = new Stack<Double>();
        StringTokenizer tokenizer = new StringTokenizer(postfixNotation, ConstantName.SPACE, false);

        while (tokenizer.hasMoreTokens()) {
            word = tokenizer.nextToken();

            try {
                switch (word) {
                    case ConstantName.ADDITION_OPERATION:
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case ConstantName.SUBSTRACTION_OPERATION:
                        firstNumberInStack = stack.pop();
                        secondNumberInStack = stack.pop();
                        stack.push(secondNumberInStack - firstNumberInStack);
                        break;
                    case ConstantName.MULTIPLICATION_OPERATION:
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case ConstantName.DIVISION_OPERATION:
                        firstNumberInStack = stack.pop();
                        secondNumberInStack = stack.pop();
                        stack.push(secondNumberInStack / firstNumberInStack);
                        break;
                    case ConstantName.PERCENT_OPERATION:
                        firstNumberInStack = stack.pop();
                        secondNumberInStack = stack.peek();
                        stack.push(secondNumberInStack * firstNumberInStack / 100);
                        break;
                    case ConstantName.INVOLUTION_OPERATION:
                        firstNumberInStack = stack.pop();
                        secondNumberInStack = stack.pop();
                        stack.push(Math.pow(secondNumberInStack, firstNumberInStack));
                        break;
                    default:
                        stack.push(Double.valueOf(word));
                }
            } catch (Exception e) {
                Log.w(e.getMessage(), e);
            }
        }

        return stack.pop();
    }
}