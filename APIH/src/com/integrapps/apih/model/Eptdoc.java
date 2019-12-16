package com.integrapps.apih.model;

public class Eptdoc {

	private int		ideptdoc,num_doc_pt,idcanastilla,idestacion,idbodegadestino,idusuario,estado_doc,idorganizacion,ind_cierre;
	private String strtipo,barcodem,barcodeh,ept_co,fechacrea,fechaedit,usucrea,usuedit,tipo_doc_ept_erp,numero_doc_ept_erp,fecha_doc_ept_erp,fecha_envio_doc_ept,bodegadestino;
	private double  cant_requerida;	      
	          
    
	public Eptdoc() {
	
	}

	


	public Eptdoc(int ideptdoc, int ind_cierre, String strtipo, String usuedit,String barcodeh) {
	    this.ideptdoc = ideptdoc;
		this.ind_cierre = ind_cierre;
		this.strtipo = strtipo;
		this.usuedit = usuedit;
		this.barcodeh = barcodeh;
	}


	public String getBodegadestino() {
		return bodegadestino;
	}




	public void setBodegadestino(String bodegadestino) {
		this.bodegadestino = bodegadestino;
	}




	public int getIdeptdoc() {
		return ideptdoc;
	}


	public void setIdeptdoc(int ideptdoc) {
		this.ideptdoc = ideptdoc;
	}


	public int getIdcanastilla() {
		return idcanastilla;
	}


	public void setIdcanastilla(int idcanastilla) {
		this.idcanastilla = idcanastilla;
	}


	public int getIdestacion() {
		return idestacion;
	}


	public void setIdestacion(int idestacion) {
		this.idestacion = idestacion;
	}


	public int getIdbodegadestino() {
		return idbodegadestino;
	}


	public void setIdbodegadestino(int idbodegadestino) {
		this.idbodegadestino = idbodegadestino;
	}


	public int getIdusuario() {
		return idusuario;
	}


	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}


	public int getEstado_doc() {
		return estado_doc;
	}


	public void setEstado_doc(int estado_doc) {
		this.estado_doc = estado_doc;
	}


	public int getIdorganizacion() {
		return idorganizacion;
	}


	public void setIdorganizacion(int idorganizacion) {
		this.idorganizacion = idorganizacion;
	}


	public int getInd_cierre() {
		return ind_cierre;
	}


	public void setInd_cierre(int ind_cierre) {
		this.ind_cierre = ind_cierre;
	}


	public String getStrtipo() {
		return strtipo;
	}


	public void setStrtipo(String strtipo) {
		this.strtipo = strtipo;
	}


	public String getBarcodem() {
		return barcodem;
	}


	public void setBarcodem(String barcodem) {
		this.barcodem = barcodem;
	}


	public String getBarcodeh() {
		return barcodeh;
	}


	public void setBarcodeh(String barcodeh) {
		this.barcodeh = barcodeh;
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


	public String getTipo_doc_ept_erp() {
		return tipo_doc_ept_erp;
	}


	public void setTipo_doc_ept_erp(String tipo_doc_ept_erp) {
		this.tipo_doc_ept_erp = tipo_doc_ept_erp;
	}


	public String getNumero_doc_ept_erp() {
		return numero_doc_ept_erp;
	}


	public void setNumero_doc_ept_erp(String numero_doc_ept_erp) {
		this.numero_doc_ept_erp = numero_doc_ept_erp;
	}


	public String getFecha_doc_ept_erp() {
		return fecha_doc_ept_erp;
	}


	public void setFecha_doc_ept_erp(String fecha_doc_ept_erp) {
		this.fecha_doc_ept_erp = fecha_doc_ept_erp;
	}


	public String getFecha_envio_doc_ept() {
		return fecha_envio_doc_ept;
	}


	public void setFecha_envio_doc_ept(String fecha_envio_doc_ept) {
		this.fecha_envio_doc_ept = fecha_envio_doc_ept;
	}


	public double getCant_requerida() {
		return cant_requerida;
	}


	public void setCant_requerida(double cant_requerida) {
		this.cant_requerida = cant_requerida;
	}


	public int getNum_doc_pt() {
		return num_doc_pt;
	}


	public void setNum_doc_pt(int num_doc_pt) {
		this.num_doc_pt = num_doc_pt;
	}


	public String getEpt_co() {
		return ept_co;
	}


	public void setEpt_co(String ept_co) {
		this.ept_co = ept_co;
	}


	@Override
	public String toString() {
		return "Eptdoc {ideptdoc=" + ideptdoc + ", idorganizacion=" + idorganizacion + ", num_doc_pt=" + num_doc_pt
				+ ", idcanastilla=" + idcanastilla + ", idestacion=" + idestacion + ", idbodegadestino="
				+ idbodegadestino + ", idusuario=" + idusuario + ", estado_doc=" + estado_doc + ", ind_cierre="
				+ ind_cierre + ", strtipo=" + strtipo + ", barcodem=" + barcodem + ", barcodeh=" + barcodeh
				+ ", ept_co=" + ept_co + ", fechacrea=" + fechacrea + ", fechaedit=" + fechaedit + ", usucrea="
				+ usucrea + ", usuedit=" + usuedit + ", tipo_doc_ept_erp=" + tipo_doc_ept_erp + ", numero_doc_ept_erp="
				+ numero_doc_ept_erp + ", fecha_doc_ept_erp=" + fecha_doc_ept_erp + ", fecha_envio_doc_ept="
				+ fecha_envio_doc_ept + ", cant_requerida=" + cant_requerida + ", bodegadestino =" + bodegadestino + "}";
	}



	
	

}
