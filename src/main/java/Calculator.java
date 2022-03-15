import exception.NegativeNumberException;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public int sum(String expression) {
        if ("".equals(expression)) {
            return 0;
        }

        String delimiter = String.valueOf(',');

        if (expression.startsWith("//[")) {
            delimiter = expression.substring(3, expression.indexOf(']'));
            expression = expression.substring(expression.indexOf('\n') + 1);
        } else if (expression.startsWith("//")) {
            delimiter = String.valueOf(expression.charAt(2));
            expression = expression.substring(4);
        }

        if (delimiter.contains("*")) {
            delimiter = delimiter.replace("*", "\\*");
        }
        String oneDelimiterExpression = expression.replace("\n", delimiter);
        validate(oneDelimiterExpression);
        String[] numbers = oneDelimiterExpression.split(delimiter);
        validateNegativeNumbers(numbers);
        return calculateResult(numbers);
    }

    private void validateNegativeNumbers(String[] numbers) {
        List<Integer> negativeNumbers = new ArrayList<>();
        for (String number : numbers) {
            int value = Integer.parseInt(number);
            if (value < 0 ) {
                negativeNumbers.add(value);
            }
        }
        if (!negativeNumbers.isEmpty()) {
            throw new NegativeNumberException(negativeNumbers.toString());
        }
    }

    private int calculateResult(String[] array) {
        int result = 0;
        for (String s : array) {
            result = result + Integer.parseInt(s);
        }
        return result;
    }

    private void validate(String expression) {
        if (expression.contains(",,")) {
            throw new UnsupportedOperationException();
        }
    }
}
