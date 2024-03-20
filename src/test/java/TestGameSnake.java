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
        assertEquals( 320, game.getSIZE());

        // Проверка значения константы DOT_SIZE
        assertEquals( 16, game.getDOT_SIZE());

        // Проверка значения константы ALL_DOTS
        assertEquals(400, game.getALL_DOTS());

        // Проверка инициализации массивов x и y
        assertTrue(game.getX()>0);
        assertTrue(game.getY()>0);

        // Проверка значения поля inGame
        assertTrue(game.isInGame());
        // Проверка значениq полей направлений
        assertFalse(game.isLeft());
        assertTrue(game.isRight());
        assertFalse(game.isDown());
        assertFalse(game.isUp());
    }

    @Test
    @DisplayName("Тест на проверку метода загрузки изображений")
    public void loadImagesTest() {
        Game game = new Game();
        game.loadImages();

        assertNotNull(game.getApple());
        assertNotNull(game.getDot());
    }

    @Test
    @DisplayName("Тест на проверку инициализации игры")
    public void initGameTest() {
        Game game = new Game();
        game.initGame();

        // Проверка значений переменных dots, x и y
        assertEquals("Исходное количество точек должно быть 3", 3, game.dots);
        for (int i = 0; i < game.dots; i++) {
            assertEquals("Позиция x[" + i + "] должна быть вычислена верно", 48 - i * game.DOT_SIZE, game.x[i]);
            assertEquals("Позиция y[" + i + "] должна быть равна 48", 48, game.y[i]);
        }

        // Проверка корректности настройки таймера
        assertNotNull("Таймер должен быть инициализирован", game.timer);
        assertTrue("Таймер должен быть запущен", game.timer.isRunning());
        assertEquals("Частота таймера должна быть 250 миллисекунд", 250, game.timer.getDelay());
    }
}