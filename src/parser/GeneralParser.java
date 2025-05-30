package parser;


/*Esta clase modela los atributos y metodos comunes a todos los distintos tipos de parser existentes en la aplicacion*/
public abstract class GeneralParser<T> {
    String in;
    GeneralParser(String in){
        this.in = in;
    }

    public abstract T parse();
    public abstract String parseSiteName(String url);

}
