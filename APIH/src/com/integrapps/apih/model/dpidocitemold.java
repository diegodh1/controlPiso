package com.integrapps.apih.model;

public class dpidocitemold {
    private String item="";
    private int cant=0;
    private String estante="";
    private String canasta="";
    private String ubica="";
    private String unidad="";
    private String peso="";
    private String pesotot="";
    private String val_id="";
    private String embala="";
    
    
    public dpidocitemold() {
    }
    
        public dpidocitemold(String item, int cant, String estante, String canasta, String ubica,
                          String unidad, String peso,  String val_id, String embala) {
        this.item = item.trim();
        this.cant = cant;
        this.estante = estante.trim();
        this.canasta = canasta.trim();
        this.ubica = ubica.trim();
        this.unidad = unidad.trim();
        this.peso = peso;
        this.pesotot = String.valueOf(cant * Integer.valueOf(peso));
        this.val_id = val_id;
        this.embala = embala.trim();
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the cant
     */
    public int getCant() {
        return cant;
    }

    /**
     * @param cant the cant to set
     */
    public void setCant(int cant) {
        this.cant = cant;
    }

    /**
     * @return the estante
     */
    public String getEstante() {
        return estante;
    }

    /**
     * @param estante the estante to set
     */
    public void setEstante(String estante) {
        this.estante = estante;
    }

    /**
     * @return the canasta
     */
    public String getCanasta() {
        return canasta;
    }

    /**
     * @param canasta the canasta to set
     */
    public void setCanasta(String canasta) {
        this.canasta = canasta;
    }

    /**
     * @return the ubica
     */
    public String getUbica() {
        return ubica;
    }

    /**
     * @param ubica the ubica to set
     */
    public void setUbica(String ubica) {
        this.ubica = ubica;
    }
    
    @Override
     public String toString() {
        return "{\"cant\":" + cant + ",\"val_id\":\"" + val_id +
               "\",\"estante\":\"" + estante + "\", \"canasta\":\"" + canasta + 
                "\",\"ubica\":\"" + ubica + "\", \"item\":\"" + item + 
                "\",\"unidad\":\"" + unidad + "\", \"peso\":\"" + peso + 
                "\",\"pesotot\":\"" + pesotot +  
                "\", \"embala\":\"" + embala + "\"}";
    }
   
 
    /**
     * @return the unidad
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the peso
     */
    public String getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(String peso) {
        this.peso = peso;
    }

    /**
     * @return the pesotot
     */
    public String getPesotot() {
        return pesotot;
    }

    /**
     * @param pesotot the pesotot to set
     */
    public void setPesotot(String pesotot) {
        this.pesotot = pesotot;
    }

    
  
    public String getVal_id() {
		return val_id;
	}

	public void setVal_id(String val_id) {
		this.val_id = val_id;
	}

	/**
     * @return the embala
     */
    public String getEmbala() {
        return embala;
    }

    /**
     * @param embala the embala to set
     */
    public void setEmbala(String embala) {
        this.embala = embala;
    }
}
