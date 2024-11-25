package vn.HiepKa.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.HiepKa.entity.UserInfo;
import vn.HiepKa.repository.UserInfoRepository;
@Service
public record UserService (UserInfoRepository repository,
							PasswordEncoder passwordEncoder){
	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "Add user successed ";
	}
}

