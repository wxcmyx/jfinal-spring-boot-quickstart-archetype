package ${package}.jfinal.gen.tool;

import java.util.Map;


public class Generator extends com.wixct.blogapi.jfinal.gen.tool.AbstractGenerator {
	/**
	 * 根据模版文件生成文件
	 * @param templateFilePath 模版文件
	 * @param toFilePath 产生文件
	 * @param data 数据
	 */
	public void generateFile(String templateFilePath,String toFilePath,Map data){
	     super.generate(templateFilePath, data, toFilePath);
	}
}
