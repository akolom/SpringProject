//package edu.mum.ezstore.validator;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.support.MessageSourceAccessor;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import edu.mum.ezstore.domain.User;
//
//@Component
//public class UserValidator implements Validator {
//
//	@Autowired
//	UserStoryService userStoryService;
//
////	access to message source
////	@Autowired
////	MessageSourceAccessor messageAccessor;
//	
//	@Override
//	public boolean supports(Class<?> clazz) {
//		// TODO Auto-generated method stub
//		return User.class.equals(clazz);
//	}
//
//	@Override
//	public void validate(Object target, Errors errors) {
//		// TODO Auto-generated method stub
//		Userstory userStory = (Userstory) target;
//		if (userStory.getTitle() != null && !userStory.getTitle().equals("")) {
//			Userstory us = userStoryService.findUserStoryByTitle(userStory.getTitle());
//			if (us != null && us.getId()!=userStory.getId()) {
//				errors.rejectValue("title", "userStory.title.duplicate", "Duplication error!");
////				errors.reject("userStory.duplicate", messageAccessor.getMessage("userStory.title.duplicate"));
//			}
//		}
//	}
//
//}
