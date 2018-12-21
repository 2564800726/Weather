package cf.yuanbing.weather;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static Button information;
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (LocationWeather.city != null) {
                        information.setText(LocationWeather.city);
                        information.setEnabled(true);
                    } else {
                        information.setText("未找到该城市");
                        information.setEnabled(false);
                    }
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        final EditText editText = findViewById(R.id.et_get_location);
        information = findViewById(R.id.btn_information);
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CityActivity.class);
                startActivity(intent);
            }
        });
        final ImageView search = findViewById(R.id.iv_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetData(editText.getText().toString()).start();
//                information.setText(LocationWeather.city);
            }
        });
    }
}
