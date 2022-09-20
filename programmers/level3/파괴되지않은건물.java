package study55;

public class 파괴되지않은건물 {

	static int n,m;
    static int sum[][];
    public int solution(int[][] board, int[][] skill) {
        n=board.length;
         m=board[0].length;
        int answer = 0;
        sum=new int[n+1][m+1];
        
        prefixSum(skill);
        
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                board[i][j]+=sum[i][j];

                if(board[i][j]>0) {
                    answer++;
                }
            }
        }
        
        
        return answer;
    }
    
    static void prefixSum(int[][] skill) {
        
        for(int i=0; i<skill.length; i++)
        {
            int type=skill[i][0];
            int r1=skill[i][1];
            int c1=skill[i][2];
            int r2=skill[i][3];
            int c2=skill[i][4];
            int degree=skill[i][5];

            if(type==1) { //공격일 경우
                degree*=-1;
            }
            
            
            sum[r1][c1]+=degree;
            sum[r1][c2+1]+=(-1*degree);
            
            sum[r2+1][c1]+=(-1*degree);
            sum[r2+1][c2+1]+=degree;
     
        }
        

                
        
        for(int i=0; i<n; i++)
        {
            int val=0;
            for(int j=0; j<m; j++)
            {
                val+=sum[i][j];
                sum[i][j]=val;
            }
        }
        
        
        for(int i=0; i<m; i++)
        {
            int val=0;
            for(int j=0; j<n; j++)
            {
                val+=sum[j][i];
                sum[j][i]=val;
            }
        }
        
        

        
        
    }
}