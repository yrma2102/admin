package com.coding.web.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.coding.web.models.User;
import com.coding.web.repositories.IUserRepository;

@Service
public class UserService {
	@Autowired
	private IUserRepository userRepo;

// registrar el usuario y hacer Hash a su password
	public User registerUser(User user, BindingResult resultado) {
		User userRegistrado = userRepo.findByEmail(user.getEmail());
		if (userRegistrado != null) {
			resultado.rejectValue("email", "Matches", "Correo electronico ya existe");
		}
		if(!user.getPassword().equals(user.getPasswordConfirmation())) {
			resultado.rejectValue("password", "Matches", "La confirmacion de contraseña debe coincidir");
		}
		if(resultado.hasErrors()) {
        	return null;
        }

		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		if(!hayAdmins()) {
			user.setAdmin(true);
		}
		return userRepo.save(user);
	}

	// encontrar un usuario por su email
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	// encontrar un usuario por su id
	public User findUserById(Long id) {
		Optional<User> u = userRepo.findById(id);

		if (u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}

//autenticar usuario (LOGIN)

	public boolean authenticateUser(String email, String password, BindingResult resultado) {
		// primero encontrar el usuario por su email
		User user = userRepo.findByEmail(email);
//		System.out.println(user + " AQUI USER MAIL");
		// si no lo podemos encontrar por su email, retornamos false
		if (user == null) {
			resultado.rejectValue("email", "Matches", "Email no válido");
			return false;
		} else {
			// si el password coincide devolvemos true, sino, devolvemos false
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				resultado.rejectValue("password", "Matches", "Password no es válido");
				return false;
			}
		}
	}
	
	public boolean hayAdmins() {
		List<User> usuariosAdmin= userRepo.findByAdminTrue();
		if(usuariosAdmin.size() >=1) {
			return true;
		}
		return false;
	}
	
	public void actualizarUser(User user) {
		userRepo.save(user);
	}
	
	public List<User> usuarioAll(){
		return userRepo.findAll();
	}
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
}
