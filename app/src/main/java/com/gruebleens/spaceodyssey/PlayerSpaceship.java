package com.gruebleens.spaceodyssey;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;


public class PlayerSpaceship {

    private final int GRAVITY   = -12;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private int     _x, _y;
    private int     _maxY, _minY; // Stop ship leaving screen
    private int     _speed;
    private boolean _boosting;

    private Rect    _hitbox;
    private Bitmap  _bitmap;


    public PlayerSpaceship(Context context, int scrnX, int scrnY) {
        _boosting = false;
        _x = 50;
        _y = 50;
        _speed = 1;
        _bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
        _maxY = scrnY - _bitmap.getHeight();
        _minY = 0;

        _hitbox = new Rect(_x, _y, _bitmap.getWidth(), _bitmap.getHeight());
    }

    public void update() {
        if(_boosting)
            _speed += 2;
        else
            _speed -= 5;

        if(_speed > MAX_SPEED) _speed = MAX_SPEED;
        if(_speed < MIN_SPEED) _speed = MIN_SPEED;

        _y -= _speed + GRAVITY; // Fly up and down

        if(_y < _minY) _y = _minY;
        if(_y > _maxY) _y = _maxY;

        _hitbox.left   = _x;
        _hitbox.top    = _y;
        _hitbox.right  = _x + _bitmap.getWidth();
        _hitbox.bottom = _y + _bitmap.getHeight();
        
    }

    public void boost() { _boosting = true; }

    public void stopBoosting() { _boosting = false; }

    public Bitmap getBitmap() { return _bitmap; }

    public int getSpeed() { return _speed; }

    public int getX() { return _x; }

    public int getY() { return _y; }

    public Rect getHitbox() { return _hitbox; }
}