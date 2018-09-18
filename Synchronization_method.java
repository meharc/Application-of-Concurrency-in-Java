class Data1 {
    int i;

    synchronized public void modify() throws InterruptedException {

        i++;

        Thread.sleep(100);

        i--;

        System.out.println(Thread.currentThread().getName()+" "+i);

    }

    public void show() {
        System.out.println(Thread.currentThread().getName() + " " + i);
    }
}

class Modifier1 implements Runnable {

    Data1 data;

    public Modifier1(Data1 data) {
        this.data = data;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName());

        try {
            for(int i=0;i<5;i++)
                data.modify();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }

    }

}

public class Synchronization_method {

    public static void main(String [] args) {

        Data1 data = new Data1();

        Thread t1 = new Thread(new Modifier1(data),"T1");
        Thread t2 = new Thread(new Modifier1(data),"T2");
        Thread t3 = new Thread(new Modifier1(data),"T3");

        t1.start();
        t2.start();
        t3.start();

    }


}

