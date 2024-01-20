package net.server.coordinator.login;

import config.YamlConfig;
import net.server.Server;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
/**
 * @author Ronan
 */
public class LoginStorage {
    private final ConcurrentHashMap<Integer, List<Instant>> loginHistory = new ConcurrentHashMap<>(); // Key: accountId

    public boolean registerLogin(int accountId) {
        List<Instant> attempts = loginHistory.computeIfAbsent(accountId, k -> new ArrayList<>());

        synchronized (attempts) {
            final Instant attemptExpiry = Instant.ofEpochMilli(Server.getInstance().getCurrentTime() + YamlConfig.config.server.LOGIN_ATTEMPT_DURATION);

            if (attempts.size() > YamlConfig.config.server.MAX_ACCOUNT_LOGIN_ATTEMPT) {
                Collections.fill(attempts, attemptExpiry);
                return false;
            }

            attempts.add(attemptExpiry);
            return true;
        }
    }

    public void clearExpiredAttempts() {
        final Instant now = Instant.ofEpochMilli(Server.getInstance().getCurrentTime());
        List<Integer> accountIdsToClear = new ArrayList<>();

        for (Entry<Integer, List<Instant>> loginEntries : loginHistory.entrySet()) {
            final List<Instant> attempts = loginEntries.getValue();
            synchronized (attempts) {
                List<Instant> attemptsToRemove = attempts.stream()
                        .filter(attempt -> attempt.isBefore(now))
                        .collect(Collectors.toList());

                for (Instant attemptToRemove : attemptsToRemove) {
                    attempts.remove(attemptToRemove);
                }

                if (attempts.isEmpty()) {
                    accountIdsToClear.add(loginEntries.getKey());
                }
            }
        }

        for (Integer accountId : accountIdsToClear) {
            loginHistory.remove(accountId);
        }
    }
}
