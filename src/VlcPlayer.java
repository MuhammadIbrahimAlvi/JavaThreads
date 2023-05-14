public class VlcPlayer extends Thread{
    @Override
    public void run(){
        PrimeThread primeThread = new PrimeThread(5);
        primeThread.start();
    }
}
