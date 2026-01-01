import java.util.*;

public class Tokenizer {
    private Set<String> stopWords;

    public Tokenizer(){
        stopWords = new HashSet<>();
        loadstopwords();
    }

    public void loadstopwords(){
        String[] words = {
                "a", "an", "the", "is", "are", "in", "on", "at",
                "and", "or", "of", "to", "for", "with"
        };

        stopWords.addAll(Arrays.asList(words));
    }

    public List<String> tokenize(String text){
        List<String> tokens = new ArrayList<>();

        // 1. lowercase
        text = text.toLowerCase();

        // 2. remove punctuation
        text = text.replaceAll("[^a-z]", " ");  // deletes characters like commas, periods, and exclamation marks

        // 3. split
        String[] words = text.split("\\s+"); // split by white spaces

        // 4. filter
        for (String word : words) {
            if (word.isEmpty()) continue;  // if word is empty
            if (stopWords.contains(word)) continue;  // if the word is a "stopword"
            tokens.add(word);
        }

        return tokens;
    }
}
