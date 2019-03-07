package com.tashad16a.gmail.calculate;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        ConstantName.OPERATIONS,
        ConstantName.DELIMETERS,
        ConstantName.SUBSTRACTION_OPERATION,
        ConstantName.ADDITION_OPERATION,
        ConstantName.DIVISION_OPERATION,
        ConstantName.MULTIPLICATION_OPERATION,
        ConstantName.PERCENT_OPERATION,
        ConstantName.INVOLUTION_OPERATION,
        ConstantName.POINT,
        ConstantName.ROUND_BRACKET_LEFT,
        ConstantName.ROUND_BRACKET_RIGHT,
        ConstantName.ZERO_DIGIT,
        ConstantName.ONE_DIGIT,
        ConstantName.TWO_DIGIT,
        ConstantName.THREE_DIGIT,
        ConstantName.FOUR_DIGIT,
        ConstantName.FIVE_DIGIT,
        ConstantName.SIX_DIGIT,
        ConstantName.SEVEN_DIGIT,
        ConstantName.EIGHT_DIGIT,
        ConstantName.NINE_DIGIT,
        ConstantName.SPACE,
        ConstantName.ERROR_DIVISION_BY_ZERO
})
public @interface ConstantName {

    public static final String OPERATIONS = "%^*/+-";
    public static final String DELIMETERS = "%^*/+-() ";
    public static final String SUBSTRACTION_OPERATION = "-";
    public static final String ADDITION_OPERATION = "+";
    public static final String DIVISION_OPERATION = "/";
    public static final String MULTIPLICATION_OPERATION = "*";
    public static final String PERCENT_OPERATION = "%";
    public static final String INVOLUTION_OPERATION = "^";
    public static final String POINT = ".";
    public static final String ROUND_BRACKET_LEFT = "(";
    public static final String ROUND_BRACKET_RIGHT = ")";
    public static final String ZERO_DIGIT = "0";
    public static final String ONE_DIGIT = "1";
    public static final String TWO_DIGIT = "2";
    public static final String THREE_DIGIT = "3";
    public static final String FOUR_DIGIT = "4";
    public static final String FIVE_DIGIT = "5";
    public static final String SIX_DIGIT = "6";
    public static final String SEVEN_DIGIT = "7";
    public static final String EIGHT_DIGIT = "8";
    public static final String NINE_DIGIT = "9";
    public static final String SPACE = " ";
    public static final String ERROR_DIVISION_BY_ZERO = "Деление на ноль невозможно";
}

