import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestGameSnake {
    @Test
    @DisplayName("тест на создание окна приложения")
    void windowCreationTest() {
        MainWindow window = new MainWindow();
        Assertions.assertNotNull(window);
    }
}