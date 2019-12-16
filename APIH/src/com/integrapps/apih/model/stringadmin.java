package com.integrapps.apih.model;

public class stringadmin {

    public stringadmin() {
    }
    
    public String AdicionaCeros(String cadInicial , int longFinal){
        int actlong;
        int diflong;
        String resCad;
        resCad = cadInicial.trim();
        actlong = resCad.length();
        diflong = longFinal - actlong;

        if(diflong > 0){
            for(int x=0;x < diflong;x++){ 
                resCad = "0" + resCad;
            }
        }

        return resCad;
    }
}
