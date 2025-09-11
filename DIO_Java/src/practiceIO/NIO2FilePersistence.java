package practiceIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NIO2FilePersistence implements FilePersistence {

    private final String currentDir = System.getProperty("user.dir");
    private final String storedDir = "/managedFiles/NIO2/";
    private final String fileName;

    public NIO2FilePersistence(final String fileName) throws IOException {
        this.fileName = fileName;
        var path = Paths.get(currentDir + storedDir);

        if(Files.exists(path)) {
            clearFile();
        }else{
            Files.createDirectory(path);
        }
    }

    @Override
    public String write(final String data) {
        var path = Paths.get(currentDir + storedDir + fileName);
        try{
            Files.write(path, data.getBytes(), StandardOpenOption.APPEND);
            Files.write(path, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
        }catch(IOException e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean remove(String sentence) {
        var contentList = toListString();

        if(contentList.stream().noneMatch(c -> c.contains(sentence))) return false;

        clearFile();
        contentList.stream()
                .filter(c -> !c.contains(sentence))
                .forEach(this::write);

        return true;
    }

    @Override
    public String replace(String oldSentence, String newSentence) {
        var contentList = toListString();

        if(contentList.stream().noneMatch(c -> c.contains(oldSentence))) return "not found";

        clearFile();
        contentList.stream()
                .map(c -> c.contains(oldSentence) ? newSentence : c)
                .forEach(this::write);

        return newSentence;
    }

    @Override
    public String readAll() {
        var path = Paths.get(currentDir + storedDir + fileName);
        var content = "";
        try(var lines = Files.lines(path)){
            content = lines.collect(Collectors.joining(System.lineSeparator()));
        }catch(IOException e){
            e.printStackTrace();
        }
        return content;
    }

    @Override
    public String findBy(final String sentence) {
        var content = readAll();
        return Stream.of(content.split(System.lineSeparator()))
                .filter(c -> c.contains(sentence))
                .findFirst()
                .orElse("not found");
    }

    private void clearFile() {
        try (OutputStream outputStream = new FileOutputStream(currentDir + storedDir + fileName)) {

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private List<String> toListString() {
        var content = readAll();
        return new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());
    }
}
