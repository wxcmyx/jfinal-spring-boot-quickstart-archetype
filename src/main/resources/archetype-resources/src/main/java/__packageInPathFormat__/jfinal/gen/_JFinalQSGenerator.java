package ${package}.jfinal.gen;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.TableMeta;
import com.wixct.blogapi.jfinal.gen.tool.CodingNowGo;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class _JFinalQSGenerator {

    public static String globalPackage="${package}";
    public static String globalPath= StringUtils.replace(globalPackage,".","/");
    public static String webPath=PathKit.getWebRootPath() + "/src/main/java/"+globalPath+"/web";
    public static String TOOL_PATH=PathKit.getWebRootPath() + "/src/main/java/"+globalPath+"/jfinal/gen/templates";
    public static String globalMappingKit=PathKit.getWebRootPath() + "/src/main/java/"+globalPath+"/jfinal/model/_MappingKit.java";


    private static DataSource getMasterDataSource() {
        Prop prop = PropKit.use("conf-generator.properties");
        String url = prop.get("spring.datasource.url");
        String username = prop.get("spring.datasource.username");
        String password = prop.get("spring.datasource.password");
        HikariCpPlugin druidPlugin = new HikariCpPlugin(url, username, password);
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

//    private static void configExcludingTable(Generator generator) {
//        generator.addExcludedTable("adv");
//    }

    public static void main(String[] args) {

        // base model 所使用的包名
        String baseModelPackageName = globalPackage+".jfinal.model.base";
        // base model 文件保存路径
        String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/"+globalPath+"/jfinal/model/base";

        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = globalPackage+".jfinal.model";



        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = baseModelOutputDir + "/..";

        // 创建生成器
        DataSource dataSource = getMasterDataSource();
        com.wixct.blogapi.jfinal.gen.MyGenerator generator = new com.wixct.blogapi.jfinal.gen.MyGenerator(dataSource, baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
        // 添加不需要生成的表名
//        configExcludingTable(generator);
        // 设置是否在 Model 中生成 dao 对象
        generator.setGenerateDaoInModel(true);
        // 设置是否生成字典文件
        generator.setGenerateDataDictionary(true);
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        generator.setRemovedTableNamePrefixes("t_");

        generator.addIncludedTable("t_contents");
        // 生成
        generator.generate();

//        System.out.println("----->ssssssss"+generator.geTableList().size());
        for (TableMeta tableMeta : generator.geTableList()) {

            try {
                CodingNowGo.build(tableMeta);
            } catch (ConfigurationException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
