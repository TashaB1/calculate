package com.tashad16a.gmail.calculate;

import java.util.Stack;
import java.util.StringTokenizer;

class GetExpressionOpz {
    private static String delimiters = "%^*/+-() ";

    public static boolean isDelimiter(String delim) {
        if (delimiters.indexOf(delim) != -1)
            return true;
        return false;
    }

    public static int priority(String oper) {
        if (oper.equals("(")) return 1;
        if (oper.equals("+") || oper.equals("-")) return 2;
        if (oper.equals("*") || oper.equals("/")) return 3;
        if (oper.equals("%")) return 4;
        if (oper.equals("^")) return 5;
        return 6;
    }

    public String ParseExpression(String infnot) {
        String postnot = new String();
        Stack<String> stack = new Stack<String>();
        StringTokenizer tokenizer = new StringTokenizer(infnot, delimiters, true); //разбиваем выражение на слова, разделители включаются в число слов

        String word = "";

        while (tokenizer.hasMoreTokens()) { //пока в строке еще есть слова
            word = tokenizer.nextToken(); //возвращает в виде строки следующее слово

            if (word.equals(" ")) continue;

            if (isDelimiter(word)) {  // если слово - разделитель
                if (word.equals("(")) stack.push(word);   // если скобка откр. то помещ ее в стек
                else if (word.equals(")")) {  // извлекаем символы из стека в выходную строку до тех пор, пока не встретим в стеке открывающую скобку
                    while (!stack.peek().equals("(")) {
                        postnot += stack.pop() + " ";   // для массива postnot.add((String) stack.pop());
                    }
                    stack.pop(); // уничтожили "("
                } else {
                    while (!stack.isEmpty() && (priority(word) <= priority(String.valueOf(stack.peek())))) { /* если символ на вершине стека имеет приоритет >= приоритету текущего символа
                                                                                                                то извлекаем символы в выходную строку до тех пор по вып-ся это условие*/
                        postnot += stack.pop() + " ";
                    }
                    stack.push(word);/* если стек пуст или находящиеся в нем символы(знаки операц и скобки) имеют меньший приоритет, чем приоритет текущего символа
                                        то помещаем текущий символ в стек */
                }
            } else {
                postnot += word + " ";
            }
        }
        while (!stack.isEmpty()) {  //если входная строка разобрана, а в стеке остаются знаки операций, извлекаем их в вых стр
            postnot += stack.pop() + " ";
        }
        return postnot;
    }
}
