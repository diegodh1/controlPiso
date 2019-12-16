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
public class usuario {
    private int idorganizacion=0,numactivo=0,idMnu_Grp=0,idusuario=0;
    private String strusuario="",strpassword="",strnombre="",numidentificacion="",stremail="",strtelefono="",fechacrea="",fechaedit="",usucrea="",usuedit="",codbarmaq="",codbarhum="",nombreorg="";

    public usuario() {
    }

    public usuario(int idorganizacion, int numactivo, int idMnu_Grp, int idusuario, String strusuario, String strpassword, String strnombre, String numidentificacion, String stremail, String strtelefono, String fechacrea, String fechaedit, String usucrea, String usuedit, String codbarmaq, String codbarhum, String nombreorg) {
        this.idorganizacion = idorganizacion;
        this.numactivo = numactivo;
        this.idMnu_Grp = idMnu_Grp;
        this.idusuario = idusuario;
        this.strusuario = strusuario;
        this.strpassword = strpassword;
        this.strnombre = strnombre;
        this.numidentificacion = numidentificacion;
        this.stremail = stremail;
        this.strtelefono = strtelefono;
        this.fechacrea = fechacrea;
        this.fechaedit = fechaedit;
        this.usucrea = usucrea;
        this.usuedit = usuedit;
        this.codbarmaq = codbarmaq;
        this.codbarhum = codbarhum;
        this.nombreorg = nombreorg;
    }

    public String getNombreorg() {
        return nombreorg;
    }

    public void setNombreorg(String nombreorg) {
        this.nombreorg = nombreorg;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdorganizacion() {
        return idorganizacion;
    }

    public void setIdorganizacion(int idorganizacion) {
        this.idorganizacion = idorganizacion;
    }

    public int getNumactivo() {
        return numactivo;
    }

    public void setNumactivo(int numactivo) {
        this.numactivo = numactivo;
    }

    public int getIdMnu_Grp() {
        return idMnu_Grp;
    }

    public void setIdMnu_Grp(int idMnu_Grp) {
        this.idMnu_Grp = idMnu_Grp;
    }

    public String getStrusuario() {
        return strusuario;
    }

    public void setStrusuario(String strusuario) {
        this.strusuario = strusuario;
    }

    public String getStrpassword() {
        return strpassword;
    }

    public void setStrpassword(String strpassword) {
        this.strpassword = strpassword;
    }

    public String getStrnombre() {
        return strnombre;
    }

    public void setStrnombre(String strnombre) {
        this.strnombre = strnombre;
    }

    public String getNumidentificacion() {
        return numidentificacion;
    }

    public void setNumidentificacion(String numidentificacion) {
        this.numidentificacion = numidentificacion;
    }

    public String getStremail() {
        return stremail;
    }

    public void setStremail(String stremail) {
        this.stremail = stremail;
    }

    public String getStrtelefono() {
        return strtelefono;
    }

    public void setStrtelefono(String strtelefono) {
        this.strtelefono = strtelefono;
    }

    public String getFechacrea() {
        return fechacrea;
    }

    public void setFechacrea(String fechacrea) {
        this.fechacrea = fechacrea;
    }

    public String getFechaedit() {
        return fechaedit;
    }

    public void setFechaedit(String fechaedit) {
        this.fechaedit = fechaedit;
    }

    public String getUsucrea() {
        return usucrea;
    }

    public void setUsucrea(String usucrea) {
        this.usucrea = usucrea;
    }

    public String getUsuedit() {
        return usuedit;
    }

    public void setUsuedit(String usuedit) {
        this.usuedit = usuedit;
    }

    public String getCodbarmaq() {
        return codbarmaq;
    }

    public void setCodbarmaq(String codbarmaq) {
        this.codbarmaq = codbarmaq;
    }

    public String getCodbarhum() {
        return codbarhum;
    }

    public void setCodbarhum(String codbarhum) {
        this.codbarhum = codbarhum;
    }


   
   
    
    
    
}
