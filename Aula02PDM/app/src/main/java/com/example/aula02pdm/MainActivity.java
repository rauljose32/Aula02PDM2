package com.example.aula02pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private final String URL = "https://jsonplaceholder.typicode.com/posts";
    private StringBuilder builder = null;
    private List<Item> items = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewDados);

        new obterDados().execute();
    }

    private class obterDados extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Conexao conexao = new Conexao();
            InputStream inputStream = conexao.obterRespostaHttp(URL);
            Auxiliar auxiliar = new Auxiliar();
            String textoJson = auxiliar.converter(inputStream);
            Log.i("JSON", "doInBackground: " + textoJson);
            Gson gson = new Gson();
            builder = new StringBuilder();

            if (textoJson != null) {
                Type type = new TypeToken<List<Item>>() {
                }.getType();
                items = gson.fromJson(textoJson, type);
                for (int i = 0; i < items.size(); i++) {
                    builder.append(items.get(i).getUserId()).append("\n");
                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Não foi possivel obter Json", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }


        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Baixando...", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void unused) {
            textView.setText(builder.toString());
        }

    }

}