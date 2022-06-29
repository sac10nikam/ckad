package com.oventus.template.service;

import com.opencsv.CSVParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LineItrator2 {

    private static CSVParser parser = new CSVParser();

    //Header size is 5
    static int HEADER_SIZE = 5;

    public static void main(String[] args) throws IOException {
        test("44768999256,,,name, name2 ");
        test("44768999256,This is my message,,name, name2 ");
        test("44768999256,This is my message, with comma in it,,name, name2 ");
        test("44768999256,\"This is my message, with comma in it and protected by qoutes, but still no help with line.split method \",,name, name2 ");
        System.out.println("----------------------------------");
        testWithParser("44768999256,,,name, name2 ");
        testWithParser("44768999256,This is my message,,name, name2 ");
        testWithParser("44768999256,This is my message, with comma in it,,name, name2 ");
        testWithParser("44768999256,\"This is my message, with comma in it and protected by qoutes, but still no help with line.split method \",,name, name2 ");

    }

    public static void test(String test) {
        List<String> strings = Arrays.asList(test.split(","));
        System.out.println("strings size = " + strings.size() + ", is a match with header size = " + (strings.size()==HEADER_SIZE));
        //System.out.println("strings = " + strings);
    }

    public static void testWithParser(String test) throws IOException {
        List<String> strings = Arrays.asList(parser.parseLine(test));
        System.out.println("strings size = " + strings.size() + ", is a match with header size = " + (strings.size()==HEADER_SIZE));
        //System.out.println("strings = " + strings);
    }
}
