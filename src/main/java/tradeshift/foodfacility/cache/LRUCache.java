package tradeshift.foodfacility.cache;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * Solution: Hash + list.
 *
 * @author xuch
 */
public class LRUCache<K, V> implements Cache<K, V>{
    private final Map<K, V> map;
    private final CachePolicy cachePolicy;
    private final Map<K, DateTime> record;
    private final int capacity;
    private final ReadWriteLock readWriteLock;
    private final Lock readLock;
    private final Lock writeLock;

    public LRUCache(CachePolicy cachePolicy, int capacity) {
        this.cachePolicy = cachePolicy;
        this.capacity = capacity;
        this.map = new LinkedHashMap<K, V>(capacity, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
        this.record = new LinkedHashMap<K, DateTime>(capacity, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, DateTime> eldest) {
                return size() > capacity;
            }
        };
        this.readWriteLock = new ReentrantReadWriteLock(true);
        this.readLock = this.readWriteLock.readLock();
        this.writeLock = this.readWriteLock.writeLock();
    }
    
    public V get(K key) {
        readLock.lock();
        try {
            if (!map.containsKey(key) || isExpired(key)) {
                return null;
            }
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }
    
    public void set(K key, V value) {
        writeLock.lock();
        try {
            record.put(key, DateTime.now());
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        writeLock.lock();
        try {
            record.clear();
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        return map.size();
    }

    public int capacity() {
        return capacity;
    }

    public boolean isExpired(K key) {
        return !record.containsKey(key) || record.get(key).plusMillis(Math.toIntExact(cachePolicy.getFixedExpirationMillis())).isBeforeNow();
    }
}
