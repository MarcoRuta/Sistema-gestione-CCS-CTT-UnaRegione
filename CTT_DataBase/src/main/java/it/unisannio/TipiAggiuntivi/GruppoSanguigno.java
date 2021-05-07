package it.unisannio.TipiAggiuntivi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public enum GruppoSanguigno {
	Ap("A+"), Am("A-"), Bp("B+"), Bm("B-"), ZEROp("0+"), ZEROm("0-"), ABp("AB+"), ABm("AB-");

GruppoSanguigno(String string) {
		// TODO Auto-generated constructor stub
	}

//Restituisce un ArrayList che contiene solo i gruppi compatibili col GruppoSanguigno desiderato (non se stesso). 
//Utilizzato in ricercaSacca. Dà la priorità minima al gruppo Zerom per non farlo utilizzare quasi mai in ricercaSacca.
	public static ArrayList<GruppoSanguigno> gruppiCompatibili(GruppoSanguigno gs) {
		ArrayList<GruppoSanguigno> gsCompatibili = new ArrayList<GruppoSanguigno>();
		if(gs==Ap) {
			gsCompatibili.add(ABp);
			}
		if(gs==Am) {
			gsCompatibili.add(Ap);
			gsCompatibili.add(ABp);
			gsCompatibili.add(ABm);
			}
		if(gs==Bp) {
			gsCompatibili.add(ABp);
			}
		if(gs==Bm) {
			gsCompatibili.add(Bp);
			gsCompatibili.add(ABp);
			gsCompatibili.add(ABm);
			}
		if(gs==ZEROp) {
			gsCompatibili.add(Ap);
			gsCompatibili.add(Bp);
			gsCompatibili.add(ABp);
			}
		if(gs==ZEROm) {
			gsCompatibili.add(Ap);
			gsCompatibili.add(Am);
			gsCompatibili.add(Bp);
			gsCompatibili.add(Bm);
			gsCompatibili.add(ABp);
			gsCompatibili.add(ABm);
			gsCompatibili.add(ZEROp);
			}
		if(gs==ABp) return null;
		if(gs==ABm) {
			gsCompatibili.add(ABp);
			}
		return gsCompatibili;
	}
	
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
	
	/*
	 * @pre
	 * gs not null
	 */
	public static Iterator<GruppoSanguigno> puoDonareA(GruppoSanguigno gs) {
		assert gs !=null;
		return puoDonareA.get(gs).iterator();
	}
	
	/*
	 * @pre
	 * gs not null
	 */
	public static Iterator<GruppoSanguigno> puoRicevereDa(GruppoSanguigno gs) {
		assert gs !=null;
		return puoRicevereDa.get(gs).iterator();
	}
		
}

