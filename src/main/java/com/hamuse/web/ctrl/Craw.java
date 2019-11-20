package com.hamuse.web.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hamuse.web.proxy.Box;
import com.hamuse.web.proxy.CrawlingProxy;
import com.hamuse.web.proxy.PageProxy;

@RestController
@RequestMapping("/craw")
public class Craw {
	@Autowired CrawlingProxy crp;
	@Autowired PageProxy ppxy;
	@Autowired Box<Object> box;
	
  @GetMapping("/naver")
  public  ArrayList<HashMap<String, String>> naverCraw() {
	  System.out.println("naver Craw");
	  return crp.engCrawling("https://endic.naver.com/?sLn=kr");
	 
  }
  @GetMapping("/cgv")
  public  ArrayList<HashMap<String, String>> cgvCraw() {
	  System.out.println("cgv Craw");
   return crp.cgvCrawl();
  }
  @GetMapping("/bugs")
  public Map<?,?> bugsCraw() {
	  System.out.println("bugs Craw");
	  ArrayList<HashMap<String, String>> list = crp.bugsCrawling();
	  /*int size = list.size();*/
	  ppxy.setRowCount(list.size());
	  ppxy.setPageSize(5);
	  ppxy.setBlockSize(5);
	  ppxy.setNowPage(0);
	  ppxy.paging();

	  ArrayList<HashMap<String, String>> temp = new ArrayList<>();
	  for(int i = 0; i <list.size();i++) {
		  if(i >= ppxy.getStartRow() && i <= ppxy.getEndRow()) {
			  temp.add(list.get(i));
		  }
		  if(i>ppxy.getEndRow()) {break;}
	  }
	  box.put("pager", ppxy);
	  box.put("list", temp );
	  return box.get();
  }
}
