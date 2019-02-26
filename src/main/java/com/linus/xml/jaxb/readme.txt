1.第一步，你需要binding schema，这一步需要JDK bin目录中的一个批处理程序xjc. XSD文件一般放在main/resource目录中，请到main/resource目录寻找。

xjc -p com.linus.xml.jaxb.test datatypes_global_v62.xsd -d ../../java

命令解释：在当前目录根据datatypes_global_v62.xsd生成类，类的包名使用com.linus.xml.text.jaxb.

