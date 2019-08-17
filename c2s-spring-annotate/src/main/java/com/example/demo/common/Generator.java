package com.example.demo.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.beanio.BeanReader;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.beanio.builder.StreamBuilder;
import org.springframework.beans.BeanUtils;

import com.example.demo.entity.FileO1;

import lombok.extern.slf4j.Slf4j;

@Slf4j

/**
 * @param <T>  input 
 * @param <O>  output
 */
public class Generator<T, O> {
	
	// beanIo注解版配置，读取定长文件
	public List textPrintAnnotate(String path, T t, Class<T> clazz) {
//		FileO1 fileO1 = new FileO1();
		List<T> list = new ArrayList<T>();
		StreamFactory factory = StreamFactory.newInstance();
		log.info("beanIoTextPrintAnnotate=====================================================");
        File file = new File(path);
        
        // StreamBuilder() name值必须和createReader()第一个参数值相同
        // create a new StreamBuilder and define its layout
        StreamBuilder builder = new StreamBuilder("O1TextFileAnnotate")
        		// 读取定长文件
                .format("fixedlength")
                .addRecord(clazz);

        // pass the StreamBuilder to the factory
        factory.define(builder);

        BeanReader in = factory.createReader("O1TextFileAnnotate", file);
        Object record = null;
        while ((record = in.read()) != null) {
        	t = (T) record;
        	
        	// FileO1CSV FileO1
        	
        	list.add(t);
            log.info(t.toString());
        }
        log.info("beanIoTextPrintAnnotateEND=====================================================");
        log.info("\n");
        
        for (int i = 0; i < list.size(); i++) {
        	System.out.println("排序前：" + list.get(i));
		}
        
        // 、按照时间进行排序
        ListSort.ListSort(list);
        
        for (int i = 0; i < list.size(); i++) {
        	System.out.println("排序后：" + list.get(i));
		}
        
        return list;
	}
	
	// 写csv文件
	public void csvWriteAnnotate(List list, T t, O o, Class<O> clazz) {
//		FileO1CSV fileCsv = new FileO1CSV();
    	File file = new File("C:\\Users\\asus\\Desktop\\Hello.csv");
    	StreamFactory factory = StreamFactory.newInstance();
    	StreamBuilder builder = new StreamBuilder("O1CsvFileAnnotate")
              .format("csv")
              .addRecord(clazz);

      // pass the StreamBuilder to the factory
      factory.define(builder);
      BeanWriter createWriter = factory.createWriter("O1CsvFileAnnotate", file);
      for (int i = 0; i < list.size(); i++) {
		t = (T) list.get(i);
		BeanUtils.copyProperties(t, o);
		
//		System.out.println("CSV读取的结果：" + fileO1);
		System.out.println("CSV读取的结果：" + o);
		
		createWriter.write(o);
		createWriter.flush();
		
      }
    }
	
	public static <T> void ListSort(List<T> list, T t) {
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                	Date dt1 = ((FileO1) o1).getCollection_time();
                	Date dt2 = ((FileO1) o2).getCollection_time();
//                    Date dt1 = format.parse(o1.getCollection_time());
//                    Date dt2 = format.parse(o2.getCollection_time());
                    if (dt1.getTime() > dt2.getTime()) {
                        return 1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }
	
}
