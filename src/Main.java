import java.util.*;

public class Main {
    public static String calc(String input) {
        // Создаем карты для преобразования римских и арабских чисел
        Map<Character, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);

        Map<Integer, String> arabicNumerals = new HashMap<>();
        arabicNumerals.put(1, "I");
        arabicNumerals.put(2, "II");
        arabicNumerals.put(3, "III");
        arabicNumerals.put(4, "IV");
        arabicNumerals.put(5, "V");
        arabicNumerals.put(6, "VI");
        arabicNumerals.put(7, "VII");
        arabicNumerals.put(8, "VIII");
        arabicNumerals.put(9, "IX");
        arabicNumerals.put(10, "X");

        String[] parts = input.split(" ");
        if (parts.length != 3) {
            return "throws Exception";
        }

        try {
            String num1Str = parts[0];
            String operator = parts[1];
            String num2Str = parts[2];

            boolean isRoman1 = isRoman(num1Str);
            boolean isRoman2 = isRoman(num2Str);

            if (isRoman1 && isRoman2) {
                int num1 = convertRomanToArabic(num1Str, romanNumerals);
                int num2 = convertRomanToArabic(num2Str, romanNumerals);

                int result;
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        return convertArabicToRoman(result, arabicNumerals);
                    case "-":
                        result = num1 - num2;
                        return convertArabicToRoman(result, arabicNumerals);
                    case "*":
                        result = num1 * num2;
                        return convertArabicToRoman(result, arabicNumerals);
                    case "/":
                        if (num2 == 0) {
                            return "throws Exception";
                        }
                        result = num1 / num2;
                        return convertArabicToRoman(result, arabicNumerals);
                    default:
                        return "throws Exception";
                }
            } else if (!isRoman1 && !isRoman2) {
                int num1 = Integer.parseInt(num1Str);
                int num2 = Integer.parseInt(num2Str);

                int result;
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        return String.valueOf(result);
                    case "-":
                        result = num1 - num2;
                        return String.valueOf(result);
                    case "*":
                        result = num1 * num2;
                        return String.valueOf(result);
                    case "/":
                        if (num2 == 0) {
                            return "throws Exception";
                        }
                        result = num1 / num2;
                        return String.valueOf(result);
                    default:
                        return "throws Exception";
                }
            } else {
                return "throws Exception";
            }
        } catch (NumberFormatException e) {
            return "throws Exception";
        }
    }

    private static boolean isRoman(String input) {
        return input.matches("^[IVXLCDM]+$");
    }

    private static int convertRomanToArabic(String roman, Map<Character, Integer> romanNumerals) {
        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int curValue = romanNumerals.get(roman.charAt(i));
            if (curValue < prevValue) {
                result -= curValue;
            } else {
                result += curValue;
            }
            prevValue = curValue;
        }

        return result;
    }

    private static String convertArabicToRoman(int arabic, Map<Integer, String> arabicNumerals) {
        if (arabic <= 0) {
            return "throws Exception";
        }

        StringBuilder roman = new StringBuilder();

        while (arabic >= 10) {
            roman.append("X");
            arabic -= 10;
        }

        if (arabic >= 9) {
            roman.append("IX");
            arabic -= 9;
        }

        if (arabic >= 5) {
            roman.append("V");
            arabic -= 5;
        }

        if (arabic >= 4) {
            roman.append("IV");
            arabic -= 4;
        }

        while (arabic >= 1) {
            roman.append("I");
            arabic -= 1;
        }

        return roman.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите арифметическое выражение (например, 1 + 2):");

        String input = scanner.nextLine();
        String result = calc(input);

        System.out.println("Результат: " + result);
    } }