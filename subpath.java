package homework;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
class Station {
		 String name; //地铁站名称，假设具备唯一性
		 Station prev; //本站在lineNo线上面的前一个站
		 Station next; //本站在lineNo线上面的后一个站
		//本站到某一个目标站(key)所经过的所有站集合(value)，保持前后顺序
		 Map<Station,LinkedHashSet<Station>> orderSetMap = new HashMap<Station,LinkedHashSet<Station>>();
		 Station (String name){
			this.name = name;
		}
		 LinkedHashSet<Station> getAllPassedStations(Station station) {
			if(orderSetMap.get(station) == null){
				LinkedHashSet<Station> set = new LinkedHashSet<Station>(); 
				set.add(this);
				orderSetMap.put(station, set);
			}
			return orderSetMap.get(station);
		}
		public boolean equals(Object obj) {
			if(this == obj){
				return true;
			} else if(obj instanceof Station){
				Station s = (Station) obj;
				if(s.name.equals(this.name)){
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		public int hashCode() {
			return this.name.hashCode();
		}
	}

public  class subpath {
	public static List<Station> l1=new ArrayList<Station>();
	public static List<Station> l2=new ArrayList<Station>();
	public static List<Station> l3=new ArrayList<Station>();
	public static List<Station> l10=new ArrayList<Station>();
	public static List<Station> ls1=new ArrayList<Station>();
	public static List<Station> ls8=new ArrayList<Station>();
	public static List<Station> l4=new ArrayList<Station>();
	public static List<Station> ls3=new ArrayList<Station>();
	public static List<Station> ls9=new ArrayList<Station>();
	public static List<Station> ls7=new ArrayList<Station>();
    public static Set<List<Station>> lineset=new HashSet<List<Station>>();
    public static int totalstation=0;
    public static void main(String[] args) throws IOException {
    	for(int i=0;i<args.length;i++) {
         	if(args[i].equals("-map")) {
    			File filename=new File(args[i+1]);
    	    	InputStreamReader reader=new InputStreamReader(new FileInputStream(filename));
    	        BufferedReader br=new BufferedReader(reader);
    	        br.readLine();
    			//1号线
    			String l1str = br.readLine();
    			String[] l1arr = l1str.split("，");
    			for(String s : l1arr){
    				if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    				l1.add(new Station(s));
    			}
    			for(int j =0;j<l1.size();j++){
    				if(j<l1.size()-1){
    					l1.get(j).next = l1.get(j+1);
    					l1.get(j+1).prev = l1.get(j);
    				}
    			}
    			 //2号线
    	        br.readLine();
    	        String l2str=br.readLine();
    	        String[] l2arr=l2str.split("，");
    	        for(String s: l2arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	l2.add(new Station(s));
    	        }
    	        	
    	        for(int j=0;j<l2.size();j++) {
    	        	if(j<l2.size()-1) {
    	        		l2.get(j).next=l2.get(j+1);
    	        		l2.get(j+1).prev=l2.get(j);
    	        	}
    	        }
    	        //line3
    	        br.readLine();
    	        String l3str=br.readLine();
    	        String[] l3arr=l3str.split("，");
    	        for(String s: l3arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	l3.add(new Station(s));
    	        }
    	        	
    	        for(int j=0;j<l3.size();j++) {
    	        	if(j<l3.size()-1) {
    	        		l3.get(j).next=l3.get(j+1);
    	        		l3.get(j+1).prev=l3.get(j);
    	        	}
    	        }
    	        //line 10
    	        br.readLine();
    	        String l10str=br.readLine();
    	        String[] l10arr=l10str.split("，");
    	        for(String s: l10arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	l10.add(new Station(s));
    	        }
    	        	
    	        for(int j=0;j<l10.size();j++) {
    	        	if(j<l10.size()-1) {
    	        		l10.get(j).next=l10.get(j+1);
    	        		l10.get(j+1).prev=l10.get(j);
    	        	}
    	        }
    	        //line s1
    	        br.readLine();
    	        String ls1str=br.readLine();
    	        String[] ls1arr=ls1str.split("，");
    	        for(String s: ls1arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	    ls1.add(new Station(s));
    	        }
    	        	
    	        for(int j=0;j<ls1.size();j++) {
    	        	if(j<ls1.size()-1) {
    	        		ls1.get(j).next=ls1.get(j+1);
    	        		ls1.get(j+1).prev=ls1.get(j);
    	        	}
    	        }
    	        //line s8
    	        br.readLine();
    	        String ls8str=br.readLine();
    	        String[] ls8arr=ls8str.split("，");
    	        for(String s: ls8arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	ls8.add(new Station(s));
    	        }
    	        	
    	        for(int j=0;j<ls8.size();j++) {
    	        	if(j<ls8.size()-1) {
    	        		ls8.get(j).next=ls8.get(j+1);
    	        		ls8.get(j+1).prev=ls8.get(j);
    	        	}
    	        }
    	        //line 4
    	        br.readLine();
    	        String l4str=br.readLine();
    	        String[] l4arr=l4str.split("，");
    	        for(String s: l4arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	l4.add(new Station(s));
    	        }
    	        	
    	        for(int j=0;j<l4.size();j++) {
    	        	if(j<l4.size()-1) {
    	        		l4.get(j).next=l4.get(j+1);
    	        		l4.get(j+1).prev=l4.get(j);
    	        	}
    	        }
    	        //line s3
    	        br.readLine();
    	        String ls3str=br.readLine();
    	        String[] ls3arr=ls3str.split("，");
    	        for(String s: ls3arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	ls3.add(new Station(s));
    	        }
    	        	
    	        for(int j=0;j<ls3.size();j++) {
    	        	if(j<ls3.size()-1) {
    	        		ls3.get(j).next=ls3.get(j+1);
    	        		ls3.get(j+1).prev=ls3.get(j);
    	        	}
    	        }
    	        //line s9
    	        br.readLine();
    	        String ls9str=br.readLine();
    	        String[] ls9arr=ls9str.split("，");
    	        for(String s: ls9arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	ls9.add(new Station(s));
    	        }
    	        	
    	        for(int j=0;j<ls9.size();j++) {
    	        	if(j<ls9.size()-1) {
    	        		ls9.get(j).next=ls9.get(j+1);
    	        		ls9.get(j+1).prev=ls9.get(j);
    	        	}
    	        }
    	        //line s7
    	        br.readLine();
    	        String ls7str=br.readLine();
    	        String[] ls7arr=ls7str.split("，");
    	        for(String s: ls7arr) {
    	        	if(s.indexOf("#")!=-1) {
    					s=s.substring(0, s.indexOf("#"));
    				}
    	        	ls7.add(new Station(s));
    	        }
    	        	
    	        for(int j=0;j<ls7.size();j++) {
    	        	if(j<ls7.size()-1) {
    	        		ls7.get(j).next=ls7.get(j+1);
    	        		ls7.get(j+1).prev=ls7.get(j);
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
    	        totalstation=l1.size()+l2.size()+l3.size()+l10.size()+ls1.size()+ls8.size()+l4.size()+ls3.size()+ls9.size()+ls7.size();
    			System.out.println(totalstation);
    		//}
    	//}
    	
    }
}
    }
}
