package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    private static final String DESCRIPTION_KEY = "description";
    private static final String IMAGE_KEY = "image";
    private static final String INGREDIENTS_ARRAY_KEY = "ingredients";
    private static final String NAME_KEY = "name";
    private static final String MAIN_NAME_KEY = "mainName";
    private static final String ALSO_KNOWN_AS_KEY = "alsoKnownAs";

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();
        try {
            JSONObject jsonObject = new JSONObject(json);
            if(jsonObject.has(PLACE_OF_ORIGIN_KEY)){
                sandwich.setPlaceOfOrigin(jsonObject.getString(PLACE_OF_ORIGIN_KEY));
            }
            if(jsonObject.has(DESCRIPTION_KEY)){
                sandwich.setDescription(jsonObject.getString(DESCRIPTION_KEY));
            }
            if(jsonObject.has(IMAGE_KEY)){
                sandwich.setImage(jsonObject.getString(IMAGE_KEY));
            }
            if(jsonObject.has(INGREDIENTS_ARRAY_KEY)){
                JSONArray ingredientsJSONArray = jsonObject.getJSONArray(INGREDIENTS_ARRAY_KEY);
                List<String> ingredients = new ArrayList<>();
                for(int i=0; i<ingredientsJSONArray.length(); i++){
                    ingredients.add(ingredientsJSONArray.getString(i));
                }
                sandwich.setIngredients(ingredients);
            }
            if(jsonObject.has(NAME_KEY)){
                JSONObject nameJSONObject = jsonObject.getJSONObject(NAME_KEY);
                if(nameJSONObject.has(MAIN_NAME_KEY)){
                    sandwich.setMainName(nameJSONObject.getString(MAIN_NAME_KEY));
                }
                if(nameJSONObject.has(ALSO_KNOWN_AS_KEY)){
                    JSONArray alsoKnownAsJSONArray = nameJSONObject.getJSONArray(ALSO_KNOWN_AS_KEY);
                    List<String> alsoKnownAsList = new ArrayList<>();
                    for(int i=0; i<alsoKnownAsJSONArray.length(); i++){
                        alsoKnownAsList.add(alsoKnownAsJSONArray.getString(i));
                    }
                    sandwich.setAlsoKnownAs(alsoKnownAsList);
                }
            }

        } catch (JSONException e) {
            sandwich = null;
            e.printStackTrace();
        }

        return sandwich;

    }
}
