package practiceIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class FilePersistence {

    // System.getProperty("user.dir") -> return current directory
    protected final String currentDir = System.getProperty("user.dir");
    protected final String storedDir;
    protected final String fileName;

    protected FilePersistence(final String fileName, final String storedDir) {
        this.fileName = fileName;
        this.storedDir = storedDir;
    }

    public abstract String write(final String data);
    public abstract String readAll();
    public abstract String findBy(final String sentence);

    public boolean remove(String sentence) {
        var contentList = toListString();

        if(contentList.stream().noneMatch(c -> c.contains(sentence))) return false;
        clearFile();

        contentList.stream()
                .filter(c -> !c.contains(sentence))
                .forEach(this::write);

        return true;
    }

    public String replace(String oldSentence, String newSentence) {
        var contentList = toListString();

        if(contentList.stream().noneMatch(c -> c.contains(oldSentence))) return "";
        clearFile();

        contentList.stream()
                .map(c -> c.contains(oldSentence) ? newSentence : c)
                .forEach(this::write);

        return newSentence;
    }

    protected List<String> toListString() {
        var content = readAll();
        //It was used an ArrayList to create a mutable list
        return new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());
    }

    protected void clearFile(){
        //don't need to use outputStream.close() -> redundant when using try-with-resources (Java 7+)
        try (OutputStream outputStream = new FileOutputStream(currentDir + storedDir + fileName)) {

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
