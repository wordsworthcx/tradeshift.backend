package tradeshift.foodfacility.translator;

/**
 * @author xuch.
 */
public abstract class BaseTranslator<S, T> {
    /**
     * translates source object to target object
     *
     * @param s
     * @return T
     */
    public abstract T translate(S s);

    /**
     * translates target object to source object
     *
     * @param t
     * @return S
     */
    public abstract S reverse(T t);

}
