package edu.mum.ezstore.validator;

import edu.mum.ezstore.service.UserCredentialService;
import edu.mum.ezstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.domain.UserCredentials;
import edu.mum.ezstore.repository.UserCredentialRepository;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserCredentialRepository userCredentialRepository;

//	access to message source
//	@Autowired
//	MessageSourceAccessor messageAccessor;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		UserCredentials ucInUser=user.getUserCredentials();
		if(ucInUser.getUsername()!=null && !StringUtils.isEmpty(ucInUser.getUsername())){
			UserCredentials userCredential=userCredentialRepository.findOne(ucInUser.getUsername());
			if(userCredential!=null){
				errors.rejectValue("userCredentials.username","userCredentials.username.duplicate","Duplication error!");
	//			errors.reject("userCredentials.username.duplicate", messageAccessor.getMessage("userCredentials.username.duplicate"));
			}
		}
	}
	

}
