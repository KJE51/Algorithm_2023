import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

//백준 2206 벽 뚫기

public class BrakeWall {
	static class Point{
		int x;
		int y;
		int cnt;
		boolean flag;
		public Point(int x, int y, int cnt, boolean flag) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.flag = flag;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		boolean[][] arr;
		boolean[][] visited1;
		boolean[][] visited2;
		int n;
		int m;
		boolean flag = false;
		int[] di = { 1, -1, 0, 0 };
		int[] dk = { 0, 0, 1, -1 };
		
		String strI;
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);

		// 벽 1 true, 빈칸 0 false
		arr = new boolean[n][m];
		//visited를 분리해 벽을 부쉈을 때, 부수지 않았을 때로 분리
		visited1 = new boolean[n][m];
		visited2 = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			strI = br.readLine();
			for (int k = 0; k < m; k++) {
				if (strI.charAt(k) == '0') {
					arr[i][k] = false;
				} else {
					arr[i][k] = true;
				}
			}
		}

		visited1[0][0] = true;
		visited2[0][0] = true;
		
		//한 칸일 때는 바로 끝내기 - 예외처리 필요
		if(n == 1 && m == 1) {
			System.out.println("1");
			return;
		}
		
		ArrayDeque<Point> que = new ArrayDeque<>();
		que.add(new Point(0, 0, 1, false));
		//벽 통과 안함 - false
		
		Point tmp;
		while(!que.isEmpty()&& flag == false) {
			tmp = que.poll();
			for (int i = 0; i < 4; i++) {
				int newI = tmp.x + di[i];
				int newK = tmp.y + dk[i];
				
				//목적지 도착
				if(newI == n-1 && newK == m-1) {
					flag = true;
					System.out.println(tmp.cnt + 1);
					break;
				}
				
				//범위 내
				if (newI < n && newI > -1 && newK > -1 && newK < m) {
					//벽 통과 안 했을 때
					if(tmp.flag == false) {
						//빈칸 가기
						if(arr[newI][newK] == false && visited1[newI][newK] == false) {
							visited1[newI][newK] = true;
							que.add(new Point(newI, newK, tmp.cnt+1, false));
						}
						//벽 가기
						if(arr[newI][newK] == true) {
							visited1[newI][newK] = true;
							visited2[newI][newK] = true;
							que.add(new Point(newI, newK, tmp.cnt+1, true));
						}
					}
					//벽 통과 했을 때
					else {
						//빈칸 가기
						if(arr[newI][newK] == false && visited1[newI][newK] == false && visited2[newI][newK] == false) {
							visited2[newI][newK] = true;
							que.add(new Point(newI, newK, tmp.cnt+1, true));
						}
					}

				}
			}
		}
		if(flag == false)
			System.out.println("-1");
	}

}
