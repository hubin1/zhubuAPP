package com.fp.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

import com.fp.util.Variables;

public class StartupPreparingListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("正在读取数据库配置");
		InputStream input = this.getClass().getResourceAsStream("/config.properties");
		Properties p = new Properties();
		if(input == null){
			throw new IllegalArgumentException("未能找到配置文件，请重试");
		}
		try {
			p.load(input);
		} catch (IOException e) {
			throw new IllegalArgumentException("配置文件读写异常");
		}finally{
			if(input != null){
				try {
					input.close();
				} catch (IOException e) {
					System.out.println("流关闭失败");
				}
			}
		}
		
		String url = p.getProperty("url");
		String driver = p.getProperty("driver");
		String username = p.getProperty("username");
		String password = p.getProperty("password");
		
		Variables.driver = driver;
		Variables.password = password;
		Variables.url = url;
		Variables.username = username;
		
		String ffmepg = p.getProperty("ffmpegpath");
		String suffix = p.getProperty("suffix");
		String time = p.getProperty("time");
		
		Variables.ffmepgpath = ffmepg;
		Variables.suffix = suffix;
		Variables.time = time;
		
		System.out.println("数据读写成功");
		
		
		
		InputStream path = this.getClass().getResourceAsStream("/log4j.properties");
		if(null == path){
			throw new IllegalArgumentException("未能找到日志启动所需要的配置文件");
		}
		
		System.out.println("正在启动日志系统");
		
		PropertyConfigurator.configure(path);
		
		System.out.println("日志系统启动完成");
		
	}

}
