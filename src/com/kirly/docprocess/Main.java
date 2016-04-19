package com.kirly.docprocess;

public class Main {

    public static void main(String[] args) {
        String              query = "foo bar 42";
        DocumentProcessor   processor = new DocumentProcessor(query);

        processor.addDocument("docs/file1.txt");
        processor.addDocument("docs/file2.txt");
        processor.addDocument("docs/file3.txt");
        processor.search();
        processor.printResults();
    }
}
