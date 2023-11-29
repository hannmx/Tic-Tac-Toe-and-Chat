package org.hannmx;

import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;

public class AIPlayer {
    private static final char AI_MARK = 'O'; // Символ, которым ходит ИИ
    private static final char HUMAN_MARK = 'X'; // Символ, которым ходит человек

    public static void makeMove(Map map, boolean xTurn) {
        if (xTurn) {
            return; // Если сейчас ход игрока, не делаем ход ИИ
        }

        JButton[][] buttons = map.getButtons();
        Move bestMove = minimax(map, AI_MARK);

        buttons[bestMove.row][bestMove.col].setText(String.valueOf(AI_MARK));
        map.setXTurn(true); // Переключаем ход на игрока
    }

    private static Move minimax(Map map, char player) {
        JButton[][] buttons = map.getButtons();

        if (isWinner(map, AI_MARK)) {
            return new Move(10);
        } else if (isWinner(map, HUMAN_MARK)) {
            return new Move(-10);
        } else if (isDraw(map)) {
            return new Move(0);
        }

        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    buttons[i][j].setText(String.valueOf(player));
                    Move move;
                    if (player == AI_MARK) {
                        move = new Move(minimax(map, HUMAN_MARK).score);
                    } else {
                        move = new Move(minimax(map, AI_MARK).score);
                    }
                    move.row = i;
                    move.col = j;
                    moves.add(move);
                    buttons[i][j].setText("");
                }
            }
        }

        int bestMoveIdx = 0;
        int bestScore = (player == AI_MARK) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < moves.size(); i++) {
            if ((player == AI_MARK && moves.get(i).score > bestScore) ||
                    (player == HUMAN_MARK && moves.get(i).score < bestScore)) {
                bestScore = moves.get(i).score;
                bestMoveIdx = i;
            }
        }

        return moves.get(bestMoveIdx);
    }

    private static boolean isWinner(Map map, char player) {
        return map.checkWin(String.valueOf(player));
    }

    private static boolean isDraw(Map map) {
        return map.checkDraw();
    }

    static class Move {
        int row, col, score;

        Move(int score) {
            this.score = score;
        }
    }
}
