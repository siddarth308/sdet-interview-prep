public class BestTimeToBuySellStock {
    static int maxProfit(int[] prices) {
        // int n = prices.length;
        // int res = 0;

        // // Explore all possible ways to buy and sell stock
        // for (int i = 0; i < n - 1; i++) {
        // for (int j = i + 1; j < n; j++) {
        // res = Math.max(res, prices[j] - prices[i]);
        // }
        // }
        // return res;
        // }

        // better approach -[Expected Approach] One Traversal Solution - O(n) Time and
        // O(1) Space
        // In order to maximize the profit, we need to minimize the cost price and
        // maximize the selling price. So at every step, we keep track of the minimum
        // buy price of stock encountered so far. For every price, we subtract with the
        // minimum so far and if we get more profit than the current result, we update
        // the result.

        int res = 0;
        int minSoFar = prices[0];

        for (int i = 1; i < prices.length; i++) {

            minSoFar = Math.min(minSoFar, prices[i]);

            res = Math.max(res, prices[i] - minSoFar);
        }

        return res;
    }

}
