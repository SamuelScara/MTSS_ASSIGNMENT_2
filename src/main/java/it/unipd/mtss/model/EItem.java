////////////////////////////////////////////////////////////////////
// [Samuel] [Scarabottolo] [2012435]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import it.unipd.mtss.business.Bill;

import java.util.List;



public class EItem {
    public enum item {Processore, Scheda_Madre, Tastiera, Mouse};

    private item tipoItem;
    private String nome;
    private double price;

    public EItem(item tipoItem, String nome, double price){
        this.tipoItem = tipoItem;
        this.nome = nome;
        if(price >= 0.0D){
            this.price = price;
        }else{
            throw new IllegalArgumentException("Il prezzo deve per forza essere >=0");
        }
    }
    public item getTipoItem(){
        return tipoItem;
    }

    public String getNome(){
        return nome;
    }

    public double getPrice(){
        return price;
    }
}