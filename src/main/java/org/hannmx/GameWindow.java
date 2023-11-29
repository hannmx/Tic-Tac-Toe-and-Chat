package org.hannmx;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow {
    private JFrame gameFrame;
    private Map gameMap;

    public GameWindow(boolean vsAI) {
        gameFrame = new JFrame("Крестики-нолики");

        gameMap = new Map(vsAI);
        gameFrame.add(gameMap); // Добавление игрового поля на игровое окно

        gameFrame.setSize(300, 300); // Установка размеров окна
        gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Не закрывать по умолчанию
        gameFrame.setLocationRelativeTo(null);
        gameFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(gameFrame, "Вы уверены, что хотите закрыть игру?", "Закрытие игры", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0); // Закрыть приложение
                }
            }
        });
    }

    public void show() {
        gameFrame.setVisible(true);
    }
}

