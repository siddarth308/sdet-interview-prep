//  https://www.youtube.com/watch?v=xvXbvrUUGMM&list=PLsyeobzWxl7rmuFYRpkqLanwoG4pQQ7oW&index=3

package Java.MultiThreading;

public class ThreadDemo {

    class MyThread extends Thread {

        int[] values = { 2, 3, 4, 5, 8 };

        public void run() {
            for (int i = 0; i < 5; i++) {
                values[i] = values[i] * 2;
            }
        }
    }

}
