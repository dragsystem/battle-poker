package games.poker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class BattlePoker extends Activity {
	private PokerView mPokerView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.main);
        mPokerView = (PokerView)findViewById(R.id.poker);
        
        mPokerView.setTextView((TextView)findViewById(R.id.text));
        
        mPokerView.setMode(PokerView.TITLE);
        
        //mPokerView.initGame();
    }
}