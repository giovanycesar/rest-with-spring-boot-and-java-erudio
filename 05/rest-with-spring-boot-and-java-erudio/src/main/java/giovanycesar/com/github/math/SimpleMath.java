package giovanycesar.com.github.math;

import giovanycesar.com.github.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class SimpleMath {

    public Double sum(Double number01, Double number02) {
        return number01 + number02;
    }

    public Double subtraction(Double number01, Double number02) {
        return number01 - number02;
    }

    public Double multiplication(Double number01, Double number02) {
        return number01 * number02;
    }

    public Double division(Double number01, Double number02) {
        return number01 / number02;
    }

    public Double mean(Double number01, Double number02) {
        return (number01 + number02) / 2;
    }

    public Double squareroot(Double number) {
        return Math.round(Math.sqrt(number) * 100.0) / 100.0;
    }
}
