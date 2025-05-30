package namedEntity.types.subTypes;
import namedEntity.types.Sport;
import subscription.Counter;

public class F1 extends Sport {
    static Counter counter = new Counter();
    public F1() {
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









