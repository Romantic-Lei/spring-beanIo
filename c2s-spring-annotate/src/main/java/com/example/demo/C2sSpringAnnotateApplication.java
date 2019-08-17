package com.example.demo;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.beanio.BeanReader;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.beanio.builder.StreamBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.common.ListSort;
import com.example.demo.common.TextPrintAnnotate;
import com.example.demo.common.TextReadAndCsvWrite;
import com.example.demo.entity.FileO1;
import com.example.demo.entity.FileO1CSV;

@SpringBootApplication
@ComponentScan
public class C2sSpringAnnotateApplication {

	@Autowired
    private StreamFactory factory;
	
	@Autowired
	TextPrintAnnotate textPrintAnnotate;
	
	
	private static Logger log= LoggerFactory.getLogger(C2sSpringAnnotateApplication.class);
	
	List<FileO1> list = new ArrayList<FileO1>();
	
	List<FileO1CSV> listCsv = new ArrayList<FileO1CSV>();
	
	FileO1 fileO1 = new FileO1();
	FileO1CSV fileCsv = new FileO1CSV();
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(C2sSpringAnnotateApplication.class, args);
		
//		TextReadAndCsvWrite tx = new TextReadAndCsvWrite();
//		tx.beanIoTextPrintAnnotate();
//		List beanIoTextPrintAnnotate = TextReadAndCsvWrite.beanIoTextPrintAnnotate("F:\\workspace\\eclipse\\annotate\\c2s-spring-annotate\\src\\main\\resources\\data\\O1");
//		TextReadAndCsvWrite.beanIoCsvWriteAnnotate(beanIoTextPrintAnnotate);
		
		TextReadAndCsvWrite tx = new TextReadAndCsvWrite();
		String path = "F:\\workspace\\eclipse\\annotate\\c2s-spring-annotate\\src\\main\\resources\\data\\O1";
		List next = tx.textPrintAnnotate(path, new FileO1(), FileO1.class);
		tx.csvWriteAnnotate(next, new FileO1(), new FileO1CSV(), FileO1CSV.class);
		
//		ctx.getBean(C2sSpringAnnotateApplication.class).beanIoTextPrintAnnotate();
//		ctx.getBean(C2sSpringAnnotateApplication.class).beanIoCsvWriteAnnotate();
		
//		TextPrintAnnotate t = new TextPrintAnnotate();
//		t.beanIoTextPrintAnnotate();nim
//		((TextPrintAnnotate) ctx.getBean("textPrintAnnotate")).beanIoTextPrintAnnotate();
	}
	
	// beanIo注解版配置，读取定长文件
    private void beanIoTextPrintAnnotate() {
        log.info("beanIoTextPrintAnnotate=====================================================");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("data/O1").getFile());
        
        // create a new StreamBuilder and define its layout
        StreamBuilder builder = new StreamBuilder("O1TextFileAnnotate")
                .format("fixedlength")
                .addRecord(FileO1.class);

        // pass the StreamBuilder to the factory
        factory.define(builder);

        BeanReader in = factory.createReader("O1TextFileAnnotate", file);
        Object record = null;
        while ((record = in.read()) != null) {
        	fileO1 = (FileO1) record;
        	
        	// FileO1CSV FileO1
        	
        	list.add(fileO1);
            log.info(fileO1.toString());
        }
        log.info("beanIoTextPrintAnnotateEND=====================================================");
        log.info("\n");
        
        for (int i = 0; i < list.size(); i++) {
        	System.out.println("排序前：" + list.get(i));
		}
        // 按照时间进行排序
        ListSort.ListSort(list);
        
        for (int i = 0; i < list.size(); i++) {
        	System.out.println("排序后：" + list.get(i));
		}
    }
    
    public void beanIoCsvWriteAnnotate() {
    	File file = new File("C:\\Users\\lyue2\\Desktop\\Hello.csv");
    	StreamBuilder builder = new StreamBuilder("O1CsvFileAnnotate")
              .format("csv")
              .addRecord(FileO1CSV.class);

      // pass the StreamBuilder to the factory
      factory.define(builder);
      BeanWriter createWriter = factory.createWriter("O1CsvFileAnnotate", file);
      for (int i = 0; i < list.size(); i++) {
		FileO1 fileO12 = list.get(i);
		BeanUtils.copyProperties(fileO12, fileCsv);
		
		System.out.println("CSV读取的结果：" + fileO1);
		System.out.println("CSV读取的结果：" + fileCsv);
		
		createWriter.write(fileCsv);
		createWriter.flush();
		
      }
    }
}
