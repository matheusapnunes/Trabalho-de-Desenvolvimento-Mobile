package com.example.socialmotors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class cadastroveiculo extends AppCompatActivity {


    private EditText marcaveiculo;
    private EditText modeloveiculo;
    private EditText tipoveiculo;
    private veiculoDAO dao;
    private  veiculo veiculo = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastroveiculo);

        marcaveiculo = findViewById(R.id.editmarcaveiculo);
        modeloveiculo = findViewById(R.id.editmodeloveiculo);
        tipoveiculo = findViewById(R.id.edittipoveiculo);
        dao = new veiculoDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("veiculo")){
            veiculo = (veiculo) it.getSerializableExtra("veiculo");
            marcaveiculo.setText(veiculo.getMarcaveiculo());
            modeloveiculo.setText(veiculo.getModeloveiculo());
            tipoveiculo.setText(veiculo.getTipoveiculo());

        }
    }

    public void cadastrar(View view) {

        if (veiculo == null) {

            veiculo = new veiculo();
            veiculo.setMarcaveiculo(marcaveiculo.getText().toString());
            veiculo.setModeloveiculo(modeloveiculo.getText().toString());
            veiculo.setTipoveiculo(tipoveiculo.getText().toString());
            long id = dao.inserir(veiculo);
            Intent intent = new Intent(this, listarveiculosctivity.class);
            Toast.makeText(this, "veiculo inserido com id: " + id, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else {
            veiculo.setMarcaveiculo(marcaveiculo.getText().toString());
            veiculo.setModeloveiculo(modeloveiculo.getText().toString());
            veiculo.setTipoveiculo(tipoveiculo.getText().toString());
            dao.atualizar(veiculo);
            Toast.makeText(this, "Veiculo foi Atualizado!", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

}
