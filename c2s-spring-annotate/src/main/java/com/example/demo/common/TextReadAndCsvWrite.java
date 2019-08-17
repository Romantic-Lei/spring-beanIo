package com.example.demo.common;

import java.io.File;
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
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.C2sSpringAnnotateApplication;
import com.example.demo.entity.FileO1;
import com.example.demo.entity.FileO1CSV;

@ComponentScan
public class TextReadAndCsvWrite extends Generator<FileO1 , FileO1CSV> {

//	@Autowired
//	private StreamFactory factory;
	
	private static Logger log= LoggerFactory.getLogger(C2sSpringAnnotateApplication.class);
	
//	List<FileO1> list = new ArrayList<FileO1>();
	
	List<FileO1CSV> listCsv = new ArrayList<FileO1CSV>();
	
	public static List beanIoTextPrintAnnotate(String path) {
		FileO1 fileO1 = new FileO1();
		List<FileO1> list = new ArrayList<FileO1>();
		StreamFactory factory = StreamFactory.newInstance();
		log.info("beanIoTextPrintAnnotate=====================================================");
        File file = new File(path);
        
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
        
        // 、按照时间进行排序
        ListSort.ListSort(list);
        
        for (int i = 0; i < list.size(); i++) {
        	System.out.println("排序后：" + list.get(i));
		}
        
        return list;
	}
	
	public static void beanIoCsvWriteAnnotate(List list) {
		FileO1CSV fileCsv = new FileO1CSV();
    	File file = new File("C:\\Users\\asus\\Desktop\\Hello.csv");
    	StreamFactory factory = StreamFactory.newInstance();
    	StreamBuilder builder = new StreamBuilder("O1CsvFileAnnotate")
              .format("csv")
              .addRecord(FileO1CSV.class);

      // pass the StreamBuilder to the factory
      factory.define(builder);
      BeanWriter createWriter = factory.createWriter("O1CsvFileAnnotate", file);
      for (int i = 0; i < list.size(); i++) {
		FileO1 fileO12 = (FileO1) list.get(i);
		BeanUtils.copyProperties(fileO12, fileCsv);
		
//		System.out.println("CSV读取的结果：" + fileO1);
		System.out.println("CSV读取的结果：" + fileCsv);
		
		createWriter.write(fileCsv);
		createWriter.flush();
		
      }
    }

}
