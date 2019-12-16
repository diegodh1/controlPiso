/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrapps.apih.model;

/**
 *
 * @author User
 */
public class Listaitem {
    
    private String item="",descripcion="",valor1="",valor2="",valor3="",valor4="",valor5="";

    public Listaitem() {
    }

    public Listaitem(String item, String descripcion, String valor1, String valor2, String valor3, String valor4, String valor5) {
        this.item = item;
        this.descripcion = descripcion;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.valor3 = valor3;
        this.valor4 = valor4;
        this.valor5 = valor5;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getValor1() {
        return valor1;
    }

    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }
    
    public String getValor2() {
        return valor2;
    }

    public void setValor2(String valor2) {
        this.valor2 = valor2;
    }
    
    public String getValor3() {
        return valor3;
    }

    public void setValor3(String valor3) {
        this.valor3 = valor3;
    }
    
    public String getValor4() {
        return valor4;
    }

    public void setValor4(String valor4) {
        this.valor4 = valor4;
    }
    
    public String getValor5() {
        return valor5;
    }

    public void setValor5(String valor5) {
        this.valor5 = valor5;
    }

	@Override
	public String toString() {
		return "Lista {item=" + item + ", descripcion=" + descripcion + ", valor1=" + valor1 + ", valor2=" + valor2 + ", valor3=" + valor3 + ", valor4=" + valor4 + ", valor5=" + valor5 + "}";
	}   
    
}
