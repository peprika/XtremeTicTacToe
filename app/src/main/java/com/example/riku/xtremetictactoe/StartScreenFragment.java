/**
 * Created by Riku Pepponen
 */

package com.example.riku.xtremetictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class StartScreenFragment extends Fragment {

//TODO: BACKGROUND MUSIC

    private static final String TAG = "StartScreenFragment"; // for logging

    public ImageView mAppLogoView;
    public Button mNewGameButton;
    public TextView mCopyrightTextView;
    private AudioPlayer mAudioPlayer = new AudioPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "StartScreenFragment onCreate() called");

        mAudioPlayer.playMusic(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.start_screen, parent, false);

        // We've got to have a cool logo
        mAppLogoView = (ImageView)v.findViewById(R.id.app_logo_view);

        // New Game Button; I've heard it's pretty popular in games
        mNewGameButton = (Button)v.findViewById(R.id.new_game_button);
        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), GameActivity.class);
                mAudioPlayer.stopMusic();
                startActivity(i);
            }
        });

        // Copyright footer; just for bragging
        mCopyrightTextView = (TextView)v.findViewById(R.id.copyright_textview);

        // Always return the view
        return v;
    }

    // Stop the music when the activity is destroyed
    @Override
    public void onDestroy() {
        super.onDestroy();
        mAudioPlayer.stopMusic();
    }
}