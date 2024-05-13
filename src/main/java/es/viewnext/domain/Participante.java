package es.viewnext.domain;

import java.util.Date;

public class Participante {

    public Participante(int idParticipante, String numper, String matricula, String nif, String nombre,
            String apellido1, String apellido2, String email, String prefijoTelefonoMovil, String telefonoMovil,
            Date fechaInicio, Date fechaFinalizacion, String idioma) {
        this.idParticipante = idParticipante;
        this.numper = numper;
        this.matricula = matricula;
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.prefijoTelefonoMovil = prefijoTelefonoMovil;
        this.telefonoMovil = telefonoMovil;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.idioma = idioma;
    }

    public Participante() {
    }

    private int idParticipante;
    private String numper;
    private String matricula;
    private String nif;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String prefijoTelefonoMovil;
    private String telefonoMovil;
    private Date fechaInicio;
    private Date fechaFinalizacion;
    private String idioma;

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNumper() {
        return numper;
    }

    public void setNumper(String numper) {
        this.numper = numper;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrefijoTelefonoMovil() {
        return prefijoTelefonoMovil;
    }

    public void setPrefijoTelefonoMovil(String prefijoTelefonoMovil) {
        this.prefijoTelefonoMovil = prefijoTelefonoMovil;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
