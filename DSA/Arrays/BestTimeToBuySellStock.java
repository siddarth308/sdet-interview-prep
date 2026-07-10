public class BestTimeToBuySellStock {
    static int maxProfit(int[] prices) {
        int n = prices.length;
        int res = 0;

        // Explore all possible ways to buy and sell stock
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                res = Math.max(res, prices[j] - prices[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] prices = { 7, 10, 1, 3, 6, 9, 2 };
        System.out.println(maxProfit(prices));
    }

    // better approach -

    int res = 0;
    int minsofar = prices[0];

    for(int=1;i<prices.length;i++)
    {
        minsofar = Math.min(minsofar, prices[i]);

        res = Math.max(res, prices[i] - minsofar);
    }return res;
}
