package haus.rsch.evolution;

/**
 * Mutates an organism
 *
 */
public interface Mutator {
	/**
	 * Mutates the organism
	 */
	@SuppressWarnings("unchecked")
	public void mutateOrganism (Organism org);
}
