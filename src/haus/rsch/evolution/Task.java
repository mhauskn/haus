package haus.rsch.evolution;

/**
 * The task represents a task for organisms to perform
 *
 * @param <OrganismInput> The input given to the organism
 * @param <OrganismOutput> The output expected from the organism
 */
public interface Task<OrganismInput, OrganismOutput> {
	/**
	 * Evaluates a given organism
	 */
	Double evaluateOrganism (Organism<OrganismInput, OrganismOutput> org);
}
