import org.example.Game;
import org.example.MainWindow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.event.ActionEvent;

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
        assertTrue(game.getXlength()>0);
        assertTrue(game.getYlength()>0);

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
        assertEquals( 3, game.getDots());
        for (int i = 0; i < game.getDots(); i++) {
            assertEquals( 48 - i * game.getDOT_SIZE(), game.getX(i));
            assertEquals(48, game.getY(i));
        }

        // Проверка корректности настройки таймера
        assertNotNull(game.getTimer());//Таймер должен быть инициализирован
        assertTrue(game.getTimer().isRunning());//Tаймер должен быть запущен
        assertEquals( 250, game.getTimer().getDelay());//Частота таймера должна быть 250 миллисекунд
    }

    @Test
    @DisplayName("Тест на проверку создания еды для змейки - яблоко")
    public void appleCreateTest() {
        Game game = new Game();
        game.createApple();

        // Проверка координат яблока
        int appleX = game.getAppleX();
        int appleY = game.getAppleY();

        assertTrue(appleX >= 0);//Координата X яблока должна быть неотрицательным числом
        assertTrue(appleY >= 0);
        assertTrue(appleX < 20 * game.getDOT_SIZE());//Координата X яблока должна быть меньше 20 * DOT_SIZE
        assertTrue(appleY < 20 * game.getDOT_SIZE());
        assertEquals(0, appleX % game.getDOT_SIZE());//"Координата X и Y яблока должна быть кратна DOT_SIZE"
        assertEquals(0, appleY % game.getDOT_SIZE());
    }

    @Test
    @DisplayName("Тест на проверку движения змейки")
    public void moveTest() {
        Game game = new Game();
        game.initGame();
        int[] massivAllX = game.getXmassiv();
        int[] massivX = new int[game.getDots()];
        int[] massivAllY = game.getYmassiv();
        int[] massivY = new int[game.getDots()];
        for(int i=0;i<game.getDots();i++){
            massivX[i]=massivAllX[i];
            massivY[i]=massivAllY[i];
        }
        //начальные зн-я позиций ячеек
        assertArrayEquals(new int[]{48, 32,16},massivX);
        assertArrayEquals(new int[]{48, 48, 48}, massivY);
        // Вызываем метод moveSnake, который сдвигает вправо
        game.moveSnake();
        int[] massivAllX1 = game.getXmassiv();
        int[] massivX1 = new int[game.getDots()];
        int[] massivAllY1 = game.getYmassiv();
        int[] massivY1 = new int[game.getDots()];
        for(int i=0;i<game.getDots();i++){
            massivX1[i]=massivAllX1[i];
            massivY1[i]=massivAllY1[i];
        }
        // Проверяем, что элементы массива x сдвинулись правильно
        assertArrayEquals(new int[]{64, 48, 32},massivX1);

        // Проверяем, что элементы массива y сдвинулись правильно
        assertArrayEquals(new int[]{48, 48, 48}, massivY1);
    }
}
