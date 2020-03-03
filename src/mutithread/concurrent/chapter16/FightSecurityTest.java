package mutithread.concurrent.chapter16;

/**
 * @author zhangjinglong
 * @date 2020-02-20-22:32
 */

public class FightSecurityTest {
    //旅客线程
    static class Passengers extends Thread{
        //机场安检类
        private final FightSecurity fightSecurity;

        //旅客的身份证
        private final String idCard;

        //旅客的登机牌
        private final String boardingPass;

        //构造旅客时传入身份证，登机牌以及机场安检类
        private Passengers(FightSecurity fightSecurity,String idCard,String boardingPass){
            this.fightSecurity=fightSecurity;
            this.idCard=idCard;
            this.boardingPass=boardingPass;
        }

        @Override
        public void run() {
            while (true){
                //旅客不断进行安检
                fightSecurity.pass(boardingPass,idCard);
            }
        }
    }

    public static void main(String[] args) {
        //定义三个旅客，身份证号和登机牌首字母均相同
        final FightSecurity fightSecurity=new FightSecurity();
        new Passengers(fightSecurity,"A123456","AF123456").start();
        new Passengers(fightSecurity,"B123456","BF123456").start();
        new Passengers(fightSecurity,"C123456","CF123456").start();

    }
}
