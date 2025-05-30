package namedEntity.classes;

import namedEntity.NamedEntity;
import namedEntity.Type;
import subscription.Counter;

public class Other extends NamedEntity{
    static Counter counter = new Counter();
    public Other(String name, String category, int frequency, Type type){
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
