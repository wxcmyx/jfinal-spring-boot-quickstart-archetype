/**
 * 
 */
package com.wixct.pachong.common;



import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.hikaricp.HikariCpPlugin;
import com.wixct.pachong.jfinal.model._MappingKit;

/**
 * @author wxc
 *
 */
public class DbUtils {
	public static void initDb(){
		Prop prop = PropKit.use("db-conf-generator.properties");
		String url = prop.get("spring.datasource.url");
		String username = prop.get("spring.datasource.username");
		String password = prop.get("spring.datasource.password");
		HikariCpPlugin hikarPlugin = new HikariCpPlugin(url, username, password);
		hikarPlugin.start();
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(hikarPlugin);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		arp.setTransactionLevel(4);
		arp.setDialect(new MysqlDialect());
		arp.setShowSql(false);
		arp.start();
	}
}
