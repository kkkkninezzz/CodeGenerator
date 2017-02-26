package cn.makisekurisu.codeGenerator.service.database;

import cn.makisekurisu.codeGenerator.bean.ModelInfo;
import cn.makisekurisu.codeGenerator.config.DataBaseConfig;
import cn.makisekurisu.codeGenerator.config.CodeGeneratorConfig;

import java.util.List;

/**
 * Created by ym on 2017/2/19 0019.
 *
 * 数据库相关服务
 */
public interface IDataBaseService {
    // 以下为表描述拥有的列
    /**
     * 表类别（可为 null）
     * */
    public static final String TABLE_CAT = "TABLE_CAT";

    /**
     * 表模式（可为 null）
     * */
    public static final String TABLE_SCHEM = "TABLE_SCHEM";

    /**
     * 表名称
     * */
    public static final String TABLE_NAME = "TABLE_NAME";

    /**
     * 表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
     * */
    public static final String TABLE_TYPE = "TABLE_TYPE";

    /**
     * 表的解释性注释
     * */
    public static final String REMARKS = "REMARKS";

    /**
     * 类型的类别（可为 null）
     * */
    public static final String TYPE_CAT = "TYPE_CAT";

    /**
     * 类型模式（可为 null）
     * */
    public static final String TYPE_NAME = "TYPE_NAME";

    /**
     * 有类型表的指定 "identifier" 列的名称（可为 null）
     * */
    public static final String SELF_REFERENCING_COL_NAME = "SELF_REFERENCING_COL_NAME";

    /**
     * 指定在 SELF_REFERENCING_COL_NAME 中创建值的方式。这些值为 "SYSTEM"、"USER" 和 "DERIVED"。（可能为 null）
     * */
    public static final String REF_GENERATION = "REF_GENERATION";

    /**
     * 列名称
     * */
    public static final String COLUMN_NAME = "COLUMN_NAME";

    /**
     * 列大小
     * */
    public static final String COLUMN_SIZE = "COLUMN_SIZE";

    /**
     * 该列的默认值，当值在单引号内时应被解释为一个字符串（可为 null）
     * */
    public static final String COLUMN_DEF = "COLUMN_DEF";

    /**
     * ISO 规则用于确定列是否包括 null。
     * YES --- 如果参数可以包括 NULL
     * NO --- 如果参数不可以包括 NULL
     * 空字符串 --- 如果不知道参数是否可以包括 null
     * */
    public static final String IS_NULLABLE = "IS_NULLABLE";

    /**
     * 是否允许使用 NULL。
     * columnNoNulls - 可能不允许使用 NULL 值
     * columnNullable - 明确允许使用 NULL 值
     * columnNullableUnknown - 不知道是否可使用 null
     * */
    public static final String NULLABLE = "NULLABLE";

    /**
     * 小数部分的位数。对于 DECIMAL_DIGITS 不适用的数据类型，则返回 Null。
     * */
    public static final String DECIMAL_DIGITS = "DECIMAL_DIGITS";

    /**
     * 来自 java.sql.Types 的 SQL 类型
     * */
    public static final String DATA_TYPE = "DATA_TYPE";

    /**
     * 获取需要生成代码的实体
     * */
    List<ModelInfo> loadModelInfos(DataBaseConfig dataBaseConfig, CodeGeneratorConfig codeGeneratorConfig);
}
