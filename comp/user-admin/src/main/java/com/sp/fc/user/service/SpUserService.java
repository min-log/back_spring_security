package com.sp.fc.user.service;

import com.sp.fc.user.domain.SpAuthority;
import com.sp.fc.user.domain.SpUser;
import com.sp.fc.user.repository.SpUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;


@Service
@Transactional
public class SpUserService implements UserDetailsService {
    private final SpUserRepository userRepository;

    public SpUserService(SpUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //권한
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findSpUserByEmail(username)
                // 없을시
                .orElseThrow(()-> new UsernameNotFoundException(username));
    }


    // user 찾기
    public Optional<SpUser> findUser(String email){
        return userRepository.findSpUserByEmail(email);
    }

    //user 저장
    public SpUser save(SpUser user){
        return userRepository.save(user);
    }


    //권한 추가
    public void addAuthority(Long userId,String authority){
        userRepository.findById(userId).ifPresent(user->{
            SpAuthority newRole = new SpAuthority(user.getUserId(),authority);
            if (user.getAuthorities() == null){
                HashSet<SpAuthority> authorities = new HashSet<>();
                authorities.add(newRole);
                user.setAuthorities(authorities);
                save(user);
            } else if (!user.getAuthorities().contains(newRole)) {
                HashSet<SpAuthority> authorities = new HashSet<>();
                authorities.addAll(user.getAuthorities());
                authorities.add(newRole);
                user.setAuthorities(authorities);
                save(user);

            }
        });
    }




}
