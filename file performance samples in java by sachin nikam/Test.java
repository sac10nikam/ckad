package com.oventus.template.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class Test {
    public static DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy-MM-dd HH:mm:ss]")
            .appendPattern("[dd/MM/yyyy h:mm:ss a]")
            .appendPattern("[yyyy/MM/dd HH:mm:ss]")
            .appendPattern("[dd-MM-yyyy HH:mm]")
            .appendPattern("[yyyy-MM-dd hh:mm:ss a]")
            .appendPattern("[yyyy/MM/dd hh:mm:ss a]")
            .appendPattern("[dd/MM/yyyy HH:mm:ss]")
            .appendPattern("[dd-MM-yyyy HH:mm:ss]")
            .appendPattern("[dd-MM-yyyy hh:mm:ss a]")
            .appendPattern("[dd/MM/yyyy hh:mm:ss a]")
            .appendPattern("[dd/MM/yyyy HH:mm]")
            .appendPattern("[dd-MM-yyyy hh:mm a]")
            .appendPattern("[dd-MM-yyyy h:mm]")
            .appendPattern("[dd/MM/yyyy h:mm]")
            .appendPattern("[M/dd/yy h:mm a]")
            .appendPattern("[M/dd/yy h:mm]")
            .appendPattern("[dd/MM/yyyy hh:mm:ss a]")
            .appendPattern("[M/dd/yy HH:mm]")
            .appendPattern("[M/dd/yy hh:mm a]")
            .appendPattern("[MM/dd/yy HH:mm]")
            .appendPattern("[M/d/yy HH:mm]")
            .appendPattern("[d/M/yyyy]")
            .appendPattern("[dd/MM/yyyy]")
            .appendPattern("[M/d/yy hh:mm a]")
            .appendPattern("[MM/d/yy HH:mm]")
            .appendPattern("[MM/d/yy hh:mm a]")
            .appendPattern("[MM/d/yyyy HH:mm]")
            .appendPattern("[yyyy-MM-dd'T'HH:mm]")
            .appendPattern("[yyyy-MM-dd'T'HH:mm:ss'Z']")
            .appendPattern("[yyyy-MM-dd'T'HH:mm:ss]")
            .appendPattern("[yyyy-MM-dd'T'HH:mmZ]")
            .appendPattern("[M/dd/yy hh.mm a]")
            .appendPattern("[MM/dd/yy hh.mm a]")
            .appendPattern("[dd-MMM-yy hh.mm.ss.S a]")
            .appendPattern("[MM/dd/yy hh.mm.ss.S a]")
            .appendPattern("[MM/dd/yy HH:mm a]")
            .appendPattern("[M/dd/yy HH:mm a]")
            .appendPattern("[M/dd/yyyy HH:mm]");

    public static DateTimeFormatter sendAtFormatter = dateTimeFormatterBuilder.toFormatter();

    public static void main(String[] args) throws IOException, ParseException {


            //String fileName = "C:\\PageOne\\documents\\Bulk Message Service V3\\10 valid excel test.csv";
            //String fileName = "C:\\PageOne\\documents\\Bulk Message Service V3\\test bulk file.txt";
//            String fileName = "C:\\PageOne\\documents\\Bulk Message Service V3\\processing.merge compose example.xlsx";
//
//            File file = new File(fileName);
//            LineIterator it = FileUtils.lineIterator(file, "UTF-8");
//            try {
//                while (it.hasNext()) {
//                    String line = it.nextLine();
//                    System.out.println(line);
//                    // do something with line
//                }
//            } finally {
//                LineIterator.closeQuietly(it);
//            }

//            String intStr = "1,109";
//
//        try {
//            Number number = NumberFormat.getNumberInstance(Locale.US).parse(intStr);
//            System.out.println(number.intValue());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yy HH:mm a");
        String dateInString = "3/17/17 4:31 PM";
        Date date = sdf.parse(dateInString);
        OffsetDateTime offsetDateTime = date.toInstant()
                .atOffset(ZoneOffset.UTC);
        System.out.println(offsetDateTime);

        System.out.println(String.valueOf(OffsetDateTime.now()));




        String str2 = "10/01/2021 17:31";
        String str = "10/01/2021  17:31:00";
        String str1 = "22/02/  2019     14:15:00";
//        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//        //LocalDateTime dateTime = LocalDateTime.parse(str1);
//       // System.out.println(OffsetDateTime.of(LocalDateTime.parse(dateTime.toString(), formatter), ZoneOffset.UTC));
//
//        System.out.println(convertFrom(sdf1.parse(str)));
//        System.out.println(formatter.parse(str));


//        if (str.contains("  ")) {
//            System.out.println(OffsetDateTime.of(LocalDateTime.parse(str.replaceAll("\\s ", " "), sendAtFormatter), ZoneOffset.UTC));
//        } else {
            String newStr = StringUtils.normalizeSpace( str );
            System.out.println( OffsetDateTime.of(LocalDateTime.parse(newStr, sendAtFormatter), ZoneOffset.UTC));
        //}

        String line = "destination,Sendat,Firstname ,Lastname";
        List<String> data = Arrays.stream(line.split(",")).collect(Collectors.toList());

        boolean isDestinationPresent = data.stream().allMatch(h -> h.equalsIgnoreCase("DESTINATION"));

        System.out.println(data.contains("destination"));
        System.out.println(data.stream().anyMatch(e -> e.equalsIgnoreCase("DESTINATION") ));
//        Optional.ofNullable(data
//                .stream()
//                .filter(h -> h.equalsIgnoreCase("DESTINATION")).findAny()
//                .orElseThrow(() -> new RuntimeException("The file has no destination column. Try another file.")));



//        List<String> list = Arrays.asList("destination", "ABC");
//        String matchingText = "DESTINATION";
//
//        boolean isMatched = list.stream().anyMatch(matchingText::equalsIgnoreCase);
//        System.out.println(isMatched);
//
//        List<String> data = Arrays.asList("destination,Sendat,Firstname,Lastname".split(","));
//
//        Map<String, String> map = data
//                .stream()
//                .collect( toMap(Function.identity(), String::toString, (e1, e2) -> e2, LinkedHashMap::new));
//
//
//        System.out.println(map.values());

//        data.stream()
//                .filter(h -> h.equalsIgnoreCase("DESTINATION"))
//                .findAny()
//                .orElseThrow(() -> new RuntimeException("The file has no destination data. Try another file."));




            Map<Integer, String> dynamicHeaderMap = new HashMap<>();
            dynamicHeaderMap.put(1, "DESTINATION1");

//            dynamicHeaderMap.values()
//                    .stream()
//                    .filter(h -> h.equals("DESTINATION"))
//                    .findAny()
//                    .orElseThrow(() -> new RuntimeException("File has no destination data. Try another file."));

//            Optional.ofNullable(dynamicHeaderMap.values()
//                    .stream()
//                    .filter(h -> h.equalsIgnoreCase("DESTINATION")).findAny()
//                    .orElseThrow(() -> new RuntimeException("File has no destination data. Try another file.")));
//        } catch (Exception e) {
//            throw e;
//        }

    }

    public static OffsetDateTime convertFrom(Date source) {
        if (source instanceof Timestamp) {
            return ((Timestamp) source).toLocalDateTime()
                    .atOffset(ZoneOffset.UTC);
        }
        return source.toInstant().atOffset(ZoneOffset.UTC);
    }
}
