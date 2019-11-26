package com.example.socialmotors;

import java.io.Serializable;

public class veiculo implements Serializable {

    private Integer id;
    private String marcaveiculo;
    private String modeloveiculo;
    private String tipoveiculo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarcaveiculo() {
        return marcaveiculo;
    }

    public void setMarcaveiculo(String marcaveiculo) {
        this.marcaveiculo = marcaveiculo;
    }

    public String getModeloveiculo() {
        return modeloveiculo;
    }

    public void setModeloveiculo(String modeloveiculo) {
        this.modeloveiculo = modeloveiculo;
    }

    public String getTipoveiculo() {
        return tipoveiculo;
    }

    public void setTipoveiculo(String tipoveiculo) {
        this.tipoveiculo = tipoveiculo;
    }

    @Override

    public String toString(){

        return modeloveiculo;

    }



}
