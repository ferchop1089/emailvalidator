/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.icfes.emailvalidator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;

/**
 *
 * @author fernando
 */
@Slf4j
public class Starter {

    public static void main(String[] args) {
        String pathCsv = "depurado6.csv";

        Reader r;
        try {
            r = new FileReader(pathCsv);
            CsvClient<Registro> csvClient = new CsvClientImpl<>(r, Registro.class);
//            CsvClient<Registro> csvClient = new CsvClientImpl<>(r, new BeanInstructionsImpl(Registro.class)
//                                                                .mapColumnIndexToProperty(1, "usuaId")
//                                                                .mapColumnIndexToProperty(2, "errManual")
//                                                                .mapColumnIndexToProperty(3, "email1")
//                                                                .mapColumnIndexToProperty(4, "email2")
//                                                                .mapColumnIndexToProperty(5, "email3"));
            List<Registro> readBeans = csvClient.readBeans();
            log.info("Cont. Reg -> {}", readBeans.size());
//            readBeans.forEach(rg -> log.info("registro: {}", rg));
            readBeans.forEach(rg -> {
                test(rg.getUsuaId(), rg.getEmail1());
                test(rg.getUsuaId(), rg.getEmail2());
                test(rg.getUsuaId(), rg.getEmail3());
            });

            Writer w = new FileWriter("nuevo.csv");
            csvClient = new CsvClientImpl<>(w, Registro.class);
            csvClient.writeHeader(new String[]{"USUA_ID", "ERROR MANUAL", "USUA_EMAIL"});
            csvClient.writeRow(new String[]{"123", "pepito", "perez"});

        } catch (FileNotFoundException ex) {
            log.error("Err1", ex);
        } catch (IOException ex) {
            log.error("Err2", ex);
        }
    }

    private static void test(long usuaId, String email) {
        if (email == null || email.trim().isEmpty()) {
            return;
        }
        boolean valid = EmailValidator.getInstance().isValid(email);
        if (!valid) {
            log.info("uduaId: {} invalid email: {}", usuaId, email);
        }
    }

}
