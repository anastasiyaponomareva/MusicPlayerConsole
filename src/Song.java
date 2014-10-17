/**
 * Created by mac on 17.10.14.
 */
public class Song {
    private String songname;
    private String artist;
    private String genre;
    private int length_sec;


    public Song(String songname, int length_sec) {
        this.songname = songname;
        this.length_sec = length_sec;
        this.artist = "<Unknown_artist>";
        this.genre = "<Unknown_genre>";

    }

    public Song(String songname, String artist, String genre, int length_sec) {
        this.songname = songname;
        this.length_sec = length_sec;
        this.artist = artist;
        this.genre = genre;
    }

    public int getLength_sec() {
        return length_sec;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getSongname() {
        return songname;
    }

    public String SongToDisplay() {
        return new String(songname + " - " + artist +
                "(" + genre + ") length = " +length_sec+"sec");
    }


    /**
     * Setters are based on idea that you can change information
     * just like in iTunes
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }
}
