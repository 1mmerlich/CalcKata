package CalculatorCata;

import java.util.Scanner;
class Calc {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа в одном формате (римском или арабском): ");
        String expression = scanner.nextLine();
        System.out.println(calc(expression));
    }
    public static String calc(String expression) throws Exception {
        int number1;
        int number2;
        String operation;
        String conclusion;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Введите два числа");
        operation = checkedOperation(expression);
        if (operation == null) throw new Exception("Неподдерживаемая операция");
        if (Roman.isRomanByNumber(operands[0]) && Roman.isRomanByNumber(operands[1])) {
            number1 = Roman.changeToAnArabicNumber(operands[0]);
            number2 = Roman.changeToAnArabicNumber(operands[1]);
            isRoman = true;
        }
        else if (!Roman.isRomanByNumber(operands[0]) && !Roman.isRomanByNumber(operands[1])) {
            number1 = Integer.parseInt(operands[0]);
            number2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else {
            throw new Exception("Введите числа одного формата");
        }
        if (number1 > 10 || number2 > 10) {
            throw new Exception("Введите числы от 1 до 10");
        }
        int arabian = calc(number1, number2, operation);
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            conclusion = Roman.changeToAnRomanNumber(arabian);
        } else {
            conclusion = String.valueOf(arabian);
        }
        return conclusion;
    }
    static String checkedOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }
    static int calc(int a, int b, String operation) {

        if (operation.equals("+")) return a + b;
        else if (operation.equals("-")) return a - b;
        else if (operation.equals("*")) return a * b;
        else return a / b;
    }

}
class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};
    public static boolean isRomanByNumber(String value) {
        for (int i = 0; i < romanArray.length; i++) {
            if (value.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }
    public static int changeToAnArabicNumber(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }
    public static String changeToAnRomanNumber(int arabian) {
        return romanArray[arabian];
    }
}