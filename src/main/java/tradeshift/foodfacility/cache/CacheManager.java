package tradeshift.foodfacility.cache;

import tradeshift.foodfacility.constants.CacheConstants;
import tradeshift.foodfacility.model.MobileFoodFacility;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author xuch.
 */
public class CacheManager {

    private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>(16);


    /**
     * Return the cache associated with the given name.
     * @param name the cache identifier (must not be {@code null})
     * @return the associated cache, or {@code null} if none found
     */
    public Cache getCache(String name) {
        return cacheMap.get(name);
    }

    public boolean hasCache(String name) {
        return cacheMap.containsKey(name);
    }

    public final void addCache(String name, Cache cache) {
        synchronized (this.cacheMap) {
            cacheMap.put(name, cache);
        }
    }

    public void removeCache(String name) {
        synchronized (this.cacheMap) {
            cacheMap.remove(name);
        }
    }

    //private Collection<? extends org.springframework.cache.Cache> caches;


    /**
     * Specify the collection of Cache instances to use for this CacheManager.
     */
//    public void setCaches(Collection<? extends org.springframework.cache.Cache> caches) {
//        this.caches = caches;
//    }
}
