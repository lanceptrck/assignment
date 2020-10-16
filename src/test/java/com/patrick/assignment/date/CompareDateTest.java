package com.patrick.assignment.date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CompareDateTest {

    @Test
    public void compare_days_test() {
        Date date = new Date();
        assertEquals(14, Days.daysBetween(new DateTime(date).minusWeeks(2), new DateTime(date)).getDays());
    }
}
