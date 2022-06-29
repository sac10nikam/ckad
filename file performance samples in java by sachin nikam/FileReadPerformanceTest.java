package com.oventus.template.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Stream;

@SpringBootTest
public class FileReadPerformanceTest {

    @org.junit.Test
    public void testLargeFileIO_Scanner() throws Exception {

        long start = new Date().getTime();

        File fileName = new File("C:\\tmp\\storage\\1M invalid excel test.csv");
        InputStream inputStream = new FileInputStream(fileName);

        try (Scanner fileScanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
        }
        long end = new Date().getTime();

        long time = end - start;
        System.out.println("Scanner Time Consumed => " + time);

    }


    @org.junit.Test
    public void testLargeFileIO_BufferedReader() throws Exception {

        long start = new Date().getTime();

        File fileName = new File("C:\\tmp\\storage\\1M invalid excel test.csv");
        try (BufferedReader fileBufferReader = new BufferedReader(new FileReader(fileName))) {
            String fileLineContent;
            while ((fileLineContent = fileBufferReader.readLine()) != null) {
                System.out.println(fileLineContent);
            }
        }
        long end = new Date().getTime();

        long time = (long) (end - start);
        System.out.println("BufferedReader Time Consumed => " + time);

    }


    @org.junit.Test
    public void testLargeFileIO_Stream() throws Exception {

        long start = new Date().getTime();

        File fileName = new File("C:\\tmp\\storage\\1M invalid excel test.csv");
        try (Stream inputStream = Files.lines(fileName.toPath(), StandardCharsets.UTF_8)) {
            inputStream.forEach(System.out::println);
        }
        long end = new Date().getTime();

        long time = end - start;
        System.out.println("Stream Time Consumed => " + time);

    }

    @Test
    public void file_channel_test() {
        File fileName = new File("C:\\tmp\\storage\\1M invalid excel test.csv");
        long start = new Date().getTime();
            try (FileChannel fc = new RandomAccessFile(fileName, "r").getChannel()) {
                //inputStream.forEach(System.out::println);
                ByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, (int) fc.size());

//                 print contents of ByteBuffer //
                while (bb.hasRemaining()) {
                    System.out.print((char) bb.get());
                }

                long end = new Date().getTime();

                long time = end - start;
                System.out.println("FileChannel Time Consumed => " + time);
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    @Test
    public void lineIterator_test() {
//        File fileName = new File("C:\\tmp\\storage\\1M invalid excel test.csv");
//        long start = new Date().getTime();
//        try (LineIterator lineIterator = FileUtils.lineIterator(fileName, "UTF-8")) {
//
//            while (lineIterator.hasNext()) {
//                System.out.print(lineIterator.nextLine());
//            }
//
//            long end = new Date().getTime();
//
//            long time = end - start;
//            System.out.println("LineIterator Time Consumed => " + time);
//        } catch (IOException e) {
//            System.out.println("Error: " + e.toString());
//        }
    }
}
