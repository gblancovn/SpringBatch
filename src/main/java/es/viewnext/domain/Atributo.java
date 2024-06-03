package es.viewnext.domain;

public class Atributo {

    private int idAtributo;
    private int idParticipante;
    private int orden;
    private String valor;

    public Atributo(int idParticipante, int orden, String valor) {
        this.idParticipante = idParticipante;
        this.orden = orden;
        this.valor = valor;
    }

    public Atributo() {
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getIdAtributo() {
        return idAtributo;
    }

    public void setIdAtributo(int idAtributo) {
        this.idAtributo = idAtributo;
    }

}
