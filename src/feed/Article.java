package feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import namedEntity.NamedEntity;
import namedEntity.Type;
import namedEntity.classes.*;
import namedEntity.classes.subClasses.*;
import namedEntity.heuristic.Heuristic;
import namedEntity.types.*;
import namedEntity.types.subTypes.*;

/*Esta clase modela el contenido de un articulo (ie, un item en el caso del rss feed) */

public class Article {
	private String title;
	private String text;
	private Date publicationDate;
	private String link;

	private static List<NamedEntity> namedEntityList = new ArrayList<NamedEntity>();

	public Article(String title, String text, Date publicationDate, String link) {
		super();
		this.title = title;
		this.text = text;
		this.publicationDate = publicationDate;
		this.link = link;
	}

	public List<NamedEntity> getNamedEntityList() {
		return namedEntityList;
	}

    public String getSiteName() {
        String host = this.link.split("/")[2];
        String[] parts = host.split("\\.");
        String siteName = parts[parts.length - 2];
        return siteName;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", text=" + text + ", publicationDate=" + publicationDate + ", link=" + link
				+ "]";
	}

	public NamedEntity getNamedEntity(String namedEntity) {
		for (NamedEntity n : namedEntityList) {
			if (n.getName().compareTo(namedEntity) == 0) {
				return n;
			}
		}
		return null;
	}

	public void computeNamedEntities(Heuristic h) {
		String text = this.getTitle() + " " + this.getText();

		String charsToRemove = ".,;:()'!?\n";
		for (char c : charsToRemove.toCharArray()) {
			text = text.replace(String.valueOf(c), "");
		}

		for (String s : text.split(" ")) {
			if (h.isEntity(s)) {
				NamedEntity ne = this.getNamedEntity(s);
				Type type = createType(h.getType(s), h.getSubType(s));
				if (ne == null) {
					String category = h.getCategory(s) != null ? h.getCategory(s) : "Other";
					String subCategory = h.getSubCategory(s);

					NamedEntity newNe;
					switch (category.toLowerCase()) {
						case "person":
							newNe = new Person(s, category, 1, type);
							break;
						case "company":
							newNe = new Company(s, category, 1, type);
							break;
						case "place":
							newNe = new Place(s, category, 1, type);
							break;
						case "date":
							newNe = new DateC(s, category, 1, type);
							break;
						case "product":
							newNe = new Product(s, category, 1, type);
							break;
						case "event":
							newNe = new Event(s, category, 1, type);
							break;
						default:
							if (subCategory != null) { // Es subclase, no tiene que crear objeto clase Other
								createSubCategory(s, subCategory, type);
								continue;
							} else {
								newNe = new Other(s, category, 1, type);
							}
							break;
					}
					newNe.incrementCounter();
					namedEntityList.add(newNe);
				} else {
					ne.incFrequency();
					ne.incrementCounter();
				}
			}
		}
	}

	public void createSubCategory(String s, String subCategory, Type type) {
		String category = subCategory;
		NamedEntity newNe;
		switch (category.toLowerCase()) {
			case "name":
				newNe = new Name(s, category, 1, type);
				break;
			case "surname":
				newNe = new Surname(s, category, 1, type);
				break;
			case "address":
				newNe = new Address(s, category, 1, type);
				break;
			case "city":
				newNe = new City(s, category, 1, type);
				break;
			case "country":
				newNe = new Country(s, category, 1, type);
				break;
			case "grade":
				newNe = new Grade(s, category, 1, type);
				break;
			case "otherplace":
				newNe = new OtherPlace(s, category, 1, type);
				break;
			default:
				return;
		}
		newNe.incrementCounter();
		namedEntityList.add(newNe);
	}

	public Type createType(String typeName, String subTypeName) {
		typeName = (typeName != null) ? typeName.toLowerCase() : "other";
		subTypeName = (subTypeName != null) ? subTypeName.toLowerCase() : "other";

		Type type;
		switch (typeName) {
			case "sport":
				type = new Sport();
				break;
			case "culture":
				type = new Culture();
				break;
			case "policy":
				type = new Policy();
				break;
			default:
				return createSubtype(subTypeName);
		}
		type.incrementCounter();
		return type;
	}

	public Type createSubtype(String subTypeName) {
		subTypeName = (subTypeName != null) ? subTypeName.toLowerCase() : "other";

		Type subType;
		switch (subTypeName) {
			case "football":
				subType = new Football();
				break;
			case "basket":
				subType = new Basket();
				break;
			case "tennis":
				subType = new Tennis();
				break;
			case "f1":
				subType = new F1();
				break;
			case "othersport":
				subType = new OtherSport();
				break;
			case "cinema":
				subType = new Cinema();
				break;
			case "music":
				subType = new Music();
				break;
			case "otherculture":
				subType = new OtherCulture();
				break;
			case "national":
				subType = new National();
				break;
			case "international":
				subType = new International();
				break;
			case "otherpolicy":
				subType = new OtherPolicy();
				break;
			default:
				subType = new OtherType();
		}
		subType.incrementCounter();
		return subType;
	}

	public void namedEntitiesPrettyPrint() {
		for (NamedEntity ne : namedEntityList) {
			if (!ne.getCategory().equals("Other") || !ne.getType().getClass().getSimpleName().equals("OtherType")) {
				ne.prettyPrint();
			}
		}
	}

	public void counterPrettyPrint() {
		printHeader();
		printNamedEntitySummary();
		printTypeSummary();
		printFooter();
	}

	private void printHeader() {
		System.out.println();
		System.out.println(
				"══════════════════════════════════════════════════════════════════════════════════════════════");
		System.out.println("📊 Named Entity Ocurrences Summary");
		System.out.println(
				"══════════════════════════════════════════════════════════════════════════════════════════════");
	}

	private void printNamedEntitySummary() {
		NamedEntity namedEntity = new NamedEntity("", "", 0, null) {
		};
		NamedEntity person = new Person("", "", 0, null);
		NamedEntity company = new Company("", "", 0, null);
		NamedEntity date = new DateC("", "", 0, null);
		NamedEntity event = new Event("", "", 0, null);
		NamedEntity product = new Product("", "", 0, null);
		NamedEntity place = new Place("", "", 0, null);
		NamedEntity other = new Other("", "", 0, null);

		NamedEntity name = new Name("", "", 0, null);
		NamedEntity surname = new Surname("", "", 0, null);
		NamedEntity address = new Address("", "", 0, null);
		NamedEntity city = new City("", "", 0, null);
		NamedEntity country = new Country("", "", 0, null);
		NamedEntity grade = new Grade("", "", 0, null);
		NamedEntity otherPlace = new OtherPlace("", "", 0, null);

		System.out.printf("NamedEntity (Total): %d%n", namedEntity.getCounter().getValue());
		System.out.printf("│%n");
		System.out.printf("├─ Person : %d%n", person.getCounter().getValue());
		System.out.printf("│  ├─ Name     : %d%n", name.getCounter().getValue());
		System.out.printf("│  ├─ Surname  : %d%n", surname.getCounter().getValue());
		System.out.printf("│  └─ Grade    : %d%n", grade.getCounter().getValue());
		System.out.printf("│%n");
		System.out.printf("├─ Place   : %d%n", place.getCounter().getValue());
		System.out.printf("│  ├─ Address     : %d%n", address.getCounter().getValue());
		System.out.printf("│  ├─ City        : %d%n", city.getCounter().getValue());
		System.out.printf("│  ├─ Country     : %d%n", country.getCounter().getValue());
		System.out.printf("│  └─ OtherPlace  : %d%n", otherPlace.getCounter().getValue());
		System.out.printf("│%n");
		System.out.printf("├─ Company : %d%n", company.getCounter().getValue());
		System.out.printf("├─ Product : %d%n", product.getCounter().getValue());
		System.out.printf("├─ Event   : %d%n", event.getCounter().getValue());
		System.out.printf("├─ Date    : %d%n", date.getCounter().getValue());
		System.out.printf("└─ Other   : %d%n", other.getCounter().getValue());
	}

	private void printTypeSummary() {
		Type type = new Type() {
		};
		Type sport = new Sport();
		Type football = new Football();
		Type basket = new Basket();
		Type tennis = new Tennis();
		Type f1 = new F1();
		Type otherSport = new OtherSport();

		Type culture = new Culture();
		Type cinema = new Cinema();
		Type music = new Music();
		Type otherCulture = new OtherCulture();

		Type policy = new Policy();
		Type international = new International();
		Type national = new National();
		Type otherPolicy = new OtherPolicy();

		Type otherType = new OtherType();

		System.out.println("\nType (Total): " + type.getCounter().getValue());
		System.out.printf("│%n");
		System.out.printf("├─ Sports     : %d%n", sport.getCounter().getValue());
		System.out.printf("│  ├─ Football      : %d%n", football.getCounter().getValue());
		System.out.printf("│  ├─ Basket        : %d%n", basket.getCounter().getValue());
		System.out.printf("│  ├─ Tennis        : %d%n", tennis.getCounter().getValue());
		System.out.printf("│  ├─ F1            : %d%n", f1.getCounter().getValue());
		System.out.printf("│  └─ OtherSport    : %d%n", otherSport.getCounter().getValue());
		System.out.printf("│%n");
		System.out.printf("├─ Culture    : %d%n", culture.getCounter().getValue());
		System.out.printf("│  ├─ Cinema        : %d%n", cinema.getCounter().getValue());
		System.out.printf("│  ├─ Music         : %d%n", music.getCounter().getValue());
		System.out.printf("│  └─ OtherCulture  : %d%n", otherCulture.getCounter().getValue());
		System.out.printf("│%n");
		System.out.printf("├─ Policy     : %d%n", policy.getCounter().getValue());
		System.out.printf("│  ├─ National : %d%n", national.getCounter().getValue());
		System.out.printf("│  ├─ International : %d%n", international.getCounter().getValue());
		System.out.printf("│  └─ OtherPolicy   : %d%n", otherPolicy.getCounter().getValue());
		System.out.printf("│%n");
		System.out.printf("└─ OtherType  : %d%n", otherType.getCounter().getValue());
	}

	private void printFooter() {
		System.out.println(
				"══════════════════════════════════════════════════════════════════════════════════════════════");
	}

	public void prettyPrint() {
		System.out.println(
				"**********************************************************************************************");
		System.out.println("Title: " + this.getTitle());
		System.out.println("Publication Date: " + this.getPublicationDate());
		System.out.println("Link: " + this.getLink());
		System.out.println("Text: " + this.getText());
		System.out.println(
				"**********************************************************************************************");

	}

	public static void main(String[] args) {
		Article a = new Article("This Historically Black University Created Its Own Tech Intern Pipeline",
				"A new program at Bowie State connects computing students directly with companies, bypassing an often harsh Silicon Valley vetting process",
				new Date(),
				"https://www.nytimes.com/2023/04/05/technology/bowie-hbcu-tech-intern-pipeline.html");

		a.prettyPrint();
	}

}
