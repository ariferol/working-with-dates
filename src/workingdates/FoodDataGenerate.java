package workingdates;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author arif.erol
 */
public class FoodDataGenerate {

    private static SimpleDateFormat dfPattern = new SimpleDateFormat("dd.MM.yyyy");
    private static String sampleFoodListJson = "[{\"foodDate\":\"24.02.2020\",\"calorie\":1.1,\"foodDescription\":\"Mercimek Çorba\"},{\"foodDate\":\"24.02.2020\",\"calorie\":1.2,\"foodDescription\":\"Dana Rosto\"},{\"foodDate\":\"24.02.2020\",\"calorie\":1.3,\"foodDescription\":\"Nohutlu Pirinç Pilavı\"},{\"foodDate\":\"24.02.2020\",\"calorie\":1.4,\"foodDescription\":\"Yoğurtlu Kereviz Salatası\"},{\"foodDate\":\"24.02.2020\",\"calorie\":1.5,\"foodDescription\":\"Meyve\"},{\"foodDate\":\"25.02.2020\",\"calorie\":1.4,\"foodDescription\":\"Yarma Çorba\"},{\"foodDate\":\"25.02.2020\",\"calorie\":1.5,\"foodDescription\":\"Tavuk Sote\"},{\"foodDate\":\"25.02.2020\",\"calorie\":1.6,\"foodDescription\":\"Bulgur Pilavı\"},{\"foodDate\":\"25.02.2020\",\"calorie\":1.7,\"foodDescription\":\"Ayran\"},{\"foodDate\":\"25.02.2020\",\"calorie\":1.8,\"foodDescription\":\"Ayva Tatlısı\"},{\"foodDate\":\"26.02.2020\",\"calorie\":1.9,\"foodDescription\":\"Tarhana Çorba\"},{\"foodDate\":\"26.02.2020\",\"calorie\":1.1,\"foodDescription\":\"Yoğurtlu Köfte\"},{\"foodDate\":\"26.02.2020\",\"calorie\":1.11,\"foodDescription\":\"Şakşuka\"},{\"foodDate\":\"26.02.2020\",\"calorie\":1.12,\"foodDescription\":\"Meyve\"},{\"foodDate\":\"26.02.2020\",\"calorie\":1.13,\"foodDescription\":\"Seçmeli İçecek\"},{\"foodDate\":\"27.02.2020\",\"calorie\":1.14,\"foodDescription\":\"Ezogelin Çorba\"},{\"foodDate\":\"27.02.2020\",\"calorie\":1.15,\"foodDescription\":\"Orman Kebap\"},{\"foodDate\":\"27.02.2020\",\"calorie\":1.16,\"foodDescription\":\"Kol Böreği\"},{\"foodDate\":\"27.02.2020\",\"calorie\":1.17,\"foodDescription\":\"Komposto\"},{\"foodDate\":\"27.02.2020\",\"calorie\":1.18,\"foodDescription\":\"Yoğurt\"},{\"foodDate\":\"28.02.2020\",\"calorie\":1.19,\"foodDescription\":\"Brokoli Çorba\"},{\"foodDate\":\"28.02.2020\",\"calorie\":1.2,\"foodDescription\":\"Et Döner-Garnitür\"},{\"foodDate\":\"28.02.2020\",\"calorie\":1.21,\"foodDescription\":\"Pirinç Pilavı\"},{\"foodDate\":\"28.02.2020\",\"calorie\":1.22,\"foodDescription\":\"Ayran\"},{\"foodDate\":\"28.02.2020\",\"calorie\":1.23,\"foodDescription\":\"Şöbiyet\"}]";

    public static void main(String[] args) throws ParseException {
        List<FoodModel> pDataList = getSampleFoodList();
//        List<FoodModel> pDataList = getSampleFoodListJson();
        Date pStartDate = dfPattern.parse("24.02.2020");
        Date pEndDate = dfPattern.parse("28.02.2021");
        
//        List<FoodModel> sortedList = findAndPrintFoodList(pStartDate, pEndDate, pDataList);
        List<FoodModel> sortedList = findFoodList(pStartDate, pEndDate, pDataList);
        
//        sortedList = sortedList.stream().map(m -> {m.setFoodDateStr(m.getFoodDate()); return m; })
//                .collect(Collectors.toList());
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        String jsonStr = gson.toJson(sortedList);
        System.out.println(jsonStr);
    }

    private static List<FoodModel> findFoodList(Date pStartDate, Date pEndDate, List<FoodModel> pDataList) 
    {
        Calendar beforeDay = Calendar.getInstance();
        beforeDay.setTime(pStartDate);
        beforeDay.add(Calendar.DATE, -1);
    
        Calendar afterDay = Calendar.getInstance();
        afterDay.setTime(pEndDate);
        afterDay.add(Calendar.DATE, 1);
        
        System.out.println("---------------------------------------filter start------------------------------------------------------------------------------");
        List<FoodModel> resultSortedFoodList = new ArrayList<FoodModel>();
        resultSortedFoodList = pDataList.stream()
                .filter(dates -> 
                        dates.getFoodDate().after(beforeDay.getTime()) && dates.getFoodDate().before(afterDay.getTime()))
                        .collect(Collectors.toList());
        resultSortedFoodList.sort((mFirst, mLast) -> mFirst.getFoodDate().compareTo(mLast.getFoodDate()));
        if(resultSortedFoodList != null && resultSortedFoodList.size() > 0)
            resultSortedFoodList.forEach(p->System.out.println(dfPattern.format(p.getFoodDate()) + " - "+ p.getFoodDescription() + " - " + p.getCalorie()));
        else
            System.out.println(dfPattern.format(pStartDate) + " - " +dfPattern.format(pStartDate) +" tarihleri arasında kayıtlı herhangi bir veri bulunamadı!");
        System.out.println("---------------------------------------filter end------------------------------------------------------------------------------");
        return resultSortedFoodList;
    }
    
    private static List<FoodModel> findAndPrintFoodList(Date pStartDate, Date pEndDate, List<FoodModel> pDataList) {
        System.out.println("---------------------------------------filter start------------------------------------------------------------------------------");
        List<FoodModel> resultSortedFoodList = new ArrayList<FoodModel>();
        resultSortedFoodList = pDataList.stream()
                .filter(dates -> (dates.getFoodDate().after(pStartDate) || dates.getFoodDate().equals(pStartDate)) 
                        && (dates.getFoodDate().before(pEndDate) || dates.getFoodDate().equals(pEndDate)))
                        .collect(Collectors.toList());
        resultSortedFoodList.sort((mFirst, mLast) -> mFirst.getFoodDate().compareTo(mLast.getFoodDate()));
        if(resultSortedFoodList != null && resultSortedFoodList.size() > 0)
            resultSortedFoodList.forEach(p->System.out.println(dfPattern.format(p.getFoodDate()) + " - "+ p.getFoodDescription()));
        else
            System.out.println(dfPattern.format(pStartDate) + " - " +dfPattern.format(pStartDate) +" tarihleri arasında kayıtlı herhangi bir veri bulunamadı!");
        System.out.println("---------------------------------------filter end------------------------------------------------------------------------------");
        return resultSortedFoodList;
    }

    private static List<FoodModel> getSampleFoodList() throws ParseException {
        List<FoodModel> foodList = new ArrayList<FoodModel>() {
            {
                /*subathafta4-start*/
                add(new FoodModel(dfPattern.parse("24.02.2020"), 1.1f, "Mercimek Çorba"));
                add(new FoodModel(dfPattern.parse("24.02.2020"), 1.2f, "Dana Rosto"));
                add(new FoodModel(dfPattern.parse("24.02.2020"), 1.3f, "Nohutlu Pirinç Pilavı"));
                add(new FoodModel(dfPattern.parse("24.02.2020"), 1.4f, "Yoğurtlu Kereviz Salatası"));
                add(new FoodModel(dfPattern.parse("24.02.2020"), 1.5f, "Meyve"));
                
                add(new FoodModel(dfPattern.parse("25.02.2020"), 1.4f, "Yarma Çorba"));
                add(new FoodModel(dfPattern.parse("25.02.2020"), 1.5f, "Tavuk Sote"));
                add(new FoodModel(dfPattern.parse("25.02.2020"), 1.6f, "Bulgur Pilavı"));
                add(new FoodModel(dfPattern.parse("25.02.2020"), 1.7f, "Ayran"));
                add(new FoodModel(dfPattern.parse("25.02.2020"), 1.8f, "Ayva Tatlısı"));
                
                add(new FoodModel(dfPattern.parse("26.02.2020"), 1.9f, "Tarhana Çorba"));
                add(new FoodModel(dfPattern.parse("26.02.2020"), 1.10f, "Yoğurtlu Köfte"));
                add(new FoodModel(dfPattern.parse("26.02.2020"), 1.11f, "Şakşuka"));
                add(new FoodModel(dfPattern.parse("26.02.2020"), 1.12f, "Meyve"));
                add(new FoodModel(dfPattern.parse("26.02.2020"), 1.13f, "Seçmeli İçecek"));
                
                add(new FoodModel(dfPattern.parse("27.02.2020"), 1.14f, "Ezogelin Çorba"));
                add(new FoodModel(dfPattern.parse("27.02.2020"), 1.15f, "Orman Kebap"));
                add(new FoodModel(dfPattern.parse("27.02.2020"), 1.16f, "Kol Böreği"));
                add(new FoodModel(dfPattern.parse("27.02.2020"), 1.17f, "Komposto"));
                add(new FoodModel(dfPattern.parse("27.02.2020"), 1.18f, "Yoğurt"));
                
                add(new FoodModel(dfPattern.parse("28.02.2020"), 1.19f, "Brokoli Çorba"));
                add(new FoodModel(dfPattern.parse("28.02.2020"), 1.20f, "Et Döner-Garnitür"));
                add(new FoodModel(dfPattern.parse("28.02.2020"), 1.21f, "Pirinç Pilavı"));
                add(new FoodModel(dfPattern.parse("28.02.2020"), 1.22f, "Ayran"));
                add(new FoodModel(dfPattern.parse("28.02.2020"), 1.23f, "Şöbiyet"));
                /*subathafta4-end*/
//////////////////////////////////////////////////////////                                
                /*marthafta1-start*/
                add(new FoodModel(dfPattern.parse("02.03.2020"), 1.1f, "Köylü Çorba"));
                add(new FoodModel(dfPattern.parse("02.03.2020"), 1.2f, "Patlıcan Kebap"));
                add(new FoodModel(dfPattern.parse("02.03.2020"), 1.3f, "Pirinç Pilavı"));
                add(new FoodModel(dfPattern.parse("02.03.2020"), 1.4f, "Cacık"));
                add(new FoodModel(dfPattern.parse("02.03.2020"), 1.5f, "Panna Cotta"));                
                
                add(new FoodModel(dfPattern.parse("03.03.2020"), 1.1f, "Mercimek Çorba"));
                add(new FoodModel(dfPattern.parse("03.03.2020"), 1.2f, "Tepsi Köfte"));
                add(new FoodModel(dfPattern.parse("03.03.2020"), 1.3f, "Lor Peynirli Makarna"));
                add(new FoodModel(dfPattern.parse("03.03.2020"), 1.4f, "Yoğurtlu Kereviz Salatası"));
                add(new FoodModel(dfPattern.parse("03.03.2020"), 1.5f, "Meyve suyu"));                

                add(new FoodModel(dfPattern.parse("04.03.2020"), 1.1f, "Şehriye Çorba"));
                add(new FoodModel(dfPattern.parse("04.03.2020"), 1.2f, "Fırın Tavuk"));
                add(new FoodModel(dfPattern.parse("04.03.2020"), 1.3f, "Alaca Pilav"));
                add(new FoodModel(dfPattern.parse("04.03.2020"), 1.4f, "Seçmeli İçecek"));
                add(new FoodModel(dfPattern.parse("04.03.2020"), 1.5f, "Fıstıklı Kadayıf"));                

                add(new FoodModel(dfPattern.parse("05.03.2020"), 1.1f, "Tarhana Çorba"));
                add(new FoodModel(dfPattern.parse("05.03.2020"), 1.2f, "Tas Kebap"));
                add(new FoodModel(dfPattern.parse("05.03.2020"), 1.3f, "Bulgur Pilavı"));
                add(new FoodModel(dfPattern.parse("05.03.2020"), 1.4f, "Kırmızı Lahana - Havuç Salata"));
                add(new FoodModel(dfPattern.parse("05.03.2020"), 1.5f, "Yoğurt"));                

                add(new FoodModel(dfPattern.parse("06.03.2020"), 1.1f, "Ezogelin Çorba"));
                add(new FoodModel(dfPattern.parse("06.03.2020"), 1.2f, "Arnavut Ciğeri-Garnitür"));
                add(new FoodModel(dfPattern.parse("06.03.2020"), 1.3f, "Pirinç Pilavı"));
                add(new FoodModel(dfPattern.parse("06.03.2020"), 1.4f, "Ayran"));
                add(new FoodModel(dfPattern.parse("06.03.2020"), 1.5f, "Tiramisu"));
                /*marthafta1-end*/
                //////////////////////////////////////////////////////////
                
            }
        };
        return foodList;
    }

    private static List<FoodModel> getSampleFoodListJson() {
        Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
        List<FoodModel> resultList = new ArrayList<FoodModel>();
        
        Type listType = new TypeToken<ArrayList<FoodModel>>(){}.getType();
        resultList= gson.fromJson(sampleFoodListJson, listType);
        
//        /*Diger deserialize yontemi;*/
//        FoodModel[] list = gson.fromJson(sampleFoodListJson, FoodModel[].class);        
//        resultList =  Arrays.asList(list);

        return resultList;
    }
}
