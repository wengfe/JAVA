package HelloWorld;

import java.util.Scanner;

public class Helloworld {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		System.out.println("Hello world");
//		Scanner in = new Scanner(System.in);
//		System.out.println("echo:" + in.nextLine());
//		System.out.println("2+3=" + (2+3));
//		String price = in.nextLine();
//		System.out.println(price);
//		final int amount = 100 ;
//		System.out.println(amount +" - " + price + " = " + (100-price) );

//		in.close();

//		int[] a = {1,2,3,4,5};
//		int[] b = new int[a.length];
//		for(int i=1;i<b.length;i++)
//		{
//			b[i] = a[i];
//		}
//		for(int i =0;i<a.length;i++)
//		{
//			if(a[i] ==b[i]){
//				System.out.print("true");
//			}
//		}
//		System.out.println(a == b);

        Scanner in = new Scanner(System.in);
        final int SIZE = 3;
        int[][] board = new int[SIZE][SIZE];
        boolean gotResult = false;
        int numOfX = 0;
        int numOfY = 0;

        for (int i=0; i<board.length;i++)
        {
            for (int j=0; j<board[i].length; j++)
            {
                board[i][j] = in.nextInt();
            }
        }
//		检查行
        for(int i=0; i<board.length; i++)
        {
            numOfX = 0;
            numOfY = 0;
            for(int j=0;j<board[i].length; j++)
            {
                if(board[i][j] == 1)
                {
                    numOfX++;
                }
                else
                {
                    numOfY++;
                }
            }
            if(numOfX == 3 || numOfY ==3)
            {
                gotResult = true;
                break;
            }
        }

//        判断列
        if(!gotResult)
        {
            for (int j=0; j<board[0].length; j++)
            {
                numOfX = 0;
                numOfY = 0;
                for (int i=0; i<board.length; i++)
                {
                    if (board[i][j] == 1)
                    {
                        numOfX++;
                    }
                    else
                    {
                        numOfY++;
                    }
                }
                if(numOfX == 3 || numOfY ==3)
                {
                    gotResult = true;
                    break;
                }
            }

        }
//        判断对角线
        if (!gotResult)
        {
            numOfX = 0;
            numOfY = 0;
            for (int i = 0; i < board.length; i++)
            {
                if (board[i][i]==1)
                {
                    numOfX++;
                }
                else
                {
                    numOfY++;
                }
                if(numOfX == 3 || numOfY ==3)
                {
                    gotResult = true;
                    break;
                }
            }
        }
//        判断反对角线
        if (!gotResult)
        {
            numOfX = 0;
            numOfY = 0;
            for(int i=0;i<SIZE;i++)
            {
                if (board[i][SIZE-i-1] == 1)
                {
                    numOfX++;
                }
                else
                {
                    numOfY++;
                }
                if(numOfX == 3 || numOfY ==3)
                {
                    gotResult = true;
                    break;
                }
            }

        }
        if(gotResult)
        {
            if (numOfX == 3)
            {
                System.out.println("winner is X");
            }
            else
            {
                System.out.println("winner is Y");
            }
        }
        else
        {
            System.out.println("平局");
        }


		for(int i=0; i<board.length; i++)
		{
			for(int j=0;j<board[i].length;j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.print('\n');
		}
    }

}