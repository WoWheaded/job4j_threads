package ru.job4j.io;

import java.io.*;

public final class FileSave {
    private final File file;

    public FileSave(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i++) {
                outputStream.write(content.charAt(i));
            }
        }
    }
}