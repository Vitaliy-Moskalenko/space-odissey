package com.gruebleens.spaceodyssey;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.util.Random;


public class EnemyShip {

    private Bitmap _bitmap;
    private int _x, _y;
    private int _speed;

    private int _maxX;
    private int _minX;
    private int _maxY;
    private int _minY;

    private Rect _hitbox;

    public EnemyShip(Context context, int scrnX, int scrnY) {
        _bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        _minX = _minY = 0;
        _maxX = scrnX;
        _maxY = scrnY;

        Random random = new Random();
        _speed = random.nextInt(6) + 10;

        _x = scrnX;
        _y = random.nextInt(_maxY) - _bitmap.getHeight();

        _hitbox = new Rect(_x, _y, _bitmap.getWidth(), _bitmap.getHeight());
    }

    public void update(int playerSpeed) {
        _x -= playerSpeed;
        _x -= _speed;

        if(_x < _minX - _bitmap.getWidth()) { // Respawn when off screen
            Random random = new Random();
            _speed = random.nextInt(10) + 10;
            _x = _maxX;
            _y = random.nextInt(_maxY) - _bitmap.getHeight();
        }

        _hitbox.left   = _x;
        _hitbox.top    = _y;
        _hitbox.right  = _x + _bitmap.getWidth();
        _hitbox.bottom = _y + _bitmap.getHeight();
    }

    public void setX(int x) { _x = x; }

    public Bitmap getBitmap() { return _bitmap; }

    public int getX() { return _x; }

    public int getY() { return _y; }

    public Rect getHitbox() { return _hitbox; }
}
