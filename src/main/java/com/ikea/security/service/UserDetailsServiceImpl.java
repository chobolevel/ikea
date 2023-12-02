package com.ikea.security.service;

import com.ikea.entity.user.User;
import com.ikea.mapper.user.UserMapper;
import com.ikea.security.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User findUser = userMapper.findOne(User.builder().username(username).build());

    if(findUser == null) {
      throw new UsernameNotFoundException("회원정보를 찾을 수 없습니다.");
    }

    return new CustomUserDetails(findUser);
  }

}
