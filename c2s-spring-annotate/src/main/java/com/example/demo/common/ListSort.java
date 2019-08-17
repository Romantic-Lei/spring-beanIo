package com.example.demo.common;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;

public class ListSort {
	
	/**
	 * 声明需要比较的参数，如过需要比较的属性名不是这样写，我们需要自行改变
	 */
	@Getter
	@Setter
	private Date Collection_time;

	public static <T> void ListSort(List<T> list) {
		ListSort o1 = new ListSort();
		ListSort o2 = new ListSort();
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o3, T o4) {
            	// 此函数是将类对象o3的值赋值给类对象o1。注意只会赋值他们相同的属性名
            	BeanUtils.copyProperties(o3, o1);
            	BeanUtils.copyProperties(o4, o2);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                	Date dt1 = o1.getCollection_time();
                	Date dt2 = o2.getCollection_time();
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
