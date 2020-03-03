/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingdates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author arif.erol
 */
public class WorkingDates {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private static List<DateModel> dateList = null;
    private static Date start = null;
    private static String startStr = null;
    private static Date end = null;
    private static String endStr = null;
    public static List<DateModel> resultSortedDateList = new ArrayList<DateModel>();
    public static List<DateModel> resultSortedDateStrList = new ArrayList<DateModel>();
    
    private void InitializeDatas() throws ParseException {
        dateList = new ArrayList<DateModel>() {
            {
                add(new DateModel(sdf.parse("01.01.2016"),"Test açıklama 1"));
                add(new DateModel(sdf.parse("07.01.2016"),"Test açıklama 7"));
                add(new DateModel(sdf.parse("08.01.2016"),"Test açıklama 8"));
                add(new DateModel(sdf.parse("03.01.2016"),"Test açıklama 3"));
                add(new DateModel(sdf.parse("11.01.2016"),"Test açıklama 11"));
            }
        };
//        start = sdf.parse("01.01.2016");
//        startStr = "01.01.2016";
//        
//        end = sdf.parse("03.01.2016");
//        endStr = "03.01.2016";
    }
    
    public List<DateModel> getDatesBetweenStartAndFinishWithFilter(Date pStartDate, Date pEndDate, List<DateModel> pData) throws ParseException 
    {
        System.out.println("---------------------------------------Date filter start------------------------------------------------------------------------------");
        resultSortedDateList = pData.stream()
                .filter(dates -> (dates.getSampleDate().after(pStartDate) || dates.getSampleDate().equals(pStartDate)) 
                        && (dates.getSampleDate().before(pEndDate) || dates.getSampleDate().equals(pEndDate)))
                .collect(Collectors.toList());
//        Collections.sort(resultSortedDateList, new Comparator<DateModel>() {
//                                                    @Override
//                                                    public int compare(DateModel r1, DateModel r2) {
//                                                        return r1.getSampleDate().compareTo(r2.getSampleDate());
//                                                    }
//                                                });
//        resultSortedDateList.sort((DateModel o1, DateModel o2)->o1.getSampleDate().compareTo(o2.getSampleDate()));
        resultSortedDateList.sort((mFirst, mLast) -> mLast.getSampleDate().compareTo(mFirst.getSampleDate()));
        resultSortedDateList.forEach(p->System.out.println(sdf.format(p.getSampleDate()) + " - "+ p.getSampleDescription()));
        System.out.println("---------------------------------------Date filter end------------------------------------------------------------------------------");
        return resultSortedDateList;
    }

    public List<DateModel> getDatesStrBetweenStartAndFinishWithFilter(String pStartDate, String pEndDate, List<DateModel> pData) throws ParseException 
    {
        Date startDate = sdf.parse(pStartDate);
        Date endDate = sdf.parse(pEndDate);
        System.out.println("----------------------------------------Date string filter start -----------------------------------------------------------------------------");
//        List<DateModel> tempList = pData.stream().map(m -> new DateModel(m.getSampleDateStr(), m.getSampleDescription())).collect(Collectors.toList());
        pData = pData.stream().map(m -> new DateModel(m.getSampleDateStr(), m.getSampleDescription())).collect(Collectors.toList());
        
        resultSortedDateStrList = pData.stream()
                .filter(dates -> (dates.getSampleDateStrDate().after(startDate) || dates.getSampleDateStrDate().equals(startDate)) 
                        && (dates.getSampleDateStrDate().before(endDate) || dates.getSampleDateStrDate().equals(endDate)))
                .collect(Collectors.toList());
        
        resultSortedDateStrList.sort((mFirst, mLast) -> mFirst.getSampleDateStrDate().compareTo(mLast.getSampleDateStrDate()));
        
        resultSortedDateStrList.forEach(p->System.out.println(sdf.format(p.getSampleDateStrDate()) + " - "+ p.getSampleDescription()));
        System.out.println("-----------------------------------------Date string filter end ----------------------------------------------------------------------------");
        return resultSortedDateStrList;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        WorkingDates wDays = new WorkingDates();
        wDays.InitializeDatas();
                
        start = sdf.parse("01.01.2016");
        end = sdf.parse("09.01.2016");
        resultSortedDateList = wDays.getDatesBetweenStartAndFinishWithFilter(start, end , dateList);
        
        startStr = "01.01.2016";
        endStr = "09.01.2016";
        resultSortedDateStrList = wDays.getDatesStrBetweenStartAndFinishWithFilter(startStr, endStr , dateList);
    }


    
}
