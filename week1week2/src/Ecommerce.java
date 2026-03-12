import java.util.*;

public class Ecommerce {

    // productId -> stock
    private HashMap<String, Integer> stockMap = new HashMap<>();

    // productId -> waiting list
    private HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    // Add product
    public void addProduct(String productId, int stock) {
        stockMap.put(productId, stock);
        waitingList.put(productId, new LinkedList<>());
    }

    // Check stock
    public int checkStock(String productId) {
        return stockMap.getOrDefault(productId, 0);
    }

    // Purchase item
    public synchronized void purchaseItem(String productId, int userId) {

        int stock = stockMap.getOrDefault(productId, 0);

        if (stock > 0) {

            stockMap.put(productId, stock - 1);

            System.out.println("User " + userId +
                    " purchased successfully. Remaining stock: " + (stock - 1));

        } else {

            Queue<Integer> queue = waitingList.get(productId);

            queue.add(userId);

            System.out.println("Stock finished. User " + userId +
                    " added to waiting list. Position #" + queue.size());
        }
    }

    // Show waiting list
    public void showWaitingList(String productId) {

        Queue<Integer> queue = waitingList.get(productId);

        System.out.println("Waiting List: " + queue);
    }

    public static void main(String[] args) {

        Ecommerce manager = new Ecommerce();

        manager.addProduct("IPHONE15_256GB", 3);

        System.out.println("Stock Available: " +
                manager.checkStock("IPHONE15_256GB"));

        manager.purchaseItem("IPHONE15_256GB", 101);
        manager.purchaseItem("IPHONE15_256GB", 102);
        manager.purchaseItem("IPHONE15_256GB", 103);

        manager.purchaseItem("IPHONE15_256GB", 104);
        manager.purchaseItem("IPHONE15_256GB", 105);

        manager.showWaitingList("IPHONE15_256GB");
    }
}