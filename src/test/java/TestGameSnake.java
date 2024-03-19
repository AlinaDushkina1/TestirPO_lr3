import org.example.Game;
import org.example.MainWindow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс - тестирование игры Змейка
 */

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
        // Проверяем видимость окна
        assertEquals(true, window.getVisible());
    }
    @Test
    @DisplayName("тест на создание класса игры")
    void GameClassCreationTest() {
        Game game = new Game();
        assertNotNull(game);
    }
    @Test
    @DisplayName("тест-проверка добавления экземпляра класса Game в окно")
    public void addGameToAWindowTest() {
        MainWindow window=new MainWindow();
        // Проверяем, что в окне есть дочерний элемент Game
        assertEquals(1, window.getContentPane().getComponentCount());
        assertEquals(Game.class, window.getContentPane().getComponent(0).getClass());
    }
    @Test
    @DisplayName("Тест на проверку игровых параметров")
    public void gameParametersTest() {
        Game game = new Game();

        // Проверка значения константы SIZE
        assertEquals("Размер поля должен быть 320", 320, game.SIZE);

        // Проверка значения константы DOT_SIZE
        assertEquals("Размер ячейки змейки должен быть 16", 16, game.DOT_SIZE);

        // Проверка значения константы ALL_DOTS
        assertEquals("Максимальное кол-во ячеек должно быть 400", 400, game.ALL_DOTS);

        // Проверка инициализации массивов x и y
        assertTrue("Массив x должен быть инициализирован", game.x.length > 0);
        assertTrue("Массив y должен быть инициализирован", game.y.length > 0);

        // Проверка значения поля inGame
        assertTrue("Начальное состояние игры должно быть 'в игре'", game.inGame);
    }
}