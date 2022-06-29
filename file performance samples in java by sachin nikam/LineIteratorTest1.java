package com.oventus.template.service;
import com.opencsv.CSVParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LineIteratorTest1 {

    private CSVParser parser = new CSVParser();

    public static void main(String[] args) {
        String file1 = "C:\\PageOne\\documents\\Bulk Message Service V3\\10 records.csv";
        String file2 = "C:\\PageOne\\documents\\Bulk Message Service V3\\1 record sachin csv.csv";
        String file3 = "C:\\tmp7\\1 valid excel test.csv";

        try {
            new LineIteratorTest1().test(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test(String filename) throws IOException {
        File destinationFile = new File(filename);
        LineIterator lineIterator = FileUtils.lineIterator(destinationFile, StandardCharsets.UTF_8.name());

        //HEADER
        String header = lineIterator.nextLine();
        System.out.println(header);

        List<String> headerList = parseIt(header);
        System.out.println(headerList);

        boolean isDestinationPresent = headerList.stream()
                .anyMatch(h -> h.equalsIgnoreCase("destination"));
        System.out.println(isDestinationPresent);

    }

    public List<String> parseIt(String line) {
        // Destination
        // "\uFEFFDestination"
        //http://unicode.org/faq/utf_bom.html#bom6
        // https://en.wikipedia.org/wiki/Byte_order_mark

        try {
            return Arrays.asList(parser.parseLine(line.replace("\uFEFF", "")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}