import java.util.*;

public class Analytics {

    // page → visit count
    private HashMap<String, Integer> pageViews = new HashMap<>();

    // page → unique visitors
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();

    // traffic source → count
    private HashMap<String, Integer> trafficSources = new HashMap<>();

    // Process incoming event
    public void processEvent(String url, String userId, String source) {

        // Count page views
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // Track unique visitors
        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        // Count traffic sources
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    // Get dashboard data
    public void getDashboard() {

        System.out.println("\n=== Real-Time Analytics Dashboard ===");

        // Top pages
        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(pageViews.entrySet());

        System.out.println("\nTop Pages:");

        int rank = 1;

        while (!pq.isEmpty() && rank <= 10) {

            Map.Entry<String, Integer> entry = pq.poll();

            String page = entry.getKey();
            int views = entry.getValue();
            int unique = uniqueVisitors.get(page).size();

            System.out.println(rank + ". " + page +
                    " - " + views + " views (" +
                    unique + " unique)");

            rank++;
        }

        // Traffic sources
        System.out.println("\nTraffic Sources:");

        for (String source : trafficSources.keySet()) {

            System.out.println(source + " → " +
                    trafficSources.get(source) + " visits");
        }
    }

    public static void main(String[] args) {

        Analytics dashboard = new Analytics();

        dashboard.processEvent("/article/breaking-news", "user_123", "google");
        dashboard.processEvent("/article/breaking-news", "user_456", "facebook");
        dashboard.processEvent("/sports/championship", "user_111", "google");
        dashboard.processEvent("/sports/championship", "user_222", "direct");
        dashboard.processEvent("/article/breaking-news", "user_123", "google");

        dashboard.getDashboard();
    }
}