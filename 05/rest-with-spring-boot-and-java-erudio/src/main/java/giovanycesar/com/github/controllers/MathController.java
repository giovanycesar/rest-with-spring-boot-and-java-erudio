package giovanycesar.com.github.controllers;

import giovanycesar.com.github.exception.UnsupportedMathOperationException;
import giovanycesar.com.github.math.SimpleMath;
import giovanycesar.com.github.request.converters.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private SimpleMath math = new SimpleMath();

    @RequestMapping("/sum/{number01}/{number02}")
    public Double sum(@PathVariable("number01") String number01, @PathVariable("number02") String number02) {
        if (!NumberConverter.isNumeric(number01) || !NumberConverter.isNumeric(number02)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }

        Double doubleNumber01 = NumberConverter.convertToDouble(number01);
        Double doubleNumber02 = NumberConverter.convertToDouble(number02);

        return math.sum(doubleNumber01, doubleNumber02);
    }

    @RequestMapping("/subtraction/{number01}/{number02}")
    public Double subtraction(@PathVariable("number01") String number01, @PathVariable("number02") String number02) {
        if (!NumberConverter.isNumeric(number01) || !NumberConverter.isNumeric(number02)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }

        Double doubleNumber01 = NumberConverter.convertToDouble(number01);
        Double doubleNumber02 = NumberConverter.convertToDouble(number02);

        return math.subtraction(doubleNumber01, doubleNumber02);
    }

    @RequestMapping("/multiplication/{number01}/{number02}")
    public Double multiplication(@PathVariable("number01") String number01, @PathVariable("number02") String number02) {
        if (!NumberConverter.isNumeric(number01) || !NumberConverter.isNumeric(number02)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }

        Double doubleNumber01 = NumberConverter.convertToDouble(number01);
        Double doubleNumber02 = NumberConverter.convertToDouble(number02);

        return math.multiplication(doubleNumber01, doubleNumber02);
    }

    @RequestMapping("/division/{number01}/{number02}")
    public Double division(@PathVariable("number01") String number01, @PathVariable("number02") String number02) {
        if (!NumberConverter.isNumeric(number01) || !NumberConverter.isNumeric(number02)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }

        Double doubleNumber01 = NumberConverter.convertToDouble(number01);
        Double doubleNumber02 = NumberConverter.convertToDouble(number02);

        return math.division(doubleNumber01, doubleNumber02);
    }

    @RequestMapping("/mean/{number01}/{number02}")
    public Double mean(@PathVariable("number01") String number01, @PathVariable("number02") String number02) {
        if (!NumberConverter.isNumeric(number01) || !NumberConverter.isNumeric(number02)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }

        Double doubleNumber01 = NumberConverter.convertToDouble(number01);
        Double doubleNumber02 = NumberConverter.convertToDouble(number02);

        return math.mean(doubleNumber01, doubleNumber02);
    }

    @RequestMapping("/squareroot/{number}")
    public Double squareroot(@PathVariable("number") String number) {
        if (!NumberConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }

        Double doubleNumber = NumberConverter.convertToDouble(number);

        return math.squareroot(doubleNumber);
    }

}
