package es.viewnext.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Estadistica {

    private static final Logger LOG = LoggerFactory.getLogger(Estadistica.class);

    private int erroresLectura = 0;
    private int lecturasCorrectas = 0;
    private int erroresProceso = 0;
    private int procesadosCorrectamente = 0;
    private int erroresEscritura = 0;
    private int escriturasCorrectas = 0;
    private int participantesProcesados = 0;
    private int participantesMayoresEdad = 0;
    private int participantesMenoresEdad = 0;
    private int participantesDuplicados = 0;

    public Estadistica(int erroresLectura, int lecturasCorrectas, int erroresProceso, int procesadosCorrectamente,
            int erroresEscritura, int escriturasCorrectas, int participantesProcesados, int participantesMayoresEdad,
            int participantesMenoresEdad, int participantesDuplicados) {
        this.erroresLectura = erroresLectura;
        this.lecturasCorrectas = lecturasCorrectas;
        this.erroresProceso = erroresProceso;
        this.procesadosCorrectamente = procesadosCorrectamente;
        this.erroresEscritura = erroresEscritura;
        this.escriturasCorrectas = escriturasCorrectas;
        this.participantesProcesados = participantesProcesados;
        this.participantesMayoresEdad = participantesMayoresEdad;
        this.participantesMenoresEdad = participantesMenoresEdad;
        this.participantesDuplicados = participantesDuplicados;
    }

    public Estadistica() {
    }

    public void mostrarEstadisticas() {
        LOG.info("Estadisticas: " + "\n Participantes leídos: " + lecturasCorrectas
                + "\n Participantes con errores en lectura: " + erroresLectura + "\n Participantes totales procesados: "
                + participantesProcesados + "\n Participantes procesados correctamente: " + procesadosCorrectamente
                + "\n Participantes con errores en procesado: " + erroresProceso
                + "\n Participantes insertados en la base de datos: " + escriturasCorrectas
                + "\n Participantes no insertados en la base de datos: "
                + (erroresProceso + erroresEscritura + participantesMenoresEdad + participantesDuplicados)
                + "\n Participantes con errores al insertar en la base de datos: " + erroresEscritura
                + "\n Participantes mayores de edad: " + participantesMayoresEdad + "\n Participantes menores de edad: "
                + participantesMenoresEdad + "\n Participantes duplicados: " + participantesDuplicados);
    }

    public int getErroresLectura() {
        return erroresLectura;
    }

    public void setErroresLectura(int errorLectura) {
        this.erroresLectura = errorLectura;
    }

    public int getLecturasCorrectas() {
        return lecturasCorrectas;
    }

    public void setLecturasCorrectas(int correctoLectura) {
        this.lecturasCorrectas = correctoLectura;
    }

    public int getErroresProceso() {
        return erroresProceso;
    }

    public void setErroresProceso(int errorProcesado) {
        this.erroresProceso = errorProcesado;
    }

    public int getProcesadosCorrectamente() {
        return procesadosCorrectamente;
    }

    public void setProcesadosCorrectamente(int correctoProcesado) {
        this.procesadosCorrectamente = correctoProcesado;
    }

    public int getErroresEscritura() {
        return erroresEscritura;
    }

    public void setErroresEcritura(int errorEcritura) {
        this.erroresEscritura = errorEcritura;
    }

    public int getEscriturasCorrectas() {
        return escriturasCorrectas;
    }

    public void setEscriturasCorrectas(int correctoEscritura) {
        this.escriturasCorrectas = correctoEscritura;
    }

    public int getParticipantesProcesados() {
        return participantesProcesados;
    }

    public void setParticipantesProcesados(int participantesProcesados) {
        this.participantesProcesados = participantesProcesados;
    }

    public int getParticipantesMayoresEdad() {
        return participantesMayoresEdad;
    }

    public void setParticipantesMayoresEdad(int participanteMayorEdad) {
        this.participantesMayoresEdad = participanteMayorEdad;
    }

    public int getParticipantesMenoresEdad() {
        return participantesMenoresEdad;
    }

    public void setParticipantesMenoresEdad(int participanteMenorEdad) {
        this.participantesMenoresEdad = participanteMenorEdad;
    }

    public int getParticipantesDuplicados() {
        return participantesDuplicados;
    }

    public void setParticipantesDuplicados(int participanteDuplicado) {
        this.participantesDuplicados = participanteDuplicado;
    }

}
