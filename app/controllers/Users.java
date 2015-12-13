package controllers;

import play.*;
import play.mvc.With;

//@Check("admin")
@With(Secure.class)
public class Users extends CRUD {

}
