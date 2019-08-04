
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.*;
class station {
	String name;
	station prev;
	station next;
	Map<station,LinkedHashSet<station>> ordersetmap=new HashMap<station,LinkedHashSet<station>>();
	station (String name){
		this.name=name;
	}
	public LinkedHashSet<station> passedStations(station sta){
		if(ordersetmap.get(sta)==null) {
			LinkedHashSet<station> set=new LinkedHashSet<station>();
			set.add(this);
			ordersetmap.put(sta, set);
		}
		return ordersetmap.get(sta);
	}
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		} else {
			if(obj instanceof station){
			station s = (station) obj;
			if(s.name.equals(this.name)){
				return true;
			} else 
				return false;
			
		} else 
			return false;
		}
	}
	public int hashCode() {
		return this.name.hashCode();
	}
}
class path{
	 List<station> outList = new ArrayList<station>();//��¼�Ѿ���������վ��
	
	//�����s1վ��s2վ����̾���·��
	public LinkedHashSet<station> calculate(station s1,station s2){
		if(outList.size() == subpath1.totalsat){
			return s1.passedStations(s2);
			/*System.out.println(s1.passedStations(s2).size()-1);
			/*
			 for(int i=0;i<subpath1.l2.size();i++) {
 	        	if(i<(subpath1.l2.size()-1)) {
 	        		
 	        		System.out.print(subpath1.l2.get(i).next.name);
 	        	}
 	        	
 	        }*/
			
			/*for(station station : s1.passedStations(s2)){
				System.out.print("�����Ƶ�վ"+station.name);
				if(s1.passedStations(s2).contains(station.prev)&&s1.passedStations(s2).contains(station.next)) {
					System.out.println("�м��վ"+station.name+" ");
					for(List<station> line: subpath1.lineset) {
						if(line.contains(station.next)&&(!line.contains(station.prev))) {
							System.out.println("����"+station.next.name+station.prev.name);
							/*System.out.println(station.name);
							int i=0;
							for(List<station> line1: subpath1.lineset) {
								if(line1.contains(station)&&line1.contains(station.next)) {
									switch(i) {
									case 0:{
										System.out.println("1");
										break;
									}
									case 1:{
										System.out.println("2");
										break;
									}
									case 2:{
										System.out.println("3");
										break;
									}
									case 3:{
										System.out.println("10");
										break;
									}
									case 4:{
										System.out.println("s1");
										break;
									}
									case 5:{
										System.out.println("s8");
										break;
									}
									case 6:{
										System.out.println("4");
										break;
									}
									case 7:{
										System.out.println("s3");
										break;
									}
									case 8:{
										System.out.println("s9");
										break;
									}
									case 9:{
										System.out.println("s7");
										break;
									}
									default:{
										System.out.println("δ�ҵ�");
										break;
									}
									}
									break;
								}
								i++;
							}*/
					//	}
				//	}
				//}
			//	System.out.println(station.name);
				//System.out.println(station.next.name);
		//	}
		//	return;*/
		}
		if(!outList.contains(s1)){
			outList.add(s1);
		}
		//������վ��OrderSetMapΪ�գ����һ�������վ��ǰ��վ���ʼ��֮
		if(s1.ordersetmap.isEmpty()){
			List<station> Linkedstations = getAllLinkedStations(s1);
			for(station s : Linkedstations){
				s1.passedStations(s).add(s);
			}
		}
		station parent = getShortestPath(s1);//��ȡ�������վs1�����һ��վ���ж���Ļ�������ȡһ����
		if(parent == s2){
			/*System.out.println(s1.passedStations(s2).size()-1);
			for(station station : s1.passedStations(s2)){
				System.out.println(station.name);
			}*/
			return s1.passedStations(s2);
		}
		for(station child : getAllLinkedStations(parent)){
			if(outList.contains(child)){
				continue;
			}
			int shortestPath = (s1.passedStations(parent).size()-1) + 1;//ǰ�����1��ʾ����·����Ҫȥ������վ�㣬�������1��ʾ������1վ����
			if(s1.passedStations(child).contains(child)){
				//���s1�Ѿ����������child�ľ������룬��ô�Ƚϳ���С�ľ���
				if((s1.passedStations(child).size()-1) > shortestPath){
					//����S1����Χ��վ����С·��
					s1.passedStations(child).clear();
					s1.passedStations(child).addAll(s1.passedStations(parent));
					s1.passedStations(child).add(child);
				}
			} else {
				//���s1��û�м��������child�ľ�������
				s1.passedStations(child).addAll(s1.passedStations(parent));
				s1.passedStations(child).add(child);
			}
		}
		outList.add(parent);
		return calculate(s1,s2);
	}
	
	//ȡ����station������վ����̾��룬���1վ������Ϊ1����������
	private station getShortestPath(station station){
		int minPatn = Integer.MAX_VALUE;
		station rets = null;
		for(station s :station.ordersetmap.keySet()){
			if(outList.contains(s)){
				continue;
			}
			LinkedHashSet<station> set  = station.passedStations(s);//����station��s������������վ��ļ���
			if(set.size() < minPatn){
				minPatn = set.size();
				rets = s;
			}
		}
		return rets;
	}
	
	//��ȡ����stationֱ������������վ�����������������վ
	private List<station> getAllLinkedStations(station station){
		List<station> linkedStaions = new ArrayList<station>();
		for(List<station> line : subpath1.lineset){
			if(line.contains(station)){//���ĳһ���߰����˴�վ��ע��������д��hashcode������ֻ��name��ͬ������Ϊ��ͬһ������
				station s = line.get(line.indexOf(station));
				if(s.prev != null){
					linkedStaions.add(s.prev);
				}
				if(s.next != null){
					linkedStaions.add(s.next);
				}
			}
		}
		return linkedStaions;
	}
	String lineno(station s1,station s2) {
		if(subpath1.l1.contains(s1)&&subpath1.l1.contains(s2))
			return "1";
		if(subpath1.l2.contains(s1)&&subpath1.l2.contains(s2))
			return "2";
		if(subpath1.l3.contains(s1)&&subpath1.l3.contains(s2))
			return "3";
		if(subpath1.l10.contains(s1)&&subpath1.l10.contains(s2))
			return "10";
		if(subpath1.ls1.contains(s1)&&subpath1.ls1.contains(s2))
			return "s1";
		if(subpath1.ls8.contains(s1)&&subpath1.ls8.contains(s2))
			return "s8";
		if(subpath1.l4.contains(s1)&&subpath1.l4.contains(s2))
			return "4";
		if(subpath1.ls3.contains(s1)&&subpath1.ls3.contains(s2))
			return "s3";
		if(subpath1.ls9.contains(s1)&&subpath1.ls9.contains(s2))
			return "s9";
		if(subpath1.ls7.contains(s1)&&subpath1.ls7.contains(s2))
			return "s7";
		return null;
	}
 
}
public class subpath1 {
	public static List<station> l1=new ArrayList<station>();
	public static List<station> l2=new ArrayList<station>();
	public static List<station> l3=new ArrayList<station>();
	public static List<station> l10=new ArrayList<station>();
	public static List<station> ls1=new ArrayList<station>();
	public static List<station> ls8=new ArrayList<station>();
	public static List<station> l4=new ArrayList<station>();
	public static List<station> ls3=new ArrayList<station>();
	public static List<station> ls9=new ArrayList<station>();
	public static List<station> ls7=new ArrayList<station>();
    public static Set<List<station>> lineset=new HashSet<List<station>>();
    public static int totalsat=0;
    public static void main(String[] args)throws IOException {
    	for(int k=0;k<args.length;k++) {
    		if(args[k].equals("-map")) {
    			File filename=new File(args[k+1]);
    	    	InputStreamReader reader=new InputStreamReader(new FileInputStream(filename));
    	        BufferedReader br=new BufferedReader(reader);
    	        //1����
    	        br.readLine();
    	        String l1str=br.readLine();
    	        String[] l1arr=l1str.split("��");
    	        for(String s : l1arr){
    				if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    				l1.add(new station(s));
    			}
    	        for(int i=0;i<l1.size();i++) {
    	        	if(i<(l1.size()-1)) {
    	        		l1.get(i).next=l1.get(i+1);
    	        		l1.get(i+1).prev=l1.get(i);
    	        	}
    	        }
    	        //2����
    	        br.readLine();
    	        String l2str=br.readLine();
    	        String[] l2arr=l2str.split("��");
    	        for(String s: l2arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	l2.add(new station(s));
    	        }
    	        	
    	        for(int i=0;i<l2.size();i++) {
    	        	if(i<(l2.size()-1)) {
    	        		l2.get(i).next=l2.get(i+1);
    	        		l2.get(i+1).prev=l2.get(i);
    	        	}
    	        }
    	        //line3
    	        br.readLine();
    	        String l3str=br.readLine();
    	        String[] l3arr=l3str.split("��");
    	        for(String s: l3arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	l3.add(new station(s));
    	        }
    	        	
    	        for(int i=0;i<l3.size();i++) {
    	        	if(i<(l3.size()-1)) {
    	        		l3.get(i).next=l3.get(i+1);
    	        		l3.get(i+1).prev=l3.get(i);
    	        		
    	        	}
    	        }
    	        //line 10
    	        br.readLine();
    	        String l10str=br.readLine();
    	        String[] l10arr=l10str.split("��");
    	        for(String s: l10arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	l10.add(new station(s));
    	        }
    	        	
    	        for(int i=0;i<l10.size();i++) {
    	        	if(i<(l10.size()-1)) {
    	        		l10.get(i).next=l10.get(i+1);
    	        		l10.get(i+1).prev=l10.get(i);
    	        	}
    	        }
    	        //line s1
    	        br.readLine();
    	        String ls1str=br.readLine();
    	        String[] ls1arr=ls1str.split("��");
    	        for(String s: ls1arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	    ls1.add(new station(s));
    	        }
    	        	
    	        for(int i=0;i<ls1.size();i++) {
    	        	if(i<(ls1.size()-1)) {
    	        		ls1.get(i).next=ls1.get(i+1);
    	        		ls1.get(i+1).prev=ls1.get(i);
    	        	}
    	        }
    	        //line s8
    	        br.readLine();
    	        String ls8str=br.readLine();
    	        String[] ls8arr=ls8str.split("��");
    	        for(String s: ls8arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	ls8.add(new station(s));
    	        }
    	        	
    	        for(int i=0;i<ls8.size();i++) {
    	        	if(i<(ls8.size()-1)) {
    	        		ls8.get(i).next=ls8.get(i+1);
    	        		ls8.get(i+1).prev=ls8.get(i);
    	        	}
    	        }
    	        //line 4
    	        br.readLine();
    	        String l4str=br.readLine();
    	        String[] l4arr=l4str.split("��");
    	        for(String s: l4arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	l4.add(new station(s));
    	        }
    	        	
    	        for(int i=0;i<l4.size();i++) {
    	        	if(i<(l4.size()-1)) {
    	        		l4.get(i).next=l4.get(i+1);
    	        		l4.get(i+1).prev=l4.get(i);
    	        	}
    	        }
    	        //line s3
    	        br.readLine();
    	        String ls3str=br.readLine();
    	        String[] ls3arr=ls3str.split("��");
    	        for(String s: ls3arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	ls3.add(new station(s));
    	        }
    	        	
    	        for(int i=0;i<ls3.size();i++) {
    	        	if(i<(ls3.size()-1)) {
    	        		ls3.get(i).next=ls3.get(i+1);
    	        		ls3.get(i+1).prev=ls3.get(i);
    	        		//System.out.print(ls3.get(i).next.name);
    	        	}
    	        	
    	        }
    	        //line s9
    	        br.readLine();
    	        String ls9str=br.readLine();
    	        String[] ls9arr=ls9str.split("��");
    	        for(String s: ls9arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	ls9.add(new station(s));
    	        }
    	        	
    	        for(int i=0;i<ls9.size();i++) {
    	        	if(i<(ls9.size()-1)) {
    	        		ls9.get(i).next=ls9.get(i+1);
    	        		ls9.get(i+1).prev=ls9.get(i);
    	        	}
    	        }
    	        //line s7
    	        br.readLine();
    	        String ls7str=br.readLine();
    	        String[] ls7arr=ls7str.split("��");
    	        for(String s: ls7arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	ls7.add(new station(s));
    	        }
    	        	
    	        for(int i=0;i<ls7.size();i++) {
    	        	if(i<(ls7.size()-1)) {
    	        		ls7.get(i).next=ls7.get(i+1);
    	        		ls7.get(i+1).prev=ls7.get(i);
    	        	}
    	        }
    	        br.close();
    	        lineset.add(l1);
    	        lineset.add(l2);
    	        lineset.add(l3);
    	        lineset.add(l10);
    	        lineset.add(ls1);
    	        lineset.add(ls8);
    	        lineset.add(l4);
    	        lineset.add(ls3);
    	        lineset.add(ls9);
    	        lineset.add(ls7);
    	        totalsat=l1.size()+l2.size()+l3.size()+l10.size()+ls1.size()+ls8.size()+l4.size()+ls3.size()+ls9.size()+ls7.size();
    	   // System.out.println("��õ�ͼ�ɹ�");
    		}//��õ�ͼ
    	}//forѭ��
    	
    	//�ض���·��վ��
    	for(int i=0;i<args.length;i++) {
    		
    		//�����
    		if(args[i].equals("-a")) {
    			for(int w=0;w<args.length;w++) {
    				if(args[w].equals("-o")) {
	    				File writeName=new File(args[w+1]);
	    				writeName.createNewFile();
	    				FileWriter writer=new FileWriter(writeName);
	    				BufferedWriter out=new BufferedWriter(writer);
	    				if(args[i+1].equals("1����")) {
	    					out.write("1���ߣ�"+"\r\n");
	    					for(station node:l1) {
	    						out.write(node.name+"\r\n");
	    					}
	    				}
	    					
	    				if(args[i+1].equals("2����")) {
	    					out.write("2���ߣ�"+"\r\n");
	    					for(station node:l2) {
	    						out.write(node.name+"\r\n");
	    					}
	    				}
	    					
	    				if(args[i+1].equals("3����")) {

    						out.write("3���ߣ�"+"\r\n");
	    					for(station node:l3) {
	    						out.write(node.name+"\r\n");
	    					}
	    				}
	    					
	    				if(args[i+1].equals("10����")) {
    						out.write("10���ߣ�"+"\r\n");
	    					for(station node:l10) {
	    						out.write(node.name+"\r\n");
	    					}
	    				}
	    					
	    				if(args[i+1].equals("s1����")) {
    						out.write("s1���ߣ�"+"\r\n");
	    					for(station node:ls1) {
	    						out.write(node.name+"\r\n");
	    					}
	    				}
	    					
	    				if(args[i+1].equals("s8����")) {

    						out.write("s8���ߣ�"+"\r\n");
	    					for(station node:ls8) {
	    						out.write(node.name+"\r\n");
	    					}
	    				}
	    					
	    				if(args[i+1].equals("4����")) {

    						out.write("4���ߣ�"+"\r\n");
	    					for(station node:l4) {
	    						out.write(node.name+"\r\n");
	    					}
	    				}
	    					
	    				if(args[i+1].equals("s3����")) {
    						out.write("s3���ߣ�"+"\r\n");
	    					for(station node:ls3) {
	    						out.write(node.name+"\r\n");
	    					}
	    				}
	    					
	    					if(args[i+1].equals("s9����")) {

	    						out.write("s9���ߣ�"+"\r\n");
	    						for(station node:ls9) {
		    						out.write(node.name+"\r\n");
		    					}
	    					}
		    					
	    					if(args[i+1].equals("s7����")) {
	    						out.write("s7���ߣ�"+"\r\n");
	    						for(station node:ls7) {
		    						out.write(node.name+"\r\n");
		    					}
	    					}
		    					
	    				out.flush();
	    				out.close();
	    			}
    			}
    			
    		//	System.out.println("������·�ɹ�");
    		}
    		
    		//������
    		if(args[i].equals("-b")) {
    			path th=new path();
    			LinkedHashSet<station> l=th.calculate(new station(args[i+1]), new station(args[i+2]));
    			for(int w=0;w<args.length;w++) {
	    			if(args[w].equals("-o")) {
	    				File writeName=new File(args[w+1]);
	    				writeName.createNewFile();
	    				FileWriter writer=new FileWriter(writeName);
	    				BufferedWriter out=new BufferedWriter(writer);
	    				out.write(String.valueOf(l.size())+"\r\n");
	    				station[] ss=new station[l.size()];
	        			int k=0;
	        			for(station point:l) 
	        				ss[k++]=point;
	        			out.write(ss[0].name+"\r\n");
	        			for(int i1=1;i1<k-1;i1++) {
	        				out.write(ss[i1].name+"\r\n");
	        				if(!th.lineno(ss[i1], ss[i1+1]).equals(th.lineno(ss[i1-1], ss[i1]))) {
	        					out.write(th.lineno(ss[i1], ss[i1+1])+"\r\n");
	        				}
	        			}
	        			out.write(ss[k-1].name);
	    				out.flush();
	    				out.close();
	    			}
	    		}
    			
    		//	System.out.println("����·���ɹ�");
    		}
    		
    		
    		
    	}
    }
}
