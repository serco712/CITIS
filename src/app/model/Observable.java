package app.model;

public interface Observable<T> {
	void addObserver(T o);
	void removeObserver(T o);
}
