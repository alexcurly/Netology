
import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String[] texts = new String[25];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }

        Runnable logic = () -> {
            int ii = Integer.parseInt(Thread.currentThread().getName());
            String text1 = texts[ii];
            int maxSize = 0;
            for (int i = 0; i < text1.length(); i++) {
                for (int j = 0; j < text1.length(); j++) {
                    if (i >= j) {
                        continue;
                    }
                    boolean bFound = false;
                    for (int k = i; k < j; k++) {
                        if (text1.charAt(k) == 'b') {
                            bFound = true;
                            break;
                        }
                    }
                    if (!bFound && maxSize < j - i) {
                        maxSize = j - i;
                    }
                }
            }
            System.out.println("поток " + text1.substring(0, 100) + " -> " + maxSize);
        };

        List<Thread> threads = new ArrayList<>();
        long startTs2 = System.currentTimeMillis(); // start time
        for(int i = 0; i < texts.length; i++){
            String ii = Integer.toString(i);
            threads.add(new Thread(logic, ii));
        }
        for(Thread thread: threads){
            thread.start();
        }

        for (Thread thread : threads){
            thread.join();
        }
        long endTs2 = System.currentTimeMillis(); // end time

        System.out.println("Time: " + (endTs2 - startTs2) + "ms");



//        List<Thread> threads = new ArrayList<>();
//        ThreadGroup mainGroup = new ThreadGroup("main group");
//        ThreadGroup gThread = new ThreadGroup(mainGroup, "gThread");
//
//
//        for(int i = 0; i < texts.length; i++){
//            threads.add(new Thread(gThread, logic() ));
//        }









        long startTs = System.currentTimeMillis(); // start time
        for (String text : texts) {
            int maxSize = 0;
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < text.length(); j++) {
                    if (i >= j) {
                        continue;
                    }
                    boolean bFound = false;
                    for (int k = i; k < j; k++) {
                        if (text.charAt(k) == 'b') {
                            bFound = true;
                            break;
                        }
                    }
                    if (!bFound && maxSize < j - i) {
                        maxSize = j - i;
                    }
                }
            }
            System.out.println(text.substring(0, 100) + " -> " + maxSize);
        }
        long endTs = System.currentTimeMillis(); // end time

        System.out.println("Time: " + (endTs - startTs) + "ms");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
