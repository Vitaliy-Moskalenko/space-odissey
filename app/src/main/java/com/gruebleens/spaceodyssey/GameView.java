package com.gruebleens.spaceodyssey;

import android.view.Surface;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements Runnable {

    volatile boolean isPlaying;
    Thread   gameThread = null;

    private PlayerSpaceship playerSpaceship;

    private Paint  _paint;
    private Canvas _canvas;
    private SurfaceHolder _holder;



    public void run() {}
}
