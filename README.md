# spring-beanIo

BeanIo不仅可以读取定长文件，我们还可以读取CSV文件，同样的，我们可以写成文本文件和写CSV文件。
在使用BeanIo时，我们需要导入相应的jar包
      <dependency>
	        <groupId>org.beanio</groupId>
	        <artifactId>beanio</artifactId>
	        <version>2.1.0</version>
	    </dependency>
      
代码中，将需要转换的部分已经提取成共通的方法，基本不需要修改代码。
