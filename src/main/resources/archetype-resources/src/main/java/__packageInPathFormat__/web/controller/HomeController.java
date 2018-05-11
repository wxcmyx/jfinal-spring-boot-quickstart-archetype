package ${package}.web.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

	private static final String HOME_VIEW_NAME = "index";

    @Autowired
    private JdbcTemplate db;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
		return HOME_VIEW_NAME;
	}

    @RequestMapping("/hello")
    @ResponseBody
    public String home() {
        String countstr=  db.queryForList("select count(*) from githubproject").toString();
        List<Record> countlist= Db.use("ds1").find("select count(*) from githubproject");
        return countstr+"!!"+countlist.toString();
    }

}
