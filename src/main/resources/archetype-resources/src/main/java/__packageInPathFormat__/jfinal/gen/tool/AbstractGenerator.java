package ${package}.gen.tool;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.sidao.tool2.ConfigurationHelper;
import org.sidao.tool2.InterfaceGenerator;

import java.io.*;
import java.util.Map;

public abstract class AbstractGenerator implements InterfaceGenerator {

	public void generate(String templateFileName, Map data,
			String fileName) {
		try {
			String templateFileDir=templateFileName.substring(0, templateFileName.lastIndexOf("/"));
			String templateFile=templateFileName.substring(templateFileName.lastIndexOf("/"), templateFileName.length());
			  // Get the templat object
			String genFileDir=fileName.substring(0, fileName.lastIndexOf("/"));
	        Template template = ConfigurationHelper.getConfiguration(templateFileDir).getTemplate(templateFile);
	        
	        org.apache.commons.io.FileUtils.forceMkdir(new File(genFileDir));
	        File output = new File(fileName);
	        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output),"UTF-8"));
	        template.process(data, writer);
	        writer.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String package2path(String packageName) {
		return packageName.replace('.', '/');
	}
	
	protected String  getFileName(String filePath) {
		String fileName=StringUtils.substringAfterLast(filePath, "/");
		 if(fileName.equals("")||fileName==null){
			 fileName=StringUtils.substringAfterLast(filePath, "\\");
		 }
		 return fileName;
	}

	protected String capFirst(String string) {
		String s = String.valueOf(string.charAt(0)).toUpperCase();
		s = s + string.substring(1);
		return s;
	}

	protected String uncapFirst(String string) {
		String s = String.valueOf(string.charAt(0)).toLowerCase();
		s = s + string.substring(1);
		return s;
	}
}
