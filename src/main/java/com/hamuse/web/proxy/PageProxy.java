package com.hamuse.web.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component("ppxy")
@Lazy
public class PageProxy extends Proxy{
	private int rowCount,startRow,endRow,
				pageSize, pageCount,startPage,endPage,nowPage,
				blockSize,blockCount,nowBlock,prevBlock,nextBlock;
	private boolean existPrev,existNext;
	private String serch;
	
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	public int getBlockCount() {
		return blockCount;
	}
	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}
	public int getNowBlock() {
		return nowBlock;
	}
	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}
	public int getPrevBlock() {
		return prevBlock;
	}
	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}
	public int getNextBlock() {
		return nextBlock;
	}
	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}
	public boolean isExistPrev() {
		return existPrev;
	}
	public void setExistPrev(boolean existPrev) {
		this.existPrev = existPrev;
	}
	public boolean isExistNext() {
		return existNext;
	}
	public void setExistNext(boolean existNext) {
		this.existNext = existNext;
	}
	public String getSerch() {
		return serch;
	}
	public void setSerch(String serch) {
		this.serch = serch;
	}
	
	public void paging() {
		//rowCount , pageSize, blockSize , nowPage
		//count는 total이라 생각해야한다.  
		//end 랑  count는 3항 걸림.
		pageCount =(rowCount % pageSize !=0)? rowCount /pageSize+1 : rowCount /pageSize; 
//		(rowCount % pageSize == 0) ? rowCount / pageSize :;
        blockCount = (pageCount % blockSize !=0)? pageCount /blockSize+1 : pageCount /blockSize; 
        startRow = nowPage * pageSize;
        endRow = (nowPage != (pageCount-1))?startRow + (pageSize -1) :rowCount-1 ;
        nowBlock = nowPage / blockSize;
        startPage = nowBlock * blockSize;
        endPage = (nowBlock != (blockCount-1))?startPage + (blockSize -1) :pageCount-1 ;
        prevBlock = startPage - blockSize;
        nextBlock = startPage + blockSize;
        existPrev = nowBlock != 0;
        existNext = nowBlock != blockCount-1;
		
	}
	
	
	
}
