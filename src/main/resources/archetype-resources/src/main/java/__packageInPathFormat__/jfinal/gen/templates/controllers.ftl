package ${globalPackage?lower_case}.web.${modelName?lower_case};


import ${globalPackage?lower_case}.web.util.DateUtil;
import ${globalPackage?lower_case}.jfinal.model.${modelName};

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ${modelName}Controller
 * 
 */
@Api(tags="${modelName}")
@RestController
public class ${modelName}Controller extends Controller {

	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(${modelName}Controller.class.getName());



	public void index() {
		render("/page/${modelName?lower_case}/${modelName?lower_case}List.html");
	}

	@ApiOperation("GITHUB项目列表")
	@RequestMapping(value="/${modelName?lower_case}/show",method= RequestMethod.GET)
	@ResponseBody
	public Object show(//@ApiParam("项目名称") @RequestParam(value="pjname",defaultValue="",required=false) String pjname,
		@ApiParam("第几页") @RequestParam(value="page",defaultValue="1",required=false) int page,
		@ApiParam("每页多少条") @RequestParam(value="limit",defaultValue="10",required=false) int limit){

		List<Object> params = new ArrayList<Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int pageNum=page;
		int pageSize=limit;
		StringBuffer sql_select = new StringBuffer();
		StringBuffer sql_exp = new StringBuffer();
		sql_select.append("select * ");
		sql_exp.append("from ${tableName} a where 1=1 ");
//		if(StringUtils.isNoneBlank(searchKey)){
//			sql_exp.append("and LANG=? ");
//			params.add(searchKey);
//		}
//		sql_exp.append("order by a.UPDATE desc");
		logger.error(sql_exp.toString());
		Page<${modelName}> pages= ${modelName}.dao.paginate(pageNum,pageSize,sql_select.toString(),sql_exp.toString(),params.toArray());
		resultMap.put("data", pages.getList());
		resultMap.put("count", pages.getTotalRow());
		resultMap.put("code", 0);
		resultMap.put("msg", "");
		return resultMap;
	}

	public void add() {

	}
	public void save() {

	}

	public void edit() {

	}

	public void update() {

	}

	public void delete() {

	}
}


