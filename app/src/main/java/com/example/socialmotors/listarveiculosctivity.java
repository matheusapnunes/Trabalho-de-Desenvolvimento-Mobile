package com.example.socialmotors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class listarveiculosctivity extends AppCompatActivity {



    private ListView listView;
    private veiculoDAO dao;
    private List<veiculo> veiculos;
    private List<veiculo> veiculosfiltrados = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarveiculosctivity);

        listView = findViewById(R.id.lista_veiculos);
        dao = new veiculoDAO(this);
        veiculos = dao.obtertodos();
        veiculosfiltrados.addAll(veiculos);
        //ArrayAdapter<veiculo> adaptador = new ArrayAdapter<veiculo>(this, android.R.layout.simple_list_item_1, veiculosfiltrados);
        veiculoadapter adaptador = new veiculoadapter(this,veiculosfiltrados);
        listView.setAdapter(adaptador);


        registerForContextMenu(listView);


    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        final SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
               procuraveiculo(s);
                return false;
            }
        });
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }



    public void procuraveiculo(String modeloveiculo){

        veiculosfiltrados.clear();
        for (veiculo a : veiculos){
         if(a.getModeloveiculo().toLowerCase().contains(modeloveiculo.toLowerCase())){

             veiculosfiltrados.add(a);
         }

        }
        listView.invalidateViews();

    }

    public void excluir (MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

       final veiculo veiculoexcluir = veiculosfiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o veículo?")
                .setNegativeButton("Não",null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        veiculosfiltrados.remove(veiculoexcluir);
                        veiculos.remove(veiculoexcluir);
                        dao.excluir(veiculoexcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();

    }



    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, cadastroveiculo.class);
        startActivity(it);
    }

   public void atualizar(MenuItem item){

       AdapterView.AdapterContextMenuInfo menuInfo =
               (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

       final veiculo veiculoatualizar = veiculosfiltrados.get(menuInfo.position);
       Intent it = new Intent(this, cadastroveiculo.class);
       it.putExtra("veiculo", veiculoatualizar);
       startActivity(it);
   }

    @Override
    public void onResume(){
     super.onResume();
     veiculos = dao.obtertodos();
     veiculosfiltrados.clear();
     veiculosfiltrados.addAll(veiculos);
     listView.invalidateViews();


    }
}
