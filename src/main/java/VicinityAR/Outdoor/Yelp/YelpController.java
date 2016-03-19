package VicinityAR.Outdoor.Yelp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by varuna on 3/18/16.
 */

@RestController
@Component("YelpController")
public class YelpController {

    YelpService yelpService = new YelpService();

    @RequestMapping(value="/outdoor/yelp/{latitude}/{longitude}", method=RequestMethod.GET)
     public ResponseEntity<List<YelpData>> getAllActiveShipmentsInDriversVicinity(@PathVariable(value = "latitude") String latitude, @PathVariable(value = "longitude") String longitude)
    {
        return new ResponseEntity<List<YelpData>>(yelpService.getAllRestaurantsInUserVicinity(latitude, longitude), HttpStatus.OK);
    }

}
