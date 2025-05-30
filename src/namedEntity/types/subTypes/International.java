package namedEntity.types.subTypes;

import namedEntity.types.*;
import subscription.Counter;

public class International extends Policy {
    static Counter counter = new Counter();

    public International() {
        super();
    }
    @Override
    public Counter getCounter(){
        return counter;
    }
    @Override
    public void incrementCounter() {
        counter.increment();
        super.incrementCounter();
    }
}