package com.gruebleens.spaceodyssey;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;


public class GameView extends SurfaceView implements Runnable {

    volatile boolean isPlaying;
    Thread   gameThread = null;

    private PlayerSpaceship _playerShip;
    public EnemyShip        enemy1;
    public EnemyShip        enemy2;
    public EnemyShip        enemy3;

    ArrayList<StarDust> dustList = new ArrayList<StarDust>();

    private Paint  _paint;
    private Canvas _canvas;
    private SurfaceHolder _holder;

    public GameView(Context context, int x, int y) {
        super(context);

        _holder = getHolder();
        _paint  = new Paint();

        _playerShip = new PlayerSpaceship(context, x, y);

        enemy1 = new EnemyShip(context, x, y);
        enemy2 = new EnemyShip(context, x, y);
        enemy3 = new EnemyShip(context, x, y);

        int numSpec = 50;

        for(int i = 0; i < numSpec; ++i) {
            StarDust spec = new StarDust(x, y);
            dustList.add(spec);
        }
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
        // Collision detection
        if(Rect.intersects(_playerShip.getHitbox(), enemy1.getHitbox()))
            enemy1.setX(-100);

        if(Rect.intersects(_playerShip.getHitbox(), enemy2.getHitbox()))
            enemy2.setX(-100);

        if(Rect.intersects(_playerShip.getHitbox(), enemy3.getHitbox()))
            enemy3.setX(-100);

        _playerShip.update();
        enemy1.update(_playerShip.getSpeed());
        enemy2.update(_playerShip.getSpeed());
        enemy3.update(_playerShip.getSpeed());

        for(StarDust sd : dustList)
            sd.update(_playerShip.getSpeed());
    }

    private void _draw() {
        if(_holder.getSurface().isValid()) {
            _canvas = _holder.lockCanvas();
            _canvas.drawColor(Color.argb(255, 0, 0, 0));

           // _paint.setColor(Color.argb(255, 255, 255, 255)); // Set white rects for debug

            // Draw hitboxes
            _canvas.drawRect(_playerShip.getHitbox().left,
                    _playerShip.getHitbox().top,
                    _playerShip.getHitbox().right,
                    _playerShip.getHitbox().bottom,
                    _paint);

            _canvas.drawRect(enemy1.getHitbox().left,
                    enemy1.getHitbox().top,
                    enemy1.getHitbox().right,
                    enemy1.getHitbox().bottom,
                    _paint);

            _canvas.drawRect(enemy2.getHitbox().left,
                    enemy2.getHitbox().top,
                    enemy2.getHitbox().right,
                    enemy2.getHitbox().bottom,
                    _paint);

            _canvas.drawRect(enemy3.getHitbox().left,
                    enemy3.getHitbox().top,
                    enemy3.getHitbox().right,
                    enemy3.getHitbox().bottom,
                    _paint);

            _paint.setColor(Color.argb(255, 255, 255, 255));
            for(StarDust sd : dustList)
                _canvas.drawPoint(sd.getX(), sd.getY(), _paint);

            _canvas.drawBitmap(_playerShip.getBitmap(), _playerShip.getX(), _playerShip.getY(), _paint);
            _canvas.drawBitmap(enemy1.getBitmap(), enemy1.getX(), enemy1.getY(), _paint);
            _canvas.drawBitmap(enemy2.getBitmap(), enemy2.getX(), enemy2.getY(), _paint);
            _canvas.drawBitmap(enemy3.getBitmap(), enemy3.getX(), enemy3.getY(), _paint);

            _holder.unlockCanvasAndPost(_canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP   : _playerShip.stopBoosting(); break;
            case MotionEvent.ACTION_DOWN : _playerShip.boost(); break;
        }

        return true;
    }

    private void _control() {
        try {
            gameThread.sleep(17);
        }
        catch (InterruptedException e) {}
    }
}