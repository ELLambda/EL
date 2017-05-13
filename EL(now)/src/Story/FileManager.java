package Story;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**  
* 类说明   输入所在的章节，将文件转换为台词及其执行顺序
*  
* @author Andy
* @version  
*/


public class FileManager
{
	private static final String CHAPTER1 = FileManager.class.getClassLoader().getResource("chapter1.txt").getPath();
	private static final String CHAPTER2 = FileManager.class.getClassLoader().getResource("chapter2.txt").getPath();
	private static final String CHAPTER3 = FileManager.class.getClassLoader().getResource("chapter3.txt").getPath();
	private static final String CHAPTER4 = FileManager.class.getClassLoader().getResource("chapter4.txt").getPath();
	private static final String CHAPTER5 = FileManager.class.getClassLoader().getResource("chapter5.txt").getPath();
	private static final String CHAPTER6 = FileManager.class.getClassLoader().getResource("chapter6.txt").getPath();
	private static final String CHAPTER7 = FileManager.class.getClassLoader().getResource("chapter7.txt").getPath();
	private static final String CHAPTER8 = FileManager.class.getClassLoader().getResource("chapter8.txt").getPath();
	private static final String CHAPTER9 = FileManager.class.getClassLoader().getResource("chapter9.txt").getPath();
	private static final String CHAPTER10 = FileManager.class.getClassLoader().getResource("chapter10.txt").getPath();
	private static final String CHAPTER11 = FileManager.class.getClassLoader().getResource("chapter11.txt").getPath();
	private static final String CHAPTER12 = FileManager.class.getClassLoader().getResource("chapter12.txt").getPath();
	private static final String ENDING1 = FileManager.class.getClassLoader().getResource("ending1.txt").getPath();
	private static final String ENDING2 = FileManager.class.getClassLoader().getResource("ending2.txt").getPath();
	private static final String ENDING3 = FileManager.class.getClassLoader().getResource("ending3.txt").getPath();
	
	private static final String[] chapters = {CHAPTER1,CHAPTER2,CHAPTER3,CHAPTER4,CHAPTER5,CHAPTER6,CHAPTER7,CHAPTER8,CHAPTER9,CHAPTER10,CHAPTER11,CHAPTER12,ENDING1,ENDING2,ENDING3};
	
	private static final String[] chapterNames = {"第一章 遇见未知","第二章 星座物语","第三章 遇见未知","第四章 奇怪的梦","第五章 神木之约","第六章 神魔之井","第七章 迷宫初识","第八章 生死之门","第九章 三世回眸",
			"第十章 神木之戒","第十一章 神木之忆","第十二章 木石前盟","大结局","大结局","大结局"};
	private static String chapterName;
	
	//存储当前应该画哪个人物,
	//更换背景图为setWin
	public ArrayList<ArrayList> type = new ArrayList<ArrayList>();
	
	//唐绍台词
	public ArrayList<String> ts = new ArrayList<String>();
	
	//璇玑台词
	public ArrayList<String> xj = new ArrayList<String>();
	
	//老人台词
	public ArrayList<String> lr = new ArrayList<String>();
	
	//旁白
	public ArrayList<String> narration = new ArrayList<String>();
	
	//选项
	public ArrayList<String[]> choices = new ArrayList<String[]>();
	
	//背景图
	public ArrayList<String> bg = new ArrayList<String>();
	//输入所在的章节，将文件转换为台词及其执行顺序
	//章节共 为12+3 = 15 节
	public FileManager(int index){
		
		this.chapterName = chapterNames[index];
		
		try
		{
			BufferedReader reader = new BufferedReader
					(new FileReader(new File(chapters[index - 1])));
			
			String line = null;
			
			while((line = reader.readLine())!= null){
				if(line.startsWith("/*ts")){
					type.add(ts);
					ts.add(line.replaceAll("/*ts",""));
				}
				else if(line.startsWith("/*xj")){
					type.add(xj);
					xj.add(line.replaceAll("/xj",""));
				}
				else if(line.startsWith("/*lr")){
					type.add(lr);
					xj.add(line.replaceAll("/*lr", ""));
				}
				else if(line.startsWith("//")){
					type.add(narration);
					narration.add(line.replaceAll("//", ""));
				}
				else if(line.startsWith("A.")){
					type.add(choices);
					String[] twochoices = line.split(" ");
					choices.add(twochoices);
				}
				else if(line.startsWith("/*bg")){
					type.add(bg);
				}
//				else 
//					type.add(5);
					
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
		 	
		
		
		
	}
	
}
