import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


class Song {
    private String title;
    private String genre;

    public Song(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Song: " + title + " (" + genre + ")";
    }
}

interface Iterator {
    boolean hasNext();
    Song next();
}


class SequentialIterator implements Iterator {
    private List<Song> songs;
    private int position = 0;

    public SequentialIterator(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        return position < songs.size();
    }

    @Override
    public Song next() {
        return songs.get(position++);
    }
}


class ShuffleIterator implements Iterator {
    private List<Song> songs;
    private int position = 0;

    public ShuffleIterator(List<Song> songs) {
        this.songs = new ArrayList<>(songs);
        Collections.shuffle(this.songs, new Random());
    }

    @Override
    public boolean hasNext() {
        return position < songs.size();
    }

    @Override
    public Song next() {
        return songs.get(position++);
    }
}


class GenreFilterIterator implements Iterator {
    private List<Song> songs;
    private String genre;
    private int position = 0;

    public GenreFilterIterator(List<Song> songs, String genre) {
        this.songs = new ArrayList<>();
        this.genre = genre;
        for (Song song : songs) {
            if (song.getGenre().equals(genre)) {
                this.songs.add(song);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return position < songs.size();
    }

    @Override
    public Song next() {
        return songs.get(position++);
    }
}


class Playlist {
    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public Iterator createSequentialIterator() {
        return new SequentialIterator(songs);
    }

    public Iterator createShuffleIterator() {
        return new ShuffleIterator(songs);
    }

    public Iterator createGenreFilterIterator(String genre) {
        return new GenreFilterIterator(songs, genre);
    }
}

public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong(new Song("Song A", "Pop"));
        playlist.addSong(new Song("Song B", "Rock"));
        playlist.addSong(new Song("Song C", "Pop"));
        playlist.addSong(new Song("Song D", "Jazz"));

        System.out.println("Sequential Order:");
        Iterator sequentialIterator = playlist.createSequentialIterator();
        while (sequentialIterator.hasNext()) {
            System.out.println(sequentialIterator.next());
        }

        System.out.println("\nShuffle Order:");
        Iterator shuffleIterator = playlist.createShuffleIterator();
        while (shuffleIterator.hasNext()) {
            System.out.println(shuffleIterator.next());
        }

        System.out.println("\nFiltered by Genre (Pop):");
        Iterator genreFilterIterator = playlist.createGenreFilterIterator("Pop");
        while (genreFilterIterator.hasNext()) {
            System.out.println(genreFilterIterator.next());
        }
    }
}
