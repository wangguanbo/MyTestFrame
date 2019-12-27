package cn.testFrame.main;

import cn.testFrame.model.User;

import javax.swing.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class TestMain {

    public static void main(String[] args) {
        //TimeTaskTest();
        //BusinessThreadTest();
        //executorsTest();
        //ArrayQueueTest();
        //linkedListQueueTest();
        //System.out.println(2<<2);
        //priorityBlockingQueueTest();
        //concurrentHashMapTest();
        JFrame j = new JFrame("我得第一个窗口");
        j.setSize(255,255);
        j.getContentPane().add(new JButton("我是一个按钮"));
        j.setVisible(Boolean.TRUE);
        
    }

    //private static  Map<String,Integer>  map = new HashMap<>();
    private static  Map<String,Integer>  map = new ConcurrentHashMap<>();

//concurrentHashMapTest 线程安全测试
    private static void concurrentHashMapTest(){
        for (int y = 0; y < 2; y++) {
            new Thread(()->{
                int i = 0;
                while ( ++i < 6){
                    map.put(Thread.currentThread().getName()+"put" + i ,i);
                    printAll();
                }
            }).start();
        }
    }

    private static void printAll() {
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            String key = stringIntegerEntry.getKey();
            Integer value = stringIntegerEntry.getValue();
            System.out.println("key ： "+ key + "value : " + value);
        }
    }

    //队列测试 PriorityBlockingQueue 队列取出时按照自定义排序进行
    private static void priorityBlockingQueueTest(){
        PriorityBlockingQueue<User> users = new PriorityBlockingQueue<>();
        users.add(new User("藏1",11));
        users.add(new User("藏2",24));
        users.add(new User("藏3",10));
        users.add(new User("藏4",37));
        users.add(new User("藏5",34));
        users.add(new User("藏6",68));
        for (User user : users) {
            try {
                System.out.println(users.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


//队列测试 --- 该队列 LinkedBlockingQueue<\
    static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    static final LinkedBlockingQueue<File> queue = new LinkedBlockingQueue<>(100);
    static final File root = new File("E:\\Documnets");
    static final File exit = new File("");
    static final AtomicInteger rc = new AtomicInteger();
    static final AtomicInteger wc = new AtomicInteger();
    public static void linkedListQueueTest(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //read
                scneFile(root);
                scneFile(exit);
            }
        };

        executorService.submit(runnable);

        //write
        for (int i = 0; i < 4; i++) {
            final int index = i ;
            Runnable runnable1 = new Runnable() {
                String threadName = "Write" + index;

                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((long) (Math.random() * 1000));
                            int i1 = wc.incrementAndGet();
                            File takeFile = queue.take();
                            if (takeFile == exit) {
                                queue.put(takeFile);
                                break;
                            }
                            System.out.println("ThreadName" + threadName + ": " + i1 + "  " + takeFile.getParent());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            executorService.submit(runnable1);
        }
        executorService.shutdown();
    }

    public static void scneFile(File file){
        if (file.isDirectory()) {
            File[] files = file.listFiles(pathname -> pathname.isDirectory() || pathname.getPath().endsWith(".java"));
            for (File file1 : files) {
                scneFile(file1);
            }
        }else{
            try {
                int i = rc.incrementAndGet();
                System.out.println("Read"+i+" "+ file.getPath());
                queue.put(file);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//队列测试_----该队列模式为空不能取数据
    public static void ArrayQueueTest(){

       final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    while (true){
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println(Thread.currentThread().getName()+"准备放数据！");
                        queue.put(1);
                        System.out.println("队列中现在有"+queue.size()+"个");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        new Thread(()->{
            try {
                while (true){
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+"准备取数据");
                    queue.take();
                    System.out.println("取出后队列中还有" + queue.size() + "个数据！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();



    }

//线程池测试-固定线程
    public static void executorsTest(){
        //ExecutorService executorService = Executors.newFixedThreadPool(1);//固定线程
        //ExecutorService executorService = Executors.newSingleThreadExecutor();//单一线程
        //ExecutorService executorService = Executors.newCachedThreadPool();//可变线程
        //ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);//延时执行线程

        /*mySimpleThread thread1 = new mySimpleThread();
        mySimpleThread thread2 = new mySimpleThread();
        mySimpleThread thread3 = new mySimpleThread();
        mySimpleThread thread4 = new mySimpleThread();
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread3);
        executorService.execute(thread4);
        //submit方法会返回参数为callable参数的返回值
        Future<String> submit = executorService.submit(()-> "测试");
        //invokeAny 方法会集中一系列方法之后返回其中一个返回值*/

        /*
        HashSet<Callable<String>> callables = new HashSet<>(3);
        callables.add(() -> "callAbles One");
        callables.add(() -> "callAbles Two");
        callables.add(() -> "callAbles Thress");
        try {

            String s = executorService.invokeAny(callables);
            System.out.println(s);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }*/

        /*executorService.schedule(thread3,3, TimeUnit.SECONDS);
        executorService.schedule(thread4,5,TimeUnit.SECONDS);
        //shutdown 是将空闲的线程停掉，停之前的线程还会执行完毕才会被停止
        executorService.shutdown();
        //shutdownNow 将线程池中所有的线程停止，无论有没有执行完毕。
        executorService.shutdownNow();
        */
        //创建延时执行的线程池并规定次数为5次，是否一个任务被5个线程执行
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture<String> schedule = scheduledExecutorService.schedule(() -> "被执行了？", 5, TimeUnit.MILLISECONDS);
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                    System.out.println(".......");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {

            System.out.println(schedule.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        scheduledExecutorService.shutdown();

    }

    static class mySimpleThread implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+"线程执行了"+i+"次");
            }
        }
    }

//从线程执行5次然后主线程执行10次，循环三次s
    public static void BusinessThreadTest(){
        BusinessThread businessThread = new BusinessThread();
        // 主线程执行三次
        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                businessThread.subThread();
            }
        }).start();
        //从线程执行三次
        new Thread(()->{
            for (int i = 0; i < 3; i++) {
                businessThread.MainThread();
            }
        }).start();
    }

    static class BusinessThread extends Thread{
        private volatile static Boolean falg = true;
        public synchronized void MainThread(){
            while (falg){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            for (int i = 1; i < 6; i++) {
                System.out.println(Thread.currentThread().getName()+"__MainThread___start..." + i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            falg = true;
            notify();
        }

        public synchronized void subThread(){
            while (!falg){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 1; i < 11; i++) {
                System.out.println(Thread.currentThread().getName()+"___subThrad___user..." + i );
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            falg = false;
            notify();
        }
    }




//  定时任务先执行4次在执行2次
    public static void TimeTaskTest(){
        Timer timer = new Timer();
        timer.schedule(new TimerTast(), 2000 + 2000 * count);
        while (true){
            try {
                System.out.println("......" + LocalDateTime.now().getMinute());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static volatile int count = 0;

    static class TimerTast extends TimerTask{

        @Override
        public void run() {
            count = ( count + 1 ) % 2;
            System.out.println("boom" + "count ==" + count);
            new Timer().schedule(new TimerTast(),2000 + 2000 * count);
        }
    }
//map排序
    public static void sortMap(){
        HashMap<Integer, User> userHashMap = new HashMap<>(3);
        userHashMap.put(1,new User("张三",21));
        userHashMap.put(2,new User("里斯",26));
        userHashMap.put(3,new User("网速",32));
        userHashMap.put(4,new User("李i",29));
        userHashMap.put(5,new User("欧俄",11));
        HashMap<Integer, User> userHashMap1 = mapSort(userHashMap);
        for (Map.Entry<Integer, User> integerUserEntry : userHashMap1.entrySet()) {
            System.out.println(integerUserEntry.getValue());
        }

        int[] arr = {1,4,1,4,2,5,4,5,8,7,8,77,88,5,4,9,6,2,4,1,5};
        int[] brr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            brr[arr[i]] ++;
        }
        for (int i = 0; i < brr.length; i++) {
            if( brr[i] != 0){
                System.out.println(i + "出现了" + brr[i] +"次");
            }
        }
    }


    public static HashMap<Integer,User> mapSort(HashMap<Integer,User> paramHashMap){
        Set<Map.Entry<Integer, User>> entries = paramHashMap.entrySet();
        ArrayList<Map.Entry<Integer, User>> entries1 = new ArrayList<>(entries);
        Collections.sort(entries1, Comparator.comparingInt(o -> o.getValue().getAge()));
        HashMap<Integer,User> sortResultMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> integerUserEntry : entries1) {
            sortResultMap.put(integerUserEntry.getKey(),integerUserEntry.getValue());
        }
        return sortResultMap;
    }





}
