import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

import static java.lang.Thread.sleep;

public class MusicPlayer extends Component implements Iterable {
    private ArrayList<Song> musicPlayList;
    private boolean buttonPlayPause;
    private Song nowPlaying;
    ListIterator<Song> it;

    public ListIterator<Song> iterator() {
        return new SongIterator();

    }

    public MusicPlayer() {
        super();
        this.buttonPlayPause = false;
        this.musicPlayList = myPlaylists.defaultMusicPlayList;
        this.nowPlaying = this.musicPlayList.get(0);
        it = this.iterator();
    }


    public ArrayList<Song> getMusicPlayList() {
        return this.musicPlayList;
    }

    public void play() {
        this.buttonPlayPause = true;
    }

    public void pause() {
        this.buttonPlayPause = false;
    }

    public void nextSong() {

        this.nowPlaying = it.next();
    }

    public void previousSong() {

        this.nowPlaying = it.previous();
    }

    public void songAdd(Song song) {
        this.musicPlayList.add(song);

    }

    public void songRemove(int numberOfSong) {
        this.musicPlayList.remove(numberOfSong);
    }


    public Song getNowPlaying() throws IndexOutOfBoundsException{
        return nowPlaying;
    }

    public void printPlaylist() {
        for (int i = 0; i < this.musicPlayList.size(); i++) {
            System.out.print(i + 1 + ") ");
            System.out.println(musicPlayList.get(i).SongToDisplay());
        }
        if (this.musicPlayList.size()==0)  {
            throw new IndexOutOfBoundsException("The list is empty," +
                    " please add some songs!");
        }
    }

    public void restart() {
        this.nowPlaying = musicPlayList.get(0);
    }

    public void checkWork() {
        if (this.buttonPlayPause == true) {
            System.out.println("Player is turned on!");
        } else {
            System.out.println("Player is turned off!");
        }
    }

    public void printPlay() {
        System.out.println("Now playing:" + this.nowPlaying.SongToDisplay());
    }

    public void playPlaylistsFromCurrent() {
        try {
            sleep((long) (this.nowPlaying.getLength_sec()));
            NextTrackListener listen = new NextTrackListener() {
                public void TrackChanged() {
                    nextSong();
                    printPlay();
                }
            };
            listen.TrackChanged();
            playPlaylistsFromCurrent();

        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    public void sortMyList() {

        class ComparatorClass implements Comparable {
            Song songToCompare;

            ComparatorClass(Song song) {
                this.songToCompare = song;
            }

            @Override
            public int compareTo(Object obj) {
                ComparatorClass entry = (ComparatorClass) obj;
                int result = songToCompare.getSongname().compareTo(entry.songToCompare.getSongname());
                if (result != 0) {
                    return result;
                }

                result = songToCompare.getLength_sec() - entry.songToCompare.getLength_sec();
                if (result != 0) {
                    return (int) result / Math.abs(result);
                }
                return 0;
            }
        }
        TreeSet<ComparatorClass> sortedAr = new TreeSet<ComparatorClass>();
        for (Song i : musicPlayList) {
            sortedAr.add(new ComparatorClass(i));
        }
        musicPlayList.clear();
        for (ComparatorClass j : sortedAr) {
            musicPlayList.add(j.songToCompare);
        }
    }


    /**
     * Nested class.
     * It was made in order to create constant (default) playlist.
     */
    protected class SongIterator implements ListIterator<Song> {
        int position = 0;

        @Override
        public boolean hasNext() {
            return position < musicPlayList.size() - 1;
        }

        @Override
        public Song next() {
            Song result;
            if (position < musicPlayList.size() - 1) {

                result = musicPlayList.get(position + 1);
                position++;
                return result;
            } else {
                buttonPlayPause = false;
                throw new NoSuchElementException("Playlist ended!");
            }


        }

        @Override
        public boolean hasPrevious() {
            return position > 0;
        }

        public Song previous() {
            Song result;
            if (position > 0) {

                result = musicPlayList.get(position - 1);
                position--;
                return result;
            } else {
                buttonPlayPause = false;
                throw new NoSuchElementException("Playlist is already at its beginning!");
            }
        }

        @Override
        public int nextIndex() {
            if (position < musicPlayList.size()) {
                return position + 1;
            } else {
                buttonPlayPause = false;
                throw new NoSuchElementException("Playlist ended!");
            }
        }

        @Override
        public int previousIndex() {
            if (position > 0) {
                return position - 1;
            } else {
                buttonPlayPause = false;
                throw new NoSuchElementException("Playlist is already at its beginning!");
            }
        }

        @Override
        public void remove() {
            //do nothing
        }

        @Override
        public void set(Song song) {
            //do nothing
        }

        @Override
        public void add(Song song) {
            //do nothing
        }
    }
    public void printProperties()
    {
        System.out.println("Length:"+ this.getLength()+"; Width: "+this.getWidth()+
                "; Theme: " + this.getTheme() + "; Font: " +this.getFont());
    }

    static class myPlaylists {
        public static final ArrayList<Song> defaultMusicPlayList =
                new ArrayList<Song>() {
                    {
                        add(new Song("Jingle Bells", 180));
                        add(new Song("Hungarian Dance no.5", "Johannes Brahms",
                                "classical", 153));
                        add(new Song("Another Brick in the Wall (part II)", "Pink Floyd",
                                "rock", 233));
                        add(new Song("Hafanana", "Afric Simone", "pop", 234));
                    }
                };


    }


}

interface NextTrackListener {
    void TrackChanged();

}


