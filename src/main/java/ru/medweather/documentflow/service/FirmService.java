package ru.medweather.documentflow.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.medweather.documentflow.repos.FirmRepos;

@Service
public class FirmService implements UserDetailsService {

    private final FirmRepos firmRepos;

    public FirmService(FirmRepos firmRepos) {
        this.firmRepos = firmRepos;
    }

    /**
     * Возвращает пользователя
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (firmRepos.findByUsername(username) == null)
           throw new UsernameNotFoundException("No firm found with username: " + username);
        return firmRepos.findByUsername(username);
    }
}
