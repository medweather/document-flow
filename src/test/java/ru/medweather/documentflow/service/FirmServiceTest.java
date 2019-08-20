package ru.medweather.documentflow.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import ru.medweather.documentflow.models.Firm;
import ru.medweather.documentflow.repos.FirmRepos;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirmServiceTest {

    @Autowired
    private FirmService firmService;

    @MockBean
    private FirmRepos firmRepos;

    @Test
    public void loadUserByUsername() {
        Firm firm = new Firm();
        firm .setUsername("hogwarts");

        Mockito.doReturn(firm)
                .when(firmRepos)
                .findByUsername(firm.getUsername());

        UserDetails userDetailsResult = firmService.loadUserByUsername(firm.getUsername());

        assertEquals(userDetailsResult, firm);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameNull() {
        Firm firm = new Firm();
        firm .setUsername("hogwarts");

        UserDetails userDetailsResult = firmService.loadUserByUsername(firm.getUsername());

        assertEquals(userDetailsResult, firm);
    }
}