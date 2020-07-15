package com.asa.api.demo.constant.car;

import com.asa.api.demo.constant.Dropdown;
import com.asa.api.demo.exception.ResourceException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MarkTest {

    @Test
    public void getCode() {
        assertEquals(Mark.Peugeot.getCode(), "PEU");
        assertEquals(Mark.Bmw.getCode(), "BMW");
        assertEquals(Mark.Citroen.getCode(), "CIT");
        assertEquals(Mark.Renault.getCode(), "REN");
        assertEquals(Mark.Volkswagen.getCode(), "VOL");
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_WithNullValue() {
        Mark.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_WithEmptyValue() {
        Mark.of("");
    }

    @Test(expected = ResourceException.class)
    public void of_WithInvalidValue() {
        Mark.of("INVALID");
    }
    @Test
    public void testGetDropdown() {
        List<Dropdown> result = Mark.getDropDown();
        assertEquals(result.size(), 5);
    }

}