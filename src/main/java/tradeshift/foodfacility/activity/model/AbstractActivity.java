package tradeshift.foodfacility.activity.model;

/**
 * @author xuch.
 */
public abstract class AbstractActivity<I extends AbstractActivityRequest, O extends AbstractActivityResponse> {
    public abstract O enact(I i);
}
