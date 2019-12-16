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
public class pedido {
    private int idcliente=0,idpedido=0;
    private String tipo="";
    private String nombrePedido="";
    private String nombreCliente="";

    public pedido() {
    }

    public pedido(int idpedido,int idcliente, String tipo, String nombrePedido, String nombreCliente) {
        this.tipo = tipo;
        this.idpedido = idpedido;
        this.idcliente = idcliente;
        this.nombrePedido = nombrePedido;
        this.nombreCliente = nombreCliente;
    }

    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getNombrePedido() {
        return nombrePedido;
    }

    public void setNombrePedido(String nombrePedido) {
        this.nombrePedido = nombrePedido;
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }
    
    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }
  
    
}
