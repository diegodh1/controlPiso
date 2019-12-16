package com.integrapps.apih.model;

public class mmto_canastilla {
	private String mmtoId,organizacionId,canastillaId,bodegaOrigenId,bodegaDestinoId,cumplido,fcRegistro,feRegistro,usuarioCrea,usuarioEdita;
	
	public mmto_canastilla() {
    }

	public mmto_canastilla(String mmtoId, String organizacionId, String canastillaId, String bodegaOrigenId,
			String bodegaDestinoId, String cumplido, String fcRegistro, String feRegistro, String usuarioCrea,
			String usuarioEdita) {
		this.mmtoId = mmtoId;
		this.organizacionId = organizacionId;
		this.canastillaId = canastillaId;
		this.bodegaOrigenId = bodegaOrigenId;
		this.bodegaDestinoId = bodegaDestinoId;
		this.cumplido = cumplido;
		this.fcRegistro = fcRegistro;
		this.feRegistro = feRegistro;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
	}

	public String getMmtoId() {
		return mmtoId;
	}

	public void setMmtoId(String mmtoId) {
		this.mmtoId = mmtoId;
	}

	public String getOrganizacionId() {
		return organizacionId;
	}

	public void setOrganizacionId(String organizacionId) {
		this.organizacionId = organizacionId;
	}

	public String getCanastillaId() {
		return canastillaId;
	}

	public void setCanastillaId(String canastillaId) {
		this.canastillaId = canastillaId;
	}

	public String getBodegaOrigenId() {
		return bodegaOrigenId;
	}

	public void setBodegaOrigenId(String bodegaOrigenId) {
		this.bodegaOrigenId = bodegaOrigenId;
	}

	public String getBodegaDestinoId() {
		return bodegaDestinoId;
	}

	public void setBodegaDestinoId(String bodegaDestinoId) {
		this.bodegaDestinoId = bodegaDestinoId;
	}

	public String getCumplido() {
		return cumplido;
	}

	public void setCumplido(String cumplido) {
		this.cumplido = cumplido;
	}

	public String getFcRegistro() {
		return fcRegistro;
	}

	public void setFcRegistro(String fcRegistro) {
		this.fcRegistro = fcRegistro;
	}

	public String getFeRegistro() {
		return feRegistro;
	}

	public void setFeRegistro(String feRegistro) {
		this.feRegistro = feRegistro;
	}

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public String getUsuarioEdita() {
		return usuarioEdita;
	}

	public void setUsuarioEdita(String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}

	@Override
	public String toString() {
		return "mmto_canastilla [mmtoId=" + mmtoId + ", organizacionId=" + organizacionId + ", canastillaId="
				+ canastillaId + ", bodegaOrigenId=" + bodegaOrigenId + ", bodegaDestinoId=" + bodegaDestinoId
				+ ", cumplido=" + cumplido + ", fcRegistro=" + fcRegistro + ", feRegistro=" + feRegistro
				+ ", usuarioCrea=" + usuarioCrea + ", usuarioEdita=" + usuarioEdita + "]";
	}
	
	
	

}
