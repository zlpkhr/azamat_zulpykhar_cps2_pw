package fr.emse.ai.solution;

import java.util.ArrayList;

import fr.emse.ai.util.SimpleTwoPlyGameTree;

public class MinMaxAlgo {

    public int nbNode=0;

    public Integer maxMin(SimpleTwoPlyGameTree simpleTwoPlyGameTree){
        nbNode++;
        if(simpleTwoPlyGameTree.isLeaf()){
            return simpleTwoPlyGameTree.getValue();
        }
        else{
            ArrayList<SimpleTwoPlyGameTree> next= simpleTwoPlyGameTree.getChildren();
            Integer value = Integer.MIN_VALUE;
            for (SimpleTwoPlyGameTree children : next){
                value = Math.max(value,minMax(children));
            }
            return value;
        }
    }

    public Integer minMax(SimpleTwoPlyGameTree simpleTwoPlyGameTree){
        nbNode++;
        if(simpleTwoPlyGameTree.isLeaf()){
            return simpleTwoPlyGameTree.getValue();
        }
        else{
            ArrayList<SimpleTwoPlyGameTree> next= simpleTwoPlyGameTree.getChildren();
            Integer value = Integer.MAX_VALUE;
            for (SimpleTwoPlyGameTree children : next){
                value = Math.min(value,maxMin(children));
            }
            return value;
        }
    }

}
