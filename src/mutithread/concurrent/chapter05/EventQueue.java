package mutithread.concurrent.chapter05;

import java.util.LinkedList;

/**
 * @author zhangjinglong
 * @date 2020-02-16-22:24
 */

public class EventQueue {

    private final int max;

    static class Event{

    }

    private final LinkedList<Event> eventQueue=new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT=10;

    public EventQueue(){
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max){
        this.max=max;
    }

    public void offer(Event event){
        synchronized (eventQueue){
//            if(eventQueue.size()>=max){
            while(eventQueue.size()>=max){

                try {
                    console(" the queue is full.");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            console(" the new event is submitted ");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    public Event take(){
        synchronized (eventQueue){
//            if(eventQueue.isEmpty()){
            while(eventQueue.isEmpty()){//多线程时使用if会出现bug，跳过判断
                try{
                    console(" the queue is empty.");
                    eventQueue.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            Event event=eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            console(" the event "+event+" is handled.");
            return event;
        }
    }

    public void console(String message){
        System.out.printf("%s:%s \n",Thread.currentThread().getName(),message);
    }
}
