/**
 * Created by Riku Pepponen.
 */

package com.example.riku.xtremetictactoe;
import android.support.v4.app.Fragment;


public class GameActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new GameFragment();
    }

}
