class Data {
    int i;

    public void modify() throws InterruptedException {
        synchronized (this) {
            i++;

            Thread.sleep(100);

            i--;

            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public void show() {
        System.out.println(Thread.currentThread().getName() + " " + i);
    }
}

class Modifier implements Runnable {

    Data data;

    public Modifier(Data data) {
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

public class Synchronization_block {

    public static void main(String [] args) {

        Data data = new Data();

        Thread t1 = new Thread(new Modifier(data),"T1");
        Thread t2 = new Thread(new Modifier(data),"T2");
        Thread t3 = new Thread(new Modifier(data),"T3");

        t1.start();
        t2.start();
        t3.start();

    }


}


