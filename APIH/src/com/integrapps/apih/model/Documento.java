package com.integrapps.apih.model;

import java.util.ArrayList;

public class Documento {

	
	private String dtubarcode,dtufechacrea,dtuusu;
	private int iddtu;
	private ArrayList<Ept> ept;
	
	public Documento() {
		// TODO Auto-generated constructor stub
	}

	public Documento(String dtubarcode, String dtufechacrea, String dtuusu,int iddtu,ArrayList<Ept> ept) {
		this.dtubarcode = dtubarcode;
		this.dtufechacrea = dtufechacrea;
		this.dtuusu = dtuusu;
		this.iddtu=iddtu;
		this.ept = ept;
	}

	public String getDtubarcode() {
		return dtubarcode;
	}

	public void setDtubarcode(String dtubarcode) {
		this.dtubarcode = dtubarcode;
	}

	public String getDtufechacrea() {
		return dtufechacrea;
	}

	public void setDtufechacrea(String dtufechacrea) {
		this.dtufechacrea = dtufechacrea;
	}

	public String getDtuusu() {
		return dtuusu;
	}

	public void setDtuusu(String dtuusu) {
		this.dtuusu = dtuusu;
	}

	public int getIddtu() {
		return iddtu;
	}

	public void setIddtu(int iddtu) {
		this.iddtu = iddtu;
	}

	public ArrayList<Ept> getEpt() {
		return ept;
	}

	public void setEpt(ArrayList<Ept> ept) {
		this.ept = ept;
	}

	@Override
	public String toString() {
		return "Documento {dtubarcode=" + dtubarcode + ", dtufechacrea=" + dtufechacrea + ", dtuusu=" + dtuusu
				+ ", iddtu=" + iddtu + ", ept=" + ept + "}";
	}
	
	

}
