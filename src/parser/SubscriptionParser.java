package parser;

import subscription.*;

import java.util.ArrayList;
import java.util.List;


import java.io.FileReader;
import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.FileNotFoundException;
/*
 * Esta clase implementa el parser del  archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public class SubscriptionParser extends GeneralParser<Subscription> {
    protected String siteName;

    public SubscriptionParser(String filePath){
        super(filePath); // Llamado "in" en clase madre
    }

    public String parseSiteName(String url) {
        String host = url.split("/")[2];
        String[] parts = host.split("\\."); // rss.nytimes.com
        siteName = parts[parts.length - 2];
        return siteName;
    }

    public JSONArray parseArray() {
        JSONArray array = null;

        try {
            FileReader reader = new FileReader(in);
            array = new JSONArray(new JSONTokener(reader));
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found");
        }
        return array;
    }

    public JSONObject parseObject(JSONArray array, int index){
        return array.getJSONObject(index);
    }

    public SingleSubscription jsonToSingleSubscription(JSONObject object){
        String url = object.getString("url");
        String urlType = object.getString("urlType");
        JSONArray urlParamsArray = object.getJSONArray("urlParams");
        List<String> urlParams = new ArrayList<>();

        for(int i = 0; i < urlParamsArray.length(); i++){
            urlParams.add(urlParamsArray.getString(i));
        }

        return new SingleSubscription(url, urlParams, urlType);
    }

    @Override
    public Subscription parse(){
        JSONArray array = parseArray();
        Subscription subscription = new Subscription();
        for(int i = 0; i < array.length(); i++){
            subscription.addSingleSubscription(jsonToSingleSubscription(parseObject(array, i)));
        }
        return subscription;
    }

}
