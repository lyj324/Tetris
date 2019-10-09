package ai;

import java.awt.Point;
import java.util.List;

public class PierreDellacherie {
	/**
	 * ָ��ǰ������֮�󣬰�����ľ�����Ϸ����ײ��ľ��롣��Ҳ����С����ĺ��θ߶ȣ�
	 */
	private int landingHeight;
	/**
	 * �����������������֣���������������������뵱ǰ�ڷŵİ���б�������С����ĸ����ĳɼ��� 
	 */
	private int erodedPieceCellsMetric;
	/**
	 * ����ÿһ��С���񣬴������ҿ�������С������С������һ�֡��任����
	 * ����С������С����Ҳ��һ�֡��任������������Ǹ����С��任��֮��
	 */
	private int boardRowTransitions;
	/**
	 * ����ÿһ��С���񣬴������ҿ�������С������С������һ�֡��任����
	 * ����С������С����Ҳ��һ�֡��任������������Ǹ����С��任��֮��
	 */
	private int boardColTransitions;
	/**
	 * �����еġ��ն���С������֮�͡�
	 */
	private int boardBuriedHoles;
	/**
	 * �����С���������ȵ����Ӻ�
	 */
	private int boardWells;
	/**
	 * �����ϷĿǰ��ͼ,����ʾ
	 * @param maps
	 */
	public String getMap(boolean[][] maps,int type,boolean down,Point[] act) {
		return printNewMap(maps, type, getHighestFloor(maps),down,act);
	}
	public String printNewMap(boolean[][] maps,int type,int[] maxX,boolean down,Point[] act) {
		String action="";
		//I��
		if(type==0) {
			action=action(typeI(maps, maxX), type, down, act);
		}
		//T��
		if(type==1) {
			action=action(typeT(maps, maxX), type, down, act);
		}
		//L��
		if(type==2) {
			action=action(typeL(maps, maxX), type, down, act);
		}
		//S��
		if(type==3) {
			action=action(typeS(maps, maxX), type, down, act);
		}
		//O��
		if(type==4) {
			action=action(typeO(maps, maxX), type, down, act);
		}
		//J��
		if(type==5) {
			action=action(typeJ(maps, maxX), type, down, act);
		}
		//Z��
		if(type==6) {
			action=action(typeZ(maps, maxX), type, down, act);
		}
		return action;
	}
	public String action (int num,int type,boolean down,Point[] act)  {
		String action="";
		if(type==0) {
			if(act[0].y==act[1].y) {
						//��6��
					action+="4,4,4,4,4,4,";
					}
			else {
				if(down) {
							//������һ��ʹ֮����ת.
						action+="2,";
						}
						//��ת
						action+="8,";
						//��6��
						action+="4,4,4,4,4,4,";
					}
				//��i��
			if(num<7) {
				for(int i=0;i<num;i++) {
					action+="6,";
					
				}
			}
			else {
				//��ת����ֱ
				action+="8,";
				//����2��
				action+="4,4,";
				num-=7;
				for(int i=0;i<num;i++) {
					action+="6,";
					
				}
			}
		}
		if(type==1) {
			if(act[0].y==act[2].y) {
				if(act[0].y>act[3].y) {
					//��������
					//ֱ������8
					action+="4,4,4,4,4,4,4,4,";
				}
				else {
					//������
					if(down) {
						//������һ��ʹ֮����ת.
					action+="2,";
					}
					//��ת2
					action+="8,8,";
					//����8
					action+="4,4,4,4,4,4,4,4,";
				}
			}
			else {
				if(act[0].x<act[3].x) {
					//������
					if(down) {
						//������һ��ʹ֮����ת.
					action+="2,";
					}
					//��ת3
					action+="8,8,8,";
					//����8
					action+="4,4,4,4,4,4,4,4,";
				}
				else {
					//������
					if(down) {
						//������һ��ʹ֮����ת.
					action+="2,";
					}
					//��ת1
					action+="8,";
					//����8
					action+="4,4,4,4,4,4,4,4,";
				}
			}
			//��������
			if(num<8) {
				action+="8,8,";
				for(int i=0;i<num;i++) {
					action+="6,";
				}
			}
			else if(num<16) {
				for(int i=0;i<num-8;i++) {
					action+="6,";
				}
			}
			else if(num<25) {
				action+="8,8,8,";
				for(int i=0;i<num-16;i++) {
					action+="6,";
				}
			}
			else if(num<34) {
				action+="8,4,";{
					for(int i=0;i<num-25;i++) {
						action+="6,";
					}
				}
			}
		}
		if(type==2) {
			if(act[0].y==act[2].y) {
				if(act[0].y>act[3].y) {
					//��
					if(down) {
					//������һ��ʹ֮����ת.
					action+="2,";
					}
					//��ת2
					action+="8,8,";
					//����8
					action+="4,4,4,4,4,4,4,4,";
				}
				else {
					//��
					//����8
					action+="4,4,4,4,4,4,4,4,";
				}
			}
			else {
				if(act[0].x<act[3].x) {
					//��
					if(down) {
						//������һ��ʹ֮����ת.
						action+="2,";
						}
						//��ת1
						action+="8,";
						//����8
						action+="4,4,4,4,4,4,4,4,";
				}
				else {
					//��
					if(down) {
						//������һ��ʹ֮����ת.
						action+="2,";
						}
						//��ת2
						action+="8,8,8,";
						//����8
						action+="4,4,4,4,4,4,4,4,";
				}
			}
			//��������
			if(num<8) {
				for(int i=0;i<num;i++) {
					action+="6,";
				}
			}
			else if(num<16) {
				action+="8,8,";
				for(int i=0;i<num-8;i++) {
					action+="6,";
				}
			}
			else if(num<25) {
				action+="8,";
				for(int i=0;i<num-16;i++) {
					action+="6,";
				}
			}
			else if(num<34) {
				action+="8,8,8,4,";{
					for(int i=0;i<num-25;i++) {
						action+="6,";
					}
				}
			}
		}
		if(type==3) {
			if(act[0].y==act[2].y) {
				//����
				//����8
				action+="4,4,4,4,4,4,4,4,";
			}
			else {
				//����
				if(down) {
					//������һ��ʹ֮����ת.
					action+="2,";
					}
					//��ת2
					action+="8,";
					//����8
					action+="4,4,4,4,4,4,4,4,";
			}
			//��������
			if(num<8) {
				for(int i=0;i<num;i++) {
					action+="6,";
				}
			}
			else if(num<17) {
				action+="8,";
				for(int i=0;i<num-8;i++) {
					action+="6,";
				}
			}
		}
		if(type==4) {
			action+="4,4,4,4,4,4,4,4,4,";
			for(int i=0;i<num;i++) {
				action+="6,";
			}
		}
		if(type==5) {
			if(act[0].y==act[2].y) {
				if(act[0].y>act[3].y) {
					//��
					if(down) {
					//������һ��ʹ֮����ת.
					action+="2,";
					}
					//��ת2
					action+="8,8,";
					//����8
					action+="4,4,4,4,4,4,4,4,";
				}
				else {
					//��
					//����8
					action+="4,4,4,4,4,4,4,4,";
				}
			}
			else {
				if(act[0].x<act[3].x) {
					//��
					if(down) {
						//������һ��ʹ֮����ת.
						action+="2,";
						}
						//��ת1
						action+="8,";
						//����8
						action+="4,4,4,4,4,4,4,4,";
				}
				else {
					//��
					if(down) {
						//������һ��ʹ֮����ת.
						action+="2,";
						}
						//��ת2
						action+="8,8,8,";
						//����8
						action+="4,4,4,4,4,4,4,4,";
				}
			}
			//��������
			if(num<8) {
				for(int i=0;i<num;i++) {
					action+="6,";
				}
			}
			else if(num<16) {
				action+="8,8,";
				for(int i=0;i<num-8;i++) {
					action+="6,";
				}
			}
			else if(num<25) {
				action+="8,8,8,4,";
				for(int i=0;i<num-16;i++) {
					action+="6,";
				}
			}
			else if(num<34) {
				action+="8,";{
					for(int i=0;i<num-25;i++) {
						action+="6,";
					}
				}
			}
		}
		if(type==6) {
			if(act[0].y==act[2].y) {
				//����
				//����8
				action+="4,4,4,4,4,4,4,4,";
			}
			else {
				//����
				if(down) {
					//������һ��ʹ֮����ת.
					action+="2,";
					}
					//��ת2
					action+="8,";
					//����8
					action+="4,4,4,4,4,4,4,4,";
			}
			//��������
			if(num<8) {
				action+="8,";
				for(int i=0;i<num;i++) {
					action+="6,";
				}
			}
			else if(num<17) {
				for(int i=0;i<num-8;i++) {
					action+="6,";
				}
			}
		}
		return action;
	}
	/**
	 * ��ӡ��ͼ
	 * @param maps
	 */
	public void printMap(boolean[][] maps) {
		//ԭʼ��ͼ
		//�ȱ�����
		for(int y=0;y<maps[0].length;y++) {
			//�������
        	for(int x=0;x<maps.length;x++) {
        		if(maps[x][y]) {System.out.print("*");}
        		else {System.out.print("-");}
        	}
        	System.out.println();
        }
        System.out.println(".....��ͼ��ӡ���.......");
	}
	/**
	 * ����ԭʼ��ͼ��ߵ�,����ӡ
	 * @param maps
	 * @return ��߲����
	 */
	public int[] getHighestFloor(boolean[][] maps) {
		//ԭʼ��ͼ��ߵ�����
	    //�ȱ�����
	        int[] maxX=new int[10];
	        for(int x=0;x<maps.length;x++) {
	        	//�������
	        	for(int y=0;y<maps[0].length;y++) {
	        		if(maps[x][y]) {
	        			maxX[x]=Math.max(maxX[x], 18-y);
	        		}
	        	}
	        }
	        for(int i=0;i<maxX.length;i++) {
	        	System.out.print(maxX[i]+" ");
	        }
	        System.out.println();
	        System.out.println(".....ԭʼ��ͼ��ߵ��ӡ���......");
	        return maxX;
	}
	/**
	 * ������������
	 * @param maps
	 * @return
	 */
	public int remove(boolean [][]maps) {
		int r=0;
		for(int y=0;y<maps[0].length;y++) {
			if(maps[0][y]&&maps[1][y]&&maps[2][y]&&maps[3][y]&&maps[4][y]&&maps[5][y]
					&&maps[6][y]&&maps[7][y]&&maps[8][y]&&maps[9][y]) {
				r++;
			}
		}
		return r;
	}
	/**
	 * �����б任��Row Transitions��
	 * @param maps
	 * @return
	 */
	public int getBoardRowTransitions(boolean [][]maps) {
		String bool=null;
		boolean stat = false;
		int count =0;
		for(int y=0;y<maps[0].length;y++) {
			bool="";
			stat=false;
			for(int x=0;x<maps.length;x++) {
				if(maps[x][y]) {bool+="1";}
				else {bool+="0";}
				if(maps[x][y]==stat) {stat=!stat;count++;}
				if(x==maps.length-1&&!maps[x][y]) {count++;}
			}
			//System.out.println(bool+" ? "+count);
		}
		return count;
	}
	/**
	 * �����б仯��Column Transitions��
	 * @param maps
	 * @return
	 */
	public int getBoardColTransitions(boolean [][]maps) {
		String bool=null;
		boolean stat = false;
		int count =0;
		for(int x=0;x<maps.length;x++) {
			bool="";
			stat=false;
			for(int y=0;y<maps[0].length;y++) {
				if(maps[x][y]) {bool+="1";}
				else {bool+="0";}
				if(maps[x][y]==stat) {stat=!stat;count++;}
				if(y==maps[0].length-1&&!maps[x][y]) {count++;}
			}
			//System.out.println(bool+" ? "+count);
		}
		return count;
	}
	/**
	 * ��ÿն���
	 * @param maps
	 * @return
	 */
	public int getBoardBuriedHoles(boolean [][]maps) {
			int count=0;
			for(int y=0;y<maps[0].length;y++) {
				for(int x=0;x<maps.length;x++) {
					if(!maps[x][y]) {count++;}
				}
			}
			return count;
	}
	/**
	 * ��ȡ����
	 * @param maps
	 * @return
	 */
	public int getBoardWells(boolean [][]maps) {
		int count=0;
		String bool=null;
		for(int y=0;y<maps[0].length;y++) {
			bool="";
			for(int x=0;x<maps.length;x++) {
				if(maps[x][y]) {bool+="1";}
				else {bool+="0";}
				//�ȼ��������һ��,�Լ��ǿ��ĵ�,���ұ���ʵ��,����Ϊ��,�������
				if(x==0&&!maps[x][y]&&maps[x+1][y]) {
					if(y==17) {count++;}
					else {
					int k=1;
					count++;
					while(!maps[x][y+k]) {count++;k++;if(y+k==18)break;}
					}
				}
				//�ȼ������ұ�һ��,�Լ��ǿ��ĵ�,���ұ���ʵ��,����Ϊ��,�������
				else if(x==9&&!maps[x][y]&&maps[x-1][y]) {
					if(y==17) {count++;}
					else {
					int k=1;
					count++;
					while(!maps[x][y+k]) {count++;k++;if(y+k==18)break;}
					}
				}
				//�����㵱�е�
				else if(x!=9&&x!=0&&!maps[x][y]&&maps[x-1][y]&&maps[x+1][y]) {
					if(y==17) {count++;}
					else {
					int k=1;
					count++;
					while(!maps[x][y+k]) {count++;k++;if(y+k==18)break;}
					}
				}
			}
			//System.out.println(bool+"?"+count);
		}
		return count;
	}
	/**
	 * ����Ȩֵ
	 * @param landingHeight
	 * @param erodedPieceCellsMetric
	 * @param boardRowTransitions
	 * @param boardColTransitions
	 * @param boardBuriedHoles
	 * @param boardWells
	 * @return
	 */
	private int value(int landingHeight,int erodedPieceCellsMetric,int boardRowTransitions,int boardColTransitions,int boardBuriedHoles,int boardWells) {
		return -45*landingHeight+34*erodedPieceCellsMetric-32*boardRowTransitions-93*boardColTransitions-(79 * boardBuriedHoles)- 34 * boardWells;	
	}
	/**
	 * ��������λ��
	 * @param maps
	 * @param maxX
	 * @return
	 */
	private int typeI(boolean[][] maps,int[] maxX) {
		int num=0;
		int value=Integer.MIN_VALUE;
		int temp=Integer.MIN_VALUE;
		int layer=0;
		int []landingHeight=new int[17];
		int []erodedPieceCellsMetric=new int[17];
		int []boardRowTransitions=new int[17];
		int []boardColTransitions=new int[17];
		int []boardBuriedHoles=new int[17];
		int []boardWells=new int[17];
		boolean[][] newMap = new boolean[10][18];
		/**
		 * ���7�ֿ���
		 *    
		 *    ****
		 *    
		 */
			for(int i=0;i<7;i++) {
				layer=17-Math.max(Math.max(Math.max(maxX[i], maxX[i+1]),maxX[i+2]),maxX[i+3]);
				if(layer>=0&&layer<18) {
				for(int y=0;y<maps[0].length;y++) {
		        	for(int x=0;x<maps.length;x++) {
		        		newMap[x][y]=maps[x][y];
		        	}
		        }
				newMap[i][layer]=true;
				newMap[i+1][layer]=true;
				newMap[i+2][layer]=true;
				newMap[i+3][layer]=true;
				landingHeight[i]=17-layer+1;
				erodedPieceCellsMetric[i]=remove(newMap)*4;
				boardRowTransitions[i]=getBoardRowTransitions(newMap);
				boardColTransitions[i]=getBoardColTransitions(newMap);
				boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
				boardWells[i]=getBoardWells(newMap);
				temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				if(value<temp) {
				System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
				System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				printMap(newMap);
				num=i;
				value=temp;
				}
				}
			}
			/**
			 * ����10�ֿ���
			 * 
			 * 
			 * 
			 *    *
			 *    *
			 *    *
			 *    *
			 *    
			 */
			for(int i=7;i<17;i++) {
				layer=17-maxX[i-7];
				if(layer>3&&layer<18) {
				for(int y=0;y<maps[0].length;y++) {
		        	for(int x=0;x<maps.length;x++) {
		        		newMap[x][y]=maps[x][y];
		        	}
		        }
				newMap[i-7][layer]=true;
				newMap[i-7][layer-1]=true;
				newMap[i-7][layer-2]=true;
				newMap[i-7][layer-3]=true;
				landingHeight[i]=17-layer+3;
				erodedPieceCellsMetric[i]=remove(newMap);
				boardRowTransitions[i]=getBoardRowTransitions(newMap);
				boardColTransitions[i]=getBoardColTransitions(newMap);
				boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
				boardWells[i]=getBoardWells(newMap);
				temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				if(value<temp) {
				System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
				System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				printMap(newMap);
				num=i;
				value=temp;
				}
				}
			}
			////System.out.println(num);
		return num;
	}
	private int typeT(boolean[][] maps,int[] maxX) {
		int num=0;
		int value=Integer.MIN_VALUE;
		int temp=Integer.MIN_VALUE;
		int layer=0;
		int []landingHeight=new int[34];
		int []erodedPieceCellsMetric=new int[34];
		int []boardRowTransitions=new int[34];
		int []boardColTransitions=new int[34];
		int []boardBuriedHoles=new int[34];
		int []boardWells=new int[34];
		boolean[][] newMap = new boolean[10][18];
		/**
		 * Ĭ��T��8�ֿ���
		 * 
		 * 
		 *     ***
		 *      *
		 * 
		 */
		for(int i=0;i<8;i++) {
			layer=Math.max(Math.max(maxX[i], maxX[i+1]),maxX[i+2]);
			if(maxX[i+1]==Math.max(Math.max(maxX[i], maxX[i+1]),maxX[i+2])) {
				layer++;
			}
			layer=17-layer;
			if(layer>=0&&layer<16) {
			for(int y=0;y<maps[0].length;y++) {
	        	for(int x=0;x<maps.length;x++) {
	        		newMap[x][y]=maps[x][y];
	        	}
	        }	
			newMap[i][layer]=true;
			newMap[i+1][layer]=true;
			newMap[i+2][layer]=true;
			newMap[i+1][layer+1]=true;	
			landingHeight[i]=17-layer+1;
			if(remove(newMap)==0) {
				erodedPieceCellsMetric[i]=0;
			}
			else if(remove(newMap)==1) {
				erodedPieceCellsMetric[i]=1;
			}
			else if(remove(newMap)==2) {
				erodedPieceCellsMetric[i]=8;
			}
			boardRowTransitions[i]=getBoardRowTransitions(newMap);
			boardColTransitions[i]=getBoardColTransitions(newMap);
			boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
			boardWells[i]=getBoardWells(newMap);
			temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			if(value<temp) {
			System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
			System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			printMap(newMap);
			num=i;
			value=temp;
			}
			}
		}
		/**
		 * ��T��8��
		 * 
		 *     *
		 *    ***
		 */
		for(int i=8;i<16;i++) {
			layer=Math.max(Math.max(maxX[i-8], maxX[i+1-8]),maxX[i+2-8]);
			layer=17-layer;
			if(layer>=1&&layer<18) {
			for(int y=0;y<maps[0].length;y++) {
	        	for(int x=0;x<maps.length;x++) {
	        		newMap[x][y]=maps[x][y];
	        	}
	        }	
			newMap[i-8][layer]=true;
			newMap[i+1-8][layer]=true;
			newMap[i+2-8][layer]=true;
			newMap[i+1-8][layer-1]=true;	
			landingHeight[i]=17-layer+1;
			if(remove(newMap)==0) {
				erodedPieceCellsMetric[i]=0;
			}
			else if(remove(newMap)==1) {
				erodedPieceCellsMetric[i]=3;
			}
			else if(remove(newMap)==2) {
				erodedPieceCellsMetric[i]=8;
			}
			boardRowTransitions[i]=getBoardRowTransitions(newMap);
			boardColTransitions[i]=getBoardColTransitions(newMap);
			boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
			boardWells[i]=getBoardWells(newMap);
			temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			if(value<temp) {
			System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
			System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			printMap(newMap);
			num=i;
			value=temp;
			}
			}
		}
		/**
		 * ���� 9��
		 * 
		 *    *
		 *   **
		 *    *
		 *    
		 */
		for(int i=16;i<25;i++) {
		layer=Math.max(maxX[i-16], maxX[i+1-16]);
		if(maxX[i-16]<= maxX[i+1-16]){
			layer++;
		}
		layer=17-layer;
		if(layer>=1&&layer<17) {
		for(int y=0;y<maps[0].length;y++) {
        	for(int x=0;x<maps.length;x++) {
        		newMap[x][y]=maps[x][y];
        	}
        }	
		newMap[i-16][layer]=true;
		newMap[i+1-16][layer+1]=true;
		newMap[i+1-16][layer]=true;
		newMap[i+1-16][layer-1]=true;	
		landingHeight[i]=17-layer+1;
		if(remove(newMap)==0) {
			erodedPieceCellsMetric[i]=0;
		}
		else if(remove(newMap)==1) {
			erodedPieceCellsMetric[i]=1;
		}
		else if(remove(newMap)==2) {
			erodedPieceCellsMetric[i]=6;
		}
		else if(remove(newMap)==3) {
			erodedPieceCellsMetric[i]=12;
		}
		boardRowTransitions[i]=getBoardRowTransitions(newMap);
		boardColTransitions[i]=getBoardColTransitions(newMap);
		boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
		boardWells[i]=getBoardWells(newMap);
		temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
		if(value<temp) {
		System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
		System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
		printMap(newMap);
		num=i;
		value=temp;
		}
		}
	}
		/**
		 * ���� 9��
		 * 
		 *    *
		 *    **
		 *    *
		 *    
		 */
		for(int i=25;i<34;i++) {
		layer=Math.max(maxX[i-25], maxX[i+1-25]);
		if(maxX[i-25]>= maxX[i+1-25]){
			layer++;
		}
		layer=17-layer;
		if(layer>=1&&layer<17) {
		for(int y=0;y<maps[0].length;y++) {
        	for(int x=0;x<maps.length;x++) {
        		newMap[x][y]=maps[x][y];
        	}
        }	
		newMap[i-25][layer]=true;
		newMap[i-25][layer+1]=true;
		newMap[i+1-25][layer]=true;
		newMap[i-25][layer-1]=true;	
		landingHeight[i]=17-layer+1;
		if(remove(newMap)==0) {
			erodedPieceCellsMetric[i]=0;
		}
		else if(remove(newMap)==1) {
			erodedPieceCellsMetric[i]=1;
		}
		else if(remove(newMap)==2) {
			erodedPieceCellsMetric[i]=6;
		}
		else if(remove(newMap)==3) {
			erodedPieceCellsMetric[i]=12;
		}
		boardRowTransitions[i]=getBoardRowTransitions(newMap);
		boardColTransitions[i]=getBoardColTransitions(newMap);
		boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
		boardWells[i]=getBoardWells(newMap);
		temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
		if(value<temp) {
		System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
		System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
		printMap(newMap);
		num=i;
		value=temp;
		}
		}
	}
		////System.out.println(num);
		return num;
	}
	private int typeL(boolean[][] maps,int[] maxX) {
		int num=0;
		int value=Integer.MIN_VALUE;
		int temp=Integer.MIN_VALUE;
		int layer=0;
		int []landingHeight=new int[34];
		int []erodedPieceCellsMetric=new int[34];
		int []boardRowTransitions=new int[34];
		int []boardColTransitions=new int[34];
		int []boardBuriedHoles=new int[34];
		int []boardWells=new int[34];
		boolean[][] newMap = new boolean[10][18];
		
		/**
		 * Ĭ��L��8�ֿ���
		 * 
		 * 
		 *     ***
		 *     *
		 * 
		 */
		for(int i=0;i<8;i++) {
			layer=Math.max(Math.max(maxX[i], maxX[i+1]),maxX[i+2]);
			if(maxX[i]==Math.max(Math.max(maxX[i], maxX[i+1]),maxX[i+2])) {
				layer++;
			}
			layer=17-layer;
			if(layer>=0&&layer<17) {
			for(int y=0;y<maps[0].length;y++) {
	        	for(int x=0;x<maps.length;x++) {
	        		newMap[x][y]=maps[x][y];
	        	}
	        }	
			newMap[i][layer]=true;
			newMap[i+1][layer]=true;
			newMap[i+2][layer]=true;
			newMap[i][layer+1]=true;	
			landingHeight[i]=17-layer+1;
			if(remove(newMap)==0) {
				erodedPieceCellsMetric[i]=0;
			}
			else if(remove(newMap)==1) {
				erodedPieceCellsMetric[i]=1;
			}
			else if(remove(newMap)==2) {
				erodedPieceCellsMetric[i]=8;
			}
			boardRowTransitions[i]=getBoardRowTransitions(newMap);
			boardColTransitions[i]=getBoardColTransitions(newMap);
			boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
			boardWells[i]=getBoardWells(newMap);
			temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			if(value<temp) {
			System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
			System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			printMap(newMap);
			num=i;
			value=temp;
			}
			}
		}
		/**
		 * ��L��8��
		 * 
		 *      *
		 *    ***
		 */
		for(int i=8;i<16;i++) {
			layer=Math.max(Math.max(maxX[i-8], maxX[i+1-8]),maxX[i+2-8]);
			layer=17-layer;
			if(layer>=1&&layer<18) {
			for(int y=0;y<maps[0].length;y++) {
	        	for(int x=0;x<maps.length;x++) {
	        		newMap[x][y]=maps[x][y];
	        	}
	        }	
			newMap[i-8][layer]=true;
			newMap[i+1-8][layer]=true;
			newMap[i+2-8][layer]=true;
			newMap[i+2-8][layer-1]=true;	
			landingHeight[i]=17-layer+1;
			if(remove(newMap)==0) {
				erodedPieceCellsMetric[i]=0;
			}
			else if(remove(newMap)==1) {
				erodedPieceCellsMetric[i]=1;
			}
			else if(remove(newMap)==2) {
				erodedPieceCellsMetric[i]=8;
			}
			boardRowTransitions[i]=getBoardRowTransitions(newMap);
			boardColTransitions[i]=getBoardColTransitions(newMap);
			boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
			boardWells[i]=getBoardWells(newMap);
			temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			if(value<temp) {
			System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
			System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			printMap(newMap);
			num=i;
			value=temp;
			}
			}
		}
		/**
		 * ���� 9��
		 * 
		 *   **
		 *    *
		 *    *
		 *    
		 */
		for(int i=16;i<25;i++) {
		layer=Math.max(maxX[i-16], maxX[i+1-16]);
		if(maxX[i-16]-2<maxX[i+1-16]&&maxX[i-16]>maxX[i+1-16]) {
			layer++;
		}
		if(maxX[i-16]<=maxX[i+1-16]) {
			layer+=2;
		}
		layer=17-layer;
		if(layer>=0&&layer<16) {
		for(int y=0;y<maps[0].length;y++) {
        	for(int x=0;x<maps.length;x++) {
        		newMap[x][y]=maps[x][y];
        	}
        }	
		newMap[i-16][layer]=true;
		newMap[i+1-16][layer+1]=true;
		newMap[i+1-16][layer]=true;
		newMap[i+1-16][layer+2]=true;	
		landingHeight[i]=17-layer;
		if(remove(newMap)==0) {
			erodedPieceCellsMetric[i]=0;
		}
		else if(remove(newMap)==1) {
			erodedPieceCellsMetric[i]=1;
		}
		else if(remove(newMap)==2) {
			erodedPieceCellsMetric[i]=4;
		}
		else if(remove(newMap)==3) {
			erodedPieceCellsMetric[i]=12;
		}
		boardRowTransitions[i]=getBoardRowTransitions(newMap);
		boardColTransitions[i]=getBoardColTransitions(newMap);
		boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
		boardWells[i]=getBoardWells(newMap);
		temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
		if(value<temp) {
		System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
		System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
		printMap(newMap);
		num=i;
		value=temp;
		}
		}
	}
		/**
		 * ���� 9��
		 * 
		 *    *
		 *    *
		 *    **
		 *    
		 */
		for(int i=25;i<34;i++) {
		layer=Math.max(maxX[i-25], maxX[i+1-25]);
		layer=17-layer;
		if(layer>=2&&layer<18) {
		for(int y=0;y<maps[0].length;y++) {
        	for(int x=0;x<maps.length;x++) {
        		newMap[x][y]=maps[x][y];
        	}
        }	
		newMap[i-25][layer]=true;
		newMap[i-25][layer-1]=true;
		newMap[i+1-25][layer]=true;
		newMap[i-25][layer-2]=true;	
		landingHeight[i]=17-layer;
		if(remove(newMap)==0) {
			erodedPieceCellsMetric[i]=0;
		}
		else if(remove(newMap)==1) {
			erodedPieceCellsMetric[i]=2;
		}
		else if(remove(newMap)==2) {
			erodedPieceCellsMetric[i]=6;
		}
		else if(remove(newMap)==3) {
			erodedPieceCellsMetric[i]=12;
		}
		boardRowTransitions[i]=getBoardRowTransitions(newMap);
		boardColTransitions[i]=getBoardColTransitions(newMap);
		boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
		boardWells[i]=getBoardWells(newMap);
		temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
		if(value<temp) {
		System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
		System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
		printMap(newMap);
		num=i;
		value=temp;
		}
		}
	}	
		//System.out.println(num);
		return num;
	}
	private int typeS(boolean[][] maps, int[] maxX) {
		int num=0;
		int value=Integer.MIN_VALUE;
		int temp=Integer.MIN_VALUE;
		int layer=0;
		int []landingHeight=new int[17];
		int []erodedPieceCellsMetric=new int[17];
		int []boardRowTransitions=new int[17];
		int []boardColTransitions=new int[17];
		int []boardBuriedHoles=new int[17];
		int []boardWells=new int[17];
		boolean[][] newMap = new boolean[10][18];
		/**
		 * ���8�ֿ���
		 *     **
		 *    **
		 *    
		 */
			for(int i=0;i<8;i++) {
				layer=Math.max(Math.max(maxX[i], maxX[i+1]),maxX[i+2]);
				if(maxX[i+2]>maxX[i]&&maxX[i+2]>maxX[i+1]) {layer--;}
				layer=17-layer;
				if(layer>=1&&layer<18) {
				for(int y=0;y<maps[0].length;y++) {
		        	for(int x=0;x<maps.length;x++) {
		        		newMap[x][y]=maps[x][y];
		        	}
		        }
				newMap[i][layer]=true;
				newMap[i+1][layer]=true;
				newMap[i+1][layer-1]=true;
				newMap[i+2][layer-1]=true;
				landingHeight[i]=17-layer+1;
				if(remove(newMap)==0) {erodedPieceCellsMetric[i]=0;}
				else if(remove(newMap)==1) {erodedPieceCellsMetric[i]=2;}
				else if(remove(newMap)==2) {erodedPieceCellsMetric[i]=8;}
				boardRowTransitions[i]=getBoardRowTransitions(newMap);
				boardColTransitions[i]=getBoardColTransitions(newMap);
				boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
				boardWells[i]=getBoardWells(newMap);
				temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				if(value<temp) {
				System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
				System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				printMap(newMap);
				num=i;
				value=temp;
				}
			}}
			/**
			 * ����9�ֿ���
			 * 
			 * 
			 * 
			 *    *
			 *    **
			 *     *
			 *    
			 */
			for(int i=8;i<17;i++) {
				layer=Math.max(maxX[i-8], maxX[i+1-8]);
				if(maxX[i+1-8]==Math.max(maxX[i-8], maxX[i+1-8])) {layer++;}
				layer=17-layer;
				if(layer>0&&layer<17) {
					for(int y=0;y<maps[0].length;y++) {
		        	for(int x=0;x<maps.length;x++) {
		        		newMap[x][y]=maps[x][y];
		        	}
		        }
				newMap[i-8][layer]=true;
				newMap[i-8][layer-1]=true;
				newMap[i+1-8][layer]=true;
				newMap[i+1-8][layer+1]=true;
				landingHeight[i]=17-layer+1;
				if(remove(newMap)==0) {erodedPieceCellsMetric[i]=0;}
				else if(remove(newMap)==1) {erodedPieceCellsMetric[i]=1;}
				else if(remove(newMap)==2) {erodedPieceCellsMetric[i]=6;}
				else if(remove(newMap)==3) {erodedPieceCellsMetric[i]=12;}
				boardRowTransitions[i]=getBoardRowTransitions(newMap);
				boardColTransitions[i]=getBoardColTransitions(newMap);
				boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
				boardWells[i]=getBoardWells(newMap);
				temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				if(value<temp) {
				System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
				System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				printMap(newMap);
				num=i;
				value=temp;
				}
				}
			}
			//System.out.println(num);
		return num;
	}
	private int typeO(boolean[][] maps, int[] maxX) {
		int num=0;
		int value=Integer.MIN_VALUE;
		int temp=Integer.MIN_VALUE;
		int layer=0;
		int []landingHeight=new int[9];
		int []erodedPieceCellsMetric=new int[9];
		int []boardRowTransitions=new int[9];
		int []boardColTransitions=new int[9];
		int []boardBuriedHoles=new int[9];
		int []boardWells=new int[9];
		boolean[][] newMap = new boolean[10][18];
		/**
		 * 9�ֿ���
		 *    **
		 *    **
		 *    
		 */
			for(int i=0;i<9;i++) {
				layer=Math.max(maxX[i], maxX[i+1]);
				layer=17-layer;
				if(layer>=0&&layer<18) {
				for(int y=0;y<maps[0].length;y++) {
		        	for(int x=0;x<maps.length;x++) {
		        		newMap[x][y]=maps[x][y];
		        	}
		        }
				newMap[i][layer-1]=true;
				newMap[i][layer]=true;
				newMap[i+1][layer]=true;
				newMap[i+1][layer-1]=true;
				landingHeight[i]=17-layer+1;
				if(remove(newMap)==0) {erodedPieceCellsMetric[i]=0;}
				else if(remove(newMap)==1) {erodedPieceCellsMetric[i]=2;}
				else if(remove(newMap)==2) {erodedPieceCellsMetric[i]=4;}
				boardRowTransitions[i]=getBoardRowTransitions(newMap);
				boardColTransitions[i]=getBoardColTransitions(newMap);
				boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
				boardWells[i]=getBoardWells(newMap);
				temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				if(value<temp) {
				System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
				System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				printMap(newMap);
				num=i;
				value=temp;
				}
			}
				}
			//System.out.println(num);
		return num;
	}
	private int typeJ(boolean[][] maps,int[] maxX) {
		int num=0;
		int value=Integer.MIN_VALUE;
		int temp=Integer.MIN_VALUE;
		int layer=0;
		int []landingHeight=new int[34];
		int []erodedPieceCellsMetric=new int[34];
		int []boardRowTransitions=new int[34];
		int []boardColTransitions=new int[34];
		int []boardBuriedHoles=new int[34];
		int []boardWells=new int[34];
		boolean[][] newMap = new boolean[10][18];
		
		/**
		 * Ĭ��J��8�ֿ���
		 * 
		 * 
		 *     ***
		 *       *
		 * 
		 */
		for(int i=0;i<8;i++) {
			layer=Math.max(Math.max(maxX[i], maxX[i+1]),maxX[i+2]);
			if(maxX[i+2]==Math.max(Math.max(maxX[i], maxX[i+1]),maxX[i+2])) {
				layer++;
			}
			layer=17-layer;
			if(layer>=0&&layer<17) {
			for(int y=0;y<maps[0].length;y++) {
	        	for(int x=0;x<maps.length;x++) {
	        		newMap[x][y]=maps[x][y];
	        	}
	        }	
			newMap[i][layer]=true;
			newMap[i+1][layer]=true;
			newMap[i+2][layer]=true;
			newMap[i+2][layer+1]=true;	
			landingHeight[i]=17-layer+1;
			if(remove(newMap)==0) {
				erodedPieceCellsMetric[i]=0;
			}
			else if(remove(newMap)==1) {
				erodedPieceCellsMetric[i]=1;
			}
			else if(remove(newMap)==2) {
				erodedPieceCellsMetric[i]=8;
			}
			boardRowTransitions[i]=getBoardRowTransitions(newMap);
			boardColTransitions[i]=getBoardColTransitions(newMap);
			boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
			boardWells[i]=getBoardWells(newMap);
			temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			if(value<temp) {
			System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
			System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			printMap(newMap);
			num=i;
			value=temp;
			}
			}
		}
		/**
		 * ��J��8��
		 * 
		 *    *
		 *    ***
		 */
		for(int i=8;i<16;i++) {
			layer=Math.max(Math.max(maxX[i-8], maxX[i+1-8]),maxX[i+2-8]);
			layer=17-layer;
			if(layer>=1&&layer<18) {
			for(int y=0;y<maps[0].length;y++) {
	        	for(int x=0;x<maps.length;x++) {
	        		newMap[x][y]=maps[x][y];
	        	}
	        }	
			newMap[i-8][layer]=true;
			newMap[i+1-8][layer]=true;
			newMap[i+2-8][layer]=true;
			newMap[i-8][layer-1]=true;	
			landingHeight[i]=17-layer+1;
			if(remove(newMap)==0) {
				erodedPieceCellsMetric[i]=0;
			}
			else if(remove(newMap)==1) {
				erodedPieceCellsMetric[i]=1;
			}
			else if(remove(newMap)==2) {
				erodedPieceCellsMetric[i]=8;
			}
			boardRowTransitions[i]=getBoardRowTransitions(newMap);
			boardColTransitions[i]=getBoardColTransitions(newMap);
			boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
			boardWells[i]=getBoardWells(newMap);
			temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			if(value<temp) {
			System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
			System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			printMap(newMap);
			num=i;
			value=temp;
			}
			}
		}
		/**
		 * ���� 9��
		 * 
		 *    **
		 *    *
		 *    *
		 *    
		 */
		for(int i=16;i<25;i++) {
		layer=Math.max(maxX[i-16], maxX[i+1-16]);
		if(maxX[i+1-16]-2<maxX[i-16]&&maxX[i+1-16]>maxX[i-16]) {
			layer++;
		}
		if(maxX[i+1-16]<=maxX[i-16]) {
			layer+=2;
		}
		layer=17-layer;
		if(layer>=0&&layer<16) {
		for(int y=0;y<maps[0].length;y++) {
        	for(int x=0;x<maps.length;x++) {
        		newMap[x][y]=maps[x][y];
        	}
        }	
		newMap[i+1-16][layer]=true;
		newMap[i-16][layer+1]=true;
		newMap[i-16][layer]=true;
		newMap[i-16][layer+2]=true;	
		landingHeight[i]=17-layer;
		if(remove(newMap)==0) {
			erodedPieceCellsMetric[i]=0;
		}
		else if(remove(newMap)==1) {
			erodedPieceCellsMetric[i]=1;
		}
		else if(remove(newMap)==2) {
			erodedPieceCellsMetric[i]=4;
		}
		else if(remove(newMap)==3) {
			erodedPieceCellsMetric[i]=12;
		}
		boardRowTransitions[i]=getBoardRowTransitions(newMap);
		boardColTransitions[i]=getBoardColTransitions(newMap);
		boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
		boardWells[i]=getBoardWells(newMap);
		temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
		if(value<temp) {
		System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
		System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
		printMap(newMap);
		num=i;
		value=temp;
		}
		}
	}
		/**
		 * ���� 9��
		 * 
		 *    *
		 *    *
		 *   **
		 *    
		 */
		for(int i=25;i<34;i++) {
			layer=Math.max(maxX[i-25], maxX[i+1-25]);
			layer=17-layer;
			if(layer>=2&&layer<18) {
			for(int y=0;y<maps[0].length;y++) {
	        	for(int x=0;x<maps.length;x++) {
	        		newMap[x][y]=maps[x][y];
	        	}
	        }	
			newMap[i-25][layer]=true;
			newMap[i+1-25][layer-1]=true;
			newMap[i+1-25][layer]=true;
			newMap[i+1-25][layer-2]=true;	
			landingHeight[i]=17-layer;
			if(remove(newMap)==0) {
				erodedPieceCellsMetric[i]=0;
			}
			else if(remove(newMap)==1) {
				erodedPieceCellsMetric[i]=1;
			}
			else if(remove(newMap)==2) {
				erodedPieceCellsMetric[i]=6;
			}
			else if(remove(newMap)==3) {
				erodedPieceCellsMetric[i]=12;
			}
			boardRowTransitions[i]=getBoardRowTransitions(newMap);
			boardColTransitions[i]=getBoardColTransitions(newMap);
			boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
			boardWells[i]=getBoardWells(newMap);
			temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			if(value<temp) {
			System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
			System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
			printMap(newMap);
			num=i;
			value=temp;
			}
			}
		}	
		//System.out.println(num);
		return num;
	}
	private int typeZ(boolean[][] maps, int[] maxX) {
		int num=0;
		int value=Integer.MIN_VALUE;
		int temp=Integer.MIN_VALUE;
		int layer=0;
		int []landingHeight=new int[17];
		int []erodedPieceCellsMetric=new int[17];
		int []boardRowTransitions=new int[17];
		int []boardColTransitions=new int[17];
		int []boardBuriedHoles=new int[17];
		int []boardWells=new int[17];
		boolean[][] newMap = new boolean[10][18];
		/**
		 * ���8�ֿ���
		 *     **
		 *      **
		 *    
		 */
			for(int i=0;i<8;i++) {
				layer=Math.max(Math.max(maxX[i], maxX[i+1]),maxX[i+2]);
				if(maxX[i+2]<maxX[i]&&maxX[i]>maxX[i+1]) {layer--;}
				layer=17-layer;
				if(layer>=1&&layer<18) {
				for(int y=0;y<maps[0].length;y++) {
		        	for(int x=0;x<maps.length;x++) {
		        		newMap[x][y]=maps[x][y];
		        	}
		        }
				newMap[i][layer-1]=true;
				newMap[i+1][layer]=true;
				newMap[i+1][layer-1]=true;
				newMap[i+2][layer]=true;
				landingHeight[i]=17-layer+1;
				if(remove(newMap)==0) {erodedPieceCellsMetric[i]=0;}
				else if(remove(newMap)==1) {erodedPieceCellsMetric[i]=2;}
				else if(remove(newMap)==2) {erodedPieceCellsMetric[i]=8;}
				boardRowTransitions[i]=getBoardRowTransitions(newMap);
				boardColTransitions[i]=getBoardColTransitions(newMap);
				boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
				boardWells[i]=getBoardWells(newMap);
				temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				if(value<temp) {
				System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
				System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				printMap(newMap);
				num=i;
				value=temp;
				}
			}}
			/**
			 * ����9�ֿ���
			 * 
			 * 
			 * 
			 *    *
			 *    **
			 *     *
			 *    
			 */
			for(int i=8;i<17;i++) {
				layer=Math.max(maxX[i-8], maxX[i+1-8]);
				if(maxX[i-8]==Math.max(maxX[i-8], maxX[i+1-8])) {layer++;}
				layer=17-layer;
				if(layer>0&&layer<17) {
					for(int y=0;y<maps[0].length;y++) {
		        	for(int x=0;x<maps.length;x++) {
		        		newMap[x][y]=maps[x][y];
		        	}
		        }
				newMap[i-8][layer]=true;
				newMap[i-8][layer+1]=true;
				newMap[i+1-8][layer]=true;
				newMap[i+1-8][layer-1]=true;
				landingHeight[i]=17-layer+1;
				if(remove(newMap)==0) {erodedPieceCellsMetric[i]=0;}
				else if(remove(newMap)==1) {erodedPieceCellsMetric[i]=1;}
				else if(remove(newMap)==2) {erodedPieceCellsMetric[i]=6;}
				else if(remove(newMap)==3) {erodedPieceCellsMetric[i]=12;}
				boardRowTransitions[i]=getBoardRowTransitions(newMap);
				boardColTransitions[i]=getBoardColTransitions(newMap);
				boardBuriedHoles[i]=getBoardBuriedHoles(newMap);
				boardWells[i]=getBoardWells(newMap);
				temp=Math.max(temp, value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				if(value<temp) {
				System.out.println("���ľ���"+landingHeight[i]+"��������"+erodedPieceCellsMetric[i]+"�б任"+boardRowTransitions[i]+"�б仯"+boardColTransitions[i]+"�ն���"+boardBuriedHoles[i]+"�����"+boardWells[i]);
				System.out.println(value(landingHeight[i], erodedPieceCellsMetric[i], boardRowTransitions[i], boardColTransitions[i], boardBuriedHoles[i], boardWells[i]));
				printMap(newMap);
				num=i;
				value=temp;
				}
				}
			}
			//System.out.println(num);
		return num;
	}
}
