import java.util.*;

/*Boolean queries decide eligibility

TF-IDF decides relevance

Heap decides top results*/

public class searchEngine {

    private InvertedIndex invertedIndex;
    private Tokenizer tokenizer;
    private int docIdCounter;

    public searchEngine() {
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
    // TF-IDF searching
    // TF - total number of frequency of a word in a document
    // IDF - how rare the word is in other documents..
    public Map<Integer, Double> search(String query) {

        // TF is already stored in InvertedIndex

        // For IDF, we use formula - IDF(word) = log( (totalDocs + 1) / (docsContainingWord + 1) ) + 1
        // -> this formula avoids  divide by 0, avoids -ve value

        Map<Integer, Double> docs = new HashMap<>();
        List<String> tokens = tokenizer.tokenize(query);

        int totalDocs = docIdCounter;

        for (String word : tokens) {
            Map<Integer, Integer> postings = invertedIndex.getPostings(word);
            int docContainWord = postings.size();

            if (docContainWord == 0) continue;

            // IDF formula
            double idf = Math.log ((totalDocs + 1.0) / (docContainWord + 1.0)) + 1.0;

            // iterate...
            for (Map.Entry<Integer, Integer> entry : postings.entrySet()) {
                int docId = entry.getKey();
                int freq = entry.getValue();

                double tfIdfScore = freq * idf;

                docs.put(
                        docId,
                        docs.getOrDefault(docId, 0.0) + tfIdfScore
                );
            }
        }

        return docs;
    }

    // For Top-K
    // Static Result -> This avoids unnecessary reference to outer class.
    public static class Result {
        int docId;
        double score;

        Result (int docId, double score) {
            this.docId = docId;
            this.score = score;
        }
    }

    // Now add --"""Boolean Queries"""-- into the search engine which will help in easy filtering of the searched queries
    // To implement Boolean Queries -> we use words like AND, OR(default), NOT with searching query words
    public Set<Integer> booleanSearch(String query) {

        String[] tokens = query.split("\\s+");
        Set<Integer> result = new HashSet<>();

        String operator = "OR";  // default OR

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (token.equals("AND") || token.equals("OR") || token.equals("NOT")) {
                // because we will give operators with our input also...
                operator = token;
                continue;
            }

            // if not operator, then convert it to lower case and send it to function which isolates index cleanly
            Set<Integer> docs = getDocsForWord(token.toLowerCase());

            if (result.isEmpty() && !operator.equals("NOT")) {
                result.addAll(docs);
                continue;
            }

            switch(operator) {
                case "AND":
                    result.retainAll(docs);  // intersection
                    break;
                case "OR":
                    result.addAll(docs);   // union
                    break;
                case "NOT":
                    result.removeAll(docs);     // difference
                    break;
            }
        }

        return result;
    }

    private Set<Integer> getDocsForWord(String word) {
        return new HashSet<>(invertedIndex.getPostings(word).keySet());
        // keySet() is a view, not a copy. So copy view in HashSet then return.
    }


    // Top-K search...
    public List<Result> topKSearch(String query, int k) {

        Map<Integer, Double> docScores = search(query);

        PriorityQueue<Result> maxHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(b.score, a.score));

        for (Map.Entry<Integer, Double> entry : docScores.entrySet()) {
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

        searchEngine engine = new searchEngine();

//        engine.addDocument("Java is a powerful language");
//        engine.addDocument("Search engine use inverted index");
//        engine.addDocument("Java search engine example");
//        engine.addDocument("Kislay Tinker is a good student");
        engine.addDocument("Java search engine");
        engine.addDocument("Python search engine");
        engine.addDocument("Java programming");

        System.out.println(engine.getPostings("java"));
        System.out.println(engine.getPostings("search"));
        System.out.println(engine.getPostings("engine"));
        System.out.println(engine.getPostings("student"));

        Map<Integer, Double> docs = engine.search("kislay tinker");
        System.out.println(docs);

        List<Result> topResults = engine.topKSearch("java search", 2);

        for (Result r : topResults) {
            System.out.println("Doc " + r.docId + " → Score " + r.score);
        }

//        List<searching.Result> results =
//                engine.topKSearch("java programming", 5);
//
//        for (searching.Result r : results) {
//            System.out.println("Doc " + r.docId + " → Score " + r.score);
//        }

        // Java Bool search (searching with boolean queries)

        System.out.println(engine.booleanSearch("java AND search"));
        System.out.println(engine.booleanSearch("java OR python"));
    }

}
