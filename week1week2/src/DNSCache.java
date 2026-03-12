import java.util.*;

class DNSEntry {
    String domain;
    String ipAddress;
    long expiryTime;

    public DNSEntry(String domain, String ipAddress, int ttlSeconds) {
        this.domain = domain;
        this.ipAddress = ipAddress;
        this.expiryTime = System.currentTimeMillis() + ttlSeconds * 1000L;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class DNSCache {

    private int maxSize;

    private LinkedHashMap<String, DNSEntry> cache;

    private int hits = 0;
    private int misses = 0;

    public DNSCache(int maxSize) {

        this.maxSize = maxSize;

        cache = new LinkedHashMap<>(16, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> eldest) {
                return size() > DNSCache.this.maxSize;
            }
        };
    }

    // Simulate upstream DNS lookup
    private String queryUpstreamDNS(String domain) {

        System.out.println("Querying upstream DNS for " + domain);

        return "172.217.14." + new Random().nextInt(255);
    }

    public String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null) {

            if (!entry.isExpired()) {
                hits++;
                System.out.println("Cache HIT → " + entry.ipAddress);
                return entry.ipAddress;
            }

            cache.remove(domain);
            System.out.println("Cache EXPIRED for " + domain);
        }

        misses++;

        String ip = queryUpstreamDNS(domain);

        DNSEntry newEntry = new DNSEntry(domain, ip, 5);

        cache.put(domain, newEntry);

        System.out.println("Cache MISS → Stored " + ip);

        return ip;
    }

    public void getCacheStats() {

        int total = hits + misses;

        double hitRate = total == 0 ? 0 : (hits * 100.0) / total;

        System.out.println("\nCache Statistics:");
        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
        System.out.println("Hit Rate: " + hitRate + "%");
    }

    public static void main(String[] args) throws InterruptedException {

        DNSCache cache = new DNSCache(5);

        cache.resolve("google.com");
        cache.resolve("google.com");
        cache.resolve("facebook.com");

        Thread.sleep(6000);

        cache.resolve("google.com");

        cache.getCacheStats();
    }
}