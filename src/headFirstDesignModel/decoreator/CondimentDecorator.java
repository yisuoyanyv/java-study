package headFirstDesignModel.decoreator;

/**
 * 调料
 */
public abstract class CondimentDecorator extends Beverage{
    // 所有的调料装饰者都必须重新实现getDescription() 方法。
    public abstract String getDescription();
}
