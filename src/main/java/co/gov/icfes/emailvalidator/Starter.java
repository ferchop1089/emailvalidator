/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.icfes.emailvalidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;
import org.csveed.bean.conversion.EasyAbstractConverter;

/**
 *
 * @author fernando
 */
@Slf4j
public class Starter {

    public static void main(String[] args) {
        archivo();
//        testEmail();
    }

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static void testEmail() {
        String email = "ceneduca±oviejo@semmonteria.gov.co";
        log.info("test email1: {}", EmailValidator.getInstance().isValid(email));
        log.info("test email2: {}", EmailValidator.getInstance(false).isValid(email));
        log.info("test email3: {}", EmailValidator.getInstance(true).isValid(email));
        log.info("test email4: {}", EmailValidator.getInstance(false, false).isValid(email));
        log.info("test email5: {}", EmailValidator.getInstance(false, true).isValid(email));
        log.info("test email6: {}", EmailValidator.getInstance(true, true).isValid(email));
        log.info("test email7: {}", EmailValidator.getInstance(true, false).isValid(email));

        log.info("test email8: {}", Pattern.compile(PATTERN_EMAIL).matcher(email).matches());
    }

    private static void archivo() {
        String pathCsv = "depurado6.csv";

//        Reader r;
        try {
            File file = new File(pathCsv);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            CsvClient<Registro> csvClient = new CsvClientImpl<>(br, Registro.class);
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

//            Writer w = new FileWriter("nuevo.csv");
//            csvClient = new CsvClientImpl<>(w, Registro.class);
//            csvClient.writeHeader(new String[]{"USUA_ID", "ERROR MANUAL", "USUA_EMAIL"});
//            csvClient.writeRow(new String[]{"123", "pepito", "perez"});
//            csvClient.setConverter("", new EasyAbstractConverter(null) {
//                
//                @Override
//                public Object fromString(String text) throws Exception {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//            });
//            csvClient.set
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
        boolean valid = Pattern.compile(PATTERN_EMAIL).matcher(email).matches();
//        boolean valid = EmailValidator.getInstance().isValid(email);
        if (!valid) {
            log.info("uduaId: {} invalid email: {}", usuaId, email);
        }
    }

}
