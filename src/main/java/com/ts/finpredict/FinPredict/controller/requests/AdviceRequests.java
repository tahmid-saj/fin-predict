package com.ts.finpredict.FinPredict.controller.requests;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AdviceRequests {

    public AdviceRequests() {

    }

    public static List<Map<String, String>> getAdvices() {
        List<Map<String, String>> res = new ArrayList<>();
        Map<String, String> resAdvices = new TreeMap<String, String>();

        resAdvices.put(
                "Create a Budget",
                "Track Your Income and Expenses: Record all sources of income and track your spending to see where your money goes." +
                        "Set Spending Limits: Allocate specific amounts for categories like housing, groceries, entertainment, and savings." +
                        "Adjust as Needed: Regularly review and adjust your budget to reflect changes in income or expenses."
        );
        resAdvices.put(
                "Build an Emergency Fund",
                "Aim for 3-6 Months of Expenses: Save enough to cover essential expenses for several months in case of unexpected events like job loss or medical emergencies." +
                        "Keep It Accessible: Store this money in a high-yield savings account where it’s easily accessible but still earns some interest."
        );
        resAdvices.put(
                "Manage Debt Wisely",
                "Prioritize High-Interest Debt: Focus on paying off high-interest debts first, such as credit cards, to reduce the amount of interest you pay over time." +
                        "Consider Debt Consolidation: If you have multiple debts, consolidating them into a single loan with a lower interest rate can simplify payments and reduce costs."
        );
        resAdvices.put(
                "Save for Retirement",
                "Start Early: The earlier you start saving for retirement, the more you can benefit from compound interest." +
                        "Contribute to Retirement Accounts: Use tax-advantaged accounts like a 401(k) or IRA. Contribute at least enough to get any employer match, if available."
        );
        res.add(resAdvices);

        resAdvices = new TreeMap<String, String>();
        resAdvices.put(
                "Invest Wisely",
                "Diversify Your Portfolio: Spread your investments across different asset classes (stocks, bonds, real estate) to manage risk." +
                        "Understand Your Risk Tolerance: Choose investments that match your risk tolerance and time horizon."
        );
        resAdvices.put(
                "Plan for Major Expenses",
                "Save for Big Purchases: Plan and save for major expenses like buying a home, car, or funding education to avoid taking on excessive debt." +
                        "Consider Future Needs: Think about long-term goals and how they impact your financial planning, such as children’s education or a major life event."
        );
        resAdvices.put(
                "Protect Your Assets",
                "Get Insurance: Ensure you have adequate insurance coverage for health, life, disability, home, and auto to protect against unexpected financial burdens." +
                        "Create a Will: Establish a will and other estate planning documents to ensure your assets are distributed according to your wishes."
        );
        resAdvices.put(
                "Review and Adjust Regularly",
                "Monitor Your Financial Health: Regularly review your financial situation, track your progress towards goals, and make adjustments as needed." +
                        "Seek Professional Advice: Consider consulting with a financial advisor for personalized advice, especially for complex financial situations."
        );
        res.add(resAdvices);

        return res;
    }
}
