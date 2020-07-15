package com.asa.api.demo.constant.car;

import com.asa.api.demo.constant.Dropdown;
import com.asa.api.demo.exception.ResourceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryTest {

    @Test
    public void getCode() {
        assertEquals(Category.Urban.getCode(), "UR");
        assertEquals(Category.Sedan.getCode(), "SE");
        assertEquals(Category.Break.getCode(), "BR");
        assertEquals(Category.Monospace.getCode(), "MO");
        assertEquals(Category.Coupe.getCode(), "CO");
        assertEquals(Category.Cabriolet.getCode(), "CA");
        assertEquals(Category.Pickup.getCode(), "PI");
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_WithNullValue() {
        Category.of(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void of_WithEmptyValue() {
        Category.of("");
    }

    @Test(expected = ResourceException.class)
    public void of_WithInvalidValue() {
        Category.of("INVALID");
    }
    @Test
    public void testGetDropdown() {
        List<Dropdown> result = Category.getDropDown();
        assertEquals(result.size(), 7);
    }
}