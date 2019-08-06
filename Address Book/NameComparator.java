package HW1_LeonGrinshpun;

import java.util.Comparator;

public class NameComparator implements Comparator<AddressInformation>
{
	public int compare(AddressInformation o1 , AddressInformation o2)
	{
		return o1.getName().compareTo(o2.getName());
	}
}

