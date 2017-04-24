/**
 * Created by Riku Pepponen on 24.4.2017.
 * (riku.pepponen@gmail.com)
 */
package com.example.riku.xtremetictactoe;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {

    private MediaPlayer mMediaPlayer;

    public void playMusic(Context c) {
        stopMusic();
        mMediaPlayer = MediaPlayer.create(c, R.raw.guitar_c);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }

    public void stopMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}