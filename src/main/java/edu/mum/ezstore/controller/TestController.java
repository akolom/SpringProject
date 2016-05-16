package edu.mum.ezstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import edu.mum.ezstore.json.User;


@Controller
@RequestMapping("/demo")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    private List<User> userList;

    public TestController() {
    	userList = new ArrayList<User>();
    	User c1 = new User();
        c1.setName("Chi Proeng Dov");
        c1.setAge(24);
        c1.setGender('M');
        userList.add(c1);

        User c2 = new User();
        c2.setName("David Villa");
        c2.setAge(20);
        c2.setGender('M');
        userList.add(c2);
    }

    @RequestMapping(value = "/v1/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllCustomer() {

        LOG.info(">>>>>>>>>>>>>>> get all customer >>>>>>>>>>");
        return new ResponseEntity<>(userList, HttpStatus.OK);
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

        LOG.info(">>>>>>>>>>>>>>> say name you pass: " + c.getName());
        return new ResponseEntity<User>(c, HttpStatus.OK);
    }

    private User searchName(String name) {
        for(User c : userList) {
            if(StringUtils.equalsIgnoreCase(name, c.getName())) {
                return c;
            }
        }

        return null;
    }

}
