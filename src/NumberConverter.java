import java.util.Arrays;
public class NumberConverter {
    int[] digits;
    int base;
    int number;

    public NumberConverter(int number, int base) {
        this.number = number;
        this.base = base;
        String numberAsString = Integer.toString(number);
        digits = new int[numberAsString.length()];
        for (int i = 0; i < numberAsString.length(); i++) {
            String single = numberAsString.substring(i, i + 1);
            int d = Integer.parseInt(single);
            digits[i] = d;
        }
        if ((base < 2 || base > 10) && base != 16) {
            throw new IllegalArgumentException("New base must be from 2 - 10 or 16");
        }
    }

    public String displayOriginalNumber() {
        StringBuilder o = new StringBuilder();
        if (base == 8) {
            o.append(Integer.toOctalString(number));
        } else {
            for (int i = 0; i < digits.length; i++) {
                o.append(digits[i]);
            }
        }
        o.append("\n");
        return o.toString();
    }

    public int[] getDigits() {
        return digits;
    }

    public int[] convertToDecimal() {
        if (base == 10){
            number = Integer.valueOf(number);
        }
        if (base == 2){
            int decValue = 0;
            base = 1;

            int temp = number;
            while (temp > 0) {
                int last_digit = temp % 10;
                temp = temp / 10;
                decValue += last_digit * base;
                base = base * 2;
                int[] old = new int[1];
                old[0] = decValue;
                digits = old;
            }
            return digits;
        }
        if (base == 8){
            int decValue = 0;
            base = 1;
            int temp = number;
            while (temp > 0) {
                int last_digit = temp % 10;
                temp = temp / 10;
                decValue += last_digit * base;
                base = base * 8;
                int[] old = new int[1];
                old[0] = decValue;
                digits = old;
            }
        }
        return digits;
    }
    public int[] convertToBinary() {
        if (base == 2) {
            number = Integer.valueOf(number);
        }
        StringBuilder result = new StringBuilder();
        if (base == 10) {
            // Conversion from decimal to binary
            int temp = number;
            while (temp > 0) {
                result.insert(0, temp % 2);
                temp = temp / 2;
            }
        } else if (base == 8) {
            // Conversion from octal to binary
            int temp = number;
            while (temp > 0) {
                int n = temp % 10;
                result.insert(0, String.format("%03d", Integer.parseInt(Integer.toBinaryString(n))));
                temp = temp / 10;
            }
        }
        int[] binaryArray = new int[result.length()];
        for (int i = 0; i < result.length(); i++) {
            binaryArray[i] = Integer.parseInt(result.substring(i, i + 1));
        }
        return binaryArray;
    }
    public int[] convertToOctal() {
        if (base == 8){
            number = Integer.valueOf(number);
        }
        if (base == 10) {
            StringBuilder result = new StringBuilder();
            int temp = number;
            while (temp > 0) {
                result.insert(0, temp % 8);
                temp /= 8;
            }
            return Arrays.stream(result.toString().split("")).mapToInt(Integer::parseInt).toArray();
        }
        if (base == 2) {
            int decValue = 0;
            int i = 0;
            while (number > 0) {
                decValue += Math.pow(2, i++) * (number % 10);
                number /= 10;
            }
            int[] old = new int[1];
            old[0] = decValue;
            digits = old;
        }
        return digits;
    }
}

