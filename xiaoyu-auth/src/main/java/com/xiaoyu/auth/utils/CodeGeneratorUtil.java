//package com.xiaoyu.auth.utils;
//
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * <p>
// * MP 代码自动生成 (执行 main 方法控制台输入模块表名回车自动生成对应项目目录中)
// * </p>
// *
// * @author ZhangXianYu   Email: 1600501744@qq.com
// * @since 2020-07-13 11:03
// */
//public class CodeGeneratorUtil {
//
//
//    /**
//     * 使用注意事项
//     * <p>
//     * 需要填写作者，因为自动生成类头注释需要有类作者名称。
//     */
//    private static String author = "ZhangXianYu";
//
//
//    /**
//     * 读取控制台内容
//     */
//    private static String scanner() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入表名，多个英文逗号分割");
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (!StringUtils.isEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的表名！");
//    }
//
//    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/xiaoyu-auth/src/main/java");
//        //用户
//        gc.setAuthor(author);
//        if (StringUtils.isEmpty(author)) {
//            System.out.println("请输入作者名！");
//            return;
//        }
//        gc.setOpen(false);
//        // gc.setSwagger2(true); 实体属性 Swagger2 注解
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//
//        //JDBC四要素
//        dsc.setUrl("jdbc:mysql://47.113.97.132:3306/testquistart?serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("zxy123!@");
//        mpg.setDataSource(dsc);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setEntity("entity");
//        //控制器生成包名
//        pc.setController("controller");
//        //你哪个父目录下创建包
//        pc.setParent("com.xiaoyu.auth");
//        mpg.setPackageInfo(pc);
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//
//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/xiaoyu-auth/src/main/resources/mapper/" +
//                        "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//
//        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//
//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        //数据库表映射到实体的命名策略
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        //数据库表字段映射到实体类的命名策略
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        //自定义继承entity类，添加这一个会在生成实体类的时候继承entity
////        strategy.setSuperEntityClass("com.xiaoyu.common.entity.BaseEntity");
//
//        //实体是否为lombok模型
//        strategy.setEntityLombokModel(true);
//        //是否生成@RestController控制器
//        //strategy.setRestControllerStyle(true);
//
//        //是否生成实体时，生成字段注解
//        strategy.setEntityTableFieldAnnotationEnable(true);
//
//        //strategy.setSuperEntityColumns("id");
//        strategy.setInclude(scanner().split(","));
//        //驼峰转连字符串
//        strategy.setControllerMappingHyphenStyle(true);
//        //表前缀
//        strategy.setTablePrefix(pc.getModuleName() + "_");
//        //是否生成 serialVersionUID
//        strategy.setEntitySerialVersionUID(true);
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//    }
//}
