package com.egrasoft.commentremover.service;

import com.egrasoft.fxcommons.service.FileManagerService;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PascalFileManagerService implements FileManagerService<String> {
    private PascalFileManagerService() {
    }

    @Override
    public void save(String s, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(s);
        writer.flush();
        writer.close();
    }

    @Override
    public String read(File file) throws IOException {
        FileReader reader = new FileReader(file);
        char[] data = new char[(int) file.length()];
        reader.read(data);
        reader.close();
        return new String(data);
    }

    public static PascalFileManagerService getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final PascalFileManagerService instance = new PascalFileManagerService();
    }
}
