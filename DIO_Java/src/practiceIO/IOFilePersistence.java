package practiceIO;

import java.io.*;

public class IOFilePersistence extends FilePersistence {

    public IOFilePersistence(String fileName) throws IOException {
        super(fileName, "/managedFiles/IO/");
        var file = new File(currentDir + storedDir);
        if(!file.exists() && !file.mkdirs()) throw new IOException("Error creating file");

        clearFile();
    }

    @Override
    public String write(String data) {
        //For the try resources: We only can use the classes extended from Closeable
        try(
                var fileWriter = new FileWriter(currentDir + storedDir + fileName, true);
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
    public String readAll() {
        var content = new StringBuilder();
        try(var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))){
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
        try(var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))){
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
}
