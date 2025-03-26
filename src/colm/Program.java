package colm;

import javax.swing.*; // подключаем все средства java Swing

public class Program { // класс с методом main()

    public static void main(String[] args) {
        JFrame frame = new JFrame("НеОбманБанк"); // Для окна нужна "рама" - Frame
        // стандартное поведение при закрытии окна - завершение приложения
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300); // размеры окна
        frame.setLocationRelativeTo(null); // окно - в центре экрана
        JButton button = new JButton("Снять деньги!"); // Экземпляр класса JButton
        // getContentPane() - клиентская область окна
        frame.getContentPane().add(button); // Добавляем кнопку на Frame
        frame.setVisible(true); // Делаем окно видимым
    }
}