package namedEntity.types;

import namedEntity.Type;
import subscription.Counter;

public class Sport extends Type{
    static Counter counter = new Counter();
    public Sport(){
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
