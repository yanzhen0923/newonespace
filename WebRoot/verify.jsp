<%@ page language="java" contentType="image/jpeg" import="java.awt.*, 
java.awt.image.*,java.util.*,javax.imageio.*"  pageEncoding="utf-8"%>
	<%! 
	Color getRandColor(int fc,int bc) 
	{ 
		Random random = new Random(); 
		if(fc>255) fc=255; 
		if(bc>255) bc=255; 
		int r=fc+random.nextInt(bc-fc); 
		int g=fc+random.nextInt(bc-fc); 
		int b=fc+random.nextInt(bc-fc); 
		return new Color(r,g,b); 
	} 
	%> 
<% 
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
	int width=60, height=20; 
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
	Graphics g = image.getGraphics(); 
	Random random = new Random();
	
	//随机背景颜色 
	g.setColor(getRandColor(200,255)); 
	g.fillRect(0, 0, width, height); 
	
	//字体
	g.setFont(new Font("Times New Roman",Font.PLAIN,18)); 
	
	//线条颜色
	g.setColor(getRandColor(170,190));
	
	//画用于干扰的线条
	for (int i=0;i<80;i++) 
	{ 
		int x = random.nextInt(width); 
		int y = random.nextInt(height); 
		int xl = random.nextInt(12); 
		int yl = random.nextInt(12); 
		g.drawLine(x,y,x+xl,y+yl); 
	} 
	String sRand=""; 
	for (int i=0;i<4;i++){
		
		//选择并产生随机字符
		int select = random.nextInt(2);
		String rand = null;
		
		if(select == 1){
		
			//数字
			rand=String.valueOf(random.nextInt(10));
		}
		else{
		
			//字母
			char c = (char)(random.nextInt(26) + 97);
			rand = String.valueOf(c);
		}
		
		sRand+=rand;
		
		//设置随机颜色
		g.setColor(new Color(random.nextInt(130),random.nextInt(130),random.nextInt(130)));
		
		//绘制图片
		g.drawString(rand,14*i+random.nextInt(8) + 2,random.nextInt(6) + 13);
	} 
	// 将验证码存入SESSION 
	session.setAttribute("rand",sRand); 
	g.dispose(); 
	ImageIO.write(image, "JPEG", response.getOutputStream()); 
%>
