package fr.emse.ai.util;

import java.util.ArrayList;

import fr.emse.ai.solution.AlphaBetaAlgo;
import fr.emse.ai.solution.MinMaxAlgo;
import fr.emse.ai.util.SimpleTwoPlyGameTree;

public class TestGameBis {
	
	public static void main(String[] args) {
		// niveau 4
		ArrayList<SimpleTwoPlyGameTree > sublist41 = new ArrayList<SimpleTwoPlyGameTree >();
		sublist41.add(new SimpleTwoPlyGameTree (20, true));
		sublist41.add(new SimpleTwoPlyGameTree (22, true));

		ArrayList<SimpleTwoPlyGameTree > sublist42 = new ArrayList<SimpleTwoPlyGameTree >();
		sublist42.add(new SimpleTwoPlyGameTree (25, true));
		sublist42.add(new SimpleTwoPlyGameTree (30, true));

		ArrayList<SimpleTwoPlyGameTree > sublist43 = new ArrayList<SimpleTwoPlyGameTree >();
		sublist43.add(new SimpleTwoPlyGameTree (17, true));
		sublist43.add(new SimpleTwoPlyGameTree (0, true));
		sublist43.add(new SimpleTwoPlyGameTree (30, true));
		sublist43.add(new SimpleTwoPlyGameTree (15, true));
		
		ArrayList<SimpleTwoPlyGameTree > sublist44 = new ArrayList<SimpleTwoPlyGameTree >();
		sublist44.add(new SimpleTwoPlyGameTree (50, true));
		sublist44.add(new SimpleTwoPlyGameTree (53, true));

		ArrayList<SimpleTwoPlyGameTree > sublist45 = new ArrayList<SimpleTwoPlyGameTree >();
		sublist45.add(new SimpleTwoPlyGameTree (65, true));
		sublist45.add(new SimpleTwoPlyGameTree (20, true));
		
		ArrayList<SimpleTwoPlyGameTree > sublist46 = new ArrayList<SimpleTwoPlyGameTree >();
		sublist46.add(new SimpleTwoPlyGameTree (10, true));
		sublist46.add(new SimpleTwoPlyGameTree (8, true));

		ArrayList<SimpleTwoPlyGameTree > sublist47 = new ArrayList<SimpleTwoPlyGameTree >();
		sublist47.add(new SimpleTwoPlyGameTree (5, true));
		sublist47.add(new SimpleTwoPlyGameTree (2, true));
		
		ArrayList<SimpleTwoPlyGameTree > sublist48 = new ArrayList<SimpleTwoPlyGameTree >();
		sublist48.add(new SimpleTwoPlyGameTree (92, true));
		sublist48.add(new SimpleTwoPlyGameTree (1, true));
		
		ArrayList<SimpleTwoPlyGameTree > sublist49 = new ArrayList<SimpleTwoPlyGameTree >();
		sublist49.add(new SimpleTwoPlyGameTree (25, true));
		sublist49.add(new SimpleTwoPlyGameTree (30, true));

// niveau 3	
		SimpleTwoPlyGameTree  subTree31 = new SimpleTwoPlyGameTree (Integer.MIN_VALUE, false,
				sublist41);
		SimpleTwoPlyGameTree  subTree32 = new SimpleTwoPlyGameTree (Integer.MIN_VALUE, false,
				sublist42);
		SimpleTwoPlyGameTree  subTree33 = new SimpleTwoPlyGameTree (Integer.MIN_VALUE, false,
				sublist43);
		SimpleTwoPlyGameTree  subTree34 = new SimpleTwoPlyGameTree (Integer.MIN_VALUE, false,
				sublist44);
		SimpleTwoPlyGameTree  subTree35 = new SimpleTwoPlyGameTree (Integer.MIN_VALUE, false,
				sublist45);
		SimpleTwoPlyGameTree  subTree36 = new SimpleTwoPlyGameTree (Integer.MIN_VALUE, false,
				sublist46);
		SimpleTwoPlyGameTree  subTree37 = new SimpleTwoPlyGameTree (Integer.MIN_VALUE, false,
				sublist47);
		SimpleTwoPlyGameTree  subTree38 = new SimpleTwoPlyGameTree (Integer.MIN_VALUE, false,
				sublist48);
		SimpleTwoPlyGameTree  subTree39 = new SimpleTwoPlyGameTree (Integer.MIN_VALUE, false,
				sublist49);

// niveau 2		
		SimpleTwoPlyGameTree  subTree21 = new   SimpleTwoPlyGameTree (Integer.MAX_VALUE, true);
		subTree21.addChild(subTree31);
		subTree21.addChild(subTree32);
		subTree21.addChild(subTree33);
		SimpleTwoPlyGameTree  subTree22 = new   SimpleTwoPlyGameTree (Integer.MAX_VALUE, true);
		subTree22.addChild(subTree34);
		subTree22.addChild(subTree35);
		
		SimpleTwoPlyGameTree  subTree23 = new   SimpleTwoPlyGameTree (Integer.MAX_VALUE, true);
		subTree23.addChild(subTree36);
		subTree23.addChild (subTree37);
		
		SimpleTwoPlyGameTree  subTree24 = new   SimpleTwoPlyGameTree (Integer.MAX_VALUE, true);
		subTree24.addChild(subTree38);
		subTree24.addChild(subTree39);

		
// niveau 1
		SimpleTwoPlyGameTree  subTree11 = new   SimpleTwoPlyGameTree (Integer.MIN_VALUE, false);
		subTree11.addChild(subTree21);
		subTree11.addChild(subTree22);
		
		SimpleTwoPlyGameTree  subTree12 = new   SimpleTwoPlyGameTree (Integer.MIN_VALUE, false);
		subTree12.addChild(subTree23);
		subTree12.addChild(subTree24);

// Niveau 0
		SimpleTwoPlyGameTree  tree = new   SimpleTwoPlyGameTree (Integer.MAX_VALUE, true);
		tree.addChild(subTree11);
		tree.addChild(subTree12);

		MinMaxAlgo testMinMax = new MinMaxAlgo();
		System.out.println(testMinMax.maxMin(tree));
		System.out.println("NB node mM:" + testMinMax.nbNode);
		
		AlphaBetaAlgo testAlphaBeta = new AlphaBetaAlgo();
		System.out.println(testAlphaBeta.maxValue(tree,Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println("NB node ab:" + testAlphaBeta.nbNode);
	}

}
