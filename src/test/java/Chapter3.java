import org.junit.Test;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.*;
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

        public Track() {
        }

        public Track(String name) {
            this.name = name;
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


        public Album() {
        }

        public Album(List<Track> trackList) {
            this.trackList = trackList;
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




    public int add(Stream<Integer> numbers){
        return numbers.reduce(0, (acm, n) -> acm+n);
    }
    public int add2(Stream<Integer> numbers){
        return numbers.reduce(0,  Integer::sum);
    }

    public Optional<Integer> add3(Stream<Integer> numbers){
        return numbers.reduce((acm, n) -> acm+n);
    }

    @Test
    public void checkAdd(){
        Integer[] numbers = {2,4,22,1,5};


        System.out.println(add(Arrays.stream(numbers)));


        System.out.println(add2(Arrays.stream(numbers)));


        System.out.println(add3(Arrays.stream(numbers)).get());


        Integer[] numbers2 = {null, 2,4,52,1,5};

        System.out.println(add(Arrays.stream(numbers)));

        System.out.println(add2(Arrays.stream(numbers)));

        System.out.println(add3(Arrays.stream(numbers)).get());


    }




    class Artist{
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        String name;
     String nationality;


        public Stream<Artist> getMembers() {
            return members.stream();
        }

        public void setMembers(List<Artist> members) {
            this.members = members;
        }

        List<Artist> members = new ArrayList<>();

        public List<Artist> getMembersList(){
            return members;
        }

        public Artist(String name, String nationality) {
            this.name = name;
            this.nationality = nationality;
        }
    }

    public static List<String> getNamesAndOrigins(List<Artist> artists) {
        return artists.stream()
                .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
                .collect(Collectors.toList());
    }



    @Test
    public void checkGetNamesAndOrigins(){

       List<Artist> artists = new ArrayList<>();
       artists.add(new Artist("Beatls", "England"));
       artists.add(new Artist("Мумитроль", "Питер"));

       System.out.println(getNamesAndOrigins(artists).toString());

    }


    public List<Album> getAlbumsWithAtMostThreeTracks(List<Album> albums){

        return albums.stream()
                      .filter(album -> album.getTrackList().size()>2)
                .collect(Collectors.toList());

    }

    @Test
    public void checkGetAlbumsWithAtMostThreeTracks(){

        List<Album> albums = new ArrayList<>();
        Track t1 = new Track("1");
        Track t2 = new Track("32");
        Track t3 = new Track("3");
        Track t4 = new Track("133");
        Track t5 = new Track("1rw44");
        Track t6 = new Track("144");
        Track t7 = new Track("14wre4");
        Track t8= new Track("144wer");


        albums.add(new Album(Arrays.asList(new Track[]{t1, t2})));
        albums.add(new Album(Arrays.asList(new Track[]{t1,t2, t2})));
        albums.add(new Album(Arrays.asList(new Track[]{t7, t8, t6, t2})));
        albums.add(new Album(Arrays.asList(new Track[]{t5, t4})));
        albums.add(new Album(Arrays.asList(new Track[]{t5, t4, t3})));
        albums.add(new Album(Arrays.asList(new Track[]{t3})));

        System.out.println(getAlbumsWithAtMostThreeTracks(albums).size());



    }



    @Test
    public void iteration(){
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist("Beatls", "England"));

        artists.add(new Artist("Мумитроль", "Питер"));

        Artist a3 = new Artist("Мумитроль3", "Питер2");
        a3.getMembersList().add(new Artist("Мумитроль3", "Питер2"));
        a3.getMembersList().add(new Artist("Мумитроль3", "Питер2"));
        a3.getMembersList().add(new Artist("Мумитроль3", "Питер2"));
        artists.add(a3);



        //My Answer
        long totalMembers1 = artists.stream()
                            .flatMap(a -> a.getMembers())
                            .count();

        System.out.println(totalMembers1);


        int totalMembers = 0;
        for (Artist artist : artists) {
            Stream<Artist> members = artist.getMembers();
            totalMembers += members.count();
        }

        System.out.println(totalMembers);



        //Book Answer ???? Why so hard?
        int totalMembers3 =  artists.stream()
                .map(artist -> artist.getMembers().count())
                .reduce(0L, Long::sum)
                .intValue();

        System.out.println(totalMembers3);

    }


}

