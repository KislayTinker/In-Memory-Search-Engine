import java.util.List;

public class Main {
    public static void main(String[] args) {

        searchEngine engine = new searchEngine();

        engine.addDocument("Java search engine");
        engine.addDocument("Python search engine");
        engine.addDocument("Java programming");
        engine.addDocument("Advanced Java search techniques");

        List<searchEngine.Result> results = engine.topKTfIdfBoolean("Java AND search NOT python", 5);

        for (searchEngine.Result result : results) {
            System.out.println("Doc " + result.docId + " -> score: " + result.score);
        }
      
    }
}
