package namedEntity.classes;

import namedEntity.NamedEntity;
import namedEntity.Type;
import subscription.Counter;

public class Company extends NamedEntity{
    static Counter counter = new Counter();
    public Company(String name, String category, int frequency, Type type){
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
