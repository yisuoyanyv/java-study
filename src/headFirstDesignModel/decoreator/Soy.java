package headFirstDesignModel.decoreator;

/**
 * 摩卡
 */
public class Soy extends CondimentDecorator{
    Beverage beverage;

    public Soy(Beverage beverage){
        this.beverage=beverage;
    }



    @Override
    public String getDescription() {
        return beverage.getDescription()+", Soy";
    }

    @Override
    public double cost() {
        return .15+beverage.cost();
    }


}
