package haus.rsch.evolution;

/**
 * The organism is the base unit of life in our system
 */
public abstract class Organism<AnswerInput, AnswerOutput> implements 
	Comparable<Organism<AnswerInput,AnswerOutput>> {
	
	public double fitnessScore;
	
	/**
	 * Runs the organism on the given task input
	 */
	public abstract AnswerOutput run(AnswerInput input);

	public int compareTo (Organism<AnswerInput, AnswerOutput> other) {
		return fitnessScore < other.fitnessScore? 1 : -1;
	}
}
