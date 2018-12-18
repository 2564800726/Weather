package cf.yuanbing.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        final EditText editText = findViewById(R.id.et_get_location);
        ImageView search = findViewById(R.id.iv_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUri(editText.getText().toString());
            }
        });
    }

    private void setUri(String locate) {
        try {
            locate = URLEncoder.encode(locate, String.valueOf(StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(this, "参数异常", Toast.LENGTH_SHORT).show();
        }
        final String port = "https://www.apiopen.top/weatherApi?city=" + locate;
        String result = null;
        new Thread() {
            private BufferedReader bufferedReader;
            @Override
            public void run() {
                try {
                    URL url = new URL(port);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Content-type", "application/json");
                    httpURLConnection.connect();
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String line = null;
                    StringBuilder stringBuffer = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    httpURLConnection.disconnect();
                    if (AnalyzeData.analyzeData.startAnalyze(stringBuffer.toString())) {
                        Intent intent = new Intent(MainActivity.this, CityActivity.class);
                        startActivity(intent);
                    } else {
                        Log.e("TAG", "==============================");
                        Toast.makeText(MainActivity.this, "未查找到相关信息", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
