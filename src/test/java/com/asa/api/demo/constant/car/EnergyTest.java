package com.asa.api.demo.constant.car;

import com.asa.api.demo.exception.ResourceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
@RunWith(MockitoJUnitRunner.class)
public class EnergyTest {

    @Test
    public void getCode() {
        assertEquals(Energy.Essence.getCode(), "ES");
        assertEquals(Energy.Diesel.getCode(), "GA");
        assertEquals(Energy.Electric.getCode(), "EL");
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_WithNullValue() {
        Energy.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_WithEmptyValue() {
        Energy.of("");
    }

    @Test(expected = ResourceException.class)
    public void of_WithInvalidValue() {
        Energy.of("INVALID");
    }

    @Test
    public void of() {
        assertEquals(Energy.Essence.of("ES"), Energy.Essence);
        assertEquals(Energy.Diesel.of("GA"), Energy.Diesel);
        assertEquals(Energy.Electric.of("EL"), Energy.Electric);
    }
}