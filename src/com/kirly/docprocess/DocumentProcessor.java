package com.kirly.docprocess;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by kirly on 19-Apr-16.
 */

class DocumentsComparator implements Comparator<Document> {
    @Override
    public int compare(Document lhs, Document rhs) {
        return rhs.getNbMatched() - lhs.getNbMatched();
    }
}

public class DocumentProcessor {

    private LinkedList<Document> docs = new LinkedList<>();
    private String[] keywords;

    DocumentProcessor(String query) {
        keywords = query.split(" ");
    }

    public void search() {
        for (Document doc : docs)
            doc.match();
        docs.sort(new DocumentsComparator());
    }

    public void printResults() {
        for (Document doc : docs) {
            if (doc.getNbMatched() == 0) continue;
            doc.printPath();
            doc.printMatchings();
        }
    }

    public void addDocument(String path) {
        Document doc = new Document(path, keywords);
        docs.add(doc);
    }
}
