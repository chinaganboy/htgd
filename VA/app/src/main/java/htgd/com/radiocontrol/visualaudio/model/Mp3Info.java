package htgd.com.radiocontrol.visualaudio.model;

public class Mp3Info {
    public Mp3Info(long id, long duration, long size, long albumId, String title, String albun, String displayName, String artist, String url, String ircTitle, String ircSize) {
        this.id = id;
        this.duration = duration;
        this.size = size;
        this.albumId = albumId;
        this.title = title;
        this.albun = albun;
        this.displayName = displayName;
        this.artist = artist;
        this.url = url;
        this.ircTitle = ircTitle;
        this.ircSize = ircSize;
    }

    private  long  id;
    private  long  duration;
    private  long  size;
    private  long  albumId;
    private  String title;
    private  String albun;
    private  String displayName;
    private  String artist;
    private  String url;
    private  String ircTitle;
    private  String ircSize;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbun() {
        return albun;
    }

    public void setAlbun(String albun) {
        this.albun = albun;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIrcTitle() {
        return ircTitle;
    }

    public void setIrcTitle(String ircTitle) {
        this.ircTitle = ircTitle;
    }

    public String getIrcSize() {
        return ircSize;
    }

    public void setIrcSize(String ircSize) {
        this.ircSize = ircSize;
    }


    public  Mp3Info(){
        super();
    }


}
