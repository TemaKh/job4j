package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        while ((true)) {
            while (true) {
                this.rect.setX(this.rect.getX() + 1);
                this.rect.setY(this.rect.getY() - 1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.rect.getY() == 0) {
                    break;
                }
            }
            this.rect.setX(150);
            this.rect.setY(0);
            while (true) {
                this.rect.setX(this.rect.getX() + 1);
                this.rect.setY(this.rect.getY() + 1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.rect.getX() == 300) {
                    break;
                }
            }
            this.rect.setX(300);
            this.rect.setY(150);
            while (true) {
                this.rect.setX(this.rect.getX() - 1);
                this.rect.setY(this.rect.getY() + 1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.rect.getX() == 150) {
                    break;
                }
            }
            this.rect.setX(150);
            this.rect.setY(300);
            while (true) {
                this.rect.setX(this.rect.getX() - 1);
                this.rect.setY(this.rect.getY() - 1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.rect.getX() == 0) {
                    break;
                }
            }
        }
    }
}
