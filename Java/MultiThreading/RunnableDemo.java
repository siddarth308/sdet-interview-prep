package Java.MultiThreading;

public class RunnableDemo {

    class A {

    }

    class MyThread extends A implements Runnable {

        int[] values = { 1, 2, 3, 4, 5 };

        public void run() {
            for (int i = 0; i < 5; i++) {
                values[i] = values[i] * 2;
            }
        }
    }
}
