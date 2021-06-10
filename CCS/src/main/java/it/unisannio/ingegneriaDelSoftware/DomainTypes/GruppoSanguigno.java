package it.unisannio.ingegneriaDelSoftware.DomainTypes;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GruppoSanguigno {
	Ap,
	Am,
	Bp,
	Bm,
	ZEROp,
	ZEROm,
	ABp,
	ABm;

	@SuppressWarnings("serial")
	private final static HashMap<GruppoSanguigno, List<GruppoSanguigno>> puoDonareA = new HashMap<GruppoSanguigno, List<GruppoSanguigno>>() {
		{
			put (Ap, new ArrayList<GruppoSanguigno>(Arrays.asList(Ap,ABp)));
			put (Am, new ArrayList<GruppoSanguigno>(Arrays.asList(Ap,Am,ABp,ABm)));
			put (Bp, new ArrayList<GruppoSanguigno>(Arrays.asList(Bp,ABp)));
			put (Bm, new ArrayList<GruppoSanguigno>(Arrays.asList(Bp,Bm,ABp,ABm)));
			put (ZEROp, new ArrayList<GruppoSanguigno>(Arrays.asList(ZEROp,Ap,Bp,ABp)));
			put (ZEROm, new ArrayList<GruppoSanguigno>(Arrays.asList(Ap,Am,Bp,Bm,ZEROp,ZEROm,ABp,ABm)));
			put (ABp, new ArrayList<GruppoSanguigno>(Arrays.asList(ABp)));
			put (ABm, new ArrayList<GruppoSanguigno>(Arrays.asList(ABp,ABm)));
		}
	};

	@SuppressWarnings("serial")
	private final static HashMap<GruppoSanguigno, List<GruppoSanguigno>> puoRicevereDa = new HashMap<GruppoSanguigno, List<GruppoSanguigno>>() {
		{
			put (Ap, new ArrayList<GruppoSanguigno>(Arrays.asList(Ap,Am,ZEROp,ZEROm)));
			put (Am, new ArrayList<GruppoSanguigno>(Arrays.asList(Am,ZEROm)));
			put (Bp, new ArrayList<GruppoSanguigno>(Arrays.asList(Bp,Bm,ZEROp,ZEROm)));
			put (Bm, new ArrayList<GruppoSanguigno>(Arrays.asList(Bm,ZEROm)));
			put (ZEROp, new ArrayList<GruppoSanguigno>(Arrays.asList(ZEROp,ZEROm)));
			put (ZEROm, new ArrayList<GruppoSanguigno>(Arrays.asList(ZEROm)));
			put (ABp, new ArrayList<GruppoSanguigno>(Arrays.asList(ABp,Ap,Am,Bp,Bm,ZEROp,ZEROm,ABm)));
			put (ABm, new ArrayList<GruppoSanguigno>(Arrays.asList(ABm,Am,Bm,ZEROm)));
		}
	};

	public static Iterator<GruppoSanguigno> puoDonareA(GruppoSanguigno gs) {
		assert gs !=null;
		return puoDonareA.get(gs).iterator();
	}
	
	public static Iterator<GruppoSanguigno> puoRicevereDa(GruppoSanguigno gs) {
		assert gs !=null;
		return puoRicevereDa.get(gs).iterator();
	}
}