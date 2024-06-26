package es.viewnext.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.viewnext.domain.Atributo;
import es.viewnext.domain.Participante;
import es.viewnext.util.Utils;

public class GuardarParticipanteDescartado {

    private static final Logger LOG = LoggerFactory.getLogger(GuardarParticipanteDescartado.class);

    /**
     * Escribe la información de un participante descartado en un archivo CSV.
     * 
     * @param participante el participante a escribir en el archivo
     */
    public void write(Participante participante) {
        if (participante != null) {
            File tempFile = new File("temp.csv");
            File targetFile = new File("src/main/resources/csv/Descartados.csv");

            try {
                List<String> lines = new ArrayList<>();

                if (targetFile.exists()) {
                    lines = Files.readAllLines(targetFile.toPath());
                }

                String atributosString = "";

                for (Atributo atributo : participante.getAtributos()) {
                    atributosString += ",\"" + atributo.getValor().toUpperCase() + "\"";
                }

                lines.add(String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%s", participante.getNombre(),
                        participante.getApellido1() + " " + participante.getApellido2(), participante.getEmail(),
                        participante.getIdioma(), Utils.dateToString(participante.getFechaInicio()),
                        Utils.dateToString(participante.getFechaFinalizacion()), atributosString));

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
                    for (String line : lines) {
                        bw.write(line + "\n");
                    }

                    bw.flush();

                }

                if (targetFile.exists()) {
                    targetFile.delete();
                }

                FileUtils.moveFile(tempFile, targetFile);

            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
    }
}
