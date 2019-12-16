package com.integrapps.apih.model;

public class dtuencabezado {
    private int iddtu=0;
    private String optbarcode="";
    private int optest=0;
    private String optusu="";
    private String optfec="";
    
    public dtuencabezado() {
    }
        
    public dtuencabezado(int iddtu, String optbarcode, int optest, String optusu, String optfec) {
        this.iddtu = iddtu;
        this.optbarcode = optbarcode;
        this.optest = optest;
        this.optusu = optusu;
        this.optfec = optfec;
    }

    /**
     * @return the iddtu
     */
    public int getIddtu() {
        return iddtu;
    }

    /**
     * @param iddtu the iddtu to set
     */
    public void setIddtu(int iddtu) {
        this.iddtu = iddtu;
    }

    /**
     * @return the optbarcode
     */
    public String getOptbarcode() {
        return optbarcode;
    }

    /**
     * @param optbarcode the optbarcode to set
     */
    public void setOptbarcode(String optbarcode) {
        this.optbarcode = optbarcode;
    }

    /**
     * @return the optest
     */
    public int getOptest() {
        return optest;
    }

    /**
     * @param optest the optest to set
     */
    public void setOptest(int optest) {
        this.optest = optest;
    }

    /**
     * @return the optusu
     */
    public String getOptusu() {
        return optusu;
    }

    /**
     * @param optusu the optusu to set
     */
    public void setOptusu(String optusu) {
        this.optusu = optusu;
    }

    /**
     * @return the optfec
     */
    public String getOptfec() {
        return optfec;
    }

    /**
     * @param optfec the optfec to set
     */
    public void setOptfec(String optfec) {
        this.optfec = optfec;
    }
    
    @Override
    public String toString() {
        return "{\"iddtu\":" + iddtu + ",\"optest\":" + optest +
               ",\"optbarcode\":\"" + optbarcode + "\", \"optusu\":\"" + optusu + 
                "\",\"optfec\":\"" + optfec + "\"}";
    }
}
