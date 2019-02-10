package com.tashad16a.gmail.calculate;

import java.util.Stack;
import java.util.StringTokenizer;

class CalcExpression {
    public double CalcExpression(String postnot) {
        Stack<Double> stack = new Stack<Double>();
        String word = "";
        StringTokenizer tokenizer = new StringTokenizer(postnot, " ", false); //разбиваем выражение на слова, разделители включаются в число слов

        while (tokenizer.hasMoreTokens()) { //пока в строке еще есть слова
            word = tokenizer.nextToken(); //возвращает в виде строки следующее слово
            try {
                if (word.equals("+")) stack.push(stack.pop() + stack.pop());
                else if (word.equals("-")) {
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(a - b);
                } else if (word.equals("*")) stack.push(stack.pop() * stack.pop());
                else if (word.equals("/")) {
                    Double b = stack.pop(), a = stack.pop();
                    Double c = a / b;
                    stack.push(c);
                } else if (word.equals("%")) {
                    Double b = stack.pop(), a = stack.peek();
                    stack.push(a * b / 100);
                } else if (word.equals("^")) {
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(Math.pow(a, b));
                } else stack.push(Double.valueOf(word));
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        return stack.pop();
    }
}