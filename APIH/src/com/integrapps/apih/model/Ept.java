package com.integrapps.apih.model;

public class Ept {

	private int idept,idorganizacion,idusuario,ideptdoc,idop,item,impreso,reimpreso,leido,idbodegadestino;
	private double cantenviada,cantubicada;
	private String 	 stretiqueta, barcdem ,barcodeh,strbodega,strubicacion,strlocalizacion,fechacrea,fechaedit,enviado,usuacrea,usuedit,numop,coditem,descripcionitem,strcanasta;
	      
	public Ept() {
		// TODO Auto-generated constructor stub
	}

	
	public String getStrcanasta() {
		return strcanasta;
	}


	public void setStrcanasta(String strcanasta) {
		this.strcanasta = strcanasta;
	}


	public String getStrlocalizacion() {
		return strlocalizacion;
	}


	public void setStrlocalizacion(String strlocalizacion) {
		this.strlocalizacion = strlocalizacion;
	}


	public double getCantubicada() {
		return cantubicada;
	}


	public void setCantubicada(double cantubicada) {
		this.cantubicada = cantubicada;
	}


	public String getNumop() {
		return numop;
	}

	public void setNumop(String numop) {
		this.numop = numop;
	}

	public String getCoditem() {
		return coditem;
	}

	public void setCoditem(String coditem) {
		this.coditem = coditem;
	}

	public String getDescripcionitem() {
		return descripcionitem;
	}

	public void setDescripcionitem(String descripcionitem) {
		this.descripcionitem = descripcionitem;
	}

	public int getIdept() {
		return idept;
	}

	public void setIdept(int idept) {
		this.idept = idept;
	}

	public int getIdorganizacion() {
		return idorganizacion;
	}

	public void setIdorganizacion(int idorganizacion) {
		this.idorganizacion = idorganizacion;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public int getIdeptdoc() {
		return ideptdoc;
	}

	public void setIdeptdoc(int ideptdoc) {
		this.ideptdoc = ideptdoc;
	}

	public int getIdop() {
		return idop;
	}

	public void setIdop(int idop) {
		this.idop = idop;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getImpreso() {
		return impreso;
	}

	public void setImpreso(int impreso) {
		this.impreso = impreso;
	}

	public int getReimpreso() {
		return reimpreso;
	}

	public void setReimpreso(int reimpreso) {
		this.reimpreso = reimpreso;
	}

	public int getLeido() {
		return leido;
	}

	public void setLeido(int leido) {
		this.leido = leido;
	}

	public int getIdbodegadestino() {
		return idbodegadestino;
	}

	public void setIdbodegadestino(int idbodegadestino) {
		this.idbodegadestino = idbodegadestino;
	}

	public double getCantenviada() {
		return cantenviada;
	}

	public void setCantenviada(double cantenviada) {
		this.cantenviada = cantenviada;
	}

	public String getStretiqueta() {
		return stretiqueta;
	}

	public void setStretiqueta(String stretiqueta) {
		this.stretiqueta = stretiqueta;
	}

	public String getBarcdem() {
		return barcdem;
	}

	public void setBarcdem(String barcdem) {
		this.barcdem = barcdem;
	}

	public String getBarcodeh() {
		return barcodeh;
	}

	public void setBarcodeh(String barcodeh) {
		this.barcodeh = barcodeh;
	}


	public String getStrbodega() {
		return strbodega;
	}


	public void setStrbodega(String strbodega) {
		this.strbodega = strbodega;
	}


	public String getStrubicacion() {
		return strubicacion;
	}

	public void setStrubicacion(String strubicacion) {
		this.strubicacion = strubicacion;
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

	public String getEnviado() {
		return enviado;
	}

	public void setEnviado(String enviado) {
		this.enviado = enviado;
	}

	public String getUsuacrea() {
		return usuacrea;
	}

	public void setUsuacrea(String usuacrea) {
		this.usuacrea = usuacrea;
	}

	public String getUsuedit() {
		return usuedit;
	}

	public void setUsuedit(String usuedit) {
		this.usuedit = usuedit;
	}


	@Override
	public String toString() {
		return "Ept {idept=" + idept + ", idorganizacion=" + idorganizacion + ", idusuario=" + idusuario + ", ideptdoc="
				+ ideptdoc + ", idop=" + idop + ", item=" + item + ", impreso=" + impreso + ", reimpreso=" + reimpreso
				+ ", leido=" + leido + ", idbodegadestino=" + idbodegadestino + ", cantenviada=" + cantenviada
				+ ", cantubicada=" + cantubicada + ", stretiqueta=" + stretiqueta + ", barcdem=" + barcdem
				+ ", barcodeh=" + barcodeh + ", strbodega=" + strbodega + ", strubicacion=" + strubicacion
				+ ", strlocalizacion=" + strlocalizacion + ", fechacrea=" + fechacrea + ", fechaedit=" + fechaedit
				+ ", enviado=" + enviado + ", usuacrea=" + usuacrea + ", usuedit=" + usuedit + ", numop=" + numop
				+ ", coditem=" + coditem + ", descripcionitem=" + descripcionitem + "}";
	}





}
