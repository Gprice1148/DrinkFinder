package com.gordon.Dhelve.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.gordon.Dhelve.exception.Dhelve;
import com.gordon.Dhelve.model.CocktailDetail;
import com.gordon.Dhelve.util.HttpUtil;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CocktailService {

    private static final Logger logger = LoggerFactory.getLogger(CocktailService.class);

    private static final JsonParser JSON_PARSER = new JsonParser();
    private final String COCKTAIL_DETAIL_URL = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=";

    public CocktailDetail getCocktailDetail(String searchQuery) {
        try {
            URL url = new URL(COCKTAIL_DETAIL_URL + searchQuery);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != 200) {
                throw new Dhelve("Error getting Cocktail selections");
            }

            String jsonString = HttpUtil.readResponse(connection);
            return getRandomCocktailDetailFromJsonString(jsonString);
        } catch (Exception e) {
            String msg = String.format("Error calling getCocktailDetail(%s)", searchQuery);
            logger.error(msg, e);
        }
        return null;
    }

    private CocktailDetail getRandomCocktailDetailFromJsonString(String jsonString) {
        JsonArray jsonArray = parseJsonArrayFromRootObject("drinks", jsonString);
        JsonElement jsonElement = jsonArray.get(calculateRandomNumFromJsonArray(jsonArray));
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        return new CocktailDetail(
            Long.parseLong(jsonObject.get("idDrink").getAsString()),
            jsonObject.get("strDrink").getAsString(),
            jsonObject.get("strDrinkThumb").getAsString()
        );
    }

    private int calculateRandomNumFromJsonArray(JsonArray jsonArray) {
        return (int) (Math.random() * jsonArray.size() + 1);
    }

    /**
     * This method parses an array of Json objects out from underneath the root object of the Json
     * array. Other URLs for this API will have different root object names.
     *
     * @param rootObject name of the root object that contains the Json array
     * @param jsonString The string that contains the Json response
     * @return JsonArray of JsonObjects that are underneath the Object that contains the array
     */
    private JsonArray parseJsonArrayFromRootObject(String rootObject, String jsonString) {
        return (JsonArray) JSON_PARSER.parse(jsonString).getAsJsonObject()
            .get(rootObject);
    }


}
