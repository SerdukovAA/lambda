import org.junit.Test;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by serdukovaa on 03.02.2017.
 */
@SuppressWarnings("ALL")
public class Chapter3 {



    class Track{

        public String getName() {
            return name;
        }

        String name;

        public int getLength() {
            return length;
        }

        int length;

    }

    class Album{

        public List<Track> getTrackList() {
            return trackList;
        }

        List<Track> trackList = new ArrayList<Track>();

        public Stream<Track> getTracks()
        {
            return trackList.stream();
        }



    }

    @Test
    public void _1(){

        Album a = new Album();
        System.out.println(a.getTracks().count());


    }

    public Set<String> finndLongTracks1(List<Album> albums) {
     Set<String> trackNames = new HashSet<String>();
               for(Album album : albums) {
                  for (Track track : album.getTrackList()) {
                          if (track.getLength() > 60) {
                                String name = track.getName();
                                trackNames.add(name);
                          }
                  }
               }
     return trackNames;
    }

    public Set<String> finndLongTracks2(List<Album> albums) {
        Set<String> trackNames = new HashSet<String>();
        albums
             .stream()
                .forEach(album -> {
                                    album.getTracks().forEach(track -> {
                                        if (track.getLength() > 60) {
                                            String name = track.getName();
                                            trackNames.add(name);
                                        }
                                    });
                        });
        return trackNames;
    }


    public Set<String> finndLongTracks3(List<Album> albums) {
        Set<String> trackNames = new HashSet<String>();
        albums
                .stream()
                .forEach(album -> {
                    album.getTracks()
                         .filter(track -> track.getLength() > 60)
                         .map(track -> track.getName())
                         .forEach(name -> trackNames.add(name));
                    });

        return trackNames;
    }



    public Set<String> finndLongTracks4(List<Album> albums) {
        Set<String> trackNames = new HashSet<String>();
        albums
                .stream()
                .flatMap(album -> album.getTrackList().stream())
                .filter(track -> track.getLength() > 60)
                .map(track -> track.getName())
                .forEach(name -> trackNames.add(name));
        return trackNames;
    }


    public Set<String> finndLongTracks(List<Album> albums) {
        Set<String> trackNames = albums
                                        .stream()
                                        .flatMap(album -> album.getTrackList().stream())
                                        .filter(track -> track.getLength() > 60)
                                        .map(track -> track.getName())
                                        .collect(Collectors.toSet());
        return trackNames;
    }




}

