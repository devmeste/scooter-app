package com.arqui.integrador.initializer;

import org.springframework.stereotype.Component;

import com.arqui.integrador.model.User;
import com.arqui.integrador.repository.IUserRepository;

@Component
public class UserInitializer {
	
    private IUserRepository userRepository;
    
    public UserInitializer(IUserRepository userRepository) {
    	this.userRepository = userRepository;
    }
    
    public void init(){
    	this.userRepository.save(User.builder().username("meste73")
    			.cellphone(2494380393L).email("elmeste.88@gmail.com").firstname("Ezequiel").surname("Mestelan").build());
    	
    	this.userRepository.save(User.builder().username("frank")
    			.cellphone(2494554466L).email("fdeluccho@gmail.com").firstname("Franco").surname("Delucci").build());
    	
    	this.userRepository.save(User.builder().username("matt")
    			.cellphone(2494745475L).email("matt.s@gmail.com").firstname("Matias").surname("Sanchez Abrego").build());
    	
        this.userRepository.save(User.builder().username("jebu")
        		.cellphone(2494332456L).email("eljebu@gmail.com").firstname("Jesus").surname("Diaz").build());
        
        this.userRepository.save(User.builder().username("carlo")
        		.cellphone(2494252314L).email("elcharly@gmail.com").firstname("Carlos").surname("Garcia").build());
        
        this.userRepository.save(User.builder().username("john")
        		.cellphone(2494313665L).email("john.rambo@gmail.com").firstname("Juan").surname("Rodriguez").build());
    }
}
