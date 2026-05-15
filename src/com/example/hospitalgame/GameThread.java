package com.example.hospitalgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * GameThread - Dedicated thread for game rendering
 * Maintains consistent 60 FPS game loop
 */
public class GameThread extends Thread {
    
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    private static final int FPS = 60;
    
    public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }
    
    public void setRunning(boolean running) {
        this.running = running;
    }
    
    @Override
    public void run() {
        long ticksPS = 1000 / FPS; // Milliseconds per frame
        long startTime = 0;
        long sleepTime = 0;
        
        while (running) {
            Canvas canvas = null;
            startTime = System.currentTimeMillis();
            
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    gameView.update();
                    gameView.draw(canvas);
                }
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
            // Frame rate control
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
