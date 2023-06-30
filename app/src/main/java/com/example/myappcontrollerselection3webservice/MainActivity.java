package com.example.myappcontrollerselection3webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myappcontrollerselection3webservice.Adaptadores.AdaptadorUsuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.myappcontrollerselection3webservice.Modelos.Usuario;
import com.example.myappcontrollerselection3webservice.WebService.WebService;
import com.example.myappcontrollerselection3webservice.WebService.Asynchtask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    private ListView lstOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url="https://reqres.in/api/users";
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url,datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

        //VISTA
        lstOp = (ListView) findViewById(R.id.lstListaUsuario);
        View header = getLayoutInflater().inflate(R.layout.lyheaderusuarios, null);
        lstOp.addHeaderView(header);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject Jsonlista = new JSONObject(result);
        JSONArray JSONlistaUsuarios = Jsonlista.getJSONArray("data");
        ArrayList<Usuario>lstUsuarios = Usuario.JsonObjectsBuild(JSONlistaUsuarios);
        AdaptadorUsuario adaptadorUsuario = new AdaptadorUsuario(this,lstUsuarios);
        lstOp.setAdapter(adaptadorUsuario);
    }
}