public class Lock {
    public boolean canLock() {
        return true;
    }

    public void lock() {
        System.out.println("Locked.");

    }

    public void unlock() {
        System.out.println("Unlocked.");
    }
}


public class UseLock {

    Lock lock;

    UseLock() {
        lock = new Lock();
    }

    void testLock() {
        try {
            if (lock.canLock()) {
                lock.lock();
                System.out.println('running in Lock');

            }
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args){
        UseLock useLock = new UseLock();
        useLock.testLock();
    }

}


