package de.OFactory.Substitution.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Element {
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> PSE = new ArrayList<ArrayList>();

	//        NAME			 {Ordnungszahl, Symbol, Gruppe, Periode}
	public static final List<?> WASSERSTOFF = Arrays.asList(1, "H" , 1, 1);
	public static final List<?> KOHLENSTOFF = Arrays.asList(6, "C" , 4, 2);
	public static final List<?> STICKSTOFF  = Arrays.asList(7, "N" , 5, 2);
	public static final List<?> SAUERSTOFF  = Arrays.asList(8, "O" , 6, 2);
	public static final List<?> FLOUR       = Arrays.asList(9, "F" , 7, 2);
	public static final List<?> CHLOR       = Arrays.asList(1, "Cl", 7, 3);
	public static final List<?> BROM        = Arrays.asList(1, "Br", 7, 4);
	
	
	
}
