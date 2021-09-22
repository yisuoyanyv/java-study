package juc.sync;

import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendSMS() throws Exception {
        //停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------------sendSMS");
    }

    public  synchronized void sendEmail() throws Exception{
        System.out.println("----------sendEmail");
    }

    public void getHello(){
        System.out.println("----------getHello");
    }
}

/**
 * 锁的8种问题
 *
 * 1.标准访问，先打印短信还是邮件
 * ------------sendSMS
 * ----------sendEmail
 *
 * 2.停4秒在短信方法内，先打印短信还是邮件
 *------------sendSMS
 * ----------sendEmail
 *      1 2 synchronized 锁的是同一个对象
 * 3.新增普通的hello方法，是先打短信还是hello
 *----------getHello
 * ------------sendSMS
 *      hello是普通方法，直接执行，不需要锁
 * 4.现在右两部手机，先打印短信还是邮件
 *----------sendEmail
 * ------------sendSMS
 *      synchronized锁的是当前对象，两个对象，不会影响，用的不是同一把锁
 * 5.两个静态同步方法，1部手机，先打印短信还是邮件
 *------------sendSMS
 * ----------sendEmail
 *
 * 6.两个静态同步方法，2部手机，先打印短信还是邮件
 * ------------sendSMS
 * ----------sendEmail
 *   5 6 static synchronized ，锁的范围是当前类的class对象
 * 7.一个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 *----------sendEmail
 * ------------sendSMS
 *
 * 8.1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
 *----------sendEmail
 * ------------sendSMS
 *
 */

class Lock_8{
    public static void main(String[] args) throws Exception{
        Phone phone = new Phone();
        Phone phone2 = new Phone();


        new Thread(()->{
            try{
                phone.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"AA").start();

        Thread.sleep(100);

        new Thread(()->{
            try{
//                phone.sendEmail();
//                phone.getHello();
                phone2.sendEmail();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"BB").start();
    }
}
