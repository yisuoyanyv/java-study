package headFirstDesignModel.decoreator;

/**
 * 深焙咖啡
 */
public class DarkRoast extends Beverage{


    public DarkRoast() {
        description="Dark Roast Coffee";
    }

    @Override
    public double cost() {
        return .99;
    }
}
