package fr.emse.ai.solution;

import java.util.ArrayList;

import fr.emse.ai.util.SimpleTwoPlyGameTree;

public class AlphaBetaAlgo {

    public int nbNode=0;

    public Integer maxValue(SimpleTwoPlyGameTree tree,Integer alpha,Integer beta){
        nbNode++;
        if(tree.isLeaf()){
            return tree.getValue();
        }
        else{
            Integer value = Integer.MIN_VALUE;
            ArrayList<SimpleTwoPlyGameTree> next = tree.getChildren();
            for(SimpleTwoPlyGameTree children: next){
                value=Math.max(value,minValue(children,alpha,beta));
                if(value >= beta){
                    return value;
                }
                alpha = Math.max(alpha,value);
            }
            return value;
        }
    }

    public Integer minValue(SimpleTwoPlyGameTree tree,Integer alpha,Integer beta){
        nbNode++;
        if(tree.isLeaf()){
            return tree.getValue();
        }
        else{
            Integer value = Integer.MAX_VALUE;
            ArrayList<SimpleTwoPlyGameTree> next = tree.getChildren();
            for(SimpleTwoPlyGameTree children: next){
                value=Math.min(value,maxValue(children,alpha,beta));
                if(value <= alpha){
                    return value;
                }
                beta = Math.min(beta,value);
            }
            return value;
        }
    }
}
