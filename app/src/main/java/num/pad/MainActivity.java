package num.pad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


//aluksi git harjoittelua

public class MainActivity extends AppCompatActivity {

    EditText answerText;

    GridView numpadGrid;

    static final String[] numbers = new String[]{
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "C", "0", "OK"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerText = findViewById(R.id.answerView);
        numpadGrid = findViewById(R.id.numbadGrid);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, numbers);

        numpadGrid.setAdapter(adapter);

        numpadGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Object selected_button = numpadGrid.getItemAtPosition(position);
                answerText.setText(answerText.getText() + "1");
            }
        });
    }
}
