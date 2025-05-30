package namedEntity.types.subTypes;
import namedEntity.types.Culture;
import subscription.Counter;


public class Cinema extends Culture {
    static Counter counter = new Counter();
    public Cinema() {
        super();
    }
    @Override
    public Counter getCounter(){
        return counter;
    }
    @Override
    public void incrementCounter(){
        counter.increment();
        super.incrementCounter();
    }
}

