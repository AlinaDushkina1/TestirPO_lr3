import org.example.MainWindow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestGameSnake {
    @Test
    @DisplayName("тест на создание класса окна приложения")
    void windowClassCreationTest() {
        MainWindow window = new MainWindow();
        assertNotNull(window);
    }
    @Test
    @DisplayName("тест на создание окна приложения JFrame")
    void windowCreationTest() {
        MainWindow window = new MainWindow();
        // Проверяем, что объект JFrame был создан
        assertNotNull(window);

        // Проверяем значение title
        assertEquals("Змейка", window.getTitle());

        // Проверяем операцию закрытия окна
        assertEquals(JFrame.EXIT_ON_CLOSE, window.getDefaultCloseOperation());

        // Проверяем размер окна
        assertEquals(320, window.getWidth());
        assertEquals(345, window.getHeight());

        // Проверяем расположение окна
        assertEquals(400, window.getX());
        assertEquals(400, window.getY());
    }
}