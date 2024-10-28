import java.util.*;

public class Analyzer {
    private List<String> words;
    private List<String> reservedWords;
    private Map<String, Integer> wordCount;
    private Map<Character, Integer> startingLetterCount;
    private Map<String, Integer> reservedWordCount;

    public Analyzer(String input, List<String> reservedWords) {
        this.reservedWords = new ArrayList<String>(reservedWords);
        this.words = processInput(input);
        this.wordCount = calculateWordCount();
        this.wordCount = calculateWordCount();
        this.startingLetterCount = calculateStartingLetterCount();
        this.reservedWordCount = calculateReservedWordCount();
    }

    private List<String> processInput(String input) {
        input = input.toLowerCase().replaceAll("[.,;:'\"-]", "");
        return Arrays.asList(input.split("\\s+"));
    }

    private Map<String, Integer> calculateWordCount() {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word: words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        return wordCount;
    }

    private Map<Character, Integer> calculateStartingLetterCount() {
        Map<Character, Integer> startingLetterCount = new TreeMap<>();
        for (String word: words) {
            char firstLetter = word.charAt(0);
            startingLetterCount.put(firstLetter, startingLetterCount.getOrDefault(firstLetter, 0) + 1);
        }
        return startingLetterCount;
    }

    private Map<String, Integer> calculateReservedWordCount() {
        Map<String, Integer> reservedWordCount = new LinkedHashMap<>();
        for (String word: reservedWords) {
            int count = wordCount.getOrDefault(word, 0);
            reservedWordCount.put(word, count);
        }
        return reservedWordCount;
    }

    // 1a unique words in input order
    public List<String> getUniqueWordsInInputOrder() {
        return new ArrayList<>(new LinkedHashSet<>(words));
    }

    // 1b unique words in alphabetical order
    public List<String> getUniqueWordsInAlphabeticalOrder() {
        Set<String> sortedSet = new TreeSet<>(words);
        return new ArrayList<>(sortedSet);
    }

    // 1c unique words by increasing word size
    public List<String> getUniqueWordsBySize() {
        List<String> uniqueWords = getUniqueWordsInInputOrder();
        uniqueWords.sort(Comparator.comparingInt(String::length).thenComparing(String::compareTo));
        return uniqueWords;
    }

    // 1d uniqye words by increasing frequency
    public List<String> getUniqueWordsByFrequency() {
        List<String> uniqueWords = new ArrayList<>(wordCount.keySet());
        uniqueWords.sort(Comparator.comparingInt(wordCount::get).thenComparing(words::indexOf));
        return uniqueWords;
    }

    // 2 count of words by starting letter
    public Map<Character, Integer> getStartingLetterCount() {
        return startingLetterCount;
    }

    // 3a reserved words in input order
    public List<String> getReservedWordsInInputOrder() {
        List<String> reservedInOrder = new ArrayList<>();
        Set<String> addedWords = new HashSet<>();

        for (String word: words) {
            if (reservedWords.contains(word) && !addedWords.contains(word)) {
                reservedInOrder.add(word);
                addedWords.add(word);
            }
        }
        return reservedInOrder;
    }

    // 3b reserved words by decreasing frequency
    public List<String> getReservedWordsByDecreasingFrequency() {
        List<String> reservedList = new ArrayList<>(reservedWordCount.keySet());
        reservedList.sort((w1, w2) -> reservedWordCount.get(w2) - reservedWordCount.get(w1));
        return reservedList;
    }

    public static void main(String[] args) {
        List<String> reservedWords = Arrays.asList( "elements", "collections", "groups");
        String input = "A collection - sometimes called a container - is simply an object that groups multiple elements into a single unit. A collections framework helps manipulate collections, creating sets of elements etc.";

        Analyzer a = new Analyzer(input, reservedWords);

        System.out.println("1a > " + a.getUniqueWordsInInputOrder());
        System.out.println("1b > " + a.getUniqueWordsInAlphabeticalOrder());
        System.out.println("1c > " + a.getUniqueWordsBySize());
        System.out.println("1d > " + a.getUniqueWordsByFrequency());

        System.out.println("2 > " + a.getStartingLetterCount());

        System.out.println("3a > " + a.getReservedWordsInInputOrder());
        System.out.println("3b > " + a.getReservedWordsByDecreasingFrequency());
    }
}