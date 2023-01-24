package frequencyanalysissimulator.presentation.main;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 56; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\r Hello " + i);
        }
    }
}
