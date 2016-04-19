package com.kirly.docprocess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Matching {
    public static int NOT_FOUND = -1;

    public int     lineNb;
    public String  line;
    public int     idx;

    Matching(int lineNb, int idx, String line) {
        this.lineNb = lineNb;
        this.line = line;
        this.idx = idx;
    }
}

/**
 * Created by kirly on 19-Apr-16.
 */
public class Document {
    private final String path;
    private final String[] keywords;
    private HashMap<String, Matching> matched = new HashMap<>();

    Document(String path, final String[] keywords) {
        this.path = path;
        this.keywords = keywords;
    }

    public int match() {
        String line;
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            for (int lineNb = 1; (line = in.readLine()) != null; ++lineNb) {
                lineProcess(line, lineNb, keywords);
                if (foundAll()) break;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void lineProcess(String line, int lineNb, String[] keywords) {
        for (int i = 0; i < keywords.length; ++i) {
            if (matched.get(keywords[i]) != null) continue;

            int idx = line.indexOf(keywords[i]);
            if (idx != Matching.NOT_FOUND) {
                matched.put(keywords[i], new Matching(lineNb, idx, line));
            }
        }
    }

    public void printPath() {
        System.out.print(ANSI_COLORS.PURPLE + path);
        System.out.println(ANSI_COLORS.RESET + ":");
    }

    public void printMatchings()
    {
        Set<Map.Entry<String, Matching>> set = matched.entrySet();

        for (Map.Entry<String, Matching> m : set) {
            Matching    curr = m.getValue();
            String keyword = m.getKey();
            System.out.print("\t" + ANSI_COLORS.GREEN + curr.lineNb + ANSI_COLORS.RESET);
            System.out.print(": " + curr.line.substring(0, curr.idx));
            System.out.print(ANSI_COLORS.RED + keyword + ANSI_COLORS.RESET);
            System.out.println(curr.line.substring(curr.idx + keyword.length()));
        }
    }

    public boolean foundAll() { return keywords.length == getNbMatched(); }

    public int getNbMatched() { return matched.size(); }
}
