# CodeGenerator
ssm框架的代码生成器，根据数据库表生成model、dao、mapper.xml、service以及controller。代码模板采用freemarker，可以在一定范围内修改模板。

## 更新
1. 将旧版本代码转换为Maven项目，方便打包为jar包
2. 调整了模板和配置文件的位置，方便打包后进行配置文件和模板文件的修改
3. 修改全局的换行符和编码格式

## 如何使用
1. `git clone ` 仓库
2. 使用`JDK1.8`及以上
3. 导入`IDEA`
4. 修改`./config.properties` ，比如修改jdbc相关配置<br/>
    ```java
    url=your url
    username=your name
    password=your psw
    ```
5. 运行`cn.kurisu9.codeGenerator.main.Main`

## 一些说明
### 1.自动生成代码
如果设置某一层不创建，但是其他又需要引用到该层，则会采用自动生成的全类名，那么可能生成的代码有错。
### 2.扩展代码生成器
继承`AbstractCodeGenerator`，同时可以考虑将相关配置写入`resources/config.properties` ，并在`CodeGeneratorConfig`中添加扩展以后的配置。最后在`cn.kurisu9.codeGenerator.main.Main`中添加到`ICodeGeneratorService`中，如
```java
private static void addGenerators(ICodeGeneratorService codeGeneratorService) {
      codeGeneratorService.addGenerator(new ModelGenerator());
      codeGeneratorService.addGenerator(new DtoGenerator());
      codeGeneratorService.addGenerator(new MapperGenerator());
      codeGeneratorService.addGenerator(new MapperXmlGenerator());
      codeGeneratorService.addGenerator(new DefaultClassesGenerator());
      codeGeneratorService.addGenerator(new IServiceGenerator());
      codeGeneratorService.addGenerator(new ServiceImplGenerator());
      codeGeneratorService.addGenerator(new ControllerGenerator());

      // 添加你自己扩展的生成器
      codeGeneratorService.addGenerator(new YourGenerator());
}
```
### 3. 打包
这里是通过Maven来进行打包的，运行的时候依赖jar包所在根目录的```config.properties```和```templates```
## 可能会继续实现
1. 打包为jar（已完成）
2. 增加日志输出（已完成）
3. 兼容Oracle、SQL Server
