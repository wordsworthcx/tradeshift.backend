package tradeshift.foodfacility.cache;

/**
 * @author xuch.
 */
public interface Cache<K, V> {
    V get(K key);
    void set(K key, V value);
    void clear();
    int size();
    int capacity();
    boolean isExpired(K key);
}
