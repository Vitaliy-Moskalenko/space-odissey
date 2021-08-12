package com.gruebleens.spaceodyssey;

import java.util.Random;


public class StarDust {

    private int _x, _y;
    private int _speed;

    private int _maxX;
    private int _maxY;
    private int _minX;
    private int _minY;

    public StarDust(int scrnX, int scrnY) {
        _maxX = scrnX;
        _maxY = scrnY;
        _minX = _minY = 0;

        Random random = new Random();
        _speed = random.nextInt(10);

        _x = random.nextInt(_maxX);
        _y = random.nextInt(_maxY);
    }

    public void update(int playerSpeed) {
        _x -= playerSpeed;
        _x -= _speed;

        if(_x < 0) {
            _x = _maxX;
            Random random = new Random();
            _y = random.nextInt(_maxY);
            _speed = random.nextInt(15);
        }
    }

    public int getX() { return _x; }

    public int getY() { return _y; }
}
