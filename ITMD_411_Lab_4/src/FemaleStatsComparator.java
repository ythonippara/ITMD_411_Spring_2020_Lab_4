import java.util.Comparator;

public class FemaleStatsComparator implements Comparator<BankRecords> {

	@Override
	public int compare(BankRecords o1, BankRecords o2) {
		
		int compareMortgage = o1.getMortgage().compareTo(o2.getMortgage());
		if (compareMortgage != 0) 
			return compareMortgage;
		
		int compareSavings = o1.getSavingsAcct().compareTo(o2.getSavingsAcct());
		if (compareSavings != 0) 
			return compareSavings;
		
		return o1.getSex().compareTo(o2.getSex());
	}
}
