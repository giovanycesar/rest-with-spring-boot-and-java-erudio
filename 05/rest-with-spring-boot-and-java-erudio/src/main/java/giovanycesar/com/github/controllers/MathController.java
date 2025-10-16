package giovanycesar.com.github.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{number01}/{number02}")
    public Double sum(@PathVariable("number01") String number01, @PathVariable("number02") String number02) throws Exception {
        if (!isNumeric(number01) || !isNumeric(number02)) {
            throw new IllegalArgumentException();
        }
        return convertToDouble(number01) + convertToDouble(number02);
    }

    private Double convertToDouble(String strNumber) throws IllegalArgumentException {
        if (strNumber == null || strNumber.isEmpty()) {
            throw new IllegalArgumentException();
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
