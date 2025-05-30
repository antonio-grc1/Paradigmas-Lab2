import feed.*;
import httpRequest.HttpRequester;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
import namedEntity.heuristic.RandomHeuristic;
import parser.GeneralParser;
import parser.RssParser;
import parser.SubscriptionParser;
import subscription.*;
import java.util.List;


public class FeedReaderMain {

	private static String filePath = "config/subscriptions.json";

    private static void printHelp() {
		System.out.println("Please, call this program in correct way: FeedReader [-ne]");
		System.out.println(
				"Correct usage: FeedReader [Heuristic]\n" +
						"No arguments: prints the article in a human-readable format.\n" +
						"With one argument: uses a heuristic to compute a set of named entities from the feeds and their number of occurrences (global, by class, and by subclass), and displays the results in a table format.\n");

	}
	
	public static Feed obtainFeed(){
		// Leemos el archivo de suscripcion por defecto, lo parseamos y creamos el objeto Subscription
		GeneralParser<Subscription> subscriptionParser = new SubscriptionParser(filePath);
		Subscription subscription = subscriptionParser.parse();

		// Realizamos el request, obtenemos el feed de la primer singleSubscription con el primer parametro y lo parseamos
		HttpRequester request = new HttpRequester();
		GeneralParser<List<Article>> rssParser = new RssParser(request.getFeedRss(subscription.getSingleSubscription(0).getFeedToRequest(0)));
		List<Article> articleList = rssParser.parse();
		
		// Llamamos al constructor de Feed y le asignamos la lista de articulos obtenida
		Feed feed = new Feed(rssParser.parseSiteName(articleList.get(0).getLink())); //El cero es solo para indicarle un articulo para acceder a su link (es el mismo en todos)
		feed.setArticleList(articleList);
		return feed;
	}

	public static void main(String[] args) {
		System.out.println("************* FeedReader version 1.0 *************");
		if (args.length == 0) {

			// Imprimimos en pantalla los articulos del Feed
			obtainFeed().prettyPrint();
			
		} else if (args.length == 1){

			// Chequeamos la heuristica recibida
			Heuristic h = null;
			if(args[0].equals("Quick") || args[0].equals("q")){
				h = new QuickHeuristic();
			}else if(args[0].equals("Random") || args[0].equals("r")){
				h = new RandomHeuristic();
			}else{
				printHelp();
				System.exit(1);
			}

			// Obtenemos el feed
			Feed feed = obtainFeed();

			// Computamos las entidades nombradas a partir de la heuristica recibida
			for(int i = 0; i < feed.getArticleList().size(); i++){
				Article article = feed.getArticle(i);
				article.computeNamedEntities(h);
			}

			// PrettyPrint de las tablas de entidades
			feed.getArticle(0).getNamedEntityList().get(0).printHeader();
			feed.getArticle(0).namedEntitiesPrettyPrint();
			feed.getArticle(0).counterPrettyPrint();
			
		}else {
			printHelp();
		}
	}
	
}
