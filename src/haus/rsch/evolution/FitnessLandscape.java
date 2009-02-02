package haus.rsch.evolution;

import haus.rsch.evolution.elim.Eliminator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The fitness landscape is the basis for our evolution.
 * It will contain several organisms and will evaluate
 * their performance.
 *
 */
@SuppressWarnings("unchecked")
public class FitnessLandscape {
	ArrayList<Organism> organisms;
	Task task;
	Replacer replacer;
	Eliminator eliminator;
	public Organism best_org;
	
	public FitnessLandscape (ArrayList<Organism> orgs, Task t,
			Replacer r, Eliminator e) {
		organisms = orgs;
		task = t;
		replacer = r;
		eliminator = e;
	}
	
	public void evaluate () {
		for (Organism org : organisms)
			org.fitnessScore = task.evaluateOrganism(org);
		int size = organisms.size();
		Collections.sort(organisms);
		best_org = organisms.get(0);
		eliminator.eliminate(organisms);
		replacer.replace(size - organisms.size(), organisms);
	}
}
