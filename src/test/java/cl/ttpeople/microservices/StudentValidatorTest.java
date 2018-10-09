package cl.ttpeople.microservices;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cl.ttpeople.microservices.validators.StudentValidator;

public class StudentValidatorTest {

	@Test
	public void rutValidatorTest() {
		StudentValidator tester = new StudentValidator();
		
		String validRutWithDots = "11.111.111-1";
		String validRutWithoutDots = "11111111-1";
		String invalidRutFormat1 = "1111.111.111-1";
		String invalidRutFormat2 = "11.11.111-1";
		String invalidRutFormat3 = "11.111.11-1";
		int ageTooYoung = 17;
		int ageAccepted = 18;
		
		assertTrue("Valid RUT with dots",tester.validateRut(validRutWithDots));
		assertTrue("Valid RUT without dots",tester.validateRut(validRutWithoutDots));
		assertFalse("Invalid RUT format on first segment",tester.validateRut(invalidRutFormat1));
		assertFalse("Invalid RUT format on second segment",tester.validateRut(invalidRutFormat2));
		assertFalse("Invalid RUT format on third segment",tester.validateRut(invalidRutFormat3));
		assertFalse("Age minor than 18",tester.validateAge(ageTooYoung));
		assertTrue("Age greater or equal to 18",tester.validateAge(ageAccepted));
	}

}
