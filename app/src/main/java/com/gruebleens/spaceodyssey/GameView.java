package com.gruebleens.spaceodyssey;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements Runnable {

    volatile boolean isPlaying;
    Thread   gameThread = null;

    private PlayerSpaceship _playerShip;

    private Paint  _paint;
    private Canvas _canvas;
    private SurfaceHolder _holder;

    public GameView(Context context) {
        super(context);

        _holder = getHolder();
        _paint  = new Paint();

        _playerShip = new PlayerSpaceship(context);
    }

    @Override
    public void run() {
        while(isPlaying) {
            _update();
            _draw();
            _control();
        }
    }

    public void pause() {
        isPlaying = false;
        try {
            gameThread.join();
        }
        catch (InterruptedException e) {}
    }

    public void resume() {
        isPlaying = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void _update() {
        _playerShip.update();
    }

    private void _draw() {
        if(_holder.getSurface().isValid()) {
            _canvas = _holder.lockCanvas();
            _canvas.drawColor(Color.argb(0, 0,255,0));
            _canvas.drawBitmap(_playerShip.getBitmap(), _playerShip.getX(), _playerShip.getY(), _paint);
            _holder.unlockCanvasAndPost(_canvas);
        }
    }

    private void _control() {
        try {
            gameThread.sleep(17);
        }
        catch (InterruptedException e) {}
    }
}