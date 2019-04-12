package com.portillo.naomyportillo.wellmood;

import com.portillo.naomyportillo.wellmood.logfragments.ReoccurringBehaviorFragment;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReoccurringBehaviorFragmentUnitTest {


    ReoccurringBehaviorFragment reoccurringBehaviorFragment;


    @Before
    public void setUp() throws Exception {
        reoccurringBehaviorFragment = ReoccurringBehaviorFragment.newInstance();
    }

    @Test
    public void checkWidth() {

        int greatSetWidth = 2;
        int badSetWidth = 15;
        int okaySetWidth = 20;
        int terribbleSetWidth = 0;

        int widths[] = {greatSetWidth, badSetWidth, okaySetWidth, terribbleSetWidth};

        for (int i = 0; i < widths.length; i++) {
            widths[i] = reoccurringBehaviorFragment.getWidth(widths[i]);
        }

        int [] expected = {90, 175, 350, 1};
        int[] actual = widths;
        Assert.assertArrayEquals(expected, actual);
    }

    @After
    public void tearDown() throws Exception {
        reoccurringBehaviorFragment = null;
    }
}
