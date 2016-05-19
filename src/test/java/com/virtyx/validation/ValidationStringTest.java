package com.virtyx.validation;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.virtyx.exception.ValidationError;

public class ValidationStringTest {

	private ValidationString target;

	@Before
	public void setup() {
		target = new ValidationString(null);
	}

	@Test
	public void testMinValid() throws Exception {
		target.min(5);
		List<ValidationError> errors = target.validateValue("key", "testing");
		assertEquals(0, errors.size());
	}
	
	@Test
	public void testMinInvalid() throws Exception {
		target.min(5);
		
		List<ValidationError> errors = target.validateValue("key", "hi");
		assertEquals(1, errors.size());
		
		ValidationError error = errors.get(0);
		assertEquals("key", error.getKey());
		assertEquals(
				"The string 'hi' needs to be at least 5 characters long",
				error.getMessage()
		);
	}
	
	@Test
	public void testMinNotString() throws Exception {
		target.min(0);
		List<ValidationError> errors = target.validateValue("k", 10);
		assertEquals(1, errors.size());
		
		ValidationError error = errors.get(0);
		assertEquals(
				"Failed to convert the value of 'k' into a String",
				error.getMessage()
		);
	}
	
	@Test
	public void testMinBadInput() throws Exception {
		target.min(1);
		List<ValidationError> errors = target.validateValue("k", new Object());
		assertEquals(1, errors.size());
		
		ValidationError error = errors.get(0);
		assertEquals("k", error.getKey());
		assertEquals(
				"Failed to convert the value of 'k' into a String",
				error.getMessage()
		);
	}
	
	@Test
	public void testMaxValid() throws Exception {
		target.max(5);
		List<ValidationError> errors = target.validateValue("key", "okay");
		assertEquals(0, errors.size());
	}
	
	@Test
	public void testMaxInvalid() throws Exception {
		target.max(5);
		
		List<ValidationError> errors = target.validateValue("key", "longstring");
		assertEquals(1, errors.size());
		
		ValidationError error = errors.get(0);
		assertEquals("key", error.getKey());
		assertEquals(
				"The string 'longstring' needs to be less than or equal to 5 characters long",
				error.getMessage()
		);
	}
	
	@Test
	public void testMaxConvertToString() throws Exception {
		target.max(3).convertToString();
		List<ValidationError> errors = target.validateValue("key", 100);
		assertEquals(0, errors.size());
	}
	
	@Test
	public void testMinValidOutOfOrder() throws Exception {
		target.valid("thisisokay").min(5);
		List<ValidationError> errors = target.validateValue("key", "thisisokay");
		assertEquals(0, errors.size());
	}
	
}