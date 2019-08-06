package HW1_LeonGrinshpun;

import java.util.Comparator;

public class StreetComparator implements Comparator<AddressInformation>

{
	public int compare(AddressInformation o1 , AddressInformation o2)
	{
		int tempStreet = 0;
		tempStreet = o1.getStreet().compareTo(o2.getStreet());
		if (tempStreet == 0)
			return 1;
		return tempStreet;
	}
}
