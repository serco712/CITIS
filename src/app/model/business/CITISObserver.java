package app.model.business;

public interface CITISObserver {
	void registerObserver(CITISMap cm);
	void CITISObjectAdded(CITISMap cm, CITISObject co);
	void CITISObjectModified(CITISMap cm, CITISObject co);
	void CITISObjectDeleted(CITISMap cm, CITISObject co);
}
