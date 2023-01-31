import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

//18111 마인크래프트
public class MineCraft {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int blocks = Integer.parseInt(input[2]);
		
		int min = 256;
		int max = 0;
		int tmp;

		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			String []temp = br.readLine().split(" ");
			for (int k = 0; k < m; k++) {
				tmp = Integer.parseInt(temp[k]);
				arr[i][k] = tmp;
				min = Math.min(min, tmp);
				max = Math.max(max, tmp);
			}
		}

		int time = Integer.MAX_VALUE;
		int resultH = 0;
		int tmpTime=0;
		int dif;
		int block;
		
		// 땅 높이 최소부터 최대까지 하나씩 비교
		for (int height = min; height < max + 1; height++) {
			block = blocks;
			tmpTime = 0;
			
			for (int i = 0; i < n; i++) {
				if(tmpTime > time) {
					break;
				}
				for (int k = 0; k < m; k++) {
					
					// 블럭 쌓기
					dif = arr[i][k] - height;
					if(dif == 0)
						continue;
					if (dif<0) {
						tmpTime -= dif;
						block += dif;

						// 블럭 제거
					} else if (dif>0) {
						tmpTime += 2 * dif;
						block += dif;
					}
					
					if(tmpTime > time) {
						break;
					}
				}
			}

			if (block>=0 && time >= tmpTime) {
				time = tmpTime;
				resultH = height;
			}
		}
		System.out.println(time + " " + resultH);
	}
}
