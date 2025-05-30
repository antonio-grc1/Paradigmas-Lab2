package namedEntity;

import subscription.Counter;

public class Type {
    static Counter counter = new Counter();
    public Type(){
        super();
    }
    
    public Counter getCounter(){
        return counter;
    }
    
    public void incrementCounter(){
        counter.increment();
    }
}