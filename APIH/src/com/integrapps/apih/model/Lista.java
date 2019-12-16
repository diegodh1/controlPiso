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
public class Lista {
    
    private double id;
    private String nombre="";

    public Lista() {
    }

    public Lista(double id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	@Override
	public String toString() {
		return "Lista {id=" + id + ", nombre=" + nombre + "}";
	}   
    
}
