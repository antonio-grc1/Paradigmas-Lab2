package namedEntity.classes.subClasses;

import namedEntity.Type;
import namedEntity.classes.Place;
import subscription.Counter;


public class Country extends Place{
    static Counter counter = new Counter();
    public Country(String name, String category, int frequency, Type type){
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
