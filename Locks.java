class Resource {

    private String name;

    public Resource(String name) {
        this.name = name;
    }

    synchronized public void operate(Resource res,int rec_depth) {

        System.out.println(Thread.currentThread().getName()+" is operating "+name+" rec:"+rec_depth+" ...");

        try {
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }

        res.operate(res,rec_depth+1);

    }

}

class Operator implements Runnable {

    private Resource r1;
    private Resource r2;

    public Operator(Resource r1, Resource r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        r1.operate(r2,0);
    }

}

public class Locks {

    public static void main(String [] args) {

        Resource r1 = new Resource("R1");
        Resource r2 = new Resource("R2");

        Operator op1 = new Operator(r1,r2);
        Operator op2 = new Operator(r2,r1);

        new Thread(op1,"T1").start();
        new Thread(op2,"T2").start();

    }

}


