package es.viewnext.domain;

import java.time.LocalDate;

//TODO mirar eliminar esta clase o no, revisar funcionalidad
public class DatosPersonalesParticipante {

    private String email;
    private LocalDate fechaNacimiento;

    public DatosPersonalesParticipante(String email, LocalDate fechaNacimiento) {
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public DatosPersonalesParticipante() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
