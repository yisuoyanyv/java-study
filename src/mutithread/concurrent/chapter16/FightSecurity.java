package mutithread.concurrent.chapter16;

/**
 * @author zhangjinglong
 * @date 2020-02-20-22:26
 */

public class FightSecurity {

    private  int count=0;

    //登机牌
    private String boardingPass="null";
    //身份证
    private String idCard="null";

    public synchronized void pass(String boardingPass,String idCard){
        this.boardingPass=boardingPass;
        this.idCard=idCard;
        this.count++;
        check();

    }

    private void check(){
        //简单的测试，当登机牌和身份证首字母不相同时则表示检查不通过
        if(boardingPass.charAt(0)!=idCard.charAt(0)){
            throw new RuntimeException("=============Exception==========="+toString());
        }
    }

    public String toString(){
        return "The "+count+" passagers,boardingPass[ "+boardingPass+" ],idCard [ "+idCard+"]";

    }
}
