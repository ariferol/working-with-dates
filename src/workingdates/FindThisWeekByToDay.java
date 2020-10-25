/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingdates;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author arif.erol
 */
public class FindThisWeekByToDay {
    private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    public static void main(String[] args) 
    {        
        //        LocalDate localDate = LocalDate.of(2020, 03, 03);
        LocalDate localDateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        
        System.out.println("Bu gun (other format): " + formatter.format(localDateNow.now()) + " " + localDateNow.getDayOfWeek());   
        System.out.println("Bu gun : " + df.format(convertToDate(localDateNow)) + " " + localDateNow.getDayOfWeek());   

        LocalDate pLD = LocalDate.of(2020, 03, 02);
//        LocalDate firstWorkingDay = pLD.with(DayOfWeek.MONDAY);                   
//        LocalDate lastWorkingDay = pLD.with(DayOfWeek.FRIDAY); 
        for(int i= 0; i < 7; i++){            
            System.out.println(df.format(convertToDate(pLD)) + " e gore Pazartesi : " + df.format(convertToDate(pLD.with(DayOfWeek.MONDAY))));
            System.out.println(df.format(convertToDate(pLD)) + " e gore Cuma      : " + df.format(convertToDate(pLD.with(DayOfWeek.FRIDAY))));
            System.out.println("--------------------------------------------");
            pLD = pLD.plusDays(1);
        }
        
        Date startDate = getFirstWorkingDay(localDateNow);
        Date endDate = getLastWorkingDay(localDateNow);
        
        /*Belirli bir tarihe gore haftanin gunlerinin Date cinsinden degerini bulmak icin, optimum alttaki gibi kullan.*/
        startDate = convertToDate(LocalDate.now().with(DayOfWeek.MONDAY));//Bu gune gore pazartesi nin tarihini verir
        endDate = convertToDate(LocalDate.now().with(DayOfWeek.FRIDAY));//Bu gune gore cuma gununun tarihini verir...
    }
    
    public static Date convertToDate(LocalDate dateToConvert) 
    {
        return java.sql.Date.valueOf(dateToConvert);
    }

    private static Date getFirstWorkingDay(LocalDate pLocalDateNow) 
    {
        LocalDate firstWorkingDay = LocalDate.now().with(DayOfWeek.MONDAY);
        java.time.DayOfWeek dayOfWeek = pLocalDateNow.getDayOfWeek();
        switch (dayOfWeek) 
        {
            case TUESDAY: firstWorkingDay = pLocalDateNow.minusDays(1); break;
            case WEDNESDAY: firstWorkingDay = pLocalDateNow.minusDays(2); break;
            case THURSDAY: firstWorkingDay = pLocalDateNow.minusDays(3); break;
            case FRIDAY:firstWorkingDay = pLocalDateNow.minusDays(4); break;
            case SATURDAY:firstWorkingDay = pLocalDateNow.minusDays(5); break;
            case SUNDAY:firstWorkingDay = pLocalDateNow.minusDays(6); break;            
//            default:firstWorkingDay=localDate.plusDays(0); break;
        }
        Date monDayDate = convertToDate(firstWorkingDay);
        System.out.println(convertToDate(pLocalDateNow) + " e gore Pazartesi : " + df.format(monDayDate));  
        return monDayDate;
    }
    
    private static Date getLastWorkingDay(LocalDate pLocalDateNow) 
    {        
        LocalDate lastWorkingDay = LocalDate.now().with(DayOfWeek.FRIDAY);
        java.time.DayOfWeek dayOfWeek = pLocalDateNow.getDayOfWeek();
        switch (dayOfWeek) 
        {
            case MONDAY:lastWorkingDay = pLocalDateNow.plusDays(4); break;
            case TUESDAY: lastWorkingDay = pLocalDateNow.plusDays(3); break;
            case WEDNESDAY: lastWorkingDay = pLocalDateNow.plusDays(2); break;
            case THURSDAY: lastWorkingDay = pLocalDateNow.plusDays(1); break;            
            case SATURDAY:lastWorkingDay = pLocalDateNow.minusDays(1); break;
            case SUNDAY:lastWorkingDay = pLocalDateNow.minusDays(2); break;            
//            default: break;
        }                
        Date friDayDate = convertToDate(lastWorkingDay);
        System.out.println(convertToDate(pLocalDateNow) + " e gore Cuma : " + df.format(friDayDate));  
        return friDayDate;
    }

}


