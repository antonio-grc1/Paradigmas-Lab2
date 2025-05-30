package namedEntity.types.subTypes;

import namedEntity.types.*;
import subscription.Counter;

public class Tennis extends Sport {
    static Counter counter = new Counter();

    public Tennis() {
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
