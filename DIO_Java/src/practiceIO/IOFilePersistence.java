package practiceIO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class IOFilePersistence implements FilePersistence {

    // System.getProperty("user.dir") -> return current directory
    private final String currentDir = System.getProperty("user.dir");
    private final String storeDir = "/managedFiles/IO/";
    private final String fileName;

    public IOFilePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        var file = new File(currentDir + storeDir);
        if(!file.exists() && !file.mkdirs()) throw new IOException("Error creating file");

        clearFile();
    }

    @Override
    public String write(String data) {
        //For the try resources: We only can use the classes extended from Closeable
        try(
                var fileWriter = new FileWriter(currentDir + storeDir + fileName, true);
                var bufferedWriter = new BufferedWriter(fileWriter);
                var printWriter = new PrintWriter(bufferedWriter)
        ){
                printWriter.println(data);
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean remove(String sentence) {
        var contentList = toListString();

        if(contentList.stream().noneMatch(c -> c.contains(sentence))) return false;

        clearFile(); //To clear the file and write from "scratch"
        contentList.stream()
                .filter(c -> !c.contains(sentence))
                .forEach(this::write);
        return true;
    }

    @Override
    public String replace(String oldSentence, String newSentence) {
        var contentList = toListString();

        if(contentList.stream().noneMatch(c -> c.contains(oldSentence))) return "";

        clearFile();
        contentList.stream()
                .map(c -> c.contains(oldSentence) ? newSentence : c)
                .forEach(this::write);

        return newSentence;
    }

    @Override
    public String readAll() {
        var content = new StringBuilder();
        try(var reader = new BufferedReader(new FileReader(currentDir + storeDir + fileName))){
            String line;
            do{
                line = reader.readLine();
                if(line != null){
                    content.append(line).append(System.lineSeparator());

                }
            }while(line!=null);

        } catch(IOException e){
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(String sentence) {
        String found = "";
        try(var reader = new BufferedReader(new FileReader(currentDir + storeDir + fileName))){
            String line = reader.readLine();
            while(line != null){
                if(line.contains(sentence)){
                    found = line;
                    break;
                }
                line = reader.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return found;
    }

    private List<String> toListString() {
        var content = readAll();
        //It was used an ArrayList to create a mutable list
        return new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());
    }

    private void clearFile(){
        //don't need to use outputStream.close() -> redundant when using try-with-resources (Java 7+)
        try (OutputStream outputStream = new FileOutputStream(currentDir + storeDir + fileName)) {

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
