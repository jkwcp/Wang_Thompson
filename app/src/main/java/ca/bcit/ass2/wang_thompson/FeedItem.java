package ca.bcit.ass2.wang_thompson;

public class FeedItem {
    private String title;
    private String imageUrl;
    private String date;
    private String author;

    public FeedItem(String title, String imageUrl, String date, String author){
        this.title = title;
        this.imageUrl = imageUrl;
        this.date = date;
        this.author = author;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }


}
