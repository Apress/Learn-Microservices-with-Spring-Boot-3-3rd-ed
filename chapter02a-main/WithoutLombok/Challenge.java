/** 
 * Represents a challenge with two factors. 
 */
public final class Challenge {
  // Both factors 
  private final int factorA;
  private final int factorB;
  public Challenge(int factorA, int factorB) {
    this.factorA = factorA;
    this.factorB = factorB;
  }
  public int getFactorA() {
    return this.factorA;
  }
  public int getFactorB() {
    return this.factorB;
  }
  @Override 
  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof Challenge)) return false;
    final Challenge other = (Challenge) o;
    if (this.getFactorA() != other.getFactorA()) return false;
    if (this.getFactorB() != other.getFactorB()) return false;
    return true;
  }
  @Override
  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    result = result * PRIME + factorA;
    result = result * PRIME + factorB;
    return result;
  }
  @Override 
  public String toString() {
    return "Challenge [factorA=" + factorA + ", factorB=" + factorB + "]";
  }
}
