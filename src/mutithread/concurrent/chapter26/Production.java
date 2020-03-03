package mutithread.concurrent.chapter26;

/**
 * 产品 继承了说明书
 * 每个产品都有产品编号
 * @author zhangjinglong
 * @date 2020-02-23-12:56
 */

public class Production extends InstructionBook {
    //产品编号
    private final int prodID;
    public Production(int prodID){
        this.prodID=prodID;
    }

    @Override
    protected void firstProcess() {
        System.out.println("execute the "+prodID+" first process");
    }

    @Override
    protected void secondProcess() {
        System.out.println("execute the "+prodID+" second process" +
                "");
    }
}
