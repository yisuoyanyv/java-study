package headFirstDesignModel.decoreator;

/**
 *综合咖啡
 */
public class HouseBlend extends Beverage{


    public HouseBlend() {
        description="House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
