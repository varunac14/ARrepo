package VicinityAR.Outdoor.Yelp;

import VicinityAR.Outdoor.Yelp.YelpCore.YelpApi2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by varuna on 3/18/16.
 */

@Repository
public class YelpService {

    public List<YelpData> getAllRestaurantsInUserVicinity(String latitude , String longitude) {

        String consumerKey =  "o-kttC1GE2aQt3sXUrr3nQ";
        String consumerSecret = "obSzwDEDhS2Ed0kFmqc3sd2mync";
        String token = "PD8Bw_bDY7fryupXbrjSjtrCfO86gsJm";
        String tokenSecret = "f2Edqz7bkZMXW5iUja8gZAZG2uI";

        OAuthService service = new ServiceBuilder().provider(YelpApi2.class).
                                apiKey(consumerKey).apiSecret(consumerSecret).build();

        Token accessToken = new Token(token, tokenSecret);


        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
        request.addQuerystringParameter("term", "restaurants");
        request.addQuerystringParameter("ll", latitude + "," +longitude);
        request.addQuerystringParameter("sort", 1 + "");
        service.signRequest(accessToken, request);
        Response response = request.send();

        List<YelpData> result = null;
        try
        {
           result = processJson(response.getBody()) ;


        }catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }


    public List<YelpData> processJson(String jsonStuff) throws JSONException {

        JSONObject json = new JSONObject(jsonStuff);
        JSONArray businesses = json.getJSONArray("businesses");

        ArrayList<YelpData> businessObjs = new ArrayList<YelpData>(businesses.length());

        for (int i = 0; i < businesses.length(); i++) {
            JSONObject business = businesses.getJSONObject(i);
            JSONObject location = business.getJSONObject("location");
            JSONArray addressList = location.getJSONArray("display_address");
            StringBuilder address =  new StringBuilder() ;

            if(addressList!=null)
            {


                if(addressList.get(0).toString() != null)
                    address = address.append(addressList.get(0).toString());

//                if(addressList.get(1).toString() != null)
//                    address = address.append(addressList.get(1).toString());
//
//                if(addressList.get(2).toString() != null)
//                    address = address.append(addressList.get(2).toString());
            }

            JSONObject coordinates = location.getJSONObject("coordinate");

            businessObjs.add(new YelpData(
                    business.optString("review_count"),
                    business.optString("name"),
                    address.toString(),
                    coordinates.optString("latitude"),
                    coordinates.optString("longitude"),
                    business.optString("display_phone"),
                    business.optString("distance"),
                    business.optString("image_url"),
                    business.optString("rating_img_url_large")));
        }
        return businessObjs;
    }

}
