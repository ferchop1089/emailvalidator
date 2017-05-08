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
public class Registro {

    @CsvCell(columnIndex = 1, required = true, columnName = "USUA_ID")
    private long usuaId;
    @CsvCell(columnIndex = 2, columnName = "ERROR MANUAL")
    private String errManual;
    @CsvCell(columnIndex = 3, columnName = "USUA_EMAIL")
    private String email1;
    @CsvCell(columnIndex = 4)
    private String email2;
    @CsvCell(columnIndex = 5)
    private String email3;

    public Registro() {
    }

    public long getUsuaId() {
        return usuaId;
    }

    public void setUsuaId(long usuaId) {
        this.usuaId = usuaId;
    }

    public String getErrManual() {
        return errManual;
    }

    public void setErrManual(String errManual) {
        this.errManual = errManual;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail1(String email) {
        email1 = email;
    }

    public void setEmail2(String email) {
        email2 = email;
    }

    public void setEmail3(String email) {
        email3 = email;
    }

    @Override
    public String toString() {
        return "[usuaId: " + usuaId + ", errManual :" + errManual
               + ", email: {" + email1 + ", " + email2 + ", " + email3 + "}]";
    }

}
