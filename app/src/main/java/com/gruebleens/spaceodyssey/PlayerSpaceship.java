package com.gruebleens.spaceodyssey;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PlayerSpaceship {

    private Bitmap _bitmap;
    private int _x, _y;
    private int _speed;

    public PlayerSpaceship(Context context) {
        _x = 50;
        _y = 50;
        _speed = 1;
        _bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
    }

    public void update() {
        _x++;
    }

    public Bitmap getBitmap() { return _bitmap; }

    public int getSpeed() { return _speed; }

    public int getX() { return _x; }

    public int getY() { return _y; }

}
