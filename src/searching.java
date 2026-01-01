import javax.xml.transform.Result;
import java.util.*;

public class searching {

    private InvertedIndex invertedIndex;
    private Tokenizer tokenizer;
    private int docIdCounter;

    public searching() {
        invertedIndex = new InvertedIndex();
        tokenizer = new Tokenizer();
        docIdCounter = 0;
    }

    public int addDocument(String text) {
        docIdCounter++;
        List<String> tokens = tokenizer.tokenize(text);
        invertedIndex.addDocument(docIdCounter, tokens);

        return docIdCounter;
    }

    public Map<Integer, Integer> getPostings(String word) {
        return invertedIndex.getPostings(word);
    }

    // search through words
    public Map<Integer, Integer> search(String query) {
        Map<Integer, Integer> docs = new HashMap<>();
        List<String> tokens = tokenizer.tokenize(query);

        for (String word : tokens) {
            Map<Integer, Integer> postings = invertedIndex.getPostings(word);
            // iterate...
            for (Map.Entry<Integer, Integer> entry : postings.entrySet()) {
                int docId = entry.getKey();
                int freq = entry.getValue();

                docs.put(docId, docs.getOrDefault(docId, 0) + freq);
            }
        }

        return docs;
    }

    public class Result {
        int docId;
        int score;

        Result (int docId, int score) {
            this.docId = docId;
            this.score = score;
        }
    }

    public List<Result> topKSearch(String query, int k) {

        Map<Integer, Integer> docScores = search(query);

        PriorityQueue<Result> maxHeap = new PriorityQueue<>(
                (a, b) -> b.score - a.score
        );

        for (Map.Entry<Integer, Integer> entry : docScores.entrySet()) {
            maxHeap.add(new Result(entry.getKey(), entry.getValue()));
        }

        List<Result> results = new ArrayList<>();

        while (k > 0 && !maxHeap.isEmpty()) {
            results.add(maxHeap.poll());
            k--;
        }

        return results;
    }


    public static void main(String[] args) {

        searching engine = new searching();

        engine.addDocument("Java is a powerful language");
        engine.addDocument("Search engine use inverted index");
        engine.addDocument("Java search engine example");
        engine.addDocument("Kislay Tinker is a good student");

        System.out.println(engine.getPostings("java"));
        System.out.println(engine.getPostings("search"));
        System.out.println(engine.getPostings("engine"));
        System.out.println(engine.getPostings("student"));

        Map<Integer, Integer> docs = engine.search("kislay tinker");
        System.out.println(docs);

        List<Result> topResults = engine.topKSearch("java search", 2);

        for (Result r : topResults) {
            System.out.println("Doc " + r.docId + " → Score " + r.score);
        }


    }

}
