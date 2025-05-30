package namedEntity.types;

import namedEntity.Type;
import subscription.Counter;
public class Policy extends Type {
    static Counter counter = new Counter();
    public Policy(){
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
