package edu.mum.ezstore.controller;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egen.exhandle.exception.BusinessException;
import com.egen.exhandle.exception.InvalidArgumentsException;
import com.egen.exhandle.exception.ObjectNotFoundException;

import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.service.UserService;


@Controller
@RequestMapping("/demo")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    private UserService userService;

    @Autowired
    public TestController(UserService userService) {
    	this.userService=userService;
    }

    @RequestMapping(value = "/v1/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllCustomer() {

        List<User> users=userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/customers/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getCustomer(@RequestParam String name) {

        if(StringUtils.equalsIgnoreCase(name, "error")) {
            throw new BusinessException("Server error, please contact your admin", 200);
        }

        if(NumberUtils.isNumber(name)) {
            throw new InvalidArgumentsException(String.format("[Name=%s] must be string", name));
        }

        User c = searchName(name);
        if(c == null) {
            throw new ObjectNotFoundException(String.format("[Name=%s] search not found.", name));
        }

        LOG.info(">>>>>>>>>>>>>>> say name you pass: " + c.getFirstName());
        return new ResponseEntity<User>(c, HttpStatus.OK);
    }

    private User searchName(String name) {
        for(User c : userService.findAll()) {
            if(StringUtils.equalsIgnoreCase(name, c.getFirstName())) {
                return c;
            }
        }

        return null;
    }

}
