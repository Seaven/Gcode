package ${data.packageName};

<#list data.importers as importer>
import ${importer}
</#list>
/**
* ${data.className}
* Created by Gcode on ${createDate?date}.
*/
public class ${data.className} {
    <#list data.parameters as param>
    /**
    * ${param.name}
    */
    private ${param.type} ${param.name};
    </#list>

    <#list data.parameters as param>
    /**
    * get${param.upperName}
    */
    public ${param.type} get${param.upperName}() {
        return this.${param.name};
    }

    /**
    * set${param.upperName}
    */
    public void get${param.upperName}(${param.type} ${param.name}) {
        this.${param.name} = ${param.name};
    }
    </#list>

    @Override
    public String toString() {
        return ${data.toParamStr}
    }
}
