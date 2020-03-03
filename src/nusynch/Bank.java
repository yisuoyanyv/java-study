package nusynch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * a bank with a number of bank accounts.
 * @author zhangjinglong
 * @date 2020-02-12-15:52
 */

public class Bank {
    private Lock bankLock;//ReentrantLock implements the Lock interface
    private Condition sufficientFunds;
    private  final double[] accounts;

    /**
     * Constructs the bank
     * @param n the number of accounts
     * @param initialBalance initialBalance the initial balance for each account
     */
    public Bank(int n,double initialBalance){
        accounts=new double[n];
        Arrays.fill(accounts,initialBalance);
        bankLock=new ReentrantLock();
        sufficientFunds=bankLock.newCondition();

    }

    /**
     * Transfers money from one account to another
     * @param from the account to transfer from
     * @param to the account to  transfer to
     * @param amount amount the amount to transfer
     */
    /*public void transfer(int from,int to, double amount) throws InterruptedException{
        bankLock.lock();//显式加锁

        try{
//            if(accounts[from]<amount) return;
            while (accounts[from]<amount){
                sufficientFunds.await();
            }
            System.out.println(Thread.currentThread());
            accounts[from]-=amount;
            System.out.printf("%10.2f from %d to %d %n",amount,from,to);
            accounts[to]+=amount;
            System.out.printf("Total Balance:%10.2f%n",getTotalBalance());
            sufficientFunds.signalAll();
        }finally {
            bankLock.unlock();
        }

    }*/

    /**
     * Transfers money from one account to another
     * @param from the account to transfer from
     * @param to the account to  transfer to
     * @param amount amount the amount to transfer
     */
    public synchronized void  transfer(int from,int to, double amount) throws InterruptedException{


        while (accounts[from]<amount){
            wait();//wait on intrinsic object lock's single condition
        }
        System.out.println(Thread.currentThread());
        accounts[from]-=amount;
        System.out.printf("%10.2f from %d to %d %n",amount,from,to);
        accounts[to]+=amount;
        System.out.printf("Total Balance:%10.2f%n",getTotalBalance());
        notifyAll();


    }

   /* *//**
     * Gets the sum of all account balances.
     * @return
     *//*
    public double getTotalBalance(){
        bankLock.lock();
        try{
            double sum=0;

            for(double a:accounts)
                sum+=a;
            return sum;
        }finally {
            bankLock.unlock();
        }


    }*/

    /**
     * Gets the sum of all account balances.
     * @return
     */
    public double getTotalBalance(){

        double sum=0;

        for(double a:accounts)
            sum+=a;
        return sum;

    }

    /**
     * Gets the number of accounts in the bank.
     * @return the number of accounts
     */
    public int size(){
        return accounts.length;
    }
}
