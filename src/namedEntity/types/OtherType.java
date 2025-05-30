package namedEntity.types;

import namedEntity.Type;
import subscription.Counter;

public class OtherType extends Type{
    static Counter counter = new Counter();
    public OtherType(){
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

