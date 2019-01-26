package ${globalPackage?lower_case}.web.${modelName?lower_case};

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * ${modelName} model.
 */
@SuppressWarnings("serial")
public class ${modelName} extends Model<${modelName}> {
	public static final ${modelName} dao = new ${modelName}();
	
	/**
	 * 分页查询
	 */
	public Page<${modelName}> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from ${tableName} order by id desc");
	}
}
