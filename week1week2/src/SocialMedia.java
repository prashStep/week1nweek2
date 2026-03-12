import java.util.*;

class UsernameChecker {

    // Stores registered usernames
    private HashMap<String, Integer> users = new HashMap<>();

    // Tracks username attempts
    private HashMap<String, Integer> attempts = new HashMap<>();

    // Register some existing users
    public UsernameChecker() {
        users.put("john_doe", 1);
        users.put("admin", 2);
        users.put("alice", 3);
    }

    // Check availability
    public boolean checkAvailability(String username) {

        // Update attempt count
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        return !users.containsKey(username);
    }

    // Suggest alternatives
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {

            String suggestion = username + i;

            if (!users.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        suggestions.add(username.replace("_", "."));

        return suggestions;
    }

    // Get most attempted username
    public String getMostAttempted() {

        String most = null;
        int max = 0;

        for (String name : attempts.keySet()) {

            int count = attempts.get(name);

            if (count > max) {
                max = count;
                most = name;
            }
        }

        return most + " (" + max + " attempts)";
    }
}

public class SocialMedia {

    public static void main(String[] args) {

        UsernameChecker checker = new UsernameChecker();

        // Check usernames
        System.out.println("john_doe available? " +
                checker.checkAvailability("john_doe"));

        System.out.println("jane_smith available? " +
                checker.checkAvailability("jane_smith"));

        // Suggestions
        System.out.println("Suggestions for john_doe: " +
                checker.suggestAlternatives("john_doe"));

        // Simulate repeated attempts
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");

        // Most attempted username
        System.out.println("Most attempted: " +
                checker.getMostAttempted());
    }
}