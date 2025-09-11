package practiceIO;

public interface FilePersistence {
    String write(final String data);
    boolean remove(final String sentence);
    String replace(final String oldSentence, final String newSentence);
    String readAll();
    String findBy(final String sentence);
}
