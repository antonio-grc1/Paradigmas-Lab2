package namedEntity.types.subTypes;

import namedEntity.types.Sport;
import subscription.Counter;

public class Basket extends Sport {

    static Counter counter = new Counter();
    public Basket() {
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
