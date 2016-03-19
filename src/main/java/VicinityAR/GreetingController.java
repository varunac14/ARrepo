package VicinityAR; /**
 * Created by varuna on 3/18/16.
 */

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import Outdoor.Yelp.YelpData;
import Outdoor.Yelp.YelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();



    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name") String name) {

        System.out.println("Here!!");
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }


    YelpService yelpService = new YelpService();

    @RequestMapping(value="/outdoor/yelp")
    public ResponseEntity<List<YelpData>> getAllActiveShipmentsInDriversVicinity()
    {
        //double sLatitude            = Double.parseDouble(latitude);
        //double sLongitude           = Double.parseDouble(longitude);
        System.out.println("Yelp controller");

        return new ResponseEntity<List<YelpData>>(yelpService.getAllRestaurantsInUserVicinity(), HttpStatus.OK);

    }





}
