package giovanycesar.com.github.controllers;

import giovanycesar.com.github.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{number01}/{number02}")
    public Double sum(@PathVariable("number01") String number01, @PathVariable("number02") String number02) {
        if (!isNumeric(number01) || !isNumeric(number02)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }
        return convertToDouble(number01) + convertToDouble(number02);
    }

    @RequestMapping("/subtraction/{number01}/{number02}")
    public Double subtraction(@PathVariable("number01") String number01, @PathVariable("number02") String number02) {
        if (!isNumeric(number01) || !isNumeric(number02)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }

        if (convertToDouble(number01) > convertToDouble(number02)) {
            return convertToDouble(number01) - convertToDouble(number02);
        } else {
            return convertToDouble(number02) - convertToDouble(number01);
        }
    }

    @RequestMapping("/multiplication/{number01}/{number02}")
    public Double multiplication(@PathVariable("number01") String number01, @PathVariable("number02") String number02) {
        if (!isNumeric(number01) || !isNumeric(number02)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }
        return convertToDouble(number01) * convertToDouble(number02);
    }

    @RequestMapping("/division/{number01}/{number02}")
    public Double division(@PathVariable("number01") String number01, @PathVariable("number02") String number02) {
        if (!isNumeric(number01) || !isNumeric(number02)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }

        if (convertToDouble(number01) > convertToDouble(number02)) {
            return convertToDouble(number01) / convertToDouble(number02);
        } else {
            return convertToDouble(number02) / convertToDouble(number01);
        }
    }

    @RequestMapping("/mean/{number01}/{number02}")
    public Double mean(@PathVariable("number01") String number01, @PathVariable("number02") String number02) {
        if (!isNumeric(number01) || !isNumeric(number02)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }
        return (convertToDouble(number01) + convertToDouble(number02)) / 2;
    }

    @RequestMapping("/squareRoot/{number}")
    public Double squareRoot(@PathVariable("number") String number) throws Exception {
        if (!isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }
        return Math.round(Math.sqrt(convertToDouble(number)) * 100.0) / 100.0;
    }


    private Double convertToDouble(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) {
            return false;
        }
        String number = strNumber.replace(",", ".");

        return (number.matches("[-+]?[0-9]*\\.?[0-9]+"));
    }
}
