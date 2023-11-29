package org.hannmx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Map extends JPanel {
    private boolean vsAI;
    private JButton[][] buttons; // Массив кнопок для игрового поля
    private boolean xTurn = true; // Переменная для определения текущего хода ("X" или "O")

    public Map(boolean vsAI) {
        this.vsAI = vsAI;
        initializeMap();
        if(vsAI) {
            AIPlayer.makeMove(this, true); // Передайте необходимые аргументы для метода AIPlayer.makeMove
            xTurn = false; // Установка значения xTurn в false после хода ИИ
        }
    }



    public JButton[][] getButtons() {
        return buttons;
    }

    public boolean isXTurn() {
        return xTurn;
    }

    public void setXTurn(boolean turn) {
        xTurn = turn;
    }

    private void initializeMap() {
        setLayout(new GridLayout(3, 3)); // Устанавливаем сетку 3x3 для кнопок

        buttons = new JButton[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                add(buttons[row][col]);
            }
        }
    }

    public boolean checkWin(String player) {
        // Проверка горизонтальных и вертикальных линий
        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getText().equals(player))
                    && (buttons[i][1].getText().equals(player))
                    && (buttons[i][2].getText().equals(player))) {
                return true; // Горизонтальная линия
            }
            if ((buttons[0][i].getText().equals(player))
                    && (buttons[1][i].getText().equals(player))
                    && (buttons[2][i].getText().equals(player))) {
                return true; // Вертикальная линия
            }
        }

        // Проверка диагоналей
        if ((buttons[0][0].getText().equals(player))
                && (buttons[1][1].getText().equals(player))
                && (buttons[2][2].getText().equals(player))) {
            return true; // Главная диагональ
        }
        if ((buttons[0][2].getText().equals(player))
                && (buttons[1][1].getText().equals(player))
                && (buttons[2][0].getText().equals(player))) {
            return true; // Побочная диагональ
        }

        return false;
    }

    private void endGame(String winner) {
        JOptionPane.showMessageDialog(this, "Победитель: " + winner);

        int choice = JOptionPane.showConfirmDialog(this, "Хотите сыграть еще раз?", "Конец игры", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            resetGame(); // Метод для сброса игры и очистки поля
        } else {
            System.exit(0); // Закрыть приложение
        }
    }

    public boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false; // Есть пустая клетка, игра продолжается
                }
            }
        }
        return true; // Все клетки заполнены, ничья
    }


    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(""); // Очистка текста на кнопках
                buttons[row][col].setBackground(null);
            }
        }
    }


    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();

            if (clickedButton.getText().isEmpty()) {
                if (xTurn) {
                    clickedButton.setText("X");
                    clickedButton.setBackground(Color.RED);
                    if (checkWin("X")) {
                        endGame("X");
                    } else if (checkDraw()) {
                        endGame("Ничья");
                    }
                } else {
                    clickedButton.setText("O");
                    clickedButton.setBackground(Color.BLUE);
                    if (checkWin("O")) {
                        endGame("O");
                    } else if (checkDraw()) {
                        endGame("Ничья");
                    }
                }

                xTurn = !xTurn;
            }
        }
    }
}
