package es.viewnext.domain;

public class Estadistica {

    private int errorLectura = 0;
    private int correctoLectura = 0;
    private int errorProcesado = 0;
    private int correctoProcesado = 0;
    private int errorEcritura = 0;
    private int correctoEscritura = 0;

    public Estadistica(int errorLectura, int correctoLectura, int errorProcesado, int correctoProcesado,
            int errorEcritura, int correctoEscritura) {
        this.errorLectura = errorLectura;
        this.correctoLectura = correctoLectura;
        this.errorProcesado = errorProcesado;
        this.correctoProcesado = correctoProcesado;
        this.errorEcritura = errorEcritura;
        this.correctoEscritura = correctoEscritura;
    }

    public Estadistica() {
    }

    public int getErrorLectura() {
        return errorLectura;
    }

    public void setErrorLectura(int errorLectura) {
        this.errorLectura = errorLectura;
    }

    public int getCorrectoLectura() {
        return correctoLectura;
    }

    public void setCorrectoLectura(int correctoLectura) {
        this.correctoLectura = correctoLectura;
    }

    public int getErrorProcesado() {
        return errorProcesado;
    }

    public void setErrorProcesado(int errorProcesado) {
        this.errorProcesado = errorProcesado;
    }

    public int getCorrectoProcesado() {
        return correctoProcesado;
    }

    public void setCorrectoProcesado(int correctoProcesado) {
        this.correctoProcesado = correctoProcesado;
    }

    public int getErrorEcritura() {
        return errorEcritura;
    }

    public void setErrorEcritura(int errorEcritura) {
        this.errorEcritura = errorEcritura;
    }

    public int getCorrectoEscritura() {
        return correctoEscritura;
    }

    public void setCorrectoEscritura(int correctoEscritura) {
        this.correctoEscritura = correctoEscritura;
    }

    @Override
    public String toString() {
        return "\n Estadistica [ \n //LECTURA// \n Error Lectura = " + errorLectura + " \n Correcto Lectura = " + correctoLectura
                + "\n //PROCESADO//  \n Error Procesado = " + errorProcesado + "\n correcto Procesado = " + correctoProcesado + 
                "\n //ESCRITURA// \n Error Ecritura = " + errorEcritura + "\n Correcto Escritura = " + correctoEscritura + " ]";
    }

}
