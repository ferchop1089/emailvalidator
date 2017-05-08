/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.icfes.emailvalidator;

import lombok.extern.slf4j.Slf4j;
import org.csveed.annotations.CsvCell;

/**
 *
 * @author fernando
 */
@Slf4j
public class Registro2 {

    @CsvCell(columnIndex = 1, required = true, columnName = "COMENTARIO")
    private String comentario;
    @CsvCell(columnIndex = 2, required = true, columnName = "USUA_ID")
    private long usuaId;
    //@CsvCell(columnIndex = 3, columnName = "USUA_EMAIL")
    private String emails;

    public Registro2() {
    }

    public long getUsuaId() {
        return usuaId;
    }

    public void setUsuaId(long usuaId) {
        this.usuaId = usuaId;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String email) {
        emails = email;
    }

    @Override
    public String toString() {
        return "[usuaId: " + usuaId
                + ", email: {" + emails + "}]";
    }

}
