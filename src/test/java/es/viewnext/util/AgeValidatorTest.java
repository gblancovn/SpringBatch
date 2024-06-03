package es.viewnext.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import es.viewnext.domain.Participante;

public class AgeValidatorTest {

    @Test
    public void testAgeValidator_MayorDeEdad() {
        Participante participante = new Participante();
        participante.setFechaNacimiento(LocalDate.now().minusYears(28));
        assertTrue(Utils.validateAge(participante.getFechaNacimiento()));
    }

    @Test
    public void testAgeValidator_MenorDeEdad() {
        Participante participante = new Participante();
        participante.setFechaNacimiento(LocalDate.now().minusYears(10));
        assertFalse(Utils.validateAge(participante.getFechaNacimiento()));
    }

    @Test
    public void testAgeValidator_EdadExacta() {
        Participante participante = new Participante();
        participante.setFechaNacimiento(LocalDate.now().minusYears(18));
        assertTrue(Utils.validateAge(participante.getFechaNacimiento()));
    }
}
