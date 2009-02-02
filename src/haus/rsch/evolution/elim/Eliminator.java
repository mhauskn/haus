package haus.rsch.evolution.elim;

import haus.rsch.evolution.Organism;

import java.util.ArrayList;

/**
 * Designed decide how to choose which organisms are fit 
 * and which are unfit.
 *
 */
public interface Eliminator {
	@SuppressWarnings("unchecked")
	public void eliminate (ArrayList<Organism> organisms);
}
