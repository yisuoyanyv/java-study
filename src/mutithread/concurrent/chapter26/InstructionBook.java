package mutithread.concurrent.chapter26;

/**
 * 产品及组装说明书
 * @author zhangjinglong
 * @date 2020-02-23-12:53
 */

//在流水线上需要加工的产品，create作为一个模版方法，提供了加工产品的说明书
public abstract class InstructionBook {
    public final void create(){
        this.firstProcess();
        this.secondProcess();
    }

    protected abstract void firstProcess();
    protected abstract void secondProcess();

}
