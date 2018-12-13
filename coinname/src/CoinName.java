import java.util.HashMap;
import java.util.Scanner;

public class CoinName {
    private HashMap<Integer,String> coinname = new HashMap<Integer, String>();

    public CoinName(){
        coinname.put(1,"penny");
        coinname.put(10,"dime");
        coinname.put(25,"quarter");
        coinname.put(50,"half-dolar");

    }

    public String getName(int amount){
        if (coinname.containsKey(amount))
        {
            return coinname.get(amount);
        }else
            return "NOT FUND";
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CoinName coin = new CoinName();
        int amount = in.nextInt();
        String name = coin.getName(amount);
        System.out.println(name);

    }
}
