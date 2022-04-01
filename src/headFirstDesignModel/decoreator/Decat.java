package headFirstDesignModel.decoreator;

/**
 * 低咖啡因咖啡
 */
public class Decat extends Beverage{


    public Decat() {
        description="Decat";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
