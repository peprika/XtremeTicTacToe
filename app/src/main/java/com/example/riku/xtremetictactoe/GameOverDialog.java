/**
 * Created by Riku Pepponen on 24.4.2017.
 * (riku.pepponen@gmail.com)
 */

package com.example.riku.xtremetictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class GameOverDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.game_over)
                .setMessage("Game over, dude!")
                .setPositiveButton(R.string.new_game, null)
                .setNegativeButton(R.string.quit, null)
                .create();
    }
}
