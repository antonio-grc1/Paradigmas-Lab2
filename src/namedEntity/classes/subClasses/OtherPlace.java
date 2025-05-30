package namedEntity.classes.subClasses;

import namedEntity.Type;
import namedEntity.classes.Place;
import subscription.Counter;


public class OtherPlace extends Place{
    static Counter counter = new Counter();
    public OtherPlace(String name, String category, int frequency, Type type){
        super(name, category, frequency, type);
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
