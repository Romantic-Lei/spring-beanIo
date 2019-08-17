package com.example.demo.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import org.beanio.builder.StreamBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.FileO1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class TextPrintAnnotate {
	
	@Autowired
    private StreamFactory factory;
	
	List<FileO1> list = new ArrayList<FileO1>();
	
//	@Bean
	public void beanIoTextPrintAnnotate() {
        log.info("beanIoTextPrintAnnotate====================================++++++++++++++++");
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
        	FileO1 contact = (FileO1) record;
        	list.add(contact);
            log.info(contact.toString());
        }
        log.info("beanIoTextPrintAnnotateEND=================================++++++++++++++++");
        log.info("\n");
        
        // 、按照时间进行排序
        ListSort.ListSort(list);
        for (int i = 0; i < list.size(); i++) {
        	System.out.println(list.get(i));
		}
        
    }
}
