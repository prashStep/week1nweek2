import java.util.*;

public class Plagirasim {

    // n-gram → document IDs
    private HashMap<String, Set<String>> ngramIndex = new HashMap<>();

    private int N = 5;

    // Add document
    public void addDocument(String docId, String text) {

        List<String> ngrams = generateNgrams(text);

        for (String gram : ngrams) {

            ngramIndex.putIfAbsent(gram, new HashSet<>());

            ngramIndex.get(gram).add(docId);
        }

        System.out.println("Document " + docId + " indexed with " + ngrams.size() + " n-grams");
    }

    // Analyze document
    public void analyzeDocument(String docId, String text) {

        List<String> ngrams = generateNgrams(text);

        HashMap<String, Integer> matchCount = new HashMap<>();

        for (String gram : ngrams) {

            if (ngramIndex.containsKey(gram)) {

                for (String existingDoc : ngramIndex.get(gram)) {

                    matchCount.put(existingDoc,
                            matchCount.getOrDefault(existingDoc, 0) + 1);
                }
            }
        }

        System.out.println("\nAnalyzing " + docId);
        System.out.println("Extracted " + ngrams.size() + " n-grams");

        for (String doc : matchCount.keySet()) {

            int matches = matchCount.get(doc);

            double similarity = (matches * 100.0) / ngrams.size();

            System.out.println("Matches with " + doc +
                    " → " + matches +
                    " n-grams → Similarity: " +
                    String.format("%.2f", similarity) + "%");

            if (similarity > 60) {
                System.out.println("PLAGIARISM DETECTED");
            }
        }
    }

    // Generate n-grams
    private List<String> generateNgrams(String text) {

        List<String> grams = new ArrayList<>();

        String[] words = text.toLowerCase().split("\\s+");

        for (int i = 0; i <= words.length - N; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < N; j++) {

                gram.append(words[i + j]).append(" ");
            }

            grams.add(gram.toString().trim());
        }

        return grams;
    }

    public static void main(String[] args) {

        Plagirasim detector = new Plagirasim();

        String doc1 = "machine learning is a method of data analysis that automates analytical model building";
        String doc2 = "machine learning is a method of analyzing data automatically using algorithms";
        String newEssay = "machine learning is a method of data analysis that automates models";

        detector.addDocument("essay_089", doc1);
        detector.addDocument("essay_092", doc2);

        detector.analyzeDocument("essay_123", newEssay);
    }
}