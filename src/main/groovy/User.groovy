/**
 * A class that has toString method.
 */
class User {

    String name
    int id

    int toStringCalledTimes = 0

    @Override
    String toString() {
        println(super.toString() + ' toString called times: ' + (++toStringCalledTimes))
        // Sleep for better demonstration
        sleep(1000)
        name + ':' + id
    }
}
