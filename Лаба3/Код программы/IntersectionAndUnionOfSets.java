package laba3n11;

import java.util.HashSet;
import java.util.Iterator;

public class IntersectionAndUnionOfSets {
	public HashSet<Integer> Intersection(HashSet<Integer> set1, HashSet<Integer> set2, int n3) {
		int dop = 0;
		HashSet<Integer> intsec = new HashSet<>();
		Iterator<?> iter = set1.iterator();
		for (int i = 0; i < n3; i++) {
			if (iter.hasNext()) {
				dop = (Integer) iter.next();
				if (set2.contains(dop)) intsec.add(dop);
			}
		}
		return intsec;
	}
	public HashSet<Integer> Union(HashSet<Integer> set1, HashSet<Integer> set2, int n3){
		int dop = 0;
		HashSet<Integer> unin = new HashSet<>();
		Iterator<?> iter1 = set1.iterator();
		Iterator<?> iter2 = set2.iterator();
		for (int i = 0; i < n3; i++) {
			if (iter1.hasNext()) {
				dop = (Integer) iter1.next();
				unin.add(dop);
			}
			dop = 0;
			if (iter2.hasNext()) {
				dop = (Integer) iter2.next();
				unin.add(dop);
			}
		}
		return unin;
	}
}
