/**
 * Created by Riku Pepponen
 */

package com.example.riku.xtremetictactoe;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameFragment extends Fragment {

    private static final String TAG = "GameFragment"; //for logging

    // The basic declarations
    public static int[] buttonIds = new int[]{R.id.button_a1, R.id.button_a2, R.id.button_a3,
                                              R.id.button_b1, R.id.button_b2, R.id.button_b3,
                                              R.id.button_c1, R.id.button_c2, R.id.button_c3};
    public TableLayout mGameField;
    public ImageButton AbstractButton;
    public TextView mPlayer1Textview;
    public TextView mPlayer2Textview;
    public int mCurrentPlayer;
    public String mGameState;
    public String mPotentialWinnter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");

        setRetainInstance(true);
        GameLogic.initalizeGame();
        mGameState = "Start";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_screen, parent, false);

        Log.d(TAG, "onCreateView() called");

        // Get the view elements
        mGameField = (TableLayout) v.findViewById(R.id.game_field);
        mPlayer1Textview = (TextView) v.findViewById(R.id.game_footer_player1_textview);
        mPlayer2Textview = (TextView) v.findViewById(R.id.game_footer_player2_textview);

        // Listeners for each imageButton in the 3x3 grid
        for (int i = 0; i < buttonIds.length; i++) {
            final int buttonReference = buttonIds[i];
            AbstractButton = (ImageButton) v.findViewById(buttonReference);
            AbstractButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Button clicked");
                    // So much happening on every click....

                    // First, get the imagebutton...
                    AbstractButton = (ImageButton) v.findViewById(buttonReference);

                    // Set its image to 'X' or 'O' -- and you can't click it again
                    AbstractButton.setImageResource(GameLogic.getSymbol());
                    AbstractButton.setClickable(false);

                    // Set the box value and check if there's a winner
                    mPotentialWinnter = GameLogic.checkIfWin(GameLogic.setBoxValue(buttonReference));

                    // Check if there's a win/tie
                    // TODO: Clean this up, and what happens when the game ends?
                    // TODO: Disable buttons after the game ends
                    if ((mPotentialWinnter).equals("Player 1")) {
                        // If the winner is player 1
                        Log.d(TAG, "OnClick, we found a winner, player 1!");
                        mGameState = "Won";
                        Toast.makeText(getActivity(), "Player 1 wins!", Toast.LENGTH_LONG).show();
                        }
                    if ((mPotentialWinnter).equals("Player 2")) {
                        // If the winner is player 2
                        Log.d(TAG, "OnClick, we found a winner, player 2!");
                        mGameState = "Won";
                        Toast.makeText(getActivity(), "Player 2 wins!", Toast.LENGTH_LONG).show();
                    }
                    if ((mPotentialWinnter).equals("Tie")) {
                        // It's a tie
                        Log.d(TAG, "OnClick, a tie happened.");
                        Toast.makeText(getActivity(), "It's an extreme tie!", Toast.LENGTH_LONG).show();
                        mGameState = "Tie";
                    }

                    // If there's no win/tie, let the other player have a go
                    GameLogic.changePlayer();

                    // Update the playerviews to show whose turn it is
                    mCurrentPlayer = GameLogic.getPlayer();
                    if (mCurrentPlayer == 0) {
                        mPlayer1Textview.setText(R.string.player1_turn);
                        mPlayer1Textview.setTypeface(null, Typeface.BOLD_ITALIC);
                        mPlayer2Textview.setText(R.string.player2);
                        mPlayer2Textview.setTypeface(null, Typeface.NORMAL);
                    }
                    if (mCurrentPlayer == 1) {
                        mPlayer2Textview.setText(R.string.player2_turn);
                        mPlayer2Textview.setTypeface(null, Typeface.BOLD_ITALIC);
                        mPlayer1Textview.setText(R.string.player1);
                        mPlayer1Textview.setTypeface(null, Typeface.NORMAL);
                    }
                }
            });
        }

        // Finally, return the view as supposed to
        return v;
    }

}