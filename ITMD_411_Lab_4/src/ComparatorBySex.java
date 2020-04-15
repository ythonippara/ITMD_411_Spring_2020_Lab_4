import java.util.Comparator;

public class ComparatorBySex implements Comparator<BankRecords> {

	@Override
	public int compare(BankRecords o1, BankRecords o2) {
		
		// use compareTo() to compare strings
		int result = o1.getSex().compareTo(o2.getSex());
		return result;
	}

}
