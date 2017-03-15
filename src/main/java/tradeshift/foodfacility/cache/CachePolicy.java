package tradeshift.foodfacility.cache;

/**
 * Allows you to define a cache policy for how to update the cache.
 *
 * It allows you to specify an initial expiring period, and a fixed expiring period.
 * For example, if you want to set up a CacheConstants with 1 hour initial expiring and 30 minutes fixed expiring, you can do:
 * 		new CacheConstants(3, 1000, 2)
 * @author xuch.
 */
public class CachePolicy {
    private boolean initialExpired;
    private long fixedExpirationMillis;
    private long initialExpirationMillis;

    private static final boolean DEFAULT_INITIAL_EXPIRED = true;
    private static final long DEFAULT_FIXED_EXPIRATION_MILLIS = 30 * 60 * 1000;
    private static final long DEFAULT_INITIAL_EXPIRATION_MILLIS = 30 * 60 * 1000;

    public CachePolicy() {
        this.fixedExpirationMillis = DEFAULT_FIXED_EXPIRATION_MILLIS;
        this.initialExpirationMillis = DEFAULT_INITIAL_EXPIRATION_MILLIS;
        this.initialExpired = DEFAULT_INITIAL_EXPIRED;
    }

    public CachePolicy(long fixedExpirationMillis) {
        this.fixedExpirationMillis = fixedExpirationMillis;
        this.initialExpirationMillis = DEFAULT_INITIAL_EXPIRATION_MILLIS;
        this.initialExpired = DEFAULT_INITIAL_EXPIRED;
    }

    public CachePolicy(long fixedExpirationMillis, long initialExpirationMillis, boolean initialExpired) {
        this.fixedExpirationMillis = fixedExpirationMillis;
        this.initialExpirationMillis = initialExpirationMillis;
        this.initialExpired = initialExpired;
    }

    public boolean isInitialExpired() {
        return initialExpired;
    }

    public void setInitialExpired(boolean initialExpired) {
        this.initialExpired = initialExpired;
    }

    public long getFixedExpirationMillis() {
        return fixedExpirationMillis;
    }

    public void setFixedExpirationMillis(long fixedExpirationMillis) {
        this.fixedExpirationMillis = fixedExpirationMillis;
    }

    public long getInitialExpirationMillis() {
        return initialExpirationMillis;
    }

    public void setInitialExpirationMillis(long initialExpirationMillis) {
        this.initialExpirationMillis = initialExpirationMillis;
    }
}
