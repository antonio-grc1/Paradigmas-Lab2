package namedEntity.types.subTypes;

import namedEntity.types.*;
import subscription.Counter;

public class National extends Policy {
    static Counter counter = new Counter();

    public National() {
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