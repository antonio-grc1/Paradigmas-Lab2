package namedEntity.heuristic;

import java.util.Map;
import static java.util.Map.entry;

public abstract class Heuristic {

	private static Map<String, String> categoryMap = Map.ofEntries(
			Map.entry("McKinsey", "Company"),
			Map.entry("OxyContin", "Product"),
			Map.entry("G7", "Event"),
			Map.entry("Ukraine", "Place"),
			Map.entry("BYD", "Company"),
			Map.entry("April", "Date"),
			Map.entry("Deepfake", "Product"),
			Map.entry("ElonMusk", "Person"),
			Map.entry("Apple", "Company"),
			Map.entry("China", "Place"),
			Map.entry("Crypto", "Product"),
			Map.entry("Divorce", "Event"),
			Map.entry("WallStreet", "Place"),
			Map.entry("Tesla", "Company"),
			Map.entry("Tariff", "Event"),
			Map.entry("Hollywood", "Place"),
			Map.entry("Republicans", "Person"),
			Map.entry("Disney", "Company"),
			Map.entry("NationalGolf", "Place"),
			Map.entry("Infrastructure", "Event"),
			Map.entry("Batteries", "Product"),
			Map.entry("Biden", "Person"),
			Map.entry("Amazon", "Company"),
			Map.entry("Euro2024", "Event"),
			Map.entry("F1", "Event"),
			Map.entry("Oscars", "Event"),
			Map.entry("Meta", "Company"),
			Map.entry("Bitcoin", "Product"),
			Map.entry("WTO", "Event"));

	private static Map<String, String> subCategoryMap = Map.ofEntries(
			Map.entry("David", "Name"),
			Map.entry("Trump", "Surname"),
			Map.entry("NewYork", "City"),
			Map.entry("Philadelphia", "City"),
			Map.entry("Chicago", "City"),
			Map.entry("SiliconValley", "Address"),
			Map.entry("WhiteHouse", "Address"),
			Map.entry("Elon", "Name"),
			Map.entry("Musk", "Surname"),
			Map.entry("IsabelAllende", "Name"),
			Map.entry("DeliaOwens", "Name"),
			Map.entry("LaurenePowell", "Name"),
			Map.entry("Jobs", "Surname"),
			Map.entry("WashingtonDC", "City"),
			Map.entry("GoldenGate", "Address"),
			Map.entry("Harvard", "Grade"),
			Map.entry("PhD", "Grade"),
			Map.entry("MBA", "Grade"),
			Map.entry("MountEverest", "OtherPlace"),
			Map.entry("GrandCanyon", "OtherPlace"),
			Map.entry("John", "Name"),
			Map.entry("Doe", "Surname"),
			Map.entry("Paris", "City"),
			Map.entry("EiffelTower", "Address"),
			Map.entry("MIT", "Grade"),
			Map.entry("NobelPrize", "Grade"),
			Map.entry("NiagaraFalls", "OtherPlace"),
			Map.entry("Sahara", "OtherPlace"),
			Map.entry("Smith", "Surname"),
			Map.entry("MountFuji", "OtherPlace"));

	private static Map<String, String> typeMap = Map.ofEntries(
			Map.entry("Golf", "Sports"),
			Map.entry("Trade", "Policy"),
			Map.entry("Crime", "Policy"),
			Map.entry("Deepfake", "Culture"),
			Map.entry("Books", "Culture"),
			Map.entry("Divorce", "Policy"),
			Map.entry("Crypto", "Culture"),
			Map.entry("AI", "Culture"),
			Map.entry("Finance", "Policy"),
			Map.entry("Tax", "Policy"),
			Map.entry("Reading", "Culture"),
			Map.entry("G7", "Policy"),
			Map.entry("UkraineWar", "Policy"),
			Map.entry("Elections", "Policy"),
			Map.entry("Movies", "Culture"),
			Map.entry("Music", "Culture"),
			Map.entry("Basketball", "Sports"),
			Map.entry("Tennis", "Sports"),
			Map.entry("Cybersecurity", "Policy"),
			Map.entry("Climate", "Policy"),
			Map.entry("Soccer", "Sports"),
			Map.entry("Olympics", "Sports"),
			Map.entry("Theater", "Culture"),
			Map.entry("Painting", "Culture"),
			Map.entry("Immigration", "Policy"),
			Map.entry("Healthcare", "Policy"),
			Map.entry("Baseball", "Sports"),
			Map.entry("Photography", "Culture"),
			Map.entry("Education", "Policy"),
			Map.entry("Space", "Policy"));

	private static Map<String, String> subtypeMap = Map.ofEntries(
			Map.entry("PremierLeague", "Football"),
			Map.entry("NBA", "Basket"),
			Map.entry("Wimbledon", "Tennis"),
			Map.entry("MonacoGP", "F1"),
			Map.entry("WorldCup", "Football"),
			Map.entry("Oscars", "Cinema"),
			Map.entry("Grammys", "Music"),
			Map.entry("Broadway", "OtherCulture"),
			Map.entry("Trump", "International"),
			Map.entry("Congress", "National"),
			Map.entry("NATO", "International"),
			Map.entry("SupremeCourt", "National"),
			Map.entry("TourDeFrance", "OtherSport"),
			Map.entry("MetGala", "OtherCulture"),
			Map.entry("G20", "International"),
			Map.entry("Parliament", "National"),
			Map.entry("Masters", "Golf"),
			Map.entry("SuperBowl", "OtherSport"),
			Map.entry("Cannes", "Cinema"),
			Map.entry("Coachella", "Music"),
			Map.entry("FIFA", "International"),
			Map.entry("WhiteHouse", "National"),
			Map.entry("Olympics", "OtherSport"),
			Map.entry("Sundance", "Cinema"),
			Map.entry("Eurovision", "Music"),
			Map.entry("WTO", "International"),
			Map.entry("Senate", "National"),
			Map.entry("WorldSeries", "OtherSport"),
			Map.entry("VeniceFilmFestival", "Cinema"),
			Map.entry("Billboard", "Music"));

	public String getCategory(String entity) {
		return categoryMap.get(entity);
	}

	public String getSubCategory(String entity) {
		return subCategoryMap.get(entity);
	}

	public String getType(String entity) {
		return typeMap.get(entity);
	}

	public String getSubType(String entity) {
		return subtypeMap.get(entity);
	}

	public abstract boolean isEntity(String word);

}
