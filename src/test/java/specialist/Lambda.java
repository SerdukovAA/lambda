package specialist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by serdukovaa on 09.02.2017.
 */
public class Lambda {

    ///Subscriber pattern

    class Switcher{

        List<ElecticifyConsumer> consumers = new ArrayList<>();

        public void switchOn(){
            System.out.println("����������� ��������");
            consumers.stream().forEach(ElecticifyConsumer::electrycityOn);
        }
    }

    class Lamp implements ElecticifyConsumer{
        public void laytOn(){
            System.out.println("����� ��������");
        }
        @Override
        public void electrycityOn() {
            laytOn();
        }
    }

    class Radio implements ElecticifyConsumer{
        public void playMusik(){
            System.out.println("������ ������");
        }
        @Override
        public void electrycityOn() {
            playMusik();
        }
    }

    @FunctionalInterface // ���� �����!
    interface ElecticifyConsumer{
       void electrycityOn();
       //void electrycityOn2();
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

        //And now is Magic Time!!!
        //Ta-Da!!))
        switcher.consumers.add(()-> System.out.println("Lambda expression"));  // - ������� ������ ����� �������� ����� ����� 1 ��������! �� ������ ��� ����� 1 �� ����������� ������� ������.

        switcher.consumers.add(Lambda::fire);

        switcher.consumers.stream().forEach(ElecticifyConsumer::electrycityOn);


        switcher.switchOn();

    }

    static void fire(String name){
        System.out.println("�������� "+name);
    }

    static void fire(){
        System.out.println("��������");
    }



    class Person {

        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void print(){
            System.out.println(name +" - "+age);
        }

    }

    @Test
    public void streamTutorial(){
      List<Person> persons = new ArrayList<>();
      persons.add(new Person("����",32));
      persons.add(new Person("����",27));
      persons.add(new Person("����",45));
      persons.add(new Person("����",22));
      persons.add(new Person("����",19));

        persons.stream().forEach(Person::print);


        Optional<Person> ps = persons.stream().findAny();

        ps.get().print();
    }
}


