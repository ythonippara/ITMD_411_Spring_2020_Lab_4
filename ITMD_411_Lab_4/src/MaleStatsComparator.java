import java.util.Comparator;

public class MaleStatsComparator implements Comparator<BankRecords> {

	@Override
	public int compare(BankRecords o1, BankRecords o2) {

		int compareRegion = o1.getRegion().compareTo(o2.getRegion());
		if (compareRegion != 0) {
			return compareRegion;
		} 
		
		int compareCars = o1.getCar().compareTo(o2.getCar());
		if (compareCars != 0) {
			return compareCars;
		}
		
		int compareChildren = o1.getChildren() - o2.getChildren();
		if (compareChildren != 0) {
			return compareChildren;
		}
		
		return o1.getSex().compareTo(o2.getSex());
    }

}
