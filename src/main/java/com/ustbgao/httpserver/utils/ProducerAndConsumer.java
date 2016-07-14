package com.ustbgao.httpserver.utils;

import java.util.PriorityQueue;

/**
 * Created by ustbgao
 */
public class ProducerAndConsumer {
    private static int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
    public static void main(String [] args){
        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();
        Producer producer = producerAndConsumer.new Producer();
        Consumer consumer = producerAndConsumer.new Consumer();
        Consumer consumer1 = producerAndConsumer.new Consumer();
        Thread pthread = new Thread(producer);
        Thread cthread1 = new Thread(consumer);
        Thread cthread2 = new Thread(consumer1);
        pthread.start();
        cthread1.start();
        cthread2.start();

    }



    class Producer implements Runnable {

        @Override
        public void run() {
            produce();

        }

        public void produce() {
            synchronized (queue) {
                while (queue.size() == queueSize) {
                    System.out.println("仓库已满，正在等待消费者取出，腾出空间");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        queue.notify();
                    }
                }
                queue.offer(1);
                queue.notify();
                System.out.println("向仓库存入一件东西后，仓库剩余的空间是:" + (queueSize - queue.size()));

            }
        }
    }

        class Consumer implements Runnable{
            @Override
            public void run() {

                consume();
            }
            public void consume(){
               synchronized (queue){
                   while(queue.size() == 0){
                       System.out.println("此时仓库里没有货物，无法取出");
                       try {
                           queue.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                           queue.notify();
                       }
                   }
                   queue.poll();
                   queue.notify();
                   System.out.println("消费者从仓库里取出了一件东西,仓库里剩余的物品数量为" + queue.size());
               }
            }
        }



}
