public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        int wordLength = word.length();
        for (int i = 0; i < wordLength; i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Palindrome palindrome = new Palindrome();
        Deque<Character> deque = palindrome.wordToDeque(word);
        while (!deque.isEmpty() && deque.size() != 1) {
            char front = deque.removeFirst();
            char end = deque.removeLast();
            if (front != end) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Palindrome palindrome = new Palindrome();
        Deque<Character> deque = palindrome.wordToDeque(word);
        while (!deque.isEmpty() && deque.size() != 1) {
            char front = deque.removeFirst();
            char end = deque.removeLast();
            if (!cc.equalChars(front, end)) {
                return false;
            }
        }
        return true;
    }

}
