package namedEntity.types;

import namedEntity.Type;
import subscription.Counter;

public class Culture extends Type{
    static Counter counter = new Counter();
    public Culture(){
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
