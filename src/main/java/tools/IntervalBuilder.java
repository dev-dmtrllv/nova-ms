package tools;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * @author Ronan
 */
public class IntervalBuilder {
    private final List<Line2D> intervalLimits = new ArrayList<>();
    private final Lock intervalRlock;
    private final Lock intervalWlock;

    public IntervalBuilder() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
        this.intervalRlock = readWriteLock.readLock();
        this.intervalWlock = readWriteLock.writeLock();
    }

    private void refitOverlappedIntervals(int st, int en, int newFrom, int newTo) {
        List<Line2D> checkLimits = new ArrayList<>(intervalLimits.subList(st, en));

        float newLimitX1, newLimitX2;
        if (!checkLimits.isEmpty()) {
            Line2D firstLimit = checkLimits.get(0);
            Line2D lastLimit = checkLimits.get(checkLimits.size() - 1);

            newLimitX1 = (float) ((newFrom < firstLimit.getX1()) ? newFrom : firstLimit.getX1());
            newLimitX2 = (float) ((newTo > lastLimit.getX2()) ? newTo : lastLimit.getX2());

            for (@SuppressWarnings("unused") Line2D limit : checkLimits) {
                intervalLimits.remove(st);
            }
        } else {
            newLimitX1 = newFrom;
            newLimitX2 = newTo;
        }

        intervalLimits.add(st, new Line2D.Float(newLimitX1, 0, newLimitX2, 0));
    }

    private int bsearchInterval(int point) {
        int st = 0, en = intervalLimits.size() - 1;

        int mid, idx;
        while (en >= st) {
            idx = (st + en) / 2;
            mid = (int) intervalLimits.get(idx).getX1();

            if (mid == point) {
                return idx;
            } else if (mid < point) {
                st = idx + 1;
            } else {
                en = idx - 1;
            }
        }

        return en;
    }

    public void addInterval(int from, int to) {
        intervalWlock.lock();
        try {
            int st = bsearchInterval(from);
            if (st < 0) {
                st = 0;
            } else if (intervalLimits.get(st).getX2() < from) {
                st += 1;
            }

            int en = bsearchInterval(to);
            if (en < st) {
                en = st - 1;
            }

            refitOverlappedIntervals(st, en + 1, from, to);
        } finally {
            intervalWlock.unlock();
        }
    }

    public boolean inInterval(int point) {
        return inInterval(point, point);
    }

    public boolean inInterval(int from, int to) {
        intervalRlock.lock();
        try {
            int idx = bsearchInterval(from);
            return idx >= 0 && to <= intervalLimits.get(idx).getX2();
        } finally {
            intervalRlock.unlock();
        }
    }

    public void clear() {
        intervalWlock.lock();
        try {
            intervalLimits.clear();
        } finally {
            intervalWlock.unlock();
        }
    }

}
