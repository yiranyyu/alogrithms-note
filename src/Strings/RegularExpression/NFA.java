package Strings.RegularExpression;
// Created by yirany on 2018/2/3

import Fundamentals.Bags_Queues_Stacks.Stack;
import Graphs.UndirectedGraphs.Bag;
import Graphs.DirectedGraphs.Digraph;
import Graphs.DirectedGraphs.DirectedDFS;

public class NFA
{
    private char[] regex;
    private Digraph<Integer> graph;
    private int numberOfStates; // length of regex

    public static boolean recognize(String text, String pattern)
    {
        return new NFA(pattern).recognizes(text);
    }

    private NFA(String pattern)
    {
        Stack<Integer> ops = new Stack<>();
        regex = pattern.toCharArray();
        numberOfStates = regex.length;
        graph = new Digraph<>();
        for (int i = 0; i <= numberOfStates; ++i)
            graph.addVertex(i);

        for (int i = 0; i < numberOfStates; ++i)
        {
            int left = i;
            if (regex[i] == '(' || regex[i] == '|')
                ops.push(i);
            else if (regex[i] == ')')
            {
                int or = ops.pop();
                if (regex[or] == '|')
                {
                    left = ops.pop();
                    graph.addEdge(left, or+1);
                    graph.addEdge(or, i);
                }
                else left = or;
            }

            if (i < numberOfStates-1 && regex[i+1] == '*')// look at next character
            {
                graph.addEdge(left, i+1);
                graph.addEdge(i+1, left);
            }

            if (regex[i] == '(' || regex[i] == '*' || regex[i] == ')')
                graph.addEdge(i, i+1);
        }
    }

    private Iterable<Integer> states()
    {
        return graph.vertices();
    }

    private boolean recognizes(String text)
    {
        // start from state zero
        DirectedDFS<Integer> dfs = new DirectedDFS<>(graph, 0);
        Bag<Integer> nextStates = updateReachableStates(dfs);

        for (int i = 0; i < text.length(); ++i)
        {
            // cal possible states in NFA to reach from text[i+1]
            Bag<Integer> match = new Bag<>();
            for (int state : nextStates)
                if (state < numberOfStates)
                    if (regex[state] == text.charAt(i) || regex[state] == '.')    // this is connect operation
                        match.add(state+1);

            dfs = new DirectedDFS<>(graph, match);
            nextStates = updateReachableStates(dfs);
        }

        for (int state : nextStates)
            if (state == numberOfStates)
                return true;
        return false;
    }

    private  Bag<Integer> updateReachableStates(DirectedDFS<Integer> dfs)
    {
        Bag<Integer> nextStates = new Bag<>();
        for (int state : states())
            if (dfs.reachableTo(state))
                nextStates.add(state);
        return nextStates;
    }
}
