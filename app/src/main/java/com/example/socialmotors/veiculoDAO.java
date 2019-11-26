package com.example.socialmotors;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class veiculoDAO {


    private conexao conexao;
    private SQLiteDatabase banco;

    public veiculoDAO(Context context){

        conexao = new conexao(context);
        banco = conexao.getWritableDatabase();

    }

    public long inserir(veiculo veiculo){

        ContentValues values = new ContentValues();
        values.put("marcaveiculo", veiculo.getMarcaveiculo());
        values.put("modeloveiculo",veiculo.getModeloveiculo());
        values.put("tipoveiculo", veiculo.getTipoveiculo());
       return banco.insert("cadastro",null, values);

    }

     public List<veiculo> obtertodos(){

         List<veiculo> veiculos = new ArrayList<>();
         Cursor cursor = banco.query("cadastro", new String[]{"id", "marcaveiculo", "modeloveiculo", "tipoveiculo"},
                 null,null,null,null,null);

         while (cursor.moveToNext()){

           veiculo a  = new veiculo();
           a.setId(cursor.getInt(0));
           a.setMarcaveiculo(cursor.getString(1));
           a.setModeloveiculo(cursor.getString(2));
           a.setTipoveiculo(cursor.getString(3));
           veiculos.add(a);
         }
       return veiculos;

     }


     public void excluir(veiculo a){

        banco.delete("cadastro", "id = ?", new String[]{a.getId().toString()} );

     }

     public void atualizar(veiculo veiculo){


         ContentValues values = new ContentValues();
         values.put("marcaveiculo", veiculo.getMarcaveiculo());
         values.put("modeloveiculo",veiculo.getModeloveiculo());
         values.put("tipoveiculo", veiculo.getTipoveiculo());
         banco.update("cadastro", values,"id = ?", new String[]{veiculo.getId().toString()});
     }
}
