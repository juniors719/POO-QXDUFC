package pulapula;

import java.util.*;
import java.util.stream.Collectors;

class Trampoline {

    private LinkedList<Kid> playing;
    private LinkedList<Kid> waiting;

    public static Kid removeFromList(String name, LinkedList<Kid> list){
        for(Kid kid : list) {
            if (kid.getName().equals(name)) {
                Kid removedKid = kid;
                list.remove(kid);
                return removedKid;
            }
        }
        return null;
    }

    public Trampoline() {
        this.waiting = new LinkedList<Kid>();
        this.playing = new LinkedList<Kid>();
    }

    @Override
    public String toString() {
        String waitingList = waiting.stream()
                                    .map(kid -> kid.getName() + ":" + kid.getAge())
                                    .collect(Collectors.joining(", "));

        String playingList = playing.stream()
                                    .map(kid -> kid.getName() + ":" + kid.getAge())
                                    .collect(Collectors.joining(", "));

        return "[" + waitingList + "] => [" + playingList + "]";
    }

    public void arrive(Kid kid) {
        this.waiting.addFirst(kid);
    }

    public void enter() {
        if (!this.waiting.isEmpty()) {
            Kid enteringKid = this.waiting.removeLast();
            this.playing.addFirst(enteringKid);
        }
    }

    public void leave() {
        if (!this.playing.isEmpty()) {
            Kid leavingKid = this.playing.removeLast();
            this.waiting.addFirst(leavingKid);
        }
    }

    public Kid remove(String name) {
        Kid removedKidFromWaiting = removeFromList(name, this.waiting);
        if (removedKidFromWaiting != null) return removedKidFromWaiting;
        Kid removedKidFromPlaying = removeFromList(name, this.playing);
        if (removedKidFromPlaying != null) return removedKidFromPlaying;
        return null;
    }
}
