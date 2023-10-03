package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class FileContent {
    private final File file;

    public FileContent(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = inputStream.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

    public String getContentWithoutUnicode() throws IOException {
        return getContent(character -> character < 0x80);
    }
}