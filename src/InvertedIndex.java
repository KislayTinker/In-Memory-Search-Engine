import java.util.*;

public class InvertedIndex {

    private Map<String, Map<Integer, Integer>> index;

    public InvertedIndex(){
        index = new HashMap<>();
    }

    public void addDocument (int docId, List<String> tokens) {
        for (String word : tokens) {

            // get or create a inner map
            Map<Integer, Integer> postings = index.get(word);
            if (postings == null) {
                postings = new HashMap<>();
                index.put(word, postings);
            }

            // update frequency
            postings.put (docId, postings.getOrDefault(docId, 0) + 1);
        }
    }

    public Map<Integer, Integer> getPostings (String word){
        return index.getOrDefault(word, Collections.emptyMap());
    }

//    public boolean containsWord(String word) {
//        return index.containsKey(word);
//    }
//
//    public int getVocabularySize() {
//        return index.size();
//    }

}
