class Lock {
    boolean canLock() {
        return true
    }

    void lock() {
        println("Locked.")

    }

    void unlock() {
        println("unlocked.")
    }
}


class LockService{

    Lock lock

  LockService(){
        lock = new Lock()
    }

    void lock(Closure code){
        try{
            if(lock.canLock()){
                lock.lock()
                code()
            }
        }finally {
            lock.unlock()
        }

    }

}

LockService lockService = new LockService()

lockService.lock{
    println "running in lock"
}

lockService.lock{
    println "running in lock 2"
}