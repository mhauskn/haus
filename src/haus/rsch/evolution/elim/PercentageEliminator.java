package haus.rsch.evolution.elim;

import haus.rsch.evolution.Organism;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Eliminates the worst x% of the class
 *
 */
public class PercentageEliminator implements Eliminator {
	Double percentage_cut;
	ArrayList<Organism> organisms;
	
	public PercentageEliminator (Double percentage) {
		percentage_cut = percentage;
	}

	/**
	 * Eliminates a portion of the organisms
	 */
	@SuppressWarnings("unchecked")
	public void eliminate(ArrayList<Organism> _organisms) {
		organisms = _organisms;
		int cutoff = (int) (organisms.size() * percentage_cut);
		for (int i = 0; i < cutoff; i++)
			organisms.remove(organisms.size()-1);
	}
}
