package org.hannmx;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow {
    private JFrame settingsFrame;
    private JButton startButton;
    private JButton exitButton;
    private JRadioButton humanVsHumanButton;
    private JRadioButton humanVsAIButton;

    public SettingsWindow() {
        settingsFrame = new JFrame("Настройки игры");

        exitButton = new JButton("Выход");

        humanVsHumanButton = new JRadioButton("Играть против друга");
        humanVsAIButton = new JRadioButton("Играть против ИИ");

        ButtonGroup group = new ButtonGroup();
        group.add(humanVsHumanButton);
        group.add(humanVsAIButton);
        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        startButton = new JButton("Старт");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        JPanel panel = new JPanel();
        panel.add(humanVsHumanButton);
        panel.add(humanVsAIButton);
        panel.add(startButton);
        panel.add(exitButton);
        settingsFrame.add(panel);

        settingsFrame.add(panel);

        settingsFrame.setSize(300, 300); // Установка размеров окна
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setLocationRelativeTo(null);
    }

    public void show() {
        settingsFrame.setVisible(true);
    }

    private void startGame() {
        settingsFrame.dispose(); // Закрытие окна настроек
        boolean vsAI = humanVsAIButton.isSelected(); // Измените настройки в зависимости от выбора пользователя
        GameWindow gameWindow = new GameWindow(vsAI);
        gameWindow.show();
    }
}
