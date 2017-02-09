package specialist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serdukovaa on 09.02.2017.
 */
public class Lambda {

    ///Subscriber pattern

    class Switcher{

        List<ElecticifyConsumer> consumers = new ArrayList<>();

        public void switchOn(){
            System.out.println("Выключатель включили");
            consumers.stream().forEach(ElecticifyConsumer::electrycityOn);
        }
    }

    class Lamp implements ElecticifyConsumer{
        public void laytOn(){
            System.out.println("Лампа зажглась");
        }
        @Override
        public void electrycityOn() {
            laytOn();
        }
    }

    class Radio implements ElecticifyConsumer{
        public void playMusik(){
            System.out.println("Музыка играет");
        }
        @Override
        public void electrycityOn() {
            playMusik();
        }
    }

    @FunctionalInterface
    interface ElecticifyConsumer{
       void electrycityOn();
   }

    @Test
    public void firstTutorial(){

        Switcher switcher = new Switcher();

        switcher.consumers.add(new Lamp());
        switcher.consumers.add(new Radio());

        switcher.consumers.add(new ElecticifyConsumer(){
            @Override
            public void electrycityOn() {
                System.out.println("noname class");
            }
        });

        //And now Magic!!!
        //Ta-Da!!))
        switcher.consumers.add(()-> System.out.println("Lambda expression"));

        switcher.switchOn();

    }


}
