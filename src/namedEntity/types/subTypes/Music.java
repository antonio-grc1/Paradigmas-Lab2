package namedEntity.types.subTypes;

import namedEntity.types.*;
import subscription.Counter;

public class Music extends Culture {
    static Counter counter = new Counter();

    public Music() {
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