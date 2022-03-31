package app.model.business;

public interface Observable<T> {
	void addObserver(T o);
	void removeObserver(T o);
}
