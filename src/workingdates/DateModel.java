/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingdates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arif.erol
 */
public class DateModel {
    private Date sampleDate;
    private String sampleDateStr;
    private Date sampleDateStrDate;
    
    public DateModel(String sampleDate, String sampleDescription) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sampleDateStr = sampleDate;
        try {
            this.sampleDateStrDate = sampleDate.isEmpty() ? new Date("01.01.1999") : sdf.parse(sampleDate);
        } catch (ParseException ex) {
            Logger.getLogger(DateModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.sampleDescription = sampleDescription;
    }
        
    public DateModel(Date sampleDate, String sampleDescription) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.sampleDate = sampleDate;
        this.sampleDateStr = sdf.format(sampleDate);
        this.sampleDescription = sampleDescription;
    }
    
    public Date getSampleDateStrDate() {        
        return sampleDateStrDate;
    }

    public void setSampleDateStrDate(Date sampleDateStrDate) {
        this.sampleDateStrDate = sampleDateStrDate;
    }
    private String sampleDescription;

    public String getSampleDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sampleDate != null ? sdf.format(sampleDate) : sampleDateStr;
    }

    public void setSampleDateStr(String sampleDateStr) {
        this.sampleDateStr = sampleDateStr;
    }

    public Date getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(Date sampleDate) {
        this.sampleDate = sampleDate;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }
    
    
}
