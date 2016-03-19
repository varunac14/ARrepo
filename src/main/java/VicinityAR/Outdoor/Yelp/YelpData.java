package VicinityAR.Outdoor.Yelp;

/**
 * Created by varuna on 3/18/16.
 */
public class YelpData {

    String reviewCount;
    String name;
    String address;
    String latitude;
    String longitude;
    String phone_number;
    String distance;
    String image_thumbnail;
    String rating_img_thumbnail;

    public YelpData(String reviewCount, String name, String address, String latitude, String longitude, String phone_number, String distance, String thumbnail,  String rating_img_thumbnail) {
        this.reviewCount = reviewCount;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone_number = phone_number;
        this.distance = distance;
        this.image_thumbnail = thumbnail;
        this.rating_img_thumbnail = rating_img_thumbnail;
    }



    public String getReviewCount() {
        return reviewCount;
    }

    public String getName() {
        return name;
    }


    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getDistance() {
        return distance;
    }

    public String getThumbnail() {
        return image_thumbnail;
    }


    public String getRating_img_thumbnail() {
        return rating_img_thumbnail;
    }





}
