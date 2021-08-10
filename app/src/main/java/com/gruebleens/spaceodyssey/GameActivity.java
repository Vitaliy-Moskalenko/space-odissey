package com.gruebleens.spaceodyssey;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    private GameView _gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _gameView = new GameView(this);
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