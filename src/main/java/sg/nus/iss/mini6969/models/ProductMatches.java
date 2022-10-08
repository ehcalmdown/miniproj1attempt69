package sg.nus.iss.mini6969.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class ProductMatches {
    private Integer id;
    private String title;
    private String description;
    private String imageUrl;
    private String link;
    private String pairedWines;
    private String pairingText;


    public String getPairedWines() {
        return pairedWines;
    }
    public void setPairedWines(String pairedWines) {
        this.pairedWines = pairedWines;
    }
    public String getPairingText() {
        return pairingText;
    }
    public void setPairingText(String pairingText) {
        this.pairingText = pairingText;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public static ProductMatches create(JsonObject jo){
        ProductMatches pm = new ProductMatches();
        pm.setDescription(jo.getString("description"));
        pm.setId(jo.getInt("id"));
        pm.setImageUrl(jo.getString("imageUrl"));
        pm.setLink(jo.getString("link"));
        pm.setTitle(jo.getString("title"));
        pm.setPairedWines(jo.getString("pairedWines"));
        pm.setPairingText(jo.getString("pairingText"));
        return pm;
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder()
        .add("description", description)
        .add("id", id)
        .add("imageUrl", imageUrl)
        .add("link", link)
        .add("title", title)
        .build();
    }
    
}
