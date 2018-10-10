package cl.ttpeople.microservices.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cl.ttpeople.microservices.models.Student;

@Component
public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rut", "rut.whitespaces");
		Student student = (Student)target;
		if (!validateAge(student.getAge()))
			errors.rejectValue("age", "age.tooyoung");
		if (!validateRut(student.getRut()))
			errors.rejectValue("rut", "rut.invalidformat");
			
	}
	
	public boolean validateRut(String rut) {
		String regex = "((\\d{1,3}(\\.\\d{3}){2})|(\\d{7,9}))-?[\\dkK]";
		if(rut.matches(regex))
			return true;
		return false;
	}
	
	public boolean validateAge(int age) {
		return age < 18 ? false : true;
	}
}
