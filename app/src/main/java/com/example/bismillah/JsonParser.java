package com.example.bismillah;

//parsing 부분
// part of face -> brand -> product

    /*

    http://makeup-api.herokuapp.com/api/v1/products.json?brand=covergirl&product_type=lipstick

    {"id":114,
    "brand":"covergirl",
    "name":"CoverGirl Outlast Longwear Lipstick",
    "price":"10.99",
    "price_sign":null,
    "currency":null,
    "image_link":"https://d3t32hsnjxo7q6.cloudfront.net/i/2d46e82f21b11f658a4378abcbd1c31b_ra,w158,h184_pa,w158,h184.png",
    "product_link":"https://well.ca/products/covergirl-outlast-longwear-lipstick_105803.html",
    "website_link":"https://well.ca",
    "description":"With CoverGirl Outlast Longwear Lipstick you get both moisture and colour! No need to choose!This long lasting lipstick doesn’t flake or \ncrumble because it’s super-powered with moisture. It'll stays super fresh and \nsuper flexible all day, leaving you with a light weight but very pigmented lip look.",
    "rating":null,
    "category":"lipstick",
    "product_type":"lipstick",
    "tag_list":[],
    "created_at":"2016-10-01T18:25:52.547Z",
    "updated_at":"2017-12-27T01:50:37.669Z",
    "product_api_url":"http://makeup-api.herokuapp.com/api/v1/products/114.json",
    "api_featured_image":"//s3.amazonaws.com/donovanbailey/products/api_featured_images/000/000/114/original/open-uri20171223-4-fqgm96?1514062257",
    "product_colors":[{"hex_value":"#C5777E","colour_name":"Phantom Pink"},{"hex_value":"#A1211C","colour_name":"Red Siren"},{"hex_value":"#51122B","colour_name":"Plum Fury"}]}


    */

    /*

     [Info]

     Brand
     Product name
     Price

     Description
     Product_colors

     Link(Product Link)


     */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    String link;
    String imglink;

    public String Parsing(String json)

    {
        String infolist = "";
        String brand, name ,desc, price;
        JSONArray colors;

        try {

            JSONArray productArray = new JSONArray(json);
            JSONObject productObject = productArray.getJSONObject(0);

            brand = productObject.getString("brand");
            infolist += "Brand:\n  " + brand + "\n";

            name = productObject.getString("name");
            infolist += "Product name:\n  " + name + "\n";

            price = productObject.getString("price");
            infolist+= "Price:\n  " + price + "$\n\n";

            desc = productObject.getString("description");
            infolist += "Product Description:\n  " + desc + "\n\n";

            colors = productObject.getJSONArray("product_colors");
            infolist += "Colors: \n";

            for (int i = 0; i < colors.length(); i++) {
                String hex;
                String color_name;

                JSONObject colorObject = colors.getJSONObject(i);

                color_name = colorObject.getString("colour_name");

                infolist += "    "+color_name + "\n";
            }

            link = productObject.getString("product_link");
            infolist += "Link: \n  " + link + "\n";


            imglink = productObject.getString("image_link");

        } catch (
                JSONException e) {
            e.printStackTrace();
        }
        return infolist;
    }

    public String getUrl(){
        return link;
    }

    public String getImageUrl(){
        return imglink;
    }


}














