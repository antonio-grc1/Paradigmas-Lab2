package namedEntity.classes.subClasses;

import namedEntity.Type;
import namedEntity.classes.Person;
import subscription.Counter;


public class Surname extends Person{
    static Counter counter = new Counter();
    public Surname(String name, String category, int frequency, Type type){
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
