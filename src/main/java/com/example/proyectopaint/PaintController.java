package com.example.proyectopaint;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PaintController {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private final Color[] palette = {
            Color.BLACK, Color.RED, Color.GREEN, Color.BLUE,
            Color.CYAN, Color.MAGENTA, Color.color(0.95, 0.9, 0)
    };

    private int currentColorNum = 0;
    private double prevX, prevY;
    private boolean dragging = false;

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        clearAndDrawPalette();
    }

    @FXML
    private void onMousePressed(MouseEvent evt) {
        if (dragging) return;

        int x = (int) evt.getX();
        int y = (int) evt.getY();

        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();

        if (x > width - 53) {
            if (y > height - 53) {
                clearAndDrawPalette();
            } else {
                changeColor(y);
            }
        } else if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
            prevX = x;
            prevY = y;
            dragging = true;
            gc.setLineWidth(2);
            gc.setStroke(palette[currentColorNum]);
        }
    }

    @FXML
    private void onMouseReleased(MouseEvent evt) {
        dragging = false;
    }

    @FXML
    private void onMouseDragged(MouseEvent evt) {
        if (!dragging) return;

        double x = evt.getX();
        double y = evt.getY();

        if (x < 3) x = 3;
        if (x > canvas.getWidth() - 57) x = canvas.getWidth() - 57;
        if (y < 3) y = 3;
        if (y > canvas.getHeight() - 4) y = canvas.getHeight() - 4;

        gc.strokeLine(prevX, prevY, x, y);
        prevX = x;
        prevY = y;
    }

    private void changeColor(int y) {
        int height = (int) canvas.getHeight();
        int colorSpacing = (height - 56) / 7;
        int newColor = y / colorSpacing;

        if (newColor < 0 || newColor > 6) return;

        gc.setStroke(Color.GRAY);
        gc.setLineWidth(2);
        gc.strokeRect(canvas.getWidth() - 54, 2 + currentColorNum * colorSpacing, 52, colorSpacing - 1);
        currentColorNum = newColor;
        gc.setStroke(Color.WHITE);
        gc.strokeRect(canvas.getWidth() - 54, 2 + currentColorNum * colorSpacing, 52, colorSpacing - 1);
    }

    private void clearAndDrawPalette() {
        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        gc.setStroke(Color.GRAY);
        gc.setLineWidth(3);
        gc.strokeRect(1.5, 1.5, width - 3, height - 3);

        gc.setFill(Color.GRAY);
        gc.fillRect(width - 56, 0, 56, height);

        gc.setFill(Color.WHITE);
        gc.fillRect(width - 53, height - 53, 50, 50);
        gc.setFill(Color.BLACK);
        gc.fillText("CLEAR", width - 48, height - 23);

        int colorSpacing = (height - 56) / 7;
        for (int N = 0; N < 7; N++) {
            gc.setFill(palette[N]);
            gc.fillRect(width - 53, 3 + N * colorSpacing, 50, colorSpacing - 3);
        }

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeRect(width - 54, 2 + currentColorNum * colorSpacing, 52, colorSpacing - 1);
    }
}
