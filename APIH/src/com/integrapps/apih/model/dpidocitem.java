package com.integrapps.apih.model;

public class dpidocitem {

	   private String item = "";
	    private int cant = 0;
	    private String estante = "";
	    private String canasta = "";
	    private String ubica = "";
	    private String unidad = "";
	    private String peso = "";
	    private String pesotot = "";
	    private int val_id = 0;
	    private String embala = "";
	    private int docid = 0;
	    private String id = "";
	    private String emidtab = "";
	    private String bodega = "";
	    
	
	    public dpidocitem() {
	    }
	    
        
		public dpidocitem(String item, int cant, String estante, String canasta, String ubica, String unidad,
				String peso, String pesotot, int val_id, String embala, int docid, String id, String emidtab, String bodega) {
			this.item = item;
			this.cant = cant;
			this.estante = estante;
			this.canasta = canasta;
			this.ubica = ubica;
			this.unidad = unidad;
			this.peso = peso;
			this.pesotot = pesotot;
			this.val_id = val_id;
			this.embala = embala;
			this.docid = docid;
			this.id = id;
			this.emidtab = emidtab;
			this.bodega = bodega;
		}

		public String getItem() {
			return item;
		}

		public void setItem(String item) {
			this.item = item;
		}

		public int getCant() {
			return cant;
		}

		public void setCant(int cant) {
			this.cant = cant;
		}

		public String getEstante() {
			return estante;
		}

		public void setEstante(String estante) {
			this.estante = estante;
		}

		public String getCanasta() {
			return canasta;
		}

		public void setCanasta(String canasta) {
			this.canasta = canasta;
		}

		public String getUbica() {
			return ubica;
		}

		public void setUbica(String ubica) {
			this.ubica = ubica;
		}

		public String getUnidad() {
			return unidad;
		}

		public void setUnidad(String unidad) {
			this.unidad = unidad;
		}

		public String getPeso() {
			return peso;
		}

		public void setPeso(String peso) {
			this.peso = peso;
		}

		public String getPesotot() {
			return pesotot;
		}

		public void setPesotot(String pesotot) {
			this.pesotot = pesotot;
		}

		public int getVal_id() {
			return val_id;
		}

		public void setVal_id(int val_id) {
			this.val_id = val_id;
		}

		public String getEmbala() {
			return embala;
		}

		public void setEmbala(String embala) {
			this.embala = embala;
		}

		public int getDocid() {
			return docid;
		}

		public void setDocid(int docid) {
			this.docid = docid;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getEmidtab() {
			return emidtab;
		}

		public void setEmidtab(String emidtab) {
			this.emidtab = emidtab;
		}
	    
		public String getBodega() {
			return bodega;
		}

		public void setBodega(String bodega) {
			this.bodega = bodega;
		}
	    
}
