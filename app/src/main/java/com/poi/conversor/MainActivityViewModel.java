package com.poi.conversor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Boolean> activoEtDolar;
    private MutableLiveData<Boolean> activoEtEuro;
    private MutableLiveData<Boolean> activoRbDaE;
    private MutableLiveData<Boolean> activoRbEaD;
    private MutableLiveData<String> resultadoMutable;

    public LiveData<Boolean> getActivoEtDolar(){
        if (activoEtDolar==null){
            activoEtDolar = new MutableLiveData<>();
        }
        return activoEtDolar;
    }
    public LiveData<Boolean> getActivoEtEuro(){
        if (activoEtEuro==null){
            activoEtEuro = new MutableLiveData<>();
        }
        return activoEtEuro;
    }
    public LiveData<Boolean> getActivoRbDaE(){
        if (activoRbDaE==null){
            activoRbDaE = new MutableLiveData<>();
        }
        return activoRbDaE;
    }
    public LiveData<Boolean> getActivoRbEaD(){
        if (activoRbEaD==null){
            activoRbEaD = new MutableLiveData<>();
        }
        return activoRbEaD;
    }
    public LiveData<String> getResultadoMutable() {
        if (resultadoMutable==null){
            resultadoMutable = new MutableLiveData<>();
        }
        return resultadoMutable;
    }

    public void cambiarEstadoET(int idView, int idRbDaE, int idRbEaD) {
        if ( idView == idRbDaE) {
            activoEtDolar.setValue(true);
            activoEtEuro.setValue(false);
        } else {
            activoEtDolar.setValue(false);
            activoEtEuro.setValue(true);
        }
    }

    public void calcular(Boolean rbDolares, String etDorales, String etEuros) {
        try {
            double res;
            if (rbDolares) {
                // convertir de dolares a euros
                double dolar = Double.parseDouble(etDorales);
                res = dolar * 0.85;
                resultadoMutable.setValue(res + " euros");
            } else {
                // convertir de euros a dolares
                double euro = Double.parseDouble(etEuros);
                res = euro * 1.18;
                resultadoMutable.setValue(res + " dolares");
            }
        } catch (Exception ex) {
            resultadoMutable.setValue("No es un numero");
        }
    }
}
