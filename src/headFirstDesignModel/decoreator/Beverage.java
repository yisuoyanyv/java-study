package headFirstDesignModel.decoreator;

/**
 * 饮料抽象类
 */
public abstract class Beverage {
    String description="Unknown Beverage";

    public String getDescription(){
        return description;
    }

    public abstract double cost();
}
