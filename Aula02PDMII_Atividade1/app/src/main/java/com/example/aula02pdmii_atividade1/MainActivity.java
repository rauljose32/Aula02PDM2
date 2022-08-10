package com.example.aula02pdmii_atividade1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aula02pdmii_atividade1.model.Agenda;
import com.example.aula02pdmii_atividade1.model.Pessoa;
import com.example.aula02pdmii_atividade1.util.Auxiliar;
import com.example.aula02pdmii_atividade1.util.Conexao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private final String URL = "https://my-json-server.typicode.com/rauljose32/db-json/agenda";
    private StringBuilder builder = null;
    private List<Agenda> agendas = null;
    private ArrayAdapter<Agenda> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listaViewDados);


        new obterDados().execute();
    }

    private class obterDados extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Baixando...", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void unused) {
            adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,agendas);
            listView.setAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Conexao conexao = new Conexao();
            InputStream inputStream = conexao.obterRespostaHttp(URL);
            Auxiliar auxiliar = new Auxiliar();
            String textoJson = auxiliar.converter(inputStream);
            Log.i("JSON", "doInBackground: " + textoJson);
            Gson gson = new Gson();
            builder = new StringBuilder();

            if(textoJson != null){
                Type type = new TypeToken<List<Agenda>>(){}.getType();
                agendas = gson.fromJson(textoJson, type);


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
    }
}