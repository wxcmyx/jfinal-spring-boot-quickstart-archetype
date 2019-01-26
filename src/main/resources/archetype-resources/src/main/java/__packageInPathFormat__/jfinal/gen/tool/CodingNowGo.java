/**
 * 
 */
package ${package}.jfinal.gen.tool;

import com.jfinal.plugin.activerecord.generator.TableMeta;
import com.wixct.blogapi.jfinal.gen._JFinalQSGenerator;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xcwang
 *
 */
public class CodingNowGo {
	/**
	 * @param tm
	 * @throws ConfigurationException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void build(TableMeta tm) throws ConfigurationException, SQLException, ClassNotFoundException, IOException {
		//读取配置信息
//		Configuration codingConfig = new PropertiesConfiguration(TOOL_PATH+"coding.properties");
//		System.out.println("ssssssss");
		String modelName=tm.modelName;
		String tableName=tm.name;
		int html=1;
		String modelname= StringUtils.lowerCase(modelName);;
		Map data=new HashMap();
		data.put("tableName",tm.name);
		data.put("modelName",tm.modelName);
		data.put("globalPackage",_JFinalQSGenerator.globalPackage);
	
		Generator gtor=new Generator();
//		String modelFile= _JFinalQSGenerator.webPath+"/"+ modelname+"/" +modelName+".java";
		String controllersFile=_JFinalQSGenerator.webPath+"/"+ modelname+"/" +modelName+"Controller.java";
		
		//生产java文件
		if(!new File(controllersFile).exists())
		gtor.generateFile(_JFinalQSGenerator.TOOL_PATH+"/controllers.ftl", controllersFile, data);
		//设置配置文件
		File mainfile=new File(_JFinalQSGenerator.globalMappingKit);

		String addpath="arp.addMapping(\""+tableName+"\", "+modelName+".class);";
		String mainjava=FileUtils.readFileToString(mainfile);
		if(!mainjava.contains(addpath)){
			mainjava=mainjava.replaceFirst("@configmodel", "@configmodel\n"+addpath);
			FileUtils.writeStringToFile(mainfile, mainjava);
		}
	}
}
