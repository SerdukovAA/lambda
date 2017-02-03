package com.serdukovaa.lambda;

import java.util.function.Predicate;

/**
 * Created by serdukovaa on 03.02.2017.
 */
public interface Check{
    boolean check(Predicate<Integer> predicate);
    boolean check(IntPred predicate);
}
