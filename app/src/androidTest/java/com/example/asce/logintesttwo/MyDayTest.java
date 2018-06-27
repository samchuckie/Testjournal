package com.example.asce.logintesttwo;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



@RunWith(AndroidJUnit4.class)
public class MyDayTest {

    @Rule
    public ActivityTestRule<MyDay> mMyDayTest= new ActivityTestRule<MyDay>(MyDay.class);
    private String titlemock ="A good day";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void adding_text()
    {

        Espresso.onView(withId(R.id.signuer)).perform(click());

    }
}