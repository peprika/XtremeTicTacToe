/**
 * Created by Riku Pepponen
*/

package com.example.riku.xtremetictactoe;

import android.util.Log;
import java.util.Arrays;

public class GameLogic extends Object {

    private static final String TAG = "GameLogic";

    // Some basic declarations
    public static int mCurrentPlayer;
    public static int mPlayerNumber;
    public static int symbol;
    public static String mWinner;
    public static Integer buttonIndex;
    public static Integer[] boxStates = new Integer[9];
    public static Integer[] buttonIds = new Integer[]{R.id.button_a1, R.id.button_a2, R.id.button_a3,
                                                      R.id.button_b1, R.id.button_b2, R.id.button_b3,
                                                      R.id.button_c1, R.id.button_c2, R.id.button_c3};

    // New Game: Reset everything!
    public static void initalizeGame() {
        Arrays.fill(boxStates, R.drawable.emptybox);    // Reset the grid values
        mCurrentPlayer = 0;                             // Reset to player 1's turn
        mWinner = null;                                 // Reset to "no one has won yet"
    }

    // Hey, let the other guy go for a change!
    public static void changePlayer() {
        if   (mCurrentPlayer == 0) { mCurrentPlayer = 1; }
        else { mCurrentPlayer = 0; }
    }

    // Whose turn is it again?
    public static int getPlayer() {
        return mCurrentPlayer;
    }

    // Am I 'X' or 'O'?
    public static int getSymbol() {
        if (mCurrentPlayer == 0) {symbol = R.drawable.xbox; }
        if (mCurrentPlayer == 1) {symbol = R.drawable.obox; }
        return symbol;
    }

    // I clicked a box, let the system know this box is mine
    public static Integer[] setBoxValue(int buttonId) {
        symbol = getSymbol();
        buttonIndex = Arrays.asList(buttonIds).indexOf(buttonId);
        boxStates[buttonIndex] = symbol;
        return boxStates;
    }

    public static Integer[] getBoxValues() {
        return boxStates;
    }

    // Check if the game is over
    public static String checkIfWin(Integer[] boxStates) {
        // symbol is 'X' or 'O'
        symbol = getSymbol();
        // For logging
        Log.d(TAG, "CheckifWin, boxstates[012] are " + boxStates[0] + " " + boxStates[1] + " " + boxStates[2]);
        Log.d(TAG, "CheckifWin, boxstates[345] are " + boxStates[3] + " " + boxStates[4] + " " + boxStates[5]);
        Log.d(TAG, "CheckifWin, boxstates[678] are " + boxStates[6] + " " + boxStates[7] + " " + boxStates[8]);
        Log.d(TAG, "CheckifWin, emptybox is " + R.drawable.emptybox);

        // Checking if there's a WIN!
                // Horizontal rows
            if (((boxStates[0] == symbol) && (boxStates[1] == symbol) && (boxStates[2]) == symbol) ||
                ((boxStates[3] == symbol) && (boxStates[4] == symbol) && (boxStates[5]) == symbol) ||
                ((boxStates[6] == symbol) && (boxStates[7] == symbol) && (boxStates[8]) == symbol) ||
                // Vertical rows
                ((boxStates[0] == symbol) && (boxStates[3] == symbol) && (boxStates[6]) == symbol) ||
                ((boxStates[1] == symbol) && (boxStates[4] == symbol) && (boxStates[7]) == symbol) ||
                ((boxStates[2] == symbol) && (boxStates[5] == symbol) && (boxStates[8]) == symbol) ||
                // Diagonal rows
                ((boxStates[0] == symbol) && (boxStates[4] == symbol) && (boxStates[8]) == symbol) ||
                ((boxStates[2] == symbol) && (boxStates[4] == symbol) && (boxStates[6]) == symbol))
            {
            // If the game is won...
                Log.d(TAG, "CheckIfWin: The game is won");
                mPlayerNumber = mCurrentPlayer + 1;
                mWinner = "Player " + mPlayerNumber;
                Log.d(TAG, "Winner: " + mWinner);
                return mWinner; }

            // If the game ends in a tie...
            if ((mWinner == null) && (!Arrays.asList(boxStates).contains(R.drawable.emptybox))) {
                Log.d(TAG, "CheckIfWin: It's a tie.");
                return "Tie";

            // Or then it continues
            } else {
                Log.d(TAG, "CheckIfWin: Game continues...");
                return "Continue";
                }
    }


}