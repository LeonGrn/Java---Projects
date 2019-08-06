package HW1_LeonGrinshpun;

import java.util.Comparator;

public class ZipComparator implements Comparator<AddressInformation>
{
	public int compare(AddressInformation o1 , AddressInformation o2)
	{
		int firstZip =Integer.parseInt(o1.getZip().trim());
		int secondZip =Integer.parseInt(o2.getZip().trim());
		return firstZip - secondZip;
	}
}
