package apps.tipcalc;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
        
        final Spinner s1 = (Spinner) findViewById(R.id.Spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.tipChoices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        
        //TODO: Clear percent field when spinner is selected.
        //TODO: Give spinner default text that carries no value,
        //and put spinner back to default text when percent field is selected.
        
        final Button button = (Button)findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double billVal = Double.parseDouble(bill.getText().toString());
                double percentVal = 0.0;
                if(percent.getText().toString() != null && !percent.getText().toString().equals("")){
                	percentVal = Double.parseDouble(percent.getText().toString());
                }
                //XXX: figure out how to set up the xml array to have values corresponding to the strings
                //otherwise we end up with the proceeding fail.
                else if (((String)s1.getSelectedItem()).equals(((String)s1.getAdapter().getItem(0)))){
                	percentVal = 10.0;
                }
                else if (((String)s1.getSelectedItem()).equals(((String)s1.getAdapter().getItem(1)))){
                	percentVal = 12.0;
                }
                else if (((String)s1.getSelectedItem()).equals(((String)s1.getAdapter().getItem(2)))){
                	percentVal = 15.0;
                }
                else if (((String)s1.getSelectedItem()).equals(((String)s1.getAdapter().getItem(3)))){
                	percentVal = 18.0;
                }
                
                double ans = billVal * percentVal/100.0;
                double ans2 = billVal + ans;
                System.out.println("Tip Calculated!");
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