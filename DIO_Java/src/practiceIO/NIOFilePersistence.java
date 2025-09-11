package practiceIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class NIOFilePersistence implements FilePersistence {
    private final String currentDir = System.getProperty("user.dir");
    private final String storeDir = "/managedFiles/NIO/";
    private final String fileName;

    public NIOFilePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        var file = new File(currentDir + storeDir);

        if(!file.exists() && !file.mkdirs()) throw new IOException("Error creating file");

        clearFile();
    }

    @Override
    public String write(String data) {
        try(
                var file = new RandomAccessFile(new File(currentDir + storeDir + fileName), "rw");
                ){
                    file.seek(file.length());
                    file.writeBytes(data);
                    file.writeBytes(System.lineSeparator());
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
        try(
                var file = new RandomAccessFile(new File(currentDir + storeDir + fileName),"r");
                var channel = file.getChannel();
                ){
                    var buffer = ByteBuffer.allocate(256);
                    var byteReader = channel.read(buffer);

                    while(byteReader != -1){
                        buffer.flip();

                        while(buffer.hasRemaining()){
                            content.append((char) buffer.get());
                        }

                        buffer.clear();
                        byteReader = channel.read(buffer);
                    }

        }catch (IOException e){
                    e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(String sentence) {
            var content = new StringBuilder();
        try(
                var file = new RandomAccessFile(new File(currentDir + storeDir + fileName),"r");
                var channel = file.getChannel()
        ){
            var buffer = ByteBuffer.allocate(256);
            var bytesReader = channel.read(buffer);

            while(bytesReader != -1){
                buffer.flip();

                while(buffer.hasRemaining()){
                    while(!content.toString().endsWith(System.lineSeparator())){
                        content.append((char) buffer.get());
                    }

                    if(content.toString().contains(sentence)){
                        break;
                    }else{
                        content.setLength(0);
                    }

                    if(!content.isEmpty()) break;
                }
                buffer.clear();
                bytesReader = channel.read(buffer);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        return content.toString();
    }

    private void clearFile() {
        try (OutputStream outputStream = new FileOutputStream(currentDir + storeDir + fileName)) {

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private List<String> toListString() {
        var content = readAll();
        return new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());
    }

}
