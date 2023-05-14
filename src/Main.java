

class PopUp implements Runnable {

    @Override
    public void run() {
        System.out.println("PopUp");
    }
}
public class Main {
    public static void main(String[] args){
    VlcPlayer vlcPlayer = new VlcPlayer();
    vlcPlayer.start();
    PopUp popUp = new PopUp();
        System.out.println((new Thread(popUp).getName()));

    PrimeThread primeThread = new PrimeThread(5);
    System.out.println(primeThread.getName());
        System.out.println(Main.class.getName());
    Thread.currentThread();
    primeThread.start();
    }
}

class PrimeThread extends Thread {
    long isOdd;
    PrimeThread(long isOdd) {
        this.isOdd = isOdd;
    }

    public void run() {
              if(isOdd % 2 != 0 ) {
                  System.out.println("Is Odd");
              }else
              {System.out.println("Not Odd");}
    }
}

