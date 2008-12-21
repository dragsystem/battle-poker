package apps.tipcalc;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculator extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TextView answer = (TextView)findViewById(R.id.ResultText);
        final EditText bill = (EditText)findViewById(R.id.Text1);
        final EditText percent = (EditText)findViewById(R.id.Text2);
        
        
        
        
        final Button button = (Button)findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double billVal = Double.parseDouble(bill.getText().toString());
                double percentVal = Double.parseDouble(percent.getText().toString());
                
                double ans = billVal * percentVal/100.0;
                double ans2 = billVal + ans;
                answer.setText("Tip is " + formatDollars(ans) + "\n" + "Total is " + formatDollars(ans2));
            }
        });
        
        
    }
    
    private String formatDollars(double s){
    	String ans = null;
   		NumberFormat formatter = NumberFormat.getCurrencyInstance();
   		ans = formatter.format(s);
    	
    	return ans;
    }    
}