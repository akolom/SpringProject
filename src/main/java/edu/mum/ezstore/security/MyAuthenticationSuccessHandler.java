//package edu.mum.ezstore.security;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.mum.scrum.entities.Employee;
//import org.mum.scrum.entities.EmployeeRole;
//import org.mum.scrum.services.IHRSubSystem;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {
//
//	@Autowired
//	private IHRSubSystem hrService;
//			
//	
//	@Override
//    public void onAuthenticationSuccess(HttpServletRequest request, 
//            HttpServletResponse response, Authentication authentication) 
//            throws ServletException, IOException {
//        
////		UserDetails userDetails=(UserDetails) authentication.getPrincipal();		
////		
////		Authentication auth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), 
////				authentication.getCredentials(), buildUserAuthority(hrService.findEmployeeRoleByEmployee(userDetails.getUsername())));
////		SecurityContextHolder.getContext().setAuthentication(auth);
//       	super.onAuthenticationSuccess(request, response, authentication);
//        
//    }
//	
//	private List<GrantedAuthority> buildUserAuthority(Set<EmployeeRole> userRoles) {
//
//		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//
//		// Build user's authorities
//		for (EmployeeRole userRole : userRoles) {	
//				setAuths.add(new SimpleGrantedAuthority(userRole.getRole().getRoleName()));
////			}
//		}
//
//		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
//
//		return Result;
//	}
//	        
//	
//	
//	
//}
