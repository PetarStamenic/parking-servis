package raf.teamEpic.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import raf.teamEpic.domain.Admin;
import raf.teamEpic.domain.Client;

@Profile({"default"})
@Component
public class Seeder implements CommandLineRunner {

    private UserRepository userRepository;

    public Seeder(UserRepository userRepository){this.userRepository = userRepository;}

    @Override
    public void run(String... args) throws Exception {
        Client client1 = new Client();
        client1.setUsername("client");
        client1.setPassword("client");
        client1.setFirstName("Petar");
        client1.setLastName("Stamenic");
        client1.setEmail("pstamenic7721rn@raf.rs");
        client1.setPhone("0600176999");
        client1.setSocialSecurityNumber("1234567890123");
        client1.setPassportNumber("123456");
        client1.setTotalDays(25);

        Client client2 = new Client();
        client2.setUsername("client2");
        client2.setPassword("client2");
        client2.setFirstName("Test");
        client2.setLastName("Tester");
        client2.setEmail("pstamenicrn@raf.rs");
        client2.setPhone("0600176999");
        client2.setSocialSecurityNumber("9876543219876");
        client2.setPassportNumber("987654");
        client2.setTotalDays(22);

        Client client3 = new Client();
        client3.setUsername("client3");
        client3.setPassword("client3");
        client3.setFirstName("Tesaf");
        client3.setLastName("SDGSDAHG");
        client3.setEmail("p7721rn@raf.rs");
        client3.setPhone("0600176999");
        client3.setSocialSecurityNumber("1234577890123");
        client3.setPassportNumber("124456");
        client3.setTotalDays(25);

        Admin admin = new Admin();
        admin.setActive(true);
        admin.setUsername("servis");
        admin.setPassword("servis");
        admin.setFirstName("servis");
        admin.setLastName("servis");
        admin.setEmail("servis.servis@gmail.com");

        userRepository.save(admin);
        userRepository.save(client1);
        userRepository.save(client2);
        userRepository.save(client3);
    }
}
