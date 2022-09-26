package com.example.qlnvproject.service;

import com.example.qlnvproject.model.Employee;
import com.example.qlnvproject.repository.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private employeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee != null){
            return User.withUsername(username).password(employee.getPass()).roles(employee.getRole().getRoleName()).build();
        }
        throw new UsernameNotFoundException("User not found with the name " + username);
    }
}
