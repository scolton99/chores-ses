package c1.ses.chores.util;

public interface FirebaseDataListener<T> {
    /**
     * Called when a Firebase request is completed and the data have been formatted appropriately.
     *
     * @param t The data passed in from the successful request.
     */
    void onData(T t);
}