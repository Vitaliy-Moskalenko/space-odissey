package com.gruebleens.spaceodyssey;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;


public class GameActivity extends AppCompatActivity {

    private GameView _gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point(); // Load the resolution into Point object
        display.getSize(size);

        _gameView = new GameView(this, size.x, size.y);
        setContentView(_gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        _gameView.pause();
    }

    @Override
    protected  void onResume() {
        super.onResume();
        _gameView.resume();
    }
}