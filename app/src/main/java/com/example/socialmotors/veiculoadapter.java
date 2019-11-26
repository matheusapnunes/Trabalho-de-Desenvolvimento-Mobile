package com.example.socialmotors;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class veiculoadapter extends BaseAdapter {

    private List<veiculo> veiculos;
    private Activity activity;

    public veiculoadapter(Activity activity,List<veiculo> veiculos) {
        this.activity = activity;
        this.veiculos = veiculos;
    }

    @Override
    public int getCount() {
        return veiculos.size();
    }

    @Override
    public Object getItem(int i) {

        return veiculos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return veiculos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup, false);
        TextView modeloveiculo = v.findViewById(R.id.txt_modeloveiculo);
        TextView marcaveiculo = v.findViewById(R.id.txt_marcaveiculo);
        TextView tipoveiculo = v.findViewById(R.id.txt_tipoveiculo);
        veiculo a = veiculos.get(i);
        modeloveiculo.setText(a.getModeloveiculo());
        marcaveiculo.setText(a.getMarcaveiculo());
        tipoveiculo.setText(a.getTipoveiculo());


        return v;
    }
}
