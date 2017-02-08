import org.junit.Test;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by serdukovaa on 03.02.2017.
 */
public class Chapter2 {


    public interface Function<T, R> {
        R apply(T t);
    }

    class User{
        String name;
    }


    @Test
    public void _1(){

        BinaryOperator<Long> addLongs = (x, y) -> x + y;
        ///!!bad BinaryOperator add = (x, y) -> x + y;

        Function<Long, Long> f1 = x -> x + 1;
        ///!!!!! Function<Long, Long> f2 = (x, y) -> x + 1; !!!
        ///!!!!! Function<Long, Long> f3 = x -> x == 1;
    }


    public final static ThreadLocal<DateFormatter> formatter = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

    @Test
    public void _2(){
        ThreadLocal.withInitial(() -> new User());
    }

   @Test
    public void _3(){

      Runnable helloWorld = () -> System.out.println("hello world");

      JButton button = new JButton();
      button.addActionListener(event -> System.out.println(event.getActionCommand()));

    }


}

