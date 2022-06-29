package com.oventus.template.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.normalizeSpace;
import static org.springframework.util.StringUtils.hasText;

public class LineIteratorTest {

    public static void main(String[] args) throws IOException {
        Map<String, String> headersMap;
        int count = 0;
        File destinationFile = new File("C:\\tmp\\storage\\1 record sachin csv - Copy.csv");

        //readUsingFileChannel(destinationFile);

        String line2 = "destination,Sendat,Firstname ,Lastname";
        List<String> data2 = Arrays.stream(line2.split(",")).collect(Collectors.toList());
        boolean isDestinationPresent = data2.stream().anyMatch(h -> h.equalsIgnoreCase("DESTINATION"));
        System.out.println("Non lineIterator line ==> " + isDestinationPresent);

        //Using lineIterator
        LineIterator lineIterator = FileUtils.lineIterator(destinationFile, "UTF-8");

        try {
            if (lineIterator.hasNext()) {
                String line = lineIterator.nextLine();
                System.out.println("Lineiterator Line ==> " + line);

                if (hasText(line)) {
                    List<String> data = Arrays.stream(line.split(",")).collect(Collectors.toList());
                    System.out.println(data.toString());
                    boolean isDestinationPresent1 = data.stream().anyMatch(h -> h.equalsIgnoreCase("DESTINATION"));
                    System.out.println(isDestinationPresent1);

                    System.out.println(containsIgnoreCase("destination", line));
                    System.out.println(normalizeSpace(line));
                    System.out.println(line.contains("destination,"));




                    if (data.size() > 0 && count == 0) {
//                        //Validate Headers for mandatory columns
//                        headersMap = data
//                                .stream()
//                                .distinct()
//                                .collect(toMap(Function.identity(), String::toString, (e1, e2) -> e2, LinkedHashMap::new));
//
//                        Optional.ofNullable(data
//                                .stream()
//                                .filter(h -> h.equalsIgnoreCase("DESTINATION")).findAny()
//                                .orElseThrow(() -> new RuntimeException("The file has no destination column. Try another file.")));


                        count++;
                    }
                }
            } else {
                System.out.println("No headers found. Try another file.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            lineIterator.close();
        }
    }

    private static void readUsingFileChannel(File destinationFile) {
        try {

            FileChannel fc = new RandomAccessFile(destinationFile, "rw").getChannel();
            ByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, (int) fc.size());

            // print contents of ByteBuffer //
            while (bb.hasRemaining()) {
                System.out.print((char) bb.get());
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
