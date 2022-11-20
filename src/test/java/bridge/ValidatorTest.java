package bridge;

import bridge.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ValidatorTest {

    private Validator validator;

    @BeforeEach
    void init() {
        this.validator = new Validator();
    }

    @DisplayName("입력값이 숫자가 아니면 예외가 발생한다.")
    @Test
    void isNumericTest() {
        assertThatThrownBy(() -> validator.isNumeric("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력값이 3보다 작거나 20보다 크면 예외가 발생한다.")
    @Test
    void IsCorrectBoundaryTest() {
        assertAll(
                () -> assertThatThrownBy(() -> validator.isCorrectBoundary("2"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR]"),

                () -> assertThatThrownBy(() -> validator.isCorrectBoundary("21"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR]")
        );
    }

    @DisplayName("입력값이 U나 D가 아니면 예외가 발생한다.")
    @Test
    void checkDirectionTest() {
        assertThatThrownBy(() -> validator.checkDirection("UP"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("입력값이 Q나 R이 아니면 예외가 발생한다.")
    @Test
    void checkCommandTest() {
        assertThatThrownBy(() -> validator.checkCommand("QUIT"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
