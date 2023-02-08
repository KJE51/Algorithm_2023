import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//백준 7569

class Toma{
	int x;
	int y;
	int z;
	int cnt;
	
	public Toma(int x, int y,int z, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.cnt = cnt;
	}
	
}

public class Tomato {
	static int[][][] arr;
	static int m = 0, n = 0, h = 0;
	static int day = -1;
	//안 익은 토마토 수
	static int tomatoes=0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");

		m = Integer.parseInt(str[0]);
		n = Integer.parseInt(str[1]);
		h = Integer.parseInt(str[2]);
		arr = new int[h][n][m];
		Queue<Toma> que = new LinkedList<>();

		// 입력
		// 배열 작성과 동시에 토마토 수 세기, 큐에 넣어 bfs 돌릴 준비
		int tmp;
		for (int ih = 0; ih < h; ih++) {
			for (int in = 0; in < n; in++) {
				str = br.readLine().split(" ");
				for (int im = 0; im < m; im++) {
					tmp = Integer.parseInt(str[im]);
					arr[ih][in][im] = tmp;
					if(tmp == 0)
						tomatoes++;
					if(tmp == 1) {
						que.add(new Toma(im, in, ih, 0));
						arr[ih][in][im] = -1;
						day = 0;
					}
				}
			}
		}
		
		int[] hs = {0, 0, 0, 0, -1, +1};
		int[] ns = {0, 0, -1, +1, 0, 0};
		int[] ms = {-1, +1,0, 0, 0, 0};
		int tmpM, tmpN, tmpH;
		//que에 넣은 거 하나씩 꺼내며 bfs
		Toma tmpT;
		while(!que.isEmpty()) {
			//토마토가 다 익었을 때 break
			if(tomatoes == 0)
				break;
			
			tmpT = que.poll();
			//하나씩 poll, 비교 후 다시 넣기
			for(int i = 0; i < 6; i++) {
				tmpM = tmpT.x + ms[i];
				tmpN = tmpT.y + ns[i];
				tmpH = tmpT.z + hs[i];
				
				//사방? 탐색해서 0일 때 큐에 넣기
				if(tmpM<m&& tmpM>-1 && tmpN<n && tmpN>-1 && tmpH>-1 && tmpH<h) {
					if (arr[tmpH][tmpN][tmpM] == 0) {
						arr[tmpH][tmpN][tmpM] = 1;
						tomatoes--;
						//que에 넣기
						que.add(new Toma(tmpM, tmpN, tmpH, tmpT.cnt + 1));
						//날짜는 매번 업데이트
						day = Math.max(day, tmpT.cnt + 1);
					}
				}
			}
		}
		
		//토마토 출력 
		if(tomatoes>0)
			System.out.println("-1");
		else {
			System.out.println(day);
		}
	}
}
