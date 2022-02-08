package ism;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TTT implements MouseListener {

    Canvas canvas = new Canvas("TTT", 900, 900);
    int index = -1;
    boolean b0 = true;
    boolean b1 = true;
    boolean b2 = true;
    boolean b3 = true;
    boolean b4 = true;
    boolean b5 = true;
    boolean b6 = true;
    boolean b7 = true;
    boolean b8 = true;
    boolean moved = true;

    public TTT() {
        drawTTT();
    }

    public void drawTTT() {
        canvas.drawLine(300, 0, 300, 900);
        canvas.drawLine(600, 0, 600, 900);
        canvas.drawLine(0, 300, 900, 300);
        canvas.drawLine(0, 600, 900, 600);
        canvas.frame.addMouseListener(this);
        canvas.setFont(new Font("Times New Roman", 0, 200));
    }

    public void close() {
        canvas.frame.dispose();
    }

    public void drawX(int n) {
        if (n == 0) {
            canvas.drawString("X", 80, 220);
            b0 = false;
        }
        if (n == 1) {
            canvas.drawString("X", 380, 220);
            b1 = false;
        }
        if (n == 2) {
            canvas.drawString("X", 680, 220);
            b2 = false;
        }
        if (n == 3) {
            canvas.drawString("X", 80, 520);
            b3 = false;
        }
        if (n == 4) {
            canvas.drawString("X", 380, 520);
            b4 = false;
        }
        if (n == 5) {
            canvas.drawString("X", 680, 520);
            b5 = false;
        }
        if (n == 6) {
            canvas.drawString("X", 80, 820);
            b6 = false;
        }
        if (n == 7) {
            canvas.drawString("X", 380, 820);
            b7 = false;
        }
        if (n == 8) {
            canvas.drawString("X", 680, 820);
            b8 = false;
        }
    }

    public int getO() {
        return index;
    }

    public static void main(String[] args) {
        TTT t = new TTT();
    }

    public void pause() {
        while (moved) {
            canvas.wait(1000);
        }
        moved = true;
    }

    public void news() {
        canvas = new Canvas("TTT", 900, 900);
        canvas.setFont(new Font("Times New Roman", 0, 200));
    }

    public void stop() {
        canvas.wait(1000);
    }

    public void winScreen(int n) {
        if (n == 0) {
            canvas.drawString("TIE", 200, 450);
        }
        if (n == -1) {
            canvas.drawString("WIN", 200, 450);
        }
        if (n == 1) {
            canvas.drawString("LOSE", 200, 450);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        index = -1;
        if (x <= 300 && y <= 300) {
            index = 0;
            if (b0) {
                canvas.drawString("O", 80, 220);
                moved = false;
                b0 = false;
            }
        }
        if (x > 300 && x <= 600 && y <= 300) {
            index = 1;
            if (b1) {
                b1 = false;
                canvas.drawString("O", 380, 220);
                moved = false;
            }
        }
        if (x > 600 && x <= 900 && y <= 300) {
            index = 2;
            if (b2) {
                b2 = false;
                canvas.drawString("O", 680, 220);
                moved = false;
            }
        }
        if (x <= 300 && y <= 600 && y > 300) {
            index = 3;
            if (b3) {
                b3 = false;
                canvas.drawString("O", 80, 520);
                moved = false;
            }
        }
        if (x > 300 && x <= 600 && y <= 600 && y > 300) {
            index = 4;
            if (b4) {
                b4 = false;
                canvas.drawString("O", 380, 520);
                moved = false;
            }
        }
        if (x > 600 && x <= 900 && y <= 600 && y > 300) {
            index = 5;
            if (b5) {
                b5 = false;
                canvas.drawString("O", 680, 520);
                moved = false;
            }
        }
        if (x > 300 && x <= 600 && y <= 900 && y > 600) {
            index = 7;
            if (b7) {
                b7 = false;
                canvas.drawString("O", 380, 820);
                moved = false;
            }
        }
        if (x > 600 && x <= 900 && y <= 900 && y > 600) {
            index = 8;
            if (b8) {
                b8 = false;
                canvas.drawString("O", 680, 820);
                moved = false;
            }
        }
        if (x <= 300 && y <= 900 && y > 600) {
            index = 6;
            if (b6) {
                b6 = false;
                canvas.drawString("O", 80, 820);
                moved = false;
            }
        }
        System.out.println(index);

    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }
}
