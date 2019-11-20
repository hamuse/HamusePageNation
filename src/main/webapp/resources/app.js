"use strict"
var app = app || {}
app = (()=>{
	let context, js;
	let run =x=>{
		alert('run안'+x)
		$.getScript(x+'/resources/router.js',()=>{
			$.extend(new Session(x))
			init()
			onCreate()
		})
	}
	let init=()=>{
		context = $.ctx()
		js = $.js()
		alert('init안')
	}
	let onCreate=()=>{
		alert('onCreate안')
		setContentView()
	}
	let setContentView=()=>{
		$('<table><tr></tr></table>')
		.css({
			width:'80%',
			height:'800px',
			border :'1px solid pink',
			margin :'0 auto'
		})
		.appendTo('#divid')
		$('<td/>',{id:'left'})
		.css({
			width :'20%',
			height :'100%',
			border : '1px solid pink',
			'vertical-align': 'top'
		})
		.appendTo('tr')
		$('<td/>',{id:'right'})
		.css({
			width :'80%',
			height :'100%',
			border : '1px solid pink',
		})
		.appendTo('tr')
		$.each(['naver','cgv','bugs'],(i,j)=>{
			$('<div/>')
			.text(j)
			.css({
				width : '100%',
				height : '30%',
				border : '1px solid pink',
				'text-align':'center'
			})
			.appendTo('#left')
			.click(function(){
				switch($(this).text()){
				case 'naver':
					alert('naver')
					$.getJSON(context+'/craw/naver',d=>{
						$('#right').empty()
						$.each(d,(i,j)=>{
							$('<div/>')
							.html(j.origin+j.trans)
							.css({
								width:'40%',
								height:'40%',
								border:'3px solid pink',
								float : 'left',
								'text-align':'center'
							})
							.appendTo('#right')
						})
						
					})
					break
				case 'cgv':
					alert('cgv')
					$.getJSON(context+'/craw/cgv',d=>{
						$.each(d,(i,j)=>{
							$('<div><img style="width=200px"src="'+j.photo+'"/><br/>'+j.title+'<br/>'+j.percent+'<br/>'+j.info+'</div>')
							.css({
								border :'3px solid pink',
								float :'left',
								'text-align' : 'center'
							})
							.appendTo('#right')	
						})
				
					})
					break
				case 'bugs' :	
					alert('bugs')
					$.getJSON( context+ '/craw/bugs', d=>{
						$('#right').empty()
						let pager = d.pager
						let list = d.list
						// No, title, artist, thumbnail
						$('<table><tr id="head"></tr></table>')
						.css({
							width : '90%',
							height : '80px',
							border : '3px solid red'
						})
						.appendTo('#right')
						$.each(['No', '제목', '가수', '앨범'], (i, j)=>{
							$('<th/>')
							.html('<b>' + j + '</b>')
							.css({
								width : '25%',
								height : '100%',
								border : '3px solid red'
							})
							.appendTo('#head')
						})
						alert('벅스 사이즈 : ' + d)
					})
					break
				}
			})
		})
		
	}
	return{run}
})()